package mmarquee.automation;

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

		IAutomationEditBox tb1 = tab.setEditBoxByIndex(0);

		String text = tb1.text();

		//List<IAutomationWindow> list = automation.getDesktopWindows();
	}
} 