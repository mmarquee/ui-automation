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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Wrapper around the Delphi automated string gridPattern.
 *
 * @author Mark Humphreys
 * Date 03/02/2016.
 */
public final class AutomationDataGrid
        extends AutomationBase
        implements ImplementsValue, ImplementsChildSelect,
            ImplementsGrid, ImplementsTable {

    /**
     * Construct the AutomationDataGrid.
     *
     * @param builder The builder.
     */
    public AutomationDataGrid(final ElementBuilder builder) {
        super(builder);
    }

    /**
     * Gets the selected row from the gridPattern.
     *
     * @return List of AutomationStringGridItem
     * @throws AutomationException      Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationDataGridCell> selectedRow()
            throws PatternNotFoundException, AutomationException {

        List<AutomationElement> collection = getCurrentSelection();

        return convertListToAutomationDataGridCells(collection);
    }

    /**
     * Gets the selected item from the gridPattern.
     *
     * @return AutomationStringGridItem
     * @throws AutomationException      Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDataGridCell selected()
            throws PatternNotFoundException, AutomationException {

        List<AutomationElement> collection = getCurrentSelection();

        return new AutomationDataGridCell(
                new ElementBuilder(collection.get(0)));
    }

    /**
     * Gets the list of the column headers.
     *
     * @return List of GridItems
     * @throws AutomationException      Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationDataGridCell> getColumnHeaders()
            throws PatternNotFoundException, AutomationException {
        List<AutomationElement> collection = this.getCurrentColumnHeaders();

        return convertListToAutomationDataGridCells(collection);
    }

    /**
     * Gets the list of the row headers.
     *
     * @return List of GridItems
     * @throws AutomationException      Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationDataGridCell> getRowHeaders()
            throws PatternNotFoundException, AutomationException {
        List<AutomationElement> collection = this.getCurrentRowHeaders();

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
    public AutomationDataGridCell getItem(final Search search)
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
    public AutomationDataGridCell getItem(final int row,
                                          final int column)
            throws PatternNotFoundException, AutomationException {
        return new AutomationDataGridCell(
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
    public List<AutomationDataGridCell> getRow(final int row)
            throws PatternNotFoundException, AutomationException {
        List<AutomationDataGridCell> items = new ArrayList<>();

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
     * @throws AutomationException      Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationDataGridCell> getColumn(final int col)
            throws PatternNotFoundException, AutomationException {
        List<AutomationDataGridCell> items = new ArrayList<>();

        for (int count = 0; count < this.rowCount(); count++) {
            try {
                AutomationDataGridCell cell = this.getItem(count, col);
                items.add(cell);
            } catch (NullPointerException ex) {
                // Try and add am empty cell
                AutomationDataGridCell cell = new AutomationDataGridCell(new
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
    public AutomationDataGridCell getColumnHeader(final int col)
            throws PatternNotFoundException, AutomationException {
        List<AutomationDataGridCell> headers = this.getColumnHeaders();

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
    public AutomationDataGridCell getRowHeader(final int row)
            throws PatternNotFoundException, AutomationException {
        List<AutomationDataGridCell> headers = this.getRowHeaders();

        return headers.get(row);
    }

    /**
     * Converts a list of automation elements to a list of data grid cells,
     * for ease of access.
     *
     * @param collection The list of raw AutomationElements
     * @return The converted list of Data Cell Grids
     */
    List<AutomationDataGridCell> convertListToAutomationDataGridCells(
            final List<AutomationElement> collection) {
        List<AutomationDataGridCell> items = new ArrayList<>();

        for (AutomationElement item : collection) {
            try {
                items.add(new AutomationDataGridCell(new ElementBuilder(item)));
            } catch (NullPointerException ex) {
                // Try and add am empty cell
                AutomationDataGridCell cell = new AutomationDataGridCell(new
                        ElementBuilder());
                items.add(cell);
            }
        }

        return items;
    }
}
