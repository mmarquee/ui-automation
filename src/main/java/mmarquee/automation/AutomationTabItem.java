package mmarquee.automation;

import com4j.tlbimp.def.IInterface;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 28/01/2016.
 */
public class AutomationTabItem extends AutomationContainer implements IAutomationTabItem{

    public AutomationTabItem(IUIAutomation uiAuto, IUIAutomationElement element) {
        this.element = element;
        this.uiAuto = uiAuto;
    }

    public void select() {
        com4j.Com4jObject unknown = this.element.getCurrentPattern(PatternID.SelectionItemPatternId);

        if (unknown != null) {
            IUIAutomationSelectionItemPattern pattern =
                    unknown.queryInterface(IUIAutomationSelectionItemPattern.class);

            if (pattern != null) {
                pattern.select();
            }
        }
    }
}
