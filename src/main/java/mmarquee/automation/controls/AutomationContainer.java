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

package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.condition.ControlIdCondition;
import mmarquee.automation.controls.rebar.AutomationReBar;
import mmarquee.automation.controls.ribbon.AutomationRibbonBar;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.List;

/**
 * Created by inpwt on 28/01/2016.
 *
 * Encapsulates the functionality of 'containers' i.e. elemenst that
 * host other elements
 */
public class AutomationContainer extends AutomationBase {

    /**
     * Constructor for AutomationContainer
     *
     * @param element The underlying element
     * @param automation  The automation bit
     */
    public AutomationContainer(AutomationElement element, IUIAutomation automation) {
        super(element, automation);
    }

    AutomationElement getControlByControlType(int index, int id) {
        ControlIdCondition condition = new ControlIdCondition(this.automation, id);

        List<AutomationElement> collection = this.findAll(TreeScope.TreeScope_Descendants, condition);

        return collection.get(index);
    }

    /**
     * Gets the control by the control type, for s given control index
     * @param index Index of the control
     * @param id Control type
     * @param controlName The control name to use
     * @return The matching element
     */
    protected AutomationElement getControlByControlType(int index, int id, String controlName) {
        List<AutomationElement> collection;

        AutomationElement foundElement = null;

        collection = this.findAll(TreeScope.TreeScope_Descendants);

        int counter = 0;

        for (AutomationElement element : collection) {
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
     * Gets the control by the control type, for s given control index.
     *
     * There is probably a much better way of doing this
     *
     * @param name Name of the control
     * @param id Control type
     * @param controlName The control name to use
     * @return The matching element
     */
    protected AutomationElement getControlByControlType(String name, int id, String controlName) throws ElementNotFoundException {
        List<AutomationElement> collection;

        AutomationElement foundElement = null;

        collection = this.findAll(TreeScope.TreeScope_Descendants);

        int length = collection.size();

        for (AutomationElement element : collection) {
            int retVal = element.currentControlType();
            String className = element.currentClassName();

            if (retVal == id) {
                if (className.equals(controlName)) {
                    String cName = element.currentName();

                    if (cName.equals(name)) {
                        foundElement = element;
                        break;
                    }
                }
            }
        }

        if (foundElement == null) {
            throw new ElementNotFoundException();
        }

        return foundElement;
    }

    /**
     * Gets the control by the control type
     * @param name Name to use
     * @param id Control type
     * @return The matching element
     */
    protected AutomationElement getControlByControlType(String name, int id) throws ElementNotFoundException {
        return this.findFirst(TreeScope.TreeScope_Descendants,
                this.createAndCondition(
                        this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(id)));
    }

    /**
     * Gets the control by the control type and automation ID
     * @param automationId Name to use
     * @param controlType Control type
     * @return The matching element
     */
    protected AutomationElement getControlByAutomationId(String automationId, int controlType) throws ElementNotFoundException {
        return this.findFirst(TreeScope.TreeScope_Descendants,
                this.createAndCondition(
                        this.createAutomationIdPropertyCondition(automationId),
                        this.createControlTypeCondition(controlType)));
    }

    /**
     * Gets the checkbox associated with the given index
     * @param index Index of the control
     * @return AutomationCheckbox that represents the found control
     */
    public AutomationCheckbox getCheckbox(int index) {
        return new AutomationCheckbox(this.getControlByControlType(index, ControlType.CheckBox), this.automation);
    }

    /**
     * Gets the Tab control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationTab getTab(int index) {
        return new AutomationTab(this.getControlByControlType(index, ControlType.Tab), this.automation);
    }

    /**
     * Gets the Editbox control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationEditBox getEditBox(int index) {
        return new AutomationEditBox(this.getControlByControlType(index, ControlType.Edit), this.automation);
    }

    public AutomationEditBox getPasswordEditBox(int index) {
        return new AutomationEditBox(this.getControlByControlType(index, ControlType.Edit, "PasswordBox"), this.automation);
    }

    /**
     * Gets the ProgressBar control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationProgressBar getProgressBar(int index) {
        return new AutomationProgressBar(this.getControlByControlType(index, ControlType.ProgressBar), this.automation);
    }

    /**
     * Gets the ProgressBar control associated with the given index
     * @param name Name of the control
     * @return The found control
     */
    public AutomationProgressBar getProgressBar(String name) throws ElementNotFoundException {
        return new AutomationProgressBar(this.getControlByControlType(name, ControlType.ProgressBar), this.automation);
    }

    /**
     * Gets the Editbox control associated with the given name
     * @param name Name of the control
     * @return The found control
     */
    public AutomationEditBox getEditBox(String name) throws ElementNotFoundException {
        return new AutomationEditBox(this.getControlByControlType(name, ControlType.Edit), this.automation);
    }

    /**
     * Gets the slider control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationSlider getSlider(int index) {
        return new AutomationSlider(this.getControlByControlType(index, ControlType.Slider), this.automation);
    }

    /**
     * Gets the slider control associated with the given index
     * @param name Name of the control
     * @return The found control
     */
    public AutomationSlider getSlider(String name) throws ElementNotFoundException {
        return new AutomationSlider(this.getControlByControlType(name, ControlType.Slider), this.automation);
    }

    /**
     * Gets the (JHC) Masked Edit control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationMaskedEdit getMaskedEdit(int index) {
        return new AutomationMaskedEdit(this.getControlByControlType(index, ControlType.Edit, "TAutomationMaskEdit"), this.automation);
    }

    /**
     * Gets the (JHC) Masked Edit control associated with the given index
     * @param name Name of the control
     * @return The found control
     */
    public AutomationMaskedEdit getMaskedEdit(String name) throws ElementNotFoundException {
        return new AutomationMaskedEdit(this.getControlByControlType(name, ControlType.Edit, "TAutomatedMaskEdit"), this.automation);
    }

    /**
     * Gets the radio button control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationRadioButton getRadioButton(int index) {
        return new AutomationRadioButton(this.getControlByControlType(index, ControlType.RadioButton), this.automation);
    }

    /**
     * Gets the text box control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationTextBox getTextBox(int index) {
        return new AutomationTextBox(this.getControlByControlType(index, ControlType.Text), this.automation);
    }

    /**
     * Gets the combobox control associated with the given name
     * @param index Index of the control
     * @return The found control
     */
    public AutomationComboBox getCombobox(int index) throws ElementNotFoundException {
        return new AutomationComboBox(this.getControlByControlType(index, ControlType.ComboBox), this.automation);
    }

    /**
     * Gets the combobox control associated with the given name
     * @param name Name of the control
     * @return The found control
     */
    public AutomationComboBox getCombobox(String name) throws ElementNotFoundException {
        return new AutomationComboBox(this.getControlByControlType(name, ControlType.ComboBox), this.automation);
    }

    /**
     * Gets the button control associated with the given name
     * @param name Name of the control
     * @return The found control
     */
    public AutomationButton getButton(String name) throws ElementNotFoundException {
        return new AutomationButton(this.getControlByControlType(name, ControlType.Button), this.automation);
    }

    public AutomationButton getButtonByAutomationId(String id)  throws ElementNotFoundException {
        return new AutomationButton(this.getControlByAutomationId(id, ControlType.Button), this.automation);
    }


    /**
     * Gets the button control associated with the given index
     * @param index The index of the button
     * @return The AutomationButton
     */
    public AutomationButton getButton(int index) {
        return new AutomationButton(this.getControlByControlType(index, ControlType.Button), this.automation);
    }

    /**
     * Gets the  String Grid control associated with the given index, with a specific control name
     * @param index Index of the control
     * @param controlName*
     * @return The found control
     */
    public AutomationDataGrid getDataGrid(int index, String controlName) {
        return new AutomationDataGrid(this.getControlByControlType(index, ControlType.DataGrid, controlName), this.automation);
    }

    /**
     * Gets the String Grid control associated with the given index
     * @param index The index to look for
     * @return The string grid
     */
    public AutomationDataGrid getDataGrid(int index) {
        return new AutomationDataGrid(this.getControlByControlType(index, ControlType.DataGrid), this.automation);
    }

    /**
     * Gets the document associated with the given index
     * @param index Index of the control
     * @return The document control
     */
    public AutomationDocument getDocument(int index) {
        return new AutomationDocument(this.getControlByControlType(index, ControlType.Document), this.automation);
    }

    /**
     * Gets the hyperlink control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationHyperlink getHyperlink(int index) {
        return new AutomationHyperlink(this.getControlByControlType(index, ControlType.Hyperlink), this.automation);
    }

    /**
     * Gets the treeview control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationTreeView getTreeView(int index) {
        return new AutomationTreeView(this.getControlByControlType(index, ControlType.Tree), this.automation);
    }

    /**
     * Gets the treeview control associated with the given name
     * @param name Name of the control
     * @return The found control
     */
    public AutomationTreeView getTreeView(String name) throws ElementNotFoundException {
        return new AutomationTreeView(this.getControlByControlType(name, ControlType.Tree), this.automation);
    }

    /**
     * Gets the list control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationList getListItem(int index) {
        return new AutomationList(this.getControlByControlType(index, ControlType.List), this.automation);
    }

    /**
     * Gets the calendar control associated with the given index
     * @param index Index of the control
     * @return The found control
     */
    public AutomationCalendar getCalendar(int index) {
        return new AutomationCalendar(this.getControlByControlType(index, ControlType.Calendar), this.automation);
    }

    /**
     * Gets the panel control associated with the given index
     * @param index Index of the control
     */
    public AutomationPanel getPanel(int index) {
        return new AutomationPanel(this.getControlByControlType(index, ControlType.Pane), this.automation);
    }

    /**
     * Gets the panel control associated with the given name
     * @param name Name of the control
     */
    public AutomationPanel getPanel(String name) throws ElementNotFoundException {
        return new AutomationPanel(this.getControlByControlType(name, ControlType.Pane), this.automation);
    }

    /**
     * Get the AppBar associated with the given index
     * @param index The index
     * @return The AutomationAppBar
     */
    public AutomationAppBar getAppBar(int index) {
        return new AutomationAppBar(this.getControlByControlType(index, ControlType.AppBar), this.automation);
    }

    /**
     * Get the ToolBar associated with the given name
     * @param name The name
     * @return The AutomationToolBar
     */
    public AutomationToolBar getToolBar(String name) throws ElementNotFoundException {
        return new AutomationToolBar(this.getControlByControlType(name, ControlType.ToolBar), this.automation);
    }

    /**
     * Get the ToolBar associated with the given index
     * @param index The index
     * @return The AutomationToolBar
     */
    public AutomationToolBar getToolBar(int index) {
        return new AutomationToolBar(this.getControlByControlType(index, ControlType.ToolBar), this.automation);
    }

    /**
     * Get the RibbonBar associated this container
     * @return The AutomationRibbonBar
     */
    public AutomationRibbonBar getRibbonBar() {
        return new AutomationRibbonBar(this.getControlByControlType(0, ControlType.Pane, "UIRibbonCommandBarDock"), this.automation);
    }

    /**
     * Gets the AutomationReBar associated with this index
     * @param index The index
     * @return The control wrapper
     */
    public AutomationReBar getReBar(int index) {
        return new AutomationReBar(this.getControlByControlType(index, ControlType.Pane, "ReBarWindow32"), this.automation);
    }

    /**
     * Get the AutomationSplitButton associated with the given name
     * @param name The name to look for
     * @return The AutomationSplitButton
     */
    public AutomationSplitButton getSplitButton(String name) throws ElementNotFoundException {
        return new AutomationSplitButton(this.getControlByControlType(name, ControlType.SplitButton), this.automation);
    }

    /**
     * Get the AutomationImage associated with the given name
     * @param name The name to look for
     * @return The AutomationImage
     */
    public AutomationImage getImage(String name) throws ElementNotFoundException {
        return new AutomationImage(this.getControlByControlType(name, ControlType.Image), this.automation);
    }

    /**
     * Get the AutomationSpinner element associated with the given name
     * @param name The name to look for
     * @return The AutomationCustom
     */
    public AutomationSpinner getSpinner(String name) throws ElementNotFoundException {
        return new AutomationSpinner(this.getControlByControlType(name, ControlType.Spinner), this.automation);
    }

    /**
     * Get the AutomationCustom element associated with the given name
     * @param name The name to look for
     * @return The AutomationCustom
     */
    public AutomationCustom getCustom(String name) throws ElementNotFoundException {
        return new AutomationCustom(this.getControlByControlType(name, ControlType.Custom), this.automation);
    }

    /**
     * Dumps the control tree, used for exploring interfaces
     */
    public void dumpUI() {
        logger.info("About to start dumping");

        List<AutomationElement> collection = this.findAll(TreeScope.TreeScope_Descendants);

        for (AutomationElement element : collection) {
            String cName = element.currentName();

            logger.info(".." + cName);
        }

        logger.info("All done dumping");
    }
}