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

import mmarquee.automation.pattern.ExpandCollapsePattern;
import mmarquee.automation.pattern.ValuePattern;
import mmarquee.automation.uiautomation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 01/02/2016.
 */
public class AutomationComboBox extends AutomationBase {
    private ExpandCollapsePattern collapsePattern;
    private ValuePattern valuePattern;

    public AutomationComboBox(IUIAutomationElement element, IUIAutomation uiAuto) {
        super (element, uiAuto);

        this.collapsePattern = this.getExpandCollapsePattern();
        this.valuePattern = this.getValuePattern();
    }

    /**
     * Gets the text associated with this element
     * @return The current value
     */
    public String text() {
        return valuePattern.currentValue();
    }

    /**
     * Sets the text associated with this element
     * @param text The value to be set
     */
    public void setText(String text) {
        valuePattern.setValue(text);
    }

    /**
     * Expands the element
     */
    public void expand() {
        this.collapsePattern.expand();
    }

    /**
     * Collapses the element
     */
    public void collapse() {
        this.collapsePattern.collapse();
    }

    /**
     * Gets the list of items associated with this element.
     * @return List of AutomationListItems
     */
    public List<AutomationListItem> getList() {

        List<AutomationListItem> list = new ArrayList<AutomationListItem>();

        IUIAutomationElementArray collection =
                this.findAll(TreeScope.TreeScope_Descendants);

        int length = collection.length();

        for (int count = 0; count < length; count++ ) {
            IUIAutomationElement element = collection.getElement(count);

            int retValue = element.currentControlType();

            if (retValue == ControlTypeID.ListItem) {

                list.add(new AutomationListItem(element, this.uiAuto));
            }
        }

        return list;
    }
}
