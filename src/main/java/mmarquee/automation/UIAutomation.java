/*
 * Copyright 2016 inpwtepydjuf@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mmarquee.automation;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.*;
import com.sun.jna.win32.W32APIOptions;
import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.uiautomation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HumphreysM on 26/01/2016.
 */
public class UIAutomation {

    private IUIAutomationElement rootElement;
    private IUIAutomation uiAuto;
    private Process process;

    public UIAutomation() {

        uiAuto = ClassFactory.createCUIAutomation();

        rootElement = uiAuto.getRootElement();
    }

    /**
     * Launches the application
     * @param command The command to be called
     * @return AutomationApplication that represents the application
     */
    public AutomationApplication launch(String... command) {
        ProcessBuilder pb = new ProcessBuilder(command);

        try {
            process = pb.start();
        } catch (java.io.IOException ex) {
            // Never do this in real code
        }

        return new AutomationApplication(new AutomationElement(rootElement), uiAuto, process);
    }

    /**
     * Attaches to the application process
     * @param process Process to attach to
     * @return AutomationApplication that represents the application
     */
    public AutomationApplication attach(Process process) {
        return new AutomationApplication(new AutomationElement(rootElement), uiAuto, process);
    }

    /**
     * Attaches or launches the application
     * @param command Command to be started
     * @return AutomationApplication that represents the application
     */
    public AutomationApplication launchOrAttach(String... command) throws Exception {
        File file = new File(command[0]);
        String filename = file.getName();

        Kernel32 kernel32 = (Kernel32) Native.loadLibrary(Kernel32.class, W32APIOptions.UNICODE_OPTIONS);
        final Tlhelp32.PROCESSENTRY32.ByReference processEntry = new Tlhelp32.PROCESSENTRY32.ByReference();

        WinNT.HANDLE snapshot = kernel32.CreateToolhelp32Snapshot(Tlhelp32.TH32CS_SNAPPROCESS, new WinDef.DWORD(0));

        boolean found = false;

        try {
            while (kernel32.Process32Next(snapshot, processEntry)) {
                String fname = Native.toString(processEntry.szExeFile);

                if (fname.equals(filename)) {
                    found = true;
                    break;
                }
            }
        } finally {
            kernel32.CloseHandle(snapshot);
        }

        if (!found) {
            return this.launch(command);
        } else {
            WinNT.HANDLE handle = Kernel32.INSTANCE.OpenProcess (
                0x0400| /* PROCESS_QUERY_INFORMATION */
                0x0800| /* PROCESS_SUSPEND_RESUME */
                0x0001| /* PROCESS_TERMINATE */
                0x00100000 /* SYNCHRONIZE */,
                false,
                processEntry.th32ProcessID.intValue());

            if (handle == null) {
                throw new Exception(Kernel32Util.formatMessageFromLastErrorCode(Kernel32.INSTANCE.GetLastError()));
            }

            return new AutomationApplication(new AutomationElement(rootElement), uiAuto, handle);
        }
    }

    /**
     * Gets the desktop window associated with the title
     * @param title Title to search for
     * @return AutomationWindow The found window
     */
    public AutomationWindow getDesktopWindow(String title) {
        IUIAutomationElement element = null;

        for (int loop = 0; loop <10; loop++) {
            element = this.rootElement.findFirst(TreeScope.TreeScope_Descendants,
                    this.uiAuto.createAndCondition(
                            this.uiAuto.createPropertyCondition(PropertyID.Name, title),
                            this.uiAuto.createPropertyCondition(PropertyID.ControlType, ControlType.Window)));

            if (element != null) {
                break;
            }
        }

        return new AutomationWindow(new AutomationElement(element), this.uiAuto);
    }

    /**
     * Gets the list of desktop windows
     * @return List of desktop windows
     */
    public List<AutomationWindow> getDesktopWindows() {

        List<AutomationWindow> result = new ArrayList<AutomationWindow>();

        IUIAutomationCondition condition = uiAuto.createTrueCondition();

        IUIAutomationElementArray collection = this.rootElement.findAll(TreeScope.TreeScope_Children, condition);

        int length = collection.length();

        for (int count = 0; count < length; count++) {
            IUIAutomationElement element = collection.getElement(count);

            result.add(new AutomationWindow(new AutomationElement(element), this.uiAuto));
        }

        return result;
    }
}
