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

    protected IUIAutomationSelectionItemPattern getSelectItemPattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.SelectionItemPatternId);

        return unknown.queryInterface(IUIAutomationSelectionItemPattern.class);    }

    protected IUIAutomationSelectionPattern getSelectionPattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.SelectionPatternId);

        return unknown.queryInterface(IUIAutomationSelectionPattern.class);
    }

    protected IUIAutomationValuePattern getValuePattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.ValuePatternId);

        return unknown.queryInterface(IUIAutomationValuePattern.class);
    }

    protected IUIAutomationTablePattern getTablePattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.TablePatternId);

        return unknown.queryInterface(IUIAutomationTablePattern.class);
    }

    protected IUIAutomationWindowPattern getWindowPattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.WindowPatternId);

        return unknown.queryInterface(IUIAutomationWindowPattern.class);
    }

    protected IUIAutomationExpandCollapsePattern getCollapsePattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.ExpandCollapsePatternId);

        return unknown.queryInterface(IUIAutomationExpandCollapsePattern.class);
    }

    protected IUIAutomationGridPattern getGridPattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.GridPatternId);

        return unknown.queryInterface(IUIAutomationGridPattern.class);
    }

    protected IUIAutomationTogglePattern getTogglePattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.TogglePatternId);

        return unknown.queryInterface(IUIAutomationTogglePattern.class);
    }

    protected IUIAutomationInvokePattern getInvokePattern() {
        com4j.Com4jObject unknown = this.getPattern(PatternID.InvokePatternId);
        return unknown.queryInterface(IUIAutomationInvokePattern.class);
    }
}
