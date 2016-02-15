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
 * Created by inpwt on 01/02/2016.
 */
public class AutomationComboBox extends AutomationBase {
    private IUIAutomationExpandCollapsePattern collapsePattern;

    public AutomationComboBox(IUIAutomationElement element, IUIAutomation uiAuto) {
        super (element, uiAuto);

        this.collapsePattern = this.getExpandCollapsePattern();
    }

    public String text() {
        com4j.Com4jObject unknown = this.element.getCurrentPattern(PatternID.ValuePatternId);

        String value = "";

        if (unknown != null) {
            IUIAutomationValuePattern pattern =
                    unknown.queryInterface(IUIAutomationValuePattern.class);

            if (pattern != null) {
                value = pattern.currentValue();
            }
        }

        return value;
    }

    public void setText(String text) {
        com4j.Com4jObject unknown = this.element.getCurrentPattern(PatternID.ValuePatternId);

        String value = "";

        if (unknown != null) {
            IUIAutomationValuePattern pattern =
                    unknown.queryInterface(IUIAutomationValuePattern.class);

            if (pattern != null) {
                pattern.setValue(text);
            }
        }
    }

    public void expand() {
        this.collapsePattern.expand();
    }

    public void collapse() {
        this.collapsePattern.collapse();
    }
    // just for a while, need to encapsulate the elements properly
    public List<AutomationListItem> getList() {

        List<AutomationListItem> list = new ArrayList<AutomationListItem>();

        IUIAutomationElementArray collection =
                this.findAll(TreeScope.TreeScope_Descendants);

        int length = collection.length();

        for (int count = 0; count < length; count++ ) {
            IUIAutomationElement element = collection.getElement(count);

            int retValue = element.currentControlType();

            if (retValue == ControlTypeID.ListItemControlTypeId) {

                list.add(new AutomationListItem(element, this.uiAuto));
            }
        }

        return list;
    }
}
