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
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.*;
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.controls.menu.AutomationSystemMenu;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.List;

/**
 * Created by inpwt on 26/01/2016.
 *
 * Encapsulates the 'window' element
 */
public class AutomationWindow extends AutomationContainer {

    private Window windowPattern;

    /**
     * Focuses this control.
     */
    public void focus() {
        this.element.setFocus();
    }

    /**
     * Constructor for the AutomationWindow
     * @param element The underlying element
     */
    public AutomationWindow (AutomationElement element) {
        super(element);

        try {
            this.windowPattern = this.getWindowPattern();
        } catch (PatternNotFoundException ex) {
            // log this
        }
    }

    /**
     * Gets the status bar associated with this window
     * @return The status bar
     * @throws AutomationException Automation issue
     */
    public AutomationStatusBar getStatusBar() throws AutomationException {
        PointerByReference condition = this.createTrueCondition();

        List<AutomationElement> collection = this.findAll(new TreeScope(TreeScope.TreeScope_Descendants), condition.getValue());

        AutomationStatusBar found = null;

        for(AutomationElement element: collection) {
            int retVal = element.currentControlType();

            if (retVal == ControlType.StatusBar.getValue()) {
                found = new AutomationStatusBar(element);
                break;
            }
        }

        for(AutomationElement element: collection) {
            int retVal = element.currentControlType();

            if (retVal == ControlType.StatusBar.getValue()) {
                found = new AutomationStatusBar(element);
                break;
            }
        }

        return found;
    }

    /**
     * Gets the system menu associated with this window
     * @return The system menu
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSystemMenu getSystemMenu() throws AutomationException {
        return (new AutomationSystemMenu(this.getControlByControlType(0, ControlType.MenuBar)));
    }

    /**
     * Gets the main menu associated with this window.
     *
     * @return The main menu
     * @throws AutomationException Something has gone wrong
     */
    public AutomationMainMenu getMainMenu() throws AutomationException {
        return getMainMenu(1);
    }

    /**
     * Gets the main menu associated with this window.
     *
     * @param offset The menu offset to get
     * @return The main menu
     * @throws AutomationException Something has gone wrong
     */
    public AutomationMainMenu getMainMenu(int offset) throws AutomationException {
        return (new AutomationMainMenu(this.element, this.getControlByControlType(offset, ControlType.MenuBar)));
    }

    /**
     * Gets the menu associated with this window.
     * @param index Index of the menu
     * @return The menu
     * @throws AutomationException Something has gone wrong
     */
    public AutomationMainMenu getMenu(int index) throws AutomationException {
        return (new AutomationMainMenu(this.element, this.getControlByControlType(0, ControlType.Menu)));
    }

    /**
     * Waits for this window to become idle.
     * @param timeout The timeout
     * @throws AutomationException Something has gone wrong
     */
    public void waitForInputIdle(int timeout) throws AutomationException {
        this.windowPattern.waitForInputIdle(timeout);
    }

    /**
     * Maximize the window
     * @throws AutomationException Something has gone wrong
     */
    public void maximize() throws AutomationException {
        this.windowPattern.maximize();
    }

    /**
     * Minimize the window
     * @throws AutomationException Something has gone wrong
     */
    public void minimize() throws AutomationException {
        this.windowPattern.minimize();
    }

    /**
     * Finds the child window with the given title
     * @param title Title to search for
     * @return The child window
     * @throws AutomationException Something has gone wrong
     */
    public AutomationWindow getWindow(String title) throws AutomationException {
        AutomationElement item = null;

        for (int count = 0; count < 10; count++) {
            try {
                item = this.findFirst(new TreeScope(TreeScope.TreeScope_Descendants),
                        this.createAndCondition(
                                this.createNamePropertyCondition(title).getValue(),
                                this.createControlTypeCondition(ControlType.Window).getValue()));
            } catch (ElementNotFoundException ex) {
                logger.warn("Failed to find window");
            }

            if (item != null) {
                logger.warn("Found window");
                break;
            } else {
                try {
                    logger.warn("Did not find window, retrying");
                    // Wait for it
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    // interrupted
                }
            }
        }

        if (item == null) {
            throw new ElementNotFoundException();
        }

        return new AutomationWindow(item);
    }

    /**
     * Whether this window is modal
     * @return True if modal
     * @throws AutomationException Something has gone wrong
     */
    public boolean isModal() throws AutomationException {
        return this.windowPattern.isModal();
    }

    /**
     * Whether this window is topmost
     * @return True if topmost
     * @throws AutomationException Something has gone wrong
     */
    public boolean isTopMost() throws AutomationException {
        return this.windowPattern.isTopMost();
    }

    /**
     * Get the AutomationTitleBar associated with the given name
     * @return The AutomationTitleBar
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTitleBar getTitleBar() throws AutomationException {
        return new AutomationTitleBar(this.getControlByControlType(0, ControlType.TitleBar));
    }

    /**
     * Sets transparency of the window
     * @param alpha 0..255 alpha attribute
     */
    public void setTransparency(int alpha) {
        WinDef.HWND hwnd = this.getNativeWindowHandle();

        User32.INSTANCE.SetWindowLong(hwnd, User32.GWL_EXSTYLE, User32.WS_EX_LAYERED);
        User32.INSTANCE.SetLayeredWindowAttributes(hwnd, 0, (byte)alpha, User32.LWA_ALPHA);
    }
}