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
import java.util.logging.Logger;
import java.util.regex.Pattern;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * @author Mark Humphreys
 * Date 09/02/2016.
 *
 * Wrapper for the Menu control element.
 */
public class AutomationMenu extends AutomationBase {
    protected Logger logger = Logger.getLogger(AutomationMenu.class.getName());

    /**
     * Construct the AutomationMenu
     * @param element The element
     * @throws AutomationException Automation library error
     */
    public AutomationMenu(AutomationElement element)
            throws AutomationException {
        super(element);
    }

    public static ControlType controlType = ControlType.Menu;


    /**
     * Gets the list of items associated with this menu item
     * @return List of menu items
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationMenuItem> getItems() throws PatternNotFoundException, AutomationException {
    	
        List<AutomationElement> items = this.findAll(new TreeScope(TreeScope.Children),
                this.createControlTypeCondition(ControlType.MenuItem));

        List<AutomationMenuItem> list = new ArrayList<AutomationMenuItem>();

        for (AutomationElement item : items) {
            list.add(new AutomationMenuItem(item));
        }

        return list;
    }
    
    /**
     * Gets the item associated with the index
     * @param index The index
     * @return The found item
     * @throws AutomationException Something went wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMenuItem getMenuItem (int index) throws PatternNotFoundException, AutomationException {
        List<AutomationElement> items = this.findAll(new TreeScope(TreeScope.Children));

        return new AutomationMenuItem(items.get(index));
    }

    /**
     * Gets the item associated with the name
     * @param name The name to look for
     * @return The found item
     * @throws AutomationException Something went wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMenuItem getMenuItem (String name) throws PatternNotFoundException, AutomationException {

        AutomationElement item = this.findFirst(new TreeScope(TreeScope.Children),
                this.createAndCondition(
                        this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(ControlType.MenuItem)));

        return new AutomationMenuItem(item);
    }

    /**
     * Gets the item matching the name
     * @param namePattern a pattern matching the name
     * @return The found item
     * @throws AutomationException Something went wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMenuItem getMenuItem (Pattern namePattern) throws PatternNotFoundException, AutomationException {
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
        return new AutomationMenuItem(item);
    }

    /**
     * Get the menu item associated with the automationID
     * @param automationId The automation ID to look for
     * @return The menu item that matches the name
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMenuItem getMenuItemByAutomationId (String automationId)
            throws PatternNotFoundException, AutomationException {
    	
        AutomationElement item = this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createAutomationIdPropertyCondition(automationId),
                        this.createControlTypeCondition(ControlType.MenuItem)));

        return new AutomationMenuItem(item);
    }
}
