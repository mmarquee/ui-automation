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
package mmarquee.automation.controls;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.Grid;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.pattern.Table;
import mmarquee.automation.pattern.Value;
import mmarquee.automation.uiautomation.IUIAutomation;

/**
 * @author Mark Humphreys
 * Date 25/09/2017
 */
public class AutomationDataGridTest2 {

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    protected Logger logger = Logger.getLogger(AutomationDataGridTest2.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testGetCell_For_DataGrid() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        Grid grid = Mockito.mock(Grid.class);
        Table table = Mockito.mock(Table.class);
        Selection selection = Mockito.mock(Selection.class);

        when(grid.isAvailable()).thenReturn(true);
        when(table.isAvailable()).thenReturn(true);
        
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationDataGrid dataGrid = new AutomationDataGrid(
                new ElementBuilder(element).addPattern(value, grid, table, selection).automation(instance));

        AutomationDataGridCell cell = dataGrid.getItem(Search.getBuilder(0,0).build());

        verify(grid, times(1)).getItem(0, 0);
    }

    @Test
    public void testGetColumnHeaders_Returns_Correct_Size() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        Grid grid = Mockito.mock(Grid.class);
        Table table = Mockito.mock(Table.class);
        Selection selection = Mockito.mock(Selection.class);

        when(grid.isAvailable()).thenReturn(true);
        when(table.isAvailable()).thenReturn(true);
        
        List<AutomationElement> elementList = new ArrayList<>();
        AutomationElement element1 = Mockito.mock(AutomationElement.class);
        AutomationElement element2 = Mockito.mock(AutomationElement.class);

        elementList.add(element1);
        elementList.add(element2);

        when(table.getCurrentColumnHeaders()).thenReturn(elementList);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationDataGrid dataGrid = new AutomationDataGrid(
                new ElementBuilder(element).addPattern(value, grid, table, selection).automation(instance));
        List<AutomationDataGridCell> list = dataGrid.getColumnHeaders();

