package mmarquee.automation.condition;

import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationCondition;

/**
 * Created by inpwt on 24/02/2016.
 */
public abstract class PropertyCondition extends Condition {
    protected int property = -1;

    public PropertyCondition (IUIAutomation uiAuto) {
        super(uiAuto);
    }
}
