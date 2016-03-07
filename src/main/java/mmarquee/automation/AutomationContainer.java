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

import mmarquee.automation.condition.ControlIdCondition;
import mmarquee.automation.rebar.AutomationReBar;
import mmarquee.automation.ribbon.AutomationRibbonBar;
import mmarquee.automation.stringgrid.AutomationStringGrid;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 28/01/2016.
 */
public class AutomationContainer extends AutomationBase {

    /**
     * Constructor for AutomationContainer
     *
     * @param element The underlying element
     * @param uiAuto  The automation bit
     */
    public AutomationContainer(AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
    }

    protected IUIAutomationElement getControlByControlType(int index, int id) {
        IUIAutomationElementArray collection;

        IUIAutomationElement foundElement = null;

        ControlIdCondition condition = new ControlIdCondition(this.uiAuto, id);

        collection = this.findAll(TreeScope.TreeScope_Descendants, condition);

        int length = collection.length();

        int counter = 0;

        for (int count = 0; count < length; count++) {
            IUIAutomationElement element = collection.getElement(count);
            if (counter == index) {
                foundElement = element;
                break;
            } else {
                counter++;
            }
        }

        return foundElement;
    }

    /**
     * Gets the control by the control type, for s given control index
     * @param index Index of the control
     * @param id Control type
     * @param controlName
     * @return The matching element
     */
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

    /**
     * Gets the control by the control type
     * @param name Name to use
     * @param id Control type
     * @return The matching element
     */
    protected IUIAutomationElement getControlByControlType(String name, int id) {
        return this.findFirst(TreeScope.TreeScope_Descendants,
                this.createAndCondition(
                        this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(id)));
    }

    /**
     * Gets the checkbox associated with the given index
     * @param index Index of the control
     * @return AutomationCheckbox that represents the found control
     */
    public AutomationCheckbox getCheckbox(int index) {
        return new AutomationCheckbox(new AutomationElement(this.getControlByControlType(index, ControlType.CheckBox)), this.uiAuto);
    }

    /**
     * Gets the Tab control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationTab getTab(int index) {
        return new AutomationTab(new AutomationElement(this.getControlByControlType(index, ControlType.Tab)), this.uiAuto);
    }

    /**
     * Gets the Editbox control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationEditBox getEditBox(int index) {
        return new AutomationEditBox(new AutomationElement(this.getControlByControlType(index, ControlType.Edit)), this.uiAuto);
    }

    /**
     * Gets the Editbox control associated with the given name
     * @param name Name of the control
     * @return The found control
     */
    public AutomationEditBox getEditBox(String name) {
        return new AutomationEditBox(new AutomationElement(this.getControlByControlType(name, ControlType.Edit)), this.uiAuto);
    }

    /**
     * Gets the slider control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationSlider getSliderByName(int index) {
        return new AutomationSlider(new AutomationElement(this.getControlByControlType(index, ControlType.Slider)), this.uiAuto);
    }

    /**
     * Gets the (JHC) Masked Edit control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationMaskedEdit getMaskedEdit(int index) {
        return new AutomationMaskedEdit(new AutomationElement(this.getControlByControlType(index, ControlType.Edit, "TAutomationMaskEdit")), this.uiAuto);
    }

    /**
     * Gets the radio button control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationRadioButton getRadioButton(int index) {
        return new AutomationRadioButton(new AutomationElement(this.getControlByControlType(index, ControlType.RadioButton)), this.uiAuto);
    }

    /**
     * Gets the text box control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationTextBox getTextBox(int index) {
        return new AutomationTextBox(new AutomationElement(this.getControlByControlType(index, ControlType.Text)), this.uiAuto);
    }

    /**
     * Gets the combobox control associated with the given name
     * @param name Name of the control
     * @return The found control
     */
    public AutomationComboBox getCombobox(String name) {
        return new AutomationComboBox(new AutomationElement(this.getControlByControlType(name, ControlType.ComboBox)), this.uiAuto);
    }

    /**
     * Gets the button control associated with the given name
     * @param name Name of the control
     * @return The found control
     */
    public AutomationButton getButton(String name) {
        return new AutomationButton(new AutomationElement(this.getControlByControlType(name, ControlType.Button)), this.uiAuto);
    }

    /**
     * Gets the button control associated with the given index
     * @param index
     * @return
     */
    public AutomationButton getButton(int index) {
        AutomationElement elem = new AutomationElement(this.getControlByControlType(index, ControlType.Button));
        return new AutomationButton(elem, this.uiAuto);
    }

