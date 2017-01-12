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
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationTablePattern;
import mmarquee.automation.uiautomation.RowOrColumnMajor;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doAnswer;

/**
 * Created by Mark Humphreys on 11/01/2017.
 */
public class TablePatternTest {
    @Mock
    IUIAutomationTablePattern rawPattern;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getRowOrColumnMajor_Returns_ColumnMajor_When_Pattern_Returns_One() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(1);

                return 0;
            }
        }).when(rawPattern).getCurrentRowOrColumnMajor(anyObject());

        Table item = new Table(rawPattern);

        RowOrColumnMajor value = item.getRowOrColumnMajor();

        assertTrue(value == RowOrColumnMajor.ColumnMajor);
    }

    @Test
    public void test_getRowOrColumnMajor_Returns_RowMajor_When_Pattern_Returns_Zero() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(0);

                return 0;
            }
        }).when(rawPattern).getCurrentRowOrColumnMajor(anyObject());

        Table item = new Table(rawPattern);

        RowOrColumnMajor value = item.getRowOrColumnMajor();

        assertTrue(value == RowOrColumnMajor.RowMajor);
    }

    @Test
    public void test_getRowOrColumnMajor_Returns_Indeterminate_When_Pattern_Returns_Three() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(2);

                return 0;
            }
        }).when(rawPattern).getCurrentRowOrColumnMajor(anyObject());

        Table item = new Table(rawPattern);

        RowOrColumnMajor value = item.getRowOrColumnMajor();

        assertTrue(value == RowOrColumnMajor.Indeterminate);
    }

    @Test(expected= AutomationException.class)
    public void test_getRowOrColumnMajor_Throws_Exception_When_Pattern_Returns_An_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(2);

                return 1;
            }
        }).when(rawPattern).getCurrentRowOrColumnMajor(anyObject());

        Table item = new Table(rawPattern);

        RowOrColumnMajor value = item.getRowOrColumnMajor();

        assertTrue(value == RowOrColumnMajor.Indeterminate);
    }

    @Test(expected= AutomationException.class)
    public void test_GetText_Throws_Exception_When_getCurrentColumnHeaders_Returns_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 1;
            }
        }).when(rawPattern).getCurrentColumnHeaders(anyObject());

        Table pattern = new Table(rawPattern);

        List<AutomationElement> elements = pattern.getCurrentColumnHeaders();
    }

    @Test(expected= AutomationException.class)
    public void test_GetText_Throws_Exception_When_getCurrentRowHeaders_Returns_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 1;
            }
        }).when(rawPattern).getCurrentRowHeaders(anyObject());

        Table pattern = new Table(rawPattern);

        List<AutomationElement> elements = pattern.getCurrentRowHeaders();
    }
}
