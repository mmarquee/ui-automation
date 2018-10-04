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
import java.util.regex.Pattern;

import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.PropertyID;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * Encapsulates the functionality of 'containers' i.e. elements that host other elements.
 *
 * @author Mark Humphreys
 * Date 28/01/2016.
 */
public class AutomationContainer extends AutomationBase {

    /**
     * Constructor for the AutomationContainer.
     *
     * @param builder The builder
     */
    public AutomationContainer(final ElementBuilder builder) {
        super(builder);
    }

    /**
     * Gets a element by control type.
     * @param index The nth item that matches.
     * @param id The control type.
     * @return The matching element.
     * @throws AutomationException Error in the Automation library.
     */
    AutomationElement getElementByControlType(final int index,
                                              final ControlType id)
            throws AutomationException {
        PointerByReference condition =
                this.getAutomation().createPropertyCondition(
                        PropertyID.ControlType.getValue(),
                        this.createIntegerVariant(id.getValue()));

        List<AutomationElement> collection = this.findAll(
                new TreeScope(TreeScope.Subtree), condition);

        return collection.get(index);
    }

    /**
     * Gets the element by the control type, for s given control index.
     *
     * @param index Index of the element.
     * @param id Control type.
     * @param className The className to look for.
     * @return The matching element.
     * @throws AutomationException Automation issue.
     * @throws ElementNotFoundException Failed to find element.
     */
    protected AutomationElement getElementByControlType(final int index,
                           final ControlType id,
                           final String className)
            throws AutomationException {
        PointerByReference condition =
        		this.createAndCondition(
        		        this.getAutomation().createPropertyCondition(
        		                PropertyID.ControlType.getValue(),
        				        this.createIntegerVariant(id.getValue())),
                        this.createClassNamePropertyCondition(className));

        List<AutomationElement> collection;

        try {
        	collection = this.findAll(new TreeScope(TreeScope.Descendants),
                    condition);
        	return collection.get(index);
        } catch (IndexOutOfBoundsException ex) {
        	throw new ElementNotFoundException(ex); // Backward compatibility
        }
    }

