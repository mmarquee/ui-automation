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

/**
 * Created by inpwt on 09/02/2016.
 */
public class AutomationMainMenu extends AutomationBase {
    public AutomationMainMenu(IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
    }
/*
    public AutomationMenu getMenu(String name) {
        IUIAutomationCondition condition = uiAuto.createTrueCondition();

        IUIAutomationElementArray collection =
                this.element.findAll(TreeScope.TreeScope_Children, condition);

        int length = collection.length();

        boolean found = false;
        IUIAutomationElement foundElement = null;

        for (int count = 0; count < length; count++) {
            IUIAutomationElement elem = collection.getElement(count);
            String eName = elem.currentName();

            if (eName.equals(name)) {
                found = true;
                foundElement = elem;
                break;
            }
        }

        if (found) {
            return new AutomationMenu(foundElement, uiAuto);
        } else {
            return null;
        }
    }
*/

    public AutomationMenuItem getMenuItem (String name) {
        IUIAutomationElement item = this.element.findFirst(TreeScope.TreeScope_Descendants,
                uiAuto.createAndCondition(
                        uiAuto.createPropertyCondition(PropertyID.Name, name),
                        uiAuto.createPropertyCondition(PropertyID.ControlType, ControlTypeID.MenuItem)));

        return new AutomationMenuItem(item, this.uiAuto);
    }

    public List<AutomationMenuItem> getItems() {
        IUIAutomationElementArray items = this.element.findAll(TreeScope.TreeScope_Descendants,
                        uiAuto.createPropertyCondition(PropertyID.ControlType, ControlTypeID.MenuItem));

        List<AutomationMenuItem> list = new ArrayList<AutomationMenuItem>();

        for(int count = 0; count < items.length(); count++) {
            list.add(new AutomationMenuItem(items.getElement(count), uiAuto));
        }

        return list;
    }
}
