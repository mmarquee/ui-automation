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

import mmarquee.automation.*;
import mmarquee.automation.controls.*;

import java.util.List;

/**
 * @author Mark Humphreys
 * Date 16/02/2017.
 *
 * Test the automation wrapper on a Delphi AutomatedGrid.
 */
public class DemoGrid extends TestBase {

    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            application = automation.launchOrAttach("apps\\GridsDemo.exe");
        } catch (Throwable ex) {
            logger.warn("Failed to find application", ex);
        }

        // Check that the attached issue is fixed.
        logger.info(application.getIsAttached());

        // Wait for the process to start
        // This doesn't seem to wait for WPF examples
        assert application != null;
        application.waitForInputIdle(AutomationApplication.SHORT_TIMEOUT);

        // Sleep for WPF, to address above issue
        this.rest();

        AutomationWindow applicationWindow = null;

        try {
            applicationWindow = automation.getDesktopWindow("Demo Form");
        } catch (Exception ex) {
            logger.info("Failed to find `Demo Form`");
        }

        try {
            assert applicationWindow != null;
            Object framework = applicationWindow.getFramework();
            logger.info("Framework is " + framework.toString());

            Object id = applicationWindow.getProcessId();
            logger.info("Process = " + id.toString());

            String name = applicationWindow.getName();
            logger.info(name);

            try {
                boolean val = applicationWindow.isModal();

                logger.info(val);
            } catch (Exception ex) {
                logger.info("Ouch");
            }

            // GRIDS ***********************************
            AutomationDataGrid grid = applicationWindow.getDataGrid(Search.getBuilder("AutomatedCombobox1").className("TJHCGrid").build());
            logger.info(grid.getName());

            // By convention, if there are no selected rows, then show the 'fields' memu of our grids
         //   grid.showContextMenu();

            AutomationDataGridCell cell1 = grid.getItem(Search.getBuilder(1, 1).build());

            logger.info("value: " + cell1.getValue());

            List<AutomationDataGridCell> cells = grid.selectedRow();

            logger.info("size: " + cells.size());

            if (cells.size() != 0) {
                logger.info("value is " + cells.get(1).getValue());
            }

            AutomationDataGridCell cell3 = grid.getItem(Search.getBuilder(3, 3).build());
            cell3.select();

            List<AutomationDataGridCell> cells0 = grid.selectedRow();

            logger.info("value is now " + cells0.get(1).getValue());

            logger.info("++ ALL DONE ++");

//            cell3.showContextMenu();
            cell3.invoke();

        } catch (AutomationException ex) {
            logger.info("Something went wrong - " + ex.getClass());
        }
    }
}
