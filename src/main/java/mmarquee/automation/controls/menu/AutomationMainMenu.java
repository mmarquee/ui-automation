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

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.*;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.IUIAutomationExpandCollapsePattern;
import mmarquee.automation.uiautomation.TreeScope;

import java.awt.*;
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
     */
    public AutomationMainMenu(AutomationElement parent, AutomationElement element) {
        super(element);
        this.parent = parent;
    }

    /**
     * Get the menu item associated with the hierarchy of names.
     * This is to get around an odd menu when testing in the Delphi application that
     * is used as a primary testbed for this library - it looks for "Help" and the expands
     * the menu item found and then pressed the 'A' key.
     * @param item0 Top level menu item
     * @param eventKey Key to press
     * @throws AutomationException Thrown when the element is not found.
     */
    public void menuItemFudge (String item0, int eventKey) throws AutomationException {
        PointerByReference pbr = this.automation.createAndCondition(
                this.createNamePropertyCondition(item0).getValue(),
                this.createControlTypeCondition(ControlType.MenuItem).getValue());

        AutomationElement item = this.findFirst(new TreeScope(TreeScope.TreeScope_Descendants), pbr);

        if (item != null) {
            PointerByReference pElement = item.getCurrentPattern(PatternID.ExpandCollapse.getValue());

            Unknown unkConditionA = new Unknown(pElement.getValue());
            PointerByReference pUnknownA = new PointerByReference();

            Guid.REFIID refiidA = new Guid.REFIID(IUIAutomationExpandCollapsePattern.IID);

            WinNT.HRESULT resultA = unkConditionA.QueryInterface(refiidA, pUnknownA);
            if (COMUtils.SUCCEEDED(resultA)) {
                IUIAutomationExpandCollapsePattern pattern =
                        IUIAutomationExpandCollapsePattern.Converter.PointerToInterface(pUnknownA);

                pattern.Expand();

                try {
                    Thread.sleep(750);
                } catch (Exception ex) {
                    // Seems to be fine, but interrupted
                }

                // Now press the correct key
                try {
                    Robot robot = new Robot();
                    robot.keyPress(eventKey);
                    robot.delay(500);
                } catch (AWTException ex) {
                    // What is going to happen here?
                }
            }
        }
    }

    /**
     * Get the menu item associated with the hierarchy of names
     * @param name0 First Name
     * @param name1 Second name
     * @return The menu item that matches the name
     * @throws AutomationException Something has gone wrong
     */
    public AutomationMenuItem getMenuItem (String name0, String name1) throws AutomationException {

        AutomationElement foundElement = null;

        AutomationElement item = this.findFirst(new TreeScope(TreeScope.TreeScope_Descendants),
                this.createAndCondition(
                        this.createNamePropertyCondition(name0).getValue(),
                        this.createControlTypeCondition(ControlType.MenuItem).getValue()));

        if (!name1.isEmpty()) {
            // Needs a subitem
            if (item != null) {
                // Find the sub-item now
                PointerByReference pElement = item.getCurrentPattern(PatternID.ExpandCollapse.getValue());

                Unknown unkConditionA = new Unknown(pElement.getValue());
                PointerByReference pUnknownA = new PointerByReference();

                Guid.REFIID refiidA = new Guid.REFIID(IUIAutomationExpandCollapsePattern.IID);

                WinNT.HRESULT resultA = unkConditionA.QueryInterface(refiidA, pUnknownA);
                if (COMUtils.SUCCEEDED(resultA)) {
                    IUIAutomationExpandCollapsePattern pattern =
                            IUIAutomationExpandCollapsePattern.Converter.PointerToInterface(pUnknownA);

                    pattern.Expand();
                    try {
                        Thread.sleep(750);
                    } catch (Exception ex) {
                        // Seems to be find
                    }

                    foundElement = this.getParent().findFirst(new TreeScope(TreeScope.TreeScope_Descendants),
                        this.createAndCondition(
                            this.createNamePropertyCondition(name1).getValue(),
                            this.createControlTypeCondition(ControlType.MenuItem).getValue()));
                }
            }
        }

        return new AutomationMenuItem(foundElement);
    }

    /**
     * Gets the items associated with this menu control
     * @return The list of items
     * @throws AutomationException Something has gone wrong
     */
    public List<AutomationMenuItem> getItems() throws AutomationException {
        List<AutomationElement> items = this.findAll(new TreeScope(TreeScope.TreeScope_Descendants),
                this.createControlTypeCondition(ControlType.MenuItem).getValue());

        List<AutomationMenuItem> list = new ArrayList<AutomationMenuItem>();
        
        for(AutomationElement item: items) {
            list.add(new AutomationMenuItem(item));
        }

        return list;
    }
}