    /**
     * Gets the element by the control type for a given name.
     *
     * @param name Name to use.
     * @param id Control type.
     * @return The matching element.
     * @throws AutomationException Error from automation library
     */
    protected AutomationElement getElementByControlType(final String name,
                                                        final ControlType id)
            throws AutomationException {
        return this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(id)));
    }

    /**
     * Gets the element by the control type for a name matching the given
     * pattern.
     *
     * @param namePattern a pattern which matches the name
     * @param id Control type.
     * @return The matching element.
     * @throws AutomationException Error from automation library
     */
    protected AutomationElement getElementByControlType(
            final Pattern namePattern,
            final ControlType id)
            throws AutomationException {
    	List<AutomationElement> collection;

        AutomationElement foundElement = null;

        collection = this.findAll(new TreeScope(TreeScope.Descendants),
        		this.createControlTypeCondition(id));

        for (AutomationElement element : collection) {
            String name = element.getName();

            if (name != null && namePattern.matcher(name).matches()) {
                foundElement = element;
                break;
            }
        }

        if (foundElement == null) {
            throw new ElementNotFoundException("matching " + namePattern);
        }

        return foundElement;
    }

    /**
     * Gets the element by the control type, for a given control name and
     * class name.
     *
     * @param name Name of the element
     * @param id Control type
     * @param className The className to look for
     * @return The matching element
     * @throws AutomationException Did not find the element
     */
    protected AutomationElement getElementByControlType(final String name,
                                                        final ControlType id,
                                                        final String className)
            throws AutomationException {
        return this.findFirst(new TreeScope(TreeScope.Descendants),
        		this.createAndCondition(
        				this.createAndCondition(
	                        this.createNamePropertyCondition(name),
	                        this.createControlTypeCondition(id)),
        				this.createClassNamePropertyCondition(className)));
    }

    /**
     * Gets the element by the control type, for a name matching the given
     * pattern and a given control name.
     *
     * @param namePattern a pattern which matches the name
     * @param id Control type
     * @param className The className to look for
     * @return The matching element
     * @throws AutomationException Did not find the element
     */
    protected AutomationElement getElementByControlType(
            final Pattern namePattern,
            final ControlType id,
            final String className)
            throws AutomationException {
        List<AutomationElement> collection;

        AutomationElement foundElement = null;

        collection = this.findAll(new TreeScope(TreeScope.Descendants),
        		this.createAndCondition(
        				this.createControlTypeCondition(id),
        				this.createClassNamePropertyCondition(className)));

        for (AutomationElement element : collection) {
            String name = element.getName();

            if (name != null && namePattern.matcher(name).matches()) {
                foundElement = element;
                break;
            }
        }

        if (foundElement == null) {
            throw new ElementNotFoundException("matching " + namePattern);
        }

        return foundElement;
    }

    /**
     * Gets an element by the name.
     *
     * @param name Name of the element
     * @return The matching element
     * @throws AutomationException Did not find the element
     */
    protected AutomationElement getElementByName(final String name)
            throws AutomationException {
        return this.findFirst(new TreeScope(TreeScope.Descendants),
        		this.createNamePropertyCondition(name));
    }

    /**
     * Gets an element by matching the name.
     *
     * @param namePattern a pattern which matches the name
     * @return The matching element
     * @throws AutomationException Did not find the element
     */
    protected AutomationElement getElementByName(final Pattern namePattern)
            throws AutomationException {
    	List<AutomationElement> collection;

        AutomationElement foundElement = null;

        collection = this.findAll(new TreeScope(TreeScope.Descendants),
        		this.createTrueCondition());

        for (AutomationElement element : collection) {
            String name = element.getName();

            if (name != null && namePattern.matcher(name).matches()) {
                foundElement = element;
                break;
            }
        }

        if (foundElement == null) {
            throw new ElementNotFoundException("matching " + namePattern);
        }

        return foundElement;
    }

    /**
     * Gets an element by the name and className.
     *
     * @param name Name of the element
     * @param className The className to look for
     * @return The matching element
     * @throws AutomationException Did not find the element
     */
    protected AutomationElement getElementByName(
            final String name,
            final String className)
            throws AutomationException {
        return this.findFirst(new TreeScope(TreeScope.Descendants),
        		this.createAndCondition(
        				this.createNamePropertyCondition(name),
        				this.createClassNamePropertyCondition(className)));
    }

    /**
     * Gets an element by matching the name and by className.
     *
     * @param namePattern a pattern which matches the name
     * @param className The className to look for
     * @return The matching element
     * @throws AutomationException Did not find the element
     */
    protected AutomationElement getElementByName(
            final Pattern namePattern,
            final String className)
            throws AutomationException {
    	List<AutomationElement> collection;

        AutomationElement foundElement = null;

        collection = this.findAll(new TreeScope(TreeScope.Descendants),
        		this.createClassNamePropertyCondition(className));

        for (AutomationElement element : collection) {
            String name = element.getName();

            if (name != null && namePattern.matcher(name).matches()) {
                foundElement = element;
                break;
            }
        }

        if (foundElement == null) {
            throw new ElementNotFoundException("matching "
                    + namePattern
                    + " and className "
                    + className);
        }

        return foundElement;
    }

    /**
     * Gets the element by the given control index.
     *
     * @param index Index of the element
     * @param className The className to look for
     * @return The matching element
     * @throws AutomationException Automation issue
     * @throws ElementNotFoundException Failed to find element
     */
    protected AutomationElement getElementByIndex(
            final int index,
            final String className)
            throws AutomationException {
        List<AutomationElement> collection;

        collection = this.findAll(new TreeScope(TreeScope.Descendants),
                this.createClassNamePropertyCondition(className));

        try {
        	return collection.get(index);
        } catch (IndexOutOfBoundsException ex) {
            // backward compatibility
        	throw new ElementNotFoundException(ex);
        }
    }

    /**
     * Gets the control by the control type and automation ID.
     *
     * @param automationId Name to use
     * @param controlType Control type
     * @return The matching element
     * @throws AutomationException An error has occurred in automation
     */
    protected AutomationElement getElementByAutomationId(
            final String automationId,
            final ControlType controlType)
            throws AutomationException {
        return this.findFirst(new TreeScope(TreeScope.Descendants),
                this.createAndCondition(
                        this.createAutomationIdPropertyCondition(automationId),
                        this.createControlTypeCondition(controlType)));
    }

    /**
     * Gets the control by the automation ID.
     * @param automationId Name to use
     * @return The matching element
     * @throws AutomationException An error has occurred in automation
     */
    protected AutomationElement getElementByAutomationId(
            final String automationId)
            throws AutomationException {
        return this.findFirst(
                new TreeScope(TreeScope.Descendants),
                this.createAutomationIdPropertyCondition(automationId));
    }

    /**
     * Creates an integer variant.
     * @param value The value to set
     * @return ByValue variant
     */
    Variant.VARIANT.ByValue createIntegerVariant(final int value) {
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        variant.setValue(Variant.VT_INT, value);

        return variant;
    }

    ////////////////// 'old-style' API ////////////////

    /**
     * Gets the String Grid control associated with the given index.
     * @param index The index to look for
     * @return The string grid
     * @throws AutomationException Something has gone wrong
     */
    public AutomationDataGrid getDataGrid(final int index)
            throws AutomationException {
        return new AutomationDataGrid(
                new ElementBuilder(this.getElementByControlType(index,
                        ControlType.DataGrid)));
    }

    /**
     * Gets the String Grid control associated with the given name.
     * @param name The name to look for
     * @return The string grid
     * @throws AutomationException Something has gone wrong
     */
    public AutomationDataGrid getDataGrid(final String name)
            throws AutomationException {
        return new AutomationDataGrid(
                new ElementBuilder(this.getElementByControlType(name,
                        ControlType.DataGrid)));
    }

    /**
     * Gets the String Grid control matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return The string grid
     * @throws AutomationException Something has gone wrong
     */
    public AutomationDataGrid getDataGrid(final Pattern namePattern)
            throws AutomationException {
        return new AutomationDataGrid(
                new ElementBuilder(this.getElementByControlType(namePattern,
                        ControlType.DataGrid)));
    }

    /**
     * Gets the String Grid control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationDataGrid getDataGridByAutomationId(final String id)
            throws AutomationException {
        return new AutomationDataGrid(
                new ElementBuilder(this.getElementByAutomationId(id,
                        ControlType.DataGrid)));
    }

    /**
     * Gets the  String Grid control associated with the given index, with a
     * specific control name.
     * @param index Index of the control
     * @param controlName Control Type name
     * @return The found control
     * @throws AutomationException Automation issue
     */
    public AutomationDataGrid getDataGrid(final int index,
                                          final String controlName)
            throws AutomationException {
        return new AutomationDataGrid(
                new ElementBuilder(this.getElementByControlType(index,
                        ControlType.DataGrid, controlName)));
    }

    /**
     * Gets the String Grid control associated with the given name, with a
     * specific control name.
     *
     * @param name Name of the control
     * @param controlName Control Type name
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationDataGrid getDataGrid(final String name,
                                          final String controlName)
            throws AutomationException {
        return new AutomationDataGrid(
                new ElementBuilder(this.getElementByControlType(name,
                        ControlType.DataGrid, controlName)));
    }

    /**
     * Gets the String Grid control matching the given namePattern, with a
     * specific control name.
     * @param namePattern Matcher for the control name
     * @param controlName Control Type name
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationDataGrid getDataGrid(final Pattern namePattern,
                                          final String controlName)
            throws AutomationException {
        return new AutomationDataGrid(
                new ElementBuilder(this.getElementByControlType(namePattern,
                        ControlType.DataGrid, controlName)));
    }

    /**
     * Gets the CheckBox associated with the given index.
     * @param index Index of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationCheckBox getCheckBox(final int index)
            throws AutomationException {
        return new AutomationCheckBox(
                new ElementBuilder(this.getElementByControlType(index,
                                   ControlType.CheckBox)));
    }

    /**
     * Gets the CheckBox associated with the given name.
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationCheckBox getCheckBox(final String name)
            throws AutomationException {
        return new AutomationCheckBox(
                new ElementBuilder(this.getElementByControlType(name,
                                   ControlType.CheckBox)));
    }

    /**
     * Gets the CheckBox matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationCheckBox getCheckBox(final Pattern namePattern)
            throws AutomationException {
        return new AutomationCheckBox(
                new ElementBuilder(this.getElementByControlType(namePattern,
                                   ControlType.CheckBox)));
    }

    /**
     * Gets the CheckBox control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationCheckBox getCheckBoxByAutomationId(final String id)
            throws AutomationException {
        return new AutomationCheckBox(
                new ElementBuilder(this.getElementByAutomationId(id,
                                   ControlType.CheckBox)));
    }

    /**
     * Gets the Tab control associated with the given index.
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTab getTab(final int index)
            throws AutomationException {
         return new AutomationTab(
                 new ElementBuilder(
                         this.getElementByControlType(index, ControlType.Tab)));
    }

    /**
     * Gets the Tab associated with the given name.
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTab getTab(final String name)
            throws AutomationException {
        return new AutomationTab
                (new ElementBuilder(
                        this.getElementByControlType(name, ControlType.Tab)));
    }

    /**
     * Gets the Tab matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTab getTab(final Pattern namePattern)
            throws AutomationException {
        return new AutomationTab(
                new ElementBuilder(
                        this.getElementByControlType(namePattern,
                                ControlType.Tab)));
    }

    /**
     * Gets the Tab control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTab getTabByAutomationId(final String id)
            throws AutomationException {
        return new AutomationTab(
                new ElementBuilder(
                        this.getElementByAutomationId(id, ControlType.Tab)));
    }

    /**
     * Gets the Editbox control associated with the given index.
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationEditBox getEditBox(final int index)
            throws AutomationException {
        return new AutomationEditBox(
                new ElementBuilder(
                        this.getElementByControlType(index, ControlType.Edit)));
    }

    /**
     * Gets the Editbox control associated with the given name.
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationEditBox getEditBox(final String name)
            throws AutomationException {
        return new AutomationEditBox(
                new ElementBuilder(
                        this.getElementByControlType(name, ControlType.Edit)));
    }

    /**
     * Gets the Editbox control matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationEditBox getEditBox(final Pattern namePattern)
            throws AutomationException {
        return new AutomationEditBox(
                new ElementBuilder(
                        this.getElementByControlType(namePattern,
                                ControlType.Edit)));
    }

    /**
     * Gets the Editbox control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationEditBox getEditBoxByAutomationId(final String id)
            throws AutomationException {
        return new AutomationEditBox(
                new ElementBuilder(
                        this.getElementByAutomationId(id, ControlType.Edit)));
    }

    /**
     * Gets the EditBox (with password marking) associated with the given index.
     * @param index The index
     * @return The found control
     * @throws AutomationException Automation issue
     */
    public AutomationPasswordEditBox getPasswordEditBox(final int index)
            throws AutomationException {
        return new AutomationPasswordEditBox(
                new ElementBuilder(
                        this.getElementByControlType(index,
                                ControlType.Edit,
                                AutomationPasswordEditBox.CLASS_NAME)));
    }

    /**
     * Gets the EditBox (with password marking) associated with the given name.
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPasswordEditBox getPasswordEditBox(final String name)
            throws AutomationException {
        return new AutomationPasswordEditBox(
                new ElementBuilder(
                        this.getElementByControlType(name,
                                ControlType.Edit,
                                AutomationPasswordEditBox.CLASS_NAME)));
    }

    /**
     * Gets the EditBox (with password marking) matching the given name.
     * @param namePattern Matcher for the control name
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPasswordEditBox getPasswordEditBox(
            final Pattern namePattern)
            throws AutomationException {
        return new AutomationPasswordEditBox(
                new ElementBuilder(
                        this.getElementByControlType(namePattern,
                                ControlType.Edit,
                                AutomationPasswordEditBox.CLASS_NAME)));
    }

    /**
     * Gets the Editbox (with password marking) control associated with the
     * given automation id.
     *
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPasswordEditBox getPasswordEditBoxByAutomationId(
            final String id)
            throws AutomationException {
        return new AutomationPasswordEditBox(
                new ElementBuilder(
                        this.getElementByAutomationId(id, ControlType.Edit)));
    }

    /**
     * Gets the ProgressBar control associated with the given index.
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationProgressBar getProgressBar(final int index)
            throws AutomationException {
        return new AutomationProgressBar(
                new ElementBuilder(
                        this.getElementByControlType(index,
                        ControlType.ProgressBar)));
    }

    /**
     * Gets the ProgressBar control associated with the given name.
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationProgressBar getProgressBar(final String name)
            throws AutomationException {
        return new AutomationProgressBar(
                new ElementBuilder(
                        this.getElementByControlType(
                                name,
                                ControlType.ProgressBar)));
    }

    /**
     * Gets the ProgressBar control matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationProgressBar getProgressBar(final Pattern namePattern)
            throws AutomationException {
        return new AutomationProgressBar(
                new ElementBuilder(
                        this.getElementByControlType(
                                namePattern,
                                ControlType.ProgressBar)));
    }

    /**
     * Gets the ProgressBar control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationProgressBar getProgressBarByAutomationId(
            final String id)
            throws AutomationException {
        return new AutomationProgressBar(
                new ElementBuilder(this.getElementByAutomationId(id,
                        ControlType.ProgressBar)));
    }

    /**
     * Gets the slider control associated with the given index.
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong in automation
     */
    public AutomationSlider getSlider(final int index)
            throws AutomationException {
        return new AutomationSlider(
                new ElementBuilder(
                        this.getElementByControlType(index,
                                ControlType.Slider)));
    }

    /**
     * Gets the slider control associated with the given index.
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSlider getSlider(final String name)
            throws AutomationException {
        return new AutomationSlider(
                new ElementBuilder(
                        this.getElementByControlType(name,
                                ControlType.Slider)));
    }

    /**
     * Gets the slider control matching the given index.
     * @param namePattern Matcher for the control name
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSlider getSlider(final Pattern namePattern)
            throws AutomationException {
        return new AutomationSlider(
                new ElementBuilder(
                        this.getElementByControlType(namePattern,
                                        ControlType.Slider)));
    }

    /**
     * Gets the slider control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSlider getSliderByAutomationId(String id)
            throws AutomationException {
        return new AutomationSlider(
            new ElementBuilder(
                    this.getElementByAutomationId(id,
                    ControlType.Slider)));
    }

    /**
     * Gets the radio button control associated with the given index.
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationRadioButton getRadioButton(int index)
            throws AutomationException {
        return new AutomationRadioButton(
                new ElementBuilder(this.getElementByControlType(index,
                        ControlType.RadioButton)));
    }

    /**
     * Gets the radio button associated with the given name.
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationRadioButton getRadioButton(String name)
            throws AutomationException {
        return new AutomationRadioButton(
                new ElementBuilder(this.getElementByControlType(name,
                                   ControlType.RadioButton)));
    }

    /**
     * Gets the radio button matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationRadioButton getRadioButton(Pattern namePattern)
            throws AutomationException {
        return new AutomationRadioButton
                (new ElementBuilder(this.getElementByControlType(namePattern,
                ControlType.RadioButton)));
    }

    /**
     * Gets the radio button control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationRadioButton getRadioButtonByAutomationId(String id)
            throws AutomationException {
        return new AutomationRadioButton(new ElementBuilder(
                this.getElementByAutomationId(id, ControlType.RadioButton)));
    }

    /**
     * Gets the text box control associated with the given index.
     *
     * @param index Index of the control.
     * @return The found control.
     * @throws AutomationException Something has gone wrong.
     */
    public AutomationTextBox getTextBox(int index) throws AutomationException {
        return new AutomationTextBox(
                new ElementBuilder(
                        this.getElementByControlType(
                                index,
                                ControlType.Text)).automation(
                                        this.getAutomation()));
    }

    /**
     * Gets the text box control associated with the given index.
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTextBox getTextBox(String name) throws AutomationException {
        return new AutomationTextBox(
                new ElementBuilder(
                        this.getElementByControlType(name, ControlType.Text)));
    }

    /**
     * Gets the text box control matching the given index.
     * @param namePattern Matcher for the control name
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTextBox getTextBox(final Pattern namePattern)
            throws AutomationException {
        return new AutomationTextBox(
                new ElementBuilder(
                        this.getElementByControlType(namePattern,
                                ControlType.Text)));
    }

    /**
     * Gets the text box control associated with the given automation id.
     * @param id Automation id of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTextBox getTextBoxByAutomationId(final String id)
            throws AutomationException {
        return new AutomationTextBox(
                new ElementBuilder(
                        this.getElementByAutomationId(id, ControlType.Text)));
    }

    /**
     * Gets the ComboBox control associated with the given index.
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationComboBox getComboBox(final int index)
            throws AutomationException {
        return new AutomationComboBox(
                new ElementBuilder(
                        this.getElementByControlType(index,
                                ControlType.ComboBox)));
    }

    /**
     * Gets the ComboBox control associated with the given name.
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationComboBox getComboBox(final String name)
            throws AutomationException {
        return new AutomationComboBox(new ElementBuilder(this.getElementByControlType(name,
                ControlType.ComboBox)));
    }

    /**
     * Gets the ComboBox control matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationComboBox getComboBox(final Pattern namePattern) throws AutomationException {
        return new AutomationComboBox(new ElementBuilder(this.getElementByControlType(namePattern,
                ControlType.ComboBox)));
    }

    /**
     * Gets the ComboBox control associated with the given automation id.
     * @param id Automation id of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationComboBox getComboBoxByAutomationId(final String id) throws AutomationException {
        return new AutomationComboBox(new ElementBuilder(this.getElementByAutomationId(id,
                ControlType.ComboBox)));
    }

    /**
     * Gets the Custom control associated with the given index.
     * @param index Index of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationCustom getCustom(final int index) throws AutomationException {
        return new AutomationCustom(new ElementBuilder(this.getElementByControlType(index,
                ControlType.Custom)));
    }

    /**
     * Get the Custom control associated with the given name.
     * @param name The name to look for
     * @return The AutomationCustom
     * @throws AutomationException Something has gone wrong
     */
    public AutomationCustom getCustom(final String name) throws AutomationException {
        return new AutomationCustom(new ElementBuilder(this.getElementByControlType(name, ControlType.Custom)));
    }

    /**
     * Get the Custom control matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return The AutomationCustom
     * @throws AutomationException Something has gone wrong
     */
    public AutomationCustom getCustom(final Pattern namePattern) throws AutomationException {
        return new AutomationCustom(new ElementBuilder(this.getElementByControlType(namePattern, ControlType.Custom)));
    }

    /**
     * Get the Custom control associated with the given automation id.
     * @param id The id to look for
     * @return The AutomationCustom
     * @throws AutomationException Something has gone wrong
     */
    public AutomationCustom getCustomByAutomationId(final String id)
            throws AutomationException {
        return new AutomationCustom(new ElementBuilder(this.getElementByAutomationId(id, ControlType.Custom)));
    }

    /**
     * <p>
     * Get the Custom Control associated with the given control type.
     * </p>
     * <p>
     * At the moment, just get the first first element.
     * </p>
     * @param className The control type to look for
     * @return The AutomationCustom
     * @throws AutomationException Something has gone wrong
     */
    public AutomationCustom getCustomByControlType(final String className) throws AutomationException {
        return getCustomByClassName(0, className);
    }

    /**
     * Gets the Custom control associated with this index.
     * @param index The index
     * @param className The expected class name of the con.trol
     * @return The control wrapper
     * @throws AutomationException Automation issue
     */
    public AutomationCustom getCustomByClassName(final int index,
                                                 final String className) throws AutomationException {
        return new AutomationCustom(new ElementBuilder(this.getElementByControlType(index, ControlType.Custom,
                className)));
    }

    /**
     * Gets the Custom associated with the given name.
     * @param name Name of the control
     * @param className The expected class name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationCustom getCustomByClassName(final String name,
                                                 final String className)
            throws AutomationException {
        return new AutomationCustom(new ElementBuilder(this.getElementByControlType(name, ControlType.Custom,
                className)));
    }

    /**
     * Gets the Custom matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @param className The expected class name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationCustom getCustomByClassName(final Pattern namePattern,
                                                 final String className)
            throws AutomationException {
        return new AutomationCustom(new ElementBuilder(this.getElementByControlType(namePattern,
                ControlType.Custom, className)));
    }

    /**
     * Gets the Spinner control associated with the given index.
     * @param index Index of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSpinner getSpinner(final int index) throws AutomationException {
        return new AutomationSpinner(new ElementBuilder(this.getElementByControlType(index,
                ControlType.Spinner)));
    }

    /**
     * Get the Spinner control associated with the given name.
     * @param name The name to look for
     * @return The AutomationSpinner control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSpinner getSpinner(String name) throws AutomationException {
        return new AutomationSpinner(new ElementBuilder(this.getElementByControlType(name,
                ControlType.Spinner)));
    }

    /**
     * Get the Spinner control matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return The AutomationSpinner control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSpinner getSpinner(Pattern namePattern) throws AutomationException {
        return new AutomationSpinner(new ElementBuilder(this.getElementByControlType(namePattern,
                ControlType.Spinner)));
    }

    /**
     * Gets the Spinner control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSpinner getSpinnerByAutomationId(String id) throws AutomationException {
        return new AutomationSpinner(new ElementBuilder(this.getElementByAutomationId(id,
                ControlType.Spinner)));
    }

    /**
     * Gets the PowerpointSlide control associated with the given index.
     * @param index Index of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPowerpointSlide getPowerpointSlide(int index) throws AutomationException {
        return new AutomationPowerpointSlide(new ElementBuilder(this.getElementByControlType(index,
                ControlType.Custom)));
    }

    /**
     * Get the PowerpointSlide control associated with the given name.
     * @param name The name to look for
     * @return The AutomationCustom
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPowerpointSlide getPowerpointSlide(String name) throws AutomationException {
        return new AutomationPowerpointSlide(new ElementBuilder(this.getElementByControlType(name,
                ControlType.Custom)));
    }

    /**
     * Get the PowerpointSlide control matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return The AutomationCustom
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPowerpointSlide getPowerpointSlide(Pattern namePattern) throws AutomationException {
        return new AutomationPowerpointSlide(new ElementBuilder(this.getElementByControlType(namePattern,
                ControlType.Custom)));
    }

    /**
     * Gets the PowerpointSlide control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPowerpointSlide getPowerpointSlideByAutomationId(String id) throws AutomationException {
        return new AutomationPowerpointSlide(new ElementBuilder(this.getElementByAutomationId(id,
                ControlType.Custom)));
    }

    /**
     * Get the Image control associated with the given index.
     * @param index The index to look for
     * @return The AutomationImage
     * @throws AutomationException Something has gone wrong
     */
    public AutomationImage getImage(int index) throws AutomationException {
        return new AutomationImage(new ElementBuilder(this.getElementByControlType(index, ControlType.Image)));
    }

    /**
     * Get the Image control associated with the given name.
     * @param name The name to look for
     * @return The AutomationImage
     * @throws AutomationException Something has gone wrong
     */
    public AutomationImage getImage(String name) throws AutomationException {
        return new AutomationImage(new ElementBuilder(this.getElementByControlType(name, ControlType.Image)));
    }

    /**
     * Get the Image control matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return The AutomationImage
     * @throws AutomationException Something has gone wrong
     */
    public AutomationImage getImage(Pattern namePattern) throws AutomationException {
        return new AutomationImage(new ElementBuilder(this.getElementByControlType(namePattern, ControlType.Image)));
    }

    /**
     * Gets the Image control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationImage getImageByAutomationId(String id) throws AutomationException {
        return new AutomationImage(new ElementBuilder(this.getElementByAutomationId(id, ControlType.Image)));
    }

    /**
     * Gets the (JHC) Masked Edit control associated with the given index.
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Automation issue
     */
    public AutomationMaskedEdit getMaskedEdit(int index) throws AutomationException {
        return new AutomationMaskedEdit(new ElementBuilder(this.getElementByControlType(index, ControlType.Edit, AutomationMaskedEdit.CLASS_NAME)));
    }

    /**
     * Gets the (JHC) Masked Edit control associated with the given index.
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationMaskedEdit getMaskedEdit(final String name) throws AutomationException {
        return new AutomationMaskedEdit(
                new ElementBuilder(
                        this.getElementByControlType(name, ControlType.Edit, AutomationMaskedEdit.CLASS_NAME)));
    }

    /**
     * Gets the (JHC) Masked Edit control matching the given index.
     * @param namePattern Matcher for the control name
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationMaskedEdit getMaskedEdit(Pattern namePattern) throws AutomationException {
        return new AutomationMaskedEdit(new ElementBuilder(this.getElementByControlType(namePattern, ControlType.Edit, AutomationMaskedEdit.CLASS_NAME)));
    }

    /**
     * Gets the (JHC) Masked Edit control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationMaskedEdit getMaskedEditByAutomationId(String id) throws AutomationException {
        return new AutomationMaskedEdit(new ElementBuilder(this.getElementByAutomationId(id, ControlType.Edit)));
    }

    /**
     * Gets the panel control associated with the given index.
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPanel getPanel(int index) throws AutomationException {
        return new AutomationPanel(new ElementBuilder(this.getElementByControlType(index, ControlType.Pane)));
    }

    /**
     * Gets the panel control associated with the given name.
     * @param name Name of the control
     * @return The found control
     * @throws ElementNotFoundException Did not find the element
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPanel getPanel(String name) throws AutomationException {
        return new AutomationPanel(new ElementBuilder(this.getElementByControlType(name, ControlType.Pane)));
    }

    /**
     * Gets the panel control matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return The found control
     * @throws ElementNotFoundException Did not find the element
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPanel getPanel(Pattern namePattern) throws AutomationException {
        return new AutomationPanel(new ElementBuilder(this.getElementByControlType(namePattern, ControlType.Pane)));
    }

    /**
     * Gets the panel control associated with the automation id.
     * @param id Automaton id of the control
     * @return The found control
     * @throws ElementNotFoundException Did not find the element
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPanel getPanelByAutomationId(String id) throws AutomationException {
        return new AutomationPanel(new ElementBuilder(this.getElementByAutomationId(id, ControlType.Pane)));
    }

    /**
     * Gets the panel control associated with the given index, with a specific class name.
     * @param index Index of the control
     * @param className The specific classname
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPanel getPanelByClassName(int index, String className) throws AutomationException {
        return new AutomationPanel(new ElementBuilder(this.getElementByControlType(index, ControlType.Pane, className)));
    }

    /**
     * Gets the panel control associated with the given name, with a specific class name.
     * @param name Name of the control
     * @param className The specific classname
     * @return the found control
     * @throws ElementNotFoundException Did not find the element
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPanel getPanelByClassName(String name, String className) throws AutomationException {
        return new AutomationPanel(new ElementBuilder(this.getElementByControlType(name, ControlType.Pane, className)));
    }

    /**
     * Gets the panel control matching the given namePattern, with a specific class name.
     * @param namePattern Matcher for the control name
     * @param className The specific classname
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPanel getPanelByClassName(final Pattern namePattern,
                                               final String className)
            throws AutomationException {
        return new AutomationPanel(new ElementBuilder(this.getElementByControlType(namePattern, ControlType.Pane, className)));
    }

    /**
     * Gets the SplitButton control associated with the given index.
     * @param index Index of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSplitButton getSplitButton(int index) throws AutomationException {
        return new AutomationSplitButton(new ElementBuilder(this.getElementByControlType(index,
                ControlType.SplitButton)));
    }

    /**
     * Get the SplitButton control associated with the given name.
     * @param name The name to look for
     * @return The AutomationSplitButton
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSplitButton getSplitButton(String name) throws AutomationException {
        return new AutomationSplitButton(new ElementBuilder(this.getElementByControlType(name,
                ControlType.SplitButton)));
    }

    /**
     * Get the SplitButton control matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return The AutomationSplitButton
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSplitButton getSplitButton(Pattern namePattern) throws AutomationException {
        return new AutomationSplitButton(new ElementBuilder(this.getElementByControlType(namePattern,
                ControlType.SplitButton)));
    }

    /**
     * Gets the SplitButton control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSplitButton getSplitButtonByAutomationId(String id) throws AutomationException {
        return new AutomationSplitButton(new ElementBuilder(this.getElementByAutomationId(id,
                ControlType.SplitButton)));
    }

    /**
     * Gets the button control associated with the given index.
     * @param index The index of the button
     * @return The AutomationButton
     * @throws AutomationException Something has gone wrong
     */
    public AutomationButton getButton(int index) throws AutomationException {
        return new AutomationButton(new ElementBuilder(this.getElementByControlType(index, ControlType.Button)));
    }

    /**
     * Gets the button control associated with the given name.
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationButton getButton(String name) throws AutomationException {
        return new AutomationButton(new ElementBuilder(this.getElementByControlType(name,
                ControlType.Button)));
    }

    /**
     * Gets the button control matching with the given name.
     * @param namePattern Matcher for the control name
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationButton getButton(Pattern namePattern) throws AutomationException {
        return new AutomationButton(new ElementBuilder(this.getElementByControlType(namePattern,
                ControlType.Button)));
    }

    /**
     * Gets the button using the automation ID.
     * @param id The automation id
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationButton getButtonByAutomationId(String id) throws AutomationException {
        return new AutomationButton(new ElementBuilder(this.getElementByAutomationId(id,
                ControlType.Button)));
    }

    /**
     * Gets the document associated with the given index.
     * @param index Index of the control
     * @return The document control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationDocument getDocument(int index) throws AutomationException {
        return new AutomationDocument(new ElementBuilder(this.getElementByControlType(index, ControlType.Document)));
    }

    /**
     * Gets the document associated with the given name.
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationDocument getDocument(String name) throws AutomationException {
        return new AutomationDocument(new ElementBuilder(this.getElementByControlType(name, ControlType.Document)));
    }

    /**
     * Gets the document matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationDocument getDocument(Pattern namePattern) throws AutomationException {
        return new AutomationDocument(new ElementBuilder(this.getElementByControlType(namePattern,
                ControlType.Document)));
    }

    /**
     * Gets the document associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationDocument getDocumentByAutomationId(String id) throws AutomationException {
        return new AutomationDocument(new ElementBuilder(this.getElementByAutomationId(id,
                ControlType.Document)));
    }

    /**
     * Gets the hyperlink control associated with the given index.
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationHyperlink getHyperlink(int index) throws AutomationException {
        return new AutomationHyperlink(new ElementBuilder(this.getElementByControlType(index,
                ControlType.Hyperlink)));
    }

    /**
     * Gets the hyperlink control associated with the given name.
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationHyperlink getHyperlink(String name) throws AutomationException {
        return new AutomationHyperlink(new ElementBuilder(this.getElementByControlType(name,
                ControlType.Hyperlink)));
    }

    /**
     * Gets the hyperlink control matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationHyperlink getHyperlink(Pattern namePattern) throws AutomationException {
        return new AutomationHyperlink(new ElementBuilder(this.getElementByControlType(namePattern,
                ControlType.Hyperlink)));
    }

    /**
     * Gets the hyperlink control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationHyperlink getHyperlinkByAutomationId(String id) throws AutomationException {
        return new AutomationHyperlink(new ElementBuilder(this.getElementByAutomationId(id,
                ControlType.Hyperlink)));
    }

    /**
     * Gets the list control associated with the given index.
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationList getList(int index) throws AutomationException {
        return new AutomationList(new ElementBuilder(this.getElementByControlType(index, ControlType.List)));
    }

    /**
     * Gets the list control associated with the given name.
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationList getList(String name) throws AutomationException {
        return new AutomationList(new ElementBuilder(this.getElementByControlType(name, ControlType.List)));
    }

    /**
     * Gets the list control matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationList getList(Pattern namePattern) throws AutomationException {
        return new AutomationList(new ElementBuilder(this.getElementByControlType(namePattern, ControlType.List)));
    }

    /**
     * Gets the list control associated with the given automation ID.
     * @param automationId Automation id of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationList getListByAutomationId(String automationId) throws AutomationException {
        return new AutomationList(new ElementBuilder(this.getElementByAutomationId(automationId, ControlType.List)));
    }

    /**
     * Gets the treeview control associated with the given index.
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTreeView getTreeView(int index) throws AutomationException {
        return new AutomationTreeView(new ElementBuilder(this.getElementByControlType(index, ControlType.Tree)));
    }

    /**
     * Gets the treeview control associated with the given name.
     * @param name Name of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTreeView getTreeView(String name) throws AutomationException {
        return new AutomationTreeView(new ElementBuilder(this.getElementByControlType(name, ControlType.Tree)));
    }

    /**
     * Gets the treeview control matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTreeView getTreeView(Pattern namePattern) throws AutomationException {
        return new AutomationTreeView(new ElementBuilder(this.getElementByControlType(namePattern, ControlType.Tree)));
    }

    /**
     * Gets the treeview control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTreeView getTreeViewByAutomationId(String id) throws AutomationException {
        return new AutomationTreeView(new ElementBuilder(this.getElementByAutomationId(id, ControlType.Tree)));
    }

    /**
     * Gets the calendar control associated with the given index.
     * @param index Index of the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationCalendar getCalendar(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationCalendar(new ElementBuilder(this.getElementByControlType(index,
                ControlType.Calendar)));
    }

    /**
     * Gets the calendar associated with the given name.
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationCalendar getCalendar(String name) throws PatternNotFoundException, AutomationException {
        return new AutomationCalendar(new ElementBuilder(this.getElementByControlType(name,
                ControlType.Calendar)));
    }

    /**
     * Gets the calendar matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return the found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationCalendar getCalendar(Pattern namePattern) throws PatternNotFoundException, AutomationException {
        return new AutomationCalendar(new ElementBuilder(this.getElementByControlType(namePattern,
                ControlType.Calendar)));
    }

    /**
     * Gets the calendar control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationCalendar getCalendarByAutomationId(String id)
            throws PatternNotFoundException, AutomationException {
        return new AutomationCalendar(new ElementBuilder(this.getElementByAutomationId(id,
                ControlType.Calendar)));
    }

    /**
     * Get the AppBar control associated with the given index.
     * @param index The index
     * @return The AutomationAppBar
     * @throws AutomationException Something has gone wrong
     */
    public AutomationAppBar getAppBar(int index) throws AutomationException {
        return new AutomationAppBar(new ElementBuilder(this.getElementByControlType(index, ControlType.AppBar)));
    }

    /**
     * Gets the AppBar control associated with the given name.
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationAppBar getAppBar(String name) throws AutomationException {
        return new AutomationAppBar(new ElementBuilder(this.getElementByControlType(name, ControlType.AppBar)));
    }

    /**
     * Gets the AppBar control matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationAppBar getAppBar(Pattern namePattern)
            throws AutomationException {
        return new AutomationAppBar(new ElementBuilder(this.getElementByControlType(namePattern, ControlType.AppBar)));
    }

    /**
     * Gets the AppBar control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationAppBar getAppBarByAutomationId(String id) throws AutomationException {
        return new AutomationAppBar(new ElementBuilder(this.getElementByAutomationId(id, ControlType.AppBar)));
    }

    /**
     * Gets the ReBar control associated with this index.
     * @param index The index
     * @return The control wrapper
     * @throws AutomationException Automation issue
     */
    public AutomationReBar getReBar(final int index) throws AutomationException {
        return new AutomationReBar(new ElementBuilder(this.getElementByControlType(index, ControlType.Pane,
                AutomationReBar.CLASS_NAME)));
    }

    /**
     * Gets the ReBar associated with the given name.
     * @param name Name of the control
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationReBar getReBar(String name) throws AutomationException {
        return new AutomationReBar(new ElementBuilder(this.getElementByControlType(name,
                ControlType.Pane, AutomationReBar.CLASS_NAME)));
    }

    /**
     * Gets the ReBar matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationReBar getReBar(final Pattern namePattern)
            throws AutomationException {
        return new AutomationReBar(new ElementBuilder(this.getElementByControlType(namePattern,
                ControlType.Pane, AutomationReBar.CLASS_NAME)));
    }

    /**
     * Gets the ReBar control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationReBar getReBarByAutomationId(String id) throws AutomationException {
        return new AutomationReBar(new ElementBuilder(this.getElementByAutomationId(id, ControlType.Pane)));
    }

    /**
     * Get the ToolBar control associated with the given index.
     * @param index The index
     * @return The AutomationToolBar
     * @throws AutomationException Something has gone wrong
     */
    public AutomationToolBar getToolBar(int index) throws AutomationException {
        return new AutomationToolBar(new ElementBuilder(this.getElementByControlType(index, ControlType.ToolBar)));
    }

    /**
     * Get the ToolBar control associated with the given name.
     * @param name The name
     * @return The AutomationToolBar
     * @throws AutomationException Something has gone wrong
     */
    public AutomationToolBar getToolBar(String name) throws AutomationException {
        return new AutomationToolBar(
                new ElementBuilder(
                        this.getElementByControlType(name, ControlType.ToolBar)));
    }

    /**
     * Get the ToolBar control matching the given namePattern.
     * @param namePattern Matcher for the control name
     * @return The AutomationToolBar
     * @throws AutomationException Something has gone wrong
     */
    public AutomationToolBar getToolBar(Pattern namePattern) throws AutomationException {
        return new AutomationToolBar(new ElementBuilder(this.getElementByControlType(namePattern,
                ControlType.ToolBar)));
    }

    /**
     * Gets the ToolBar control associated with the given automation id.
     * @param id The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationToolBar getToolBarByAutomationId(String id) throws AutomationException {
        return new AutomationToolBar(new ElementBuilder(this.getElementByAutomationId(id, ControlType.ToolBar)));
    }

    ////////////////// Public API //////////////////

    /**
     * Get the checkbox, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationCheckBox getCheckBox(final Search search) throws AutomationException {
        if (search.getHasNamePattern()) {
            return getCheckBox(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getCheckBoxByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getCheckBox(search.getIndex());
        } else if (search.getHasName()) {
            return getCheckBox(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Get the checkbox, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTab getTab(final Search search) throws AutomationException {
        if (search.getHasNamePattern()) {
            return getTab(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getTabByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getTab(search.getIndex());
        } else if (search.getHasName()) {
            return getTab(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Get the editbox, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
*    */
    public AutomationEditBox getEditBox(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getEditBox(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getEditBoxByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getEditBox(search.getIndex());
        } else if (search.getHasName()) {
            return getEditBox(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the EditBox (with password masking), using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPasswordEditBox getPasswordEditBox(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getPasswordEditBox(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getPasswordEditBoxByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getPasswordEditBox(search.getIndex());
        } else if (search.getHasName()) {
            return getPasswordEditBox(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the ProgressBar control, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationProgressBar getProgressBar(Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getProgressBar(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getProgressBarByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getProgressBar(search.getIndex());
        } else if (search.getHasName()) {
            return getProgressBar(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the slider control, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSlider getSlider(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getSlider(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getSliderByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getSlider(search.getIndex());
        } else if (search.getHasName()) {
            return getSlider(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the radio button control, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationRadioButton getRadioButton(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getRadioButton(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getRadioButtonByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getRadioButton(search.getIndex());
        } else if (search.getHasName()) {
            return getRadioButton(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the button control, using the search criteria.
     * @param search The search criteria
     * @return The AutomationButton
     * @throws AutomationException Something has gone wrong
     */
    public AutomationButton getButton(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getButton(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getButtonByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getButton(search.getIndex());
        } else if (search.getHasName()) {
            return getButton(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the (JHC) Masked Edit control, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationMaskedEdit getMaskedEdit(Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getMaskedEdit(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getMaskedEditByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getMaskedEdit(search.getIndex());
        } else if (search.getHasName()) {
            return getMaskedEdit(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Get the text box, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTextBox getTextBox(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getTextBox(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getTextBoxByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getTextBox(search.getIndex());
        } else if (search.getHasName()) {
            return getTextBox(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Get the combobox, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationComboBox getComboBox(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getComboBox(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getComboBoxByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getComboBox(search.getIndex());
        } else if (search.getHasName()) {
            return getComboBox(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Get the data grid, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationDataGrid getDataGrid(final Search search)
            throws AutomationException {
        if (search.getHasClassName()) {
            if (search.getHasName()) {
                return getDataGrid(search.getName(),
                        search.getClassName());
            } else if (search.getHasNamePattern()) {
                return getDataGrid(search.getNamePattern(),
                        search.getClassName());
            } else if (search.getHasIndex()) {
                return getDataGrid(search.getIndex(),
                        search.getClassName());
            } else {
                throw new AutomationException("Search type not found");
            }
        } else if (search.getHasNamePattern()) {
            return getDataGrid(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getDataGridByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getDataGrid(search.getIndex());
        } else if (search.getHasName()) {
            return getDataGrid(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the document, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationDocument getDocument(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getDocument(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getDocumentByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getDocument(search.getIndex());
        } else if (search.getHasName()) {
            return getDocument(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the hyperlink, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationHyperlink getHyperlink(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getHyperlink(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getHyperlinkByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getHyperlink(search.getIndex());
        } else if (search.getHasName()) {
            return getHyperlink(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the treeview control, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationTreeView getTreeView(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getTreeView(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getTreeViewByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getTreeView(search.getIndex());
        } else if (search.getHasName()) {
            return getTreeView(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the list control, using the search criteria.
     * @param search Matcher for the control name
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationList getList(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getList(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getListByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getList(search.getIndex());
        } else if (search.getHasName()) {
            return getList(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the panel control, using the search criteria.
     * @param search Matcher for the control name
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPanel getPanel(final Search search)
            throws AutomationException {
        if (search.getHasClassName()) {
            if (search.getHasIndex()) {
                return getPanelByClassName(search.getIndex(),
                        search.getClassName());
            } else if (search.getHasName()) {
                return getPanelByClassName(search.getName(),
                        search.getClassName());
            }  else if (search.getHasNamePattern()) {
                return getPanelByClassName(search.getNamePattern(),
                        search.getClassName());
            } else {
                throw new AutomationException("Search type not found");
            }
        } else if (search.getHasNamePattern()) {
            return getPanel(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getPanelByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getPanel(search.getIndex());
        } else if (search.getHasName()) {
            return getPanel(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the calendar control, using the search criteria.
     * @param search Matcher for the control name
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationCalendar getCalendar(final Search search)
            throws PatternNotFoundException, AutomationException {
        if (search.getHasNamePattern()) {
            return getCalendar(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getCalendarByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getCalendar(search.getIndex());
        } else if (search.getHasName()) {
            return getCalendar(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the AppBar control , using the search criteria.
     * @param search Matcher for the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationAppBar getAppBar(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getAppBar(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getAppBarByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getAppBar(search.getIndex());
        } else if (search.getHasName()) {
            return getAppBar(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Get the RibbonBar control associated this container.
     * @return The AutomationRibbonBar
     * @throws AutomationException Automation issue
     */
    public AutomationRibbonBar getRibbonBar() throws AutomationException {
        return new AutomationRibbonBar(
                new ElementBuilder(
                        this.getElementByControlType(0,
                                ControlType.Pane,
                                AutomationRibbonBar.CLASS_NAME)));
    }

    /**
     * Gets the ReBar control, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationReBar getReBar(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getReBar(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getReBarByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getReBar(search.getIndex());
        } else if (search.getHasName()) {
            return getReBar(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the ToolBar control associated with the given automation id.
     * @param search Matcher for the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationToolBar getToolBar(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getToolBar(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getToolBarByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getToolBar(search.getIndex());
        } else if (search.getHasName()) {
            return getToolBar(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Get the SplitButton, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSplitButton getSplitButton(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getSplitButton(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getSplitButtonByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getSplitButton(search.getIndex());
        } else if (search.getHasName()) {
            return getSplitButton(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Get the image control, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationImage getImage(Search search) throws AutomationException {
        if (search.getHasNamePattern()) {
            return getImage(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getImageByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getImage(search.getIndex());
        } else if (search.getHasName()) {
            return getImage(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Get the custom control, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationCustom getCustom(final Search search)
            throws AutomationException {

        if (search.getHasClassName()) {
            if (search.getHasName()) {
                return getCustomByClassName(search.getName(),
                        search.getClassName());
            } else if (search.getHasNamePattern()) {
                return getCustomByClassName(search.getNamePattern(),
                        search.getClassName());
            } else if (search.getHasIndex()) {
                return getCustomByClassName(search.getIndex(),
                        search.getClassName());
            } else {
                throw new AutomationException("Search type not found");
            }
        } else if (search.getHasControlType()) {
            return getCustomByControlType(search.getControlType());
        } else if (search.getHasNamePattern()) {
            return getCustom(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getCustomByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getCustom(search.getIndex());
        } else if (search.getHasName()) {
            return getCustom(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Get the power point slide, using the search criteria.
     * @param search The search criteria
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationPowerpointSlide getPowerpointSlide(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getPowerpointSlide(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getPowerpointSlideByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getPowerpointSlide(search.getIndex());
        } else if (search.getHasName()) {
            return getPowerpointSlide(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the Spinner control, using the search criteria.
     * @param search Search criteria
     * @return the found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationSpinner getSpinner(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getSpinner(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getSpinnerByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getSpinner(search.getIndex());
        } else if (search.getHasName()) {
            return getSpinner(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    //// Generic getters
    
    /**
     * Gets a control by control type.
     * @param index The nth item that matches
     * @param id The control type
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationBase getControlByControlType(final int index,
                                                  final ControlType id)
            throws AutomationException, PatternNotFoundException {
    	AutomationElement el = this.getElementByControlType(index, id);
    	return AutomationControlFactory.get(this, el);
    }

    /**
     * Gets a control by control type.
     * @param index The nth item that matches
     * @param id The control type
     * @param className The className to look for
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationBase getControlByControlType(final int index,
                                                  final ControlType id,
                                                  final String className)
            throws AutomationException, PatternNotFoundException {
    	AutomationElement el = this.getElementByControlType(index, id, className);
    	return AutomationControlFactory.get(this, el);
    }
    
    /**
     * Gets the control by the control type.
     * @param name Name to use
     * @param id Control type
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationBase getControlByControlType(final String name,
                                                  final ControlType id)
            throws AutomationException, PatternNotFoundException {
    	AutomationElement el = this.getElementByControlType(name, id);
    	return AutomationControlFactory.get(this, el);
    }
    
    /**
     * Gets the control by the control type.
     * 
     * @param namePattern a pattern to match the name
     * @param id Control type
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationBase getControlByControlType(final Pattern namePattern,
                                                  final ControlType id)
            throws AutomationException, PatternNotFoundException {
    	AutomationElement el = this.getElementByControlType(namePattern, id);
    	return AutomationControlFactory.get(this, el);
    }
    
    /**
     * Gets the control by the control type.
     * @param name Name to use
     * @param id Control type
     * @param className The className to look for
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationBase getControlByControlType(final String name,
                                                  final ControlType id,
                                                  final String className)
            throws AutomationException, PatternNotFoundException {
    	AutomationElement el = this.getElementByControlType(name, id, className);
    	return AutomationControlFactory.get(this, el);
    }

    /**
     * Gets the control by the control type.
     * 
     * @param namePattern a pattern matching the name
     * @param id Control type
     * @param className The className to look for
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationBase getControlByControlType(final Pattern namePattern,
                                                  final ControlType id,
                                                  final String className)
            throws AutomationException, PatternNotFoundException {
    	AutomationElement el = this.getElementByControlType(namePattern, id, className);
    	return AutomationControlFactory.get(this, el);
    }
    
    /**
     * Gets the control by the name.
     *
     * @param name Name of the element
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws AutomationException Did not find the element
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationBase getControlByName(final String name)
            throws AutomationException, PatternNotFoundException {
		AutomationElement el = this.getElementByName(name);
		return AutomationControlFactory.get(this, el);
	}

    /**
     * Gets the control by the name.
     *
     * @param namePattern A pattern which matches the name of the element
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws AutomationException Did not find the element
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationBase getControlByName(final Pattern namePattern)
            throws AutomationException, PatternNotFoundException {
		AutomationElement el = this.getElementByName(namePattern);
		return AutomationControlFactory.get(this, el);
	}
    
    /**
     * Gets the first control by the className.
     *
     * @param className The className to look for
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationBase getControlByClassName(final String className)
            throws AutomationException, PatternNotFoundException {
        try {
        	AutomationElement el = this.getElementByIndex(0, className);
        	return AutomationControlFactory.get(this, el);
        } catch (IndexOutOfBoundsException ex) {
        	throw new ElementNotFoundException("with class name " + className);
        }
    }

    /**
     * Gets the control by the index and className.
     *
     * @param index The nth item that matches
     * @param className The className to look for
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationBase getControlByClassName(final int index,
                                                final String className)
            throws AutomationException, PatternNotFoundException {
        AutomationElement el = this.getElementByIndex(index, className);
        return AutomationControlFactory.get(this, el);
    }

    /**
     * Gets the control by the name and className.
     *
     * @param name Name of the element
     * @param className The className to look for
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationBase getControlByClassName(final String name, final String className)
            throws AutomationException, PatternNotFoundException {
        AutomationElement el = this.getElementByName(name, className);
        return AutomationControlFactory.get(this, el);
    }

    /**
     * Gets the control by matching the name and className.
     *
     * @param namePattern a pattern matching the name
     * @param className The className to look for
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationBase getControlByClassName(final Pattern namePattern,
                                                final String className)
            throws AutomationException, PatternNotFoundException {
        AutomationElement el = this.getElementByName(namePattern, className);
        return AutomationControlFactory.get(this, el);
    }

    /**
     * Gets the control associated with the given automation id.
     * @param automationId The id to use
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationBase getControlByAutomationId(final String automationId)
            throws AutomationException, PatternNotFoundException {
    	AutomationElement el = this.getElementByAutomationId(automationId);
    	return AutomationControlFactory.get(this, el);
    }

    /**
     * Get a control, based on the class and the name.
     *
     * Experimental and not currently recommended, doesn't seem to work consistently,
     * also not sufficiently generic, and needs converting to use Search builder maybe.
     *
     * @param type Class to return / check for
     * @param controlType The control type to look for
     * @param name Name to be looked for
     * @param <T> The Type of the class
     * @return Found element
     * @throws PatternNotFoundException Expected pattern not found
     * @throws AutomationException Raised from automation library
     */

    public <T extends AutomationBase> T get(Class<T> type,
                                            final ControlType controlType,
                                            final String name)
            throws PatternNotFoundException, AutomationException {

        Variant.VARIANT.ByValue variant1 = new Variant.VARIANT.ByValue();
        variant1.setValue(Variant.VT_INT, controlType.getValue());

        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString(name);
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        try {
            PointerByReference propertyCondition =
                    this.getAutomation().createPropertyCondition(
                            PropertyID.ControlType.getValue(), variant1);

            PointerByReference nameCondition =
                    this.getAutomation().createPropertyCondition(
                            PropertyID.Name.getValue(), variant);
            PointerByReference condition =
                    this.getAutomation().createAndCondition(
                            nameCondition, propertyCondition);

            AutomationElement elem =
                    this.findFirst(
                            new TreeScope(TreeScope.Descendants), condition);

            /* Not going to work for menus */
            return type.cast(AutomationControlFactory.get(
                    null, controlType, elem));
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }
    }
}
