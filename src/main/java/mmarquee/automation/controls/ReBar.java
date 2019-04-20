package mmarquee.automation.controls;

import mmarquee.automation.AutomationException;

/**
 * Specialised type of pane, with a specific control name.
 *
 * @author Mark Humphreys
 * Date 02/03/2016.
 *
 */
public final class ReBar extends Panel {

    /**
     * The class name.
     */
	public final static String CLASS_NAME = "ReBarWindow32";

    /**
     * Construct the ReBar.
     *
     * @param builder The builder
     * @throws AutomationException Automation library error
     */
    public ReBar(final ElementBuilder builder)
            throws AutomationException {
        super(builder);
        assertClassName(CLASS_NAME);
    }
}
