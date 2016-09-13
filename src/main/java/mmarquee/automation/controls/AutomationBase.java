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
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.*;
import mmarquee.automation.pattern.*;
import mmarquee.automation.uiautomation.*;
import org.apache.log4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.List;

/**
 * Created by Mark Humphreys on 26/01/2016.
 *
 * The base for automation.
 */
public abstract class AutomationBase {

    final Logger logger = Logger.getLogger(AutomationBase.class.getName());

    protected AutomationElement element;

    protected UIAutomation automation = UIAutomation.getInstance();

    /**
     * Constructor for the AutomationBase class
     * @param element Element to use
     */
    public AutomationBase (AutomationElement element) {
        this.element = element;
    }

    /**
     * Checks whether a pattern is available
     * @param property pattern to search for
     * @return True if available
     */
    private boolean isPatternAvailable(PropertyID property) {
        try {
            return !this.element.currentPropertyValue(property.getValue()).equals(0);
        } catch (AutomationException ex) {
            return false;
        }
    }

    /**
     * Is the dock pattern available
     * @return Yes or no
     */
    boolean isDockPatternAvailable () {
        return isPatternAvailable(PropertyID.IsDockPatternAvailable);
    }

    /**
     * Is the expand/collapse pattern available
     * @return Yes or no
     */
    boolean isExpandCollapsePatternAvailable () {
        return isPatternAvailable(PropertyID.IsExpandCollapsePatternAvailable);
    }

    /**
     * Is the gird item pattern available
     * @return Yes or no
     */
    boolean isGridItemPatternAvailable () {
        return isPatternAvailable(PropertyID.IsGridItemPatternAvailable);
    }

    /**
     * Is the multiple view pattern available
     * @return Yes or no
     */
    boolean isMultipleViewPatternAvailable () {
        return isPatternAvailable(PropertyID.IsMultipleViewPatternAvailable);
    }

    /**
     * Is the invoke pattern available
     * @return Yes or no
     */
    boolean isInvokePatternAvailable () {
        return isPatternAvailable(PropertyID.IsInvokePatternAvailable);
    }

    /**
     * Is the grid pattern available
     * @return Yes or no
     */
    boolean isGridPatternAvailable () {
        return isPatternAvailable(PropertyID.IsGridPatternAvailable);
    }

    /**
     * Is the range value pattern available
     * @return Yes or no
     */
    boolean isRangeValuePatternAvailable () {
        return isPatternAvailable(PropertyID.IsRangeValuePatternAvailable);
    }

    /**
     * Is the scroll pattern available
     * @return Yes or no
     */
    boolean isScrollPatternAvailable () {
        return isPatternAvailable(PropertyID.IsScrollPatternAvailable);
    }

    /**
     * Is the selection item pattern available
     * @return Yes or no
     */
    boolean isSelectionItemPatternAvailable () {
        return isPatternAvailable(PropertyID.IsSelectionItemPatternAvailable);
    }

    /**
     * Is the scroll item pattern available
     * @return Yes or no
     */
    boolean isScrollItemPatternAvailable () {
        return isPatternAvailable(PropertyID.IsScrollItemPatternAvailable);
    }

    /**
     * Is the window pattern available
     * @return Yes or no
     */
    boolean isWindowPatternAvailable () {
        return isPatternAvailable(PropertyID.IsWindowPatternAvailable);
    }

    /**
     * Is the text pattern available
     * @return Yes or no
     */
    boolean isTextPatternAvailable () {
        return isPatternAvailable(PropertyID.IsTextPatternAvailable);
    }

    /**
     * Is the table item pattern available
     * @return Yes or no
     */
    boolean isTableItemPatternAvailable () {
        return isPatternAvailable(PropertyID.IsTableItemPatternAvailable);
    }

    /**
     * Is the table pattern available
     * @return Yes or no
     */
    boolean isTablePatternAvailable () {
        return isPatternAvailable(PropertyID.IsTablePatternAvailable);
    }

    /**
     * Is the selection pattern available
     * @return Yes or no
     */
    boolean isSelectionPatternAvailable () {
        return isPatternAvailable(PropertyID.IsSelectionPatternAvailable);
    }

