package mmarquee.automation;

import mmarquee.automation.uiautomation.ToggleState;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		UIAutomation automation = new UIAutomation();

		AutomationApplication application = automation.launch("C:\\Users\\inpwt\\Desktop\\DelphiUIAutomation\\DelphiUIAutomation\\demo\\democlient\\Win32\\Debug\\Project1.exe");

		application.waitWhileBusy();

		AutomationWindow window = automation.getDesktopWindow("Form1");
		String name = window.name();
	//	window.focus();

		AutomationTab tab = window.getTabByIndex(0);
		tab.selectTabPage("Last Tab");
	//	String tabName = tab.name();

		String text = tab.getEditBoxByIndex(0).text();

		AutomationCheckbox check = window.getCheckboxByIndex(0);
		check.toggle();

		ToggleState state = check.getToggleState();

		AutomationRadioButton radio = window.getRadioButtonByIndex(1);
		radio.selectItem();

		AutomationStatusBar statusbar = window.getStatusBar();
		String eb0Text = statusbar.getTextBoxByIndex(0).text();
		String eb1Text = statusbar.getTextBoxByIndex(1).text();

		AutomationComboBox cb1 = window.getComboboxByName("AutomatedCombobox1");
		cb1.setText("Replacements");
		String txt = cb1.text();

//		AutomationComboBox cb0 = window.getComboboxByName("ComboBox1");
//		List<AutomationListItem> items = cb0.getList();

		String dummy = "";
/*
		AutomationButton button1 = window.getButtonByName("OK");
		button1.click();

		AutomationButton button2 = window.getButtonByName("Cancel");
		button2.click();
*/
		// Now string grids
		AutomationStringGrid grid = window.getStringGridByIndex(0);

		AutomationStringGridItem item = grid.getItem(0,0);

		String itemName = item.name();
	}
} 