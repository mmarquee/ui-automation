package mmarquee.automation.pattern;

/**
 * Created by inpwt on 25/02/2016.
 *
 * Wrapper for the toggle pattern.
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
     * @return The toggled state
     */
    public ToggleState currentToggleState() {
        return ((IUIAutomationTogglePattern)(this.pattern)).currentToggleState();
    }
}
