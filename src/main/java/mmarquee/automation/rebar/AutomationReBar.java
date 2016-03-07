package mmarquee.automation.rebar;

import mmarquee.automation.AutomationContainer;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 02/03/2016.
 *
 * Specialised type of pane, with a specific control name
 */
public class AutomationReBar extends AutomationContainer {
    public AutomationReBar(AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
    }
}
