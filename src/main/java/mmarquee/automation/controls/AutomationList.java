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

import mmarquee.automation.*;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.ArrayList;
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
     */
    public AutomationList(AutomationElement element) {
        super(element);

        try {
            this.selectionPattern = this.getSelectionPattern();
        } catch (PatternNotFoundException ex) {
            logger.warn("Could not get SelectionPattern");
        }
    }

    /**
     * Gets the item associated with the index
     *
     * @param index Index of element to get
     * @return The selected item
     * @throws AutomationException Something has gone wrong
     */
    public AutomationListItem getItem(int index) throws AutomationException {

        List<AutomationElement> items = this.findAll(new TreeScope(TreeScope.TreeScope_Descendants),
                this.createControlTypeCondition(ControlType.ListItem).getValue());

        AutomationElement item = items.get(index);

        if (item != null) {
            return new AutomationListItem(item);
        } else {
            throw new ItemNotFoundException();
        }
    }

    /**
     * Gets the item associated with the name
     * @param name Name to look for
     * @return The selected item
     * @throws AutomationException Something has gone wrong
     */
    public AutomationListItem getItem(String name) throws AutomationException {
        AutomationElement item = this.findFirst(new TreeScope(TreeScope.TreeScope_Descendants),
                this.createAndCondition(
                        this.createNamePropertyCondition(name).getValue(),
                        this.createControlTypeCondition(ControlType.ListItem).getValue()));

        if (item != null) {
            return new AutomationListItem(item);
        } else {
            throw new ItemNotFoundException();
        }
    }

    /**
     * Gets the current selection
     * @return The current selection
     * @throws AutomationException Something has gone wrong
     */
    public List<AutomationElement> getCurrentSelection() throws AutomationException {
        return this.selectionPattern.getCurrentSelection();
    }

    public List<AutomationListItem> getItems() throws AutomationException {
        List<AutomationElement> items = this.findAll();

        List<AutomationListItem> list = new ArrayList<AutomationListItem>();

        for (AutomationElement item: items) {
            list.add(new AutomationListItem(item));
        }

        return list;
    }
}
