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
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 19/02/2016.
 */
public class AutomationSystemMenu extends AutomationBase {

    public AutomationSystemMenu(IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        this.getItems();
    }

    public AutomationMenuItem getItem(String name) throws ItemNotFoundException {

        IUIAutomationCondition condition = uiAuto.createTrueCondition();

        IUIAutomationElementArray collection =
                this.element.findAll(TreeScope.TreeScope_Descendants, condition);

        int length = collection.length();
        IUIAutomationElement foundElement = null;
        boolean found = false;

        for(int count = 0; count < length; count++) {
            IUIAutomationElement elem = collection.getElement(count);
            String eName = elem.currentName();

            if (eName.equals(name)) {
                found = true;
                foundElement = elem;
                break;
            }
        }

        if (found) {
            return new AutomationMenuItem(foundElement, this.uiAuto);
        } else {
            // Throw an exception
            throw  new ItemNotFoundException();
        }
    }

    private void getItems() {
        IUIAutomationCondition condition = uiAuto.createTrueCondition();

        IUIAutomationElementArray collection =
                this.element.findAll(TreeScope.TreeScope_Children, condition);

        int length = collection.length();

        IUIAutomationElement element = collection.getElement(0);

        String name = element.currentName();
    }
}

