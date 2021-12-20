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

import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.Grid;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.pattern.Table;
import mmarquee.automation.pattern.Value;
import mmarquee.uiautomation.IUIAutomation;
import mmarquee.uiautomation.RowOrColumnMajor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Mark Humphreys
 * Date 28/11/2016.
 */
public class DataGridTest {

    protected Logger logger =
            LoggerFactory.getLogger(DataGridTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testGetName_Get_Name_From_Element() throws Exception {
        Element element = Mockito.mock(Element.class);
        Value value = Mockito.mock(Value.class);
        Grid grid = Mockito.mock(Grid.class);
        Table table = Mockito.mock(Table.class);
        Selection selection = Mockito.mock(Selection.class);

        when(grid.isAvailable()).thenReturn(true);
        when(table.isAvailable()).thenReturn(true);
        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        DataGrid dataGrid = new DataGrid(
                new ElementBuilder(element).addPattern(value, grid, table, selection).automation(instance));

        String name = dataGrid.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetValue_Gets_Value_From_Value_Pattern() throws Exception {
        Element element = Mockito.mock(Element.class);
        Value value = Mockito.mock(Value.class);
        Grid grid = Mockito.mock(Grid.class);
        Table table = Mockito.mock(Table.class);
        Selection selection = Mockito.mock(Selection.class);

        when(value.isAvailable()).thenReturn(true);
        when(grid.isAvailable()).thenReturn(true);
        when(table.isAvailable()).thenReturn(true);
        
        when(value.value()).thenReturn("VALUE");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        DataGrid dataGrid = new DataGrid(
                new ElementBuilder(element).addPattern(value, grid, table, selection).automation(instance));
        String name = dataGrid.getValue();

        assertTrue(name.equals("VALUE"));
    }

    @Test
    @Ignore("Needs reviewing")
    public void testGetRowOrColumn_Gets_Value_From_Grid_Pattern() throws Exception {
        Element element = Mockito.mock(Element.class);
        Value value = Mockito.mock(Value.class);
        Grid grid = Mockito.mock(Grid.class);
        Table table = Mockito.mock(Table.class);
        Selection selection = Mockito.mock(Selection.class);

        when(grid.isAvailable()).thenReturn(true);
        when(table.isAvailable()).thenReturn(true);
        when(table.getRowOrColumnMajor()).thenReturn(RowOrColumnMajor.RowMajor);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        DataGrid dataGrid = new DataGrid(
                new ElementBuilder(element).addPattern(value, grid, table, selection).automation(instance));
        RowOrColumnMajor val = dataGrid.getRowOrColumnMajor();

        assertTrue(val == RowOrColumnMajor.RowMajor);
    }

    @Test
    public void testIsReadOnly_Gets_Result_From_Value_Pattern() throws Exception {
        Element element = Mockito.mock(Element.class);
        Value value = Mockito.mock(Value.class);
        Grid grid = Mockito.mock(Grid.class);
        Table table = Mockito.mock(Table.class);
        Selection selection = Mockito.mock(Selection.class);

        when(grid.isAvailable()).thenReturn(true);
        when(table.isAvailable()).thenReturn(true);
        when(value.isAvailable()).thenReturn(true);
        when(value.isReadOnly()).thenReturn(true);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        DataGrid dataGrid = new DataGrid(
                new ElementBuilder(element).addPattern(value, grid, table, selection).automation(instance));

        boolean val = dataGrid.isReadOnly();

        assertTrue(val);
    }
}
