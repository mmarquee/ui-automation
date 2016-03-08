package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.uiautomation.IUIAutomation;

/**
 * Created by inpwt on 08/03/2016.
 *
 * Try and encapsulate the Custom ControlId
 */
public class AutomationCustom extends AutomationBase {
    /**
     * Constructor for the AutomationCustom
     * @param element The element
     * @param uiAuto The automation interface
     */
    public AutomationCustom (AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
    }
}