    /**
     * Gets the  String Grid control associated with the given index, with a specific control name
     * @param index Index of the control
     * @param controlName*
     * @return The found control
     */
    public AutomationStringGrid getStringGrid(int index, String controlName) {
        return new AutomationStringGrid(new AutomationElement(this.getControlByControlType(index, ControlType.DataGrid, controlName)), this.uiAuto);
    }

    /**
     * Gets the  String Grid control associated with the given index
     * @param index
     * @return The string grid
     */
    public AutomationStringGrid getStringGrid(int index) {
        return new AutomationStringGrid(new AutomationElement(this.getControlByControlType(index, ControlType.DataGrid)), this.uiAuto);
    }

    /**
     * Gets the document associated with the given index
     * @param index Index of the control
     * @return The document control
     */
    public AutomationDocument getDocument(int index) {
        return new AutomationDocument(new AutomationElement(this.getControlByControlType(index, ControlType.Document)), this.uiAuto);
    }

    /**
     * Gets the hyperlink control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationHyperlink getHyperlink(int index) {
        return new AutomationHyperlink(new AutomationElement(this.getControlByControlType(index, ControlType.Hyperlink)), this.uiAuto);
    }

    /**
     * Gets the treeview control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationTreeView getTreeView(int index) {
        return new AutomationTreeView(new AutomationElement(this.getControlByControlType(index, ControlType.Tree)), this.uiAuto);
    }

    /**
     * Gets the treeview control associated with the given name
     * @param name Name of the control
     * @return The found control
     */
    public AutomationTreeView getTreeView(String name) {
        return new AutomationTreeView(new AutomationElement(this.getControlByControlType(name, ControlType.Tree)), this.uiAuto);
    }

    /**
     * Gets the list control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationList getListItem(int index) {
        return new AutomationList(new AutomationElement(this.getControlByControlType(index, ControlType.List)), this.uiAuto);
    }

    /**
     * Gets the calendar control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationCalendar getCalendar(int index) {
        return new AutomationCalendar(new AutomationElement(this.getControlByControlType(index, ControlType.Calendar)), this.uiAuto);
    }

    /**
     * Gets the panel control associated with the given index
     * @param index Index of the control
     */
    public AutomationPanel getPanel(int index) {
        return new AutomationPanel(new AutomationElement(this.getControlByControlType(index, ControlType.Pane)), this.uiAuto);
    }

    /**
     * Gets the panel control associated with the given name
     * @param name Name of the control
     */
    public AutomationPanel getPanel(String name) {
        return new AutomationPanel(new AutomationElement(this.getControlByControlType(name, ControlType.Pane)), this.uiAuto);
    }

    /**
     * Get the AppBar associated with the given index
     * @param index The index
     * @return The AutomationAppBar
     */
    public AutomationAppBar getAppBar(int index) {
        return new AutomationAppBar(new AutomationElement(this.getControlByControlType(index, ControlType.AppBar)), this.uiAuto);
    }

    /**
     * Get the ToolBar associated with the given name
     * @param name The name
     * @return The AutomationToolBar
     */
    public AutomationToolBar getToolBar(String name) {
        return new AutomationToolBar(new AutomationElement(this.getControlByControlType(name, ControlType.ToolBar)), this.uiAuto);
    }

    /**
     * Get the ToolBar associated with the given index
     * @param index The index
     * @return The AutomationToolBar
     */
    public AutomationToolBar getToolBar(int index) {
        return new AutomationToolBar(new AutomationElement(this.getControlByControlType(index, ControlType.ToolBar)), this.uiAuto);
    }

    /**
     * Get the RibbonBar associated this container
     * @return The AutomationRibbonBar
     */
    public AutomationRibbonBar getRibbonBar() {
        return new AutomationRibbonBar(new AutomationElement(this.getControlByControlType(0, ControlType.Pane, "UIRibbonCommandBarDock")), this.uiAuto);
    }

    /**
     * Gets the AutomationReBar associated with this index
     * @param index The index
     * @return The control wrapper
     */
    public AutomationReBar getReBar(int index) {
        return new AutomationReBar(new AutomationElement(this.getControlByControlType(index, ControlType.Pane, "ReBarWindow32")), this.uiAuto);
    }

    /**
     * Get the AutomationSplitButton associated with the given name
     * @param name The name to look for
     * @return The AutomationSplitButton
     */
    public AutomationSplitButton getSplitButton(String name) {
        return new AutomationSplitButton(new AutomationElement(this.getControlByControlType(name, ControlType.SplitButton)), this.uiAuto);
    }
}