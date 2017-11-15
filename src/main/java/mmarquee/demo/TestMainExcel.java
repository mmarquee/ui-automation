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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

import java.util.List;

/**
 * @author Mark Humphreys
 * Date 26/02/2016.
 *
 * Test the automation wrapper on Excel.
 */
public class TestMainExcel extends TestBase {

    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            // 0. Load excel - this works for Excel 2013, but not for Excel 2015,as the control hierarchy is different.

            try {
                // Start the application
                application = automation.launchOrAttach("C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\EXCEL.EXE");
            } catch (Throwable ex) {
                // Smother
                logger.error("Failed to launch or attach");
            }

            // 1. Load a file in excel

            // 2. Get the sheet
            AutomationWindow window = application.getWindow("Book1 - Excel");
            logger.info(window.getName());

            AutomationPanel panelX = window.getPanel(Search.getBuilder(0).className("XLDESK").build());
            logger.info(panelX.getName());
            logger.info(panelX.getClassName());

            AutomationTab tab = panelX.getTab(Search.getBuilder(0).build());
            logger.info(tab.getName());

            List<AutomationTabItem> items = tab.getTabItems();

            for(AutomationTabItem item : items) {
                logger.info(item.getName());
            }

            AutomationDataGrid grid = window.getDataGrid(Search.getBuilder(0).build());
            logger.info(grid.getName());

            // 3. Get some data
            AutomationDataGridCell cell = grid.getItem(Search.getBuilder(0,0).build());
            logger.info(cell.getName());
            logger.info(cell.getValue());
            logger.info(cell.getColumn());
            logger.info(cell.getRow());

            AutomationDataGridCell cell1 = grid.getItem(Search.getBuilder(1,1).build());
            logger.info(cell1.getName());
            logger.info(cell1.getValue());
            logger.info(cell1.getColumn());
            logger.info(cell1.getRow());

            AutomationDataGridCell cell2 = grid.getItem(Search.getBuilder(2,2).build());
            logger.info(cell2.getName());
            logger.info(cell2.getValue());
            logger.info(cell2.getColumn());
            logger.info(cell2.getRow());

            // 3.5 Set some data
            cell2.setValue("XYZ");
            logger.info(cell2.getValue());

            AutomationDataGridCell cell3 = grid.getItem(Search.getBuilder(3,3).build());
            logger.info(cell3.getName());
            logger.info(cell3.getValue());

            if (grid.canSelectMultiple()) {
                // Play with selection - doesn't seem to be working yet
                cell.addToSelection();
                cell2.addToSelection();
                cell3.addToSelection();

                // something
                List<AutomationElement> items0 = grid.getSelection();
                logger.info(items0.size());

                cell2.removeFromSelection();

                // something again - should be different
                List<AutomationElement> items1 = grid.getSelection();
                logger.info(items1.size());
            } else {
                logger.info("Multiple selection not allowed");
            }

            // 3.4 More data

            logger.info("Rows = " + grid.rowCount());
            logger.info("Cols = " + grid.columnCount());

            List<AutomationDataGridCell> cols = grid.getColumn(0);
            for(AutomationDataGridCell col : cols) {
                logger.info(col.getValue());
            }

            // 4. Set some extra data - excel doesn't seem to implement the correct pattern
            try {
                List<AutomationDataGridCell> headers = grid.getColumnHeaders();

                for (AutomationDataGridCell header : headers) {
                    logger.info(header.getValue());
                }
            } catch (NullPointerException ex) {
                logger.info ("Not supported in Excel");
            }

            logger.info("++ ALL DONE ++");

        } catch (Exception ex) {
            logger.info("Something went wrong - " + ex.getClass());
        }
    }
}
