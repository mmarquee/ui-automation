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

package mmarquee.automation.controls;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.Grid;
import mmarquee.automation.pattern.GridItem;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Range;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.pattern.SelectionItem;
import mmarquee.automation.pattern.Table;
import mmarquee.automation.pattern.Text;
import mmarquee.automation.pattern.Toggle;
import mmarquee.automation.pattern.Value;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.OrientationType;
import mmarquee.automation.uiautomation.TreeScope;
import mmarquee.automation.utils.providers.PatternProvider;

/**
 * The base for automation.
 *
 * @author Mark Humphreys
 * Date 26/01/2016.
 */
public abstract class AutomationBase implements Automatable {

    /**
     * The logger.
     */
    final Logger logger = Logger.getLogger(AutomationBase.class.getName());

    /**
     * The automation element.
     */
    protected AutomationElement element;

    /**
     * The automation library wrapper.
     */
    protected UIAutomation automation;

    /**
     * The invoke pattern.
     */
    protected Invoke invokePattern = null;

    /**
     * Constructor for the AutomationBase.
     *
     * @param builder The builder
     */
    public AutomationBase(final ElementBuilder builder) {
        this.element = builder.getElement();

        if (builder.getHasAutomation()) {
            this.automation = builder.getInstance();
        } else {
            this.automation = UIAutomation.getInstance();
        }

        if (builder.getHasInvoke()) {
            this.invokePattern = builder.getInvoke();
        }
    }

    /**
     * Gets the underlying automation element.
     *
     * @return The automation element.
     */
    public AutomationElement getElement() {
        return this.element;
    }

    /**
     * Throws an exception if the element's class name does not equal the expected one.
     * 
     * @param expectedClassName the expected className.
     * @throws AutomationException if automation access failed.
     */
	protected void assertClassName(final String expectedClassName)
            throws AutomationException {
		if (element == null) {
			throw new ElementNotFoundException("null");
		}
		
        String cName = element.getClassName();
		if ((cName == null && expectedClassName == null)
				|| (cName != null && cName.equals(expectedClassName))) {
			return;
		}
		throw new ElementNotFoundException(expectedClassName + "(instead: " + cName + ")");
	}

    /**
     * Checks whether a pattern is available.
     *
     * @param property pattern to search for.
     * @return True if available.
     */
    private boolean isPatternAvailable(final PropertyID property) {
        try {
            return !this.element.getPropertyValue(property.getValue()).equals(0);
        } catch (AutomationException ex) {
            return false;
        }
    }

    /**
     * Is the dock pattern available.
     *
     * @return Yes or no.
     */
    boolean isDockPatternAvailable() {
        return isPatternAvailable(PropertyID.IsDockPatternAvailable);
    }

    /**
     * Is the expand/collapse pattern available.
     *
     * @return Yes or no.
     */
    boolean isExpandCollapsePatternAvailable() {
        return isPatternAvailable(PropertyID.IsExpandCollapsePatternAvailable);
    }

    /**
     * Is the gird item pattern available.
     *
     * @return Yes or no.
     */
    boolean isGridItemPatternAvailable() {
        return isPatternAvailable(PropertyID.IsGridItemPatternAvailable);
    }

    /**
     * Is the multiple view pattern available.
     *
     * @return Yes or no.
     */
    boolean isMultipleViewPatternAvailable() {
        return isPatternAvailable(PropertyID.IsMultipleViewPatternAvailable);
    }

    /**
     * Is the invoke pattern available.
     *
     * @return Yes or no.
     */
    boolean isInvokePatternAvailable() {
        return isPatternAvailable(PropertyID.IsInvokePatternAvailable);
    }

    /**
     * Is the grid pattern available.
     *
     * @return Yes or no.
     */
    boolean isGridPatternAvailable() {
        return isPatternAvailable(PropertyID.IsGridPatternAvailable);
    }

    /**
     * Is the range value pattern available.
     *
     * @return Yes or no.
     */
    boolean isRangeValuePatternAvailable() {
        return isPatternAvailable(PropertyID.IsRangeValuePatternAvailable);
    }

    /**
     * Is the scroll pattern available.
     *
     * @return Yes or no.
     */
    boolean isScrollPatternAvailable() {
        return isPatternAvailable(PropertyID.IsScrollPatternAvailable);
    }

    /**
     * Is the selection item pattern available.
     *
     * @return Yes or no.
     */
    boolean isSelectionItemPatternAvailable() {
        return isPatternAvailable(PropertyID.IsSelectionItemPatternAvailable);
    }

