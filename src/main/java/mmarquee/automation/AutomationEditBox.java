package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationEditBox extends AutomationBase implements IAutomationEditBox {
    public String text() {
        //return this.name();
        com4j.Com4jObject unknown = this.element.getCurrentPattern(PatternID.ValuePatternId);

        String value = "";

        if (unknown != null) {
            IUIAutomationValuePattern pattern =
                    unknown.queryInterface(IUIAutomationValuePattern.class);

            if (pattern != null) {
                value = pattern.currentValue();
            }
        }

        return value;
    }

    public AutomationEditBox(IUIAutomation uiAuto, IUIAutomationElement element) {
        this.element = element;
        this.uiAuto = uiAuto;
    }
}
