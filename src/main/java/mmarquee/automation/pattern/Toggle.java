package mmarquee.automation.pattern;

import mmarquee.automation.uiautomation.IUIAutomationTogglePattern;
import mmarquee.automation.uiautomation.ToggleState;

/**
 * Created by inpwt on 25/02/2016.
 */
public class Toggle extends BasePattern {
    /**
     * Toggles the control
     */
    public void toggle () {
        ((IUIAutomationTogglePattern)(this.pattern)).toggle();
    }

    /**
     * Gets the toggled state of the control
     * @return
     */
    public ToggleState currentToggleState() {
        return ((IUIAutomationTogglePattern)(this.pattern)).currentToggleState();
    }
}
