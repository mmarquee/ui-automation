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

import com.sun.jna.platform.win32.*;
import com.sun.jna.*;
import mmarquee.automation.uiautomation.*;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationApplication extends AutomationBase {
    private final User32 user32 = User32.INSTANCE;
    private WinNT.HANDLE handle = new WinNT.HANDLE();

    private static final WinDef.DWORD INFINITE_TIMEOUT = new WinDef.DWORD(0xFFFFFFFF);

    /**
     * Waits for the application to accept input, i.e. not be idle
     * @param timeout
     */
    public void waitForInputIdle(int timeout) {
        user32.WaitForInputIdle(this.handle, new WinDef.DWORD(timeout));
    }

    /**
     * Waits for the application to accept input, i.e. not be idle, with maximum timeout
     */
    public void waitForInputIdle() {
        user32.WaitForInputIdle(this.handle, INFINITE_TIMEOUT);
    }

    /**
     * Gets the window associated with the title
     * @param title The title to look for
     * @return An AutomationWindow
     */
    public AutomationWindow getWindow(String title) throws ElementNotFoundException {

        AutomationElement foundElement = null;

        List<AutomationElement> collection = this.findAll();

        int length = collection.size();

        for(int count=0; count < length; count++){
            AutomationElement element = collection.get(count);
            String name = element.element.currentName();
            if (name.equals(title)){
                foundElement = element;
                break;
            }
        }

        if (foundElement != null) {
            return new AutomationWindow(foundElement, this.uiAuto);
        } else {
            throw new ElementNotFoundException();
        }
    }

    /**
     * Constructor for the AutomationApplication.
     * @param element The underlying automation element
     * @param uiAuto The IUIAutomation associated with this session
     * @param handle The handle of this application.
     */
    public AutomationApplication (AutomationElement element, IUIAutomation uiAuto, WinNT.HANDLE handle) {
        super(element, uiAuto);
        this.handle = handle;
    }

    /**
     * Constructor for the AutomationApplication.
     * @param element The underlying automation element
     * @param uiAuto The IUIAutomation associated with this session
     * @param process The process for this application.
     */
    public AutomationApplication (AutomationElement element, IUIAutomation uiAuto, Process process) {
        super(element, uiAuto);
        // From : http://www.golesny.de/p/code/javagetpid.

        if (process.getClass().getName().equals("java.lang.Wind32Process") ||
                process.getClass().getName().equals("java.lang.ProcessImpl")) {
            try {
                Field f = process.getClass().getDeclaredField("handle");
                f.setAccessible(true);
                long handl = f.getLong(process);
                this.handle.setPointer(Pointer.createConstant(handl));
            } catch (Throwable e) {
              // Handle the error nicely
            }
        }
    }
}
