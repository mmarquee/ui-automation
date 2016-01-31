package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 28/01/2016.
 */
public class AutomationContainer extends AutomationBase {
    protected IUIAutomationElement getControlByControlType(int index, int id) {
        IUIAutomationElementArray collection;

        IUIAutomationElement foundElement = null;

        collection = this.findAll(TreeScope.TreeScope_Descendants);

        int length = collection.length();

        int counter = 0;

        for (int count = 0; count < length; count++) {
            IUIAutomationElement element = collection.getElement(count);
            int retVal = element.currentControlType();

            if (retVal == id)  {
                if (counter == index) {
                    foundElement = element;
                } else {
                    counter++;
                }
            }
        }

        return foundElement;
    }

    public IAutomationCheckbox getCheckboxByIndex(int index) {
        return new AutomationCheckbox(this.uiAuto, this.getControlByControlType(index, ControlTypeID.CheckBoxControlTypeId));
    }

    public IAutomationTab getTabByIndex(int index){
        return new AutomationTab(this.uiAuto, this.getControlByControlType(index, ControlTypeID.TabControlTypeId));
    }

    public IAutomationEditBox getEditBoxByIndex(int index) {
        return new AutomationEditBox(this.uiAuto, this.getControlByControlType(index, ControlTypeID.EditControlTypeId));
    }

}
