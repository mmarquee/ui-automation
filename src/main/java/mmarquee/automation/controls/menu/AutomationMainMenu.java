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

import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.*;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.IUIAutomationExpandCollapsePattern;
import mmarquee.automation.uiautomation.IUIAutomationExpandCollapsePatternConverter;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Humphreys on 09/02/2016.
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

    public static ControlType controlType = ControlType.MenuBar;

    /**
     * Constructor for AutomationMainMenu
     * @param parent Parent of the element
     * @param element The element
     * @throws AutomationException Automation library error
     */
    public AutomationMainMenu(AutomationElement parent, AutomationElement element) throws AutomationException {
        super(element);
        this.parent = parent;
    }

    /**
     * Gets the raw pattern from the given element
     * @param item The Element
     * @return The raw collapse pattern
     * @throws AutomationException Failed to get pattern
     */
    public IUIAutomationExpandCollapsePattern getExpandCollapsePatternFromItem(AutomationElement item)
                        throws AutomationException {
        PointerByReference pElement = item.getPattern(PatternID.ExpandCollapse.getValue());

        Unknown unkConditionA = makeUnknown(pElement.getValue());

        PointerByReference pUnknownA = new PointerByReference();

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(new Guid.REFIID(IUIAutomationExpandCollapsePattern.IID), pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            IUIAutomationExpandCollapsePattern pattern =
                    IUIAutomationExpandCollapsePatternConverter.PointerToInterface(pUnknownA);

            return pattern;
        } else {
            throw new AutomationException("QueryInterface failed");
        }
    }

    /**
     * Get the menu item associated with the hierarchy of names
     * @param name0 First Name
     * @param name1 Second name
     * @return The menu item that matches the name
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMenuItem getMenuItem (String name0, String name1)
            throws PatternNotFoundException, AutomationException {

        AutomationElement foundElement = null;

        AutomationElement item = this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createNamePropertyCondition(name0),
                        this.createControlTypeCondition(ControlType.MenuItem)));

        if (!name1.isEmpty()) {
            // Needs a sub-item
            if (item != null) {
                // Find the sub-item now
                IUIAutomationExpandCollapsePattern pattern =
                        this.getExpandCollapsePatternFromItem(item);

                pattern.expand();

                try {
                    Thread.sleep(750);
                } catch (Exception ex) {
                    // Seems to be fine
                }

                foundElement = this.getParent().findFirst(new TreeScope(TreeScope.Descendants),
                        this.createAndCondition(
                                this.createNamePropertyCondition(name1),
                                this.createControlTypeCondition(ControlType.MenuItem)));
            }
        } else {
            foundElement = item;
        }

        if (foundElement == null) {
            throw new ItemNotFoundException("Failed to find element");
        }

        return new AutomationMenuItem(foundElement);
    }

    /**
     * Gets the items associated with this menu control
     * @return The list of items
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationMenuItem> getItems() throws PatternNotFoundException, AutomationException {
        List<AutomationElement> items = this.findAll(new TreeScope(TreeScope.Descendants),
                this.createControlTypeCondition(ControlType.MenuItem));

        List<AutomationMenuItem> list = new ArrayList<AutomationMenuItem>();
        
        for(AutomationElement item: items) {
            list.add(new AutomationMenuItem(item));
        }

        return list;
    }
}
