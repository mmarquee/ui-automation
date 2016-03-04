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
package mmarquee.automation.menu;

import mmarquee.automation.AutomationBase;
import mmarquee.automation.ControlType;
import mmarquee.automation.PatternID;
import mmarquee.automation.uiautomation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 09/02/2016.
 */
public class AutomationMainMenu extends AutomationBase {

    private IUIAutomationElement parent;

    public IUIAutomationElement getParent() {
        return this.parent;
    }

    public AutomationMainMenu(IUIAutomationElement parent, IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
        this.parent = parent;
    }

    /**
     * Get the menu item associated with the hierarchy of names
     * @param name0 First Name
     * @param name1 Second name
     * @return The menu item that matches the name
     */
    public AutomationMenuItem getMenuItem (String name0, String name1) {

        IUIAutomationElement foundElement = null;

        IUIAutomationElement item = this.findFirst(TreeScope.TreeScope_Descendants,
                this.createAndCondition(
                        this.createNamePropertyCondition(name0),
                        this.createControlTypeCondition(ControlType.MenuItem)));

        if (!name1.isEmpty()) {
            // Needs a subitem
            if (item != null) {
                // Find the subitem now
                com4j.Com4jObject unknown = item.getCurrentPattern(PatternID.ExpandCollapse);
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
                                this.createControlTypeCondition(ControlType.MenuItem)).getCondition());

            }
        }

        return new AutomationMenuItem(foundElement, this.uiAuto);
    }

    /**
     * Gets the items associated with this menu control
     * @return The list of items
     */
    public List<AutomationMenuItem> getItems() {
        IUIAutomationElementArray items = this.findAll(TreeScope.TreeScope_Descendants,
                this.createControlTypeCondition(ControlType.MenuItem));

        List<AutomationMenuItem> list = new ArrayList<AutomationMenuItem>();

        for(int count = 0; count < items.length(); count++) {
            list.add(new AutomationMenuItem(items.getElement(count), uiAuto));
        }

        return list;
    }
}
