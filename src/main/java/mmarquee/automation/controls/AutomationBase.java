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

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import mmarquee.automation.pattern.BasePattern;
import mmarquee.automation.pattern.ScrollItem;
import mmarquee.automation.pattern.Text;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.Dock;
import mmarquee.automation.pattern.LegacyIAccessible;
import mmarquee.automation.pattern.Grid;
import mmarquee.automation.pattern.GridItem;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.MultipleView;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.Value;
import mmarquee.automation.pattern.Scroll;
import mmarquee.automation.pattern.Range;
import mmarquee.automation.pattern.SelectionItem;
import mmarquee.automation.pattern.TableItem;
import mmarquee.automation.pattern.Table;
import mmarquee.automation.pattern.Transform;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.pattern.Toggle;
import mmarquee.automation.pattern.PatternNotFoundException;

import org.apache.log4j.Logger;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomation;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.uiautomation.OrientationType;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * The base for automation.
 *
 * @author Mark Humphreys
 * Date 26/01/2016.
 */
public abstract class AutomationBase
        implements Automatable, CanRequestBasePattern {

    /**
     * The logger.
     */
    private final Logger logger =
            Logger.getLogger(AutomationBase.class.getName());

    /**
     * Gets the logger.
     * @return The logger
     */
    public Logger getLogger() {
        return this.logger;
    }

    /**
     * The automation element.
     */
    private AutomationElement element;

    /**
     * The automation library wrapper.
     */
    private UIAutomation automation;

    /**
     * Gets the automation wrapper.
     * @return The automation library
     */
    public UIAutomation getAutomation() {
        return this.automation;
    }

    /**
     * The basic IAccessible pattern.
     */
    private LegacyIAccessible accessible;

    /**
     * The available Patterns.
     */
    protected final Map<Class<? extends BasePattern>, BasePattern>
            automationPatterns = new HashMap<>();

    /**
     * The pattern access monitor.
     */
    private Object patternAccessMonitor = new Object();

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

        for (final BasePattern pattern: builder.getAutomationPatterns()) {
            setAutomationPattern(pattern);
        }
    }

    /**
     * for testing purposes.
     *
     * @param pattern The pattern to look for
     */
    void setAutomationPattern(final BasePattern pattern) {
        this.automationPatterns.put(pattern.getPatternClass(), pattern);
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
     * Throws an exception if the element's class name does not equal the
     * expected one.
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
     * Checks whether a pattern is available. Can be used if no BasePattern
     * class is available for the pattern, yet. Tries to get the matching
     * propertyId to check for availability of for the given PatternId
     *
     * @param patternId
     *            the Pattern ID to test for
     * @return True if available.
     *
     */
    public boolean isAutomationPatternAvailable(final PatternID patternId) {
        try {
        	final String patternIdName = patternId.name();
        	final String patternIdNameText =
                    patternIdName.replaceAll("\\d", "");
        	final String patternIdNameVersion =
                    patternIdName.replaceAll("\\D", "");
        	final String propertyName =
                    String.format("Is%sPattern%sAvailable",
                            patternIdNameText, patternIdNameVersion);
        	final PropertyID propertyId = PropertyID.valueOf(propertyName);

            final Object propertyValue =
                    this.element.getPropertyValue(propertyId.getValue());
            return BaseAutomation.isPropertyValueTrue(propertyValue);
        } catch (AutomationException ex) {
            return false;
        }
    }

    /**
     * Checks whether a pattern is available. Can be used if no BasePattern
     * class is available for the pattern, yet. Tries to get the matching
     * propertyId to check for availability of for the given PatternId
     *
     * @param patternIdValue
     *            the numerical id of the pattern to test for
     * @return True if available.
     *
     */
    public boolean isAutomationPatternAvailable(final int patternIdValue) {
    	for (final PatternID patternId: PatternID.values()) {
    		if (patternId.getValue() == patternIdValue) {
    	        return isAutomationPatternAvailable(patternId);
    		}
    	}
    	throw new IllegalArgumentException(
    	        "No PatternID constant defined for patternId " + patternIdValue);
    }

    /**
     * Checks whether a pattern is available.
     *
     * @param patternClass pattern to search for.
     * @return True if available.
     *
     */
    public boolean isAutomationPatternAvailable(
            final Class<? extends BasePattern> patternClass) {
        try {
            return requestAutomationPattern(patternClass).isAvailable();
        } catch (AutomationException ex) {
            return false;
        }
    }

    /**
     * Is the dock pattern available.
     *
     * @return Yes or no.
     */
    public boolean isDockPatternAvailable() {
        return isAutomationPatternAvailable(Dock.class);
    }

    /**
     * Is the expand/collapse pattern available.
     *
     * @return Yes or no.
     */
    public boolean isExpandCollapsePatternAvailable() {
        return isAutomationPatternAvailable(ExpandCollapse.class);
    }

    /**
     * Is the legacy accessible pattern available.
     *
     * @return Yes or no.
     */
    public boolean isLegacyIAccessiblePatternAvailable() {
        return isAutomationPatternAvailable(LegacyIAccessible.class);
    }

    /**
     * Is the grid item pattern available.
     *
     * @return Yes or no.
     */
    public boolean isGridItemPatternAvailable() {
        return isAutomationPatternAvailable(GridItem.class);
    }

    /**
     * Is the multiple view pattern available.
     *
     * @return Yes or no.
     */
    public boolean isMultipleViewPatternAvailable() {
        return isAutomationPatternAvailable(MultipleView.class);
    }

    /**
     * Is the invoke pattern available.
     *
     * @return Yes or no.
     */
    public boolean isInvokePatternAvailable() {
        return isAutomationPatternAvailable(Invoke.class);
    }

    /**
     * Is the grid pattern available.
     *
     * @return Yes or no.
     */
    public boolean isGridPatternAvailable() {
        return isAutomationPatternAvailable(Grid.class);
    }

    /**
     * Is the range value pattern available.
     *
     * @return Yes or no.
     */
    public boolean isRangeValuePatternAvailable() {
        return isAutomationPatternAvailable(Range.class);
    }

    /**
     * Is the scroll pattern available.
     *
     * @return Yes or no.
     */
    public boolean isScrollPatternAvailable() {
        return isAutomationPatternAvailable(Scroll.class);
    }

    /**
     * Is the selection item pattern available.
     *
     * @return Yes or no.
     */
    public boolean isSelectionItemPatternAvailable() {
        return isAutomationPatternAvailable(SelectionItem.class);
    }

    /**
     * Is the scroll item pattern available.
     *
     * @return Yes or no.
     */
    public boolean isScrollItemPatternAvailable() {
        return isAutomationPatternAvailable(ScrollItem.class);
    }

    /**
     * Is the window pattern available.
     *
     * @return Yes or no.
     */
    public boolean isWindowPatternAvailable() {
        return isAutomationPatternAvailable(Window.class);
    }

    /**
     * Is the text pattern available.
     *
     * @return Yes or no.
     */
    public boolean isTextPatternAvailable() {
        return isAutomationPatternAvailable(Text.class);
    }

    /**
     * Is the table item pattern available.
     *
     * @return Yes or no.
     */
    public boolean isTableItemPatternAvailable() {
        return isAutomationPatternAvailable(TableItem.class);
    }

    /**
     * Is the table pattern available.
     *
     * @return Yes or no.
     */
    public boolean isTablePatternAvailable() {
        return isAutomationPatternAvailable(Table.class);
    }

    /**
     * Is the selection pattern available.
     *
     * @return Yes or no.
     */
    public boolean isSelectionPatternAvailable() {
        return isAutomationPatternAvailable(Selection.class);
    }

    /**
     * Is the transform pattern available.
     *
     * @return Yes or no.
     */
    public boolean isTransformPatternAvailable() {
        return isAutomationPatternAvailable(Transform.class);
    }

    /**
     * Is the toggle pattern available.
     *
     * @return Yes or no.
     */
    public boolean isTogglePatternAvailable() {
        return isAutomationPatternAvailable(Toggle.class);
    }

    /**
     * Is the item container pattern available.
     *
     * @return Yes or no.
     */
    public boolean isItemContainerPatternAvailable() {
        return isAutomationPatternAvailable(ItemContainer.class);
    }

    /**
     * Is the value pattern available.
     *
     * @return Yes or no.
     */
    public boolean isValuePatternAvailable() {
        return isAutomationPatternAvailable(Value.class);
    }

    /**
     * Is the control off screen.
     *
     * @return Off screen.
     */
    public boolean isOffScreen() {
        try {
            final Object propertyValue = this.element.getPropertyValue(PropertyID.IsOffscreen.getValue());
            return BaseAutomation.isPropertyValueTrue(propertyValue);
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
    public String getName() throws AutomationException {
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
    protected PointerByReference createAutomationIdPropertyCondition(
            final String automationId)
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
    protected PointerByReference createClassNamePropertyCondition(
            final String className)
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
   protected PointerByReference createAndCondition(
           final PointerByReference condition1,
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
    protected List<AutomationElement> findAll(
            final TreeScope scope,
            final PointerByReference condition)
            throws AutomationException {
        return this.element.findAll(scope, condition);
    }

    /**
     * Is the control enabled.
     *
     * @return Enabled?
     * @throws AutomationException Something is wrong in automation
     */
    public boolean isEnabled() throws AutomationException {
        return this.element.isEnabled();
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
        Object value =
                this.element.getPropertyValue(
                        PropertyID.NativeWindowHandle.getValue());
        return new WinDef.HWND(Pointer.createConstant(
                Integer.valueOf(value.toString())));
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
    /*
    public int[] getRuntimeId() throws AutomationException {
        throw new AutomationException("Not supported");
        return this.element.getRuntimeId();
    }
    */

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
    public void invoke() throws AutomationException {

        final Invoke invokePattern = requestAutomationPattern(Invoke.class);
        if (invokePattern.isAvailable()) {
            invokePattern.invoke();
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
        return this.findAll(
                new TreeScope(deep ? TreeScope.Descendants : TreeScope.Children),
        		this.createTrueCondition());
    }

// TreeScope.Parent is not yet supported,
// see https://docs.microsoft.com/en-us/dotnet/api/system.windows.automation.treescope
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
     * Gets the metadata associated with the element.
     * @return The metadata
     * @throws AutomationException Library exception
     */
    public Object getMetadata() throws AutomationException {
        return this.getElement().getCurrentMetadataValue();
    }

    /**
     * Tries to get the full description.
     * @return The full description
     * @throws AutomationException Something has gone wrong
     */
    public String getDescription() throws AutomationException {
        return this.element.getFullDescription();
    }

    /**
     * <p>
     * Gets the specified pattern for this control from the underlying Windows
     * API.
     * </p>
     * @return  Returns the IUIAutomationInvokePattern associated with this
     *          control, or null if not available
     * @throws PatternNotFoundException Pattern is not found
     * @throws AutomationException Error in automation library
     */
    @Override
    public <T extends BasePattern> T requestAutomationPattern(
            final Class<T> automationPatternClass) throws AutomationException {
        synchronized(patternAccessMonitor) {
            @SuppressWarnings("unchecked")
            T automationPattern = (T) automationPatterns.get(automationPatternClass);
            if (automationPattern == null) {
            	automationPattern =
                        this.element.getProvidedPattern(automationPatternClass);
                if (automationPattern == null) {
	                try {
	                    automationPattern =
                                automationPatternClass.getConstructor(AutomationElement.class).newInstance(this.element);
	                } catch (Throwable e) {
	                	e = getInnerException(e);
	                	if (e instanceof AutomationException) throw (AutomationException) e;
	                	throw new AutomationException(e);
	                }
                }
                automationPatterns.put(automationPatternClass, automationPattern);
            }
            return automationPattern;
        }
    }

    /**
     * Gets the inner exception.
     *
     * @param e The throwable
     * @return The inner exception from the input throwable
     */
    private Throwable getInnerException(Throwable e) {
    	if (e instanceof InvocationTargetException) {
    		return  getInnerException(e.getCause());
    	}
    	return e;
    }

}
