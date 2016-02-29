package mmarquee.automation;

import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.ValuePattern;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 16/02/2016.
 *
 * Implements IGridProvider, IScrollProvider, ITableProvider, IValueProvider
 */
public class AutomationCalendar extends AutomationBase {
    private ValuePattern valuePattern;

    public AutomationCalendar(IUIAutomationElement element, IUIAutomation uiAuto) {
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