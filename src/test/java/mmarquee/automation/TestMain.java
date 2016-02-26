package mmarquee.automation;

import mmarquee.automation.uiautomation.ToggleState;

import java.util.List;

/**
 * Created by inpwt on 26/02/2016.
 */
public class TestMain {

    public void run () {
        UIAutomation automation = new UIAutomation();

        AutomationApplication application = null;

        try {
            application = automation.launchOrAttach("apps\\Project1.exe");
        } catch (Throwable ex) {
            // Smother????
        }

        // Wait for the process to start
        application.waitForInputIdle(5000);

        AutomationWindow window = automation.getDesktopWindow("Form1");
        String name = window.name();

        // Interact with menus
        AutomationMainMenu menu = window.getMainMenu();
//		List<AutomationMenuItem> items = menu.getItems();

//		AutomationMenuItem item = items.get(0);
//		item.expand();

//		String name1 = item.name();

        AutomationMenuItem exit = menu.getMenuItem("File", "Exit");
        exit.click();

        AutomationWindow popup = window.getWindow("Project1");
        AutomationButton btn = popup.getButton("OK");

        btn.click();

        AutomationTab tab = window.getTab(0);
        tab.selectTabPage("Last Tab");
        //	String tabName = tab.name();

        String text = tab.getEditBox(0).getValue();

        AutomationCheckbox check = window.getCheckbox(0);
        check.toggle();

        ToggleState state = check.getToggleState();

        AutomationRadioButton radio = window.getRadioButton(1);
        radio.selectItem();

        AutomationStatusBar statusbar = window.getStatusBar();
        //	String eb0Text = statusbar.getTextBoxByIndex(0).getValue();
        //	String eb1Text = statusbar.getTextBoxByIndex(1).getValue();

        AutomationComboBox cb1 = window.getCombobox("AutomatedCombobox1");
        cb1.setText("Replacements");
        String txt = cb1.text();

        AutomationComboBox cb0 = window.getCombobox("AutomatedCombobox2");
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
        AutomationStringGrid grid = window.getStringGrid(0);

        AutomationStringGridItem item1 = grid.getItem(0, 0);

        String itemName = item1.name();
        item1.setName("This");

        AutomationTreeView tree = window.getTreeView(0);
        try {
            AutomationTreeViewItem treeItem = tree.getItem("Sub-SubItem");
            treeItem.select();
        } catch (ItemNotFoundException ex) {
            // Not found
        }

        AutomationList list = window.getListItem(0);
        try {
            AutomationListItem listItem = list.getItem("First (List)");
            listItem.select();
        } catch (ItemNotFoundException ex) {
            // Not found
        }
    }
}
