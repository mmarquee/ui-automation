package mmarquee.automation.pattern;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 25/02/2016.
 */
public class InvokePattern {
    private IUIAutomationInvokePattern pattern;

    public InvokePattern (IUIAutomationInvokePattern pattern) {
        this.pattern = pattern;
    }

    public void invoke() {
        this.pattern.invoke();
    }
}