    /**
     * Is the scroll item pattern available.
     *
     * @return Yes or no.
     */
    boolean isScrollItemPatternAvailable() {
        return isPatternAvailable(PropertyID.IsScrollItemPatternAvailable);
    }

    /**
     * Is the window pattern available.
     *
     * @return Yes or no.
     */
    boolean isWindowPatternAvailable() {
        return isPatternAvailable(PropertyID.IsWindowPatternAvailable);
    }

    /**
     * Is the text pattern available.
     *
     * @return Yes or no.
     */
    boolean isTextPatternAvailable() {
        return isPatternAvailable(PropertyID.IsTextPatternAvailable);
    }

    /**
     * Is the table item pattern available.
     *
     * @return Yes or no.
     */
    boolean isTableItemPatternAvailable() {
        return isPatternAvailable(PropertyID.IsTableItemPatternAvailable);
    }

    /**
     * Is the table pattern available.
     *
     * @return Yes or no.
     */
    boolean isTablePatternAvailable() {
        return isPatternAvailable(PropertyID.IsTablePatternAvailable);
    }

    /**
     * Is the selection pattern available.
     *
     * @return Yes or no.
     */
    boolean isSelectionPatternAvailable() {
        return isPatternAvailable(PropertyID.IsSelectionPatternAvailable);
    }

    /**
     * Is the transform pattern available.
     *
     * @return Yes or no.
     */
    boolean isTransformPatternAvailable() {
        return isPatternAvailable(PropertyID.IsTransformPatternAvailable);
    }

    /**
     * Is the toggle pattern available.
     *
     * @return Yes or no.
     */
    boolean isTogglePatternAvailable() {
        return isPatternAvailable(PropertyID.IsTogglePatternAvailable);
    }

    /**
     * Is the item container pattern available.
     *
     * @return Yes or no.
     */
    boolean isItemContainerPatternAvailable() {
        return isPatternAvailable(PropertyID.IsItemContainerPatternAvailable);
    }

    /**
     * Is the value pattern available.
     *
     * @return Yes or no.
     */
    boolean isValuePatternAvailable() {
        return isPatternAvailable(PropertyID.IsValuePatternAvailable);
    }

    /**
     * Is the control off screen.
     *
     * @return Off screen.
     */
    boolean isOffScreen() {
        try {
            return !this.element.getPropertyValue(PropertyID.IsOffscreen.getValue()).equals(0);
        } catch (AutomationException ex) {
            return false;
        }
    }

    /**
     * Gets a clickable point for the control.
     *
     * This is manufactured by getting the bounding rect and finding the middle point.
     *
     * @return The clickable point.
     * @throws AutomationException Error in automation library.
     */
    public WinDef.POINT getClickablePoint() throws AutomationException {
        return this.element.getClickablePoint();
    }

    /**
     * Gets the processID of the element.
     *
     * @return The processId for the element.
     * @throws AutomationException Error in automation library.
     */
    public Object getProcessId() throws AutomationException {
        return this.element.getProcessId();
    }

    /**
     * Gets the framework used by the element.
     *
     * @return The framework object (really a string).
     * @throws AutomationException Error in automation library.
     */
    public Object getFramework() throws AutomationException {
        return this.element.getPropertyValue(PropertyID.FrameworkId.getValue());
    }

