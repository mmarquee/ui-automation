package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationWindow extends AutomationContainer implements IAutomationWindow {

    public void focus() {
        this.element.setFocus();
    }

    public AutomationWindow (IUIAutomation uiAuto, IUIAutomationElement element) {
        this.element = element;
        this.uiAuto = uiAuto;
    }
}
