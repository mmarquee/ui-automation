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

package mmarquee.automation.controls;

import mmarquee.automation.*;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * Created by Mark Humphreys on 20/02/2016.
 *
 * Wrapper for the TreeView element.
 */
public class AutomationTreeView extends AutomationBase {

    /**
     * Construct the AutomationTreeView
     * @param element The element
     * @throws AutomationException Automation library error
     */
    public AutomationTreeView(AutomationElement element) throws AutomationException {
        super(element);
    }

    /**
     * Gets the item that has the name
     * @param name The name to look for
     * @return The AutomationTreeViewItem
     * @throws ItemNotFoundException when the item is not found
     * @throws ElementNotFoundException when the element is not found
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationTreeViewItem getItem(String name) throws PatternNotFoundException, AutomationException {
        AutomationElement item = this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createNamePropertyCondition(name).getValue(),
                        this.createControlTypeCondition(ControlType.TreeItem).getValue()));

        if (item != null) {
            return new AutomationTreeViewItem(item);
        } else {
            throw new ItemNotFoundException();
        }
    }
}