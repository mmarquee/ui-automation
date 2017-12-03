package mmarquee.automation.controls;

import mmarquee.automation.AutomationException;

/**
 * Wrapper around the PasswordEditBox control.
 */
public final class AutomationPasswordEditBox extends AutomationEditBox {

    /**
     * The class name.
     */
	public static final String CLASS_NAME = "PasswordBox";
	
    /**
     * Construct the AutomationPasswordEditBox.
     * @param builder The builder
     * @throws AutomationException Error in automation library
     */
    public AutomationPasswordEditBox(final ElementBuilder builder)
            throws AutomationException {
        super(builder);
        assertClassName(CLASS_NAME);
    }
}
