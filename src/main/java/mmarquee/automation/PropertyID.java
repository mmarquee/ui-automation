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
 * Created by inpwt on 28/01/2016.
 *
 * Automation Property Identifiers
 * See - https://msdn.microsoft.com/en-us/library/windows/desktop/ee684017(v=vs.85).aspx
 */
public enum PropertyID {
    RuntimeId(30000),
    BoundingRectangle(30001),
    ProcessId(30002),
    ControlType(30003),
    LocalizedControlType(30004),
    Name(30005),
    AcceleratorKey(30006),
    AccessKey(30007),
    HasKeyboardFocus(30008),
    IsKeyboardFocusable(30009),
    IsEnabled(30010),
    AutomationId(30011),
    ClassName(30012),
    HelpText(30013),
    ClickablePoint(30014),
    Culture(30015),
    IsControlElement(30016),
    IsContentElement(30017),
    LabeledBy(30018),
    IsPassword(30019),
    NativeWindowHandle(30020),
    ItemType(30021),
    IsOffscreen(30022),
    Orientation(30023),
    FrameworkId(30024),
    IsRequiredForForm(30025),
    ItemStatus(30026),
    IsDockPatternAvailable(30027),
    IsExpandCollapsePatternAvailable(30028),
    IsGridItemPatternAvailable(30029),
    IsGridPatternAvailable(30030),
    IsInvokePatternAvailable(30031),
    IsMultipleViewPatternAvailable(30032),
    IsRangeValuePatternAvailable(30033),
    IsScrollPatternAvailable(30034),
    IsScrollItemPatternAvailable(30035),
    IsSelectionItemPatternAvailable(30036),
    IsSelectionPatternAvailable(30037),
    IsTablePatternAvailable(30038),
    IsTableItemPatternAvailable(30039),
    IsTextPatternAvailable(30040),
    IsTogglePatternAvailable(30041),
    IsTransformPatternAvailable(30042),
    IsValuePatternAvailable(30043),
    IsWindowPatternAvailable(30044),
    WindowIsModal(30077),
    WindowIsTopmost(30078),
    AriaRole(30101),
    AriaProperties(30102),
    IsDataValidForForm(30103),
    ControllerFor(30104),
    DescribedBy(30105),
    FlowsTo(30106),
    ProviderDescription(30107),
    LiveSetting(30135);

    private int value;

    public int getValue() {
        return this.value;
    }

    PropertyID (int value) {
        this.value = value;
    }
}


