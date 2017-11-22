package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Value;

/**
 * Wrapper around the PasswordEditBox control
 */
public class AutomationPasswordEditBox extends AutomationEditBox {
	
	public static final String CLASS_NAME = "PasswordBox";
	
    /**
     * Construct the AutomationPasswordEditBox
     * @param builder The builder
     * @throws AutomationException Error in automation library
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationPasswordEditBox(final ElementBuilder builder) throws PatternNotFoundException, AutomationException {
        super(builder);
        assertClassName(CLASS_NAME);
    }
}
