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
 * Created by Mark Humphreys on 28/01/2016.
 *
 * Automation Pattern Identifiers
 * See - https://msdn.microsoft.com/en-us/library/windows/desktop/ee671195%28v=vs.85%29.aspx
 */

public enum PatternID {
    Invoke(10000),
    Selection(10001),
    Value(10002),
    RangeValue(10003),
    Scroll(10004),
    ExpandCollapse(10005),
    Grid(10006),
    GridItem(10007),
    MultipleView(10008),
    Window(10009),
    SelectionItem(10010),
    Dock(10011),
    Table(10012),
    TableItem(10013),
    Text(10014),
    Toggle(10015),
    Transform(10016),
    ScrollItem(10017),
    LegacyIAccessible(10018),
    ItemContainer(10019),
    VirtualizedItem(10020),
    SynchronizedInput(10021),
    ObjectModel(10022),
    Annotation(10023),
    TextPattern2Id(10024),
    Styles(10025),
    Spreadsheet(10026),
    SpreadsheetItem(10027),
    TransformPattern2Id(10028),
    TextChild(10029),
    Drag(10030),
    DropTarget(10031),
    TextEdit(10032),
    CustomNavigation(10033);

    private int value;

    public int getValue() {
        return this.value;
    }

    PatternID (int value) {
        this.value = value;
    }
}