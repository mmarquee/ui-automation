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

import com.sun.jna.ptr.DoubleByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationRangeValuePattern;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * Created by Mark Humphreys on 11/01/2017.
 *
 * Tests for the Range pattern
 */
public class RangePatternTest {
    @Mock
    IUIAutomationRangeValuePattern rawPattern;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testsetValue_Calls_setValue_From_Pattern() throws Exception {
        Range pattern = new Range(rawPattern);

        pattern.setValue(10.0);

        verify(rawPattern, atLeastOnce()).setValue(10.0);
    }

    @Test(expected=AutomationException.class)
    public void test_setValue_Throws_Exception_When_Pattern_Returns_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 1;
            }
        }).when(rawPattern).setValue(anyObject());

        Range pattern = new Range(rawPattern);

        pattern.setValue(10.0);

        verify(rawPattern, atLeastOnce()).setValue(10.0);
    }

    @Test
    public void test_GetValue_Calls_getValue_From_Pattern() throws Exception {
        Range pattern = new Range(rawPattern);

        double value = pattern.getValue();

        verify(rawPattern, atLeastOnce()).getValue(anyObject());
    }

    @Test(expected= AutomationException.class)
    public void test_GetValue_Throws_Exception_When_COM_Call_fails() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                DoubleByReference reference = (DoubleByReference)args[0];

                reference.setValue(4.0);

                return 1;
            }
        }).when(rawPattern).getValue(anyObject());

        Range pattern = new Range(rawPattern);

        double count = pattern.getValue();

        assertTrue(count == 4);
    }

    @Test
    public void test_GetValue_Gets_Value_From_Pattern() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                DoubleByReference reference = (DoubleByReference)args[0];

                reference.setValue(4.0);

                return 0;
            }
        }).when(rawPattern).getValue(anyObject());

        Range pattern = new Range(rawPattern);

        double count = pattern.getValue();

        assertTrue(count == 4);
    }
}
