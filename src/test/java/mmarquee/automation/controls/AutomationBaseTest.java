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

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.PatternID;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.OrientationType;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by Mark Humphreys on 28/11/2016.
 *
 * Tests for AutomationBase class
 */
public class AutomationBaseTest {
    protected Logger logger = Logger.getLogger(AutomationBaseTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testGetAriaRole_For_Window() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getOrientation()).thenReturn(OrientationType.Horizontal);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        window.getAriaRole();

        verify(element, atLeastOnce()).getAriaRole();
    }

    @Test
    public void testGetClassName_For_Window() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getClassName()).thenReturn("CLASS-01");

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        window.getClassName();

        verify(element, atLeastOnce()).getClassName();
    }

    @Test
    public void testGetElement() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getOrientation()).thenReturn(OrientationType.Horizontal);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        AutomationElement result = window.getElement();

        assertTrue(result == element);
    }

    @Test
    public void testGetOrientation_For_Window() throws  Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getOrientation()).thenReturn(OrientationType.Horizontal);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        OrientationType value = window.getOrientation();

        assertTrue(value == OrientationType.Horizontal);
    }

    @Test
    public void testGetFramework_Gets_Value_From_Element() throws  Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn("FRAMEWORK");

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        Object object = window.getFramework();

        assertTrue(object.toString().equals("FRAMEWORK"));
    }

    @Test
    public void testGetFrameworkId_Gets_Value_From_Element() throws  Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getFrameworkId()).thenReturn("FRAMEWORK");

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        String name = window.getFrameworkId();

        assertTrue(name.equals("FRAMEWORK"));
    }

    @Test
    public void testIsMultipleViewPatternAvailable () throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isMultipleViewPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testIsGridItemPatternAvailable () throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isGridItemPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void test_isSelectionItemPatternAvailable  () throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isSelectionItemPatternAvailable ();

        assertTrue(value);
    }

    @Test
    public void test_isRangeValuePatternAvailable () throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isRangeValuePatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testIsTableItemPatternAvailable () throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isTableItemPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void test_isItemContainerPatternAvailable () throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isItemContainerPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void test_isTogglePatternAvailable() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isTogglePatternAvailable();

        assertTrue(value);
    }

    @Test
    public void test_isSelectionPatternAvailable() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isSelectionPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void test_isTextPatternAvailable () throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isTextPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testIsTablePatternAvailable () throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isTablePatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testIsValuePatternAvailable () throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isValuePatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testIsGridPatternAvailable () throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isGridPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testisScrollPatternAvailable() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isScrollPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testisOffScreen_Get_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isOffScreen();

        assertTrue(value);
    }

    @Test
    public void testisTableItemPatternAvailable() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(0);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isMultipleViewPatternAvailable();

        assertFalse(value);
    }

    @Test
    public void testisScrollItemPatternAvailable () throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isScrollItemPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testisTransformPatternAvailable() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isTransformPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testisGridItemPatternAvailable() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isGridItemPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testisGridItemPatternAvailable_Returns_False_When_Property_Is_Zero() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(0);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isGridItemPatternAvailable();

        assertFalse(value);
    }

    @Test
    public void testIsDockPatternPatternAvailable_Returns_True_When_Value_Is_One() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isDockPatternAvailable();

        assertTrue(value);
    }

    @Test(expected = AutomationException.class)
    public void testgetRuntimeIdThrowsException() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        window.getRuntimeId();
    }

    @Test
    public void testGetProviderDescription_Gets_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getProviderDescription()).thenReturn("DESCRIPTION");

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        String value = window.getProviderDescription();

        assertTrue(value.equals("DESCRIPTION"));
    }

    @Test
    public void testGetAcceleratorKey_Gets_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getAcceleratorKey()).thenReturn("KEY");

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        String value = window.getAcceleratorKey();

        assertTrue(value.equals("KEY"));
    }

    @Test
    public void testgetItemStatus_Gets_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getItemStatus()).thenReturn("STATUS");

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        String value = window.getItemStatus();

        assertTrue(value.equals("STATUS"));
    }

    @Test
    public void testisEnabled_Gets_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.isEnabled()).thenReturn(new WinDef.BOOL(true));

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isEnabled();

        assertTrue(value);

    }

    @Test
    public void testGetProcessId_Gets_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getProcessId()).thenReturn(99);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        Object object = window.getProcessId();

        assertTrue(object.equals(99));
    }

    @Test
    public void testGetClickablePoint_Gets_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getProcessId()).thenReturn(99);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        WinDef.POINT point = window.getClickablePoint();

        verify(element, atLeastOnce()).getClickablePoint();
    }

    @Test
    @Ignore("Throws odd exception")
    public void test_isOffScreen_returns_False_When_Element_Throws_Exeception() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getProcessId()).thenReturn(99);

        doThrow(AutomationException.class)
                .when(element)
                .getPropertyValue(anyObject());

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean val = window.isOffScreen();

        assertFalse(val);
    }

    @Test(expected= PatternNotFoundException.class)
    public void test_getPattern_throws_PatterNotFoundException_When_Pattern_Not_Found () throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        window.getPattern(PatternID.Text.getValue());

        verify(element, atLeastOnce()).getPattern(anyInt());
    }

    @Test
    public void testGetBoundingRect_Gets_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getProcessId()).thenReturn(99);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        WinDef.RECT rect = window.getBoundingRectangle();

        verify(element, atLeastOnce()).getBoundingRectangle();
    }

    @Test
    @Ignore("Need to mock variants somehow")
    public void test_getSelectItemPattern() throws Exception {
        IUIAutomationElement3 el = Mockito.mock(IUIAutomationElement3.class);

        AutomationElement element = Mockito.mock(AutomationElement.class);
        element.element = el;

        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[1];

                reference.setValue(0);

                return 0;
            }
        }).when(el).getCurrentPropertyValue(anyObject(), anyObject());

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        window.getSelectItemPattern();

        verify(element, atLeastOnce()).getBoundingRectangle();
    }
}
