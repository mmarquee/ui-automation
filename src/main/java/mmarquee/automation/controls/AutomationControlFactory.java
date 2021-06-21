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

import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.Element;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * A Factory which creates concrete controls for AutomationElements.
 */
public class AutomationControlFactory {
	
	/**
	 * Returns an Automation Control for the given element.
	 * 
	 * @param parent the parent control of the new automation control (Only relevant for menuBars).
	 * @param element The Element for which to create a specific control object.
	 * @return The AutomationBase child which best describes the given element.
	 * @throws AutomationException Automation library error.
	 * @throws PatternNotFoundException Expected pattern not found.
	 */
	public static AutomationBase get(final AutomationBase parent,
									 final Element element)
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
	 * @param element The Element for which to create a specific control object.
	 * @return The AutomationBase child which best describes the given element.
	 * @throws AutomationException Automation library error.
	 * @throws PatternNotFoundException Expected pattern not found.
	 */
	static AutomationBase get(final AutomationBase parent,
							  final ControlType controlType,
							  final Element element)
            throws AutomationException, PatternNotFoundException {

    	String className = element.getClassName();
    	switch (controlType) {
    	case Button:
    		return new Button(new ElementBuilder(element));
    	case Calendar:
    		return new Calendar(new ElementBuilder(element));
    	case CheckBox:
    		return new CheckBox(new ElementBuilder(element));
    	case ComboBox:
    		return new ComboBox(new ElementBuilder(element));
    	case Edit:
			if (PasswordEditBox.CLASS_NAME.equals(className)) {
				return new PasswordEditBox(new ElementBuilder(element));
			} else if (MaskedEdit.CLASS_NAME.equals(className)) {
				return new MaskedEdit(new ElementBuilder(element));
			}
    		return new EditBox(new ElementBuilder(element));
    	case Hyperlink:
    		return new Hyperlink(new ElementBuilder(element));
    	case Image:
    		return new Image(new ElementBuilder(element));
    	case ListItem:
    		return new ListItem(new ElementBuilder(element));
    	case List:
    		return new List(new ElementBuilder(element));
    	case Menu:
    		return new Menu(new ElementBuilder(element));
    	case MenuBar:
    		return new MainMenu(new ElementBuilder(element).parent(parent.getElement()));
    	case MenuItem:
    		return new MenuItem(new ElementBuilder(element));
    	case ProgressBar:
    		return new ProgressBar(new ElementBuilder(element));
    	case RadioButton:
    		return new RadioButton(new ElementBuilder(element));
    	case Slider:
    		return new Slider(new ElementBuilder(element));
    	case Spinner:
    		return new Spinner(new ElementBuilder(element));
    	case StatusBar:
    		return new StatusBar(new ElementBuilder(element));
    	case Tab:
    		return new Tab(new ElementBuilder(element));
    	case TabItem:
    		return new TabItem(new ElementBuilder(element));
    	case Text:
    		return new TextBox(new ElementBuilder(element));
    	case ToolBar:
    		return new ToolBar(new ElementBuilder(element));
    	case Tree:
    		return new TreeView(new ElementBuilder(element));
    	case TreeItem:
    		return new TreeViewItem(new ElementBuilder(element));
    	case Custom:
    		return new Custom(new ElementBuilder(element));
    	case DataGrid:
    		return new DataGrid(new ElementBuilder(element));
    	case Document:
    		return new Document(new ElementBuilder(element));
    	case SplitButton:
    		return new SplitButton(new ElementBuilder(element));
    	case Window:
    		return new Window(new ElementBuilder(element));
    	case Pane:
			switch (className) {
				case ReBar.CLASS_NAME:
					return new ReBar(new ElementBuilder(element));
				case RibbonBar.CLASS_NAME:
					return new RibbonBar(new ElementBuilder(element));
				case RibbonCommandBar.CLASS_NAME:
					return new RibbonCommandBar(new ElementBuilder(element));
				case RibbonWorkPane.CLASS_NAME:
					return new RibbonWorkPane(new ElementBuilder(element));
				case NUIPane.CLASS_NAME:
					return new NUIPane(new ElementBuilder(element));
				case NetUIHWND.CLASS_NAME:
					return new NetUIHWND(new ElementBuilder(element));
			}
    		return new Panel(new ElementBuilder(element));
    	case TitleBar:
    		return new TitleBar(new ElementBuilder(element));
    	case AppBar:
    		return new AppBar(new ElementBuilder(element));
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
            return new Container(new ElementBuilder(element));
    	}
    }
}