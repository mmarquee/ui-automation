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

import mmarquee.automation.condition.*;
import mmarquee.automation.pattern.*;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 26/01/2016.
 */
public abstract class AutomationBase {
    protected IUIAutomationElement element;

    protected IUIAutomation uiAuto;

    /**
     * Constructor for the AutomationBase class
     * @param element Element to use
     * @param uiAuto The automation object
     */
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
    protected IUIAutomationElement findFirst(TreeScope scope, Condition condition) {
        return this.element.findFirst(scope, condition.getCondition());
    }

    /**
     * Finds all of the elements that are associated with the given condition.
     * @param scope The scope of where to look
     * @return IUIAutomationElementArray
     */
    protected IUIAutomationElementArray findAll(TreeScope scope) {
        TrueCondition condition = this.createTrueCondition();
        return this.findAll(scope, condition);
    }

    /**
     * Creates a true condition
     * @return The true condition
     */
    public TrueCondition createTrueCondition() {
        return new TrueCondition(this.uiAuto);
    }

    /**
     * Creates a false condition
     * @return The condition
     */
    public FalseCondition createFalseCondition() {
        return new FalseCondition(this.uiAuto);
    }

    /**
     * Creates a name property condition
     * @param name The name to use
     * @return The condition
     */
    public NameCondition createNamePropertyCondition(String name) {
        return new NameCondition(this.uiAuto, name);
    }

    /**
     * Creates a control type property condition
     * @param id The control type to use
     * @return The condition
     */
    public ControlIdCondition createControlTypeCondition(int id) {
        return new ControlIdCondition(this.uiAuto, id);
    }

    /**
     * Creatss an or condition
     * @param condition1 First condition
     * @param condition2 Second condition
     * @return The Or Condition
     */
    public OrCondition createOrCondition(Condition condition1, Condition condition2) {
        return new OrCondition(this.uiAuto, condition1, condition2);
    }

    /**
     * Creates an AND condition
     * @param condition1 First condition
     * @param condition2 Second condition
     * @return The And condition
     */
    public AndCondition createAndCondition(Condition condition1, Condition condition2) {
        return new AndCondition(this.uiAuto, condition1, condition2);
    }

    /**
     * Finds all of the elements that are associated with the given condition.
     * @param scope The scope of where to look
     * @param condition The condition to check
     * @return IUIAutomationElementArray
     */
    protected IUIAutomationElementArray findAll(TreeScope scope, Condition condition) {
        return this.element.findAll(scope, condition.getCondition());
    }

    /**
     * Gets the underlying automation pattern
     * @param id The control id to look for
     * @return The pattern
     * @throws PatternNotFoundException
     */
    private com4j.Com4jObject getPattern (int id) throws PatternNotFoundException {
        com4j.Com4jObject unknown = this.element.getCurrentPattern(id);

        if (unknown != null) {
            return unknown;
        } else {
            throw new PatternNotFoundException();
        }
    }

    /**
     * <p>
     * Gets the selectItem pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationSelectionItemPattern associated with this control
     */
    protected SelectionItemPattern getSelectItemPattern() throws PatternNotFoundException {
        com4j.Com4jObject unknown = this.getPattern(PatternID.SelectionItem);

        return new SelectionItemPattern(unknown.queryInterface(IUIAutomationSelectionItemPattern.class));
    }

    /**
     * <p>
     * Gets the selection pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationSelectionPattern associated with this control
     */
    protected SelectionPattern getSelectionPattern() throws PatternNotFoundException {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Selection);

        return new SelectionPattern(unknown.queryInterface(IUIAutomationSelectionPattern.class));
    }

    /**
     * <p>
     * Gets the value pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationValuePattern associated with this control
     */
    protected ValuePattern getValuePattern() throws PatternNotFoundException {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Value);

        return new ValuePattern(unknown.queryInterface(IUIAutomationValuePattern.class));
    }

    /**
     * <p>
     * Gets the table pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTablePattern associated with this control
     */
    protected TablePattern getTablePattern() throws PatternNotFoundException {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Table);

        return new TablePattern(unknown.queryInterface(IUIAutomationTablePattern.class));
    }

    /**
     * <p>
     * Gets the window pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationWindowPattern associated with this control
     */
    protected WindowPattern getWindowPattern() throws PatternNotFoundException {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Window);

        return new WindowPattern(unknown.queryInterface(IUIAutomationWindowPattern.class));
    }

    /**
     * <p>
     * Gets the expand/collapse pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationExpandCollapsePattern associated with this control
     */
    protected ExpandCollapsePattern getExpandCollapsePattern() throws PatternNotFoundException {
        com4j.Com4jObject unknown = this.getPattern(PatternID.ExpandCollapse);

        return new ExpandCollapsePattern(unknown.queryInterface(IUIAutomationExpandCollapsePattern.class));
    }

    /**
     * <p>
     * Gets the grid pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationGridPattern associated with this control
     */
    protected GridPattern getGridPattern() throws PatternNotFoundException {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Grid);

        return new GridPattern(unknown.queryInterface(IUIAutomationGridPattern.class));
    }

    /**
     * <p>
     * Gets the toggle pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTogglePattern associated with this control
     */
    protected TogglePattern getTogglePattern() throws PatternNotFoundException {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Toggle);

        return new TogglePattern(unknown.queryInterface(IUIAutomationTogglePattern.class));
    }

    /**
     * <p>
     * Gets the invoke pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationInvokePattern associated with this control
     */
    protected InvokePattern getInvokePattern() throws PatternNotFoundException {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Invoke);
        return new InvokePattern(unknown.queryInterface(IUIAutomationInvokePattern.class));
    }

    /**
     * <p>
     * Gets the text pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTextPattern associated with this control
     */
    protected TextPattern getTextPattern() throws PatternNotFoundException {
        com4j.Com4jObject unknown = this.getPattern(PatternID.Text);
        return new TextPattern(unknown.queryInterface(IUIAutomationTextPattern.class));
    }
}
