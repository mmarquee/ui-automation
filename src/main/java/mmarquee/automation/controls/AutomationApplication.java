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
package mmarquee.automation.controls;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.ElementNotFoundException;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by inpwt on 26/01/2016.
 *
 * Wrapper around the Application element
 */
public class AutomationApplication extends AutomationBase {

    /**
     * The window handle
     */
    private WinNT.HANDLE handle = new WinNT.HANDLE();

    /**
     * Did we attach to the application, or start it
     */
    private boolean isAttached = false;

    /**
     * A very, very long timeout
     */
    private static final WinDef.DWORD INFINITE_TIMEOUT = new WinDef.DWORD(0xFFFFFFFF);

    /**
     * Waits for the application to accept input, i.e. not be idle
     * @param timeout Timeout to wait for
     */
    public void waitForInputIdle(int timeout) {
        user32.WaitForInputIdle(this.handle, new WinDef.DWORD(timeout));
    }

    /**
     * Gets whether the application was already running
     * @return Attached or not
     */
    public boolean getIsAttached() {
        return this.isAttached;
    }

    /**
     * Sets the isAttached value
     * @param value True or false
     */
    public void setIsAttached(boolean value) {
        this.isAttached = value;
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
     * @throws ElementNotFoundException Count find element
     */
    public AutomationWindow getWindow(String title) throws ElementNotFoundException {

        AutomationElement foundElement = null;

        List<AutomationElement> collection = this.findAll();

        for (AutomationElement element : collection) {
            String name = element.getName();
            if (name.equals(title)){
                foundElement = element;
                break;
            }
        }

        if (foundElement != null) {
            return new AutomationWindow(foundElement);
        } else {
            throw new ElementNotFoundException();
        }
    }

    /**
     * Constructor for the AutomationApplication.
     * @param element The underlying automation element
     * @param handle The handle of this application.
     * @param attached if we attach or launch the application?
     */
    public AutomationApplication (AutomationElement element, WinNT.HANDLE handle, boolean attached) {
        super(element);
        this.handle = handle;
        this.isAttached = attached;
    }

    /**
     * Constructor for the AutomationApplication.
     * @param element The underlying automation element
     * @param process The process for this application.
     * @param attached if we attach or launch the application?
     * */
    public AutomationApplication (AutomationElement element, Process process, boolean attached) {
        super(element);
        // From : http://www.golesny.de/p/code/javagetpid.

        this.isAttached = attached;

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
