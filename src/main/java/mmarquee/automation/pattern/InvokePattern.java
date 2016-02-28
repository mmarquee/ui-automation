package mmarquee.automation.pattern;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 25/02/2016.
 */
public class InvokePattern implements Pattern {
    private IUIAutomationInvokePattern pattern;

    public InvokePattern () {
        this.pattern = null;
    }

    public void setPattern (IUIAutomationInvokePattern pattern) {
        this.pattern = pattern;
    }

    public void invoke() {
        this.pattern.invoke();
    }

    public boolean isAvailable () {
        return (pattern == null);
    }
}
