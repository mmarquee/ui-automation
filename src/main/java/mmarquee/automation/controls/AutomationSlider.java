package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Value;
import mmarquee.automation.uiautomation.IUIAutomation;

/**
 * Created by inpwt on 15/02/2016.
 *
 * Wrapper for the Slider element.
 *
 * IRangeProvider, ISelectionProvider, IValueProvider
 */
public class AutomationSlider extends AutomationBase {

    private Value valuePattern;

    /**
     * Construct the AutomationSlider
     * @param element The element
     * @param automation The automation library
     */
    public AutomationSlider(AutomationElement element, IUIAutomation automation) {
        super(element, automation);

        try {
            this.valuePattern = this.getValuePattern();
        } catch (PatternNotFoundException ex) {
            // Handle this nicely somehow
        }
    }

    /**
     * Gets the current value of the control
     * @return The current value
     */
    public String getValue() {
        return this.valuePattern.value();
    }
}
