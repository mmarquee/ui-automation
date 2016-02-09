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

    protected IUIAutomationSelectionItemPattern getSelectItemPattern() {
        com4j.Com4jObject unknown = this.element.getCurrentPattern(PatternID.SelectionItemPatternId);

        IUIAutomationSelectionItemPattern pattern = null;

        if (unknown != null) {
            IUIAutomationSelectionItemPattern selectItemPattern =
                    unknown.queryInterface(IUIAutomationSelectionItemPattern.class);

            if (selectItemPattern != null) {
                pattern = selectItemPattern;
            }
        }

        return pattern;
    }

    protected IUIAutomationSelectionPattern getSelectionPattern() {
        com4j.Com4jObject unknown = this.element.getCurrentPattern(PatternID.SelectionPatternId);

        IUIAutomationSelectionPattern pattern = null;

        if (unknown != null) {
            IUIAutomationSelectionPattern selectPattern =
                    unknown.queryInterface(IUIAutomationSelectionPattern.class);

            if (selectPattern != null) {
                pattern = selectPattern;
            }
        }

        return pattern;
    }

    protected IUIAutomationValuePattern getValuePattern() {
        com4j.Com4jObject unknown = this.element.getCurrentPattern(PatternID.ValuePatternId);

        IUIAutomationValuePattern valuePattern = null;

        if (unknown != null) {
            IUIAutomationValuePattern pattern =
                    unknown.queryInterface(IUIAutomationValuePattern.class);

            if (pattern != null) {
                valuePattern = pattern;
            }
        }

        return valuePattern;
    }

    protected IUIAutomationTablePattern getTablePattern() {
        com4j.Com4jObject unknown = this.element.getCurrentPattern(PatternID.TablePatternId);

        IUIAutomationTablePattern tablePattern = null;

        if (unknown != null) {
            IUIAutomationTablePattern pattern =
                    unknown.queryInterface(IUIAutomationTablePattern.class);

            if (pattern != null) {
                tablePattern = pattern;
            }
        }

        return tablePattern;
    }

    private com4j.Com4jObject getPattern (int id) {
        com4j.Com4jObject unknown = this.element.getCurrentPattern(id);

        if (unknown != null) {
            return unknown;
        } else {
            return null;
        }
    }

    protected IUIAutomationExpandCollapsePattern getCollapsePattern() {
        com4j.Com4jObject unknown = getPattern(PatternID.ExpandCollapsePatternId);

        IUIAutomationExpandCollapsePattern pattern =
                unknown.queryInterface(IUIAutomationExpandCollapsePattern.class);

        return pattern;
    }

    protected IUIAutomationGridPattern getGridPattern() {
        com4j.Com4jObject unknown = getPattern(PatternID.GridPatternId);

        IUIAutomationGridPattern gridPattern = null;

        IUIAutomationGridPattern pattern =
                unknown.queryInterface(IUIAutomationGridPattern.class);

        if (pattern != null) {
            gridPattern = pattern;
        }

        return gridPattern;
    }

    protected IUIAutomationTogglePattern getTogglePattern() {
        com4j.Com4jObject unknown = this.element.getCurrentPattern(PatternID.TogglePatternId);

        IUIAutomationTogglePattern togglePattern = null;

        if (unknown != null) {
            IUIAutomationTogglePattern pattern =
                    unknown.queryInterface(IUIAutomationTogglePattern.class);

            if (pattern != null) {
                togglePattern = pattern;
            }
        }

        return togglePattern;
    }

    protected IUIAutomationInvokePattern getInvokePattern() {
        com4j.Com4jObject unknown = this.element.getCurrentPattern(PatternID.InvokePatternId);

        IUIAutomationInvokePattern invokePattern = null;

        if (unknown != null) {
            IUIAutomationInvokePattern pattern =
                    unknown.queryInterface(IUIAutomationInvokePattern.class);

            if (pattern != null) {
                invokePattern = pattern;
            }
        }

        return invokePattern;
    }
}
