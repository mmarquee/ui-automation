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

/**
 * Created by Mark Humphreys on 24/05/2016.
 *
 * Automation Event Identifiers
 */
public enum EventID {
    ToolTipOpened(20000),
    ToolTipClosed(20001),
    StructureChanged(20002),
    MenuOpened(20003),
    PropertyChanged(20004),
    FocusChanged(20005),
    AsyncContentLoaded(20006),
    MenuClosed(20007),
    LayoutInvalidated(20008),
    Invoke_Invoked(20009),
    SelectionItem_ElementAddedToSelection(20010),
    SelectionItem_ElementRemovedFromSelection(20011),
    SelectionItem_ElementSelected(20012),
    Selection_Invalidated(20013),
    Text_TextSelectionChanged(20014),
    Text_TextChanged(20015),
    Window_WindowOpened(20016),
    Window_WindowClosed(20017),
    MenuModeStart(20018),
    MenuModeEnd(20019),
    InputReachedTarget(20020),
    InputReachedOtherElement(20021),
    InputDiscarded(20022);

    private int value;

    public int getValue() {
        return this.value;
    }

    EventID(int value) {
        this.value = value;
    }
}
