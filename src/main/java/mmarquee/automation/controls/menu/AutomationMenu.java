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

package mmarquee.automation.controls.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.controls.ElementBuilder;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * Wrapper for the Menu control element.
 * @author Mark Humphreys
 * Date 09/02/2016
 */
public class AutomationMenu extends AutomationBase {
    /**
     * Construct the AutomationMenu.
     *
     * @param builder The builder
     */
    public AutomationMenu(ElementBuilder builder) {
        super(builder);
    }

    /**
     * The control type.
     */
    public static ControlType controlType = ControlType.Menu;

    /**
     * Gets the list of items associated with this menu item.
     * @return List of menu items
     * @throws AutomationException Something has gone wrong
     */
    public List<AutomationMenuItem> getItems()
            throws  AutomationException {
    	
        List<AutomationElement> items = this.findAll(new TreeScope(TreeScope.Children),
                this.createControlTypeCondition(ControlType.MenuItem));

        List<AutomationMenuItem> list = new ArrayList<>();

        for (AutomationElement item : items) {
            list.add(new AutomationMenuItem(new ElementBuilder(item)));
        }

        return list;
    }
    
    /**
     * Gets the item associated with the index.
     * @param index The index
     * @return The found item
     * @throws AutomationException Something went wrong
     */
    public AutomationMenuItem getMenuItem(final int index)
            throws AutomationException {
        List<AutomationElement> items = this.findAll(new TreeScope(TreeScope.Children));

        return new AutomationMenuItem(new ElementBuilder(items.get(index)));
    }

    /**
     * Gets the item associated with the name.
     * @param name The name to look for
     * @return The found item
     * @throws AutomationException Something went wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMenuItem getMenuItem(final String name)
            throws PatternNotFoundException, AutomationException {

        AutomationElement item = this.findFirst(new TreeScope(TreeScope.Children),
                this.createAndCondition(
                        this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(ControlType.MenuItem)));

        return new AutomationMenuItem(new ElementBuilder(item));
    }

    /**
     * Gets the item matching the name.
     * @param namePattern a pattern matching the name
     * @return The found item
     * @throws AutomationException Something went wrong
     */
    public AutomationMenuItem getMenuItem(final Pattern namePattern)
            throws  AutomationException {
    	List<AutomationElement> collection;

        AutomationElement item = null;

        collection = this.findAll(new TreeScope(TreeScope.Children),
        		this.createControlTypeCondition(ControlType.MenuItem));

        for (AutomationElement element : collection) {
            String name = element.getName();

            if (name != null && namePattern.matcher(name).matches()) {
                item = element;
                break;
            }
        }

        if (item == null) {
            throw new ItemNotFoundException("Failed to find element matching " + namePattern);
        }
        return new AutomationMenuItem(new ElementBuilder(item));
    }

    /**
     * Get the menu item associated with the automationID.
     * @param automationId The automation ID to look for
     * @return The menu item that matches the name
     * @throws AutomationException Something has gone wrong
     */
    public AutomationMenuItem getMenuItemByAutomationId(final String automationId)
            throws  AutomationException {
    	
        AutomationElement item = this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createAutomationIdPropertyCondition(automationId),
                        this.createControlTypeCondition(ControlType.MenuItem)));

        return new AutomationMenuItem(new ElementBuilder(item));
    }
}
