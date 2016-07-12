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

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.Pointer;
import mmarquee.automation.*;
import mmarquee.automation.condition.*;
import mmarquee.automation.pattern.*;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.*;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.List;

/**
 * Created by inpwt on 26/01/2016.
 *
 * The base for automation.
 */
public abstract class AutomationBase {

    final Logger logger = Logger.getLogger(AutomationBase.class.getName());

    /**
     * TODO: Make this work with protected (done for EventHandler)
     */
    public AutomationElement element;

    protected UIAutomation automation = UIAutomation.getInstance();

    private WinDef.HWND handle = null;

    protected final User32 user32 = User32.INSTANCE;

    /**
     * Constructor for the AutomationBase class
     * @param element Element to use
     */
    public AutomationBase (AutomationElement element) {
        this.element = element;

        if (element != null) {
            // Can we get the handle (HWND) and hence the rect?
            this.handle = this.getNativeWindowHandle();
        }
    }

    protected boolean isDockPatternAvailable () {
        return this.element.get_CurrentPropertyValue(PropertyID.IsDockPatternAvailable.getValue()).equals(true);
    }

    protected boolean isExpandCollapsePatternAvailable () {
        return this.element.get_CurrentPropertyValue(PropertyID.IsExpandCollapsePatternAvailable.getValue()).equals(true);
    }

    protected boolean isGridItemPatternAvailable () {
        return this.element.get_CurrentPropertyValue(PropertyID.IsGridItemPatternAvailable.getValue()).equals(true);
    }

    protected boolean isMultipleViewPatternAvailable () {
        return this.element.get_CurrentPropertyValue(PropertyID.IsMultipleViewPatternAvailable.getValue()).equals(true);
    }

    protected boolean isInvokePatternAvailable () {
        return this.element.get_CurrentPropertyValue(PropertyID.IsInvokePatternAvailable.getValue()).equals(true);
    }

    protected boolean isGridPatternAvailable () {
        return this.element.get_CurrentPropertyValue(PropertyID.IsGridPatternAvailable.getValue()).equals(true);
    }

    protected boolean isRangeValuePatternAvailable () {
        return this.element.get_CurrentPropertyValue(PropertyID.IsRangeValuePatternAvailable.getValue()).equals(true);
    }

    protected boolean isScrollPatternAvailable () {
        return this.element.get_CurrentPropertyValue(PropertyID.IsScrollPatternAvailable.getValue()).equals(true);
    }

