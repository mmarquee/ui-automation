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
 * Automation Property Identifiers.
 *
 * @author Mark Humphreys
 * Date 28/01/2016.
 * See - https://msdn.microsoft.com/en-us/library/windows/desktop/ee671199(v=vs.85).aspx
 */
public enum PropertyID {
    /**
     * Runtime id.
     */
    RuntimeId(30000),

    /**
     * The bounding rectangle.
     */
    BoundingRectangle(30001),

    /**
     * Process id.
     */
    ProcessId(30002),

    /**
     * Control type.
     */
    ControlType(30003),

    /**
     * Localized control type.
     */
    LocalizedControlType(30004),

    /**
     * Element Name.
     */
    Name(30005),

    /**
     * Accelerator key.
     */
    AcceleratorKey(30006),

    /**
     * Access key.
     */
    AccessKey(30007),

    /**
     * Has keyboard focus.
     */
    HasKeyboardFocus(30008),

    /**
     * Is keyboard focusable.
     */
    IsKeyboardFocusable(30009),

    /**
     * Is enabled.
     */
    IsEnabled(30010),

    /**
     * Automation id.
     */
    AutomationId(30011),

    /**
     * Class name.
     */
    ClassName(30012),

    /**
     * Help text.
     */
    HelpText(30013),

    /**
     * Clickable point.
     */
    ClickablePoint(30014),

    /**
     * Culture.
     */
    Culture(30015),

    /**
     * Is Control element.
     */
    IsControlElement(30016),

    /**
     * Is content element.
     */
    IsContentElement(30017),

    /**
     * Labelled by.
     */
    LabeledBy(30018),

    /**
     * Is password.
     */
    IsPassword(30019),

    /**
     * Native window handle.
     */
    NativeWindowHandle(30020),

    /**
     * Item type.
     */
    ItemType(30021),

    /**
     * Is off screen.
     */
    IsOffscreen(30022),

    /**
     * Orientation.
     */
    Orientation(30023),

    /**
     * Framework id.
     */
    FrameworkId(30024),

    /**
     * Is required for form.
     */
    IsRequiredForForm(30025),

    /**
     * Item status.
     */
    ItemStatus(30026),

    /**
     * IsDockPatternAvailable.
     */
    IsDockPatternAvailable(30027),

    /**
     * IsExpandCollapsePatternAvailable.
     */
    IsExpandCollapsePatternAvailable(30028),

    /**
     * IsGridItemPatternAvailable.
     */
    IsGridItemPatternAvailable(30029),

    /**
     * IsGridPatternAvailable.
     */
    IsGridPatternAvailable(30030),

    /**
     * IsInvokePatternAvailable.
     */
    IsInvokePatternAvailable(30031),

    /**
     * IsMultipleViewPatternAvailable.
     */
    IsMultipleViewPatternAvailable(30032),

    /**
     * IsRangeValuePatternAvailable.
     */
    IsRangeValuePatternAvailable(30033),

    /**
     * IsScrollPatternAvailable.
     */
    IsScrollPatternAvailable(30034),

    /**
     * IsScrollItemPatternAvailable.
     */
    IsScrollItemPatternAvailable(30035),

    /**
     * IsSelectionItemPatternAvailable.
     */
    IsSelectionItemPatternAvailable(30036),

    /**
     * IsSelectionPatternAvailable.
     */
    IsSelectionPatternAvailable(30037),

    /**
     * IsTablePatternAvailable.
     */
    IsTablePatternAvailable(30038),

    /**
     * IsTableItemPatternAvailable.
     */
    IsTableItemPatternAvailable(30039),

    /**
     * IsTextPatternAvailable.
     */
    IsTextPatternAvailable(30040),

    /**
     * IsTogglePatternAvailable.
     */
    IsTogglePatternAvailable(30041),

    /**
     * IsTransformPatternAvailable.
     */
    IsTransformPatternAvailable(30042),

    /**
     * IsValuePatternAvailable.
     */
    IsValuePatternAvailable(30043),

    /**
     * IsWindowPatternAvailable.
     */
    IsWindowPatternAvailable(30044),

    /**
     * Grid row count.
     */
    GridRowCount(30062),

    /**
     * Grid column count.
     */
    GridColumnCount(30063),

    /**
     * Window Is Modal.
     */
    WindowIsModal(30077),

    /**
     * WindowIsTopmost.
     */
    WindowIsTopmost(30078),

    /**
     * IsLegacyIAccessiblePatternAvailable.
     */
    IsLegacyIAccessiblePatternAvailable(30090),

    /**
     * LegacyIAccessibleChildId.
     */
    LegacyIAccessibleChildId(30091),

    /**
     * LegacyIAccessibleName.
     */
    LegacyIAccessibleName(30092),

