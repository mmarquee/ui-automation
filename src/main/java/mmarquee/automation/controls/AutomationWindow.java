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

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.ControlType;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.controls.menu.AutomationSystemMenu;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.List;

/**
 * @author Mark Humphreys
 * Date 26/01/2016.
 *
 * Encapsulates the 'window' element
 *
 * Currently all of these tests require to run on Windows.
 */
public class AutomationWindow extends AutomationContainer implements Focusable {
    /**
     * The window pattern.
     */
    private Window windowPattern;

    /**
     * The user32 instance.
     */
    private User32 user32;

    /**
     * The sleep duration.
     */
    static final int SLEEP_DURATION = 500;

    /**
     * Focuses this control.
     */
    public void focus() {
        this.element.setFocus();
    }

    /**
     * Constructor for the AutomationWindow.
     * @param element The underlying element.
     * @throws AutomationException Something is wrong in automation.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public AutomationWindow (AutomationElement element)
            throws PatternNotFoundException, AutomationException {
        super(element);

        this.user32 = User32.INSTANCE;
//        this.windowPattern = this.getWindowPattern();
    }

    /**
     * Constructor for the AutomationWindow.
     * @param element The underlying element.
     * @param window The Window pattern.
     * @param container The Container pattern.
     * @param instance Automation instance.
     * @throws AutomationException Something is wrong in automation.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    AutomationWindow (AutomationElement element, Window window, ItemContainer container, UIAutomation instance)
            throws PatternNotFoundException, AutomationException {
        super(element, container, instance);

        this.user32 = User32.INSTANCE;
        this.windowPattern = window;
    }

    /**
     * Constructor for the AutomationWindow.
     * @param element The underlying element.
     * @param window The Window pattern.
     * @param container The Container pattern.
     * @throws AutomationException Something is wrong in automation.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    AutomationWindow (AutomationElement element, Window window, ItemContainer container)
            throws PatternNotFoundException, AutomationException {
        super(element, container);

        this.user32 = User32.INSTANCE;
        this.windowPattern = window;
    }

    /**
     * Constructor for the AutomationWindow.
     * @param element The underlying element.
     * @param window The Window pattern.
     * @param container The Container pattern.
     * @param user32 The user32 instance.
     * @param instance The automation instance.
     * @throws AutomationException Something is wrong in automation.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public AutomationWindow (AutomationElement element, Window window, ItemContainer container, User32 user32, UIAutomation instance)
            throws PatternNotFoundException, AutomationException {
        super(element, container, instance);

        this.user32 = user32;
        this.windowPattern = window;
    }

    /**
     * Constructor for the AutomationWindow.
     * @param element The underlying element.
     * @param window The Window pattern.
     * @param container The Container pattern.
     * @param user32 The user32 instance.
     * @throws AutomationException Something is wrong in automation.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public AutomationWindow (AutomationElement element, Window window, ItemContainer container, User32 user32)
            throws PatternNotFoundException, AutomationException {
        super(element, container);

        this.user32 = user32;
        this.windowPattern = window;
    }

    /**
     * Gets the status bar associated with this window.
     * @return The status bar.
     * @throws AutomationException Automation issue.
     * @throws PatternNotFoundException Did not find the pattern.
     */
    public AutomationStatusBar getStatusBar() throws AutomationException, PatternNotFoundException {
        PointerByReference condition = this.createTrueCondition();

        List<AutomationElement> collection = this.findAll(new TreeScope(TreeScope.Descendants), condition);

        AutomationStatusBar found = null;

        for(AutomationElement element: collection) {
            int retVal = element.getControlType();

            if (retVal == ControlType.StatusBar.getValue()) {
                found = new AutomationStatusBar(element);
                break;
            }
        }

        return found;
    }

    /**
     * Gets the system menu associated with this window.
     * @return The system menu.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public AutomationSystemMenu getSystemMenu() throws PatternNotFoundException, AutomationException {
        return (new AutomationSystemMenu(this.getElementByControlType(0, ControlType.MenuBar)));
    }

    /**
     * Gets the main menu associated with this window.
     *
     * @return The main menu.
     * @throws AutomationException Something has gone wrong.
     */
    public AutomationMainMenu getMainMenu() throws AutomationException {
        return getMainMenu(1);
    }

