package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Value;
import mmarquee.automation.uiautomation.IUIAutomation;

/**
 * Created by inpwt on 15/02/2016.
 *
 * IRangeProvider, ISelectionProvider, IValueProvider
 */
public class AutomationSlider extends AutomationBase {

    private Value valuePattern;

    public AutomationSlider(AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

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
