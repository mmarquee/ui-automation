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

import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.controls.*;
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.controls.menu.AutomationMenuItem;
import mmarquee.automation.controls.mouse.AutomationMouse;
import mmarquee.automation.uiautomation.ToggleState;

import java.util.List;

/**
 * Created by inpwt on 26/02/2016.
 *
 * Test the automation wrapper on a WPF application.
 */
public class TestMainWPF extends TestBase {

    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            application = automation.launchOrAttach("apps\\SampleWpfApplication.exe");
        } catch (Throwable ex) {
            logger.warn("Failed to find application", ex);
        }

        // Wait for the process to start
        // This doesn't seem to wait for WPF examples
        application.waitForInputIdle(5000);

        // Sleep for WPF, to address above issue
        this.rest();

        AutomationWindow window = null;

        try {
            window = automation.getDesktopWindow("MainWindow");
        } catch (ElementNotFoundException ex) {
            logger.info("Failed to find `MainWindow`");
        }

        try {

            Object framework = window.getFramework();
            logger.info("Framework is " + framework.toString());

            Object id = window.getProcessId();
            logger.info("Process = " + id.toString());

            WinDef.POINT point = window.getClickablePoint();
            logger.info("Clickable point = " + point.toString());

            String name = window.name();
            logger.info(name);

            // Works to here!!!!

            boolean val = window.isModal();

            // Interact with menus
            AutomationMainMenu menu = window.getMainMenu(0);

            logger.info("Menu name " + menu.name());

            logger.info(menu.getItems().size() + " menu items");

            logger.info(menu.getItems().get(0).name());

            // Actual program menu is a `Menu`

            AutomationMainMenu mainMenu = window.getMenu(0);
            logger.info("Menu name " + mainMenu.name());

            logger.info(mainMenu.getItems().size() + " menu items");

            logger.info(mainMenu.getItems().get(0).name());

//            AutomationMainMenu menu = window.getMenu();   // WPF menus seem to be different from Delphi VCL windows

            AutomationMenuItem file = mainMenu.getItems().get(0);
            file.expand();

            this.rest();

            logger.info("Items = " + file.getItems().size());

            AutomationMenuItem exit = file.getItems().get(3);

            exit.click();

            try {
                AutomationWindow popup = window.getWindow("Confirm Exit");

                AutomationButton btn = popup.getButton("Cancel");

                boolean val1 = popup.isModal();

                logger.info("Modal? " + val1);

                try {
                    automation.captureScreen("TestMainWPF.png");
                } catch (Exception ex) {
                    // Should capture each exception
                    logger.info("Failed to capture screen for some reason");
                }

                btn.click();
            } catch (ItemNotFoundException ex) {
                logger.info("Failed to find popup");
            }

            // Get and set an edit box by index (WPF doesn't care about control names)

            AutomationTab tab = window.getTab(0);
            tab.selectTabPage("Details");

            String text = window.getEditBox(1).getValue();
            logger.info("Text for edit box 1 is " + text);

            window.getEditBox(1).setValue("Hi");
            logger.info("Text for edit box 1 is now " + window.getEditBox(1).getValue());

            // CHECK BOX *********************************************

            AutomationCheckbox check = window.getCheckbox(0);
            check.toggle();
            try {
                ToggleState state = check.getToggleState();
            } catch (Exception ex) {
                logger.info("Failed to get toggle state");
            }

            // RADIO BUTTON *********************************************

            AutomationRadioButton radio = window.getRadioButton(1);
            radio.selectItem();

            // TEXT BOX *********************************************

            AutomationTextBox tb0 = window.getTextBox(9);
            String tb0Text = tb0.getValue();
            logger.info("Text for text box 1 is " + tb0Text);

            AutomationTextBox tb1 = window.getTextBox(18);
            String tb1Text = tb1.getValue();
            logger.info("Text for text box 1 is " + tb1Text);

            // PROGRESS BAR *********************************************

            AutomationProgressBar progress = window.getProgressBar(0);
            logger.info("Progress = " + progress.getRangeValue());

            // Looks like this does bad things
            //  progress.setRangeValue(100.0);
            //  logger.info("Progress is now = " + progress.getRangeValue());

            // SLIDER *********************************************

            AutomationSlider slider = window.getSlider(0);
            logger.info("Slider value = " + slider.getRangeValue());

            // Looks like this does bad things too
            //       progress.setRangeValue(25);
            //       logger.info("Progress is now = " + progress.getRangeValue());

            // Status bar *********************************************

            AutomationStatusBar statusbar = window.getStatusBar();

            AutomationTextBox tb = statusbar.getTextBox(0);

            String ebText = tb.getValue();

            logger.info("Statusbar text = " + ebText);

            // Now make something happen in the statusbar
            AutomationEditBox sbeb = window.getEditBox(0);
            logger.info(sbeb.getValue());
            sbeb.setValue("Some text");

            logger.info("Statusbar text = " + tb.getValue());

            // COMBOBOX *********************************************

            try {
                AutomationComboBox cb0 = window.getCombobox(0);

// NPE thrown here
//                String txt = cb0.text();
//                logger.info("Text for Combobox is `" + txt + "`");
            } catch (ElementNotFoundException ex) {
                logger.error("Failed to find element");
            }

            // EDITTABLE COMBOBOX ************************************

            try {
                AutomationComboBox cb1 = window.getCombobox(1);

                String txt = cb1.text();

                logger.info("Text for Combobox is `" + txt + "`");

                cb1.setText("Here we are");
                logger.info("Text for Combobox is now `" + cb1.text() + "`");

            } catch (ElementNotFoundException ex) {
                logger.error("Failed to find element");
            }

            // DATAGRIDS ***********************************************************

            // These are entirely different beasts in WPF, but look the same to us!

            // Now string grids
            AutomationDataGrid grid = window.getDataGrid(0);

            AutomationDataGridCell cell1 = grid.getItem(1, 1);

            String itemName = cell1.name();
            logger.info("Grid item is " + itemName);
//            cell1.setName("This");
//            logger.info("Grid item is " + cell1.name());

            List<AutomationDataGridCell> headers = grid.getColumnHeaders();

            for(AutomationDataGridCell cell : headers) {
                logger.info(cell.name());
            }

            // TREEVIEW **************************

            AutomationTreeView tree = window.getTreeView(0);
            try {
                AutomationTreeViewItem treeItem = tree.getItem("Level 2.2");
                treeItem.select();

                logger.info("Item is " + treeItem.name());

            } catch (ItemNotFoundException ex) {
                logger.info("Failed to find item");
            } catch (ElementNotFoundException ex) {
                logger.info("Failed to find element");
            }

            // BUTTONS ***********************************

            // NOTE: WPF buttons will set the automationID to be the name of the control

            AutomationButton btnClickMe = window.getButtonByAutomationId("btnClickMe");
            logger.info(btnClickMe.name());
            btnClickMe.click();

            // LISTS ****************************************

            AutomationList list = window.getListItem(0);
            try {
                AutomationListItem listItem = list.getItem("Hello, Window world!");
                listItem.select();
                logger.info(listItem.name());

                // Now find by index
                AutomationListItem listItem0 = list.getItem(0);
                listItem0.select();
                logger.info("0th element is " + listItem0.name());

            } catch (ItemNotFoundException ex) {
                logger.info("Didn't find item");
            } catch (ElementNotFoundException ex) {
                logger.info("Didn't find element");
            }

            // HYPERLINK ***********************************

            AutomationHyperlink link = window.getHyperlink(0);
            link.click();

            AutomationToolBar toolbar = window.getToolBar(0);
            logger.info("Toolbar name is " + toolbar.name()); // Blank in default WPF

            AutomationButton btn1 = toolbar.getButton(1);

            if (btn1.isEnabled()) {
                logger.info("btn0 Enabled");
                logger.info(btn1.name());
                btn1.click();

                // Now cope with the results of the click
                try {
                    AutomationWindow popup1 = window.getWindow("New Thing");

                    AutomationButton okBtn = popup1.getButton("OK");

                    boolean val2 = popup1.isModal();

                    logger.info("Modal - " + val2);

                    okBtn.click();
                } catch (ItemNotFoundException ex) {
                    logger.info("Failed to find window");
                }
            }

            // CALENDAR ***********************************

            tab.selectTabPage("Calendar");

            AutomationCalendar calendar = window.getCalendar(0);

            logger.info("Date is " + calendar.name());

            // Not sure what we can get out of a calendar

            // DOCUMENT *********************************************

            tab.selectTabPage("Document");

            AutomationDocument document = window.getDocument(0);

            logger.info("Document name is " + document.name());

            // TITLEBAR ****************************************

            AutomationTitleBar titleBar = window.getTitleBar();
            logger.info("TitleBar name is " + titleBar.name());

            AutomationMainMenu menuBar = titleBar.getMenuBar();

            AutomationButton btnMin = titleBar.getButton(0);
            AutomationButton btnMax = titleBar.getButton(1);
            AutomationButton btnClose = titleBar.getButton(2);

            logger.info(btnMin.name());
            logger.info(btnMax.name());
            logger.info(btnClose.name());

            // PASSWORD EDITBOX **********************************
            AutomationEditBox passwd = window.getPasswordEditBox(0);
            passwd.setValue("Hello there everyone");

            logger.info("IsPassword = " + passwd.isPassword());
            // Can't get the text out of a password control, but probably shouldn't just crash.
            //   logger.info(passwd.getValue());
/*
            logger.info("Investigate the cache");

            CacheRequest cache = automation.createCacheRequest();
            cache.add(PropertyID.Name);
            cache.add(PropertyID.IsEnabled);
            cache.add(PropertyID.ControlType);
            cache.add(PatternID.SelectionItem);
            cache.setTreeScope(TreeScope.TreeScope_Children);

            TrueCondition condition = new TrueCondition();
            cache.setTreeFilter(condition);

            List<AutomationElement> elements = window.findAllBuildCache(
                    cache);

            if (elements == null) {
                logger.info("Cache seems to be empty");
            } else {
                logger.info(("Cached items : " + elements.size()));

                // See what is actually in the cache
                // Currently this causes a big crash
                for (AutomationElement element: elements) {
                    logger.info(": " + element.cachedName());
                }

            }
            */

            logger.info("Investigated the cache");

            logger.info("Provider Description:" + window.getProviderDescription());
            logger.info("Handle: " + window.getNativeWindowHandle());

            WinDef.RECT rect = window.getBoundingRectangle();

            logger.info("Rect: " + rect);

            logger.info("ARIA role : " + window.getAriaRole());
//            logger.info("Orientation: " + window.getOrientation().toString());
//            logger.info("Item Status: " + window.getItemStatus());
//            logger.info("FrameworkId: " + window.getFrameworkId());

            AutomationMouse mouse = AutomationMouse.getInstance();

            AutomationButton rightClickBtn = window.getButton("Right-click me!");

            //mouse.setLocation(1119, 896);
            WinDef.POINT clickPoint = rightClickBtn.getClickablePoint();

            mouse.setLocation(clickPoint.x, clickPoint.y);
            mouse.rightClick();

            this.rest();

            //window.dumpUI();

            // Should be able to get the popup menu here

            // Window / element not found
            try {
                AutomationWindow popupNotThere = window.getWindow("Not there");
            } catch (ItemNotFoundException ex) {
                logger.info("Failed to find window");
            }
/*
            // OK, lets have a look and event handlers
            EventHandler handler = new EventHandler();
            automation.addAutomationEventHandler(window.element.element,
                    EventID.Invoke_Invoked.getValue(),
                    TreeScope.TreeScope_Children,
                    handler);

            AutomationButton btn2 = window.getButton(1);
            btn2.click();

            automation.removeAutomationEventHandler(window.element.element,
                    EventID.Invoke_Invoked.getValue(),
                    handler);
*/
        } catch (ElementNotFoundException ex) {
            logger.info("Element Not Found ");
        }
    }
}
