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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.controls.menu.AutomationMenu;
import mmarquee.automation.controls.menu.AutomationMenuItem;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * A Factory which creates concrete controls for AutomationElements.
 */
public class AutomationControlFactory {
	
	/**
	 * Returns an Automation Control for the given element.
	 * 
	 * @param parent the parent control of the new automation control (Only relevant for menuBars).
	 * @param element The AutomationElement for which to create a specific control object.
	 * @return The AutomationBase child which best describes the given element.
	 * @throws AutomationException Automation library error.
	 * @throws PatternNotFoundException Expected pattern not found.
	 */
	public static AutomationBase get(final AutomationBase parent,
									 final AutomationElement element)
			throws AutomationException, PatternNotFoundException {
		if (element == null) {
			throw new ElementNotFoundException("null");
		}
		
    	ControlType controlType = ControlType.fromValue(element.getControlType());
		return get(parent, controlType, element);
	}

	/**
	 * Returns an Automation Control for the given element.
	 *
	 * @param parent the parent control of the new automation control (Only relevant for menuBars).
	 * @param controlType The Control type to get.
	 * @param element The AutomationElement for which to create a specific control object.
	 * @return The AutomationBase child which best describes the given element.
	 * @throws AutomationException Automation library error.
	 * @throws PatternNotFoundException Expected pattern not found.
	 */
	static AutomationBase get(final AutomationBase parent,
							  final ControlType controlType,
							  final AutomationElement element)
            throws AutomationException, PatternNotFoundException {

    	String className = element.getClassName();
    	switch (controlType) {
    	case Button:
    		return new AutomationButton(new ElementBuilder(element));
    	case Calendar:
    		return new AutomationCalendar(new ElementBuilder(element));
    	case CheckBox:
    		return new AutomationCheckBox(new ElementBuilder(element));
    	case ComboBox:
    		return new AutomationComboBox(new ElementBuilder(element));
    	case Edit:
			if (AutomationPasswordEditBox.CLASS_NAME.equals(className)) {
				return new AutomationPasswordEditBox(new ElementBuilder(element));
			} else if (AutomationMaskedEdit.CLASS_NAME.equals(className)) {
				return new AutomationMaskedEdit(new ElementBuilder(element));
			}
    		return new AutomationEditBox(new ElementBuilder(element));
    	case Hyperlink:
    		return new AutomationHyperlink(new ElementBuilder(element));
    	case Image:
    		return new AutomationImage(new ElementBuilder(element));
    	case ListItem:
    		return new AutomationListItem(new ElementBuilder(element));
    	case List:
    		return new AutomationList(new ElementBuilder(element));
    	case Menu:
    		return new AutomationMenu(new ElementBuilder(element));
    	case MenuBar:
    		return new AutomationMainMenu(new ElementBuilder(element).parent(parent.getElement()));
    	case MenuItem:
    		return new AutomationMenuItem(new ElementBuilder(element));
    	case ProgressBar:
    		return new AutomationProgressBar(new ElementBuilder(element));
    	case RadioButton:
    		return new AutomationRadioButton(new ElementBuilder(element));
    	case Slider:
    		return new AutomationSlider(new ElementBuilder(element));
    	case Spinner:
    		return new AutomationSpinner(new ElementBuilder(element));
    	case StatusBar:
    		return new AutomationStatusBar(new ElementBuilder(element));
    	case Tab:
    		return new AutomationTab(new ElementBuilder(element));
    	case TabItem:
    		return new AutomationTabItem(new ElementBuilder(element));
    	case Text:
    		return new AutomationTextBox(new ElementBuilder(element));
    	case ToolBar:
    		return new AutomationToolBar(new ElementBuilder(element));
    	case Tree:
    		return new AutomationTreeView(new ElementBuilder(element));
    	case TreeItem:
    		return new AutomationTreeViewItem(new ElementBuilder(element));
    	case Custom:
    		return new AutomationCustom(new ElementBuilder(element));
    	case DataGrid:
    		return new AutomationDataGrid(new ElementBuilder(element));
    	case Document:
    		return new AutomationDocument(new ElementBuilder(element));
    	case SplitButton:
    		return new AutomationSplitButton(new ElementBuilder(element));
    	case Window:
    		return new AutomationWindow(new ElementBuilder(element));
    	case Pane:
			switch (className) {
				case AutomationReBar.CLASS_NAME:
					return new AutomationReBar(new ElementBuilder(element));
				case AutomationRibbonBar.CLASS_NAME:
					return new AutomationRibbonBar(new ElementBuilder(element));
				case AutomationRibbonCommandBar.CLASS_NAME:
					return new AutomationRibbonCommandBar(new ElementBuilder(element));
				case AutomationRibbonWorkPane.CLASS_NAME:
					return new AutomationRibbonWorkPane(new ElementBuilder(element));
				case AutomationNUIPane.CLASS_NAME:
					return new AutomationNUIPane(new ElementBuilder(element));
				case AutomationNetUIHWND.CLASS_NAME:
					return new AutomationNetUIHWND(new ElementBuilder(element));
			}
    		return new AutomationPanel(new ElementBuilder(element));
    	case TitleBar:
    		return new AutomationTitleBar(new ElementBuilder(element));
    	case AppBar:
    		return new AutomationAppBar(new ElementBuilder(element));
    		// No Custom controls, yet:
    	case ScrollBar:
    	case ToolTip:
    	case Group:
    	case Thumb:
    	case DataItem:
    	case Header:
    	case HeaderItem:
    	case Table:
    	case Separator:
    	case SemanticZoom:
    	default:
    		// best try
            return new AutomationContainer(new ElementBuilder(element));
    	}
    }
}