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

import mmarquee.automation.rebar.AutomationReBar;
import mmarquee.automation.ribbon.*;
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
        AutomationRibbonBar ribbon = window.getRibbonBar(0);
        AutomationRibbonCommandBar commandBar = ribbon.getRibbonCommandBar(0);
        AutomationRibbonWorkPane pane = commandBar.getRibbonWorkPane(0);
        logger.info("First work pane is " + pane.name());

        AutomationNUIPane uiPane = pane.getNUIPane(0);
        logger.info("First NUIPane is " + uiPane.name());

        AutomationNetUIHWND uiHWND = uiPane.getNetUIHWND(0);
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

        AutomationReBar rebar = window.getReBar(0);
        AutomationToolBar toolbar = rebar.getToolBar("Up band toolbar");

        logger.info("Toolbar = " + toolbar.name());
        AutomationButton upButton = toolbar.getButton(0);
        upButton.click();



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
