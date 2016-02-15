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

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationApplication extends AutomationBase {
    private Process process;
    private final User32 user32 = User32.INSTANCE;
    private WinNT.HANDLE handle = new WinNT.HANDLE();

    public void waitForInputIdle(int timeout) {
        user32.WaitForInputIdle(this.handle, new WinDef.DWORD(timeout));
    }

    public AutomationWindow getWindow(String title) {

        IUIAutomationElement foundElement = null;

        IUIAutomationElementArray collection = this.findAll();

        int length = collection.length();

        for(int count=0; count < length; count++){
            IUIAutomationElement element = collection.getElement(count);
            String name = element.currentName();
            if (name.equals(title)){
                foundElement = element;
                break;
            }
        }

        return new AutomationWindow(foundElement, this.uiAuto);
    }

    public AutomationApplication (IUIAutomationElement element, IUIAutomation uiAuto, Process process) {
        super(element, uiAuto);
        this.process = process;

        // From : http://www.golesny.de/p/code/javagetpid.

        if (this.process.getClass().getName().equals("java.lang.Wind32Process") ||
                this.process.getClass().getName().equals("java.lang.ProcessImpl")) {
            try {
                Field f = this.process.getClass().getDeclaredField("handle");
                f.setAccessible(true);
                long handl = f.getLong(this.process);
                this.handle.setPointer(Pointer.createConstant(handl));
            } catch (Throwable e) {
              // Handle the error nicely
            }
        }
    }
}
