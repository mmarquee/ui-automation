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

import mmarquee.automation.BaseAutomationTest;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 28/11/2016.
 */
public class AutomationDataGridCellTest extends BaseAutomationTest {

    protected Logger logger = Logger.getLogger(AutomationDataGridCellTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testValue() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            AutomationDataGridCell cell1 = grid.getItem(1, 2);

            String value = cell1.value();

            assertTrue(value.equals("Row 1, Col 2"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetCellName() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            AutomationDataGridCell cell1 = grid.getItem(1, 2);

            String itemName = cell1.name();

            logger.info(itemName);

            assertTrue(itemName.equals("Row 1, Col 2"));
        } finally {
            closeApplication();
        }
    }
}