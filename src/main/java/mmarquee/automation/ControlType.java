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
 * Enumeration for the control-type.
 *
 * @author Mark Humphreys
 * Date 27/01/2016.
 *
 * Control types - see https://msdn.microsoft.com/en-us/library/windows/desktop/ee671198(v=vs.85).aspx
 */
public enum ControlType {
    /**
     * No control type.
     */
    None(0),

    /**
     * Button.
     */
    Button(50000),

    /**
     * Calendar.
     */
    Calendar(50001),

    /**
     * CheckBox.
     */
    CheckBox(50002),

    /**
     * ComboBox.
     */
    ComboBox(50003),

    /**
     * Edit.
     */
    Edit(50004),

    /**
     * Hyperlink.
     */
    Hyperlink(50005),

    /**
     * Image.
     */
    Image(50006),

    /**
     * ListItem.
     */
    ListItem(50007),

    /**
     * List.
     */
    List(50008),

    /**
     * Menu.
     */
    Menu(50009),

    /**
     * MenuBar.
     */
    MenuBar(50010),

    /**
     * MenuItem.
     */
    MenuItem(50011),

    /**
     * ProgressBar.
     */
    ProgressBar(50012),

    /**
     * ProgressBAr.
     */
    RadioButton(50013),

    /**
     * ScrollBar.
     */
    ScrollBar(50014),

    /**
     * Slider.
     */
    Slider(50015),

    /**
     * Spinner.
     */
    Spinner(50016),

    /**
     * StatusBar.
     */
    StatusBar(50017),

    /**
     * Tab (page control).
     */

    Tab(50018),
    /**
     * TabItem.
     */
    TabItem(50019),

    /**
     * Text.
     */
    Text(50020),

    /**
     * Toolbar.
     */
    ToolBar(50021),

    /**
     * ToolTip.
     */
    ToolTip(50022),

    /**
     * Tree.
     */
    Tree(50023),

    /**
     * TreeItem.
     */
    TreeItem(50024),

    /**
     * TreeItem.
     */

    Custom(50025),

    /**
     * Group.
     */
    Group(50026),

    /**
     * Thumb.
     */
    Thumb(50027),

    /**
     * DataGrid.
     */
    DataGrid(50028),

    /**
     * DataItem.
     */
    DataItem(50029),

    /**
     * Document.
     */
    Document(50030),

    /**
     * SplitButton.
     */
    SplitButton(50031),

    /**
     * Window.
     */
    Window(50032),

    /**
     * Pane.
     *
     * A pane or panel.
     */
    Pane(50033),

    /**
     * Header.
     */
    Header(50034),

    /**
     * HeaderItem.
     */
    HeaderItem(50035),

    /**
     * Table.
     */
    Table(50036),

    /**
     * TitleBar.
     */
    TitleBar(50037),

    /**
     * Separator.
     */
    Separator(50038),

    /**
     * SemanticZoom.
     */
    SemanticZoom(50039),

    /**
     * AppBar.
     */
    AppBar(50040);

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
     * Constructor for the ControlType.
     *
     * @param inValue The initial value.
     */
    ControlType(final int inValue) {
        this.value = inValue;
    }

    /**
     * Gets the control type from the value.
     * @param controlTypeValue The value
     * @return The control type
     */
	public static ControlType fromValue(int controlTypeValue) {
		for (ControlType type: values()) {
			if (type.getValue() == controlTypeValue) {
				return type;
			}
		}
		return None;
	}
}