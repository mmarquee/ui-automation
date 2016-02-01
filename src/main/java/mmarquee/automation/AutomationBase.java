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

}
