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

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sun.jna.platform.win32.WinDef;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.ControlType;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.Dock;
import mmarquee.automation.pattern.Grid;
import mmarquee.automation.pattern.GridItem;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.MultipleView;
import mmarquee.automation.pattern.Range;
import mmarquee.automation.pattern.Scroll;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.pattern.SelectionItem;
import mmarquee.automation.pattern.Table;
import mmarquee.automation.pattern.TableItem;
import mmarquee.automation.pattern.Text;
import mmarquee.automation.pattern.Toggle;
import mmarquee.automation.pattern.Value;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.OrientationType;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * @author Mark Humphreys
 * Date 28/11/2016.
 *
 * Tests for AutomationBase class
 *
 */
public class AutomationBaseTest {
    protected Logger logger = Logger.getLogger(AutomationBaseTest.class.getName());

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    @Mock AutomationElement element;
    @Mock AutomationElement targetElement;
    @Mock Window pattern;
    @Mock ItemContainer container;

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }
    
    // Helper to test the abstract class
    static class ConcreteAutomationBase extends AutomationBase {

		public ConcreteAutomationBase(AutomationElement element) {
			super(new ElementBuilder(element));
		}
	}

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(pattern.isAvailable()).thenReturn(true);
        when(container.isAvailable()).thenReturn(true);
    }

    @Test
    public void testGetAriaRole_For_Window() throws Exception {
        when(element.getOrientation()).thenReturn(OrientationType.Horizontal);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        window.getAriaRole();

        verify(element, atLeastOnce()).getAriaRole();
    }

    @Test
    public void testGetClassName_For_Window() throws Exception {
        when(element.getClassName()).thenReturn("CLASS-01");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        window.getClassName();

        verify(element, atLeastOnce()).getClassName();
    }

    @Test
    public void testGetElement() throws Exception {
        when(element.getOrientation()).thenReturn(OrientationType.Horizontal);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        AutomationElement result = window.getElement();

        assertTrue(result == element);
    }

    @Test
    public void testGetOrientation_For_Window() throws  Exception {
        when(element.getOrientation()).thenReturn(OrientationType.Horizontal);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        OrientationType value = window.getOrientation();

        assertTrue(value == OrientationType.Horizontal);
    }

    @Test
    public void testGetFramework_Gets_Value_From_Element() throws  Exception {
        when(element.getPropertyValue(anyInt())).thenReturn("FRAMEWORK");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        Object object = window.getFramework();

        assertTrue(object.toString().equals("FRAMEWORK"));
    }

    @Test
    public void testGetFrameworkId_Gets_Value_From_Element() throws  Exception {
        when(element.getFrameworkId()).thenReturn("FRAMEWORK");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        String name = window.getFrameworkId();

        assertTrue(name.equals("FRAMEWORK"));
    }

    @Test
    public void testIsAutomationPatternAvailablePatternID () throws Exception {
    	
        AutomationWindow window = new AutomationWindow(new ElementBuilder(element));
        when(element.getPropertyValue(PropertyID.IsGridItemPatternAvailable.getValue())).thenReturn(1);

        boolean value = window.isAutomationPatternAvailable(PatternID.GridItem);

        assertTrue(value);
    }

    @Test
    public void testIsAutomationPatternAvailableAllDefinedPatternID_no_Exception () throws Exception {
    	
    	AutomationWindow window = new AutomationWindow(new ElementBuilder(element));
        when(element.getPropertyValue(anyInt())).thenReturn(0);
        
    	for (final PatternID patternId: PatternID.values()) {
    		window.isAutomationPatternAvailable(patternId);
    	}
    }

    @Test
    public void testIsAutomationPatternAvailablePatternID_when_not_available () throws Exception {

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element));
        when(element.getPropertyValue(PropertyID.IsRangeValuePatternAvailable.getValue())).thenReturn(0);

        boolean value = window.isAutomationPatternAvailable(PatternID.RangeValue);

        assertFalse(value);
    }


    @Test
    public void testIsAutomationPatternAvailableInt () throws Exception {
    	
        AutomationWindow window = new AutomationWindow(new ElementBuilder(element));
        when(element.getPropertyValue(PropertyID.IsWindowPatternAvailable.getValue())).thenReturn(1);

        boolean value = window.isAutomationPatternAvailable(PatternID.Window.getValue());

        assertTrue(value);
    }


    @Test
    public void testIsAutomationPatternAvailableIntSecondVersion () throws Exception {
    	
        AutomationWindow window = new AutomationWindow(new ElementBuilder(element));
        when(element.getPropertyValue(PropertyID.IsTextPattern2Available.getValue())).thenReturn(1);

        boolean value = window.isAutomationPatternAvailable(PatternID.Text2.getValue());

        assertTrue(value);
    }

    @Test
    public void testIsAutomationPatternAvailableInt_when_not_available () throws Exception {

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element));
        when(element.getPropertyValue(PropertyID.IsTogglePatternAvailable.getValue())).thenReturn(0);

        boolean value = window.isAutomationPatternAvailable(PatternID.Toggle.getValue());

        assertFalse(value);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testIsAutomationPatternAvailableInt_when_bad_number () throws Exception {

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element));

        window.isAutomationPatternAvailable(-815);
    }
    
    @Test
    public void testIsAutomationPatternAvailableBasePatternClass () throws Exception {
    	
    	MultipleView pattern = Mockito.mock(MultipleView.class);
    	when(pattern.isAvailable()).thenReturn(true);

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element).addPattern(pattern));

        boolean value = window.isAutomationPatternAvailable(MultipleView.class);

        assertTrue(value);
    }

    @Test
    public void testIsAutomationPatternAvailableBasePatternClass_when_not_available () throws Exception {
    	
    	Dock pattern = Mockito.mock(Dock.class);
    	when(pattern.isAvailable()).thenReturn(false);

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element).addPattern(pattern));

        boolean value = window.isAutomationPatternAvailable(Dock.class);

        assertFalse(value);
    }
    
    @Test
    public void testIsMultipleViewPatternAvailable () throws Exception {
    	
    	MultipleView pattern = Mockito.mock(MultipleView.class);
    	when(pattern.isAvailable()).thenReturn(true);

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element).addPattern(pattern));

        boolean value = window.isMultipleViewPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testIsGridItemPatternAvailable () throws Exception {

    	GridItem pattern = Mockito.mock(GridItem.class);
    	when(pattern.isAvailable()).thenReturn(true);

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element).addPattern(pattern));

        boolean value = window.isGridItemPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void test_isSelectionItemPatternAvailable  () throws Exception {

    	SelectionItem pattern = Mockito.mock(SelectionItem.class);
    	when(pattern.isAvailable()).thenReturn(true);

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element).addPattern(pattern));

        boolean value = window.isSelectionItemPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void test_isRangeValuePatternAvailable () throws Exception {
        Range pattern = Mockito.mock(Range.class);
    	when(pattern.isAvailable()).thenReturn(true);

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element).addPattern(pattern));

        boolean value = window.isRangeValuePatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testIsTableItemPatternAvailable () throws Exception {
        TableItem pattern = Mockito.mock(TableItem.class);
    	when(pattern.isAvailable()).thenReturn(true);

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element).addPattern(pattern));

        boolean value = window.isTableItemPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void test_isItemContainerPatternAvailable () throws Exception {
        ItemContainer pattern = Mockito.mock(ItemContainer.class);
    	when(pattern.isAvailable()).thenReturn(true);

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element).addPattern(pattern));

        boolean value = window.isItemContainerPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void test_isTogglePatternAvailable() throws Exception {
        Toggle pattern = Mockito.mock(Toggle.class);
    	when(pattern.isAvailable()).thenReturn(true);

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element).addPattern(pattern));

        boolean value = window.isTogglePatternAvailable();

        assertTrue(value);
    }

    @Test
    public void test_isSelectionPatternAvailable() throws Exception {
        Selection pattern = Mockito.mock(Selection.class);
    	when(pattern.isAvailable()).thenReturn(true);

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element).addPattern(pattern));

        boolean value = window.isSelectionPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void test_isTextPatternAvailable () throws Exception {
        Text pattern = Mockito.mock(Text.class);
    	when(pattern.isAvailable()).thenReturn(true);

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element).addPattern(pattern));

        boolean value = window.isTextPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testIsTablePatternAvailable () throws Exception {
        Table pattern = Mockito.mock(Table.class);
    	when(pattern.isAvailable()).thenReturn(true);

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element).addPattern(pattern));

        boolean value = window.isTablePatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testIsValuePatternAvailable () throws Exception {
        Value pattern = Mockito.mock(Value.class);
    	when(pattern.isAvailable()).thenReturn(true);

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element).addPattern(pattern));

        boolean value = window.isValuePatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testIsGridPatternAvailable () throws Exception {
        Grid pattern = Mockito.mock(Grid.class);
    	when(pattern.isAvailable()).thenReturn(true);

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element).addPattern(pattern));

        boolean value = window.isGridPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testisScrollPatternAvailable() throws Exception {
        Scroll pattern = Mockito.mock(Scroll.class);
    	when(pattern.isAvailable()).thenReturn(true);

        AutomationWindow window = new AutomationWindow(new ElementBuilder(element).addPattern(pattern));

        boolean value = window.isScrollPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testisOffScreen_Get_Value_From_Element() throws Exception {
        when(element.getPropertyValue(anyInt())).thenReturn(1);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        boolean value = window.isOffScreen();

        assertTrue(value);
    }

    @Test
    public void testisTableItemPatternAvailable() throws Exception {
        when(element.getPropertyValue(anyInt())).thenReturn(0);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        boolean value = window.isMultipleViewPatternAvailable();

        assertFalse(value);
    }

    @Test
    public void testisScrollItemPatternAvailable () throws Exception {
        when(element.getPropertyValue(anyInt())).thenReturn(1);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        boolean value = window.isScrollItemPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testisTransformPatternAvailable() throws Exception {
        when(element.getPropertyValue(anyInt())).thenReturn(1);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        boolean value = window.isTransformPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testisGridItemPatternAvailable() throws Exception {
        when(element.getPropertyValue(anyInt())).thenReturn(1);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        boolean value = window.isGridItemPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testisGridItemPatternAvailable_Returns_False_When_Property_Is_Zero() throws Exception {
        when(element.getPropertyValue(anyInt())).thenReturn(0);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        boolean value = window.isGridItemPatternAvailable();

        assertFalse(value);
    }

    @Test
    public void testIsDockPatternPatternAvailable_Returns_True_When_Value_Is_One() throws Exception {
        when(element.getPropertyValue(anyInt())).thenReturn(1);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        boolean value = window.isDockPatternAvailable();

        assertTrue(value);
    }

    /*
    @Test(expected = AutomationException.class)
    public void testgetRuntimeIdThrowsException() throws Exception {
        when(element.getPropertyValue(anyInt())).thenReturn(1);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        window.getRuntimeID();
    }
    */

    @Test
    public void testGetProviderDescription_Gets_Value_From_Element() throws Exception {
        when(element.getProviderDescription()).thenReturn("DESCRIPTION");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        String value = window.getProviderDescription();

        assertTrue(value.equals("DESCRIPTION"));
    }

    @Test
    public void testGetAcceleratorKey_Gets_Value_From_Element() throws Exception {
        when(element.getAcceleratorKey()).thenReturn("KEY");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        String value = window.getAcceleratorKey();

        assertTrue(value.equals("KEY"));
    }

    @Test
    public void testgetItemStatus_Gets_Value_From_Element() throws Exception {
        when(element.getItemStatus()).thenReturn("STATUS");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        String value = window.getItemStatus();

        assertTrue(value.equals("STATUS"));
    }

    @Test
    public void testisEnabled_Gets_Value_From_Element() throws Exception {
        when(element.isEnabled()).thenReturn(true);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        boolean value = window.isEnabled();

        assertTrue(value);

    }

    @Test
    public void testGetProcessId_Gets_Value_From_Element() throws Exception {
        when(element.getProcessId()).thenReturn(99);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        Object object = window.getProcessId();

        assertTrue(object.equals(99));
    }

    @Test
    public void testGetClickablePoint_Gets_Value_From_Element() throws Exception {
        when(element.getProcessId()).thenReturn(99);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        window.getClickablePoint();

        verify(element, atLeastOnce()).getClickablePoint();
    }

    @Test
    @Ignore("Throws odd exception")
    public void test_isOffScreen_returns_False_When_Element_Throws_Exception() throws Exception {
        when(element.getProcessId()).thenReturn(99);

        doThrow(AutomationException.class)
                .when(element)
                .getPropertyValue(any());

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        boolean val = window.isOffScreen();

        assertFalse(val);
    }

    @Test
    public void testGetBoundingRect_Gets_Value_From_Element() throws Exception {
        when(element.getProcessId()).thenReturn(99);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow window = new AutomationWindow(
                new ElementBuilder(element).addPattern(container).automation(instance).addPattern(pattern));

        window.getBoundingRectangle();

        verify(element, atLeastOnce()).getBoundingRectangle();
    }

    @Test
    public void test_GetChildren_non_deep_Returns_Children_When_List_Not_Empty() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        list.add(targetElement);
        when(targetElement.getControlType()).thenReturn(ControlType.CheckBox.getValue());

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(list);

        AutomationBase base = new ConcreteAutomationBase(element);

        List<AutomationBase> children = base.getChildren(false);

        assertEquals(list.size(), children.size());
        assertEquals(targetElement, children.get(0).getElement());
        assertEquals(AutomationCheckBox.class, children.get(0).getClass());

        verify(element).findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any());
    }

    @Test
    public void test_GetChildren_non_deep_Returns_No_Items_When_List_Empty() throws Exception {
        AutomationBase base = new ConcreteAutomationBase(element);

        List<AutomationBase> children = base.getChildren(false);

        assertEquals(0, children.size());
    }

    @Test
    public void test_GetChildren_deep_Returns_Children_When_List_Not_Empty() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        list.add(targetElement);
        when(targetElement.getControlType()).thenReturn(ControlType.ComboBox.getValue());

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationBase base = new ConcreteAutomationBase(element);

        List<AutomationBase> children = base.getChildren(true);

        assertEquals(list.size(), children.size());
        assertEquals(targetElement, children.get(0).getElement());
        assertEquals(AutomationComboBox.class, children.get(0).getClass());

        verify(element).findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any());
    }

    @Test
    public void test_GetChildren_deep_Returns_No_Items_When_List_Empty() throws Exception {
        AutomationBase base = new ConcreteAutomationBase(element);

        List<AutomationBase> children = base.getChildren(true);

        assertEquals(0, children.size());
    }
}
