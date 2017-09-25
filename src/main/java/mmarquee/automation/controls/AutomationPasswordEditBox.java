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
     * @param element The element
     * @throws AutomationException Error in automation library
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationPasswordEditBox(AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);
        assertClassName(CLASS_NAME);
    }

    /**
     * Construct the AutomationPasswordEditBox
     * @param element The element
     * @param value Value pattern
     * @param instance Automaiton instance
     * @throws AutomationException Error in automation library
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationPasswordEditBox(AutomationElement element, Value value, UIAutomation instance) throws PatternNotFoundException, AutomationException {
        super(element, value, instance);
        assertClassName(CLASS_NAME);
    }
}
