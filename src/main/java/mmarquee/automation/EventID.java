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
 * Automation Event Identifiers.
 *
 * @author Mark Humphreys
 * Date 24/05/2016.
 */
public enum EventID {
    /**
     * Tool tip opened.
     */
    ToolTipOpened(20000),

    /**
     * Tool tip closed.
     */
    ToolTipClosed(20001),

    /**
     * Structure changed.
     */
    StructureChanged(20002),

    /**
     * Menu opened.
     */
    MenuOpened(20003),

    /**
     * Property changed.
     */
    PropertyChanged(20004),

    /**
     * Focus changed.
     */
    FocusChanged(20005),

    /**
     * Async Content Loaded.
     */
    AsyncContentLoaded(20006),

    /**
     * Menu closed.
     */
    MenuClosed(20007),

    /**
     * Layout invalidated.
     */
    LayoutInvalidated(20008),

    /**
     * Invoke invoked.
     */
    Invoke_Invoked(20009),

    /**
     * SelectionItem Element Added To Selection.
     */
    SelectionItem_ElementAddedToSelection(20010),

    /**
     * SelectionItem Element Removed To Selection.
     */
    SelectionItem_ElementRemovedFromSelection(20011),

    /**
     * SelectionItem Element Selection.
     */
    SelectionItem_ElementSelected(20012),

    /**
     * Selection invalidated.
     */
    Selection_Invalidated(20013),

    /**
     * Text selection changed.
     */
    Text_TextSelectionChanged(20014),

    /**
     * Text text changed.
     */
    Text_TextChanged(20015),

    /**
     * Window opened.
     */
    Window_WindowOpened(20016),

    /**
     * Window closed.
     */
    Window_WindowClosed(20017),

    /**
     * Menu mode start.
     */
    MenuModeStart(20018),

    /**
     * Menu mode ended.
     */
    MenuModeEnd(20019),

    /**
     * Input reached target.
     */
    InputReachedTarget(20020),

    /**
     * Input reached other element.
     */
    InputReachedOtherElement(20021),

    /**
     * Input discarded.
     */
    InputDiscarded(20022);

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
     * Constructor for EventID.
     *
     * @param theValue the value to use
     */
    EventID(final int theValue) {
        this.value = theValue;
    }
}
