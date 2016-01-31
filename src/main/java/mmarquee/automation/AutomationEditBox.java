package mmarquee.automation;

import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElement;

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationEditBox extends AutomationBase implements IAutomationEditBox {
    public String text() {
        return this.name();
    }

    public AutomationEditBox(IUIAutomation uiAuto, IUIAutomationElement element) {
        this.element = element;
        this.uiAuto = uiAuto;
    }
}
