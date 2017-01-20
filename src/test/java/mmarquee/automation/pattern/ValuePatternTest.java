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

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationValuePattern;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

/**
 * Created by Mark Humphreys on 11/01/2017.
 *
 * Tests for the Value pattern
 *
 */
public class ValuePatternTest {
    @Mock
    IUIAutomationValuePattern rawPattern;

    @Spy
    private Unknown mockUnknown;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_Value_Calls_getValue_From_Pattern() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[0];

                String value = "Hello";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(rawPattern).getValue(anyObject());

        Value pattern = new Value(rawPattern);

        String text = pattern.value();

        assertTrue(text.equals("Hello"));

        verify(rawPattern, atLeastOnce()).getValue(anyObject());
    }

    @Test(expected=AutomationException.class)
    public void test_Value_Throws_Exception_When_COM_Sets_Error_State() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[0];

//                reference.setValue("VALUE-01");

                return 1;
            }
        }).when(rawPattern).getValue(anyObject());

        Value pattern = new Value(rawPattern);

        String text = pattern.value();

        verify(rawPattern, atLeastOnce()).getValue(anyObject());
    }

    @Test(expected= AutomationException.class)
    public void test_isReadOnly_Throws_Exception_When_COM_Returns_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(0);

                return 1;
            }
        }).when(rawPattern).getCurrentIsReadOnly(anyObject());

        Value pattern = new Value(rawPattern);

        boolean state = pattern.isReadOnly();

        assertFalse(state);
    }

    @Test
    public void test_isReadOnly_Returns_False_When_COM_Returns_Two() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(0);

                return 0;
            }
        }).when(rawPattern).getCurrentIsReadOnly(anyObject());

        Value pattern = new Value(rawPattern);

        boolean state = pattern.isReadOnly();

        assertFalse(state);
    }

    @Test
    public void test_isReadOnly_Returns_True_When_COM_Returns_One() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(1);

                return 0;
            }
        }).when(rawPattern).getCurrentIsReadOnly(anyObject());

        Value pattern = new Value(rawPattern);

        boolean state = pattern.isReadOnly();

        assertTrue(state);
    }

    @Test(expected=AutomationException.class)
    public void test_SetValue_Throws_Exception_When_COM_Returns_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 1;
            }
        }).when(rawPattern).setValue(anyObject());

        Value pattern = new Value(rawPattern);

        pattern.setValue("VALUE-01");
    }

    @Test
    public void test_SetValue_Calls_SetValue_From_Pattern() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 0;
            }
        }).when(rawPattern).setValue(anyObject());

        Value pattern = new Value(rawPattern);

        pattern.setValue("VALUE-01");

        verify(rawPattern, atLeastOnce()).setValue(anyObject());
    }

    @Test(expected=AutomationException.class)
    public void test_That_getPattern_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(-1);
            }
        }).when(mockUnknown).QueryInterface(anyObject(), anyObject());

        Value pattern = new Value();

        Value spyPattern = Mockito.spy(pattern);

        IUIAutomationValuePattern mockPattern = Mockito.mock(IUIAutomationValuePattern.class);

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(anyObject());

        doReturn(mockPattern)
                .when(spyPattern)
                .convertPointerToInterface(anyObject());

        spyPattern.value();

        verify(mockPattern, atLeastOnce()).getValue(anyObject());
    }

    @Test
    public void test_That_getPattern_Gets_Pattern_When_No_Pattern_Set() throws Exception {
        doAnswer(new Answer() {
            @Override
            public WinNT.HRESULT answer(InvocationOnMock invocation) throws Throwable {
                return new WinNT.HRESULT(0);
            }
        }).when(mockUnknown).QueryInterface(anyObject(), anyObject());

        Value pattern = new Value();

        Value spyPattern = Mockito.spy(pattern);

        IUIAutomationValuePattern mockPattern = Mockito.mock(IUIAutomationValuePattern.class);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[0];

                String value = "Hello";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(mockPattern).getValue(anyObject());

        doReturn(mockUnknown)
                .when(spyPattern)
                .makeUnknown(anyObject());

        doReturn(mockPattern)
                .when(spyPattern)
                .convertPointerToInterface(anyObject());

        spyPattern.value();

        verify(mockPattern, atLeastOnce()).getValue(anyObject());
    }
}
