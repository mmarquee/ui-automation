package mmarquee.automation.controls.rebar;

import mmarquee.automation.controls.AutomationContainer;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 02/03/2016.
 *
 * Specialised type of pane, with a specific control name
 */
public class AutomationReBar extends AutomationContainer {
    /**
     * Construct the AutomationReBar
     * @param element The element
     * @param uiAuto The automation library
     */
    public AutomationReBar(AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
    }
}
