/*
 * Copyright 2016 inpwtepydjuf@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
                    break;
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
                    break;
                } else {
                    counter++;
                }
            }
        }

        return foundElement;
    }

    protected IUIAutomationElement getControlByControlType(String name, int id) {
        return this.findFirst(TreeScope.TreeScope_Descendants,
                this.createOrCondition(this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(id)));
    }

    /**
     * Gets the checkbox associated with the given index
     * @param index Index of the control
     * @return AutomationCheckbox that represents the found control
     */
    public AutomationCheckbox getCheckboxByIndex(int index) {
        return new AutomationCheckbox(this.getControlByControlType(index, ControlTypeID.CheckBox), this.uiAuto);
    }

    /**
     * Gets the Tab control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationTab getTabByIndex(int index){
        return new AutomationTab(this.getControlByControlType(index, ControlTypeID.Tab), this.uiAuto);
    }

    /**
     * Gets the Editbox control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationEditBox getEditBoxByIndex(int index) {
        return new AutomationEditBox(this.getControlByControlType(index, ControlTypeID.Edit), this.uiAuto);
    }

    /**
     * Gets the slider control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationSlider getSliderByName(int index) {
        return new AutomationSlider(this.getControlByControlType(index, ControlTypeID.Slider), this.uiAuto);
    }

    /**
     * Gets the (JHC) Masked Edit control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationMaskedEdit getMaskedEditByIndex(int index) {
        return new AutomationMaskedEdit(this.getControlByControlType(index, ControlTypeID.Edit, "TAutomationMaskEdit"), this.uiAuto);
    }

    /**
     * Gets the radio button control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationRadioButton getRadioButtonByIndex(int index) {
        return new AutomationRadioButton(this.getControlByControlType(index, ControlTypeID.RadioButton), this.uiAuto);
    }

    /**
     * Gets the text box control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationTextBox getTextBoxByIndex(int index) {
        return new AutomationTextBox(this.getControlByControlType(index, ControlTypeID.Text), this.uiAuto);
    }

    /**
     * Gets the combobox control associated with the given name
     * @param name Name of the control
     * @return The found control
     */
    public AutomationComboBox getComboboxByName(String name) {
        return new AutomationComboBox(this.getControlByControlType(name, ControlTypeID.ComboBox), this.uiAuto);
    }

    /**
     * Gets the button control associated with the given name
     * @param name Name of the control
     * @return The found control
     */
    public AutomationButton getButtonByName(String name) {
        return new AutomationButton(this.getControlByControlType(name, ControlTypeID.Button), this.uiAuto);
    }

    /**
     * Gets the (JHC) String Grid control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationStringGrid getStringGridByIndex(int index) {
        return new AutomationStringGrid(this.getControlByControlType(index, ControlTypeID.DataGrid, "TAutomationStringGrid"), this.uiAuto);
    }

    /**
     * Gets the hyperlink control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationHyperlink getHyperlinkByName(int index) {
        return new AutomationHyperlink((this.getControlByControlType(index, ControlTypeID.Hyperlink)), this.uiAuto);
    }

    /**
     * Gets the treeview control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationTreeView getTreeViewByIndex(int index) {
        return new AutomationTreeView((this.getControlByControlType(index, ControlTypeID.Tree)), this.uiAuto);
    }

    /**
     * Gets the calendar control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationCalendar getCalendarByName (int index) {
        return new AutomationCalendar((this.getControlByControlType(index, ControlTypeID.Calendar)), this.uiAuto);
    }
}
