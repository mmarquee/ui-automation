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

import mmarquee.automation.menu.AutomationMainMenu;
import mmarquee.automation.menu.AutomationSystemMenu;
import mmarquee.automation.pattern.*;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 26/01/2016.
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
     * @param uiAuto The automation library
     */
    public AutomationWindow (IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        try {
            this.windowPattern = this.getWindowPattern();
        } catch (PatternNotFoundException ex) {
            // log this
        }
    }

    /**
     * Gets the statusbar associated with this window
     * @return The statusbar
     */
    public AutomationStatusBar getStatusBar() {
        IUIAutomationCondition condition = uiAuto.createTrueCondition();

        IUIAutomationElementArray collection = this.element.findAll(TreeScope.TreeScope_Descendants, condition);

        int length = collection.length();

        AutomationStatusBar found = null;

        for (int count = 0; count < length; count++) {
            IUIAutomationElement element = collection.getElement(count);

            int retVal = element.currentControlType();

            if (retVal == ControlType.StatusBar) {
                found = new AutomationStatusBar(element, uiAuto);
                break;
            }
        }

        return found;
    }

    /**
     * Gets the system menu associated with this window
     * @return The system menu
     */
    public AutomationSystemMenu getSystemMenu() {
        IUIAutomationElement menu = this.getControlByControlType(0, ControlType.MenuBar);
        return (new AutomationSystemMenu(menu, this.uiAuto));
    }

    /**
     * Gets the main menu associated with this window
     * @return The main menu
     */
    public AutomationMainMenu getMainMenu() {
        IUIAutomationElement menu = this.getControlByControlType(1, ControlType.MenuBar);

        return (new AutomationMainMenu(this.element, menu, this.uiAuto));
    }

    /**
     * Waits for this window to become idle.
     * @param timeout The timeout
     */
    public void waitForInputIdle(int timeout) {
        this.windowPattern.waitForInputIdle(timeout);
    }

    /**
     * Maximize the window
     */
    public void maximize() {
        this.windowPattern.maximize();
    }

    /**
     * Minimize the window
     */
    public void minimize() {
        this.windowPattern.minimize();
    }

    /**
     * Finds the child window with the given title
     * @param title Title to search for
     * @return The child window
     */
    public AutomationWindow getWindow(String title) {
        IUIAutomationElement item = null;

        for (int count = 0; count < 10; count++) {
            item = this.findFirst(TreeScope.TreeScope_Descendants,
                    this.createAndCondition(
                            this.createNamePropertyCondition(title),
                            this.createControlTypeCondition(ControlType.Window)));

            if (item != null) {
                break;
            } else {
                try {
                    // Wait for it
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    // interrupted
                }
            }
        }

        return new AutomationWindow(item, this.uiAuto);
    }

    /**
     * Whether this window is modal
     * @return True if modal
     */
    public boolean isModal() {
        return this.windowPattern.isModal();
    }

    /**
     * Whether this window is topmost
     * @return True if topmost
     */
    public boolean isTopMost() {
        return this.windowPattern.isTopMost();
    }

    /**
     * Get the AutomationTitleBar associated with the given name
     * @return The AutomationTitleBar
     */
    public AutomationTitleBar getTitleBar() {
        return new AutomationTitleBar(this.getControlByControlType(0, ControlType.TitleBar), this.uiAuto);
    }

    /**
     * Sets transparency of the window
     * @param alpha 0..255 alpha attribute
     */
    public void getTransparency(int alpha) {
//        int handle = this.getNativeWindowHandle();
//
//        WinDef.HWND hwnd = new WinDef.HWND(handle);
//
//        User32 user32 = User32.INSTANCE;
//
//        user32.SetWindowLong(hwnd, user32.GWL_EXSTYLE, user32.WS_EX_LAYERED);
//        user32.SetLayeredWindowAttributes(hwnd, 0, (byte)alpha, user32.LWA_ALPHA);
    }
}
