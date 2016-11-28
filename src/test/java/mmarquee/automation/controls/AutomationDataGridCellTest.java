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
package mmarquee.automation.controls;

import mmarquee.automation.AutomationException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.IUIAutomationTest;
import mmarquee.automation.uiautomation.RowOrColumnMajor;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 28/11/2016.
 */
public class AutomationDataGridCellTest {

    protected Logger logger = Logger.getLogger(AutomationDataGridCellTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    protected void rest() {
        try {
            Thread.sleep(1500);
        } catch (Exception ex) {
            logger.info("Interrupted");
        }
    }

    private AutomationApplication application = null;
    private AutomationWindow applicationWindow = null;

    private void loadApplication(String appName, String windowName) throws Exception {
        this.rest();

        UIAutomation automation = UIAutomation.getInstance();

        application = automation.launchOrAttach(appName);

        // Wait for the process to start
        // This doesn't seem to wait for WPF examples
        application.waitForInputIdle(5000);

        this.rest();

        applicationWindow = automation.getDesktopWindow(windowName);
    }

    private void closeApplication() throws PatternNotFoundException, AutomationException {
        application.quit("Form1");
    }

    @Test
    public void testValue() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = applicationWindow.getDataGrid(0);

            AutomationDataGridCell cell1 = grid.getItem(2, 2);

            String value = cell1.value();
            logger.info(value);

            assertTrue(value.equals("AutomationStringGrid1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetCellName() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = applicationWindow.getDataGrid(0);

            AutomationDataGridCell cell1 = grid.getItem(2, 2);

            String itemName = cell1.name();
            logger.info(itemName);

            assertTrue(itemName.equals("AutomationStringGrid1"));
        } finally {
            closeApplication();
        }
    }
}


