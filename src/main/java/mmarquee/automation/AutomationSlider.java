package mmarquee.automation;

import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.ValuePattern;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationValuePattern;

/**
 * Created by inpwt on 15/02/2016.
 *
 * IRangeProvider, ISelectionProvider, IValueProvider
 */
public class AutomationSlider extends AutomationBase {

    private ValuePattern valuePattern;

    public AutomationSlider(IUIAutomationElement element, IUIAutomation uiAuto) {
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
        return this.valuePattern.currentValue();
    }
}
