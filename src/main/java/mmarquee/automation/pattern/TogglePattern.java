package mmarquee.automation.pattern;

import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationSelectionItemPattern;
import mmarquee.automation.uiautomation.IUIAutomationTogglePattern;
import mmarquee.automation.uiautomation.ToggleState;

/**
 * Created by inpwt on 25/02/2016.
 */
public class TogglePattern implements Pattern {
    private IUIAutomationTogglePattern pattern;

    public boolean isAvailable () {
        return (pattern == null);
    }

    public void setPattern(IUIAutomationTogglePattern pattern) {
        this.pattern = pattern;
    }

    public TogglePattern() {
        this.pattern = null;
    }

    public void toggle () {
        pattern.toggle();
    }

    public ToggleState currentToggleState() {
        return pattern.currentToggleState();
    }

}
