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

/**
 * Created by inpwt on 20/02/2016.
 */
public class AutomationTreeView extends AutomationBase {

    public AutomationTreeView(AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
    }

    /**
     * Gets the item that has the name
     * @param name The name to look for
     * @return The AutomationTreeViewItem
     */
    public AutomationTreeViewItem getItem(String name) throws ItemNotFoundException, ElementNotFoundException {
        AutomationElement item = this.findFirst(TreeScope.TreeScope_Descendants,
                this.createAndCondition(
                        this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(ControlType.TreeItem)));

        if (item != null) {
            return new AutomationTreeViewItem(item, this.uiAuto);
        } else {
            throw new ItemNotFoundException();
        }
    }
}