    /**
     * Is the transform pattern available
     * @return Yes or no
     */
    boolean isTransformPatternAvailable () {
        return isPatternAvailable(PropertyID.IsTransformPatternAvailable);
    }

    /**
     * Is the toggle pattern available
     * @return Yes or no
     */
    boolean isTogglePatternAvailable () {
        return isPatternAvailable(PropertyID.IsTogglePatternAvailable);
    }

    /**
     * Is the value pattern available
     * @return Yes or no
     */
    boolean isValuePatternAvailable () {
        return isPatternAvailable(PropertyID.IsValuePatternAvailable);
    }

    /**
     * Is the control off screen?
     * @return Off screen?
     */
    boolean isOffScreen () {
        try {
            return !this.element.currentPropertyValue(PropertyID.IsOffscreen.getValue()).equals(0);
        } catch (AutomationException ex) {
            return false;
        }
    }

    /**
     * Gets a clickable point for the control
     *
     * This is manufactured by getting the bounding rect and finding the middle point.
     *
     * @return The clickable point
     * @throws AutomationException Error in automation library
     */
    public WinDef.POINT getClickablePoint () throws AutomationException {
        return this.element.getClickablePoint();
    }

    /**
     * Gets the processID of the element
     * @return The processId for the element
     * @throws AutomationException Error in automation library
     */
    public Object getProcessId() throws AutomationException {
        return this.element.getProcessId();
    }

    /**
     * Gets the framework used by the element
     * @return The framework object (really a string)
     * @throws AutomationException Error in automation library
     */
    public Object getFramework() throws AutomationException {
        return this.element.currentPropertyValue(PropertyID.FrameworkId.getValue());
    }

    /**
     * Gets the name associated with this element
     * @return The name of the element
     * @throws AutomationException Error in automation library
     */
    public String name () throws AutomationException {
        return this.element.getName();
    }

//    /**
 //    * Sets the name of the element
//     * @param name The name to be set.
//     */
//    public void setName(String name) {
 //       this.element.setName(name);
 //   }

    /**
     * Finds all of the elements that are associated with this element
     * @return List List of elements
     * @throws AutomationException Something is up with automation
     */
    protected List<AutomationElement> findAll() throws AutomationException {
        return this.findAll(new TreeScope(TreeScope.Children));
    }

    /**
     * Finds the first match for the condition
     * @param scope The scope of where to look
     * @param condition The condition to use
     * @return The found AutomationElement
     * @throws AutomationException An error has occurred in automation
     */
   protected AutomationElement findFirst(TreeScope scope, PointerByReference condition) throws AutomationException {
        return this.element.findFirst(scope, condition);
   }

    /**
     * Finds all of the elements that are associated with the given condition.
     * @param scope The scope of where to look
     * @return List list of all the elements found
     * @throws AutomationException Something is wrong in automation
     */
    protected List<AutomationElement> findAll(TreeScope scope) throws AutomationException {
        PointerByReference condition = this.createTrueCondition();
        return this.findAll(scope, condition.getValue());
    }

    /**
     * Creates a true condition
     * @return The true condition
     * @throws AutomationException Something is up with automation
     */
    protected PointerByReference createTrueCondition() throws AutomationException {
        return this.automation.createTrueCondition();
    }

    /**
     * Creates a false condition
     * @return The condition
     * @throws AutomationException Error thrown in automation
     */
    protected PointerByReference createFalseCondition() throws AutomationException {
        return this.automation.CreateFalseCondition();
    }

    /**
     * Creates a name property condition
     * @param name The name to use
     * @return The condition
     * @throws AutomationException Something has gone wrong
     */
    protected PointerByReference createNamePropertyCondition(String name) throws AutomationException {
        return this.automation.CreateNamePropertyCondition(name);
    }

    /**
     * Creates an automation ID property condition
     * @param automationId The automation ID to use
     * @return The condition
     * @throws AutomationException Something has gone wrong
     */
    protected PointerByReference createAutomationIdPropertyCondition(String automationId) throws AutomationException {
        return this.automation.CreateAutomationIdPropertyCondition(automationId);
    }

