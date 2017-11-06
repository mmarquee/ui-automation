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
import mmarquee.automation.controls.Clickable;
import mmarquee.automation.controls.Expandable;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * @author Mark Humphreys
 * Date 10/02/2016.
 *
 * Wrapper for the MenuItem element.
 */
public class AutomationMenuItem extends AutomationBase implements Clickable, Expandable {
    private ExpandCollapse collapsePattern;
    
    protected AutomationElement mainMenuParentElement;
    protected String parentMenuName;

    /**
     * Construct the AutomationMenuItem
     * @param element The element
     * @throws PatternNotFoundException Expected pattern not found
     * @throws AutomationException Automation error
     */
    public AutomationMenuItem(AutomationElement element)
            throws PatternNotFoundException, AutomationException {
        super(element);
    }

    /**
     *
     * Construct the AutomationMenuItem
     * @param element The element
     * @param collapse ExpandCollapse pattern
     * @param invoke Invoke Pattern
     * @throws PatternNotFoundException Pattern eas not found
     * @throws AutomationException Error in the automation library
     */
    AutomationMenuItem(AutomationElement element, ExpandCollapse collapse, Invoke invoke)
            throws PatternNotFoundException, AutomationException {
        super(element);
        this.collapsePattern = collapse;
        this.invokePattern = invoke;
    }

    public static ControlType controlType = ControlType.MenuItem;

    /**
     * Invoke the click pattern for the menu item.
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public void click() throws AutomationException, PatternNotFoundException {
        if (invokePattern != null && invokePattern.isAvailable()) {
            invokePattern.invoke();
        } else if (isExpanded()) {
            collapse();
        } else {
            expand();
        }
    }

    /**
     * Gets the list of items associated with this menu item. If the current item is known as member of an AutomationMainMenu,
     * the request is automatically redirected to the correspondent AutomationMenu, when such a menu can be found
     * (i.e. this item is expanded)
     * 
     * @return List of menu items
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationMenuItem> getItems() throws PatternNotFoundException, AutomationException {

    	AutomationMenu realMenu = getRealMenu();
    	if (realMenu != null) {
    		return realMenu.getItems();
    	}
    	
        List<AutomationElement> items = this.findAll(new TreeScope(TreeScope.Descendants),
                this.createControlTypeCondition(ControlType.MenuItem));

        List<AutomationMenuItem> list = new ArrayList<AutomationMenuItem>();

        for (AutomationElement item : items) {
            list.add(new AutomationMenuItem(item));
        }

        return list;
    }

    /**
     * Gets the subItem associated with the index. If the current item is known as member of an AutomationMainMenu,
     * the request is automatically redirected to the correspondent AutomationMenu, when such a menu can be found
     * (i.e. this item is expanded)
     * 
     * @param index The index
     * @return The found item
     * @throws AutomationException Something went wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMenuItem getMenuItem (int index) throws PatternNotFoundException, AutomationException {

    	AutomationMenu realMenu = getRealMenu();
    	if (realMenu != null) {
    		return realMenu.getMenuItem(index);
    	}
    	
        List<AutomationElement> items = this.findAll(new TreeScope(TreeScope.Children));

        return new AutomationMenuItem(items.get(index));
    }
    
    /**
     * Get the menu item associated with the name. If the current item is known as member of an AutomationMainMenu,
     * the request is automatically redirected to the correspondent AutomationMenu, when such a menu can be found
     * (i.e. this item is expanded)
     * 
     * @param name First Name
     * @return The menu item that matches the name
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMenuItem getMenuItem (String name)
            throws PatternNotFoundException, AutomationException {
    	
    	AutomationMenu realMenu = getRealMenu();
    	if (realMenu != null) {
    		return realMenu.getMenuItem(name);
    	}

        AutomationElement item = this.findFirst(new TreeScope(TreeScope.Children),
                this.createAndCondition(
                        this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(ControlType.MenuItem)));

        return new AutomationMenuItem(item);
    }

    /**
     * Get the menu item matching the name. If the current item is known as member of an AutomationMainMenu,
     * the request is automatically redirected to the correspondent AutomationMenu, when such a menu can be found
     * (i.e. this item is expanded)
     * 
     * @param namePattern a pattern matching the menu item name
     * @return The menu item that matches the name
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMenuItem getMenuItem (Pattern namePattern)
            throws PatternNotFoundException, AutomationException {
    	
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
        
        return new AutomationMenuItem(item);
    }
    
    /**
     * Get the menu item associated with the automationID. If the current item is known as member of an AutomationMainMenu,
     * the request is automatically redirected to the correspondent AutomationMenu, when such a menu can be found
     * (i.e. this item is expanded)
     * 
     * @param automationId The automation ID to look for
     * @return The menu item that matches the name
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMenuItem getMenuItemByAutomationId (String automationId)
            throws PatternNotFoundException, AutomationException {
    	
    	AutomationMenu realMenu = getRealMenu();
    	if (realMenu != null) {
    		return realMenu.getMenuItemByAutomationId(automationId);
    	}

        AutomationElement item = this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createAutomationIdPropertyCondition(automationId),
                        this.createControlTypeCondition(ControlType.MenuItem)));

        return new AutomationMenuItem(item);
    }
    
    // For MainMenus, the dropdown is disconnected from the MenuItem here
    private AutomationMenu getRealMenu() throws AutomationException {
    	if (parentMenuName == null || mainMenuParentElement == null) {
    		return null;
    	}
    	try {
	    	AutomationElement item = mainMenuParentElement.findFirst(new TreeScope(TreeScope.Descendants),
	                this.createAndCondition(
	                        this.createNamePropertyCondition(parentMenuName),
	                        this.createControlTypeCondition(ControlType.Menu)));
	    	if (item == null) {
	    		return null;
	    	}
	    	return new AutomationMenu(item);
    	} catch (ElementNotFoundException ex) {
    		return null;
    	}
    }
    
    /**
     * Is the control expanded
     * @return True if expanded
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public boolean isExpanded() throws AutomationException, PatternNotFoundException {
    	if (this.collapsePattern == null) {
    		this.collapsePattern = this.getExpandCollapsePattern();
    	}
    	if (this.collapsePattern != null) {
    		return collapsePattern.isExpanded();
    	}
    	throw new AutomationException("Collapse state cannot be determined");
    }

    /**
     * Collapses the element
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public void collapse() throws AutomationException, PatternNotFoundException {
    	if (this.collapsePattern == null) {
    		this.collapsePattern = this.getExpandCollapsePattern();
    	}
    	if (this.collapsePattern != null) {
    		this.collapsePattern.collapse();
            return;
    	}
    	throw new AutomationException("Cannot collapse");
    }

    /**
     * Expands the element
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public void expand() throws AutomationException, PatternNotFoundException {
    	if (this.collapsePattern == null) {
    		this.collapsePattern = this.getExpandCollapsePattern();
    	}
    	if (this.collapsePattern != null) {
    		this.collapsePattern.expand();
            return;
    	}
    	throw new AutomationException("Cannot expand");
    }

}
