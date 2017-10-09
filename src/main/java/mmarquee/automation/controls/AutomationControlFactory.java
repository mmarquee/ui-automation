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
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.controls.menu.AutomationMenu;
import mmarquee.automation.controls.menu.AutomationMenuItem;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * A Factory which creates concrete controls for AutomationElements
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
    		return new AutomationButton(element);
    	case Calendar:
    		return new AutomationCalendar(element);
    	case CheckBox:
    		return new AutomationCheckBox(element);
    	case ComboBox:
    		return new AutomationComboBox(element);
    	case Edit:
			if (AutomationPasswordEditBox.CLASS_NAME.equals(className)) {
				return new AutomationPasswordEditBox(element);
			} else if (AutomationMaskedEdit.CLASS_NAME.equals(className)) {
				return new AutomationMaskedEdit(element);
			}
    		return new AutomationEditBox(element);
    	case Hyperlink:
    		return new AutomationHyperlink(element);
    	case Image:
    		return new AutomationImage(element);
    	case ListItem:
    		return new AutomationListItem(element);
    	case List:
    		return new AutomationList(element);
    	case Menu:
    		return new AutomationMenu(element);
    	case MenuBar:
    		return new AutomationMainMenu(parent.getElement(), element);
    	case MenuItem:
    		return new AutomationMenuItem(element);
    	case ProgressBar:
    		return new AutomationProgressBar(element);
    	case RadioButton:
    		return new AutomationRadioButton(element);
    	case Slider:
    		return new AutomationSlider(element);
    	case Spinner:
    		return new AutomationSpinner(element);
    	case StatusBar:
    		return new AutomationStatusBar(element);
    	case Tab:
    		return new AutomationTab(element);
    	case TabItem:
    		return new AutomationTabItem(element);
    	case Text:
    		return new AutomationTextBox(element);
    	case ToolBar:
    		return new AutomationToolBar(element);
    	case Tree:
    		return new AutomationTreeView(element);
    	case TreeItem:
    		return new AutomationTreeViewItem(element);
    	case Custom:
    		return new AutomationCustom(element);
    	case DataGrid:
    		return new AutomationDataGrid(element);
    	case Document:
    		return new AutomationDocument(element);
    	case SplitButton:
    		return new AutomationSplitButton(element);
    	case Window:
    		return new AutomationWindow(element);
    	case Pane:
			if (AutomationReBar.CLASS_NAME.equals(className)) {
				return new AutomationReBar(element);
			} else if (AutomationRibbonBar.CLASS_NAME.equals(className)) {
				return new AutomationRibbonBar(element);
			} else if (AutomationRibbonCommandBar.CLASS_NAME.equals(className)) {
				return new AutomationRibbonCommandBar(element);
			} else if (AutomationRibbonWorkPane.CLASS_NAME.equals(className)) {
				return new AutomationRibbonWorkPane(element);
			} else if (AutomationNUIPane.CLASS_NAME.equals(className)) {
				return new AutomationNUIPane(element);
			} else if (AutomationNetUIHWND.CLASS_NAME.equals(className)) {
				return new AutomationNetUIHWND(element);
			}
    		return new AutomationPanel(element);
    	case TitleBar:
    		return new AutomationTitleBar(element);
    	case AppBar:
    		return new AutomationAppBar(element);
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
            return new AutomationContainer(element);
    	}
    }
}