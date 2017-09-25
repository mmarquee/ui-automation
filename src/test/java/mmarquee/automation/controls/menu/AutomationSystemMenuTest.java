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
package mmarquee.automation.controls.menu;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.ItemNotFoundException;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import mmarquee.automation.uiautomation.IUIAutomationElement3;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Humphreys on 03/12/2016.
 *
 * Tests for SystemMenu automation.
 */
public class AutomationSystemMenuTest extends BaseAutomationTest {

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testName() throws Exception {
        AutomationElement element =
                Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("MENU-01");

        AutomationSystemMenu item = new AutomationSystemMenu(element);

        assertEquals("MENU-01", item.getName());
    }

    @Test
    public void testGetItems_Returns_Correct_Number_Of_Elements() throws Exception {
        AutomationElement element =
                Mockito.mock(AutomationElement.class);

        List<AutomationElement> collection = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        collection.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(collection);

        AutomationSystemMenu item = new AutomationSystemMenu(element);
        List<AutomationMenuItem> items = item.getItems();

        assertTrue(items.size() == 1);
    }

    @Test
    public void testGetItem_Does_Not_Throw_Exception_When_Found() throws Exception {
        AutomationElement element =
                Mockito.mock(AutomationElement.class);

        List<AutomationElement> collection = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[0];

                String value = "NAME-01";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(elem).getCurrentName(any());

        collection.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(collection);

        AutomationSystemMenu menu = new AutomationSystemMenu(element);

        AutomationMenuItem item = menu.getItem("NAME-01");
    }

    @Test(expected = ItemNotFoundException.class)
    public void testGetItem_Throw_Exception_When_Not_Found() throws Exception {
        AutomationElement element =
                Mockito.mock(AutomationElement.class);

        List<AutomationElement> collection = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[0];

                String value = "NAME-01";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(elem).getCurrentName(any());

        collection.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(collection);

        AutomationSystemMenu menu = new AutomationSystemMenu(element);

        AutomationMenuItem item = menu.getItem("NAME-02");
    }
}
