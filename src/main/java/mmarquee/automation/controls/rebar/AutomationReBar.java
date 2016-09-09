package mmarquee.automation.controls.rebar;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.controls.AutomationContainer;

/**
 * Created by inpwt on 02/03/2016.
 *
 * Specialised type of pane, with a specific control name
 */
public class AutomationReBar extends AutomationContainer {
    /**
     * Construct the AutomationReBar
     * @param element The element
     * @throws AutomationException Automation library error
     */
    public AutomationReBar(AutomationElement element) throws AutomationException {
        super(element);
    }
}
