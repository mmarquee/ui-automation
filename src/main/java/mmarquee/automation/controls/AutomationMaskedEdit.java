package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.controls.AutomationEditBox;
import mmarquee.automation.uiautomation.IUIAutomation;

/**
 * Created by inpwt on 15/02/2016.
 */
public class AutomationMaskedEdit extends AutomationEditBox {
    /**
     * Construct the AutomationMaskedEdit
     * @param element The element
     * @param uiAuto The automation library
     */
    public AutomationMaskedEdit(AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
    }
}
