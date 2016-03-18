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

import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.condition.*;
import mmarquee.automation.pattern.*;
import mmarquee.automation.pattern.raw.*;
import mmarquee.automation.uiautomation.*;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by inpwt on 26/01/2016.
 */
public abstract class AutomationBase {

    protected final Logger logger = Logger.getLogger(AutomationBase.class.getName());

    protected AutomationElement element;

    protected IUIAutomation uiAuto;

    /**
     * Constructor for the AutomationBase class
     * @param element Element to use
     * @param uiAuto The automation object
     */
    public AutomationBase (AutomationElement element, IUIAutomation uiAuto) {
        this.element = element;
        this.uiAuto = uiAuto;

        // Can we get the handle (HWND) and hence the rect?
        WinDef.HWND handle = this.getNativeWindowHandle();



//        this.element.
    }

    protected boolean isDockPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsDockPatternAvailable).equals(true);
    }

    protected boolean isExpandCollapsePatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsExpandCollapsePatternAvailable).equals(true);
    }

    protected boolean isGridItemPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsGridItemPatternAvailable).equals(true);
    }

    protected boolean isMultipleViewPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsMultipleViewPatternAvailable).equals(true);
    }

    protected boolean isInvokePatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsInvokePatternAvailable).equals(true);
    }

    protected boolean isGridPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsGridPatternAvailable).equals(true);
    }

    protected boolean isRangeValuePatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsRangeValuePatternAvailable).equals(true);
    }

    protected boolean isScrollPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsScrollPatternAvailable).equals(true);
    }

    protected boolean isSelectionItemPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsSelectionItemPatternAvailable).equals(true);
    }

    protected boolean isScrollItemPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsScrollItemPatternAvailable).equals(true);
    }

    protected boolean isWindowPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsWindowPatternAvailable).equals(true);
    }

    protected boolean isTextPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsTextPatternAvailable).equals(true);
    }

    protected boolean isTableItemPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsTableItemPatternAvailable).equals(true);
    }

    protected boolean isTablePatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsTablePatternAvailable).equals(true);
    }

    protected boolean isSelectionPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsSelectionPatternAvailable).equals(true);
    }

    protected boolean isTransformPatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsTransformPatternAvailable).equals(true);
    }

    protected boolean isTogglePatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsTogglePatternAvailable).equals(true);
    }

    protected boolean isValuePatternAvailable () {
        return this.element.getCurrentPropertyValue(PropertyID.IsValuePatternAvailable).equals(true);
    }

    /**
     * Is the control off screen?
     * @return Off screen?
     */
    protected boolean isOffScreen () {
        return this.element.getCurrentPropertyValue(PropertyID.IsOffscreen).equals(true);
    }

    /**
     * Gets a clickable point for the control
     *
     * This is currently not working
     *
     * @return The clickable point
     */
    public WinDef.POINT getClickablePoint () {
        Object value = this.element.getCurrentPropertyValue(PropertyID.ClickablePoint);

        WinDef.POINT point = new WinDef.POINT();

        return point;
    }

    /**
     * Gets the name associated with this element
     * @return The name of the element
     */
    public String name () {
        return this.element.currentName();
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
     * @return List<AutomationElement>
     */
    protected List<AutomationElement> findAll() {
        return this.findAll(TreeScope.TreeScope_Children);
    }

    /**
     * Finds the first match for the condition
     * @param scope The scope of where to look
     * @param condition The condition to use
     * @return The found AutomationElement
     */
    protected AutomationElement findFirst(TreeScope scope, Condition condition) throws ElementNotFoundException {
        return this.element.findFirst(scope, condition);
    }

    /**
     * Finds all of the elements that are associated with the given condition.
     * @param scope The scope of where to look
     * @return List<AutomationElement>
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
        return new TrueCondition(this.uiAuto);
    }

    /**
     * Creates a false condition
     * @return The condition
     */
    public FalseCondition createFalseCondition() {
        return new FalseCondition(this.uiAuto);
    }

    /**
     * Creates a name property condition
     * @param name The name to use
     * @return The condition
     */
    public NameCondition createNamePropertyCondition(String name) {
        return new NameCondition(this.uiAuto, name);
    }

    /**
     * Creates a control type property condition
     * @param id The control type to use
     * @return The condition
     */
    public ControlIdCondition createControlTypeCondition(int id) {
        return new ControlIdCondition(this.uiAuto, id);
    }

    /**
     * Creatss an or condition
     * @param condition1 First condition
     * @param condition2 Second condition
     * @return The Or Condition
     */
    public OrCondition createOrCondition(Condition condition1, Condition condition2) {
        return new OrCondition(this.uiAuto, condition1, condition2);
    }

    /**
     * Creates an AND condition
     * @param condition1 First condition
     * @param condition2 Second condition
     * @return The And condition
     */
    public AndCondition createAndCondition(Condition condition1, Condition condition2) {
        return new AndCondition(this.uiAuto, condition1, condition2);
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
     */
    protected SelectionItem getSelectItemPattern() throws PatternNotFoundException {
        SelectionItem pattern = new SelectionItem();

        if (isSelectionItemPatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.SelectionItem);

            pattern.setPattern(unknown.queryInterface(IUIAutomationSelectionItemPattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the selection pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationSelectionPattern associated with this control
     */
    protected Selection getSelectionPattern() throws PatternNotFoundException {
        Selection pattern = new Selection();

        if (isSelectionPatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Selection);

            pattern.setPattern(unknown.queryInterface(IUIAutomationSelectionPattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the value pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationValuePattern associated with this control
     */
    protected Value getValuePattern() throws PatternNotFoundException {
        Value pattern = new Value();

        if (isValuePatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Value);

            pattern.setPattern(unknown.queryInterface(IUIAutomationValuePattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the table pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTablePattern associated with this control
     */
    protected Table getTablePattern() throws PatternNotFoundException {
        Table pattern = new Table();

        if (isTablePatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Table);

            pattern.setPattern(unknown.queryInterface(IUIAutomationTablePattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the window pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationWindowPattern associated with this control
     */
    protected Window getWindowPattern() throws PatternNotFoundException {
        Window pattern = new Window();

        if (isWindowPatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Window);

            pattern.setPattern(unknown.queryInterface(IUIAutomationWindowPattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the expand/collapse pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationExpandCollapsePattern associated with this control
     */
    protected ExpandCollapse getExpandCollapsePattern() throws PatternNotFoundException {
        ExpandCollapse pattern = new ExpandCollapse();

        if (isExpandCollapsePatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.ExpandCollapse);

            pattern.setPattern(unknown.queryInterface(IUIAutomationExpandCollapsePattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the grid pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationGridPattern associated with this control
     */
    protected Grid getGridPattern() throws PatternNotFoundException {
        Grid pattern = new Grid();

        if (isGridPatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Grid);

            pattern.setPattern(unknown.queryInterface(IUIAutomationGridPattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the toggle pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTogglePattern associated with this control
     */
    protected Toggle getTogglePattern() throws PatternNotFoundException {
        Toggle pattern = new Toggle();

        if (isTogglePatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Toggle);

            pattern.setPattern(unknown.queryInterface(IUIAutomationTogglePattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the invoke pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationInvokePattern associated with this control
     */
    protected Invoke getInvokePattern() throws PatternNotFoundException {
        Invoke pattern = new Invoke();

        if (isInvokePatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Invoke);
            pattern.setPattern(unknown.queryInterface(IUIAutomationInvokePattern.class));
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the text pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTextPattern associated with this control
     */
    protected Text getTextPattern() throws PatternNotFoundException {
        Text pattern = new Text();

        if (this.isTextPatternAvailable()) {
            com4j.Com4jObject unknown = this.getPattern(PatternID.Text);
            pattern.setPattern(unknown.queryInterface(IUIAutomationTextPattern.class));
        }

        return pattern;
    }

    /**
     * Is the control enabled
     * @return Enabled ?
     */
    public boolean isEnabled () {
        return this.element.getCurrentPropertyValue(PropertyID.IsEnabled).equals(true);
    }

    /**
     * Gets the bounding rectangle of the control
     */
    public WinDef.RECT getBoundingRectangle() {
        Object obj = this.element.getCurrentPropertyValue(PropertyID.BoundingRectangle);


         // obj is always empty :-(
        WinDef.RECT rect = new WinDef.RECT();
        rect = (WinDef.RECT)obj;

        return rect;
    }

    /**
     * Get the native window handle
     * @return The handle
     */
    public WinDef.HWND getNativeWindowHandle() {
        Object value = this.element.getCurrentPropertyValue(PropertyID.NativeWindowHandle);

        WinDef.HWND hwnd = new WinDef.HWND();

       // hwnd.

        return hwnd;
    }
}