package mmarquee.automation.controls;

import mmarquee.automation.AutomationException;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Range;

/**
 * Wrapper for the Slider element.
 *
 * @author Mark Humphreys
 * Date 15/02/2016.
 *
 * IRangeProvider, ISelectionProvider, IValueProvider
 */
public final class AutomationSlider extends AutomationBase {

    /**
     * The range pattern.
     */
    private Range rangePattern;

    /**
     * Construct the AutomationSlider.
     * @param builder The builder
     */
      public AutomationSlider(final ElementBuilder builder) {
        super(builder);
        this.rangePattern = builder.getRange();
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
