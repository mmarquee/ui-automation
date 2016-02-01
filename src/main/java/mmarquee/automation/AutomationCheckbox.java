package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 31/01/2016.
 */
public class AutomationCheckbox extends AutomationBase implements IAutomationCheckbox, IProvidesToggle {

    private IUIAutomationTogglePattern togglePattern;

    public void toggle () {
        this.togglePattern.toggle();
    }

    public ToggleState getToggleState () {
        return this.togglePattern.currentToggleState();
    }

    public AutomationCheckbox (IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        togglePattern = this.getTogglePattern();
    }
}