        assertTrue(list.size() == 2);
    }

    @Test
    @Ignore("Gets an empty cell")
    public void testGetColumnHeader() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        Grid grid = Mockito.mock(Grid.class);
        Table table = Mockito.mock(Table.class);
        Selection selection = Mockito.mock(Selection.class);

        when(grid.isAvailable()).thenReturn(true);
        when(table.isAvailable()).thenReturn(true);
        
        List<AutomationElement> elementList = new ArrayList<>();
        AutomationElement element1 = Mockito.mock(AutomationElement.class);
        AutomationElement element2 = Mockito.mock(AutomationElement.class);

        when(element1.getName()).thenReturn("CELL-01");
        when(element2.getName()).thenReturn("CELL-02");

        elementList.add(element1);
        elementList.add(element2);

        when(table.getCurrentColumnHeaders()).thenReturn(elementList);
        when(grid.columnCount()).thenReturn(elementList.size());

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationDataGrid dataGrid = new AutomationDataGrid(
                new ElementBuilder(element).addPattern(value, grid, table, selection).automation(instance));
        AutomationDataGridCell item = dataGrid.getColumnHeader(0);

        logger.info(item.getValue());

        assertTrue(item.getValue().equals("CELL-01"));
    }

    @Test
    public void testGetColumnHeaders_Returns_Size_As_ColumnCount() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        Grid grid = Mockito.mock(Grid.class);
        Table table = Mockito.mock(Table.class);
        Selection selection = Mockito.mock(Selection.class);

        when(grid.isAvailable()).thenReturn(true);
        when(table.isAvailable()).thenReturn(true);
        
        List<AutomationElement> elementList = new ArrayList<>();
        AutomationElement element1 = Mockito.mock(AutomationElement.class);
        AutomationElement element2 = Mockito.mock(AutomationElement.class);

        elementList.add(element1);
        elementList.add(element2);

        when(table.getCurrentColumnHeaders()).thenReturn(elementList);
        when(grid.columnCount()).thenReturn(elementList.size());

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationDataGrid dataGrid = new AutomationDataGrid(
                new ElementBuilder(element).addPattern(value, grid, table, selection).automation(instance));
        List<AutomationDataGridCell> list = dataGrid.getColumnHeaders();

        assertTrue(list.size() == dataGrid.columnCount());
    }

    @Test
    @Ignore("Out of bounds exception thrown now")
    public void testSelected() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        Grid grid = Mockito.mock(Grid.class);
        Table table = Mockito.mock(Table.class);
        Selection selection = Mockito.mock(Selection.class);

        when(grid.isAvailable()).thenReturn(true);
        when(table.isAvailable()).thenReturn(true);
        
        List<AutomationElement> elementList = new ArrayList<>();
        AutomationElement element1 = Mockito.mock(AutomationElement.class);
        AutomationElement element2 = Mockito.mock(AutomationElement.class);

        elementList.add(element1);
        elementList.add(element2);

        when(element1.getName()).thenReturn("CELL-01");
        when(element2.getName()).thenReturn("CELL-02");

        when(table.getCurrentColumnHeaders()).thenReturn(elementList);
        when(grid.columnCount()).thenReturn(elementList.size());

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationDataGrid dataGrid = new AutomationDataGrid(
                new ElementBuilder(element).addPattern(value, grid, table, selection).automation(instance));

        AutomationDataGridCell cell = dataGrid.selected();

        assertTrue(cell.getValue().equals("CELL-01"));
    }

    @Test
    public void testGetColumn_Returns_Size_As_RowCount() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        Grid grid = Mockito.mock(Grid.class);
        Table table = Mockito.mock(Table.class);
        Selection selection = Mockito.mock(Selection.class);

        AutomationElement element1 = Mockito.mock(AutomationElement.class);
        AutomationElement element2 = Mockito.mock(AutomationElement.class);

        when(table.isAvailable()).thenReturn(true);
        when(grid.isAvailable()).thenReturn(true);
        when(grid.getItem(0,1)).thenReturn(element1);
        when(grid.getItem(1,1)).thenReturn(element2);
        when(grid.rowCount()).thenReturn(2);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationDataGrid dataGrid = new AutomationDataGrid(
                new ElementBuilder(element).addPattern(value, grid, table, selection).automation(instance));

        List<AutomationDataGridCell> list = dataGrid.getColumn(1);

        assertTrue(list.size() == grid.rowCount());
    }

    @Test
    public void testGetRows_Returns_Size_As_RowCount() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        Grid grid = Mockito.mock(Grid.class);
        Table table = Mockito.mock(Table.class);
        Selection selection = Mockito.mock(Selection.class);

        AutomationElement element1 = Mockito.mock(AutomationElement.class);
        AutomationElement element2 = Mockito.mock(AutomationElement.class);

        when(table.isAvailable()).thenReturn(true);
        when(grid.isAvailable()).thenReturn(true);
        when(grid.getItem(0,1)).thenReturn(element1);
        when(grid.getItem(1,1)).thenReturn(element2);
        when(grid.rowCount()).thenReturn(2);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationDataGrid dataGrid = new AutomationDataGrid(
                new ElementBuilder(element).addPattern(value, grid, table, selection).automation(instance));

        List<AutomationDataGridCell> list = dataGrid.getRow(1);

        assertTrue(list.size() == grid.rowCount());
    }

    @Test
    public void testGetRow() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        Grid grid = Mockito.mock(Grid.class);
        Table table = Mockito.mock(Table.class);
        Selection selection = Mockito.mock(Selection.class);

        AutomationElement element1 = Mockito.mock(AutomationElement.class);
        AutomationElement element2 = Mockito.mock(AutomationElement.class);

        when(table.isAvailable()).thenReturn(true);
        when(grid.isAvailable()).thenReturn(true);
        when(grid.getItem(0,1)).thenReturn(element1);
        when(grid.getItem(1,1)).thenReturn(element2);
        when(grid.rowCount()).thenReturn(2);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationDataGrid dataGrid = new AutomationDataGrid(
                new ElementBuilder(element).addPattern(value, grid, table, selection).automation(instance));

        List<AutomationDataGridCell> list = dataGrid.getRow(1);

        assertTrue(list.size() == 2);
    }

    @Test
    public void testGetColumn() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Value value = Mockito.mock(Value.class);
        Grid grid = Mockito.mock(Grid.class);
        Table table = Mockito.mock(Table.class);
        Selection selection = Mockito.mock(Selection.class);

        AutomationElement element1 = Mockito.mock(AutomationElement.class);
        AutomationElement element2 = Mockito.mock(AutomationElement.class);

        when(table.isAvailable()).thenReturn(true);
        when(grid.isAvailable()).thenReturn(true);
        when(grid.getItem(0,1)).thenReturn(element1);
        when(grid.getItem(1,1)).thenReturn(element2);
        when(grid.rowCount()).thenReturn(2);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationDataGrid dataGrid = new AutomationDataGrid(
                new ElementBuilder(element).addPattern(value, grid, table, selection).automation(instance));

        List<AutomationDataGridCell> list = dataGrid.getRow(1);

        assertTrue(list.size() == 2);
    }
}
