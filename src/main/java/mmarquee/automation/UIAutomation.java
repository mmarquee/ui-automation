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

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.Tlhelp32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.win32.W32APIOptions;
import com.sun.jna.Native;
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

        return new AutomationApplication(rootElement, uiAuto, process);
    }

    /**
     * Attaches to the application process
     * @param process Process to attach to
     * @return AutomationApplication that represents the application
     */
    public AutomationApplication attach(Process process) {
        return new AutomationApplication(rootElement, uiAuto, process);
    }

    /**
     * Attachs or launches the application
     * @param command Command to be started
     * @return AutomationApplication that represents the application
     */
    public AutomationApplication launchOrAttach(String... command) {
        File file = new File(command[0]);
        String filename = file.getName();

        Kernel32 kernel32 = (Kernel32) Native.loadLibrary(Kernel32.class, W32APIOptions.UNICODE_OPTIONS);
        Tlhelp32.PROCESSENTRY32.ByReference processEntry = new Tlhelp32.PROCESSENTRY32.ByReference();

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
            return new AutomationApplication(rootElement, uiAuto, new WinNT.HANDLE(processEntry.th32ProcessID));
        }
    }

    /**
     * Gets the desktop window associated with the title
     * @param title Title to search for
     * @return AutomationWindow The found window
     */
    public AutomationWindow getDesktopWindow(String title) {
        List<AutomationWindow> result = new ArrayList<AutomationWindow>();

        IUIAutomationCondition condition = uiAuto.createTrueCondition();

        IUIAutomationElementArray collection = this.rootElement.findAll(TreeScope.TreeScope_Children, condition);

        int length = collection.length();

        AutomationWindow foundElement = null;

        for (int count = 0; count < length; count++) {
            IUIAutomationElement element = collection.getElement(count);
            String name = element.currentName();

            if (name.equals(title)) {
                foundElement = new AutomationWindow(element, this.uiAuto);
            }
        }

        return foundElement;
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

            result.add(new AutomationWindow(element, this.uiAuto));
        }

        return result;
    }
}
