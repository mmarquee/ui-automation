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
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Value;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Humphreys on 01/02/2016.
 *
 * Wrapper for the ComboBoc element.
 */
public class AutomationComboBox extends AutomationBase {
    private ExpandCollapse collapsePattern;
    private Value valuePattern;

    /**
     * Constructor for the AutomationComboBox.
     * @param element The underlying automation element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern(s) not found
     */
    public AutomationComboBox(AutomationElement element) throws PatternNotFoundException, AutomationException {
        super (element);

        this.collapsePattern = this.getExpandCollapsePattern();
        this.valuePattern = this.getValuePattern();
    }

    /**
     * Constructor for the AutomationComboBox.
     * @param element The underlying automation element
     * @param collapse The collapse pattern
     * @param value The value pattern
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern(s) not found
     */
    public AutomationComboBox(AutomationElement element, ExpandCollapse collapse, Value value)
            throws PatternNotFoundException, AutomationException {
        super (element);

        this.collapsePattern = collapse;
        this.valuePattern = value;
    }

    /**
     * Gets the text associated with this element
     * @return The current value
     * @throws AutomationException Something has gone wrong
     */
    public String text() throws AutomationException {
        return valuePattern.value();
    }

    /**
     * Sets the text associated with this element
     * @param text The value to be set.
     * @throws AutomationException Something has gone wrong
     */
    public void setText(String text) throws AutomationException {
        valuePattern.setValue(text);
    }

    /**
     * Expands the element.
     * @throws AutomationException Something has gone wrong
     */
    public void expand() throws AutomationException {
        this.collapsePattern.expand();
    }

    /**
     * Is the control expanded
     * @return True if expanded
     * @throws AutomationException Something has gone wrong
     */
    public boolean isExpanded() throws AutomationException {
        return collapsePattern.isExpanded();
    }

    /**
     * Collapses the element
     * @throws AutomationException Something has gone wrong
     */
    public void collapse() throws AutomationException {
        this.collapsePattern.collapse();
    }

    /**
     * Gets the list of items associated with this element.
     * @return List of AutomationListItems
     * @throws AutomationException Automation issue
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationListItem> getList() throws PatternNotFoundException, AutomationException {

        List<AutomationListItem> list = new ArrayList<AutomationListItem>();

        List<AutomationElement> collection =
                this.findAll(new TreeScope(TreeScope.Descendants));

        for (AutomationElement element : collection) {
            int retValue = element.currentControlType();

            if (retValue == ControlType.ListItem.getValue()) {
                list.add(new AutomationListItem(element));
            }
        }

        return list;
    }
}