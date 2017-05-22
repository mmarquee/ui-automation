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

import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationWindowPattern;
import mmarquee.automation.uiautomation.WindowVisualState;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * Created by Mark Humphreys on 12/01/2017.
 */
public class WindowPatternTest {
    @Mock
    IUIAutomationWindowPattern rawPattern;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_isTopMost_Calls_getCurrentIsTopmost_From_Pattern() throws Exception {
        Window pattern = new Window(rawPattern);

        boolean value = pattern.isTopMost();

        verify(rawPattern, atLeastOnce()).getCurrentIsTopmost(anyObject());
    }

    @Test
    public void test_isModal_Calls_getCurrentIsModal_From_Pattern() throws Exception {
        Window pattern = new Window(rawPattern);

        boolean value = pattern.isModal();

        verify(rawPattern, atLeastOnce()).getCurrentIsModal(anyObject());
    }

    @Test(expected= AutomationException.class)
    public void test_isTopMost_Throws_Exception_When_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 1;
            }
        }).when(rawPattern).getCurrentIsTopmost(anyObject());

        Window pattern = new Window(rawPattern);

        boolean value = pattern.isTopMost();

        verify(rawPattern, atLeastOnce()).getCurrentIsTopmost(anyObject());
    }

    @Test(expected= AutomationException.class)
    public void test_isModal_Throws_Exception_When_Error() throws Exception {
        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 1;
            }
        }).when(rawPattern).getCurrentIsModal(anyObject());

        Window pattern = new Window(rawPattern);

        boolean value = pattern.isModal();

        verify(rawPattern, atLeastOnce()).getCurrentIsModal(anyObject());
    }

    @Test
    public void test_Close_Calls_Close_From_Pattern() throws Exception {
        Window pattern = new Window(rawPattern);

        pattern.close();

        verify(rawPattern, atLeastOnce()).close();
    }

    @Test(expected=AutomationException.class)
    public void test_Close_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 1;
            }
        }).when(rawPattern).close();

        Window pattern = new Window(rawPattern);

        pattern.close();

        verify(rawPattern, atLeastOnce()).close();
    }

    @Test
    public void test_Maximize_Calls_SetWindowState_From_Pattern() throws Exception {
        Window pattern = new Window(rawPattern);

        pattern.maximize();

        verify(rawPattern, atLeastOnce()).setWindowVisualState(1);
    }

    @Test
    public void test_Minimize_Calls_SetWindowState_From_Pattern() throws Exception {
        Window pattern = new Window(rawPattern);

        pattern.minimize();

        verify(rawPattern, atLeastOnce()).setWindowVisualState(2);
    }

    @Test(expected=AutomationException.class)
    public void test_SetWindowVisualState_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 1;
            }
        }).when(rawPattern).setWindowVisualState(anyObject());

        Window pattern = new Window(rawPattern);

        pattern.setWindowState(WindowVisualState.Maximized);

        verify(rawPattern, atLeastOnce()).setWindowVisualState(1);
    }

    @Test(expected=AutomationException.class)
    public void test_waitForInputIdle_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                return 1;
            }
        }).when(rawPattern).waitForInputIdle(anyInt(), anyObject());

        Window pattern = new Window(rawPattern);

        pattern.waitForInputIdle(100);

        verify(rawPattern, atLeastOnce()).setWindowVisualState(1);
    }

    @Test
    public void test_waitForInputIdle_Calls_waitForInputIdle_From_Pattern() throws Exception {

        Window pattern = new Window(rawPattern);

        pattern.waitForInputIdle(100);

        verify(rawPattern, atLeastOnce()).waitForInputIdle(anyInt(), anyObject());
    }
}
