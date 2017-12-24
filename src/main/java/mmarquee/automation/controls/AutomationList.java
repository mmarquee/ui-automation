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
import java.util.regex.Pattern;

/**
 * Wrapper for the List control element.
 * @author Mark Humphreys
 * Date 26/01/2016.
 */
public final class AutomationList extends AutomationBase {

    /**
     * The selection pattern.
     */
    private Selection selectionPattern;

    /**
     * Constructor for the AutomationList.
     *
     * @param builder The underlying automation element.
     */
    public AutomationList(final ElementBuilder builder) {
        super(builder);
        this.selectionPattern = builder.getSelection();

        //        this.selectionPattern = this.getSelectionPattern();
    }

    /**
     * Gets the item associated with the index.
     *
     * @param index Index of element to get.
     * @return The selected item.
     * @throws AutomationException Something has gone wrong.
     */
    public AutomationListItem getItem(final int index)
            throws AutomationException {

        List<AutomationElement> items = this.findAll(new TreeScope(TreeScope.Children),
                this.createControlTypeCondition(ControlType.ListItem));

        AutomationElement item = items.get(index);

        if (item != null) {
            return new AutomationListItem(new ElementBuilder(item));
        } else {
            throw new ItemNotFoundException(index);
        }
    }

    /**
     * Gets the item associated with the name.
     *
     * @param name Name to look for.
     * @return The selected item.
     * @throws AutomationException Something has gone wrong.
     */
    public AutomationListItem getItem(final String name)
            throws AutomationException {
        AutomationElement item = this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(ControlType.ListItem)));

        if (item != null) {
            return new AutomationListItem(new ElementBuilder(item));
        } else {
            throw new ItemNotFoundException(name);
        }
    }

    /**
     * Gets the item matching the namePattern.
     * 
     * @param namePattern Name to look for
     * @return The selected item
     * @throws AutomationException Something has gone wrong
     */
    public AutomationListItem getItem(Pattern namePattern) throws AutomationException {
        List<AutomationElement> collection;

        AutomationElement foundElement = null;

        collection = this.findAll(new TreeScope(TreeScope.Descendants),
        		this.createControlTypeCondition(ControlType.ListItem));

        for (AutomationElement element : collection) {
            String name = element.getName();

            if (name != null && namePattern.matcher(name).matches()) {
                foundElement = element;
                break;
            }
        }

        if (foundElement == null) {
            throw new ItemNotFoundException(namePattern.toString());
        }

        return new AutomationListItem(new ElementBuilder(foundElement));
    }
    
    /**
     * Gets the item associated with the automationId.
     *
     * @param automationId AutomationId to look for.
     * @return The selected item.
     * @throws AutomationException Something has gone wrong.
     */
    public AutomationListItem getItemByAutomationId(final String automationId)
            throws AutomationException {
        AutomationElement item = this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createAutomationIdPropertyCondition(automationId),
                        this.createControlTypeCondition(ControlType.ListItem)));

        if (item != null) {
            return new AutomationListItem(new ElementBuilder(item));
        } else {
            throw new ItemNotFoundException(automationId);
        }
    }

    /**
     * Gets the current selection.
     *
     * @return The current selection.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Failed to find pattern.
     */
    public List<AutomationElement> getSelection()
            throws AutomationException, PatternNotFoundException {
        if (this.selectionPattern == null) {
            this.selectionPattern = this.getSelectionPattern();
        }
        return this.selectionPattern.getCurrentSelection();
    }

    /**
     * Gets the items from the list.
     *
     * @return List of elements.
     * @throws AutomationException Something is wrong in automation library.
     */
    public List<AutomationListItem> getItems()
            throws AutomationException {
        List<AutomationElement> items = this.findAll(
                new TreeScope(TreeScope.Descendants),
                this.createControlTypeCondition(ControlType.ListItem));

        List<AutomationListItem> list = new ArrayList<>();

        for (AutomationElement item: items) {
            list.add(new AutomationListItem(new ElementBuilder(item)));
        }

        return list;
    }

    /**
     * Gets the current selection.
     *
     * @return The current selection.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Failed to find pattern.
     */
    public List<AutomationListItem> getSelectedItems()
            throws AutomationException, PatternNotFoundException {
        if (this.selectionPattern == null) {
            this.selectionPattern = this.getSelectionPattern();
        }

        if (this.selectionPattern != null) {
	        List<AutomationElement> collection = this.selectionPattern.getCurrentSelection();
	
	        List<AutomationListItem> list = new ArrayList<>();
	        
	        for (AutomationElement element : collection) {
	            list.add(new AutomationListItem(new ElementBuilder(element)));
	        }
	
	        return list;
	    }
        throw new PatternNotFoundException("Could not determine selection");
    }

    private AutomationElement getCurrentSelectedItem()
            throws AutomationException, PatternNotFoundException {
        if (this.selectionPattern == null) {
            this.selectionPattern = this.getSelectionPattern();
        }

        if (this.selectionPattern != null) {
            return selectionPattern.getCurrentSelectedItem();
        } else {
            throw new AutomationException("Failed to call getCurrentSelectedItem");
        }
    }

    /**
     * Gets the first currently selected element.
     *
     * @return The current selection.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Failed to find pattern.
     */
    public AutomationListItem getSelectedItem()
            throws AutomationException, PatternNotFoundException {

        // Try and use the more modern interface

        try {
            AutomationElement elem = this.getCurrentSelectedItem();

            return new AutomationListItem(new ElementBuilder(elem));
        } catch (AutomationException ex) {
            List<AutomationListItem> list = this.getSelectedItems();
            if (list.size() == 0) {
                throw new ElementNotFoundException();
            }
            return list.get(0);
        }
    }
}

