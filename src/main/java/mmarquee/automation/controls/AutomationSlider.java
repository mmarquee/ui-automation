package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Range;

/**
 * @author Mark Humphreys
 * Date 15/02/2016.
 *
 * Wrapper for the Slider element.
 *
 * IRangeProvider, ISelectionProvider, IValueProvider
 */
public class AutomationSlider extends AutomationBase {

    /**
     * The range pattern.
     */
    private Range rangePattern;

    /**
     * Construct the AutomationSlider.
     * @param element The element.
     * @throws PatternNotFoundException Expected pattern not found.
     * @throws AutomationException Automation library error.
     */
    public AutomationSlider(final AutomationElement element)
            throws PatternNotFoundException, AutomationException {
        super(element);
//        this.rangePattern = this.getRangePattern();
    }

    /**
     * Construct the AutomationSlider.
     * @param element The element.
     * @param pattern The range pattern.
     * @param instance Automation instance.
     * @throws PatternNotFoundException Expected pattern not found.
     * @throws AutomationException Automation library error.
     */
    AutomationSlider(final AutomationElement element,
                     final Range pattern,
                     final UIAutomation instance)
            throws PatternNotFoundException, AutomationException {
        super(element, instance);
        this.rangePattern = pattern;
    }

    /**
     * Gets the range value.
     *
     * @return The range value.
     * @throws AutomationException Error in automation library.
     * @throws PatternNotFoundException Cannot find pattern.
     */
    public double getRangeValue()
            throws AutomationException, PatternNotFoundException {
        if (this.rangePattern == null) {
            this.rangePattern = this.getRangePattern();
        }

        return this.rangePattern.getValue();
    }

    /**
     * Sets the range value.
     *
     * @param value The value to set.
     * @throws AutomationException Error in automation library.
     * @throws PatternNotFoundException Cannot find pattern.
     */
    public void setRangeValue(final double value)
            throws AutomationException, PatternNotFoundException {
        if (this.rangePattern == null) {
            this.rangePattern = this.getRangePattern();
        }

        this.rangePattern.setValue(value);
    }
}
