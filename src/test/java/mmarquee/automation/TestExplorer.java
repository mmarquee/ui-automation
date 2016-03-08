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

import mmarquee.automation.controls.*;
import mmarquee.automation.controls.rebar.*;
import mmarquee.automation.controls.ribbon.*;
import org.apache.log4j.Logger;

/**
 * Created by inpwt on 26/02/2016.
 *
 * Test the automation library on a non-Delphi application, and see
 * whether we can get to all the bits of the UI
 */
public class TestExplorer {
    public void run() {

        Logger logger = Logger.getLogger(AutomationBase.class.getName());

        UIAutomation automation = new UIAutomation();

        AutomationApplication application = null;

        try {
            // Start the application
            application = automation.launchOrAttach("explorer");
        } catch (Throwable ex) {
            // Smother
            logger.error("Failed to launch or attach");
        }

        application.waitForInputIdle(5000);

        // Get the main explorer window
        AutomationWindow window = automation.getDesktopWindow("File Explorer");
        window.focus();

        // Get the ribbon, work our way down and click the "Preview Button"
        AutomationRibbonBar ribbon = window.getRibbonBar();
        AutomationRibbonCommandBar commandBar = ribbon.getRibbonCommandBar();
        AutomationRibbonWorkPane pane = commandBar.getRibbonWorkPane();
        logger.info("First work pane is " + pane.name());

        AutomationNUIPane uiPane = pane.getNUIPane(0);
        logger.info("First NUIPane is " + uiPane.name());

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
            try {
                Thread.sleep(500);
            } catch (Exception ex) {
                logger.info("Interrupted");
            }
            split.click();
        } catch (ElementNotFoundException ex) {

        }

        AutomationReBar rebar = window.getReBar(0);
        try {
            AutomationToolBar toolbar = rebar.getToolBar("Up band toolbar");

            //   logger.info("Toolbar = " + toolbar.name());
            //   AutomationButton upButton = toolbar.getButton(0);
            //   upButton.click();
        } catch (ElementNotFoundException ex) {

        }

        try {
            // Now try and get to the list of items in explorer
            AutomationPanel explorer = window.getPanel("File Explorer");
            logger.info(explorer.name());
            AutomationPanel pane0 = explorer.getPanel("Control Host");
            logger.info(pane0.name());
            AutomationTreeView treeView = pane0.getTreeView(0);
            logger.info(treeView.name());
            try {
                AutomationTreeViewItem treeItem = treeView.getItem("Desktop");
                logger.info(treeItem.name());
                treeItem.select();
                treeItem.click();
            } catch (ItemNotFoundException ex) {
                logger.info("Didn't find the item");
            }
        } catch (ElementNotFoundException ex) {

        }


        /*
        logger.info(explorerPane.name());
        AutomationPanel folderLayoutPane = explorerPane.getPanel("Folder Layout Pane");
        logger.info(folderLayoutPane.name());
        AutomationPanel controlHost = folderLayoutPane.getPanel(0);
        logger.info(controlHost.name());
        AutomationTreeView treeView = controlHost.getTreeView(0);
        try {
            AutomationTreeViewItem desktopItem = treeView.getItem("Desktop");

            desktopItem.select();
        } catch (ItemNotFoundException ex) {
            logger.error("Didn't find Desktop");
        }
*/

        /*
        // Minimize
        btn.click();
        try {
            Thread.sleep(500);
        } catch (Exception ex) {
            logger.info("Interrupted");
        }
        */

/*
        // Wait for the process to start
        application.waitForInputIdle(5000);

        AutomationWindow window = automation.getDesktopWindow("Untitled - Notepad");
        window.focus();
        window.maximize();

        AutomationDocument document = window.getDocument(0);

        //document.setText("This is a journey into sound");

        String text = document.getText();
*/
//        document.setName("This is a journey into sound");

/*
		AutomationMainMenu menu = window.getMainMenu();

		AutomationMenuItem exit = menu.getMenuItem("File", "Exit");
		exit.click();
		*/
    }
}
