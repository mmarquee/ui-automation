package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationBase {
    protected IUIAutomationElement element;

    protected IUIAutomation uiAuto;

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
}
