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
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Humphreys on 26/01/2016.
 *
 * Wrapper for the List control element.
 */
public class AutomationList extends AutomationBase {

    private Selection selectionPattern;

    /**
     * Constructor for the AutomationList
     * @param element The underlying automation element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationList(AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);
//        this.selectionPattern = this.getSelectionPattern();
    }

    /**
     * Constructor for the AutomationList
     * @param element The underlying automation element
     * @param selection The Selection pattern
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationList(AutomationElement element, Selection selection) throws PatternNotFoundException, AutomationException {
        super(element);
        this.selectionPattern = selection;
    }

    /**
     * Gets the item associated with the index
     *
     * @param index Index of element to get
     * @return The selected item
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationListItem getItem(int index) throws PatternNotFoundException, AutomationException {

        List<AutomationElement> items = this.findAll(new TreeScope(TreeScope.Children),
                this.createControlTypeCondition(ControlType.ListItem));

        AutomationElement item = items.get(index);

        if (item != null) {
            return new AutomationListItem(item);
        } else {
            throw new ItemNotFoundException(index);
        }
    }

    /**
     * Gets the item associated with the name
     * @param name Name to look for
     * @return The selected item
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationListItem getItem(String name) throws PatternNotFoundException, AutomationException {
        AutomationElement item = this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(ControlType.ListItem)));

        if (item != null) {
            return new AutomationListItem(item);
        } else {
            throw new ItemNotFoundException(name);
        }
    }

    /**
     * Gets the item associated with the automationId
     * @param automationId AutomationId to look for
     * @return The selected item
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationListItem getItemByAutomationId(String automationId) throws PatternNotFoundException, AutomationException {
        AutomationElement item = this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createAutomationIdPropertyCondition(automationId),
                        this.createControlTypeCondition(ControlType.ListItem)));

        if (item != null) {
            return new AutomationListItem(item);
        } else {
            throw new ItemNotFoundException(automationId);
        }
    }
    
    
    /**
     * Gets the current selection
     * @return The current selection
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    public List<AutomationElement> getSelection() throws AutomationException, PatternNotFoundException {
        if (this.selectionPattern == null) {
            this.selectionPattern = this.getSelectionPattern();
        }
        return this.selectionPattern.getCurrentSelection();
    }

    /**
     * Gets the items from the list
     * @return List of elements
     * @throws AutomationException Something is wrong in automation library
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationListItem> getItems() throws PatternNotFoundException, AutomationException {
        List<AutomationElement> items = this.findAll(new TreeScope(TreeScope.Descendants),this.createControlTypeCondition(ControlType.ListItem));

        List<AutomationListItem> list = new ArrayList<AutomationListItem>();

        for (AutomationElement item: items) {
            list.add(new AutomationListItem(item));
        }

        return list;
    }

    /**
     * Gets the current selection
     * @return The current selection
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    public List<AutomationListItem> getSelectedItems() throws AutomationException, PatternNotFoundException {
        if (this.selectionPattern == null) {
            this.selectionPattern = this.getSelectionPattern();
        }
        if (this.selectionPattern != null) {
	        List<AutomationElement> collection = this.selectionPattern.getCurrentSelection();
	
	        List<AutomationListItem> list = new ArrayList<AutomationListItem>();
	        
	        for (AutomationElement element : collection) {
	            list.add(new AutomationListItem(element));
	        }
	
	        return list;
	    }
        throw new PatternNotFoundException("Could not determine selection");
    }

    /**
     * Gets the first currently selected element
     * @return The current selection
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    public AutomationListItem getSelectedItem() throws AutomationException, PatternNotFoundException {
        List<AutomationListItem> list = this.getSelectedItems();
        if (list.size() == 0) {
        	throw new ElementNotFoundException();
        }
        return list.get(0);
    }
}
