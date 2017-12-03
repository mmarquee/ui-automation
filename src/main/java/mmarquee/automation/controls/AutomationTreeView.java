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

import java.util.List;
import java.util.regex.Pattern;

import mmarquee.automation.*;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * Wrapper for the TreeView element.
 *
 * @author Mark Humphreys
 * Date 20/02/2016
 */
public class AutomationTreeView extends AutomationBase {

    /**
     * Construct the AutomationTreeView.
     *
     * @param builder The builder
     */
    public AutomationTreeView(final ElementBuilder builder){
        super(builder);
    }

    /**
     * Gets the item, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTreeViewItem getItem(final Search search) throws AutomationException {
        if (search.getHasNamePattern()) {
            return getItem(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getItemByAutomationId(search.getAutomationId());
        } else if (search.getHasName()) {
            return getItem(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the item that has the name.
     * @param name The name to look for
     * @return The AutomationTreeViewItem
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTreeViewItem getItem(final String name)
            throws AutomationException {
        AutomationElement item = this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(ControlType.TreeItem)));

        if (item != null) {
            return new AutomationTreeViewItem(new ElementBuilder(item));
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
    public AutomationTreeViewItem getItem(final Pattern namePattern)
            throws AutomationException {
        List<AutomationElement> collection;

        AutomationElement foundElement = null;

        collection = this.findAll(new TreeScope(TreeScope.Descendants),
        		this.createControlTypeCondition(ControlType.TreeItem));

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

        return new AutomationTreeViewItem(new ElementBuilder(foundElement));
    }

    /**
     * Gets the item that has the name.
     * @param automationId The automationId of the item
     * @return The AutomationTreeViewItem
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTreeViewItem getItemByAutomationId(final String automationId)
            throws AutomationException {
        AutomationElement item = this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createAutomationIdPropertyCondition(automationId),
                        this.createControlTypeCondition(ControlType.TreeItem)));

        if (item != null) {
            return new AutomationTreeViewItem(new ElementBuilder(item));
        } else {
            throw new ItemNotFoundException(automationId);
        }
    }
}