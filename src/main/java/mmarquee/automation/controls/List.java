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
import mmarquee.automation.pattern.Selection;
import mmarquee.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * Wrapper for the List control element.
 * @author Mark Humphreys
 * Date 26/01/2016.
 */
public final class List
        extends AutomationBase
        implements ImplementsChildSelect {

    /**
     * The selection pattern.
     */
    private Selection selectionPattern;

    /**
     * Constructor for the List.
     *
     * @param builder The underlying automation element.
     */
    public List(final ElementBuilder builder) {
        super(builder);
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

        java.util.List<Element> items =
                this.findAll(new TreeScope(TreeScope.CHILDREN),
                    this.createControlTypeCondition(ControlType.ListItem));

        Element item = items.get(index);

        if (item != null) {
            return new ListItem(new ElementBuilder(item));
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
    public ListItem getItem(final String name)
            throws AutomationException {
        Element item = this.findFirst(
                new TreeScope(TreeScope.DESCENDANTS),
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
     * 
     * @param namePattern Name to look for
     * @return The selected item
     * @throws AutomationException Something has gone wrong
     */
    public ListItem getItem(Pattern namePattern) throws AutomationException {
        java.util.List<Element> collection;

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
     *
     * @param automationId AutomationId to look for.
     * @return The selected item.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Failed to find the pattern.
     */
    public ListItem getItemByAutomationId(final String automationId)
            throws AutomationException {
        Element item = this.findFirst(
                new TreeScope(TreeScope.DESCENDANTS),
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
     * Gets the items from the list.
     *
     * @return List of elements.
     * @throws AutomationException Something is wrong in automation library.
     */
    public java.util.List<ListItem> getItems()
            throws AutomationException {
        java.util.List<Element> items =
                this.findAll(new TreeScope(TreeScope.DESCENDANTS),
                        this.createControlTypeCondition(ControlType.ListItem));

        java.util.List<ListItem> list = new ArrayList<>();

        for (Element item: items) {
            list.add(new ListItem(new ElementBuilder(item)));
        }

        return list;
    }

    /**
     * Gets the current selection.
     *
     * @return The current selection.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException The pattern was not found.
     */
    public java.util.List<ListItem> getSelectedItems()
            throws AutomationException, PatternNotFoundException {
        java.util.List<Element> collection = getCurrentSelection();

        java.util.List<ListItem> list = new ArrayList<>();

        for (Element element : collection) {
            list.add(new ListItem(new ElementBuilder(element)));
        }

        return list;
    }

    /**
     * Gets the currently selected item.
     *
     * @return The currently selected item.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Failed to find pattern.
     */
    private Element getCurrentSelectedItem()
            throws AutomationException {
        if (this.selectionPattern == null) {
            this.selectionPattern =
                    this.requestAutomationPattern(Selection.class);
        }

        if (this.selectionPattern != null) {
            return selectionPattern.getCurrentSelectedItem();
        } else {
            throw new AutomationException("Failed call getCurrentSelectedItem");
        }
    }

    /**
     * Gets the first currently selected element.
     *
     * @return The current selection.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Failed to find pattern.
     */
    public ListItem getSelectedItem()
            throws AutomationException, PatternNotFoundException {

        // Try and use the more modern interface first
        try {
            Element elem = this.getCurrentSelectedItem();

            if (elem == null) {
                throw new ElementNotFoundException();
            } else {
                return new ListItem(new ElementBuilder(elem));
            }
        } catch (AutomationException ex) {
            java.util.List<ListItem> list = this.getSelectedItems();
            if (list.size() == 0) {
                throw new ElementNotFoundException();
            }
            return list.get(0);
        }
    }
}
