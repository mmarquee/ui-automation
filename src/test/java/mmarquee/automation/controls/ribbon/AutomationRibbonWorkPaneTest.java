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
package mmarquee.automation.controls.ribbon;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.BaseAutomationTest;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Humphreys on 01/12/2016.
 *
 * Tests for RibbonWorkPane controls
 */
public class AutomationRibbonWorkPaneTest extends BaseAutomationTest {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        when(element.getName()).thenReturn("NAME");
        AutomationRibbonWorkPane pane = new AutomationRibbonWorkPane(element);

        String name = pane.name();

        assertTrue(name.equals("NAME"));
    }

    @Test(expected = ElementNotFoundException.class)
    public void testGetNUIPane_Throws_Exception_When_NUIPane_Not_Found() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        List<AutomationElement> collection = new ArrayList<>();

        when(element.findAll(anyObject(), anyObject())).thenReturn(collection);

        AutomationRibbonWorkPane workPane = new AutomationRibbonWorkPane(element);

        AutomationNUIPane uiPane = workPane.getNUIPane(0);

        Mockito.verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetNUIPane_When_NUIPane_Is_Found() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        List<AutomationElement> collection = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[0];

                String value = "NUIPane";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(elem).getCurrentClassName(anyObject());

        collection.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(collection);

        AutomationRibbonWorkPane workPane = new AutomationRibbonWorkPane(element);

        AutomationNUIPane uiPane = workPane.getNUIPane(0);

        Mockito.verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }
}
