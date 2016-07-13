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

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.pattern.*;
import mmarquee.automation.uiautomation.IUIAutomationElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 03/02/2016.
 *
 * Wrapper around the Delphi automated string grid
 */
public class AutomationDataGrid extends AutomationBase
{
    private Value valuePattern;
    private Grid grid;
    private Table tablePattern;
    private Selection selectionPattern;

    /**
     * Construct the AutomationDataGrid
     * @param element The element
     */
    public AutomationDataGrid(AutomationElement element) {
        super(element);

        try {
            this.valuePattern = this.getValuePattern();
            this.grid = this.getGridPattern();
            this.tablePattern = this.getTablePattern();
            this.selectionPattern = this.getSelectionPattern();
        } catch (PatternNotFoundException ex) {
            // Handle this nicely somehow
        }
    }

    /**
     * Gets the text associated with the active cell of this element
     * @return The value of the item
     */
    public String getValue() {
        return this.valuePattern.value();
    }

    /**
     * Whether the grid is read only
     * @return Read only?
     */
    public boolean isReadOnly() {
        return this.valuePattern.isReadOnly();
    }

    /**
     * Gets the selected item from the grid
     * @return AutomationStringGridItem
     */
    public AutomationDataGridCell selected() {
        List<AutomationElement> collection = selectionPattern.getCurrentSelection();

        return new AutomationDataGridCell(collection.get(0));
    }

    /**
     * Gets the list of the column headers
     * @return List of GridItems
     */
    public List<AutomationDataGridCell> getColumnHeaders () {

        List<AutomationElement> collection = tablePattern.getCurrentColumnHeaders();

        List<AutomationDataGridCell> items = new ArrayList<AutomationDataGridCell>();

        for(AutomationElement item : collection) {
            items.add(new AutomationDataGridCell(item));
        }

        return items;
    }

    /**
     * Gets the item associated with the given cell
     * @param x X Offset
     * @param y Y Offset
     * @return The GridItem at the given cell position
     */
    public AutomationDataGridCell getItem(int x, int y) {

        PointerByReference cell = this.grid.getItem(x, y);

        Unknown uRoot = new Unknown(cell.getValue());

        Guid.REFIID refiidElement = new Guid.REFIID(IUIAutomationElement.IID);

        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT result0 = uRoot.QueryInterface(refiidElement, pbr);

        if (COMUtils.SUCCEEDED(result0)) {

            return new AutomationDataGridCell(
                new AutomationElement(IUIAutomationElement.Converter.PointerToInterface(pbr)));
        } else {
            return null;
        }
    }
}
