/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
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

import java.util.List;

import com.sun.jna.platform.win32.Variant;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.PropertyID;
import mmarquee.automation.controls.rebar.AutomationReBar;
import mmarquee.automation.controls.ribbon.AutomationRibbonBar;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * Created by Mark Humphreys on 28/01/2016.
 *
 * Encapsulates the functionality of 'containers' i.e. elements that
 * host other elements
 */
public class AutomationContainer extends AutomationBase {

    private ItemContainer itemContainerPattern;

    /**
     * Constructor for AutomationContainer
     *
     * @param element The underlying element
     * @throws AutomationException Something is wrong in automation
     * @throws PatternNotFoundException Could not find pattern
     */
    public AutomationContainer(AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);
       // itemContainerPattern = this.getItemContainerPattern();
    }

    /**
     * Constructor for AutomationContainer
     *
     * @param element The underlying element
     * @param pattern The Container pattern
     * @throws AutomationException Something is wrong in automation
     * @throws PatternNotFoundException Could not find pattern
     */
    public AutomationContainer(AutomationElement element, ItemContainer pattern) throws PatternNotFoundException, AutomationException {
        super(element);
        itemContainerPattern = pattern;
    }

    /**
     * Gets a control by control type
     * @param index The nth item that matches
     * @param id The control type
     * @return The matching element
     * @throws AutomationException Error in the Automation library
     */
    AutomationElement getControlByControlType(int index, ControlType id) throws AutomationException {
        PointerByReference condition =  this.automation.createPropertyCondition(PropertyID.ControlType.getValue(),
                this.createIntegerVariant(id.getValue()));

        List<AutomationElement> collection = this.findAll(
                new TreeScope(TreeScope.Subtree), condition.getValue());

        return collection.get(index);
    }

    /**
     * Gets the control by the control type, for s given control index
     * 
     * @param index Index of the control
     * @param id Control type
     * @param className The className to look for
     * @return The matching element
     * @throws AutomationException Automation issue
     * @throws ElementNotFoundException Failed to find element
     */
    protected AutomationElement getControlByControlType(int index, ControlType id, String className) throws AutomationException, ElementNotFoundException {
        PointerByReference condition =  this.automation.createPropertyCondition(PropertyID.ControlType.getValue(),
                this.createIntegerVariant(id.getValue()));
        
        List<AutomationElement> collection;

        AutomationElement foundElement = null;

        collection = this.findAll(new TreeScope(TreeScope.Descendants), condition.getValue());

        int counter = 0;

        for (AutomationElement element : collection) {
            String cName = element.getClassName();

            if (cName.equals(className)) {
                if (counter == index) {
                    foundElement = element;
                    break;
                } else {
                    counter++;
                }
            }
        }

        if (foundElement == null) {
            throw new ElementNotFoundException(className);
        }

        return foundElement;
    }

    /**
     * Gets the control by the control type
     * @param name Name to use
     * @param id Control type
     * @return The matching element
     * @throws ElementNotFoundException Did not find the element
     */
    protected AutomationElement getControlByControlType(String name, ControlType id) throws AutomationException {
        return this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createNamePropertyCondition(name).getValue(),
                        this.createControlTypeCondition(id).getValue()));
    }

    /**
     * Gets the control by the control type, for s given control index.
     *
     * There is probably a much better way of doing this
     *
     * @param name Name of the control
     * @param id Control type
     * @param className The className to look for
     * @return The matching element
     * @throws AutomationException Did not find the element
     */
    protected AutomationElement getControlByControlType(String name, ControlType id, String className) throws AutomationException {
        List<AutomationElement> collection;

        AutomationElement foundElement = null;

        collection = this.findAll(new TreeScope(TreeScope.Descendants),
        		this.createAndCondition(
                        this.createNamePropertyCondition(name).getValue(),
                        this.createControlTypeCondition(id).getValue()).getValue());

        for (AutomationElement element : collection) {
            String cName = element.getClassName();

            if (cName.equals(className)) {
                String elementName = element.getName();

                if (elementName.equals(name)) {
                    foundElement = element;
                    break;
                }
            }
        }

        if (foundElement == null) {
            throw new ElementNotFoundException(className);
        }

        return foundElement;
    }

    /**
     * Gets the control by the control type and automation ID
     * @param automationId Name to use
     * @param controlType Control type
     * @return The matching element
     * @throws AutomationException An error has occurred in automation
     */
    protected AutomationElement getControlByAutomationId(String automationId, ControlType controlType) throws AutomationException {
        return this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createAutomationIdPropertyCondition(automationId).getValue(),
                        this.createControlTypeCondition(controlType).getValue()));
    }


    /**
     * Creates an integer variant
     * @param value The value to set
     * @return ByValue variant
     */
    Variant.VARIANT.ByValue createIntegerVariant(int value) {
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        variant.setValue(Variant.VT_INT, value);

        return variant;
    }

    ////////////////// Public API //////////////////
    
    
    /**
     * Gets the Checkbox associated with the given index
     * @param index Index of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationCheckbox getCheckbox(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationCheckbox(this.getControlByControlType(index, ControlType.CheckBox));
    }

    /**
     * Gets the Checkbox associated with the given name
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationCheckbox getCheckbox(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationCheckbox(this.getControlByControlType(name, ControlType.CheckBox));
    }
    
    /**
     * Gets the Checkbox control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
      */
    public AutomationCheckbox getCheckboxByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationCheckbox(this.getControlByAutomationId(id, ControlType.CheckBox));
    }
    

    /**
     * Gets the Tab control associated with the given index
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationTab getTab(int index) throws PatternNotFoundException, AutomationException {
        AutomationElement tab = this.getControlByControlType(index, ControlType.Tab);

        return new AutomationTab(tab);
    }

    /**
     * Gets the Tab associated with the given name
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationTab getTab(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationTab(this.getControlByControlType(name, ControlType.Tab));
    }
    
    /**
     * Gets the Tab control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationTab getTabByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationTab(this.getControlByAutomationId(id, ControlType.Tab));
    }

    
    /**
     * Gets the Editbox control associated with the given index
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationEditBox getEditBox(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationEditBox(this.getControlByControlType(index, ControlType.Edit));
    }
    
    /**
     * Gets the Editbox control associated with the given name
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationEditBox getEditBox(String name) throws PatternNotFoundException, AutomationException {
    	return new AutomationEditBox(this.getControlByControlType(name, ControlType.Edit));
    }
    
    /**
     * Gets the Editbox control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationEditBox getEditBoxByAutomationId(String id) throws PatternNotFoundException, AutomationException {
    	return new AutomationEditBox(this.getControlByAutomationId(id, ControlType.Edit));
    }
    
    
    /**
     * Gets the EditBox (with password marking) associated with the given index
     * @param index The index
     * @return The found control
     * @throws AutomationException Automation issue
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationPasswordEditBox getPasswordEditBox(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationPasswordEditBox(this.getControlByControlType(index, ControlType.Edit, AutomationPasswordEditBox.CLASS_NAME));
    }

    /**
     * Gets the EditBox (with password marking) associated with the given name
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationPasswordEditBox getPasswordEditBox(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationPasswordEditBox(this.getControlByControlType(name, ControlType.Edit, AutomationPasswordEditBox.CLASS_NAME));
    }
    
    /**
     * Gets the Editbox (with password marking) control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationPasswordEditBox getPasswordEditBoxByAutomationId(String id) throws PatternNotFoundException, AutomationException {
    	return new AutomationPasswordEditBox(this.getControlByAutomationId(id, ControlType.Edit));
    }
    

    /**
     * Gets the ProgressBar control associated with the given index
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationProgressBar getProgressBar(int index) throws AutomationException, PatternNotFoundException {
        return new AutomationProgressBar(this.getControlByControlType(index, ControlType.ProgressBar));
    }

    /**
     * Gets the ProgressBar control associated with the given name
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationProgressBar getProgressBar(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationProgressBar(this.getControlByControlType(name, ControlType.ProgressBar));
    }
    
    /**
     * Gets the ProgressBar control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationProgressBar getProgressBarByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationProgressBar(this.getControlByAutomationId(id, ControlType.ProgressBar));
    }
    

    /**
     * Gets the slider control associated with the given index
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong in automation
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationSlider getSlider(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationSlider(this.getControlByControlType(index, ControlType.Slider));
    }

    /**
     * Gets the slider control associated with the given index
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationSlider getSlider(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationSlider(this.getControlByControlType(name, ControlType.Slider));
    }
    
    /**
     * Gets the slider control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationSlider getSliderByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationSlider(this.getControlByAutomationId(id, ControlType.Slider));
    }
    

    /**
     * Gets the (JHC) Masked Edit control associated with the given index
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Automation issue
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMaskedEdit getMaskedEdit(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationMaskedEdit(this.getControlByControlType(index, ControlType.Edit, AutomationMaskedEdit.CLASS_NAME));
    }

    /**
     * Gets the (JHC) Masked Edit control associated with the given index
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMaskedEdit getMaskedEdit(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationMaskedEdit(this.getControlByControlType(name, ControlType.Edit, AutomationMaskedEdit.CLASS_NAME));
    }
    
    /**
     * Gets the (JHC) Masked Edit control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationMaskedEdit getMaskedEditByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationMaskedEdit(this.getControlByAutomationId(id, ControlType.Edit));
    }
    

    /**
     * Gets the radio button control associated with the given index
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationRadioButton getRadioButton(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationRadioButton(this.getControlByControlType(index, ControlType.RadioButton));
    }

    /**
     * Gets the radio button associated with the given name
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationRadioButton getRadioButton(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationRadioButton(this.getControlByControlType(name, ControlType.RadioButton));
    }
    
    /**
     * Gets the radio button control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationRadioButton getRadioButtonByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationRadioButton(this.getControlByAutomationId(id, ControlType.RadioButton));
    }

    
    /**
     * Gets the text box control associated with the given index
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTextBox getTextBox(int index) throws AutomationException {
        return new AutomationTextBox(this.getControlByControlType(index, ControlType.Text));
    }

    /**
     * Gets the text box control associated with the given index
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTextBox getTextBox(String name) throws AutomationException {
        return new AutomationTextBox(this.getControlByControlType(name, ControlType.Text));
    }

    /**
     * Gets the text box control associated with the given automation id
     * @param id Automation id of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTextBox getTextBoxByAutomationId(String id) throws AutomationException {
        return new AutomationTextBox(this.getControlByAutomationId(id, ControlType.Text));
    }

    
    /**
     * Gets the combobox control associated with the given index
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationComboBox getCombobox(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationComboBox(this.getControlByControlType(index, ControlType.ComboBox));
    }

    /**
     * Gets the combobox control associated with the given name
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationComboBox getCombobox(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationComboBox(this.getControlByControlType(name, ControlType.ComboBox));
    }

    /**
     * Gets the combobox control associated with the given automation id
     * @param id Automation id of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationComboBox getComboboxByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationComboBox(this.getControlByAutomationId(id, ControlType.ComboBox));
    }

    
    /**
     * Gets the button control associated with the given index
     * @param index The index of the button
     * @return The AutomationButton
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationButton getButton(int index) throws PatternNotFoundException, AutomationException {
    	return new AutomationButton(this.getControlByControlType(index, ControlType.Button));
    }
    
    /**
     * Gets the button control associated with the given name
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationButton getButton(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationButton(this.getControlByControlType(name, ControlType.Button));
    }

    /**
     * Gets the button using the automation ID
     * @param id The automation id
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationButton getButtonByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationButton(this.getControlByAutomationId(id, ControlType.Button));
    }

    
    /**
     * Gets the String Grid control associated with the given index
     * @param index The index to look for
     * @return The string grid
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDataGrid getDataGrid(int index) throws PatternNotFoundException, AutomationException {
    	return new AutomationDataGrid(this.getControlByControlType(index, ControlType.DataGrid));
    }

    /**
     * Gets the String Grid control associated with the given name
     * @param name The name to look for
     * @return The string grid
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDataGrid getDataGrid(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationDataGrid(this.getControlByControlType(name, ControlType.DataGrid));
    }

    /**
     * Gets the String Grid control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDataGrid getDataGridByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationDataGrid(this.getControlByAutomationId(id, ControlType.DataGrid));
    }
    
    /**
     * Gets the  String Grid control associated with the given index, with a specific control name
     * @param index Index of the control
     * @param controlName Control Type name
     * @return The found control
     * @throws AutomationException Automation issue
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDataGrid getDataGrid(int index, String controlName) throws PatternNotFoundException, AutomationException {
    	return new AutomationDataGrid(this.getControlByControlType(index, ControlType.DataGrid, controlName));
    }
    
    /**
     * Gets the String Grid control associated with the given name, with a specific control name
     * @param name Name of the control
     * @param controlName Control Type name
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDataGrid getDataGrid(String name, String controlName) throws PatternNotFoundException, AutomationException {
    	return new AutomationDataGrid(this.getControlByControlType(name, ControlType.DataGrid, controlName));
    }

    
    /**
     * Gets the document associated with the given index
     * @param index Index of the control
     * @return The document control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDocument getDocument(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationDocument(this.getControlByControlType(index, ControlType.Document));
    }

    /**
     * Gets the document associated with the given name
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDocument getDocument(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationDocument(this.getControlByControlType(name, ControlType.Document));
    }
    
    /**
     * Gets the document associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDocument getDocumentByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationDocument(this.getControlByAutomationId(id, ControlType.Document));
    }
    

    /**
     * Gets the hyperlink control associated with the given index
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationHyperlink getHyperlink(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationHyperlink(this.getControlByControlType(index, ControlType.Hyperlink));
    }

    /**
     * Gets the hyperlink control associated with the given name
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationHyperlink getHyperlink(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationHyperlink(this.getControlByControlType(name, ControlType.Hyperlink));
    }
    
    /**
     * Gets the hyperlink control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationHyperlink getHyperlinkByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationHyperlink(this.getControlByAutomationId(id, ControlType.Hyperlink));
    }

    
    /**
     * Gets the treeview control associated with the given index
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTreeView getTreeView(int index) throws AutomationException {
        return new AutomationTreeView(this.getControlByControlType(index, ControlType.Tree));
    }

    /**
     * Gets the treeview control associated with the given name
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTreeView getTreeView(String name) throws AutomationException {
        return new AutomationTreeView(this.getControlByControlType(name, ControlType.Tree));
    }
    
    /**
     * Gets the treeview control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationTreeView getTreeViewByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationTreeView(this.getControlByAutomationId(id, ControlType.Tree));
    }

    
    /**
     * Deprecated. use {@link #getList(int)} instead.
     */
    @Deprecated
    public AutomationList getListItem(int index) throws PatternNotFoundException, AutomationException {
    	return getList(index);
    }

    /**
     * Gets the list control associated with the given index
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationList getList(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationList(this.getControlByControlType(index, ControlType.List));
    }

    /**
     * Gets the list control associated with the given name
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationList getList(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationList(this.getControlByControlType(name, ControlType.List));
    }

    /**
     * Gets the list control associated with the given automation ID
     * @param automationId Automation id of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationList getListByAutomationId(String automationId) throws PatternNotFoundException, AutomationException {
        return new AutomationList(this.getControlByAutomationId(automationId, ControlType.List));
    }

    
    /**
     * Gets the calendar control associated with the given index
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationCalendar getCalendar(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationCalendar(this.getControlByControlType(index, ControlType.Calendar));
    }

    /**
     * Gets the calendar associated with the given name
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationCalendar getCalendar(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationCalendar(this.getControlByControlType(name, ControlType.Calendar));
    }

    /**
     * Gets the calendar control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationCalendar getCalendarByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationCalendar(this.getControlByAutomationId(id, ControlType.Calendar));
    }

   
    /**
     * Gets the panel control associated with the given index
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationPanel getPanel(int index) throws AutomationException, PatternNotFoundException {
        return new AutomationPanel(this.getControlByControlType(index, ControlType.Pane));
    }

    /**
     * Gets the panel control associated with the given name
     * @param name Name of the control
     * @return The found control
     * @throws ElementNotFoundException Did not find the element
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationPanel getPanel(String name) throws AutomationException, PatternNotFoundException {
        return new AutomationPanel(this.getControlByControlType(name, ControlType.Pane));
    }

    /**
     * Gets the panel control associated with the automation id
     * @param id Automaton id of the control
     * @return The found control
     * @throws ElementNotFoundException Did not find the element
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationPanel getPanelByAutomationId(String id) throws AutomationException, PatternNotFoundException {
        return new AutomationPanel(this.getControlByAutomationId(id, ControlType.Pane));
    }
    

    /**
     * Gets the panel control associated with the given index, with a specific class name
     * @param index Index of the control
     * @param className The specific classname
     * @return The found control
     * @throws ElementNotFoundException Did not find the element
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationPanel getPanelByClassName(int index, String className) throws AutomationException, PatternNotFoundException {
        return new AutomationPanel(this.getControlByControlType(index, ControlType.Pane, className));
    }


    /**
     * Gets the panel control associated with the given name, with a specific class name
     * @param name Name of the control
     * @param className The specific classname
     * @return the found control
     * @throws ElementNotFoundException Did not find the element
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationPanel getPanelByClassName(String name, String className) throws PatternNotFoundException, AutomationException {
        return new AutomationPanel(this.getControlByControlType(name, ControlType.Pane, className));
    }
    

    /**
     * Get the AppBar control associated with the given index
     * @param index The index
     * @return The AutomationAppBar
     * @throws AutomationException Something has gone wrong
     */
    public AutomationAppBar getAppBar(int index) throws AutomationException {
        return new AutomationAppBar(this.getControlByControlType(index, ControlType.AppBar));
    }

    /**
     * Gets the AppBar control associated with the given name
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationAppBar getAppBar(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationAppBar(this.getControlByControlType(name, ControlType.AppBar));
    }

    /**
     * Gets the AppBar control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationAppBar getAppBarByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationAppBar(this.getControlByAutomationId(id, ControlType.AppBar));
    }

    
    /**
     * Get the ToolBar control associated with the given index
     * @param index The index
     * @return The AutomationToolBar
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Could not find pattern
     */
    public AutomationToolBar getToolBar(int index) throws AutomationException, PatternNotFoundException {
        return new AutomationToolBar(this.getControlByControlType(index, ControlType.ToolBar));
    }

    /**
     * Get the ToolBar control associated with the given name
     * @param name The name
     * @return The AutomationToolBar
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Could not find pattern
     */
    public AutomationToolBar getToolBar(String name) throws AutomationException, PatternNotFoundException {
    	return new AutomationToolBar(this.getControlByControlType(name, ControlType.ToolBar));
    }
    
    /**
     * Gets the ToolBar control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationToolBar getToolBarByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationToolBar(this.getControlByAutomationId(id, ControlType.ToolBar));
    }

    
    /**
     * Get the RibbonBar control associated this container
     * @return The AutomationRibbonBar
     * @throws AutomationException Automation issue
     * @throws PatternNotFoundException Could not find pattern
     */
    public AutomationRibbonBar getRibbonBar() throws AutomationException, PatternNotFoundException {
        return new AutomationRibbonBar(this.getControlByControlType(0, ControlType.Pane, AutomationRibbonBar.CLASS_NAME));
    }

    
    /**
     * Gets the ReBar control associated with this index
     * @param index The index
     * @return The control wrapper
     * @throws AutomationException Automation issue
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationReBar getReBar(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationReBar(this.getControlByControlType(index, ControlType.Pane, AutomationReBar.CLASS_NAME));
    }

    /**
     * Gets the ReBar associated with the given name
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationReBar getReBar(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationReBar(this.getControlByControlType(name, ControlType.Pane, AutomationReBar.CLASS_NAME));
    }

    /**
     * Gets the ReBar control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationReBar getReBarByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationReBar(this.getControlByAutomationId(id, ControlType.Pane));
    }


    /**
     * Gets the SplitButton control associated with the given index
     * @param index Index of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationSplitButton getSplitButton(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationSplitButton(this.getControlByControlType(index, ControlType.SplitButton));
    }
    
    /**
     * Get the SplitButton control associated with the given name
     * @param name The name to look for
     * @return The AutomationSplitButton
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationSplitButton getSplitButton(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationSplitButton(this.getControlByControlType(name, ControlType.SplitButton));
    }
    
    /**
     * Gets the SplitButton control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationSplitButton getSplitButtonByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationSplitButton(this.getControlByAutomationId(id, ControlType.SplitButton));
    }
    
    
    /**
     * Get the Image control associated with the given index
     * @param index The index to look for
     * @return The AutomationImage
     * @throws AutomationException Something has gone wrong
     */
    public AutomationImage getImage(int index) throws AutomationException {
    	return new AutomationImage(this.getControlByControlType(index, ControlType.Image));
    }

    /**
     * Get the Image control associated with the given name
     * @param name The name to look for
     * @return The AutomationImage
     * @throws AutomationException Something has gone wrong
     */
    public AutomationImage getImage(String name) throws AutomationException {
        return new AutomationImage(this.getControlByControlType(name, ControlType.Image));
    }

    /**
     * Gets the Image control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationImage getImageByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationImage(this.getControlByAutomationId(id, ControlType.Image));
    }


    /**
     * Gets the Spinner control associated with the given index
     * @param index Index of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationSpinner getSpinner(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationSpinner(this.getControlByControlType(index, ControlType.Spinner));
    }

    /**
     * Get the Spinner control associated with the given name
     * @param name The name to look for
     * @return The AutomationSpinner control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSpinner getSpinner(String name) throws AutomationException {
        return new AutomationSpinner(this.getControlByControlType(name, ControlType.Spinner));
    }
    
    /**
     * Gets the Spinner control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationSpinner getSpinnerByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationSpinner(this.getControlByAutomationId(id, ControlType.Spinner));
    }
    

    /**
     * Gets the Custom control associated with the given index
     * @param index Index of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationCustom getCustom(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationCustom(this.getControlByControlType(index, ControlType.Custom));
    }
    
    /**
     * Get the Custom control associated with the given name
     * @param name The name to look for
     * @return The AutomationCustom
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationCustom getCustom(String name) throws PatternNotFoundException, AutomationException {
    	return new AutomationCustom(this.getControlByControlType(name, ControlType.Custom));
    }

    /**
     * Get the Custom control associated with the given automation id
     * @param id The id to look for
     * @return The AutomationCustom
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationCustom getCustomByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationCustom(this.getControlByAutomationId(id, ControlType.Custom));
    }

    /**
     * <p>
     * Get the Custom Control associated with the given control type
     * </p>
     * <p>
     * At the moment, just get the first first element.
     * </p>
     * @param className The control type to look for
     * @return The AutomationCustom
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationCustom getCustomByControlType(String className) throws PatternNotFoundException, AutomationException {
    	return getCustomByClassName(0, className);
    }

    /**
     * Gets the Custom control associated with this index
     * @param index The index
     * @return The control wrapper
     * @throws AutomationException Automation issue
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationCustom getCustomByClassName(int index, String className) throws PatternNotFoundException, AutomationException {
        return new AutomationCustom(this.getControlByControlType(index, ControlType.Custom, className));
    }

    /**
     * Gets the Custom associated with the given name
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationCustom getCustomByClassName(String name, String className) throws PatternNotFoundException, AutomationException {
        return new AutomationCustom(this.getControlByControlType(name, ControlType.Custom, className));
    }
    

    /**
     * Gets the PowerpointSlide control associated with the given index
     * @param index Index of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationPowerpointSlide getPowerpointSlide(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationPowerpointSlide(this.getControlByControlType(index, ControlType.Custom));
    }
    
    /**
     * Get the PowerpointSlide control associated with the given name
     * @param name The name to look for
     * @return The AutomationCustom
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationPowerpointSlide getPowerpointSlide(String name) throws PatternNotFoundException, AutomationException {
    	return new AutomationPowerpointSlide(this.getControlByControlType(name, ControlType.Custom));
    }

    /**
     * Gets the PowerpointSlide control associated with the given automation id
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationPowerpointSlide getPowerpointSlideByAutomationId(String id) throws PatternNotFoundException, AutomationException {
        return new AutomationPowerpointSlide(this.getControlByAutomationId(id, ControlType.Custom));
    }
    
    
    /////////////////// Heap /////////////////////////////7

    /**
     * Get a control, based on the class and the name
     *
     * Not currently used, as it only seems to work on one PC!
     *
     * @param type Class to return / check for
     * @param controlType The control type to look for
     * @param name Name to be looked for
     * @param <T> The Type of the class
     * @return Found element
     * @throws PatternNotFoundException Expected pattern not found
     * @throws AutomationException Raised from automation library
     */
