package mmarquee.automation.pattern;

import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationSelectionItemPattern;
import mmarquee.automation.uiautomation.IUIAutomationTogglePattern;
import mmarquee.automation.uiautomation.ToggleState;

/**
 * Created by inpwt on 25/02/2016.
 */
public class TogglePattern extends Pattern {
    private IUIAutomationTogglePattern pattern;

    public TogglePattern(IUIAutomation automation, IUIAutomationTogglePattern pattern) {
        super(automation);

        this.pattern = pattern;
    }

    public void toggle () {
        pattern.toggle();
    }

    public ToggleState currentToggleState() {
        return pattern.currentToggleState();
    }

}
