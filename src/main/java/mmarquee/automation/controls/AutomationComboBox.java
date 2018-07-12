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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * Wrapper for the ComboBox element.
 * @author Mark Humphreys
 * Date 01/02/2016.
 */
public final class AutomationComboBox
        extends AutomationContainer
        implements ImplementsExpand, ImplementsValue, ImplementsFocus, ImplementsChildSelect {

    /**
     * Constructor for the AutomationComboBox.
     * @param builder The builder
     */
    public AutomationComboBox(final ElementBuilder builder) {
        super(builder);
    }

    /**
     * Sets the text associated with this element.
     * @param text The value to be set.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    public void setText(String text) throws AutomationException, PatternNotFoundException {
        setValue(text);
    }

    /**
     * Gets the list of items associated with this element.
     * @return List of AutomationListItems.
     * @throws AutomationException Automation issue.
     * @deprecated use {{@link #getItems()} instead.
     */
    @Deprecated
    public List<AutomationListItem> getList() throws AutomationException {
        return getItems();
    }

    /**
     * Gets the list of items associated with this element.
     * @return List of AutomationListItems.
     * @throws AutomationException Automation issue.
     */
    public List<AutomationListItem> getItems() throws AutomationException {

        List<AutomationListItem> list = new ArrayList<>();

        List<AutomationElement> collection =
                this.findAll(new TreeScope(TreeScope.Children),
                        this.createControlTypeCondition(ControlType.ListItem));

        for (AutomationElement element : collection) {
            list.add(new AutomationListItem(new ElementBuilder(element)));
        }

        return list;
    }

    /**
     * Get the item, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationListItem getItem(final Search search) throws AutomationException {
        if (search.getHasNamePattern()) {
            return getItem(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getItemByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getItem(search.getIndex());
        } else if (search.getHasName()) {
            return getItem(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the item associated with the index.
     *
     * @param index Index of element to get.
     * @return The selected item.
     * @throws AutomationException Something has gone wrong.
     */
    public AutomationListItem getItem(final int index) throws AutomationException {

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
     * @param name Name to look for
     * @return The selected item
     * @throws AutomationException Something has gone wrong
     */
    public AutomationListItem getItem(final String name) throws AutomationException {
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
     * @param namePattern Name to look for
     * @return The selected item
     * @throws AutomationException Something has gone wrong
     */
    public AutomationListItem getItem(final Pattern namePattern) throws AutomationException {
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
     * @param automationId AutomationId to look for
     * @return The selected item
     * @throws AutomationException Something has gone wrong
     */
    private AutomationListItem getItemByAutomationId(final String automationId)
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
     * @return The current selection
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    public List<AutomationListItem> getSelectedItems() throws AutomationException, PatternNotFoundException {
    	List<AutomationElement> collection = getCurrentSelection();

        List<AutomationListItem> list = new ArrayList<>();

        for (AutomationElement element : collection) {
            list.add(new AutomationListItem(new ElementBuilder(element)));
        }

        return list;
    }

    /**
     * Gets the first currently selected element.
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

    /**
     * <p>
     * Sets the focus to this control.
     * </p>
     */
    public void focus() {
        this.getElement().setFocus();
    }
}