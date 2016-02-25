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

package mmarquee.automation;

import mmarquee.automation.pattern.GridPattern;
import mmarquee.automation.pattern.SelectionPattern;
import mmarquee.automation.pattern.TablePattern;
import mmarquee.automation.pattern.ValuePattern;
import mmarquee.automation.uiautomation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 03/02/2016.
 */
public class AutomationStringGrid extends AutomationBase
{
    private ValuePattern valuePattern;
    private GridPattern gridPattern;
    private TablePattern tablePattern;
    private SelectionPattern selectionPattern;

    public AutomationStringGrid(IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        this.valuePattern = this.getValuePattern();
        this.gridPattern = this.getGridPattern();
        this.tablePattern = this.getTablePattern();
        this.selectionPattern = this.getSelectionPattern();
    }

    /**
     * Gets the text associated with the active cell of this element
     * @return The value of the item
     */
    public String getValue() {
        return this.valuePattern.currentValue();
    }

    /**
     * Whether the grid is read only
     * @return Read only?
     */
    public boolean isReadOnly() {
        return this.valuePattern.currentIsReadOnly() == 1;
    }

    /**
     * Gets the selected item from the grid
     * @return AutomationStringGridItem
     */
    public AutomationStringGridItem selected() {
        IUIAutomationElementArray collection = selectionPattern.getCurrentSelection();

        AutomationStringGridItem item = new AutomationStringGridItem(collection.getElement(0), uiAuto);

        return item;
    }

    /**
     * Gets the list of the column headers
     * @return List of GridItems
     */
    public List<AutomationStringGridItem> getColumnHeaders () {

        IUIAutomationElementArray collection = tablePattern.getCurrentColumnHeaders();
        int length = collection.length();

        List<AutomationStringGridItem> items = new ArrayList<AutomationStringGridItem>();

        for (int count = 0; count < length; count++) {
            items.add(new AutomationStringGridItem(collection.getElement(count), uiAuto));
        }

        return items;
    }

    /**
     * Gets the item associated with the given cell
     * @param x X Offset
     * @param y Y Offset
     * @return The GridItem at the given cell position
     */
    public AutomationStringGridItem getItem(int x, int y) {
        return new AutomationStringGridItem(this.gridPattern.getItem(x, y), uiAuto);
    }
}
