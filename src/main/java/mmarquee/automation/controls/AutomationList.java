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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.List;

/**
 * Created by inpwt on 26/01/2016.
 *
 * Wrapper for the List control element.
 */
public class AutomationList extends AutomationBase {

    private Selection selectionPattern;

    /**
     * Constructor for the AutomationList
     * @param element The underlying automation element
     * @param automation The automation library
     */
    public AutomationList(AutomationElement element, IUIAutomation automation) {
        super(element, automation);

        try {
            this.selectionPattern = this.getSelectionPattern();
        } catch (PatternNotFoundException ex) {
            logger.info("Could not get SelectionPattern");
        }
    }

    /**
     * Gets the item associated with the index
     *
     * @param index Index of element to get
     * @return The selected item
     * @throws ItemNotFoundException when the item is not found
     */
    public AutomationListItem getItem(int index) throws ItemNotFoundException, ElementNotFoundException {

        List<AutomationElement> items = this.findAll(TreeScope.TreeScope_Descendants,
                this.createControlTypeCondition(ControlType.ListItem));

        AutomationElement item = items.get(index);

        if (item != null) {
            return new AutomationListItem(item, this.automation);
        } else {
            throw new ItemNotFoundException();
        }
    }

    /**
     * Gets the item associated with the name
     * @param name Name to look for
     * @return The selected item
     * @throws ItemNotFoundException when the item is not found
     */
    public AutomationListItem getItem(String name) throws ItemNotFoundException, ElementNotFoundException {
        AutomationElement item = this.findFirst(TreeScope.TreeScope_Descendants,
                this.createAndCondition(
                        this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(ControlType.ListItem)));

        if (item != null) {
            return new AutomationListItem(item, this.automation);
        } else {
            throw new ItemNotFoundException();
        }
    }

    /**
     * Gets the current selection
     * @return The current selection
     */
    public List<AutomationElement> getCurrentSelection() {
        return this.selectionPattern.getCurrentSelection();
    }
}
