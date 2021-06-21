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

import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.Element;
import mmarquee.automation.PatternID;
import mmarquee.automation.pattern.Grid;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Table;
import mmarquee.uiautomation.RowOrColumnMajor;

import java.util.ArrayList;
import java.util.List;

/**
 * Wrapper around the Delphi automated string gridPattern.
 *
 * @author Mark Humphreys
 * Date 03/02/2016.
 */
public final class DataGrid
        extends AutomationBase
        implements ImplementsValue,
                   ImplementsChildSelect,
                   ImplementsGrid,
                   ImplementsTable {

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
    public List<DataGridCell> getColumnHeaders()
            throws PatternNotFoundException, AutomationException {
        return this.getCurrentColumnHeaders();

   //     return convertListToAutomationDataGridCells(collection);
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
        return this.getCurrentRowHeaders();

   //     return convertListToAutomationDataGridCells(collection);
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

    /**
     * Gets the row count for the grid.
     *
     * @return Number of rows
     * @throws PatternNotFoundException Failed to find the correct pattern
     * @throws AutomationException Error in Automation Library
     */
    public int getRowCount()
            throws PatternNotFoundException, AutomationException {
        final Grid pattern = requestAutomationPattern(Grid.class);
        if (pattern.isAvailable()) {
            return (pattern.rowCount());
        }
        throw new PatternNotFoundException("Cannot get row count");
    }

    /**
     * Gets the column count for the grid.
     *
     * @return Number of columns
     * @throws PatternNotFoundException Failed to find the correct pattern
     * @throws AutomationException Error in Automation Library
     */
    public int getColumnCount()
            throws PatternNotFoundException, AutomationException {
        final Grid pattern = requestAutomationPattern(Grid.class);
        if (pattern.isAvailable()) {
            return (pattern.columnCount());
        }
        throw new PatternNotFoundException("Cannot get column count");
    }

    /**
     * Gets the list of the column headers.
     *
     * @return List of GridItems
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<DataGridCell> getCurrentColumnHeaders ()
            throws PatternNotFoundException, AutomationException {
        if (isGridPatternAvailable()) {
            Table pattern = this.getTablePattern();

            List<Element> collection = pattern.getCurrentColumnHeaders();

            List<DataGridCell> items = new ArrayList<>();

            for (Element item : collection) {
                try {
                    items.add(new DataGridCell(new ElementBuilder(item)));
                } catch (NullPointerException ex) {
                    // Try and add am empty cell
                    DataGridCell cell = new DataGridCell(null);
                    items.add(cell);
                }
            }

            return items;
        } else {
            throw new PatternNotFoundException("Pattern not available");
        }
    }

    /**
     * Gets the row headers for the grid.
     * @return The list of column header
     * @throws AutomationException Something has gone wrong
     */
    public List<DataGridCell> getCurrentRowHeaders() throws AutomationException {
        if (isGridPatternAvailable()) {
            Table pattern = this.getTablePattern();

            List<Element> collection = pattern.getCurrentRowHeaders();

            List<DataGridCell> items = new ArrayList<>();

            for (Element item : collection) {
                try {
                    items.add(new DataGridCell(new ElementBuilder(item)));
                } catch (NullPointerException ex) {
                    // Try and add am empty cell
                    DataGridCell cell = new DataGridCell(null);
                    items.add(cell);
                }
            }

            return items;
        } else {
            throw new PatternNotFoundException("Pattern not available");
        }
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
        if (isGridPatternAvailable()) {
            Table pattern = this.getTablePattern();

            return pattern.getRowOrColumnMajor();
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    private PointerByReference getPattern(final int id)
        throws AutomationException {
        PointerByReference unknown = this.getElement().getPattern(id);

        if (unknown != null) {
            return unknown;
        } else {
            throw new PatternNotFoundException();
        }
    }

    private Table getTablePattern() throws AutomationException {
        if (isTablePatternAvailable()) {
            PointerByReference unknown = this.getPattern(PatternID.Table.getValue());

            //   if (unknown instanceof PatternProvider) { // Hook for mocking
            //   // tests
            //       return (Table)((PatternProvider) unknown).getPattern();
            //   }

            Table pattern = new Table(this.getElement());
            pattern.setPattern(unknown.getValue());

            return pattern;
        }
        return null;
    }
}
