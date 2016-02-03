package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 02/02/2016.
 */
public class AutomationButton extends AutomationBase implements ProvidesClick, ProvidesFocus {

    private IUIAutomationInvokePattern invokePattern = null;

    public AutomationButton(IUIAutomationElement element, IUIAutomation uiAuto) {
        super (element, uiAuto);

        this.invokePattern = this.getInvokePattern();
    }

    public void click() {
        this.invokePattern.invoke();
    }

    public void focus() {
        this.element.setFocus();
    }
}
