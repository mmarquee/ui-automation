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
 * Created by inpwt on 26/01/2016.
 */
public class AutomationBase {
    protected IUIAutomationElement element;

    protected IUIAutomation uiAuto;

    public AutomationBase (IUIAutomationElement element, IUIAutomation uiAuto) {
        this.element = element;
        this.uiAuto = uiAuto;
    }

    /**
     * Gets the name associated with this element
     * @return The name of the element
     */
    public String name () {
        return this.element.currentName();
    }

    /**
     * Sets the name of the element
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.element.setName(name);
    }

    /**
     * Finds all of the elements that are associated with this element
     * @return IUIAutomationElementArray
     */
    protected IUIAutomationElementArray findAll() {
        return this.findAll(TreeScope.TreeScope_Children);
    }

    /**
     * Finds the first match for the oondition
     * @param scope The scope of where to look
     * @param condition The condition to use
     * @return The found IUIAutomationElement
     */
    protected IUIAutomationElement findFirst(TreeScope scope, IUIAutomationCondition condition) {
        return this.element.findFirst(scope, condition);
    }

    /**
     * Finds all of the elements that are associated with the given condition.
     * @param scope The scope of where to look
     * @return IUIAutomationElementArray
     */
    protected IUIAutomationElementArray findAll(TreeScope scope) {
        IUIAutomationCondition condition = this.createTrueCondition();
        return this.findAll(scope, condition);
    }

    /**
     * Creates a true condition
     * @return The true condition
     */
    public IUIAutomationCondition createTrueCondition() {
        return uiAuto.createTrueCondition();
    }

    /**
     * Creates a false condition
     * @return The condition
     */
    public IUIAutomationCondition createFalseCondition() {
        return uiAuto.createFalseCondition();
    }

    /**
     * Creates a name property condition
     * @param name The name to use
     * @return The condition
     */
    public IUIAutomationCondition createNamePropertyCondition(String name) {
        return uiAuto.createPropertyCondition(PropertyID.Name, name);
    }

    /**
     * Creates a control type property condition
     * @param id The control type to use
     * @return The condition
     */
    public IUIAutomationCondition createControlTypeCondition(int id) {
        return uiAuto.createPropertyCondition(PropertyID.ControlType, id);
    }

    /**
     * Creatss an or condition
     * @param condition1 First condition
     * @param condition2 Second condition
     * @return The Or Condition
     */
    public IUIAutomationCondition createOrCondition(IUIAutomationCondition condition1, IUIAutomationCondition condition2) {
        return uiAuto.createOrCondition(condition1, condition2);
    }

    /**
     * Creates an AND condition
     * @param condition1
     * @param condition2
     * @return
     */
    public IUIAutomationCondition createAndCondition(IUIAutomationCondition condition1, IUIAutomationCondition condition2) {
        return uiAuto.createAndCondition(condition1, condition2);
    }

    /**
     * Finds all of the elements that are associated with the given condition.
     * @param scope The scope of where to look
     * @param condition The condition to check
     * @return IUIAutomationElementArray
     */
    protected IUIAutomationElementArray findAll(TreeScope scope, IUIAutomationCondition condition) {
        IUIAutomationElementArray collection = this.element.findAll(scope, condition);

        return collection;
    }

    private com4j.Com4jObject getPattern (int id) {
        com4j.Com4jObject unknown = this.element.getCurrentPattern(id);

        if (unknown != null) {
            return unknown;
        } else {
            return null;
        }
    }

    /**
     * <p>
     * Gets the selectItem pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationSelectionItemPattern associated with this control
     */
    protected IUIAutomationSelectionItemPattern getSelectItemPattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.SelectionItem);

        return unknown.queryInterface(IUIAutomationSelectionItemPattern.class);    }

    /**
     * <p>
     * Gets the selection pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationSelectionPattern associated with this control
     */
    protected IUIAutomationSelectionPattern getSelectionPattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Selection);

        return unknown.queryInterface(IUIAutomationSelectionPattern.class);
    }

    /**
     * <p>
     * Gets the value pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationValuePattern associated with this control
     */
    protected IUIAutomationValuePattern getValuePattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Value);

        return unknown.queryInterface(IUIAutomationValuePattern.class);
    }

    /**
     * <p>
     * Gets the table pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTablePattern associated with this control
     */
    protected IUIAutomationTablePattern getTablePattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Table);

        return unknown.queryInterface(IUIAutomationTablePattern.class);
    }

    /**
     * <p>
     * Gets the window pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationWindowPattern associated with this control
     */
    protected IUIAutomationWindowPattern getWindowPattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Window);

        return unknown.queryInterface(IUIAutomationWindowPattern.class);
    }

    /**
     * <p>
     * Gets the expand/collapse pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationExpandCollapsePattern associated with this control
     */
    protected IUIAutomationExpandCollapsePattern getExpandCollapsePattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.ExpandCollapse);

        return unknown.queryInterface(IUIAutomationExpandCollapsePattern.class);
    }

    /**
     * <p>
     * Gets the grid pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationGridPattern associated with this control
     */
    protected IUIAutomationGridPattern getGridPattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Grid);

        return unknown.queryInterface(IUIAutomationGridPattern.class);
    }

    /**
     * <p>
     * Gets the toggle pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTogglePattern associated with this control
     */
    protected IUIAutomationTogglePattern getTogglePattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Toggle);

        return unknown.queryInterface(IUIAutomationTogglePattern.class);
    }

    /**
     * <p>
     * Gets the involes pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationInvokePattern associated with this control
     */
    protected IUIAutomationInvokePattern getInvokePattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Invoke);
        return unknown.queryInterface(IUIAutomationInvokePattern.class);
    }

    /**
     * <p>
     * Gets the text pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTextPattern associated with this control
     */
    protected IUIAutomationTextPattern getTextPattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Text);
        return unknown.queryInterface(IUIAutomationTextPattern.class);
    }
}
