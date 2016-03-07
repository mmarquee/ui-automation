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

import mmarquee.automation.pattern.*;
import mmarquee.automation.uiautomation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 01/02/2016.
 */
public class AutomationComboBox extends AutomationBase {
    private ExpandCollapse collapsePattern;
    private Value valuePattern;

    /**
     * Constructor for the AutomationComboBox.
     * @param element The underlying automation element
     * @param uiAuto The IUIAutomation associated with this session
     */
    public AutomationComboBox(AutomationElement element, IUIAutomation uiAuto) {
        super (element, uiAuto);

        try {
            this.collapsePattern = this.getExpandCollapsePattern();
            this.valuePattern = this.getValuePattern();
        } catch (mmarquee.automation.pattern.PatternNotFoundException ex) {
            // Handle this nicely somehow
        }
    }

    /**
     * Gets the text associated with this element
     * @return The current value
     */
    public String text() {
        return valuePattern.value();
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
     * Is the control expanded
     * @return True if expanded
     */
    public boolean isExpanded() {
        return collapsePattern.isExpanded();
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

        List<AutomationElement> collection =
                this.findAll(TreeScope.TreeScope_Descendants);

        int length = collection.size();

        for (int count = 0; count < length; count++ ) {
            AutomationElement element = collection.get(count);

            int retValue = element.element.currentControlType();

            if (retValue == ControlType.ListItem) {

                list.add(new AutomationListItem(element, this.uiAuto));
            }
        }

        return list;
    }
}