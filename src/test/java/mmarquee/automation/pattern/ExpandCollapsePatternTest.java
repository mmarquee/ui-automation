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
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationExpandCollapsePattern;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * Created by Mark Humphreys on 11/01/2017.
 *
 * Mocked tests for the ExpandCollapse pattern
 */
public class ExpandCollapsePatternTest {

    @Mock
    IUIAutomationExpandCollapsePattern rawPattern;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCollapse_Calls_Collapse_From_Pattern() throws Exception {
        ExpandCollapse pattern = new ExpandCollapse(rawPattern);

        pattern.expand();

        verify(rawPattern, atLeastOnce()).expand();
    }

    @Test
    public void testExpand_Calls_Expand_From_Pattern() throws Exception {
        ExpandCollapse pattern = new ExpandCollapse(rawPattern);

        pattern.collapse();

        verify(rawPattern, atLeastOnce()).collapse();
    }

    @Test
    public void testIsExpanded_Returns_True_When_COM_Returns_One() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(1);

                return 0;
            }
        }).when(rawPattern).getCurrentExpandCollapseState(any());

        ExpandCollapse pattern = new ExpandCollapse(rawPattern);

        boolean selected = pattern.isExpanded();

        assertTrue(selected);
    }

    @Test(expected= AutomationException.class)
    public void testIsExpanded_Throws_Exception_When_COM_Returns_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(0);

                return 1;
            }
        }).when(rawPattern).getCurrentExpandCollapseState(any());

        ExpandCollapse pattern = new ExpandCollapse(rawPattern);

        boolean selected = pattern.isExpanded();

        assertFalse(selected);
    }

    @Test
    public void testIsExpanded_Returns_False_When_COM_Returns_One() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(0);

                return 0;
            }
        }).when(rawPattern).getCurrentExpandCollapseState(any());

        ExpandCollapse pattern = new ExpandCollapse(rawPattern);

        boolean selected = pattern.isExpanded();

        assertFalse(selected);
    }

    @Test(expected= AutomationException.class)
    public void testCollapse_Throws_Exception_When_COM_Returns_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return 1;
            }
        }).when(rawPattern).collapse();

        ExpandCollapse pattern = new ExpandCollapse(rawPattern);

        pattern.collapse();
    }

    @Test(expected= AutomationException.class)
    public void testExpand_Throws_Exception_When_COM_Returns_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                return 1;
            }
        }).when(rawPattern).expand();

        ExpandCollapse pattern = new ExpandCollapse(rawPattern);

        pattern.expand();
    }
}
