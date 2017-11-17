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

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.utils.Utils;

/**
 * @author Mark Humphreys
 * Date 26/01/2016.
 *
 * Wrapper around the Application element.
 */
public class AutomationApplication extends AutomationBase {

    /**
     * The window handle.
     */
    private WinNT.HANDLE handle = new WinNT.HANDLE();

    /**
     * Did we attach to the application, or start it.
     */
    private boolean isAttached = false;

    /**
     * The User32 instance.
     */
    private User32 user32;

    /**
     * A very, very long timeout.
     */
    private static final WinDef.DWORD INFINITE_TIMEOUT = new WinDef.DWORD(0xFFFFFFFF);

    /**
     * A default, normal timeout.
     */
    public static final int SHORT_TIMEOUT = 5000;

    /**
     * Waits for the application to accept input, i.e. not be idle.
     * @param timeout Timeout to wait for.
     */
    public void waitForInputIdle(final int timeout) {
        if (user32 == null) {
            user32 = User32.INSTANCE;
        }
        user32.WaitForInputIdle(this.handle, new WinDef.DWORD(timeout));
    }

    /**
     * Gets whether the application was already running.
     * @return Attached or not.
     */
    public boolean getIsAttached() {
        return this.isAttached;
    }

    /**
     * Sets the isAttached value.
     * @param value True or false.
     */
    public void setIsAttached(final boolean value) {
        this.isAttached = value;
    }

    /**
     * Waits for the application to accept input, i.e. not be idle, with maximum timeout.
     */
    public void waitForInputIdle() {
        if (user32 == null) {
            user32 = User32.INSTANCE;
        }

        user32.WaitForInputIdle(this.handle, INFINITE_TIMEOUT);
    }

    /**
     * Gets the window associated with the title.
     * @param title The title to look for.
     * @return An AutomationWindow.
     * @throws AutomationException Cannot find element.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    private AutomationWindow getWindow(final String title)
            throws PatternNotFoundException, AutomationException {

        AutomationElement foundElement = null;

        List<AutomationElement> collection = this.findAll();

        for (AutomationElement element : collection) {
            String name = element.getName();
            if (name.equals(title)) {
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
     * Gets the window associated with the title.
     * @param titlePattern A pattern matching the title to look for.
     * @return An AutomationWindow.
     * @throws AutomationException Cannot find element.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    private AutomationWindow getWindow(final Pattern titlePattern)
            throws PatternNotFoundException, AutomationException {

        AutomationElement foundElement = null;

        List<AutomationElement> collection = this.findAll();

        for (AutomationElement element : collection) {
            String name = element.getName();
            if (name != null && titlePattern.matcher(name).matches()) {
                foundElement = element;
                break;
            }
        }

        if (foundElement != null) {
            return new AutomationWindow(foundElement);
        } else {
            throw new ElementNotFoundException("matching " + titlePattern);
        }
    }

    /**
     * Gets the window, using the search criteria.
     *
     * @param search Matcher for the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationWindow getWindow(final Search search) throws PatternNotFoundException, AutomationException {
        if (search.getHasPattern()) {
            return getWindow(search.getPattern());
        } else if (search.getHasName()) {
            return getWindow(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Constructor for the AutomationApplication.
     *
     * @param element The underlying automation element.
     * @param inHandle The handle of this application.
     * @param attached if we attach or launch the application.
     * @throws AutomationException Automation library error.
     */
    public AutomationApplication(final AutomationElement element,
                                 final WinNT.HANDLE inHandle,
                                 final boolean attached)
            throws AutomationException  {
        super(element);
        this.handle = inHandle;
        this.isAttached = attached;
    }

    /**
     * Constructor for the AutomationApplication.
     *
     * @param element The underlying automation element.
     * @param inHandle The handle of this application.
     * @param attached if we attach or launch the application.
     * @param automation Automation instance.
     * @throws AutomationException Automation library error.
     */
    public AutomationApplication(final AutomationElement element,
                                 final WinNT.HANDLE inHandle,
                                 final boolean attached,
                                 final UIAutomation automation)
            throws AutomationException  {
        super(element, automation);
        this.handle = inHandle;
        this.isAttached = attached;
    }

    /**
     * Constructor for the AutomationApplication.
     *
     * @param element The underlying automation element.
     * @param inHandle The handle of this application.
     * @param attached if we attach or launch the application.
     * @param inUser32 The User32 instance.
     * @throws AutomationException Error in the automation library.
     */
    public AutomationApplication(final AutomationElement element,
                                 final WinNT.HANDLE inHandle,
                                 final boolean attached,
                                 final User32 inUser32)
            throws AutomationException {
        super(element);
        this.handle = inHandle;
        this.isAttached = attached;
        this.user32 = inUser32;
    }

    /**
     * Constructor for the AutomationApplication.
     *
     * @param element The underlying automation element.
     * @param inHandle The handle of this application.
     * @param attached if we attach or launch the application.
     * @param inUser32 The User32 instance.
     * @param instance Automation instance.
     * @throws AutomationException Automation library error
     */
    public AutomationApplication(final AutomationElement element,
                                 final WinNT.HANDLE inHandle,
                                 final boolean attached,
                                 final User32 inUser32,
                                 final UIAutomation instance)
            throws AutomationException {
        super(element, instance);
        this.handle = inHandle;
        this.isAttached = attached;
        this.user32 = inUser32;
    }

    /**
     * Constructor for the AutomationApplication.
     *
     * Detection of already running application is taken from:
     *   http://www.golesny.de/p/code/javagetpid.
     * @param element The underlying automation element
     * @param process The process for this application.
     * @param attached if we attach or launch the application?
     * @throws AutomationException Automation library error
     * */
    public AutomationApplication(final AutomationElement element,
                                 final Process process,
                                 final boolean attached)
            throws AutomationException {
        super(element);

        this.isAttached = attached;

        String name = process.getClass().getName();

        if (name.equals("java.lang.Wind32Process")
                || name.equals("java.lang.ProcessImpl")) {
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
     * Closes the window.
     *
     * @param title Title of the window to close.
     */
    public void close(final String title) {
        if (user32 == null) {
            user32 = User32.INSTANCE;
        }

        WinDef.HWND hwnd = user32.FindWindow(null, title);

        if (hwnd != null) {
            Utils.closeWindow(hwnd);
        }
    }

    /**
     * Closes the window.
     *
     * @param titlePattern a pattern matching the title of the window to close
     */
    public void close(final Pattern titlePattern) {
        if (user32 == null) {
            user32 = User32.INSTANCE;
        }

        WinDef.HWND hwnd = Utils.findWindow(null, titlePattern);

        if (hwnd != null) {
            Utils.closeWindow(hwnd);
        }
    }
    
    /**
     * Quits the process.
     *
     * @param title Title of the window of the process to quit.
     */
    public void quit(final String title) {
        if (user32 == null) {
            user32 = User32.INSTANCE;
        }

        WinDef.HWND hwnd = user32.FindWindow(null, title);

        if (hwnd != null) {
            Utils.quitProcess(hwnd);
        }
    }

    /**
     * Quits the process.
     *
     * @param titlePattern a pattern matching the title of the window of the process to quit
     */
    public void quit(final Pattern titlePattern) {
        if (user32 == null) {
            user32 = User32.INSTANCE;
        }

        final WinDef.HWND hwnd = Utils.findWindow(null, titlePattern);

        if (hwnd != null) {
            Utils.quitProcess(hwnd);
        }
    }

}
