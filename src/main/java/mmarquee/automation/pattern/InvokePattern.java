package mmarquee.automation.pattern;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 25/02/2016.
 */
public class InvokePattern extends Pattern {
    private IUIAutomationInvokePattern pattern;

    public InvokePattern (IUIAutomation automation, IUIAutomationInvokePattern pattern) {
        super(automation);

        this.pattern = pattern;
    }

    public void invoke() {
        this.pattern.invoke();
    }
}
