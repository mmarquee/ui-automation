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

    public AutomationMenuItem getMenuItem (String name) {
        IUIAutomationElement item = this.findFirst(TreeScope.TreeScope_Descendants,
                this.createAndCondition(
                     this.createNamePropertyCondition(name),
                     this.createControlTypeCondition(ControlTypeID.MenuItem)));

        return new AutomationMenuItem(item, this.uiAuto);
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
