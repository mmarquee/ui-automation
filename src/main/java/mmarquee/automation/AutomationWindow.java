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

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationWindow extends AutomationContainer {

    private IUIAutomationWindowPattern windowPattern;

    public void focus() {
        this.element.setFocus();
    }

    public AutomationWindow (IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        this.windowPattern = this.getWindowPattern();
    }

    public AutomationStatusBar getStatusBar() {
        IUIAutomationCondition condition = uiAuto.createTrueCondition();

        IUIAutomationElementArray collection = this.element.findAll(TreeScope.TreeScope_Descendants, condition);

        int length = collection.length();

        AutomationStatusBar found = null;

        for (int count = 0; count < length; count++) {
            IUIAutomationElement element = collection.getElement(count);

            int retVal = element.currentControlType();

            if (retVal == ControlTypeID.StatusBarControlTypeId) {
                found = new AutomationStatusBar(element, uiAuto);
            }
        }

        return found;
    }

    public AutomationMainMenu getMainMenu() {
        IUIAutomationElement menu = this.getControlByControlType(0, ControlTypeID.MenuBarControlTypeId);

        return (new AutomationMainMenu(menu, this.uiAuto));
    }

    public void waitForInputIdle(int timeout) {
        this.windowPattern.waitForInputIdle(timeout);
    }
}