    /**
     * Gets the name associated with this element.
     *
     * @return The name of the element.
     * @throws AutomationException Error in automation library.
     */
    public String getName () throws AutomationException {
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
     * Finds all of the elements that are associated with this element.
     *
     * @return List List of elements.
     * @throws AutomationException Something is up with automation.
     */
    protected List<AutomationElement> findAll() throws AutomationException {
        return this.findAll(new TreeScope(TreeScope.Children));
    }

    /**
     * Finds the first match for the condition.
     *
     * @param scope The scope of where to look.
     * @param condition The condition to use.
     * @return The found AutomationElement.
     * @throws AutomationException An error has occurred in automation.
     */
   protected AutomationElement findFirst(final TreeScope scope,
                                         final PointerByReference condition)
           throws AutomationException {
        return this.element.findFirst(scope, condition);
   }

    /**
     * Finds all of the elements that are associated with the given condition.
     *
     * @param scope The scope of where to look.
     * @return List list of all the elements found.
     * @throws AutomationException Something is wrong in automation.
     */
    protected List<AutomationElement> findAll(final TreeScope scope)
            throws AutomationException {
        PointerByReference condition = this.createTrueCondition();
        return this.findAll(scope, condition);
    }

    /**
     * Creates a true condition.
     *
     * @return The true condition.
     * @throws AutomationException Something is up with automation.
     */
    protected PointerByReference createTrueCondition() throws AutomationException {
        return this.automation.createTrueCondition();
    }

    /**
     * Creates a name property condition.
     *
     * @param name The name to use.
     * @return The condition..
     * @throws AutomationException Something has gone wrong.
     */
    protected PointerByReference createNamePropertyCondition(final String name)
            throws AutomationException {
        return this.automation.createNamePropertyCondition(name);
    }

    /**
     * Creates an automation ID property condition.
     *
     * @param automationId The automation ID to use
     * @return The condition
     * @throws AutomationException Something has gone wrong
     */
    protected PointerByReference createAutomationIdPropertyCondition(final String automationId)
            throws AutomationException {
        return this.automation.createAutomationIdPropertyCondition(automationId);
    }

    /**
     * Creates a control type property condition.
     *
     * @param id The control type to use.
     * @return The condition.
     * @throws AutomationException Something has gone wrong.
     */
    protected PointerByReference createControlTypeCondition(final ControlType id)
            throws AutomationException {
        return this.automation.createControlTypeCondition(id);
    }

    /**
     * Creates a class name property condition.
     *
     * @param className The class name to use.
     * @return The condition
     * @throws AutomationException Something has gone wrong.
     */
    protected PointerByReference createClassNamePropertyCondition(final String className)
            throws AutomationException {
        return this.automation.createClassNamePropertyCondition(className);
    }
    /**
     * Creates an AND condition.
     *
     * @param condition1 First condition.
     * @param condition2 Second condition.
     * @return The And condition.
     * @throws AutomationException Error in automation.
     */
   protected PointerByReference createAndCondition(final PointerByReference condition1,
                                                   final PointerByReference condition2)
           throws AutomationException {
       return this.automation.createAndCondition(condition1, condition2);
   }

    /**
     * Finds all of the elements that are associated with the given condition.
     *
     * @param scope The scope of where to look
     * @param condition The condition to check
     * @return IUIAutomationElementArray
     * @throws AutomationException Error in automation library
     */
    protected List<AutomationElement> findAll(final TreeScope scope,
                                              final PointerByReference condition)
            throws AutomationException {
        return this.element.findAll(scope, condition);
    }

    /**
     * Gets the underlying automation pattern.
     *
     * @param id The control id to look for
     * @return The pattern
     * @throws PatternNotFoundException Pattern not found
     * @throws AutomationException Error in automation library
     */
    PointerByReference getPattern (final int id)
            throws PatternNotFoundException, AutomationException {
        PointerByReference unknown = this.element.getPattern(id);

        if (unknown != null) {
            return unknown;
        } else {
            logger.warn("Failed to find pattern");
            throw new PatternNotFoundException();
        }
    }

    /**
     * <p>
     * Gets the selectItem pattern for this control.
     * </p>
     * @return  Returns the IUIAutomationSelectionItemPattern associated with this control, or null if not available
     * @throws PatternNotFoundException Pattern not found
     * @throws AutomationException Error in automation library
     */
    public SelectionItem getSelectItemPattern()
            throws PatternNotFoundException, AutomationException {
        if (this.isSelectionItemPatternAvailable()) {
        	SelectionItem pattern = new SelectionItem();
            PointerByReference unknown = this.getPattern(PatternID.SelectionItem.getValue());

            if (unknown instanceof PatternProvider) { // Hook for mocking tests
            	return (SelectionItem)((PatternProvider) unknown).getPattern();
            }
            
            pattern.setPattern(unknown.getValue());
            return pattern;
        }
        return null;
    }

    /**
     * <p>
     * Gets the selection pattern for this control.
     * </p>
     * @return  Returns the IUIAutomationSelectionPattern associated with this control, or null if not available
     * @throws PatternNotFoundException Pattern not found
     * @throws AutomationException Error in automation library
     */
    Selection getSelectionPattern()
            throws PatternNotFoundException, AutomationException {
        if (this.isSelectionPatternAvailable()) {
        	Selection pattern = new Selection();
            PointerByReference unknown = this.getPattern(PatternID.Selection.getValue());

            if (unknown instanceof PatternProvider) { // Hook for mocking tests
            	return (Selection)((PatternProvider) unknown).getPattern();
            }
            
            pattern.setPattern(unknown.getValue());
            return pattern;
        }
        return null;
    }

    /**
     * <p>
     * Gets the value pattern for this control.
     * </p>
     * @return Returns the IUIAutomationValuePattern associated with this control, or null if not available
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    Value getValuePattern() throws PatternNotFoundException, AutomationException {
        if (this.isValuePatternAvailable()) {
        	Value pattern = new Value();
            PointerByReference unknown = this.getPattern(PatternID.Value.getValue());

            if (unknown instanceof PatternProvider) { // Hook for mocking tests
            	return (Value)((PatternProvider) unknown).getPattern();
            }
            
            pattern.setPattern(unknown.getValue());
            return pattern;
        }
        return null;
    }

    /**
     * <p>
     * Gets the gridItem pattern for this control.
     * </p>
     * @return Returns the IUIAutomationGridItemPattern associated with this control, or null if not available
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    GridItem getGridItemPattern() throws PatternNotFoundException, AutomationException {
        if (this.isGridItemPatternAvailable()) {
        	GridItem pattern = new GridItem();
            PointerByReference unknown = this.getPattern(PatternID.GridItem.getValue());

            if (unknown instanceof PatternProvider) { // Hook for mocking tests
            	return (GridItem)((PatternProvider) unknown).getPattern();
            }
            
            pattern.setPattern(unknown.getValue());
            return pattern;
        }
        return null;
    }

    /**
     * <p>
     * Gets the rangevalue pattern for this control.
     * </p>
     * @return  Returns the IUIAutomationRangeValuePattern associated with this control, or null if not available
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    Range getRangePattern() throws PatternNotFoundException, AutomationException {
        if (isRangeValuePatternAvailable()) {
        	Range pattern = new Range();
            PointerByReference unknown = this.getPattern(PatternID.RangeValue.getValue());

            if (unknown instanceof PatternProvider) { // Hook for mocking tests
            	return (Range)((PatternProvider) unknown).getPattern();
            }
            
            pattern.setPattern(unknown.getValue());
            return pattern;
        }
        return null;
    }

    /**
     * <p>
     * Gets the table pattern for this control.
     * </p>
     * @return  Returns the IUIAutomationTablePattern associated with this control, or null if not available
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    Table getTablePattern() throws PatternNotFoundException, AutomationException {
        if (isTablePatternAvailable()) {
        	Table pattern = new Table();
            PointerByReference unknown = this.getPattern(PatternID.Table.getValue());

            if (unknown instanceof PatternProvider) { // Hook for mocking tests
            	return (Table)((PatternProvider) unknown).getPattern();
            }
            
            pattern.setPattern(unknown.getValue());
            return pattern;
        }
        return null;
    }

    /**
     * <p>
     * Gets the window pattern for this control.
     * </p>
     * @return  Returns the IUIAutomationWindowPattern associated with this control, or null if not available
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    Window getWindowPattern() throws PatternNotFoundException, AutomationException {
        if (this.isWindowPatternAvailable()) {
        	Window pattern = new Window();
            PointerByReference unknown = this.getPattern(PatternID.Window.getValue());

            if (unknown instanceof PatternProvider) { // Hook for mocking tests
            	return (Window)((PatternProvider) unknown).getPattern();
            }
            
            pattern.setPattern(unknown.getValue());
            return pattern;
        }
        return null;
    }

    /**
     * <p>
     * Gets the expand/collapse pattern for this control.
     * </p>
     * @return  Returns the IUIAutomationExpandCollapsePattern associated with this control, or null if not available
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    protected ExpandCollapse getExpandCollapsePattern()
            throws PatternNotFoundException, AutomationException {
        if (isExpandCollapsePatternAvailable()) {
        	ExpandCollapse pattern = new ExpandCollapse();
            PointerByReference unknown = this.getPattern(PatternID.ExpandCollapse.getValue());
            
            if (unknown instanceof PatternProvider) { // Hook for mocking tests
            	return (ExpandCollapse)((PatternProvider) unknown).getPattern();
            }

            pattern.setPattern(unknown.getValue());
            return pattern;
        }
        return null;
    }

    /**
     * <p>
     * Gets the grid pattern for this control.
     * </p>
     * @return  Returns the IUIAutomationGridPattern associated with this control, or null if not available
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    Grid getGridPattern() throws PatternNotFoundException, AutomationException {
        if (isGridPatternAvailable()) {
        	Grid pattern = new Grid();
            PointerByReference unknown = this.getPattern(PatternID.Grid.getValue());

            if (unknown instanceof PatternProvider) { // Hook for mocking tests
            	return (Grid)((PatternProvider) unknown).getPattern();
            }
            
            pattern.setPattern(unknown.getValue());
            return pattern;
        }
        return null;
    }

    /**
     * <p>
     * Gets the toggle pattern for this control.
     * </p>
     * @return  Returns the IUIAutomationTogglePattern associated with this control, or null if not available
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    Toggle getTogglePattern() throws PatternNotFoundException, AutomationException {
        if (isTogglePatternAvailable()) {
        	Toggle pattern = new Toggle();
            PointerByReference unknown = this.getPattern(PatternID.Toggle.getValue());

            if (unknown instanceof PatternProvider) { // Hook for mocking tests
            	return (Toggle)((PatternProvider) unknown).getPattern();
            }
            
            pattern.setPattern(unknown.getValue());
            return pattern;
        }
        return null;
    }

    /**
     * <p>
     * Gets the item container pattern for this control.
     * </p>
     * @return  Returns the IUIAutomationItemContainerPattern associated with this control, or null if not available
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    ItemContainer getItemContainerPattern() throws PatternNotFoundException, AutomationException {
        ItemContainer pattern = new ItemContainer();

        if (isItemContainerPatternAvailable()) {
            PointerByReference unknown = this.getPattern(PatternID.ItemContainer.getValue());

            if (unknown instanceof PatternProvider) { // Hook for mocking tests
            	return (ItemContainer)((PatternProvider) unknown).getPattern();
            }
            
            pattern.setPattern(unknown.getValue());
        }

        return pattern;
    }

    /**
     * <p>
     * Gets the invoke pattern for this control.
     * </p>
     * @return  Returns the IUIAutomationInvokePattern associated with this control, or null if not available
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    protected Invoke getInvokePattern() throws PatternNotFoundException, AutomationException {
        if (isInvokePatternAvailable()) {
        	Invoke pattern = new Invoke();
            PointerByReference unknown = this.getPattern(PatternID.Invoke.getValue());

            if (unknown instanceof PatternProvider) { // Hook for mocking tests
            	return (Invoke)((PatternProvider) unknown).getPattern();
            }
            
            pattern.setPattern(unknown.getValue());
            return pattern;
        }
        return null;
    }

    /**
     * <p>
     * Gets the text pattern for this control.
     * </p>
     * @return  Returns the IUIAutomationTextPattern associated with this control, or null if not available
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    Text getTextPattern() throws PatternNotFoundException, AutomationException {
        if (this.isTextPatternAvailable()) {
        	Text pattern = new Text();
            PointerByReference unknown = this.getPattern(PatternID.Text.getValue());

            if (unknown instanceof PatternProvider) { // Hook for mocking tests
            	return (Text)((PatternProvider) unknown).getPattern();
            }
            
            pattern.setPattern(unknown.getValue());
            return pattern;
        }
        return null;
    }

    /**
     * Is the control enabled.
     *
     * @return Enabled?
     * @throws AutomationException Something is wrong in automation
     */
    public boolean isEnabled () throws AutomationException {
        return this.element.isEnabled().booleanValue();
    }

    /**
     * Gets the bounding rectangle of the control.
     *
     * @return The bounding rectangle
     * @throws AutomationException Something is wrong in automation
     */
    public WinDef.RECT getBoundingRectangle() throws AutomationException {
        return this.element.getBoundingRectangle();
    }

    /**
     * Get the native window handle.
     *
     * @return The handle
     * @throws AutomationException Something is wrong in automation
     */
    public WinDef.HWND getNativeWindowHandle() throws AutomationException {
        Object value = this.element.getPropertyValue(PropertyID.NativeWindowHandle.getValue());
        return new WinDef.HWND(Pointer.createConstant(Integer.valueOf(value.toString())));
    }

    /**
     * Gets the ARIA role of the element.
     *
     * @return The ARIA role
     * @throws AutomationException Something is wrong in automation
     */
    public String getAriaRole() throws AutomationException {
        return this.element.getAriaRole();
    }

    /**
     * The current orientation of the element.
     *
     * @return The orientation
     * @throws AutomationException Something has gone wrong
     */
    public OrientationType getOrientation() throws AutomationException {
        return this.element.getOrientation();
    }

    /**
     * Gets the current class name.
     *
     * @return The class name
     * @throws AutomationException Something has gone wrong
     */
    public String getClassName() throws AutomationException {
        return this.element.getClassName();
    }

    /**
     * Gets the runtime id.
     *
     * @return The runtime id
     * @throws AutomationException Throws big error, so not implemented
     */
    public int[] getRuntimeId() throws AutomationException {
//        return this.element.getRuntimeId();
        throw new AutomationException("Not supported");
    }

    /**
     * Gets the current framework ID for the element.
     *
     * @return The framework id
     * @throws AutomationException Something is wrong in automation
     */
    public String getFrameworkId() throws AutomationException {
        return this.element.getFrameworkId();
    }

    /**
     * Gets the current provider description.
     *
     * @return The provider description
     * @throws AutomationException Something is wrong in automation
     */
    public String getProviderDescription() throws AutomationException {
        return this.element.getProviderDescription();
    }

    /**
     * Gets the current item status.
     *
     * @return The item status
     * @throws AutomationException Something is wrong in automation
     */
    public String getItemStatus() throws AutomationException {
        return this.element.getItemStatus();
    }

    /**
     * Gets the current accelerator key for the element.
     *
     * @return The key
     * @throws AutomationException Something is wrong in automation
     */
    public String getAcceleratorKey() throws AutomationException {
        return this.element.getAcceleratorKey();
    }

    /**
     * Shows the context menu for the control.
     *
     * @throws AutomationException Failed to get the correct interface
     */
    public void showContextMenu() throws AutomationException {
        this.element.showContextMenu();
    }

    /**
     * Creates an Unknown object from the pointer.
     *
     * Allows Mockito to be used to create Unknown objects
     *
     * @param pvInstance The pointer to use
     * @return An Unknown object
     */
    public Unknown makeUnknown(Pointer pvInstance) {
        return new Unknown(pvInstance);
    }
    

    /**
     * <p>
     * Invokes the click event for this control.
     * </p>
     * @throws AutomationException Error in the automation library
     * @throws PatternNotFoundException Could not find the invoke pattern
     */
    public void invoke() throws AutomationException, PatternNotFoundException {
        if (this.invokePattern == null) {
            this.invokePattern = this.getInvokePattern();
        }

        if (this.isInvokePatternAvailable()) {
            this.invokePattern.invoke();
        } else {
            throw new PatternNotFoundException("Invoke could not be called");
        }
    }
    

    /**
     * Gets child Elements.
     *
     * @param deep set to true to get also children of children
     * @return The matching element
     * @throws AutomationException Did not find the element
     */
    protected List<AutomationElement> getChildElements(final boolean deep)
            throws AutomationException {
        return this.findAll(new TreeScope(deep ? TreeScope.Descendants : TreeScope.Children),
        		this.createTrueCondition());
    }
    
 // TreeScope.Parent is not yet supported, see https://docs.microsoft.com/en-us/dotnet/api/system.windows.automation.treescope
//    /**
//     * Gets the parent element
//     *
//     * @return The matching element
//     * @throws AutomationException Did not find the element
//     */
//    protected AutomationElement getParentElement() throws AutomationException {
//        return this.findFirst(new TreeScope(TreeScope.Parent), this.createTrueCondition());
//    }

    /**
     * Gets child controls.
     *
     * @param deep set to true to get also children of children
     * @return The matching element
     * @throws AutomationException Did not find the element
     * @throws PatternNotFoundException Expected pattern not found 
     */
    public List<AutomationBase> getChildren(final boolean deep)
            throws AutomationException, PatternNotFoundException {
        List<AutomationElement> elements = this.getChildElements(deep);
        List<AutomationBase> collection = new LinkedList<>();
        
        for (AutomationElement el: elements) {
        	collection.add(AutomationControlFactory.get(this, el));
        }
        return collection;
    }

    /**
     * Tries to get the full description.
     * @return The full description
     * @throws AutomationException Something has gone wrong
     */
    public String getDescription() throws AutomationException {
        return this.element.getFullDescription();
    }

// TreeScope.Parent is not yet supported, see https://docs.microsoft.com/en-us/dotnet/api/system.windows.automation.treescope
//    /**
//     * Gets the parent control
//     *
//     * @return The matching element
//     * @throws AutomationException Did not find the element
//     * @throws PatternNotFoundException Expected pattern not found 
//     */
//    public AutomationBase getParent() throws AutomationException, PatternNotFoundException {
//    	AutomationElement el = this.getParentElement();
//    	return AutomationControlFactory.get(this, el);
//    }
}