    /**
     * Creates a control type property condition
     * @param id The control type to use
     * @return The condition
     * @throws AutomationException Something has gone wrong
     */
    protected PointerByReference createControlTypeCondition(ControlType id) throws AutomationException {
        return this.automation.CreateControlTypeCondition(id);
    }

    /**
     * Creates an OR condition
     * @param condition1 First condition
     * @param condition2 Second condition
     * @return The Or Condition
     * @throws AutomationException Automation has gone wrong
     */
    public PointerByReference createOrCondition(Pointer condition1, Pointer condition2) throws AutomationException {
        return this.automation.createOrCondition(condition1, condition2);
    }

    /**
     * Creates an AND condition
     * @param condition1 First condition
     * @param condition2 Second condition
     * @return The And condition
     * @throws AutomationException Error in automation
     */
   protected PointerByReference createAndCondition(Pointer condition1, Pointer condition2) throws AutomationException {
       return this.automation.createAndCondition(condition1, condition2);
   }

    /**
     * Finds all of the elements that are associated with the given condition.
     * @param scope The scope of where to look
     * @param condition The condition to check
     * @return IUIAutomationElementArray
     * @throws AutomationException Error in automation library
     */
    protected List<AutomationElement> findAll(TreeScope scope, Pointer condition) throws AutomationException {
        return this.element.findAll(scope, condition);
    }

    /**
     * Gets the underlying automation pattern
     * @param id The control id to look for
     * @return The pattern
     * @throws PatternNotFoundException Pattern not found
     * @throws AutomationException Error in automation library
     */
    private PointerByReference getPattern (int id) throws PatternNotFoundException, AutomationException {
        PointerByReference unknown = this.element.getCurrentPattern(id);

        if (unknown != null) {
            return unknown;
        } else {
            logger.warn("Failed to find pattern");
            throw new PatternNotFoundException();
        }
    }

