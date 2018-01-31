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
package mmarquee.automation.pattern;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.uiautomation.IUIAutomationValuePattern;

/**
 * @author Mark Humphreys
 * Date 11/01/2017.
 *
 * Tests for the Value pattern
 *
 */
public class ValuePatternTest {
    @Mock
    IUIAutomationValuePattern rawPattern;
    @Mock
    AutomationElement element;

    @Spy
    private Unknown mockUnknown;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        BaseAutomationTest.declarePatternAvailable(element, 
        		PatternID.Value, PropertyID.IsValuePatternAvailable);
    }

    @Test
    public void test_Value_Calls_getValue_From_Pattern() throws Exception {
        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            PointerByReference reference = (PointerByReference)args[0];

            String value = "Hello";
            Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
            pointer.setWideString(0, value);

            reference.setValue(pointer);

            return 0;
        }).when(rawPattern).getValue(any());

        Value pattern = new Value(element);
        pattern.rawPattern = rawPattern;

        String text = pattern.value();

        assertTrue(text.equals("Hello"));

        verify(rawPattern, atLeastOnce()).getValue(any());
    }

    @Test(expected=AutomationException.class)
    public void test_Value_Throws_Exception_When_COM_Sets_Error_State() throws Exception {
        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            PointerByReference reference = (PointerByReference)args[0];

//                reference.setValue("VALUE-01");

            return 1;
        }).when(rawPattern).getValue(any());

        Value pattern = new Value(element);
        pattern.rawPattern = rawPattern;

        String text = pattern.value();

        verify(rawPattern, atLeastOnce()).getValue(any());
    }

    @Test(expected= AutomationException.class)
    public void test_isReadOnly_Throws_Exception_When_COM_Returns_Error() throws Exception {
        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            IntByReference reference = (IntByReference)args[0];

            reference.setValue(0);

            return 1;
        }).when(rawPattern).getCurrentIsReadOnly(any());

        Value pattern = new Value(element);
        pattern.rawPattern = rawPattern;

        boolean state = pattern.isReadOnly();

        assertFalse(state);
    }

    @Test
    public void test_isReadOnly_Returns_False_When_COM_Returns_Two() throws Exception {
        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            IntByReference reference = (IntByReference)args[0];

            reference.setValue(0);

            return 0;
        }).when(rawPattern).getCurrentIsReadOnly(any());

        Value pattern = new Value(element);
        pattern.rawPattern = rawPattern;

        boolean state = pattern.isReadOnly();

        assertFalse(state);
    }

    @Test
    public void test_isReadOnly_Returns_True_When_COM_Returns_One() throws Exception {
        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            IntByReference reference = (IntByReference)args[0];

            reference.setValue(1);

            return 0;
        }).when(rawPattern).getCurrentIsReadOnly(any());

        Value pattern = new Value(element);
        pattern.rawPattern = rawPattern;

        boolean state = pattern.isReadOnly();

        assertTrue(state);
    }

    @Test(expected=AutomationException.class)
    @Ignore("Throws Mockito exception")
    public void test_That_getPattern_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(invocation -> new WinNT.HRESULT(-1)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Value pattern = new Value(element);

        Value spyPattern = Mockito.spy(pattern);

        IUIAutomationValuePattern mockPattern = Mockito.mock(IUIAutomationValuePattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any(Pointer.class));

        doReturn(mockPattern)
                .when(spyPattern)
                .convertPointerToInterface(any(PointerByReference.class));

        spyPattern.value();

        verify(mockPattern, atLeastOnce()).getValue(any());
    }

    @Test
    public void test_That_getPattern_Gets_Pattern_When_No_Pattern_Set() throws Exception {
        doAnswer(invocation -> new WinNT.HRESULT(0)).when(mockUnknown).QueryInterface(any(Guid.REFIID.class), any(PointerByReference.class));

        Value pattern = new Value(element);

        Value spyPattern = Mockito.spy(pattern);

        IUIAutomationValuePattern mockPattern = Mockito.mock(IUIAutomationValuePattern.class);

        doAnswer(invocation -> {

            Object[] args = invocation.getArguments();
            PointerByReference reference = (PointerByReference)args[0];

            String value = "Hello";
            Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
            pointer.setWideString(0, value);

            reference.setValue(pointer);

            return 0;
        }).when(mockPattern).getValue(any());

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(any());

        doReturn(mockPattern)
                .when(spyPattern)
                .convertPointerToInterface(any());

        spyPattern.value();

        verify(mockPattern, atLeastOnce()).getValue(any());
    }
}
