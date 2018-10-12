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

import java.util.ArrayList;
import java.util.List;

import mmarquee.automation.Element;
import mmarquee.automation.AutomationException;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Table;

/**
 * Wrapper around the Delphi automated string gridPattern.
 *
 * @author Mark Humphreys
 * Date 03/02/2016.
 */
public final class DataGrid
        extends AutomationBase
        implements ImplementsValue, ImplementsChildSelect,
            ImplementsGrid, ImplementsTable {

    /**
     * Construct the DataGrid.
     *
     * @param builder The builder.
     */
    public DataGrid(final ElementBuilder builder) {
        super(builder);
    }

    /**
     * Gets the selected row from the gridPattern.
     *
     * @return List of AutomationStringGridItem
     * @throws AutomationException      Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<DataGridCell> selectedRow()
            throws PatternNotFoundException, AutomationException {

        List<Element> collection = getCurrentSelection();

        return convertListToAutomationDataGridCells(collection);
    }

    /**
     * Gets the selected item from the gridPattern.
     *
     * @return AutomationStringGridItem
     * @throws AutomationException      Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public DataGridCell selected()
            throws PatternNotFoundException, AutomationException {

        List<Element> collection = getCurrentSelection();

        return new DataGridCell(
                new ElementBuilder(collection.get(0)));
    }

    /**
     * Gets the list of the column headers.
     *
     * @return List of GridItems
     * @throws AutomationException      Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<DataGridCell> _getColumnHeaders()
            throws PatternNotFoundException, AutomationException {
        List<Element> collection = this.getCurrentColumnHeaders();

        return convertListToAutomationDataGridCells(collection);
    }

    /**
     * Gets the list of the column headers.
     *
     * @return List of GridItems
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<DataGridCell> getColumnHeaders()
            throws PatternNotFoundException, AutomationException  {
        //if (this.tablePattern == null) {
        //    this.tablePattern = this.getTablePattern();
        //}

        Table tablePattern = this.getTablePattern();

        List<Element> collection = this.tablePattern.getCurrentColumnHeaders();

        List<DataGridCell> items = new ArrayList<>();

        for (Element item: collection) {
            try {
                items.add(new DataGridCell(new ElementBuilder(item)));
            } catch (NullPointerException ex) {
                // Try and add an empty cell
                DataGridCell cell = new DataGridCell(null);
                items.add(cell);
            }
        }

        return items;
    }

    /**
     * Gets the list of the row headers.
     *
     * @return List of GridItems
     * @throws AutomationException      Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<DataGridCell> getRowHeaders()
            throws PatternNotFoundException, AutomationException {
        List<Element> collection = this.getCurrentRowHeaders();

        return convertListToAutomationDataGridCells(collection);
    }

    /**
     * Gets the item associated with the given cell, using search criteria.
     *
     * @param search Search criteria
     * @return The GridItem at the given cell position
     * @throws AutomationException      Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public DataGridCell getItem(final Search search)
            throws PatternNotFoundException, AutomationException {
        if (search.getHasRow() || search.getHasColumn()) {
            return getItem(search.getRow(), search.getColumn());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the item associated with the given cell.
     *
     * @param row    X Offset
     * @param column Y Offset
     * @return The GridItem at the given cell position
     * @throws AutomationException      Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public DataGridCell getItem(final int row,
                                final int column)
            throws PatternNotFoundException, AutomationException {
        return new DataGridCell(
                new ElementBuilder(getGridItem(row, column)));
    }

    /**
     * Gets the cells for the given row.
     *
     * @param row The row
     * @return Collection of cells for the given row
     * @throws AutomationException      Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<DataGridCell> getRow(final int row)
            throws PatternNotFoundException, AutomationException {
        List<DataGridCell> items = new ArrayList<>();

        for (int count = 0; count < this.rowCount(); count++) {
            DataGridCell cell = this.getItem(row, count);
            items.add(cell);
        }

        return items;
    }

    /**
     * Gets the cells for the given column.
     *
     * @param col The column
     * @return Collection of cells for the given column
     * @throws AutomationException      Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<DataGridCell> getColumn(final int col)
            throws PatternNotFoundException, AutomationException {
        List<DataGridCell> items = new ArrayList<>();

        for (int count = 0; count < this.rowCount(); count++) {
            try {
                DataGridCell cell = this.getItem(count, col);
                items.add(cell);
            } catch (NullPointerException ex) {
                // Try and add am empty cell
                DataGridCell cell = new DataGridCell(new
                        ElementBuilder());
                items.add(cell);
            }
        }

        return items;
    }

    /**
     * Gets the column header for the given column.
     *
     * @param col The column
     * @return The header cell
     * @throws AutomationException      Error in automation library
     * @throws PatternNotFoundException Expected pattern not found
     */
    public DataGridCell getColumnHeader(final int col)
            throws PatternNotFoundException, AutomationException {
        List<DataGridCell> headers = this.getColumnHeaders();

        return headers.get(col);
    }

    /**
     * Gets the row header for the given row.
     *
     * @param row The row
     * @return The header cell
     * @throws AutomationException      Error in automation library
     * @throws PatternNotFoundException Expected pattern not found
     */
    public DataGridCell getRowHeader(final int row)
            throws PatternNotFoundException, AutomationException {
        List<DataGridCell> headers = this.getRowHeaders();

        return headers.get(row);
    }

    /**
     * Converts a list of automation elements to a list of data grid cells,
     * for ease of access.
     *
     * @param collection The list of raw AutomationElements
     * @return The converted list of Data Cell Grids
     */
    List<DataGridCell> convertListToAutomationDataGridCells(
            final List<Element> collection) {
        List<DataGridCell> items = new ArrayList<>();

        for (Element item : collection) {
            try {
                items.add(new DataGridCell(new ElementBuilder(item)));
            } catch (NullPointerException ex) {
                // Try and add am empty cell
                DataGridCell cell = new DataGridCell(new
                        ElementBuilder());
                items.add(cell);
            }
        }

        return items;
    }
}
