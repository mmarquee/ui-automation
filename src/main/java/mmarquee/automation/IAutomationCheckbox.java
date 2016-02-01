package mmarquee.automation;

import mmarquee.automation.uiautomation.ToggleState;

/**
 * Created by inpwt on 31/01/2016.
 */
public interface IAutomationCheckbox extends IProvidesToggle{
    void toggle();
    ToggleState getToggleState ();
}
