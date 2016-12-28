package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Value;

/**
 * Created by Mark Humphreys on 15/02/2016.
 *
 * Wrapper around the MaskedEdit control - specifically the automated version.
 */
public class AutomationMaskedEdit extends AutomationEditBox {
    /**
     * Construct the AutomationMaskedEdit
     * @param element The element
     * @throws AutomationException Error in automation library
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMaskedEdit(AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);
    }

    /**
     * Construct the AutomationMaskedEdit
     * @param element The element
     * @param value Value pattern
     * @throws AutomationException Error in automation library
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMaskedEdit(AutomationElement element, Value value) throws PatternNotFoundException, AutomationException {
        super(element, value);
    }
}
