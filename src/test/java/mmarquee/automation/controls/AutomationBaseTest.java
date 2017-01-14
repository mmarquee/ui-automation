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

import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.OrientationType;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mockito;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        when(element.currentPropertyValue(anyInt())).thenReturn("FRAMEWORK");

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

        when(element.currentPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isMultipleViewPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testisScrollPatternAvailable() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.currentPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isScrollPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testisOffScreen_Get_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.currentPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isOffScreen();

        assertTrue(value);
    }

    @Test
    public void testisTableItemPatternAvailable() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.currentPropertyValue(anyInt())).thenReturn(0);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isMultipleViewPatternAvailable();

        assertFalse(value);
    }

    @Test
    public void testisScrollItemPatternAvailable () throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.currentPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isScrollItemPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testisTransformPatternAvailable() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.currentPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isTransformPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testisGridItemPatternAvailable() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.currentPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isGridItemPatternAvailable();

        assertTrue(value);
    }

    @Test
    public void testisGridItemPatternAvailable_Returns_False_When_Property_Is_Zero() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.currentPropertyValue(anyInt())).thenReturn(0);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isGridItemPatternAvailable();

        assertFalse(value);
    }

    @Test
    public void testIsDockPatternPatternAvailable_Returns_True_When_Value_Is_One() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.currentPropertyValue(anyInt())).thenReturn(1);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        boolean value = window.isDockPatternAvailable();

        assertTrue(value);
    }

    @Test(expected = NotImplementedException.class)
    public void testgetRuntimeIdThrowsException() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.currentPropertyValue(anyInt())).thenReturn(1);

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

        when(element.currentIsEnabled()).thenReturn(new WinDef.BOOL(true));

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
}
