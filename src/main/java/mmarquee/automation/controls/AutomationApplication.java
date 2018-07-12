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
import mmarquee.automation.utils.ExecutableFileInfo;
import mmarquee.automation.utils.Utils;

/**
 * Wrapper around the Application element.
 * @author Mark Humphreys
 * Date 26/01/2016.
 */
public class AutomationApplication extends AutomationBase {

    /**
     * The window handle.
     */
    private WinNT.HANDLE handle = new WinNT.HANDLE();

    /**
     * Did we attach to the application, or start it.
     */
    private boolean isAttached;

    /**
     * The User32 instance.
     */
    private User32 user32;

    /**
     * The process.
     */
    private Process process;

    /**
     * A very, very long timeout.
     */
    private static final WinDef.DWORD INFINITE_TIMEOUT =
            new WinDef.DWORD(0xFFFFFFFF);

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
     * Waits for the application to accept input, i.e. not be idle, with
     * maximum timeout.
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
     */
    public AutomationWindow getWindow(final String title)
            throws AutomationException {

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
            return new AutomationWindow(new ElementBuilder(foundElement));
        } else {
            throw new ElementNotFoundException(title);
        }
    }

    /**
     * Gets the window associated with the classname.
     * @param className The class name of the element to look for
     * @return An AutomationWindow.
     * @throws AutomationException Cannot find element.
     */
    public AutomationWindow getWindowByClassName(final String  className)
            throws AutomationException {

        AutomationElement foundElement = null;

        List<AutomationElement> collection = this.findAll();

        for (AutomationElement element : collection) {
            String name = element.getClassName();
            if (name != null && name.equals(className)) {
                foundElement = element;
                break;
            }
        }

        if (foundElement != null) {
            return new AutomationWindow(new ElementBuilder(foundElement));
        } else {
            throw new ElementNotFoundException("matching " + className);
        }
    }

    /**
     * Gets the window associated with the title.
     * @param titlePattern A pattern matching the title to look for.
     * @return An AutomationWindow.
     * @throws AutomationException Cannot find element.
     */
    public AutomationWindow getWindow(final Pattern titlePattern)
            throws AutomationException {

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
            return new AutomationWindow(new ElementBuilder(foundElement));
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
     */
    public AutomationWindow getWindow(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getWindow(search.getNamePattern());
        } else if (search.getHasName()) {
            return getWindow(search.getName());
        } else if (search.getHasClassName()) {
            return getWindowByClassName(search.getClassName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Constructor for the AutomationApplication.
     *
     * @param builder The builder
     */
    public AutomationApplication(final ElementBuilder builder) {
        super(builder);
        this.isAttached = builder.getAttached();
        this.user32 = builder.getUser32();
        this.process = builder.getProcess();
       // Process process = builder.getProcess();

        if (builder.getHasHandle()) {
            this.handle = builder.getHandle();
        } else {

            String name = process.getClass().getName();

            if (name.equals("java.lang.Wind32Process")
                    || name.equals("java.lang.ProcessImpl")) {
                try {
                    //noinspection JavaReflectionMemberAccess
                    Field f = process.getClass().getDeclaredField("handle");
                    f.setAccessible(true);
                    this.handle.setPointer(Pointer.createConstant(
                            f.getLong(process)));
                } catch (Throwable e) {
                    // Handle the error nicely
                }
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
     * Ends the process associated with the application.
     */
    public void end() {
        Utils.end(process);
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
     * @param titlePattern a pattern matching the title of the window of the
     *                     process to quit
     */
    public void quit(final Pattern titlePattern) {
        if (user32 == null) {
            user32 = User32.INSTANCE;
        }

        final WinDef.HWND handl =
                Utils.findWindow(null, titlePattern);

        if (handl != null) {
            Utils.quitProcess(handl);
        }
    }

    /**
     * Gets the windows version number.
     *
     * @param arg The path
     * @return The version number
     */
    public static String getVersionNumber(final String arg) {
        int[] version = ExecutableFileInfo.getVersionInfo(arg);

        return String.format(
                "%d.%d.%d.%d",
                version[ExecutableFileInfo.MAJOR_VERSION],
                version[ExecutableFileInfo.MINOR_VERSION],
                version[ExecutableFileInfo.RELEASE],
                version[ExecutableFileInfo.BUILD]);
    }
}
