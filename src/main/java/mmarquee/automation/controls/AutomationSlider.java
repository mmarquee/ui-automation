package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Range;

/**
 * Created by Mark Humphreys on 15/02/2016.
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
     * @throws PatternNotFoundException Expected pattern not found
     * @throws AutomationException Automation library error
     */
    public AutomationSlider(AutomationElement element)
            throws PatternNotFoundException, AutomationException {
        super(element);
        this.rangePattern = this.getRangePattern();
    }

    /**
     * Construct the AutomationSlider
     * @param element The element
     * @param pattern The range pattern
     * @throws PatternNotFoundException Expected pattern not found
     * @throws AutomationException Automation library error
     */
    public AutomationSlider(AutomationElement element, Range pattern)
            throws PatternNotFoundException, AutomationException {
        super(element);
        this.rangePattern = pattern;
    }

    /**
     * Gets the range value
     * @return The range value
     * @throws AutomationException Error in automation library
     */
    public double getRangeValue() throws AutomationException {
        return this.rangePattern.getValue();
    }

    /**
     * Sets the range value
     * @param value The value to set
     * @throws AutomationException Error in automation library
     */
    public void setRangeValue(double value) throws AutomationException {
        this.rangePattern.setValue(value);
    }
}
