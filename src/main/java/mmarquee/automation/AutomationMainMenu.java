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

package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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

    public AutomationMenuItem getMenuItem (String name0, String name1) {

        IUIAutomationElement foundElement = null;

        if (!name1.isEmpty()) {
            // Needs a subitem
            IUIAutomationElement item = this.findFirst(TreeScope.TreeScope_Descendants,
                    this.createAndCondition(
                            this.createNamePropertyCondition(name0),
                            this.createControlTypeCondition(ControlTypeID.MenuItem)));
            if (item != null) {
                // Find the subitem now
            //    AutomationMenuItem menuItem = new AutomationMenuItem(item, uiAuto);
            //    menuItem.expand();
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
                                this.createControlTypeCondition(ControlTypeID.MenuItem)).getCondition());
            }
        } else {
            // Just get the item
            foundElement = this.findFirst(TreeScope.TreeScope_Descendants,
                    this.createAndCondition(
                            this.createNamePropertyCondition(name0),
                            this.createControlTypeCondition(ControlTypeID.MenuItem)));

        }

        return new AutomationMenuItem(foundElement, this.uiAuto);
    }

    public List<AutomationMenuItem> getItems() {
        IUIAutomationElementArray items = this.findAll(TreeScope.TreeScope_Descendants,
                this.createControlTypeCondition(ControlTypeID.MenuItem));

        List<AutomationMenuItem> list = new ArrayList<AutomationMenuItem>();

        for(int count = 0; count < items.length(); count++) {
            list.add(new AutomationMenuItem(items.getElement(count), uiAuto));
        }

        return list;
    }
}
