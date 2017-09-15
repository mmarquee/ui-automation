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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Humphreys on 01/12/2016.
 *
 * Tests for the NUIPane control.
 */
public class AutomationNUIPaneTest {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName_Is_Blank() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        when(element.getClassName()).thenReturn(AutomationNUIPane.CLASS_NAME);
        when(element.getName()).thenReturn("");

        AutomationNUIPane pane = new AutomationNUIPane(element);

        String name = pane.getName();

        assertTrue(name.isEmpty());
    }

    @Test(expected = ElementNotFoundException.class)
    public void testGetNetUIHWND_Throws_Exception_When_NetUIHWND_Not_Found() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        when(element.getClassName()).thenReturn(AutomationNUIPane.CLASS_NAME);
        List<AutomationElement> collection = new ArrayList<>();

        when(element.findAll(any(), any())).thenReturn(collection);

        AutomationNUIPane workPane = new AutomationNUIPane(element);

        workPane.getNetUIHWND(0);

        Mockito.verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetNetUIHWND_When_NetUIHWND_Is_Found() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        when(element.getClassName()).thenReturn(AutomationNUIPane.CLASS_NAME);
        List<AutomationElement> collection = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        Mockito.when(elem.getCurrentClassName(any())).thenAnswer(
            invocation -> {
                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference) args[0];

                String value = "NetUIHWND";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() + 1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        );

        collection.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(collection);

        AutomationNUIPane workPane = new AutomationNUIPane(element);

        workPane.getNetUIHWND(0);

        Mockito.verify(element, atLeastOnce()).findAll(any(), any());
    }
}
