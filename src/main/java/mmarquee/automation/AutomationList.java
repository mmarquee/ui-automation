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

import mmarquee.automation.pattern.SelectionPattern;
import mmarquee.automation.pattern.ValuePattern;
import mmarquee.automation.uiautomation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationList extends AutomationBase {

    private SelectionPattern selectionPattern;

    public AutomationList(IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        this.selectionPattern = this.getSelectionPattern();
    }

    public AutomationListItem getItem(String name) {
        IUIAutomationElement item = this.findFirst(TreeScope.TreeScope_Descendants,
                this.createAndCondition(
                        this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(ControlTypeID.ListItem)));

        return new AutomationListItem(item, this.uiAuto);
    }

    public IUIAutomationElementArray getCurrentSelection() {
        return this.selectionPattern.getCurrentSelection();
    }

}
