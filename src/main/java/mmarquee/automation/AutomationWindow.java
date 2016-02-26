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

import mmarquee.automation.pattern.WindowPattern;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationWindow extends AutomationContainer {

   // private WindowPattern windowPattern;

    /**
     * Focuses this control.
     */
    public void focus() {
        this.element.setFocus();
    }

    public AutomationWindow (IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
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

            if (retVal == ControlTypeID.StatusBar) {
                found = new AutomationStatusBar(element, uiAuto);
            }
        }

        return found;
    }

    /**
     * Gets the system menu associated with this window
     * @return The system menu
     */
    public AutomationSystemMenu getSystemMenu() {
        IUIAutomationElement menu = this.getControlByControlType(0, ControlTypeID.MenuBar);
        return (new AutomationSystemMenu(menu, this.uiAuto));
    }

    /**
     * Gets the main menu associated with this window
     * @return The main menu
     */
    public AutomationMainMenu getMainMenu() {
        IUIAutomationElement menu = this.getControlByControlType(1, ControlTypeID.MenuBar);

        return (new AutomationMainMenu(this.element, menu, this.uiAuto));
    }

    /**
     * Waits for this window to become idle.
     * @param timeout The timeout
     */
    public void waitForInputIdle(int timeout) {
        WindowPattern windowPattern = this.getWindowPattern();
        windowPattern.waitForInputIdle(timeout);
    }

    /**
     * Maximize the window
     */
    public void maximize() {
        WindowPattern windowPattern = this.getWindowPattern();
        windowPattern.maximize();
    }

    /**
     * Minimize the window
     */
    public void minimize() {
        WindowPattern windowPattern = this.getWindowPattern();
        windowPattern.minimize();
    }

    /**
     * Finds the child window with the given title
     * @param title Title to search for
     * @return The child window
     */
    public AutomationWindow getWindow(String title) {
        IUIAutomationElement item = this.findFirst(TreeScope.TreeScope_Descendants,
                this.createAndCondition(
                        this.createNamePropertyCondition(title),
                        this.createControlTypeCondition(ControlTypeID.Window)));

        return new AutomationWindow(item, this.uiAuto);
    }
}
