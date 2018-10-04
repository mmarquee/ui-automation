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

package mmarquee.demo;

import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.ControlType;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.controls.menu.AutomationMenuItem;
import mmarquee.automation.controls.mouse.AutomationMouse;
import mmarquee.automation.uiautomation.RowOrColumnMajor;
import mmarquee.automation.uiautomation.ToggleState;
import mmarquee.automation.utils.Utils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Test the automation wrapper on a WPF application.
 *
 * @author Mark Humphreys
 * Date 26/02/2016.
 */
public class TestMainWPF extends TestBase {

    public final void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            application =
                    automation.launchOrAttach(
                            "apps\\SampleWpfApplication.exe");
        } catch (Throwable ex) {
            logger.warn("Failed to find application", ex);
        }

        // Wait for the process to start
        // This doesn't seem to wait for WPF examples
        assert application != null;
        application.waitForInputIdle(AutomationApplication.SHORT_TIMEOUT);

        // Sleep for WPF, to address above issue
        this.rest();

        AutomationWindow applicationWindow = null;

        try {
            applicationWindow = automation.getDesktopWindow("MainWindow");

//            applicationWindow = automation.get("MainWindow");
        } catch (Exception ex) {
            logger.info("Failed to find `MainWindow`");
        }

        try {
            /*
            logger.info("++ KILLED ++");

            application.quit("MainWindow");

            logger.info("++ KILLED ++");

            application =
                automation.launchOrAttach("apps\\SampleWpfApplication.exe");
            */

            assert applicationWindow != null;
            Object framework = applicationWindow.getFramework();
            logger.info("Framework is " + framework.toString());

            Object id = applicationWindow.getProcessId();
            logger.info("Process = " + id.toString());

            WinDef.POINT point = applicationWindow.getClickablePoint();
            logger.info("Clickable point = " + point.toString());

            String name = applicationWindow.getName();
            logger.info(name);

            try {
                boolean val = applicationWindow.isModal();

                logger.info(val);
            } catch (Exception ex) {
                logger.info("Ouch");
            }

            // Interact with menus

            logger.info("++ MENUS ++");

            AutomationMainMenu menu = applicationWindow.getMainMenu(0);

            logger.info("Menu name " + menu.getName());

            logger.info(menu.getItems().size() + " menu items");

            logger.info(menu.getItems().get(0).getName());

            // Actual program menu is a `Menu`

            AutomationMainMenu mainMenu = applicationWindow.getMenu();
            logger.info("Menu name " + mainMenu.getName());

            logger.info(mainMenu.getItems().size() + " menu items");

            logger.info(mainMenu.getItems().get(0).getName());

            // WPF menus seem to be different from Delphi VCL windows
            // AutomationMainMenu menu = window.getMenu();
            AutomationMenuItem file = mainMenu.getItems().get(0);
            file.expand();

            this.rest();
            
            logger.info("Items = " + file.getItems().size());

            AutomationMenuItem exit = file.getItems().get(3);

            exit.click();

            try {
                AutomationWindow popup =
                        applicationWindow.getWindow(
                                Search.getBuilder("Confirm Exit").build());

                AutomationButton btn = popup.getButton(
                        Search.getBuilder(Pattern.compile("Cancel|Abbrechen")).build());

                boolean val1 = popup.isModal();

                logger.info("Modal? " + val1);

                try {
                    Utils.captureScreen("TestMainWPF.png");
                } catch (Exception ex) {
                    // Should capture each exception
                    logger.info("Failed to capture screen for some reason");
                }

                btn.click();
            } catch (ItemNotFoundException ex) {
                logger.info("Failed to find popup");
            }

            // Get and set an edit box by index (WPF doesn't care about
            // control names)

            logger.info("++ TAB ++");

            AutomationTab tab = applicationWindow.getTab(
                    Search.getBuilder(0).build());

            tab.selectTabPage("Details");

            String text = applicationWindow.getEditBox(
                    Search.getBuilder(1).build()).getValue();
            logger.info("Text for edit box 1 is " + text);

            applicationWindow.getEditBox(
                    Search.getBuilder(1).build()).setValue("Hi");
            logger.info("Text for edit box 1 is now " +
                    applicationWindow.getEditBox(
                            Search.getBuilder(1).build()).getValue());

            // CHECK BOX *********************************************

            logger.info("++ CHECK BOX ++");

            AutomationCheckBox check =
                    applicationWindow.getCheckBox(
                            Search.getBuilder(0).build());
            check.toggle();
            try {
                ToggleState state = check.getToggleState();
                logger.info(state.toString());
            } catch (Exception ex) {
                logger.info("Failed to get toggle state");
            }

            logger.info("++ IUIAUTOMATIONELEMENT6 stuff");

            logger.info("Description is " + check.getDescription());

            logger.info("++ Experimental GENERIC ++");

            /* Only seems to work on one PC at the moment. */

            AutomationCheckBox cb =
                    applicationWindow.get(AutomationCheckBox.class,
                        ControlType.CheckBox,
                            "Enable feature WWW");
            cb.toggle();
            try {
                ToggleState state = cb.getToggleState();
                logger.info(state.toString());
            } catch (Exception ex) {
                logger.info("Failed to get toggle state");
            }

            // RADIO BUTTON *********************************************

            logger.info("++ RADIO BUTTON ++");

            AutomationRadioButton radio =
                    applicationWindow.getRadioButton(
                            Search.getBuilder(1).build());
            radio.select();

            // TEXT BOX *********************************************

            logger.info("++ TEXT BOX ++");

            AutomationTextBox tb0 =
                    applicationWindow.getTextBox(
                            Search.getBuilder(9).build());
            String tb0Text = tb0.getValue();
            logger.info("Text for text box 1 is " + tb0Text);

            AutomationTextBox tb1 =
                    applicationWindow.getTextBox(
                            Search.getBuilder(18).build());
            String tb1Text = tb1.getValue();
            logger.info("Text for text box 1 is " + tb1Text);

            // PROGRESS BAR *********************************************

            logger.info("++ PROGRESS BAR ++");

            AutomationProgressBar progress =
                    applicationWindow.getProgressBar(
                            Search.getBuilder(0).build());
            logger.info("Progress = " + progress.getRangeValue());

            // Looks like this does bad things
            //  progress.setRangeValue(100.0);
            //  logger.info("Progress is now = " + progress.getRangeValue());

            // SLIDER *********************************************

            logger.info("++ SLIDER ++");

            AutomationSlider slider =
                    applicationWindow.getSlider(
                            Search.getBuilder(0).build());
            logger.info("Slider value = " + slider.getRangeValue());

            // Looks like this does bad things too
            //       progress.setRangeValue(25);
            //       logger.info("Progress is now = " + progress.getRangeValue());

            // Status bar *********************************************

            logger.info("++ STATUS BAR ++");

            AutomationStatusBar statusbar = applicationWindow.getStatusBar();

            AutomationTextBox tb =
                    statusbar.getTextBox(
                            Search.getBuilder(0).build());

            String ebText = tb.getValue();

            logger.info("Statusbar text = " + ebText);

            // Now make something happen in the statusbar
            AutomationEditBox sbeb =
                    applicationWindow.getEditBox(
                            Search.getBuilder(0).build());
            logger.info(sbeb.getValue());
            sbeb.setValue("Some text");

            logger.info("Statusbar text = " + tb.getValue());

            // COMBOBOX *********************************************

            logger.info("++ COMBO BOX ++");

            try {
                AutomationComboBox cb0 =
                        applicationWindow.getComboBox(
                                Search.getBuilder(0).build());

// NPE thrown here
//                String txt = cb0.text();
//                logger.info("Text for Combobox is `" + txt + "`");
            } catch (ElementNotFoundException ex) {
                logger.error("Failed to find element");
            }

            // EDITTABLE COMBOBOX ************************************

            logger.info("++ EDITTABLE COMBOBOX ++");

            try {
                AutomationComboBox cb1 =
                        applicationWindow.getComboBox(
                                Search.getBuilder(1).build());

                String txt = cb1.getValue();

                logger.info("Text for Combobox is `" + txt + "`");

                cb1.setText("Here we are");
                logger.info("Text for Combobox is now `" + cb1.getValue() + "`");

            } catch (ElementNotFoundException ex) {
                logger.error("Failed to find element");
            }

            // DATAGRIDS ***********************************************************

            logger.info("++ DATAGRIDS ++");

            // These are entirely different beasts in WPF, but look the same to us!

            // Now string grids
            AutomationDataGrid grid =
                    applicationWindow.getDataGrid(
                            Search.getBuilder(0).build());

            AutomationDataGridCell cell1 =
                    grid.getItem(
                            Search.getBuilder(1, 1).build());

            String itemName = cell1.getName();
            logger.info("Grid item is " + itemName);
//            cell1.setName("This");
//            logger.info("Grid item is " + cell1.name());

            RowOrColumnMajor rowOrColumn = grid.getRowOrColumnMajor();

            logger.info("Row or Column: " + rowOrColumn);

            List<AutomationDataGridCell> headers = grid.getColumnHeaders();

            for (AutomationDataGridCell cell : headers) {
                logger.info(cell.getName());
            }

            logger.info(grid.getColumnHeader(1).getName());

            List<AutomationDataGridCell> cols = grid.getColumn(1);
            for (AutomationDataGridCell cell : cols) {
                logger.info("Col 1 - " + cell.getName());
            }

            // TREEVIEW **************************

            logger.info("++ TREEVIEW ++");

            AutomationTreeView tree =
                    applicationWindow.getTreeView(
                            Search.getBuilder(0).build());
            try {
                AutomationTreeViewItem treeItem =
                        tree.getItem(
                                Search.getBuilder("Level 2.2").build());
                treeItem.select();

                logger.info("Item is " + treeItem.getName());

            } catch (ItemNotFoundException ex) {
                logger.info("Failed to find item");
            } catch (ElementNotFoundException ex) {
                logger.info("Failed to find element");
            }

            // BUTTONS ***********************************

            logger.info("++ BUTTONS ++");

            // NOTE: WPF buttons will set the automationID to be the
            // name of the control

            AutomationButton btnClickMe =
                    applicationWindow.getButton(
                            Search.getBuilder()
                                    .automationId("btnClickMe").build());
            logger.info(btnClickMe.getName());
            btnClickMe.click();

            // LISTS ****************************************

            logger.info("++ LISTS ++");

            AutomationList list =
                    applicationWindow.getList(
                            Search.getBuilder(0).build());
            try {
                AutomationListItem listItem =
                        list.getItem("Hello, Window world!");
                listItem.select();
                logger.info(listItem.getName());

                // Now find by index
                AutomationListItem listItem0 = list.getItem(0);
                listItem0.select();
                logger.info("0th element is " + listItem0.getName());

            } catch (ItemNotFoundException ex) {
                logger.info("Didn't find item");
            } catch (ElementNotFoundException ex) {
                logger.info("Didn't find element");
            }

            try {
                AutomationListItem item = list.getSelectedItem();
            } catch (AutomationException ex) {
                logger.info(ex.getMessage());
            }

            // HYPERLINK ***********************************
            /*
            logger.info("++ HYPERLINK ++");

            AutomationHyperlink link = applicationWindow.getHyperlink(0);
            link.click();
            */

            // TOOLBAR ***********************************

            AutomationToolBar toolbar =
                    applicationWindow.getToolBar(
                            Search.getBuilder(0).build());

            // Blank in default WPF
            logger.info("Toolbar name is " + toolbar.getName());

            AutomationButton btn1 = toolbar.getButton(
                    Search.getBuilder(1).build());

            if (btn1.isEnabled()) {
                logger.info("btn0 Enabled");
                logger.info(btn1.getName());
                btn1.click();

                logger.info("Clicked btn1");

                this.rest();

                // Now cope with the results of the click
                try {
                    logger.info("Looking for `New Thing`");
                    AutomationWindow popup1 =
                            applicationWindow.getWindow(
                                    Search.getBuilder("New Thing").build());
                    logger.info("Looking for `OK` btn");
                    AutomationButton okBtn =
                            popup1.getButton(
                                    Search.getBuilder("OK").build());

                    boolean val2 = popup1.isModal();

                    logger.info("Modal - " + val2);

                    okBtn.click();
                } catch (ItemNotFoundException ex) {
                    logger.info("Failed to find window");
                }
            }

            // CALENDAR ***********************************

            logger.info("++ CALENDAR ++");

            tab.selectTabPage("Calendar");

            AutomationCalendar calendar =
                    applicationWindow.getCalendar(
                            Search.getBuilder(0).build());

            logger.info("Date is " + calendar.getName());

            // Not sure what we can get out of a calendar

            // DOCUMENT *********************************************

            logger.info("++ DOCUMENT ++");

            tab.selectTabPage("Document");

            AutomationDocument document =
                    applicationWindow.getDocument(
                            Search.getBuilder(0).build());

            document.showContextMenu();

            logger.info("Document name is `" + document.getName() + "`");

            logger.info("Text is " + document.getText());


            String result = document.getSelection();

            logger.info("Selection is " + result);

            // PASSWORD EDITBOX **********************************

            logger.info("++ PASSWORD EDITBOX ++");

            AutomationEditBox passwd =
                    applicationWindow.getPasswordEditBox(
                            Search.getBuilder(0).build());
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

            logger.info("Investigated the cache");
            */

            logger.info("++ MISC ++");

            logger.info("Provider Description:" +
                    applicationWindow.getProviderDescription());
            logger.info("Handle: " + applicationWindow.getNativeWindowHandle());

            WinDef.RECT rect = applicationWindow.getBoundingRectangle();

            logger.info("Rect: " + rect);

            logger.info("ARIA role : " + applicationWindow.getAriaRole());
            try {
                logger.info("Orientation: " +
                        applicationWindow.getOrientation().toString());
            } catch (Exception ex) {
                logger.info("Failed to get orientation");

            }

            logger.info("Item Status: " + applicationWindow.getItemStatus());
            logger.info("FrameworkId: " + applicationWindow.getFrameworkId());

            // TITLEBAR ****************************************

            logger.info("++ TITLEBAR ++");

            AutomationTitleBar titleBar = applicationWindow.getTitleBar();

            // Title bar seems to not give back a name now
            logger.info("TitleBar name is " + titleBar.getName());

//            AutomationMainMenu menuBar = titleBar.getMenuBar();

//            AutomationButton btnMin = titleBar.getButton("Minimize");
    //        AutomationButton btnMax = titleBar.getButton(1);
    //        AutomationButton btnClose = titleBar.getButton(2);

//            AutomationButton genericButton = titleBar.get1(AutomationButton.class, ControlType.Button, "Minimize");

//            logger.info("`Generic` " + genericButton.name());

  //          logger.info("`Specific` " + btnMin.name());
      //      logger.info(btnMax.name());
      //      logger.info(btnClose.name());

            // Right-click ****************************************
            logger.info("++ CONTEXT MENU ++");

            AutomationButton rightClickBtn =
                    applicationWindow.getButton(
                            Search.getBuilder("Right-click me!").build());
            rightClickBtn.showContextMenu();

            // Right-click ****************************************
            logger.info("++ RIGHTCLICK ++");

            AutomationMouse mouse = AutomationMouse.getInstance();

            rightClickBtn = applicationWindow.getButton(
                    Search.getBuilder("Right-click me!").build());

            // Still issues with get locations out of some controls

            WinDef.POINT clickPoint = rightClickBtn.getClickablePoint();

            logger.info(clickPoint.x);
            logger.info(clickPoint.y);

            WinDef.RECT rect0 = rightClickBtn.getBoundingRectangle();

            WinDef.POINT clickPoint1 =
                    new WinDef.POINT(rect0.left + 5, rect0.top + 5);

            mouse.setLocation(clickPoint1.x, clickPoint1.y);
            mouse.rightClick();

            this.rest();

            // Should be able to get the popup menu here, if I knew
            // how to find it

            logger.info("++ NOT FOUND ++");

            // Window / element not found
            try {
                logger.info("Looking for `Not There`");
                applicationWindow.getWindow(
                        Search.getBuilder("Not there").build());
                logger.info("Found `Not There` somehow!");
            } catch (ElementNotFoundException ex) {
                logger.info("Didn't find element `Not There`");
            } catch (Exception ex) {
                logger.info(ex.toString());
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

            logger.info("Looking for a non-existent desktop window");

            // Same for desktop window
            try {
                automation.getDesktopWindow("MainWindow99", 2);
            } catch (AutomationException ex) {
                logger.info("Failed to find `MainWindow99` - " + ex.getClass());
            }

            logger.info("Looking for a non-existent desktop object");

            // .. and object
            try {
                automation.getDesktopObject("MainWindow00", 2);
            } catch (AutomationException ex) {
                logger.info("Failed to find `MainWindow00` - " + ex.getClass());
            }

            logger.info("++ ALL DONE ++");

        } catch (AutomationException ex) {
            logger.info("Something went wrong - " + ex.getClass());
            ex.printStackTrace();
        }
    }
}
