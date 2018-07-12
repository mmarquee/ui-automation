package mmarquee.automation.controls;

/**
 * Wrapper for the Slider element.
 *
 * @author Mark Humphreys
 * Date 15/02/2016.
 *
 * IRangeProvider, ISelectionProvider, IValueProvider
 */
public final class AutomationSlider extends AutomationBase
        implements ImplementsRangeValue {

    /**
     * Construct the AutomationSlider.
     * @param builder The builder
     */
      public AutomationSlider(final ElementBuilder builder) {
        super(builder);
    }
}
