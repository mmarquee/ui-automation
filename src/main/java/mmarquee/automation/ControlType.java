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
 * Created by Mark Humphreys on 27/01/2016.
 *
 * Control types - see https://msdn.microsoft.com/en-us/library/windows/desktop/ee671198(v=vs.85).aspx
 */
public enum ControlType {
    None(00000),
    Invoke(10000),
    Button(50000),
    Calendar(50001),
    CheckBox(50002),
    ComboBox(50003),
    Edit(50004),
    Hyperlink(50005),
    Image(50006),
    ListItem(50007),
    List(50008),
    Menu(50009),
    MenuBar(50010),
    MenuItem(50011),
    ProgressBar(50012),
    RadioButton(50013),
    ScrollBar(50014),
    Slider(50015),
    Spinner(50016),
    StatusBar(50017),
    Tab(50018),
    TabItem(50019),
    Text(50020),
    ToolBar(50021),
    ToolTip(50022),
    Tree(50023),
    TreeItem(50024),
    Custom(50025),
    Group(50026),
    Thumb(50027),
    DataGrid(50028),
    DataItem(50029),
    Document(50030),
    SplitButton(50031),
    Window(50032),
    Pane(50033),
    Header(50034),
    HeaderItem(50035),
    Table(50036),
    TitleBar(50037),
    Separator(50038),
    SemanticZoom(50039),
    AppBar(50040);

    private int value;

    public int getValue() {
        return this.value;
    }

    ControlType(int value) {
        this.value = value;
    }
}