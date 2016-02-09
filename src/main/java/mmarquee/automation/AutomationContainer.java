package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 28/01/2016.
 */
public class AutomationContainer extends AutomationBase {

    public AutomationContainer (IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
    }

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

    protected IUIAutomationElement getControlByControlType(int index, int id, String controlName) {
        IUIAutomationElementArray collection;

        IUIAutomationElement foundElement = null;

        collection = this.findAll(TreeScope.TreeScope_Descendants);

        int length = collection.length();

        int counter = 0;

        for (int count = 0; count < length; count++) {
            IUIAutomationElement element = collection.getElement(count);
            int retVal = element.currentControlType();
            String cName = element.currentClassName();

            if (cName.equals(controlName)) {
                if (counter == index) {
                    foundElement = element;
                } else {
                    counter++;
                }
            }
        }

        return foundElement;
    }

    protected IUIAutomationElement getControlByControlType(String name, int id) {
        IUIAutomationElementArray collection;

        IUIAutomationElement foundElement = null;

        collection = this.findAll(TreeScope.TreeScope_Descendants);

        int length = collection.length();

        for (int count = 0; count < length; count++) {
            IUIAutomationElement element = collection.getElement(count);
            int retVal = element.currentControlType();
            String elementName = element.currentName();

            if (retVal == id)  {
                if (elementName.equals(name)) {
                    foundElement = element;
                }
            }
        }

        return foundElement;
    }

    public AutomationCheckbox getCheckboxByIndex(int index) {
        return new AutomationCheckbox(this.getControlByControlType(index, ControlTypeID.CheckBoxControlTypeId), this.uiAuto);
    }

    public AutomationTab getTabByIndex(int index){
        return new AutomationTab(this.getControlByControlType(index, ControlTypeID.TabControlTypeId), this.uiAuto);
    }

    public AutomationEditBox getEditBoxByIndex(int index) {
        return new AutomationEditBox(this.getControlByControlType(index, ControlTypeID.EditControlTypeId), this.uiAuto);
    }

    public AutomationRadioButton getRadioButtonByIndex(int index) {
        return new AutomationRadioButton(this.getControlByControlType(index, ControlTypeID.RadioButtonControlTypeId), this.uiAuto);
    }

    public AutomationTextBox getTextBoxByIndex(int index) {
        return new AutomationTextBox(this.getControlByControlType(index, ControlTypeID.TextControlTypeId), this.uiAuto);
    }

    public AutomationComboBox getComboboxByName(String name) {
        return new AutomationComboBox(this.getControlByControlType(name, ControlTypeID.ComboBoxControlTypeId), this.uiAuto);
    }

    public AutomationButton getButtonByName(String name) {
        return new AutomationButton(this.getControlByControlType(name, ControlTypeID.ButtonControlTypeId), this.uiAuto);
    }

    public AutomationStringGrid getStringGridByIndex(int name) {
        return new AutomationStringGrid(this.getControlByControlType(name, ControlTypeID.DataGridControlTypeId, "TAutomationStringGrid"), this.uiAuto);
    }
}
