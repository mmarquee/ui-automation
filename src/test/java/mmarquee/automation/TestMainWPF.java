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
import mmarquee.automation.controls.stringgrid.AutomationStringGrid;
import mmarquee.automation.controls.stringgrid.AutomationStringGridCell;
import mmarquee.automation.uiautomation.ToggleState;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by inpwt on 26/02/2016.
 */
public class TestMainWPF {

    public void run() {
        UIAutomation automation = new UIAutomation();

        Logger logger = Logger.getLogger(AutomationBase.class.getName());

        AutomationApplication application = null;

        try {
            application = automation.launchOrAttach("apps\\SampleWpfApplication.exe");
        } catch (Throwable ex) {
            // Smother????
            logger.warn("Failed to find application", ex);
        }

        // Wait for the process to start
        application.waitForInputIdle(5000);

        try {
            AutomationWindow window = automation.getDesktopWindow("MainWindow");
            String name = window.name();
            logger.info(name);

            boolean val = window.isModal();

            Object rect = window.getBoundingRectangle();

            WinDef.HWND handle = window.getNativeWindowHandle();

            // Interact with menus
            AutomationMainMenu menu = window.getMainMenu(0);

//            AutomationMainMenu menu = window.getMenu();   // WPF menus seem to be different from Delphi VCL windows

            // Not menus for now

//            try {
//                AutomationMenuItem exit = menu.getMenuItem("File", "Exit");
//                exit.click();
/*
                AutomationWindow popup = window.getWindow("Project1");
                Object val111 = popup.getBoundingRectangle();

                AutomationButton btn = popup.getButton("OK");
                Object val11 = btn.getBoundingRectangle();

                boolean val1 = popup.isModal();

                btn.click();
                */
//            } catch (ElementNotFoundException ex) {
//                logger.info("Failed to find menu");
//            }

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
            ToggleState state = check.getToggleState();

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

// NOTE: this causes NPE now
//                String txt = cb0.text();
//
//                logger.info("Text for Combobox is " + txt);
            } catch (ElementNotFoundException ex) {
                logger.error("Failed to find element");
            }


            // MASKED EDIT ****************************************
/*
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
                try {
                    cb0.wait(750);
                } catch (Exception ex) {
                    // Time out
                }
                List<AutomationListItem> litems = cb0.getList();
            } catch (ElementNotFoundException ex) {
                logger.error("Failed to find combobox");
            }

            // Now string grids
            AutomationStringGrid grid = window.getStringGrid(0, "TAutomationStringGrid");

            AutomationStringGridCell cell1 = grid.getItem(1, 1);

            String itemName = cell1.name();
            logger.info("Grid item is " + itemName);
            cell1.setName("This");
            logger.info("Grid item is " + cell1.name());

            AutomationTreeView tree = window.getTreeView(0);
            try {
                AutomationTreeViewItem treeItem = tree.getItem("Sub-SubItem");
                treeItem.select();
            } catch (ItemNotFoundException ex) {
                // Not found
            } catch (ElementNotFoundException ex) {
                // Not found
            }
*/

            // BUTTONS ***********************************

            // NOTE: WPF buttons will set the automationID to be the name of the control

            AutomationButton btn = window.getButtonByAutomationId("btnClickMe");
            logger.info(btn.name());
            btn.click();

            // LISTS ****************************************

            // NOTE: WPF lists also seem to be different,


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

            /*
            AutomationHyperlink link = window.getHyperlink(0);
            link.click();
            AutomationWindow popup1 = window.getWindow("Project1");
            try {
                AutomationButton btn1 = popup1.getButton("OK");
                btn1.click();
            } catch (ElementNotFoundException ex) {
                logger.info("Failed to find button");
            }

            try {
                Thread.sleep(500);
            } catch (Exception ex) {
                logger.info("Interrupted");
            }

            // This doesn't seem to work
            /*
            AutomationToolBar toolbar = window.getToolBar(0);
            logger.info(toolbar.name());

            // Looks like the button is a problem with Delphi
            AutomationButton btn0 = toolbar.getButton(0);

            if (btn0.isEnabled()) {
                logger.info("btn0 Enabled");
                btn0.click();
            }

            AutomationButton btn1 = toolbar.getButton(1);

            if (btn1.isEnabled()) {
                logger.info("btn1 Enabled");
                btn1.click();
            }

            AutomationButton btn2 = toolbar.getButton(2);

            if (btn2.isEnabled()) {
                logger.info("btn2 Enabled");
                btn2.click();
            }

            AutomationButton btn3 = toolbar.getButton(3);

            if (btn3.isEnabled()) {
                logger.info("btn3 Enabled");
                btn3.click();
            }
*/
        } catch (ElementNotFoundException ex) {
            logger.info("Element Not Found ");
        }
    }
}
