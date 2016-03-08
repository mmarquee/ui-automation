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

import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.condition.Condition;
import mmarquee.automation.uiautomation.*;

import java.util.List;

/**
 * Created by inpwt on 19/02/2016.
 */
public class AutomationSystemMenu extends AutomationBase {

    public AutomationSystemMenu(AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        this.getItems();
    }

    public AutomationMenuItem getItem(String name) throws ItemNotFoundException {

        Condition condition = this.createTrueCondition();

        List<AutomationElement> collection =
                this.findAll(TreeScope.TreeScope_Descendants, condition);

        int length = collection.size();
        AutomationElement foundElement = null;
        boolean found = false;

        for(int count = 0; count < length; count++) {
            AutomationElement elem = collection.get(count);
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
        Condition condition = this.createTrueCondition();

        List<AutomationElement> collection =
                this.findAll(TreeScope.TreeScope_Children, condition);

        AutomationElement element = collection.get(0);

        String name = element.currentName();
    }
}

