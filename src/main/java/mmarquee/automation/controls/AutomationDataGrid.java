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

import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.pattern.*;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.RowOrColumnMajor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Humphreys on 03/02/2016.
 *
 * Wrapper around the Delphi automated string gridPattern
 */
public class AutomationDataGrid extends AutomationBase
{
    private Value valuePattern;
    private Grid gridPattern;
    private Table tablePattern;
    private Selection selectionPattern;

    /**
     * Construct the AutomationDataGrid
     * @param element The element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDataGrid(AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);

//        this.valuePattern = this.getValuePattern();
//        this.gridPattern = this.getGridPattern();
//        this.tablePattern = this.getTablePattern();
//        this.selectionPattern = this.getSelectionPattern();
    }

    /**
     * Construct the AutomationDataGrid
     * @param element The element
     * @param value Value pattern
     * @param grid Grid pattern
     * @param table Table pattern
     * @param selection Selection pattern
     */
    public AutomationDataGrid(AutomationElement element, Value value, Grid grid, Table table, Selection selection) {
        super(element);

        this.valuePattern = value;
        this.gridPattern = grid;
        this.tablePattern = table;
        this.selectionPattern = selection;
    }

    /**
     * Gets the text associated with the active cell of this element
     * @return The value of the item
     * @throws AutomationException Something has gone wrong
     */
    public String getValue() throws AutomationException, PatternNotFoundException {
        if (this.valuePattern == null) {
            this.valuePattern = this.getValuePattern();
        }
        return this.valuePattern.value();
    }

    /**
     * Whether the gridPattern is read only
     * @return Read only?
     * @throws AutomationException Something has gone wrong
     */
    public boolean isReadOnly() throws AutomationException, PatternNotFoundException {
        if (this.valuePattern == null) {
            this.valuePattern = this.getValuePattern();
        }
        return this.valuePattern.isReadOnly();
    }

    /**
     * Gets the selected item from the gridPattern
     * @return AutomationStringGridItem
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDataGridCell selected() throws PatternNotFoundException, AutomationException  {
        List<AutomationElement> collection = selectionPattern.getCurrentSelection();

        return new AutomationDataGridCell(collection.get(0));
    }

    /**
     * Gets the list of the column headers
     * @return List of GridItems
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationDataGridCell> getColumnHeaders () throws PatternNotFoundException, AutomationException  {

        List<AutomationElement> collection = tablePattern.getCurrentColumnHeaders();

        List<AutomationDataGridCell> items = new ArrayList<AutomationDataGridCell>();

        for (AutomationElement item : collection) {
            items.add(new AutomationDataGridCell(item));
        }

        return items;
    }

    /**
     * Gets the item associated with the given cell
     * @param x X Offset
     * @param y Y Offset
     * @return The GridItem at the given cell position
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDataGridCell getItem(int x, int y) throws PatternNotFoundException, AutomationException  {

        AutomationElement element = this.gridPattern.getItem(x, y);

        return new AutomationDataGridCell(element);
    }

    /**
     * Gets the row count of the gridPattern
     * @return The row count
     * @throws AutomationException Something has gone wrong
     */
    public int rowCount() throws AutomationException, PatternNotFoundException {
        if (this.gridPattern == null) {
            this.gridPattern = this.getGridPattern();
        }
        return this.gridPattern.rowCount();
    }

    /**
     * Gets the column count of the gridPattern
     * @return The column count
     * @throws AutomationException Something has gone wrong
     */
    public int columnCount() throws AutomationException, PatternNotFoundException {
        if (this.gridPattern == null) {
            this.gridPattern = this.getGridPattern();
        }
        return this.gridPattern.columnCount();
    }

    /**
     * Gets the cells for the given row
     * @param row The row
     * @return Collection of cells for the given row
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationDataGridCell> getRow(int row) throws PatternNotFoundException, AutomationException {
        List<AutomationDataGridCell> items = new ArrayList<AutomationDataGridCell>();

        for (int count = 0; count < this.rowCount(); count++) {
            AutomationDataGridCell cell = this.getItem(row, count);
            items.add(cell);
        }

        return items;
    }

    /**
     * Gets the cells for the given column
     * @param col The column
     * @return Collection of cells for the given column
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationDataGridCell> getColumn(int col) throws PatternNotFoundException, AutomationException {
        List<AutomationDataGridCell> items = new ArrayList<AutomationDataGridCell>();

        for (int count = 0; count < this.rowCount(); count++) {
            AutomationDataGridCell cell = this.getItem(count, col);
            items.add(cell);
        }

        return items;
    }

    /**
     * Gets the column headers for the given column
     * @param col The column
     * @return The header cell
     * @throws AutomationException Error in automation library
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDataGridCell getColumnHeader(int col) throws PatternNotFoundException, AutomationException {
        List<AutomationDataGridCell> headers = this.getColumnHeaders();

        return headers.get(col);
    }

    /**
     * Returns whether the grid has column or row headers
     * @return RowOrColumnMajor Row or column
     * @throws AutomationException Error thrown from automation library
     */
    public RowOrColumnMajor getRowOrColumnMajor() throws AutomationException, PatternNotFoundException {
        if (this.tablePattern == null) {
            this.tablePattern = this.getTablePattern();
        }
        
        return this.tablePattern.getRowOrColumnMajor();
    }
}

