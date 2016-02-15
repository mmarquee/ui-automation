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

    public String name () {
        return this.element.currentName();
    }

    public void setName(String name) {
        this.element.setName(name);
    }

    protected IUIAutomationElementArray findAll() {
        return this.findAll(TreeScope.TreeScope_Children);
    }

    protected IUIAutomationElementArray findAll(TreeScope scope) {
        IUIAutomationCondition condition = this.createTrueCondition();

        IUIAutomationElementArray collection = this.findAll(scope, condition);

        return collection;
    }

    protected IUIAutomationCondition createTrueCondition() {
        return uiAuto.createTrueCondition();
    }

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
        com4j.Com4jObject unknown = this.getPattern(PatternID.SelectionItemPatternId);

        return unknown.queryInterface(IUIAutomationSelectionItemPattern.class);    }

    /**
     * <p>
     * Gets the selection pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationSelectionPattern associated with this control
     */
    protected IUIAutomationSelectionPattern getSelectionPattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.SelectionPatternId);

        return unknown.queryInterface(IUIAutomationSelectionPattern.class);
    }

    /**
     * <p>
     * Gets the value pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationValuePattern associated with this control
     */
    protected IUIAutomationValuePattern getValuePattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.ValuePatternId);

        return unknown.queryInterface(IUIAutomationValuePattern.class);
    }

    /**
     * <p>
     * Gets the table pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTablePattern associated with this control
     */
    protected IUIAutomationTablePattern getTablePattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.TablePatternId);

        return unknown.queryInterface(IUIAutomationTablePattern.class);
    }

    /**
     * <p>
     * Gets the window pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationWindowPattern associated with this control
     */
    protected IUIAutomationWindowPattern getWindowPattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.WindowPatternId);

        return unknown.queryInterface(IUIAutomationWindowPattern.class);
    }

    /**
     * <p>
     * Gets the expand/collapse pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationExpandCollapsePattern associated with this control
     */
    protected IUIAutomationExpandCollapsePattern getExpandCollapsePattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.ExpandCollapsePatternId);

        return unknown.queryInterface(IUIAutomationExpandCollapsePattern.class);
    }

    /**
     * <p>
     * Gets the grid pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationGridPattern associated with this control
     */
    protected IUIAutomationGridPattern getGridPattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.GridPatternId);

        return unknown.queryInterface(IUIAutomationGridPattern.class);
    }

    /**
     * <p>
     * Gets the toggle pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTogglePattern associated with this control
     */
    protected IUIAutomationTogglePattern getTogglePattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.TogglePatternId);

        return unknown.queryInterface(IUIAutomationTogglePattern.class);
    }

    /**
     * <p>
     * Gets the involes pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationInvokePattern associated with this control
     */
    protected IUIAutomationInvokePattern getInvokePattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.InvokePatternId);
        return unknown.queryInterface(IUIAutomationInvokePattern.class);
    }

    /**
     * <p>
     * Gets the text pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTextPattern associated with this control
     */
    protected IUIAutomationTextPattern getTextPattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.TextPatternId);
        return unknown.queryInterface(IUIAutomationTextPattern.class);
    }
}
