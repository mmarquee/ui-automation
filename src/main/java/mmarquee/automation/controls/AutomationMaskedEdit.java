package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Value;

/**
 * Created by Mark Humphreys on 15/02/2016.
 *
 * Wrapper around the MaskedEdit control - specifically the automated version.
 */
public class AutomationMaskedEdit extends AutomationEditBox {
	
	public static final String CLASS_NAME = "TAutomatedMaskEdit";
	
    /**
     * Construct the AutomationMaskedEdit
     * @param element The element
     * @throws AutomationException Error in automation library
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMaskedEdit(AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);
        assertClassName(CLASS_NAME);
    }

    /**
     * Construct the AutomationMaskedEdit
     * @param element The element
     * @param value Value pattern
     * @param instance Automaiton instance
     * @throws AutomationException Error in automation library
     * @throws PatternNotFoundException Expected pattern not found
     */
    AutomationMaskedEdit(AutomationElement element, Value value, UIAutomation instance) throws PatternNotFoundException, AutomationException {
        super(element, value, instance);
        assertClassName(CLASS_NAME);
    }
}
