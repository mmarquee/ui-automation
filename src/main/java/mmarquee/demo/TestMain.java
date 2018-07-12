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
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.AutomationException;
import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.controls.menu.AutomationMenu;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.controls.menu.AutomationMenuItem;
import mmarquee.automation.controls.mouse.AutomationMouse;
import mmarquee.automation.uiautomation.ToggleState;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.controls.AutomationTab;
import mmarquee.automation.controls.Search;
import mmarquee.automation.controls.AutomationButton;
import mmarquee.automation.controls.AutomationRadioButton;
import mmarquee.automation.controls.AutomationStatusBar;
import mmarquee.automation.controls.AutomationToolBarButton;
import mmarquee.automation.controls.AutomationCheckBox;
import mmarquee.automation.controls.AutomationTextBox;
import mmarquee.automation.controls.AutomationComboBox;
import mmarquee.automation.controls.AutomationToolBar;
import mmarquee.automation.controls.AutomationMaskedEdit;
import mmarquee.automation.controls.AutomationListItem;
import mmarquee.automation.controls.AutomationDataGrid;
import mmarquee.automation.controls.AutomationDataGridCell;
import mmarquee.automation.controls.AutomationTreeView;
import mmarquee.automation.controls.AutomationTreeViewItem;
import mmarquee.automation.controls.AutomationList;

import java.util.List;

/**
 * @author Mark Humphreys
 * Date 26/02/2016
 *
 * Test the automation wrapper on a Delphi VCL application.
 */
public class TestMain extends TestBase {

