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
package mmarquee.demo;

import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.*;
import mmarquee.automation.controls.*;
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.controls.menu.AutomationMenu;
import mmarquee.automation.controls.menu.AutomationMenuItem;
import mmarquee.automation.controls.mouse.AutomationMouse;
import mmarquee.automation.uiautomation.ToggleState;

import java.util.List;

/**
 * Created by Mark Humphreys on 26/02/2016
 *  *
 * Test the automation wrapper on a Delphi VCL application.
 */
public class TestMain extends TestBase {

    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            application = automation.launchOrAttach("apps\\Project1.exe");
        } catch (Throwable ex) {
            logger.warn("Failed to find application", ex);
        }

        try {
            // Wait for the process to start
            application.waitForInputIdle(5000);
        } catch (Throwable ex) {
            logger.error("Failed to wait properly");
        }

        try {
            AutomationWindow window = automation.getDesktopWindow("Form1");
            String name = window.name();
            logger.info(name);

            Object framework = window.getFramework();
            logger.info("Framework is " + framework.toString());

            boolean val = window.isModal();

            java.lang.Object rect = window.getBoundingRectangle();

       //     WinDef.HWND handle = window.getNativeWindowHandle();

            // Interact with menus
            AutomationMainMenu menu = window.getMainMenu();
//		List<AutomationMenuItem> items = menu.getItems();

//		AutomationMenuItem item = items.get(0);
//		item.expand();

//		String name1 = item.name();

            try {
                AutomationMenuItem exit = menu.getMenuItem("File", "Exit");
                exit.click();

                try {
                    AutomationWindow popup = window.getWindow("Project1");
                    Object val111 = popup.getBoundingRectangle();

                    AutomationButton btn = popup.getButton("OK");
                    Object val11 = btn.getBoundingRectangle();

                    boolean val1 = popup.isModal();

                    btn.click();
                } catch (ItemNotFoundException ex) {
                    logger.info("Failed to find window");
                }
            } catch (ElementNotFoundException ex) {
                logger.info("Failed to find menu");
            }

            AutomationTab tab = window.getTab(0);
            tab.selectTabPage("Last Tab");
            //	String tabName = tab.name();

            String text = tab.getEditBox(0).getValue();
            logger.info("Text for editBox1 is " + text);

            AutomationCheckbox check = window.getCheckbox(0);
            check.toggle();

            try {
                ToggleState state = check.getToggleState();
            } catch (Exception ex) {
                logger.info("Failed to get toggle state");
            }

            AutomationRadioButton radio = window.getRadioButton(1);
            radio.selectItem();

            AutomationStatusBar statusbar = window.getStatusBar();

            // AutomationTextBox tb0 = statusbar.getTextBox(0);
            AutomationTextBox tb1 = statusbar.getTextBox(1);

            // String eb0Text = statusbar.getTextBox(0).getValue();
            String eb1Text = tb1.getValue();

            logger.info("Statusbar text = " + eb1Text);

            try {
                AutomationComboBox cb1 = window.getCombobox("AutomatedCombobox1");
                cb1.setText("Replacements");
                String txt = cb1.text();

                cb1.getClickablePoint();

                logger.info("Text for AutomatedCombobox1 is " + txt);
            } catch (ElementNotFoundException ex) {
                logger.error("Failed to find element");
            }

            try {
                AutomationMaskedEdit me0 = window.getMaskedEdit("AutomatedMaskEdit1");

                String value = me0.getValue();
                logger.info("Initial value " + value);

                me0.setValue("12/12/99");

                String value1 = me0.getValue();
                logger.info("Changed value is " + value1);

            } catch (ElementNotFoundException ex) {
                logger.error("Failed to find maskededit");
            }

            try {
                AutomationComboBox cb0 = window.getCombobox("AutomatedCombobox2");
                cb0.expand();

                this.rest();

                List<AutomationListItem> litems = cb0.getList();
            } catch (ElementNotFoundException ex) {
                logger.error("Failed to find combobox");
            }

            // Now string grids
            AutomationDataGrid grid = window.getDataGrid("AutomationStringGrid1", "TAutomationStringGrid");

            AutomationDataGridCell cell1 = grid.getItem(1, 1);

            String itemName = cell1.value();
            logger.info("Grid item is " + itemName);
//            cell1.setName("This");
//            logger.info("Grid item is " + cell1.name());

            AutomationTreeView tree = window.getTreeView(0);
            try {
                AutomationTreeViewItem treeItem = tree.getItem("Sub-SubItem");
                treeItem.select();
            } catch (ItemNotFoundException ex) {
                // Not found
            } catch (ElementNotFoundException ex) {
                // Not found
            }

            AutomationList list = window.getListItem(0);
            try {
                AutomationListItem listItem = list.getItem("First (List)");
                listItem.select();
            } catch (ItemNotFoundException ex) {
                // Not found
            } catch (ElementNotFoundException ex) {
                // Not found
            }

            try {
                List<AutomationListItem> items = list.getItems();

                for(AutomationListItem item: items) {
                    logger.info(" *" +item.name());
                }

            } catch (AutomationException ex) {
                // Not found
            }

            AutomationHyperlink link = window.getHyperlink(0);
            link.click();

            try {
                AutomationWindow popup1 = window.getWindow("Project1");
                try {
                    AutomationButton btn1 = popup1.getButton("OK");
                    btn1.click();
                } catch (ElementNotFoundException ex) {
                    logger.info("Failed to find button");
                }
            } catch (ItemNotFoundException ex) {
                logger.info("Failed to find window");
            }

            this.rest();

            /* This doesn't seem to work */
            AutomationToolBar toolbar = window.getToolBar(1);
            logger.info(toolbar.name());

            // Looks like the button is a problem with Delphi
            AutomationToolBarButton btn0 = toolbar.getToolbarButton(0);

            if (btn0.isEnabled()) {
                logger.info("btn0 Enabled");

                btn0.click();

                AutomationWindow popup1 = window.getWindow("Project1");
                try {
                    AutomationButton btn1 = popup1.getButton("OK");
                    btn1.click();
                } catch (ElementNotFoundException ex) {
                    logger.info("Failed to find button");
                }
            }

            AutomationToolBarButton btn1 = toolbar.getToolbarButton(1);

            if (btn1.isEnabled()) {
                logger.info("btn1 Enabled");
                btn1.click();

                AutomationWindow popup1 = window.getWindow("Project1");
                try {
                    AutomationButton btnOK = popup1.getButton("OK");
                    btnOK.click();
                } catch (ElementNotFoundException ex) {
                    logger.info("Failed to find button");
                }
            }

            AutomationToolBarButton btn2 = toolbar.getToolbarButton(2);

            if (btn2.isEnabled()) {
                logger.info("btn2 Enabled");
                btn2.click();
            }

            AutomationToolBarButton btn3 = toolbar.getToolbarButton(3);

            if (btn3.isEnabled()) {
                logger.info("btn3 Enabled");
                btn3.click();
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
            AutomationMenuItem contextItem = context.getMenuItem("Popup Menu ");
            contextItem.click();

        } catch (Exception ex) {
            logger.info("Something went wrong - " + ex.getClass());
        }
    }
}