    protected boolean isSelectionItemPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsSelectionItemPatternAvailable.getValue()).equals(true);
    }

    protected boolean isScrollItemPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsScrollItemPatternAvailable.getValue()).equals(true);
    }

    protected boolean isWindowPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsWindowPatternAvailable.getValue()).equals(true);
    }

    protected boolean isTextPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsTextPatternAvailable.getValue()).equals(true);
    }

    protected boolean isTableItemPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsTableItemPatternAvailable.getValue()).equals(true);
    }

    protected boolean isTablePatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsTablePatternAvailable.getValue()).equals(true);
    }

    protected boolean isSelectionPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsSelectionPatternAvailable.getValue()).equals(true);
    }

    protected boolean isTransformPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsTransformPatternAvailable.getValue()).equals(true);
    }

    protected boolean isTogglePatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsTogglePatternAvailable.getValue()).equals(true);
    }

    protected boolean isValuePatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsValuePatternAvailable.getValue()).equals(true);
    }

    /**
     * Is the control off screen?
     * @return Off screen?
     */
    protected boolean isOffScreen () {
        return this.element.getCurrentPropertyValue(PropertyID.IsOffscreen.getValue()).equals(true);
    }

    /**
     * Gets a clickable point for the control
     *
     * This is manufactured by getting the bouning rect and finding the middle point.
     *
     * @return The clickable point
     */
    public WinDef.POINT getClickablePoint () {
        WinDef.RECT rect = this.getBoundingRectangle();

        WinDef.POINT point = new WinDef.POINT(
                rect.left + (rect.right /2),
                rect.top + (rect.bottom /2));

        return point;
    }

    /**
     * Gets the processID of the element
     * @return The processId for the element
     */
    public Object getProcessId() {
        return this.element.getProcessId();
    }

    /**
     * Gets the framework used by the element
     * @return The framework object (really a string)
     */
    public Object getFramework() {
        return this.element.getCurrentPropertyValue(PropertyID.FrameworkId.getValue());
    }

    /**
     * Gets the name associated with this element
     * @return The name of the element
     */
    public String name () {
        return this.element.getName();
    }

    /**
     * Sets the name of the element
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.element.setName(name);
    }

    /**
     * Finds all of the elements that are associated with this element
     * @return List List of elements
     */
    protected List<AutomationElement> findAll() {
        return this.findAll(TreeScope.TreeScope_Children);
    }

    /**
     * Finds the first match for the condition
     * @param scope The scope of where to look
     * @param condition The condition to use
     * @return The found AutomationElement
     * @throws ElementNotFoundException No elements found
     */
    protected AutomationElement findFirst(TreeScope scope, Condition condition) throws ElementNotFoundException {
        return this.element.findFirst(scope, condition);
    }

    /**
     * Finds all of the elements that are associated with the given condition.
     * @param scope The scope of where to look
     * @return List list of all the elements found
     */
    protected List<AutomationElement> findAll(TreeScope scope) {
        TrueCondition condition = this.createTrueCondition();
        return this.findAll(scope, condition);
    }

    /**
     * Creates a true condition
     * @return The true condition
     */
    public TrueCondition createTrueCondition() {
        return new TrueCondition();
    }

    /**
     * Creates a false condition
     * @return The condition
     */
    public FalseCondition createFalseCondition() {
        return new FalseCondition();
    }

    /**
     * Creates a name property condition
     * @param name The name to use
     * @return The condition
     */
    public NameCondition createNamePropertyCondition(String name) {
        return new NameCondition(name);
    }

    /**
     * Creats an automation ID property condition
     * @param automationId The automation ID to use
     * @return The condition
     */
    public AutomationIdCondition createAutomationIdPropertyCondition(String automationId) {
        return new AutomationIdCondition(automationId);
    }

    /**
     * Creates a control type property condition
     * @param id The control type to use
     * @return The condition
     */
    public ControlIdCondition createControlTypeCondition(int id) {
        return new ControlIdCondition(id);
    }

    /**
     * Creates an OR condition
     * @param condition1 First condition
     * @param condition2 Second condition
     * @return The Or Condition
     */
    public OrCondition createOrCondition(Condition condition1, Condition condition2) {
        return new OrCondition(condition1, condition2);
    }

    /**
     * Creates an AND condition
     * @param condition1 First condition
     * @param condition2 Second condition
     * @return The And condition
     */
    public AndCondition createAndCondition(Condition condition1, Condition condition2) {
        return new AndCondition(condition1, condition2);
    }

    /**
     * Finds all of the elements that are associated with the given condition.
     * @param scope The scope of where to look
     * @param condition The condition to check
     * @return IUIAutomationElementArray
     */
    protected List<AutomationElement> findAll(TreeScope scope, Condition condition) {
        return this.element.findAll(scope, condition.getCondition());
    }

    /**
     * Gets the underlying automation pattern
     * @param id The control id to look for
     * @return The pattern
     * @throws PatternNotFoundException
     */
    private com4j.Com4jObject getPattern (int id) throws PatternNotFoundException {
        com4j.Com4jObject unknown = this.element.getCurrentPattern(id);

        if (unknown != null) {
            return unknown;
        } else {
            logger.info("Failed to find pattern");
            throw new PatternNotFoundException();
        }
    }

    /**
     * <p>
     * Gets the selectItem pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationSelectionItemPattern associated with this control
     * @throws PatternNotFoundException Pattern not found
     */
    protected SelectionItem getSelectItemPattern() throws PatternNotFoundException {
        SelectionItem pattern = new SelectionItem();

        if (isSelectionItemPatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.SelectionItem.getValue());

            pattern.setPattern(unknown.queryInterface(IUIAutomationSelectionItemPattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the selection pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationSelectionPattern associated with this control
     * @throws PatternNotFoundException Pattern not found
     */
    protected Selection getSelectionPattern() throws PatternNotFoundException {
        Selection pattern = new Selection();

        if (isSelectionPatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Selection.getValue());

            pattern.setPattern(unknown.queryInterface(IUIAutomationSelectionPattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the value pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationValuePattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     */
    protected Value getValuePattern() throws PatternNotFoundException {
        Value pattern = new Value();

        if (isValuePatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Value.getValue());

            pattern.setPattern(unknown.queryInterface(IUIAutomationValuePattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the rangevalue pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationRangeValuePattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     */
    protected Range getRangePattern() throws PatternNotFoundException {
        Range pattern = new Range();

        if (isRangeValuePatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.RangeValue.getValue());

            pattern.setPattern(unknown.queryInterface(IUIAutomationRangeValuePattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the table pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTablePattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     */
    protected Table getTablePattern() throws PatternNotFoundException {
        Table pattern = new Table();

        if (isTablePatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Table.getValue());

            pattern.setPattern(unknown.queryInterface(IUIAutomationTablePattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the window pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationWindowPattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     */
    protected Window getWindowPattern() throws PatternNotFoundException {
        Window pattern = new Window();

        if (isWindowPatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Window.getValue());

            pattern.setPattern(unknown.queryInterface(IUIAutomationWindowPattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the expand/collapse pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationExpandCollapsePattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     */
    protected ExpandCollapse getExpandCollapsePattern() throws PatternNotFoundException {
        ExpandCollapse pattern = new ExpandCollapse();

        if (isExpandCollapsePatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.ExpandCollapse.getValue());

            pattern.setPattern(unknown.queryInterface(IUIAutomationExpandCollapsePattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the grid pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationGridPattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     */
    protected Grid getGridPattern() throws PatternNotFoundException {
        Grid pattern = new Grid();

        if (isGridPatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Grid.getValue());

            pattern.setPattern(unknown.queryInterface(IUIAutomationGridPattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the toggle pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTogglePattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     */
    protected Toggle getTogglePattern() throws PatternNotFoundException {
        Toggle pattern = new Toggle();

        if (isTogglePatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Toggle.getValue());

            pattern.setPattern(unknown.queryInterface(IUIAutomationTogglePattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the invoke pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationInvokePattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     */
    protected Invoke getInvokePattern() throws PatternNotFoundException {
        Invoke pattern = new Invoke();

        if (isInvokePatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Invoke.getValue());
            pattern.setPattern(unknown.queryInterface(IUIAutomationInvokePattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the text pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTextPattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     */
    protected Text getTextPattern() throws PatternNotFoundException {
        Text pattern = new Text();

        if (this.isTextPatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Text.getValue());
            pattern.setPattern(unknown.queryInterface(IUIAutomationTextPattern.class));
        }

        return pattern;
    }

    /**
     * Is the control enabled
     * @return Enabled?
     */
    public boolean isEnabled () {
        return this.element.getCurrentPropertyValue(PropertyID.IsEnabled.getValue()).equals(true);
    }

    /**
     * Gets the bounding rectangle of the control
     * @return The bounding rectangle
     */
    public WinDef.RECT getBoundingRectangle() {
        WinDef.RECT rect = new WinDef.RECT();
        user32.GetWindowRect(this.handle, rect);

        // Adjust so that right and bottom match width and height
        rect.right = rect.right -rect.left;
        rect.bottom = rect.bottom -rect.top;

        return rect;
    }

    /**
     * Get the native window handle
     * @return The handle
     */
    public WinDef.HWND getNativeWindowHandle() {
        Object value = this.element.getCurrentPropertyValue(PropertyID.NativeWindowHandle.getValue());
        return new WinDef.HWND(Pointer.createConstant(Integer.valueOf(value.toString())));
    }

    /**
     * Gets the ARIA role of the element
     * @return The ARIA role
     */
    public String getAriaRole() {
        return this.element.getAriaRole();
    }

    /**
     * The current orientation of the element
     * @return The orientation
     */
    public OrientationType getOrientation() {
        return this.element.getOrientation();
    }

    /**
     * Gets the runtime id
     * @return The runtime id
     * @throws NotImplementedException Throws big error, so not implemented
     */
    public int[] getRuntimeId() throws NotImplementedException {
//        return this.element.getRuntimeId();
        throw new NotImplementedException();
    }

    /**
     * Gets the current framework ID for the element
     * @return The framework id
     */
    public String getFrameworkId() {
        return this.element.getFrameworkId();
    }

    /**
     * Gets the current provider description
     * @return The provider description
     */
    public String getProviderDescription() {
        return this.element.getProviderDescription();
    }

    /**
     * Gets the current item status
     * @return The item status
     */
    public String getItemStatus() {
        return this.element.getItemStatus();
    }

    /**
     * Gets the current accelerator key for th element
     * @return The key
     */
    public String getAcceleratorKey() {
        return this.element.getAcceleratorKey();
    }
}