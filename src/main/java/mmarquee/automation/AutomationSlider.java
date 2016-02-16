package mmarquee.automation;

import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationValuePattern;

/**
 * Created by inpwt on 15/02/2016.
 *
 * IRangeProvider, ISelectionProvider, IValueProvider
 */
public class AutomationSlider extends AutomationBase {

    private IUIAutomationValuePattern valuePattern;

    public AutomationSlider(IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
        this.valuePattern = this.getValuePattern();
    }

    /**
     * Gets the current value of the control
     * @return The current value
     */
    public String getValue() {
        return this.valuePattern.currentValue();
    }
}
