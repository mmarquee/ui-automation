package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.uiautomation.IUIAutomation;

/**
 * Created by inpwt on 15/02/2016.
 *
 * Wrapper around the MaskedEdit control - specifically the automated version.
 */
public class AutomationMaskedEdit extends AutomationEditBox {
    /**
     * Construct the AutomationMaskedEdit
     * @param element The element
     */
    public AutomationMaskedEdit(AutomationElement element) {
        super(element);
    }
}
