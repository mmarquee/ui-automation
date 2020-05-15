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

import mmarquee.automation.*;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Wrapper for the ComboBox element.
 * @author Mark Humphreys
 * Date 01/02/2016.
 */
public final class ComboBox
        extends Container
        implements ImplementsExpand, ImplementsValue,
                   ImplementsFocus, ImplementsChildSelect {

    /**
     * Constructor for the ComboBox.
     * @param builder The builder
     */
    public ComboBox(final ElementBuilder builder) {
        super(builder);
    }

    /**
     * Sets the text associated with this element.
     * @param text The value to be set.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    public void setText(final String text)
            throws AutomationException,
                   PatternNotFoundException {
        setValue(text);
    }

    /**
     * Gets the list of items associated with this element.
     * @return List of AutomationListItems.
     * @throws AutomationException Automation issue.
     * @deprecated use {{@link #getItems()} instead.
     */
    @Deprecated
    public List<ListItem> getList() throws AutomationException {
        return getItems();
    }

    /**
     * Gets the list of items associated with this element.
     * @return List of AutomationListItems.
     * @throws AutomationException Automation issue.
     */
    public List<ListItem> getItems() throws AutomationException {

        List<ListItem> list = new ArrayList<>();

        List<Element> collection =
                this.findAll(new TreeScope(TreeScope.CHILDREN),
                        this.createControlTypeCondition(ControlType.ListItem));

        for (Element element : collection) {
            list.add(new ListItem(new ElementBuilder(element)));
        }

        return list;
    }

    /**
     * Get the item, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public ListItem getItem(final Search search)
            throws AutomationException {
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
    public ListItem getItem(final int index)
            throws AutomationException {

        List<Element> items =
                this.findAll(new TreeScope(TreeScope.CHILDREN),
                             this.createControlTypeCondition(
                                     ControlType.ListItem));

        Element item = items.get(index);

        if (item != null) {
            return new ListItem(new ElementBuilder(item));
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
    public ListItem getItem(final String name)
            throws AutomationException {
        Element item =
                this.findFirst(new TreeScope(TreeScope.DESCENDANTS),
                this.createAndCondition(
                        this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(ControlType.ListItem)));

        if (item != null) {
            return new ListItem(new ElementBuilder(item));
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
    public ListItem getItem(final Pattern namePattern)
            throws AutomationException {
        List<Element> collection;

        Element foundElement = null;

        collection = this.findAll(new TreeScope(TreeScope.DESCENDANTS),
        		this.createControlTypeCondition(ControlType.ListItem));

        for (Element element : collection) {
            String name = element.getName();

            if (name != null && namePattern.matcher(name).matches()) {
                foundElement = element;
                break;
            }
        }

        if (foundElement == null) {
            throw new ItemNotFoundException(namePattern.toString());
        }

        return new ListItem(new ElementBuilder(foundElement));
    }

    /**
     * Gets the item associated with the automationId.
     * @param automationId AutomationId to look for
     * @return The selected item
     * @throws AutomationException Something has gone wrong
     */
    private ListItem getItemByAutomationId(final String automationId)
            throws AutomationException {
        Element item =
                this.findFirst(new TreeScope(TreeScope.DESCENDANTS),
                this.createAndCondition(
                        this.createAutomationIdPropertyCondition(automationId),
                        this.createControlTypeCondition(ControlType.ListItem)));

        if (item != null) {
            return new ListItem(new ElementBuilder(item));
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
    public List<ListItem> getSelectedItems()
            throws AutomationException, PatternNotFoundException {
    	List<Element> collection = getCurrentSelection();

        List<ListItem> list = new ArrayList<>();

        for (Element element : collection) {
            list.add(new ListItem(new ElementBuilder(element)));
        }

        return list;
    }

    /**
     * Gets the first currently selected element.
     * @return The current selection
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    public ListItem getSelectedItem()
            throws AutomationException, PatternNotFoundException {
        List<ListItem> list = this.getSelectedItems();
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