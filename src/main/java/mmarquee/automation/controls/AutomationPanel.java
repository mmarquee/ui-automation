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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.List;

/**
 * Created by Mark Humphreys on 26/02/2016.
 *
 * Wrapper for the Panel element.
 */
public class AutomationPanel extends AutomationContainer {
    /**
     * Construct the AutomationPanel
     * @param element The element
     * @throws AutomationException Something is wrong in automation
     * @throws PatternNotFoundException Could not find pattern
     */
    public AutomationPanel(AutomationElement element) throws AutomationException, PatternNotFoundException {
        super(element);
    }

    /**
     * Construct the AutomationPanel
     * @param element The element
     * @param containerPattern The itemContainer pattern
     * @throws AutomationException Something is wrong in automation
     * @throws PatternNotFoundException Could not find pattern
     */
    public AutomationPanel(AutomationElement element, ItemContainer containerPattern) throws AutomationException, PatternNotFoundException {
        super(element, containerPattern);
    }

    /**
     * Gets an MDI window from the panel
     *
     * Yes, panels can have windows - in this case the window is assumed to be extant.
     *
     * @param index The nth element
     * @return The found window
     * @throws PatternNotFoundException Failed to find the right pattern
     * @throws AutomationException Something went really wrong.
     */
    public AutomationWindow getMDIWindow(int index) throws PatternNotFoundException, AutomationException {
        List<AutomationElement> list =
                this.findAll(new TreeScope(TreeScope.Descendants),
                    this.createControlTypeCondition(ControlType.Window).getValue());

        AutomationElement item = list.get(index);

        return new AutomationWindow(item);
    }
}
