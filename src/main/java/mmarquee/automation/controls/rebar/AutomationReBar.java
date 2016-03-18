package mmarquee.automation.controls.rebar;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.controls.AutomationContainer;
import mmarquee.automation.uiautomation.IUIAutomation;

/**
 * Created by inpwt on 02/03/2016.
 *
 * Specialised type of pane, with a specific control name
 */
public class AutomationReBar extends AutomationContainer {
    /**
     * Construct the AutomationReBar
     * @param element The element
     * @param automation The automation library
     */
    public AutomationReBar(AutomationElement element, IUIAutomation automation) {
        super(element, automation);
    }
}
