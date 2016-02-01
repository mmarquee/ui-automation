package mmarquee.automation;

import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationValuePattern;

/**
 * Created by inpwt on 01/02/2016.
 */
public class AutomationTextBox extends AutomationBase implements IAutomationTextBox {
    public AutomationTextBox(IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
    }

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
}
