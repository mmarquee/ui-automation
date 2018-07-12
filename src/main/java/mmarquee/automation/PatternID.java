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
package mmarquee.automation;

/**
 * Automation Pattern Identifiers.
 *
 * @author Mark Humphreys
 * Date 28/01/2016.
 *
 * See - https://msdn.microsoft.com/en-us/library/windows/desktop/ee671195%28v=vs.85%29.aspx
 */

public enum PatternID {

    /**
     * Invoke pattern.
     */
    Invoke(10000),

    /**
     * Selection pattern.
     */
    Selection(10001),

    /**
     * Value pattern.
     */
    Value(10002),

    /**
     * RangeValue pattern.
     */
    RangeValue(10003),

    /**
     * Scroll pattern.
     */
    Scroll(10004),

    /**
     * ExpandCollapse pattern.
     */
    ExpandCollapse(10005),

    /**
     * Grid pattern.
     */
    Grid(10006),

    /**
     * GridItem pattern.
     */
    GridItem(10007),

    /**
     * MultipleView pattern.
     */
    MultipleView(10008),

    /**
     * Window pattern.
     */
    Window(10009),

    /**
     * SelectionItem pattern.
     */
    SelectionItem(10010),

    /**
     * Dock pattern.
     */
    Dock(10011),

    /**
     * Table pattern.
     */
    Table(10012),

    /**
     * TableItem pattern.
     */
    TableItem(10013),

    /**
     * Text pattern.
     */
    Text(10014),

    /**
     * Toggle pattern.
     */
    Toggle(10015),

    /**
     * Transform pattern.
     */
    Transform(10016),

    /**
     * ScrollItem pattern.
     */
    ScrollItem(10017),

    /**
     * LegacyIAccessible(10018), pattern.
     */
    LegacyIAccessible(10018),

    /**
     * ItemContainer pattern.
     */
    ItemContainer(10019),

    /**
     * VirtualizedItem pattern.
     */
    VirtualizedItem(10020),

    /**
     * SynchronizedInput patternn.
     */
    SynchronizedInput(10021),

    /**
     * ObjectModel pattern.
     */
    ObjectModel(10022),

    /**
     * Annotation pattern.
     */
    Annotation(10023),

    /**
     * Text2 pattern.
     */
    Text2(10024),

    /**
     * Styles pattern.
     */
    Styles(10025),

    /**
     * Spreadsheet pattern.
     */
    Spreadsheet(10026),

    /**
     * SpreadsheetItem pattern.
     */
    SpreadsheetItem(10027),

    /**
     * Transform2 pattern.
     */
    Transform2(10028),

    /**
     * TextChild pattern.
     */
    TextChild(10029),

    /**
     * Drag pattern.
     */
    Drag(10030),

    /**
     * DropTarget pattern.
     */
    DropTarget(10031),

    /**
     * TextEdit pattern.
     */
    TextEdit(10032),

    /**
     * CustomNavigation pattern.
     */
    CustomNavigation(10033);

    /**
     * The actual value.
     */
    private int value;

    /**
     * Gets the value.
     * @return The actual value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Constructor for PatterID.
     *
     * @param value Initial value.
     */
    PatternID(int value) {
        this.value = value;
    }
}