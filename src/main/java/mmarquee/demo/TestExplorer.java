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

import mmarquee.automation.AutomationException;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

/**
 * @author Mark Humphreys
 * Date 26/02/2016.
 *
 * Test the automation library on a non-Delphi, non-WPF application, and see
 * whether we can get to all the bits of the UI
 */
class TestExplorer extends TestBase {

    /**
     * Run the code please.
     */
    public final void run() {

        UIAutomation automation = UIAutomation.getInstance();

        Application application = null;

        try {
            // Start the application
            application = automation.launchOrAttach("explorer");
        } catch (Throwable ex) {
            // Smother
            logger.error("Failed to launch or attach");
        }

        try {
            assert application != null;
            application.waitForInputIdle(Application.SHORT_TIMEOUT);
        } catch (Throwable ex) {
            logger.error("Failed to wait for input idle for some reason");
        }

        // Get the main explorer window
        try {
            Window window =
                    automation.getDesktopWindow("File Explorer");
            window.focus();

            // Get the ribbon, work our way down and click the "Preview Button"
            RibbonBar ribbon = window.getRibbonBar();
            RibbonCommandBar commandBar =
                    ribbon.getRibbonCommandBar();
            RibbonWorkPane pane = commandBar.getRibbonWorkPane();
            logger.info("First work pane is " + pane.getName());

            NUIPane uiPane =
                    pane.getNUIPane(Search.getBuilder(0).build());
            logger.info("First NUIPane is " + uiPane.getName());

            NetUIHWND uiHWND =
                    uiPane.getNetUIHWND(Search.getBuilder(0).build());

            try {
                uiHWND.getButton(
                        Search.getBuilder(
                                "Minimise the Ribbon").build());

                Tab tab =
                        uiHWND.getTab(Search.getBuilder(0).build());
                tab.selectTabPage("View");

                Panel panel =
                        uiHWND.getPanel(
                                Search.getBuilder(
                                        "Lower Ribbon").build());

                ToolBar panes =
                        panel.getToolBar(
                                Search.getBuilder(
                                        "Panes").build());

                panes.getButton(
                        Search.getBuilder(
                                "Preview pane").build()).click();
                SplitButton split =
                        panes.getSplitButton(
                                Search.getBuilder(
                                        "Navigation pane").build());
                split.click();

                this.rest();

                split.click();
            } catch (ElementNotFoundException ex) {
                logger.info("Failed to find element");
            }

            this.rest();

            logger.info("+++ Rebar +++");

            ReBar rebar =
                    window.getReBar(Search.getBuilder(0).build());
            try {
                ToolBar toolbar =
                        rebar.getToolBar(
                                Search.getBuilder(
                                        "Up band toolbar").build());

                logger.info("Toolbar = " + toolbar.getName());
                Button upButton =
                        toolbar.getButton(
                                Search.getBuilder(0).build());
                logger.info("Rebar button is `" + upButton.getName() + "`");
             //   upButton.click();
            } catch (ElementNotFoundException ex) {
                logger.info("Failed to find element");
            }

            logger.info("+++ Interact with the panel now +++ ");

            try {
                // Now try and get to the list of items in explorer
                Panel explorer =
                        window.getPanel(
                                Search.getBuilder(
                                        "File Explorer").build());
                logger.info("." + explorer.getName());
                Panel pane0 =
                        explorer.getPanel(
                                Search.getBuilder("Control Host").build());
                logger.info(".." + pane0.getName());
                TreeView treeView =
                        pane0.getTreeView(
                                Search.getBuilder(0).build());
                logger.info("..." + treeView.getName());
                try {
                    TreeViewItem treeItem =
                            treeView.getItem(
                                    Search.getBuilder("Desktop").build());
                    logger.info("...." + treeItem.getName());

                    this.rest();

                    logger.info("Here");

                    // Get an issue here, maybe
                    treeItem.click();
                    logger.info("Here...");
                } catch (ItemNotFoundException ex) {
                    logger.info("Didn't find the item");
                }
            } catch (ElementNotFoundException ex) {
                logger.info("Failed to find element");
            }

            logger.info("Getting toolbar");
            ToolBar toolbar =
                    window.getToolBar(Search.getBuilder(0).build());
            logger.info("Got toolbar");
            logger.info("....." + toolbar.getName());

            // Looks like the button is a problem with Delphi
            Button btn0 =
                    toolbar.getButton(Search.getBuilder(0).build());
            logger.info("....." + btn0.getName());
            if (btn0.isEnabled()) {
                btn0.click();
            }

        } catch (AutomationException ex) {
            logger.info("Something went wrong");
            ex.printStackTrace();
        }
    }
}
