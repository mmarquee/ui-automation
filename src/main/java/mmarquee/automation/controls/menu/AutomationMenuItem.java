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
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.controls.ImplementsClick;
import mmarquee.automation.controls.ElementBuilder;
import mmarquee.automation.controls.ImplementsExpand;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * Wrapper for the MenuItem element.
 * @author Mark Humphreys
 * Date 10/02/2016.
 */
public class AutomationMenuItem
        extends AutomationBase
        implements ImplementsClick, ImplementsExpand {

    /** The parent element. */
    protected AutomationElement mainMenuParentElement;

    /** Name of the parent. */
    protected String parentMenuName;

    /**
     * Construct the AutomationMenuItem.
     * @param builder The builder
     */
    public AutomationMenuItem(final ElementBuilder builder){
        super(builder);
    }

    /**
     * The Control type.
     */
    public static ControlType controlType = ControlType.MenuItem;

    /**
     * Invoke the click pattern for the menu item.
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public void click() throws AutomationException, PatternNotFoundException {
    	this.invoke();
    }

    /**
     * Gets the list of items associated with this menu item.
     * If the current item is known as member of an AutomationMainMenu,
     * the request is automatically redirected to the correspondent
     * AutomationMenu, when such a menu can be found
     * (i.e. this item is expanded)
     * 
     * @return List of menu items
     * @throws AutomationException Something has gone wrong
     */
    public List<AutomationMenuItem> getItems() throws AutomationException {

    	AutomationMenu realMenu = getRealMenu();
    	if (realMenu != null) {
    		return realMenu.getItems();
    	}
    	
        List<AutomationElement> items =
                this.findAll(new TreeScope(TreeScope.Descendants),
                        this.createControlTypeCondition(ControlType.MenuItem));

        List<AutomationMenuItem> list = new ArrayList<>();

        for (AutomationElement item : items) {
            list.add(new AutomationMenuItem(new ElementBuilder(item)));
        }

        return list;
    }

    /**
     * Gets the subItem associated with the index.
     * If the current item is known as member of an AutomationMainMenu,
     * the request is automatically redirected to the correspondent
     * AutomationMenu, when such a menu can be found
     * (i.e. this item is expanded)
     * 
     * @param index The index
     * @return The found item
     * @throws AutomationException Something went wrong
     */
    public AutomationMenuItem getMenuItem(final int index)
            throws AutomationException {

    	AutomationMenu realMenu = getRealMenu();
    	if (realMenu != null) {
    		return realMenu.getMenuItem(index);
    	}
    	
        List<AutomationElement> items = this.findAll(new TreeScope(TreeScope.Children));

        return new AutomationMenuItem(new ElementBuilder(items.get(index)));
    }
    
    /**
     * Get the menu item associated with the name.
     * If the current item is known as member of an AutomationMainMenu,
     * the request is automatically redirected to the correspondent
     * AutomationMenu, when such a menu can be found
     * (i.e. this item is expanded)
     * 
     * @param name First Name
     * @return The menu item that matches the name
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMenuItem getMenuItem (final String name)
            throws PatternNotFoundException, AutomationException {
    	
    	AutomationMenu realMenu = getRealMenu();
    	if (realMenu != null) {
    		return realMenu.getMenuItem(name);
    	}

        AutomationElement item = this.findFirst(new TreeScope(TreeScope.Children),
                this.createAndCondition(
                        this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(ControlType.MenuItem)));

        return new AutomationMenuItem(new ElementBuilder(item));
    }

    /**
     * Get the menu item matching the name.
     * If the current item is known as member of an AutomationMainMenu,
     * the request is automatically redirected to the correspondent
     * AutomationMenu, when such a menu can be found
     * (i.e. this item is expanded)
     * 
     * @param namePattern a pattern matching the menu item name
     * @return The menu item that matches the name
     * @throws AutomationException Something has gone wrong
     */
    public AutomationMenuItem getMenuItem (final Pattern namePattern)
            throws AutomationException {
    	
    	AutomationMenu realMenu = getRealMenu();
    	if (realMenu != null) {
    		return realMenu.getMenuItem(namePattern);
    	}

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
            throw new ElementNotFoundException("Failed to find element matching " + namePattern);
        }
        
        return new AutomationMenuItem(new ElementBuilder(item));
    }
    
    /**
     * Get the menu item associated with the automationID.
     *
     * If the current item is known as member of an AutomationMainMenu,
     * the request is automatically redirected to the correspondent
     * AutomationMenu, when such a menu can be found
     * (i.e. this item is expanded)
     * 
     * @param automationId The automation ID to look for
     * @return The menu item that matches the name
     * @throws AutomationException Something has gone wrong
     */
    public AutomationMenuItem getMenuItemByAutomationId (final String automationId)
            throws AutomationException {
    	
    	AutomationMenu realMenu = getRealMenu();
    	if (realMenu != null) {
    		return realMenu.getMenuItemByAutomationId(automationId);
    	}

        AutomationElement item =
                this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createAutomationIdPropertyCondition(automationId),
                        this.createControlTypeCondition(ControlType.MenuItem)));

        return new AutomationMenuItem(new ElementBuilder(item));
    }

    /**
     * Gets the real menu.
     * @return The menu item
     * @throws AutomationException Something has gone wrong
     *
     * For MainMenus, the dropdown is disconnected from the MenuItem here
     */
    private AutomationMenu getRealMenu() throws AutomationException {
    	if (parentMenuName == null || mainMenuParentElement == null) {
    		return null;
    	}
    	try {
	    	AutomationElement item = mainMenuParentElement.findFirst(
	    	        new TreeScope(TreeScope.Descendants),
                    this.createAndCondition(
	                        this.createNamePropertyCondition(parentMenuName),
	                        this.createControlTypeCondition(ControlType.Menu)));

            if (item == null) {
                return null;
            }

	    	return new AutomationMenu(new ElementBuilder(item));
    	} catch (ElementNotFoundException ex) {
    		return null;
    	}
    }
}
