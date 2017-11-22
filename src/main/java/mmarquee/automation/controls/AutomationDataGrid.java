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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.pattern.Grid;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.pattern.Table;
import mmarquee.automation.pattern.Value;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.RowOrColumnMajor;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper around the Delphi automated string gridPattern.
 *
 * @author Mark Humphreys
 * Date 03/02/2016.
 */
public class AutomationDataGrid
        extends AutomationBase
        implements Valueable {
    /**
     * The value pattern.
     */
    private Value valuePattern;

    /**
     * The gird pattern.
     */
    private Grid gridPattern;

    /**
     * The table pattern.
     */
    private Table tablePattern;

    /**
     * The selection pattern.
     */
    private Selection selectionPattern;

    /**
     * Construct the AutomationDataGrid.
     *
     * @param builder The builder.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public AutomationDataGrid(final ElementBuilder builder)
            throws PatternNotFoundException, AutomationException {
        super(builder);

        this.valuePattern = builder.getValue();
        this.gridPattern = builder.getGrid();
        this.tablePattern = builder.getTable();
        this.selectionPattern = builder.getSelection();
    }

    /**
     * Gets the text associated with the active cell of this element.
     *
     * @return The value of the item
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    public String getValue()
            throws AutomationException, PatternNotFoundException {
        if (this.valuePattern == null) {
            this.valuePattern = this.getValuePattern();
        }

        return this.valuePattern.value();
    }

    /**
     * Whether the gridPattern is read only.
     *
     * @return Read only?
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    public boolean isReadOnly()
            throws AutomationException, PatternNotFoundException {
        if (this.valuePattern == null) {
            this.valuePattern = this.getValuePattern();
        }

        return this.valuePattern.isReadOnly();
    }

    /**
     * Gets the selected row from the gridPattern.
     *
     * @return List of AutomationStringGridItem
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationDataGridCell> selectedRow()
            throws PatternNotFoundException, AutomationException  {
        if (this.selectionPattern == null) {
            this.selectionPattern = this.getSelectionPattern();
        }

        List<AutomationElement> collection = this.selectionPattern.getCurrentSelection();

        List<AutomationDataGridCell> items = new ArrayList<AutomationDataGridCell>();

        for (AutomationElement item : collection) {
            try {
                items.add(new AutomationDataGridCell(item));
            } catch (NullPointerException ex) {
                // Try and add am empty cell
                AutomationDataGridCell cell = new AutomationDataGridCell(null);
                items.add(cell);
            }
        }

        return items;
    }


    /**
     * Gets the selected item from the gridPattern.
     *
     * @return AutomationStringGridItem
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDataGridCell selected()
            throws PatternNotFoundException, AutomationException  {
        if (this.selectionPattern == null) {
            this.selectionPattern = this.getSelectionPattern();
        }
        List<AutomationElement> collection = selectionPattern.getCurrentSelection();

        return new AutomationDataGridCell(collection.get(0));
    }

    /**
     * Gets the list of the column headers.
     *
     * @return List of GridItems
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationDataGridCell> getColumnHeaders ()
            throws PatternNotFoundException, AutomationException  {
        if (this.tablePattern == null) {
            this.tablePattern = this.getTablePattern();
        }

        List<AutomationElement> collection = this.tablePattern.getCurrentColumnHeaders();

        List<AutomationDataGridCell> items = new ArrayList<AutomationDataGridCell>();

        for (AutomationElement item : collection) {
            try {
                items.add(new AutomationDataGridCell(item));
            } catch (NullPointerException ex) {
                // Try and add am empty cell
                AutomationDataGridCell cell = new AutomationDataGridCell(null);
                items.add(cell);
            }
        }

        return items;
    }

    /**
     * Gets the item associated with the given cell, using search criteria.
     *
     * @param search Search criteria
     * @return The GridItem at the given cell position
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDataGridCell getItem(final Search search)
            throws PatternNotFoundException, AutomationException  {
        if (search.getHasRow() || search.getHasColumn()) {
            return getItem(search.getRow(), search.getColumn());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the item associated with the given cell.
     *
     * @param row X Offset
     * @param column Y Offset
     * @return The GridItem at the given cell position
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDataGridCell getItem(final int row,
                                          final int column)
            throws PatternNotFoundException, AutomationException  {
        if (this.gridPattern == null) {
            this.gridPattern = this.getGridPattern();
        }

        return new AutomationDataGridCell(this.gridPattern.getItem(row, column));
    }

    /**
     * Gets the row count of the gridPattern.
     *
     * @return The row count
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    public int rowCount() throws AutomationException, PatternNotFoundException {
        if (this.gridPattern == null) {
            this.gridPattern = this.getGridPattern();
        }
        return this.gridPattern.rowCount();
    }

    /**
     * Gets the column count of the gridPattern.
     *
     * @return The column count
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    public int columnCount() throws AutomationException, PatternNotFoundException {
        if (this.gridPattern == null) {
            this.gridPattern = this.getGridPattern();
        }
        return this.gridPattern.columnCount();
    }

    /**
     * Gets the cells for the given row.
     *
     * @param row The row
     * @return Collection of cells for the given row
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationDataGridCell> getRow(final int row)
            throws PatternNotFoundException, AutomationException {
        List<AutomationDataGridCell> items = new ArrayList<AutomationDataGridCell>();

        for (int count = 0; count < this.rowCount(); count++) {
            AutomationDataGridCell cell = this.getItem(row, count);
            items.add(cell);
        }

        return items;
    }

    /**
     * Gets the cells for the given column.
     *
     * @param col The column
     * @return Collection of cells for the given column
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationDataGridCell> getColumn(final int col)
            throws PatternNotFoundException, AutomationException {
        List<AutomationDataGridCell> items = new ArrayList<AutomationDataGridCell>();

        for (int count = 0; count < this.rowCount(); count++) {
            try {
                AutomationDataGridCell cell = this.getItem(count, col);
                items.add(cell);
            } catch (NullPointerException ex) {
                // Try and add am empty cell
                AutomationDataGridCell cell = new AutomationDataGridCell(null);
                items.add(cell);
            }
        }

        return items;
    }

    /**
     * Gets the column headers for the given column.
     *
     * @param col The column
     * @return The header cell
     * @throws AutomationException Error in automation library
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDataGridCell getColumnHeader(final int col)
            throws PatternNotFoundException, AutomationException {
        List<AutomationDataGridCell> headers = this.getColumnHeaders();

        return headers.get(col);
    }

    /**
     * Returns whether the grid has column or row headers.
     *
     * @return RowOrColumnMajor Row or column
     * @throws AutomationException Error thrown from automation library
     * @throws PatternNotFoundException Failed to find pattern
     */
    public RowOrColumnMajor getRowOrColumnMajor()
            throws AutomationException, PatternNotFoundException {
        if (this.tablePattern == null) {
            this.tablePattern = this.getTablePattern();
        }

        return this.tablePattern.getRowOrColumnMajor();
    }

    /**
     * Is multiple selection allowed.
     *
     * @return True is multiple selection is allowed
     * @throws AutomationException Error thrown from automation library
     * @throws PatternNotFoundException Failed to find pattern
     */
    public boolean canSelectMultiple() throws AutomationException, PatternNotFoundException {
        if (this.selectionPattern == null) {
            this.selectionPattern = this.getSelectionPattern();
        }

        return this.selectionPattern.canSelectMultiple();
    }

    /**
     * Gets the selection from the data grid.
     *
     * @return The list of selected elements.
     * @throws AutomationException An automation error has occurred.
     * @throws PatternNotFoundException Pattern was not found.
     */
    public List<AutomationElement> getSelection()
            throws AutomationException, PatternNotFoundException {
        if (this.selectionPattern == null) {
            this.selectionPattern = this.getSelectionPattern();
        }

        return this.selectionPattern.getSelection();
    }
}
