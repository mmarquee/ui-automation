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

import com.sun.jna.ptr.PointerByReference;
import com.sun.org.apache.xpath.internal.operations.Bool;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.OrientationType;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 28/11/2016.
 */
public class AutomationBaseTest extends BaseAutomationTest {
    protected Logger logger = Logger.getLogger(AutomationBaseTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    protected void closeApplication() throws PatternNotFoundException, AutomationException {
        AutomationButton btnClickMe = window.getButton("Press Me");
        assertTrue(btnClickMe.name().equals("Press Me"));

        btnClickMe.click();

        AutomationWindow popup = window.getWindow("Confirmation");

        AutomationButton btn = popup.getButton("Yes");
        btn.click();

        this.andRest();
    }

    @Test
    public void testGetAriaRole_For_Window() throws Exception {
        loadApplication("apps\\WpfApplicationWithAutomationIds.exe", "MainWindow");

        try {
            String m = window.getAriaRole();

            assertTrue(m.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetOrientation_For_Window() throws  Exception {
        loadApplication("apps\\WpfApplicationWithAutomationIds.exe", "MainWindow");

        try {
            OrientationType m = window.getOrientation();

            assertTrue(m == OrientationType.None);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetFramework_Gets_Value_From_Element() throws  Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.currentPropertyValue(anyInt())).thenReturn(new String("FRAMEWORK"));

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
    public void testisOffScreen() throws Exception {
        loadApplication("apps\\WpfApplicationWithAutomationIds.exe", "MainWindow");

        try {
            assertTrue(window.isOffScreen());
        } finally {
            closeApplication();
        }
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
        loadApplication("apps\\WpfApplicationWithAutomationIds.exe", "MainWindow");

        try {
            int[] result = window.getRuntimeId();
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testgetProviderDescription_starts_with_correct_string() throws Exception {
        loadApplication("apps\\WpfApplicationWithAutomationIds.exe", "MainWindow");

        try {
            String result = window.getProviderDescription();

            logger.info(result);

            assertTrue(result.startsWith("[pid:"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetElement() throws Exception {
        loadApplication("apps\\WpfApplicationWithAutomationIds.exe", "MainWindow");

        try {
            AutomationElement result = window.getElement();

            assertTrue(result != null);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testgetAcceleratorKey() throws Exception {
        loadApplication("apps\\WpfApplicationWithAutomationIds.exe", "MainWindow");

        try {
            String result = window.getAcceleratorKey();
            assertTrue(result.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testgetItemStatus() throws Exception {
        loadApplication("apps\\WpfApplicationWithAutomationIds.exe", "MainWindow");

        try {
            String result = window.getItemStatus();
            assertTrue(result.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testisEnabled() throws Exception {
        loadApplication("apps\\WpfApplicationWithAutomationIds.exe", "MainWindow");

        try {
            assertTrue(window.isEnabled());
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetProcessId_Equals_Application_Id() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getProcessId()).thenReturn(99);

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        Object object = window.getProcessId();

        assertTrue(object.equals(99));
    }

    @Test
    public void testGetProcessId() throws Exception {
        loadApplication("apps\\WpfApplicationWithAutomationIds.exe", "MainWindow");

        try {
            Object obj = window.getProcessId();

            logger.info(obj);

            assertTrue(obj != null);
        } finally {
            closeApplication();
        }
    }
}
