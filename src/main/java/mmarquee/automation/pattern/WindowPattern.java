package mmarquee.automation.pattern;

import com4j.Com4jObject;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 25/02/2016.
 */
public class WindowPattern implements Pattern {
    private IUIAutomationWindowPattern pattern;

    public WindowPattern () {
        this.pattern = null;
    }

    public boolean isAvailable () {
        return (pattern == null);
    }

    public void setPattern (Com4jObject pattern) {
        this.pattern = (IUIAutomationWindowPattern)pattern;
    }

    public void waitForInputIdle(int timeout){
        pattern.waitForInputIdle(timeout);
    }

    public void maximize() {
        this.pattern.setWindowVisualState(WindowVisualState.WindowVisualState_Maximized);
    }

    public void minimize() {
        this.pattern.setWindowVisualState(WindowVisualState.WindowVisualState_Minimized);
    }

    public int currentIsModal () {
        return this.pattern.currentIsModal();
    }
}
