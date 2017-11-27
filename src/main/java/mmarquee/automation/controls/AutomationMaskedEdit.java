package mmarquee.automation.controls;

import mmarquee.automation.AutomationException;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Wrapper around the MaskedEdit control - specifically the automated version.
 *
 * @author Mark Humphreys
 * Date 15/02/2016.
 */
public final class AutomationMaskedEdit extends AutomationEditBox {

    /**
     * The class name.
     */
	public static final String CLASS_NAME = "TAutomatedMaskEdit";
	
    /**
     * Construct the AutomationMaskedEdit.
     * @param builder The builder
     * @throws AutomationException Error in automation library
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMaskedEdit(final ElementBuilder builder)
            throws PatternNotFoundException, AutomationException {
        super(builder);
        assertClassName(CLASS_NAME);
    }
}