/*
    public <T extends AutomationBase> T get1(Class<T> type, ControlType controlType, String name)
            throws PatternNotFoundException, AutomationException {

        Variant.VARIANT.ByValue variant1 = new Variant.VARIANT.ByValue();
        variant1.setValue(Variant.VT_INT, controlType.getValue());

        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString(name);
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        try {
            PointerByReference propertyCondition = this.automation.createPropertyCondition(PropertyID.ControlType.getValue(), variant1);

            PointerByReference nameCondition = this.automation.createPropertyCondition(PropertyID.Name.getValue(), variant);
            PointerByReference condition = this.automation.createAndCondition(nameCondition.getValue(), propertyCondition.getValue());

            AutomationElement elem = this.findFirst(new TreeScope(TreeScope.Descendants), condition);

            return type.cast(AutomationControlFactory.get(controlType, elem));

        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }
    }
*/
    
    /*
    static private class AutomationControlFactory {
        public static Automatable get(ControlType controlType, AutomationElement element)
                throws AutomationException, PatternNotFoundException {

            if (controlType == ControlType.None) {
                throw new AutomationException();
            } else if (controlType == ControlType.Button) {
                return new AutomationButton(element);
            } else if (controlType == ControlType.TitleBar) {
                return new AutomationTitleBar(element);
            }

            // TODO: Get more to work like this.

            return null;
        }
    }
    */
}