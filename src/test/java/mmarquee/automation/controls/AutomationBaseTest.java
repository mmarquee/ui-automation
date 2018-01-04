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
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.sun.jna.platform.win32.User32;
import mmarquee.automation.*;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import org.apache.log4j.Logger;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;

import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Window;
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
    @Mock
    AutomationElement element;
    @Mock
    AutomationElement targetElement;
    @Mock
    Window pattern;
    @Mock
    ItemContainer container;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    // Helper to test the abstract class
    static class ConcreteAutomationBase extends AutomationBase {

        public ConcreteAutomationBase(AutomationElement element) {
            super(new ElementBuilder(element));
        }
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
