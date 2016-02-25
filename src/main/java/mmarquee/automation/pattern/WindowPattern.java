package mmarquee.automation.pattern;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 25/02/2016.
 */
public class WindowPattern {
    private IUIAutomationWindowPattern pattern;

    public WindowPattern (IUIAutomationWindowPattern pattern) {
        this.pattern = pattern;
    }

    public void waitForInputIdle(int timeout){
        pattern.waitForInputIdle(timeout);
    }
}
