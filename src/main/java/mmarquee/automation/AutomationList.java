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

import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.uiautomation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationList extends AutomationBase {

    private Selection selectionPattern;

    public AutomationList(AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        try {
            this.selectionPattern = this.getSelectionPattern();
        } catch (PatternNotFoundException ex) {
            // Handle this nicely somehow
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
            return new AutomationListItem(item, this.uiAuto);
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
