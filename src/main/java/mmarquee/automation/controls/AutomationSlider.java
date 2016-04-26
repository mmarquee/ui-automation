package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Range;
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

    private Range rangePattern;

    /**
     * Construct the AutomationSlider
     * @param element The element
     * @param automation The automation library
     */
    public AutomationSlider(AutomationElement element, IUIAutomation automation) {
        super(element, automation);

        try {
            this.rangePattern = this.getRangePattern();
        } catch (PatternNotFoundException ex) {
            logger.info("RangeValue pattern not found");
        }
    }

    public double getRangeValue() {
        return this.rangePattern.getValue();
    }

    public void setRangeValue(double value) {
        this.rangePattern.setValue(value);
    }
}