    /**
     * Gets the main menu associated with this window.
     *
     * @param offset The menu offset to get.
     * @return The main menu.
     * @throws AutomationException Something has gone wrong.
     */
    public AutomationMainMenu getMainMenu(int offset) throws AutomationException {
        return (new AutomationMainMenu(this.element, this.getElementByControlType(offset, ControlType.MenuBar)));
    }

    /**
     * Gets the menu associated with this window.
     * @param index Index of the menu.
     * @return The menu.
     * @throws AutomationException Something has gone wrong.
     */
    public AutomationMainMenu getMenu(int index) throws AutomationException {
        return (new AutomationMainMenu(this.element, this.getElementByControlType(0, ControlType.Menu)));
    }

    /**
     * Waits for this window to become idle.
     * @param timeout The timeout
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    public void waitForInputIdle(int timeout) throws AutomationException, PatternNotFoundException {
        if (this.windowPattern == null) {
            this.windowPattern = this.getWindowPattern();
        }

        this.windowPattern.waitForInputIdle(timeout);
    }

    /**
     * Maximize the window.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public void maximize() throws AutomationException, PatternNotFoundException {
        if (this.windowPattern == null) {
            this.windowPattern = this.getWindowPattern();
        }

        this.windowPattern.maximize();
    }

    /**
     * Minimize the window.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public void minimize() throws AutomationException, PatternNotFoundException {
        if (this.windowPattern == null) {
            this.windowPattern = this.getWindowPattern();
        }

        this.windowPattern.minimize();
    }

    /**
     * Closes the window
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public void close() throws AutomationException, PatternNotFoundException {
        if (this.windowPattern == null) {
            this.windowPattern = this.getWindowPattern();
        }

        this.windowPattern.close();
    }

    /**
     * Finds the child window with the given title.
     * @param title Title to search for.
     * @return The child window.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public AutomationWindow getWindow(String title) throws PatternNotFoundException, AutomationException {
        AutomationElement item = null;

        for (int count = 0; count < 10; count++) {
            try {
                item = this.findFirst(new TreeScope(TreeScope.Descendants),
                        this.createAndCondition(
                                this.createNamePropertyCondition(title),
                                this.createControlTypeCondition(ControlType.Window)));
            } catch (ElementNotFoundException ex) {
                logger.warn("Failed to find `" + title + "` window");
            }

            if (item != null) {
                logger.warn("Found window");
                break;
            } else {
                try {
                    logger.warn("Did not find `" + title + "` window, retrying");
                    // Wait for it
                    Thread.sleep(SLEEP_DURATION);
                } catch (InterruptedException ex) {
                    // interrupted
                }
            }
        }

        if (item == null) {
            throw new ElementNotFoundException(title);
        }

        return new AutomationWindow(item);
    }

    /**
     * Whether this window is modal.
     * @return True if modal.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public boolean isModal() throws AutomationException, PatternNotFoundException {
        if (this.windowPattern == null) {
            this.windowPattern = this.getWindowPattern();
        }

        return this.windowPattern.isModal();
    }

    /**
     * Whether this window is topmost
     * @return True if topmost
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public boolean isTopMost() throws AutomationException, PatternNotFoundException {
        if (this.windowPattern == null) {
            this.windowPattern = this.getWindowPattern();
        }

        return this.windowPattern.isTopMost();
    }

    /**
     * Get the AutomationTitleBar associated with the given name.
     * @return The AutomationTitleBar.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    public AutomationTitleBar getTitleBar() throws AutomationException, PatternNotFoundException {
        return new AutomationTitleBar(this.getElementByControlType(0, ControlType.TitleBar));
    }

    /**
     * Sets transparency of the window.
     * @param alpha 0..255 alpha attribute.
     * @throws Win32Exception WIN32 call has failed.
     * @throws AutomationException Something is wrong in automation.
     */
    public void setTransparency(int alpha) throws Win32Exception, AutomationException {
        WinDef.HWND hwnd = this.getNativeWindowHandle();

        if (user32.SetWindowLong(hwnd, User32.GWL_EXSTYLE, User32.WS_EX_LAYERED) == 0) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }

        if (!user32.SetLayeredWindowAttributes(hwnd, 0, (byte)alpha, User32.LWA_ALPHA)) {
            throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
        }
    }
}