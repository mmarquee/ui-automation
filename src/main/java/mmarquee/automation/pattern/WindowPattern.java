package mmarquee.automation.pattern;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 25/02/2016.
 */
public class WindowPattern extends Pattern {
    private IUIAutomationWindowPattern pattern;

    public WindowPattern (IUIAutomation automation, IUIAutomationWindowPattern pattern) {
        super(automation);
        this.pattern = pattern;
    }

    public void waitForInputIdle(int timeout){
        pattern.waitForInputIdle(timeout);
    }
}
