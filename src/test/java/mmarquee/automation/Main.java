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

import mmarquee.automation.uiautomation.ToggleState;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		UIAutomation automation = new UIAutomation();

		AutomationApplication application = null;

		try {
			application = automation.launchOrAttach("C:\\Users\\inpwt\\Desktop\\DelphiUIAutomation\\demo\\democlient\\Win32\\Debug\\Project1.exe");
		} catch (Throwable ex) {
			// Smother
		}

		// Wait for the process to start
		application.waitForInputIdle(5000);

		AutomationWindow window = automation.getDesktopWindow("Form1");
		String name = window.name();

		// Interact with menus
		AutomationMainMenu menu = window.getMainMenu();

		List<AutomationMenuItem> items = menu.getItems();

		AutomationMenuItem item = items.get(0);
		item.expand();
		String name1 = item.name();
		List<AutomationMenuItem> subItems = item.getItems();

/*
		AutomationMenuItem file = items.get(0);
		file.expand();
        file.collapse();
        try {
            Thread.sleep(750);                 //750 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

		AutomationMenuItem exit = file.getMenuItem("Exit1");
		exit.click();
*/

		AutomationTab tab = window.getTabByIndex(0);
		tab.selectTabPage("Last Tab");
	//	String tabName = tab.name();

		String text = tab.getEditBoxByIndex(0).getValue();

		AutomationCheckbox check = window.getCheckboxByIndex(0);
		check.toggle();

		ToggleState state = check.getToggleState();

		AutomationRadioButton radio = window.getRadioButtonByIndex(1);
		radio.selectItem();

		AutomationStatusBar statusbar = window.getStatusBar();
	//	String eb0Text = statusbar.getTextBoxByIndex(0).getValue();
	//	String eb1Text = statusbar.getTextBoxByIndex(1).getValue();

	AutomationComboBox cb1 = window.getComboboxByName("AutomatedCombobox1");
    cb1.setText("Replacements");
	String txt = cb1.text();

	AutomationComboBox cb0 = window.getComboboxByName("AutomatedCombobox2");
	cb0.expand();
	try {
		cb0.wait(750);
	} catch (Exception ex) {
		// Time out
	}
	List<AutomationListItem> litems = cb0.getList();

		String dummy = "";
/*
		AutomationButton button1 = window.getButtonByName("OK");
		button1.click();

		AutomationButton button2 = window.getButtonByName("Cancel");
		button2.click();
*/
		// Now string grids
		AutomationStringGrid grid = window.getStringGridByIndex(0);

		AutomationStringGridItem item1 = grid.getItem(0,0);

		String itemName = item1.name();
		item1.setName("This");

		AutomationTreeView tree = window.getTreeViewByIndex(0);
		AutomationTreeViewItem treeItem = tree.getItem("Sub-SubItem");
		treeItem.select();
	}
} 