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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.ControlType;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.Value;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 01/02/2016.
 *
 * Wrapper for the ComboBoc element.
 */
public class AutomationComboBox extends AutomationBase {
    private ExpandCollapse collapsePattern;
    private Value valuePattern;

    /**
     * Constructor for the AutomationComboBox.
     * @param element The underlying automation element
     * @param automation The IUIAutomation associated with this session
     */
    public AutomationComboBox(AutomationElement element, IUIAutomation automation) {
        super (element, automation);

        try {
            this.collapsePattern = this.getExpandCollapsePattern();
            this.valuePattern = this.getValuePattern();
        } catch (mmarquee.automation.pattern.PatternNotFoundException ex) {
            logger.info("Failed to get patterns");
        }
    }

    /**
     * Gets the text associated with this element
     * @return The current value
     */
    public String text() {

        String value = valuePattern.value();

        return value;
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

        for (AutomationElement element : collection) {
            int retValue = element.currentControlType();

            if (retValue == ControlType.ListItem) {

                list.add(new AutomationListItem(element, this.automation));
            }
        }

        return list;
    }
}