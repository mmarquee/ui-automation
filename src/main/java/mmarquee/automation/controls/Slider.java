package mmarquee.automation.controls;

/**
 * Wrapper for the Slider element.
 *
 * @author Mark Humphreys
 * Date 15/02/2016.
 *
 * IRangeProvider, ISelectionProvider, IValueProvider
 */
public final class Slider extends AutomationBase
        implements ImplementsRangeValue {

    /**
     * Construct the Slider.
     * @param builder The builder
     */
      public Slider(final ElementBuilder builder) {
        super(builder);
    }
}
