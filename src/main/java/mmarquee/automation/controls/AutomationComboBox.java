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
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.ControlType;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.pattern.Value;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Humphreys on 01/02/2016.
 *
 * Wrapper for the ComboBox element.
 */
public class AutomationComboBox extends AutomationBase implements Expandable, Valueable {
    private ExpandCollapse collapsePattern;
    private Value valuePattern;
    private Selection selectionPattern;

    /**
     * Constructor for the AutomationComboBox.
     * @param element The underlying automation element.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Expected pattern(s) not found.
     */
    public AutomationComboBox(AutomationElement element) throws PatternNotFoundException, AutomationException {
        super (element);
    }

    /**
     * Constructor for the AutomationComboBox.
     * @param element The underlying automation element.
     * @param collapse The collapse pattern.
     * @param value The value pattern.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Expected pattern(s) not found.
     */
    AutomationComboBox(AutomationElement element, ExpandCollapse collapse, Value value, Selection selection)
            throws PatternNotFoundException, AutomationException {
        super (element);

        this.collapsePattern = collapse;
        this.valuePattern = value;
        this.selectionPattern = selection;
    }

    /**
     * Constructor for the AutomationComboBox.
     * @param element The underlying automation element.
     * @param collapse The collapse pattern.
     * @param value The value pattern.
     * @param instance The automation instance.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Expected pattern(s) not found.
     */
    AutomationComboBox(AutomationElement element, ExpandCollapse collapse, Value value, Selection selection, UIAutomation instance)
            throws PatternNotFoundException, AutomationException {
        super (element, instance);

        this.collapsePattern = collapse;
        this.valuePattern = value;
        this.selectionPattern = selection;
    }

    /**
     * Gets the text associated with this element.
     * @return The current value.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    public String getValue() throws AutomationException, PatternNotFoundException {
        if (this.valuePattern == null) {
            this.valuePattern = this.getValuePattern();
        }

        return valuePattern.value();
    }

    /**
     * Sets the text associated with this element.
     * @param text The value to be set.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    public void setText(String text) throws AutomationException, PatternNotFoundException {
        if (this.valuePattern == null) {
            this.valuePattern = this.getValuePattern();
        }

        valuePattern.setValue(text);
    }

    /**
     * Expands the element.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    public void expand() throws AutomationException, PatternNotFoundException {
        if (this.collapsePattern == null) {
            this.collapsePattern = this.getExpandCollapsePattern();
        }

        this.collapsePattern.expand();
    }

    /**
     * Is the control expanded.
     * @return True if expanded.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    public boolean isExpanded() throws AutomationException, PatternNotFoundException {
        if (this.collapsePattern == null) {
            this.collapsePattern = this.getExpandCollapsePattern();
        }

        return collapsePattern.isExpanded();
    }

    /**
     * Collapses the element.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    public void collapse() throws AutomationException, PatternNotFoundException {
        if (this.collapsePattern == null) {
            this.collapsePattern = this.getExpandCollapsePattern();
        }
        this.collapsePattern.collapse();
    }

    /**
     * Gets the list of items associated with this element.
     * @return List of AutomationListItems.
     * @throws AutomationException Automation issue.
     * @throws PatternNotFoundException Expected pattern not found.
     * @deprecated use {{@link #getItems()} instead.
     */
    @Deprecated
    public List<AutomationListItem> getList() throws PatternNotFoundException, AutomationException {
        return getItems();
    }

    /**
     * Gets the list of items associated with this element.
     * @return List of AutomationListItems.
     * @throws AutomationException Automation issue.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public List<AutomationListItem> getItems() throws PatternNotFoundException, AutomationException {

        List<AutomationListItem> list = new ArrayList<AutomationListItem>();

        List<AutomationElement> collection =
                this.findAll(new TreeScope(TreeScope.Children),
                        this.createControlTypeCondition(ControlType.ListItem));

        for (AutomationElement element : collection) {
            list.add(new AutomationListItem(element));
        }

        return list;
    }
    
    /**
     * Gets the item associated with the index.
     *
     * @param index Index of element to get.
     * @return The selected item.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Expected pattern not found.
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