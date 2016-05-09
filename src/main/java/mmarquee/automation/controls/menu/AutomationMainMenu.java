/*
 * Copyright 2016 inpwtepydjuf@gmail.com
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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.PatternID;
import mmarquee.automation.condition.TrueCondition;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.pattern.raw.IUIAutomationExpandCollapsePattern;
import mmarquee.automation.uiautomation.TreeScope;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 09/02/2016.
 *
 * Wrapper for the MainMenu element.
 */
public class AutomationMainMenu extends AutomationBase {

    private AutomationElement parent;

    /**
     * Gets the parent element of the menu element
     * @return The parent element
     */
    private AutomationElement getParent() {
        return this.parent;
    }

    /**
     * Constructor for AutomationMainMenu
     * @param parent Parent of the element
     * @param element The element
     * @param automation The automation interface
     */
    public AutomationMainMenu(AutomationElement parent, AutomationElement element, IUIAutomation automation) {
        super(element, automation);
        this.parent = parent;
    }

    /**
     * Get the menu item associated with the hierarchy of names.
     * This is to get around an odd menu when testing in Delphi.
     */
    public void getMenuItemFudge () throws ElementNotFoundException {
        AutomationElement item = this.findFirst(TreeScope.TreeScope_Descendants,
                this.createAndCondition(
                        this.createNamePropertyCondition("Help"),
                        this.createControlTypeCondition(ControlType.MenuItem)));

        if (item != null) {
            com4j.Com4jObject unknown = item.getCurrentPattern(PatternID.ExpandCollapse.getValue());
            IUIAutomationExpandCollapsePattern pattern = unknown.queryInterface(IUIAutomationExpandCollapsePattern.class);
            pattern.expand();

            try {
                Thread.sleep(750);
            } catch (Exception ex) {
                // Seems to be find
            }

            try {
                Robot robot = new Robot();
                robot.keyPress(KeyEvent.VK_A);
                robot.delay(500);
            } catch (AWTException ex) {
                // What is going to happen here?
            }
        }
    }

    /**
     * Get the menu item associated with the hierarchy of names
     * @param name0 First Name
     * @param name1 Second name
     * @return The menu item that matches the name
     */
    public AutomationMenuItem getMenuItem (String name0, String name1) throws ElementNotFoundException {

        AutomationElement foundElement = null;

        AutomationElement item = this.findFirst(TreeScope.TreeScope_Descendants,
                this.createAndCondition(
                        this.createNamePropertyCondition(name0),
                        this.createControlTypeCondition(ControlType.MenuItem)));

        if (!name1.isEmpty()) {
            // Needs a subitem
            if (item != null) {
                // Find the subitem now
                com4j.Com4jObject unknown = item.getCurrentPattern(PatternID.ExpandCollapse.getValue());
                IUIAutomationExpandCollapsePattern pattern = unknown.queryInterface(IUIAutomationExpandCollapsePattern.class);
                pattern.expand();
                try {
                    Thread.sleep(750);
                } catch (Exception ex) {
                    // Seems to be find
                }

                foundElement = this.getParent().findFirst(TreeScope.TreeScope_Descendants,
                        this.createAndCondition(
                                this.createNamePropertyCondition(name1),
                                this.createControlTypeCondition(ControlType.MenuItem)));

            }
        }

        return new AutomationMenuItem(foundElement, this.automation);
    }

    /**
     * Gets the items associated with this menu control
     * @return The list of items
     */
    public List<AutomationMenuItem> getItems() {
        List<AutomationElement> items = this.findAll(TreeScope.TreeScope_Descendants,
                this.createControlTypeCondition(ControlType.MenuItem));

        List<AutomationMenuItem> list = new ArrayList<AutomationMenuItem>();
        
        for(AutomationElement item: items) {
            list.add(new AutomationMenuItem(item, automation));
        }

        return list;
    }
}
