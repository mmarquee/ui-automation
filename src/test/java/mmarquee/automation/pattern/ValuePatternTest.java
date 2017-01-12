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

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationValuePattern;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @Ignore("Needs further work")
    public void test_Value_Calls_getValue_From_Pattern() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[0];

//                reference.setValue("VALUE-01");

                return 0;
            }
        }).when(rawPattern).getValue(anyObject());

        Value pattern = new Value(rawPattern);

        String text = pattern.value();

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
}
