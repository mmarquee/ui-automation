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

import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

import java.util.List;

/**
 * Created by Mark Humphreys on 26/02/2016.
 *
 * Test the automation wrapper on Excel.
 */
public class TestMainWord extends TestBase {

    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            // 0. Load Word
            try {
                // Start the application
                application = automation.launchOrAttach("C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\WINWORD.EXE");
            } catch (Throwable ex) {
                // Smother
                logger.error("Failed to launch or attach");
            }

            // 1. Load a file in Word

            // 2. Get the sheet
            AutomationWindow window = application.getWindow("Document1 - Word");
            logger.info(window.name());

            AutomationPanel pane = window.getPanel("Document1");
            logger.info(pane.name());
            logger.info(pane.getClassName());
            AutomationPanel pane1 = pane.getPanel(0);
            logger.info(pane1.name());

            AutomationDocument doc = pane1.getDocument(0);
            logger.info(doc.name());

            AutomationDocumentPage page0 = doc.getPage(0);
            logger.info(page0.name());

            AutomationEditBox edit = page0.getEditBox(0);
            logger.info(edit.name());

            String text = edit.getText();
            logger.info("Text = " + text);

/*
            AutomationPanel panelX = window.getPanelByClassName(0, "XLDESK");
            logger.info(panelX.name());
            logger.info(panelX.getClassName());

            AutomationTab tab = panelX.getTab(0);
            logger.info(tab.name());
            AutomationDataGrid grid = tab.getDataGrid(0);
            logger.info(grid.name());

            // 3. Get some data
            AutomationDataGridCell cell = grid.getItem(0,0);
            logger.info(cell.name());
            logger.info(cell.value());

            // 3.4 More data
            List<AutomationDataGridCell> cols = grid.getColumn(0);
            for(AutomationDataGridCell col : cols) {
                logger.info(col.value());
            }

            // 4. Set some extra data
            List<AutomationDataGridCell> headers = grid.getColumnHeaders();

            for(AutomationDataGridCell header : headers) {
                logger.info(header.value());
            }
*/
            logger.info("++ ALL DONE ++");

        } catch (Exception ex) {
            logger.info("Something went wrong - " + ex.getClass());
        }
    }
}
