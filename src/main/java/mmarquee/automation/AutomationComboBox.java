package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 01/02/2016.
 */
public class AutomationComboBox extends AutomationBase {
    private IUIAutomationExpandCollapsePattern collapsePattern;

    public AutomationComboBox(IUIAutomationElement element, IUIAutomation uiAuto) {
        super (element, uiAuto);

        this.collapsePattern = this.getCollapsePattern();
    }

    public String text() {
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

    public void setText(String text) {
        com4j.Com4jObject unknown = this.element.getCurrentPattern(PatternID.ValuePatternId);

        String value = "";

        if (unknown != null) {
            IUIAutomationValuePattern pattern =
                    unknown.queryInterface(IUIAutomationValuePattern.class);

            if (pattern != null) {
                pattern.setValue(text);
            }
        }
    }

    public void expand() {
        this.collapsePattern.expand();
    }

    public void collapse() {
        this.collapsePattern.collapse();
    }
    // just for a while, need to encapsulate the elements properly
    public List<AutomationListItem> getList() {

        List<AutomationListItem> list = new ArrayList<AutomationListItem>();

        IUIAutomationElementArray collection =
                this.findAll(TreeScope.TreeScope_Descendants);

        int length = collection.length();

        for (int count = 0; count < length; count++ ) {
            IUIAutomationElement element = collection.getElement(count);

            int retValue = element.currentControlType();

            if (retValue == ControlTypeID.ListItemControlTypeId) {

                list.add(new AutomationListItem(element, this.uiAuto));
            }
        }

        return list;
    }
}
