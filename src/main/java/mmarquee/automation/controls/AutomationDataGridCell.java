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
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.GridItem;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.SelectionItem;
import mmarquee.automation.pattern.Value;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Wrapper around the 'virtual' cell element in the automated Delphi string grid.
 *
 * @author Mark Humphreys
 * Date 04/02/2016.
 */
public class AutomationDataGridCell extends AutomationBase implements Valueable {

    /**
     * The value pattern.
     */
    private Value valuePattern;

    /**
     * The gridItem pattern.
     */
    private GridItem gridItemPattern;

    /**
     * The selectionItem pattern.
     */
    private SelectionItem selectionItemPattern;

    /**
     * The invoke pattern.
     */
    private Invoke invokePattern;

    /**
     * Construct the AutomationDataGridCell
     * @param element The element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDataGridCell(final AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(new ElementBuilder(element));
    }

    /**
     * Construct the AutomationDataGridCell.
     * @param element The element.
     * @param value The Value pattern.
     * @param instance Automation instance.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    AutomationDataGridCell(final AutomationElement element,
                           final Value value,
                           final UIAutomation instance) throws PatternNotFoundException, AutomationException {
        super(new ElementBuilder(element).automation(instance));
        this.valuePattern = value;
    }

    /**
     * Construct the AutomationDataGridCell.
     *
     * @param element The element.
     * @param value The Value pattern.
     * @param grid The GridItem pattern.
     * @param selectionItem The SelectionItem pattern.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    AutomationDataGridCell(AutomationElement element, Value value, GridItem grid, SelectionItem selectionItem) throws PatternNotFoundException, AutomationException {
        super(new ElementBuilder(element));
        this.valuePattern = value;
        this.gridItemPattern = grid;
        this.selectionItemPattern = selectionItem;
    }

    /**
     * Gets the text associated with this element.
     * @return The current value.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    public String getValue() throws AutomationException, PatternNotFoundException {
        if (this.valuePattern == null) {
            try {
                this.valuePattern = this.getValuePattern();
            } catch (NullPointerException ex) {
                logger.info("No value pattern available");
            }
        }

        try {
            return valuePattern.value();
        } catch (NullPointerException ex) {
            return "<Empty>";
        }
    }

    /**
     * Calls the invoke on the cell.
     *
     * @throws AutomationException Something went wrong in automation.
     * @throws PatternNotFoundException Didn't find the pattern.
     */
    public void invoke() throws AutomationException, PatternNotFoundException {
        super.invoke();
    }

    /**
     * Sets the text associated with this element.
     * @param value The value to set.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    public void setValue(String value) throws AutomationException, PatternNotFoundException {
        if (this.valuePattern == null) {
            this.valuePattern = this.getValuePattern();
        }

        valuePattern.setValue(value);
    }

    /**
     * Gets the current row for this element.
     * @return The row.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    public int getRow() throws AutomationException, PatternNotFoundException {
        if (this.gridItemPattern == null) {
            this.gridItemPattern = this.getGridItemPattern();
        }

        return this.gridItemPattern.getRow();
    }

    /**
     * Gets the current column for this element.
     * @return The column.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    public int getColumn() throws AutomationException, PatternNotFoundException {
        if (this.gridItemPattern == null) {
            this.gridItemPattern = this.getGridItemPattern();
        }

        return this.gridItemPattern.getColumn();
    }

    /**
     * Selects the cell.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    public void select() throws AutomationException, PatternNotFoundException {
        if (this.selectionItemPattern == null) {
            this.selectionItemPattern = this.getSelectItemPattern();
        }

        this.selectionItemPattern.select();
    }

    /**
     * Adds to the selection.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    public void addToSelection() throws AutomationException, PatternNotFoundException {
        if (this.selectionItemPattern == null) {
            this.selectionItemPattern = this.getSelectItemPattern();
        }

        this.selectionItemPattern.addToSelection();
    }

    /**
     * Removes the cell from the selection.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    public void removeFromSelection() throws AutomationException, PatternNotFoundException {
        if (this.selectionItemPattern == null) {
            this.selectionItemPattern = this.getSelectItemPattern();
        }

        this.selectionItemPattern.removeFromSelection();
    }

    //   public boolean isReadOnly() {
 //       int value = valuePattern.isReadOnly();
 //       return (value == 1);
 //   }

 //   /**
 //    * Sets the value
 //    * @param value The value to set
 //    */
 //   public void setValue(String value) {
 //       this.valuePattern.setValue(value);
 //   }
}