    /**
     * LegacyIAccessibleValue.
     */
    LegacyIAccessibleValue(30093),

    /**
     * LegacyIAccessibleDescription.
     */
    LegacyIAccessibleDescription(30094),

    /**
     * LegacyIAccessibleRole.
     */
    LegacyIAccessibleRole(30095),

    /**
     * LegacyIAccessibleState.
     */
    LegacyIAccessibleState(30096),

    /**
     * LegacyIAccessibleHelp.
     */
    LegacyIAccessibleHelp(30097),

    /**
     * LegacyIAccessibleKeyboardShortcut.
     */
    LegacyIAccessibleKeyboardShortcut(30098),

    /**
     * LegacyIAccessibleSelection.
     */
    LegacyIAccessibleSelection(30099),

    /**
     * LegacyIAccessibleDefaultAction.
     */
    LegacyIAccessibleDefaultAction(30100),

    /**
     * ARIA role.
     */
    AriaRole(30101),

    /**
     * AriaProperties.
     */
    AriaProperties(30102),

    /**
     * IsDataValidForForm.
     */
    IsDataValidForForm(30103),

    /**
     * ControllerFor.
     */
    ControllerFor(30104),

    /**
     * DescribedBy.
     */
    DescribedBy(30105),

    /**
     * FlowsTo.
     */
    FlowsTo(30106),

    /**
     * ProviderDescription.
     */
    ProviderDescription(30107),

    /**
     * IsItemContainerPatternAvailable.
     */
    IsItemContainerPatternAvailable(30108),

    /**
     * IsVirtualizedItemPatternAvailable.
     */
    IsVirtualizedItemPatternAvailable(30109),

    /**
     * IsSynchronizedInputPatternAvailable.
     */
    IsSynchronizedInputPatternAvailable(30110),

    /**
     * IsObjectModelPatternAvailable.
     */
    IsObjectModelPatternAvailable(30112),

    /**
     * IsAnnotationPatternAvailable.
     */
    IsAnnotationPatternAvailable(30118),

    /**
     * IsTextPattern2Available.
     */
    IsTextPattern2Available(30119),

    /**
     * IsStylesPatternAvailable.
     */
    IsStylesPatternAvailable(30127),

    /**
     * IsSpreadsheetPatternAvailable.
     */
    IsSpreadsheetPatternAvailable(30128),

    /**
     * IsSpreadsheetItemPatternAvailable.
     */
    IsSpreadsheetItemPatternAvailable(30132),

    /**
     * IsTransformPattern2Available.
     */
    IsTransformPattern2Available(30134),

    /**
     * LiveSetting.
     */
    LiveSetting(30135),

    /**
     * IsTextChildPatternAvailable.
     */
    IsTextChildPatternAvailable(30136),

    /**
     * IsDragPatternAvailable.
     */
    IsDragPatternAvailable(30137),

    /**
     * IsDropTargetPatternAvailable.
     */
    IsDropTargetPatternAvailable(30141),

    /**
     * FlowsFrom.
     */
    FlowsFrom(30148),

    /**
     * IsTextEditPatternAvailable.
     */
    IsTextEditPatternAvailable(30149),

    /**
     * IsPeripheral.
     */
    IsPeripheral(30150),

    /**
     * IsCustomNavigationPatternAvailable.
     */
    IsCustomNavigationPatternAvailable(30151),

    /**
     * PositionInSet.
     */
    PositionInSet(30152),

    /**
     * SizeOfSet.
     */
    SizeOfSet(30153),

    /**
     * Level.
     */
    Level(30154),

    /**
     * AnnotationTypes.
     */
    AnnotationTypes(30155),

    /**
     * AnnotationObjects.
     */
    AnnotationObjects(30156),

    /**
     * LandmarkType.
     */
    LandmarkType(30157),

    /**
     * LocalizedLandmark.
     */
    LocalizedLandmark(30158),

    /**
     * FullDescription.
     */
    FullDescription(30159),

    /**
     * FillColor.
     */
    FillColor(30160),

    /**
     * OutlineColor.
     */
    OutlineColor(30161),

    /**
     * FillType.
     */
    FillType(30162),

    /**
     * OutlineThickness.
     */
    OutlineThickness(30164),

    /**
     * CenterPoint.
     */
    CenterPoint(30165),

    /**
     * Rotation.
     */
    Rotation(30166),

    /**
     * Size.
     */
    Size(30167);

    /**
     * The actual value.
     */
    private int value;

    /**
     * Gets the value.
     *
     * @return The actual value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Constructor for PropertyID.
     *
     * @param value The initial value.
     */
    PropertyID(final int value) {
        this.value = value;
    }
}
