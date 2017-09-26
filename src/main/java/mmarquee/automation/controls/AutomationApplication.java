/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
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
import com.sun.jna.platform.win32.*;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.utils.Utils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by Mark Humphreys on 26/01/2016.
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

    private User32 user32;

    /**
     * A very, very long timeout
     */
    private static final WinDef.DWORD INFINITE_TIMEOUT = new WinDef.DWORD(0xFFFFFFFF);

    /**
     * Waits for the application to accept input, i.e. not be idle
     * @param timeout Timeout to wait for
     */
    public void waitForInputIdle(int timeout) {
        if (user32 == null) {
            user32 = User32.INSTANCE;
        }
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
        if (user32 == null) {
            user32 = User32.INSTANCE;
        }
        user32.WaitForInputIdle(this.handle, INFINITE_TIMEOUT);
    }

    /**
     * Gets the window associated with the title
     * @param title The title to look for
     * @return An AutomationWindow
     * @throws AutomationException Cannot find element
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationWindow getWindow(String title)
            throws PatternNotFoundException, AutomationException {

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
            throw new ElementNotFoundException(title);
        }
    }

    /**
     * Constructor for the AutomationApplication.
     * @param element The underlying automation element
     * @param handle The handle of this application.
     * @param attached if we attach or launch the application?
     * @throws AutomationException Automation library error
     */
    public AutomationApplication (AutomationElement element, WinNT.HANDLE handle, boolean attached) throws AutomationException  {
        super(element);
        this.handle = handle;
        this.isAttached = attached;
    }

    public AutomationApplication (AutomationElement element, WinNT.HANDLE handle, boolean attached, User32 user32) throws AutomationException  {
        super(element);
        this.handle = handle;
        this.isAttached = attached;
        this.user32 = user32;
    }

    /**
     * Constructor for the AutomationApplication.
     * Detection of already running application is taken from:
     *   http://www.golesny.de/p/code/javagetpid.
     * @param element The underlying automation element
     * @param process The process for this application.
     * @param attached if we attach or launch the application?
     * @throws AutomationException Automation library error
     * */
    public AutomationApplication (AutomationElement element, Process process, boolean attached) throws AutomationException {
        super(element);

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

    /**
     * Closes the process
     * @param title Title of the window to close
     */
    public void close(String title) {
        if (user32 == null) {
            user32 = User32.INSTANCE;
        }

        WinDef.HWND hwnd = user32.FindWindow(null, title);

        if (hwnd != null) {
            Utils.closeProcess(hwnd);
        }
    }

    /**
     * Quits the process
     * @param title Title of the window to quit
     */
    public void quit(String title) {
        if (user32 == null) {
            user32 = User32.INSTANCE;
        }

        WinDef.HWND hwnd = user32.FindWindow(null, title);

        if (hwnd != null) {
            Utils.quitProcess(hwnd);
        }
    }
}