    public final void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            application =
                    automation.launchOrAttach("apps\\Project1.exe");
        } catch (Throwable ex) {
            logger.warn("Failed to find application", ex);
        }

        try {
            // Wait for the process to start
            assert application != null;
            application.waitForInputIdle(AutomationApplication.SHORT_TIMEOUT);
        } catch (Throwable ex) {
            logger.error("Failed to wait properly");
        }

        try {
            AutomationWindow window = automation.getDesktopWindow("Form1");
            String name = window.getName();
            logger.info(name);

            Object framework = window.getFramework();
            logger.info("Framework is " + framework.toString());

            logger.info("Modal? : " + window.isModal());

            logger.info("Offscreen? : " + window.isOffScreen());

            java.lang.Object rect = window.getBoundingRectangle();

            logger.info("Rect : " + rect.toString());

            // Interact with menus
            AutomationMainMenu menu = window.getMainMenu();

            try {
                AutomationMenuItem exit = menu.getMenuItem("File", "Exit");
                exit.click();

                try {
                    AutomationWindow popup =
                            window.getWindow(Search.getBuilder(
                                    "Project1").build());
                    Object val111 = popup.getBoundingRectangle();

                    AutomationButton btn =
                            popup.getButton(Search.getBuilder(
                                    "OK").build());

                    String aText = btn.getValueFromIAccessible();
                    logger.info("From ImplementsLegacyIAccessible: " + aText);

                    Object val11 = btn.getBoundingRectangle();

                    boolean val1 = popup.isModal();

                    btn.click();
                } catch (ItemNotFoundException ex) {
                    logger.info("Failed to find window");
                }
            } catch (ElementNotFoundException ex) {
                logger.info("Failed to find menu");
            }

            AutomationTab tab =
                    window.getTab(Search.getBuilder(0).build());
            tab.selectTabPage("Last Tab");
            //	String tabName = tab.name();

            String text =
                    tab.getEditBox(
                            Search.getBuilder(0).build()).getValue();
            logger.info("Text for editBox1 is " + text);

            AutomationCheckBox check =
                    window.getCheckBox(Search.getBuilder(0).build());
            check.toggle();

            try {
                ToggleState state = check.getToggleState();
                logger.info("State: " + state);
            } catch (AutomationException ex) {
                logger.info("Failed to get toggle state");
            }

            AutomationRadioButton radio =
                    window.getRadioButton(Search.getBuilder(1).build());
            radio.select();

            AutomationStatusBar statusBar = window.getStatusBar();

            AutomationTextBox tb1 =
                    statusBar.getTextBox(Search.getBuilder(1).build());

            String eb1Text = tb1.getValue();

            String aText = tb1.getValueFromIAccessible();

            Object v = tb1.getMetadata();
            logger.info("Metadata...");
            logger.info(v);

            logger.info("Status Bar text = " + eb1Text);
            logger.info("Status Bar text (from accessible) = " + aText);

            try {
                AutomationComboBox cb1 =
                        window.getComboBox(Search.getBuilder(
                                "AutomatedCombobox1").build());
                cb1.setText("Replacements");
                String txt = cb1.getValue();

                cb1.getClickablePoint();

                logger.info("Text for AutomatedCombobox1 is " + txt);
            } catch (ElementNotFoundException ex) {
                logger.error("Failed to find element");
            }

            try {
                AutomationMaskedEdit me0 =
                        window.getMaskedEdit(Search.getBuilder(
                                "AutomatedMaskEdit1").build());

                String value = me0.getValue();
                logger.info("Initial value " + value);

                me0.setValue("12/12/99");

                String value1 = me0.getValue();
                logger.info("Changed value is " + value1);

            } catch (ElementNotFoundException ex) {
                logger.error("Failed to find maskededit");
            }

            try {
                AutomationComboBox cb0 =
                        window.getComboBox(
                                Search.getBuilder(
                                        "AutomatedCombobox2").build());
                cb0.expand();

                this.rest();

                List<AutomationListItem> litems = cb0.getItems();
            } catch (ElementNotFoundException ex) {
                logger.error("Failed to find combobox");
            }

            // Now string grids
            AutomationDataGrid grid =
                    window.getDataGrid(
                            Search.getBuilder(
                                    "AutomationStringGrid1").className(
                                            "TAutomationStringGrid").build());

            AutomationDataGridCell cell1 =
                    grid.getItem(
                            Search.getBuilder(1, 1).build());

            String itemName = cell1.getValue();
            logger.info("Grid item is " + itemName);
//            cell1.setName("This");
//            logger.info("Grid item is " + cell1.name());

            AutomationTreeView tree =
                    window.getTreeView(
                            Search.getBuilder(0).build());
            try {
                AutomationTreeViewItem treeItem =
                        tree.getItem(
                                Search.getBuilder("Sub-SubItem").build());
                treeItem.select();
            } catch (ItemNotFoundException ex) {
                // Not found
                logger.info("ItemNotFoundException");
            } catch (ElementNotFoundException ex) {
                // Not found
                logger.info("ElementNotFoundException");
            }

            AutomationList list =
                    window.getList(
                            Search.getBuilder(0).build());
            try {
                AutomationListItem listItem =
                        list.getItem("First (List)");
                listItem.select();
            } catch (ItemNotFoundException ex) {
                // Not found
                logger.info("ItemNotFoundException");
            } catch (ElementNotFoundException ex) {
                // Not found
                logger.info("ElementNotFoundException");
            }

            try {
                List<AutomationListItem> items = list.getItems();

                for(AutomationListItem item: items) {
                    logger.info(" *" +item.getName());
                }

            } catch (AutomationException ex) {
                logger.info("AutomationException");
                // Not found
            }
/*
            AutomationHyperlink link =
            window.getHyperlink(Search.getBuilder(0).build());
            link.click();

            try {
                AutomationWindow popup1 =
                 window.getWindow(Search.getBuilder("Project1").build());
                try {
                    AutomationButton btn1 =
                      popup1.getButton(Search.getBuilder("OK").build());
                    btn1.click();
                } catch (ElementNotFoundException ex) {
                    logger.info("Failed to find button");
                }
            } catch (ItemNotFoundException ex) {
                logger.info("Failed to find window");
            }
*/
            this.rest();

            AutomationToolBar toolbar =
                    window.getToolBar(Search.getBuilder(1).build());
            logger.info(toolbar.getName());

            // Looks like the button is a problem with Delphi
            AutomationToolBarButton btn0 =
                    toolbar.getToolbarButton(Search.getBuilder(0).build());

            if (btn0.isEnabled()) {
                logger.info("btn0 Enabled");
/*
                btn0.click();

                AutomationWindow popup1 =
                  window.getWindow(Search.getBuilder("Project1").build());
                try {
                    AutomationButton btn1 =
                      popup1.getButton(Search.getBuilder("OK").build());
                    btn1.click();
                } catch (ElementNotFoundException ex) {
                    logger.info("Failed to find button");
                }
*/
            }

            AutomationToolBarButton btn1 =
                    toolbar.getToolbarButton(Search.getBuilder(1).build());

            if (btn1.isEnabled()) {
                logger.info("btn1 Enabled");
/*
                btn1.click();

                AutomationWindow popup1 =
                  window.getWindow(Search.getBuilder("Project1").build());
                try {
                    AutomationButton btnOK =
                      popup1.getButton(Search.getBuilder("OK").build());
                    btnOK.click();
                } catch (ElementNotFoundException ex) {
                    logger.info("Failed to find button");
                }
  */          }

            AutomationToolBarButton btn2 =
                    toolbar.getToolbarButton(Search.getBuilder(2).build());

            if (btn2.isEnabled()) {
                logger.info("btn2 Enabled");
    //            btn2.click();
            }

            AutomationToolBarButton btn3 =
                    toolbar.getToolbarButton(Search.getBuilder(3).build());

            if (btn3.isEnabled()) {
                logger.info("btn3 Enabled");
      //          btn3.click();
            }

            window.setTransparency(128);

            // Context menus
            WinDef.POINT point = grid.getClickablePoint();
            AutomationMouse mouse = AutomationMouse.getInstance();

            mouse.setLocation(point);
            mouse.leftClick();
            mouse.rightClick();

            AutomationMenu context = automation.getDesktopMenu("Context");

            logger.info("Found context menu");
            AutomationMenuItem contextItem =
                    context.getMenuItem("Popup Menu ");
            contextItem.click();

        } catch (AutomationException ex) {
            logger.info("Something went wrong - " + ex.getClass());
        }
    }
}
