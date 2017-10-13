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

import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;
import mmarquee.automation.controls.AutomationReBar;

/**
 * @author Mark Humphreys
 * Date 26/02/2016.
 *
 * Test the automation library on a non-Delphi, non-WPF application, and see
 * whether we can get to all the bits of the UI
 */
class TestExplorer extends TestBase {

    void run() {
        UIAutomation automation = UIAutomation.getInstance();

        try {
            AutomationApplication application = null;

            try {
                // Start the application
                application = automation.launchOrAttach("explorer");
            } catch (Throwable ex) {
                // Smother
                logger.error("Failed to launch or attach");
            }

            try {
                application.waitForInputIdle(AutomationApplication.SHORT_TIMEOUT);
            } catch (Throwable ex) {
                logger.error("Failed to wait for input idle for some reason");
            }

            // Get the main explorer window
            try {
                AutomationWindow window = automation.getDesktopWindow("File Explorer");
                window.focus();

                // Get the ribbon, work our way down and click the "Preview Button"
                AutomationRibbonBar ribbon = window.getRibbonBar();
                AutomationRibbonCommandBar commandBar = ribbon.getRibbonCommandBar();
                AutomationRibbonWorkPane pane = commandBar.getRibbonWorkPane();
                logger.info("First work pane is " + pane.getName());

                AutomationNUIPane uiPane = pane.getNUIPane(0);
                logger.info("First NUIPane is " + uiPane.getName());

                AutomationNetUIHWND uiHWND = uiPane.getNetUIHWND(0);

                try {
                    AutomationButton btn = uiHWND.getButton("Minimise the Ribbon");

                    AutomationTab tab = uiHWND.getTab(0);
                    tab.selectTabPage("View");

                    AutomationPanel panel = uiHWND.getPanel("Lower Ribbon");

                    AutomationToolBar panes = panel.getToolBar("Panes");

                    panes.getButton("Preview pane").click();
                    AutomationSplitButton split = panes.getSplitButton("Navigation pane");
                    split.click();

                    this.rest();

                    split.click();
                } catch (ElementNotFoundException ex) {
                    logger.info("Failed to find element");
                }

                this.rest();

                logger.info("+++ Rebar +++");

                AutomationReBar rebar = window.getReBar(0);
                try {
                    AutomationToolBar toolbar = rebar.getToolBar("Up band toolbar");

                    logger.info("Toolbar = " + toolbar.getName());
                    AutomationButton upButton = toolbar.getButton(0);
                    logger.info("Rebar button is `" + upButton.getName() + "`");
                    //   upButton.click();
                } catch (ElementNotFoundException ex) {
                    logger.info("Failed to find element");
                }

                logger.info("+++ Interact with the panel now +++ ");

                try {
                    // Now try and get to the list of items in explorer
                    AutomationPanel explorer = window.getPanel("File Explorer");
                    logger.info("." + explorer.getName());
                    AutomationPanel pane0 = explorer.getPanel("Control Host");
                    logger.info(".." + pane0.getName());
                    AutomationTreeView treeView = pane0.getTreeView(0);
                    logger.info("..." + treeView.getName());
                    try {
                        AutomationTreeViewItem treeItem = treeView.getItem("Desktop");
                        logger.info("...." + treeItem.getName());

                        this.rest();

                        logger.info("Here");
                        // Get an issue here, not sure whether this previously existed
                        treeItem.click();
                        logger.info("Here...");
                    } catch (ItemNotFoundException ex) {
                        logger.info("Didn't find the item");
                    }
                } catch (ElementNotFoundException ex) {
                    logger.info("Failed to find element");
                }

                logger.info("Getting toolbar");
                AutomationToolBar toolbar = window.getToolBar(0);
                logger.info("Got toolbar");
                logger.info("....." + toolbar.getName());

                // Looks like the button is a problem with Delphi
                AutomationButton btn0 = toolbar.getButton(0);
                logger.info("....." + btn0.getName());
                if (btn0.isEnabled()) {
                    btn0.click();
                }

            } catch (Exception ex) {
                logger.info("Something went wrong");
                ex.printStackTrace();
            }
        } finally {
            automation.cleanUp();
        }
    }
}
