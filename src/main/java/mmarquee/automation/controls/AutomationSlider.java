package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Range;

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
     */
    public AutomationSlider(AutomationElement element) {
        super(element);

        try {
            this.rangePattern = this.getRangePattern();
        } catch (PatternNotFoundException ex) {
            logger.warn("RangeValue pattern not found");
        }
    }

    public double getRangeValue() throws AutomationException {
        return this.rangePattern.getValue();
    }

    public void setRangeValue(double value) throws AutomationException {
        this.rangePattern.setValue(value);
    }
}
