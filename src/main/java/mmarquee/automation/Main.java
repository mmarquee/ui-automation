package mmarquee.automation;

import mmarquee.automation.uiautomation.ToggleState;

import java.io.Console;

public class Main {

	public static void main(String[] args) {

		UIAutomation automation = new UIAutomation();

		IAutomationApplication application = automation.launch("C:\\Users\\inpwt\\Desktop\\DelphiUIAutomation\\DelphiUIAutomation\\demo\\democlient\\Win32\\Debug\\Project1.exe");

		application.waitWhileBusy();

		IAutomationWindow window = automation.getDesktopWindow("Form1");
		String name = window.name();
	//	window.focus();

		IAutomationTab tab = window.getTabByIndex(0);
		tab.selectTabPage("Last Tab");
	//	String tabName = tab.name();

		IProvidesText eb1;
		eb1 = tab.getEditBoxByIndex(0);

		String text = eb1.text();

		IAutomationCheckbox check = window.getCheckboxByIndex(0);
		check.toggle();

		ToggleState state = check.getToggleState();

		IAutomationRadioButton radio = window.getRadioButtonByIndex(1);
		radio.selectItem();

		IAutomationStatusBar statusbar = window.getStatusBar();
		IProvidesText tb0 = statusbar.getTextBoxByIndex(0);
		IProvidesText tb1 = statusbar.getTextBoxByIndex(1);
		String eb0Text = tb0.text();
		String eb1Text = tb1.text();

		IAutomationComboBox cb1 = window.getComboboxByName("AutomatedCombobox1");
		cb1.setText("Replacements");

		String txt = cb1.text();

		String dummy = "";
	}
} 