    /**
     * <p>
     * Gets the selectItem pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationSelectionItemPattern associated with this control
     * @throws PatternNotFoundException Pattern not found
     * @throws AutomationException Error in automation library
     */
    SelectionItem getSelectItemPattern() throws PatternNotFoundException, AutomationException {
        SelectionItem pattern = new SelectionItem();

        if (this.isSelectionItemPatternAvailable()) {
            PointerByReference unknown = this.getPattern(PatternID.SelectionItem.getValue());

            pattern.setPattern(unknown.getValue());
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the selection pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationSelectionPattern associated with this control
     * @throws PatternNotFoundException Pattern not found
     * @throws AutomationException Error in automation library
     */
    Selection getSelectionPattern() throws PatternNotFoundException, AutomationException {
        Selection pattern = new Selection();

        if (this.isSelectionPatternAvailable()) {
            PointerByReference unknown = this.getPattern(PatternID.Selection.getValue());

            pattern.setPattern(unknown.getValue());
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the value pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationValuePattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    Value getValuePattern() throws PatternNotFoundException, AutomationException {
        Value pattern = new Value();

        if (this.isValuePatternAvailable()) {
            PointerByReference unknown = this.getPattern(PatternID.Value.getValue());
            pattern.setPattern(unknown.getValue());
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the rangevalue pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationRangeValuePattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    Range getRangePattern() throws PatternNotFoundException, AutomationException {
        Range pattern = new Range();

        if (isRangeValuePatternAvailable()) {
            PointerByReference unknown = this.getPattern(PatternID.RangeValue.getValue());

            pattern.setPattern(unknown.getValue());
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the table pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTablePattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    Table getTablePattern() throws PatternNotFoundException, AutomationException {
        Table pattern = new Table();

        if (isTablePatternAvailable()) {
            PointerByReference unknown = this.getPattern(PatternID.Table.getValue());

            pattern.setPattern(unknown.getValue());
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the window pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationWindowPattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    Window getWindowPattern() throws PatternNotFoundException, AutomationException {
        Window pattern = new Window();

        if (this.isWindowPatternAvailable()) {
            PointerByReference unknown = this.getPattern(PatternID.Window.getValue());

            pattern.setPattern(unknown.getValue());
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the expand/collapse pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationExpandCollapsePattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    protected ExpandCollapse getExpandCollapsePattern() throws PatternNotFoundException, AutomationException {
        ExpandCollapse pattern = new ExpandCollapse();

        if (isExpandCollapsePatternAvailable()) {
            PointerByReference unknown = this.getPattern(PatternID.ExpandCollapse.getValue());

            pattern.setPattern(unknown.getValue());
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the grid pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationGridPattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    Grid getGridPattern() throws PatternNotFoundException, AutomationException {
        Grid pattern = new Grid();

        if (isGridPatternAvailable()) {
            PointerByReference unknown = this.getPattern(PatternID.Grid.getValue());

            pattern.setPattern(unknown.getValue());
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the toggle pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTogglePattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    Toggle getTogglePattern() throws PatternNotFoundException, AutomationException {
        Toggle pattern = new Toggle();

        if (isTogglePatternAvailable()) {
            PointerByReference unknown = this.getPattern(PatternID.Toggle.getValue());

            pattern.setPattern(unknown.getValue());
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the invoke pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationInvokePattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    protected Invoke getInvokePattern() throws PatternNotFoundException, AutomationException {
        Invoke pattern = new Invoke();

        if (isInvokePatternAvailable()) {
            PointerByReference unknown = this.getPattern(PatternID.Invoke.getValue());
            pattern.setPattern(unknown.getValue());
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the text pattern for this control
     * </p>
     * @return  Returns ths IUIAutomationTextPattern associated with this control
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    Text getTextPattern() throws PatternNotFoundException, AutomationException {
        Text pattern = new Text();

        if (this.isTextPatternAvailable()) {
            PointerByReference unknown = this.getPattern(PatternID.Text.getValue());
            pattern.setPattern(unknown.getValue());
        }

        return pattern;
    }

    /**
     * Is the control enabled
     * @return Enabled?
     * @throws AutomationException Something is wrong in automation
     */
    public boolean isEnabled () throws AutomationException {
        return this.element.currentIsEnabled().booleanValue();
    }

    /**
     * Gets the bounding rectangle of the control
     * @return The bounding rectangle
     * @throws AutomationException Something is wrong in automation
     */
    public WinDef.RECT getBoundingRectangle() throws AutomationException {
        return this.element.get_CurrentBoundingRectangle();
    }

    /**
     * Get the native window handle
     * @return The handle
     * @throws AutomationException Something is wrong in automation
     */
    public WinDef.HWND getNativeWindowHandle() throws AutomationException {
        Object value = this.element.currentPropertyValue(PropertyID.NativeWindowHandle.getValue());
        return new WinDef.HWND(Pointer.createConstant(Integer.valueOf(value.toString())));
    }

    /**
     * Gets the ARIA role of the element
     * @return The ARIA role
     * @throws AutomationException Something is wrong in automation
     */
    public String getAriaRole() throws AutomationException {
        return this.element.getAriaRole();
    }

    /**
     * The current orientation of the element
     * @return The orientation
     * @throws AutomationException Something has gone wrong
     */
    public OrientationType getOrientation() throws AutomationException {
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
     * @throws AutomationException Something is wrong in automation
     */
    public String getFrameworkId() throws AutomationException {
        return this.element.getFrameworkId();
    }

    /**
     * Gets the current provider description
     * @return The provider description
     * @throws AutomationException Something is wrong in automation
     */
    public String getProviderDescription() throws AutomationException {
        return this.element.getProviderDescription();
    }

    /**
     * Gets the current item status
     * @return The item status
     * @throws AutomationException Something is wrong in automation
     */
    public String getItemStatus() throws AutomationException {
        return this.element.getItemStatus();
    }

    /**
     * Gets the current accelerator key for the element
     * @return The key
     * @throws AutomationException Something is wrong in automation
     */
    public String getAcceleratorKey() throws AutomationException {
        return this.element.getAcceleratorKey();
    }

    /**
     * Shows the context menu for the control
     * @throws AutomationException Failed to get the correct interface
     */
    public void showContextMenu() throws AutomationException {
        this.element.showContextMenu();
    }
}