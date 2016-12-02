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
import mmarquee.automation.uiautomation.RowOrColumnMajor;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 28/11/2016.
 */
public class AutomationDataGridTest extends BaseAutomationTest {

    protected Logger logger = Logger.getLogger(AutomationDataGridTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testGetName_For_DataGrid() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            String name = grid.name();
            assertTrue(name.equals("AutomationStringGrid1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetCell_For_DataGrid() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            AutomationDataGridCell cell1 = grid.getItem(1, 1);

            String itemName = cell1.name();

            logger.info(itemName);

            assertTrue(itemName.equals("Row 1, Col 1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetValue_For_DataGrid() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            String value = grid.getValue();

            assertTrue(value.equals("Row 1, Col 1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetRowOrColumn_For_DataGrid() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            RowOrColumnMajor rowOrColumn = grid.getRowOrColumnMajor();

            assertTrue(rowOrColumn == RowOrColumnMajor.RowMajor);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetColumnHeaders_Returns_Correct_Size() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            List<AutomationDataGridCell> list = grid.getColumnHeaders();

            logger.info(list.size());

            assertTrue(list.size() == 5);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetColumnHeader() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            AutomationDataGridCell item = grid.getColumnHeader(3);

            logger.info(item.value());

            assertTrue(item.value().equals("Title 4"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetColumnHeaders_Returns_Size_As_ColumnCount() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            List<AutomationDataGridCell> list = grid.getColumnHeaders();

            logger.info(list.size());
            logger.info(grid.columnCount());

            assertTrue(list.size() == grid.columnCount());
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testIsReadOnly() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            assertFalse(grid.isReadOnly());
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testSelected() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            AutomationDataGridCell selected = grid.selected();

            assertTrue(selected.value().equals("Row 1, Col 1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetColumns_Returns_Size_As_RowCount() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            List<AutomationDataGridCell> list = grid.getColumn(1);

            logger.info(list.size());
            logger.info(grid.rowCount());

            assertTrue(list.size() == grid.rowCount());
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetRows_Returns_Size_As_ColumnCount() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            List<AutomationDataGridCell> list = grid.getRow(1);

            logger.info(list.size());
            logger.info(grid.columnCount());

            assertTrue(list.size() == grid.columnCount());
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetRow() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            List<AutomationDataGridCell> items = grid.getRow(3);

            assertTrue(items.size() == 5);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetColumn() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationDataGrid grid = window.getDataGrid(0);

            List<AutomationDataGridCell> items = grid.getColumn(3);

            assertTrue(items.size() == 5);
        } finally {
            closeApplication();
        }
    }

}
