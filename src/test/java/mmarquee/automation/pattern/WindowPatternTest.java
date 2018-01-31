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
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.uiautomation.IUIAutomationWindowPattern;
import mmarquee.automation.uiautomation.WindowVisualState;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * @author Mark Humphreys
 * Date 12/01/2017.
 */
public class WindowPatternTest {
    @Mock
    AutomationElement element;
    
    @Mock
    IUIAutomationWindowPattern rawPattern;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        BaseAutomationTest.declarePatternAvailable(element, 
        		PatternID.Window, PropertyID.IsWindowPatternAvailable);
    }

    @Test
    public void test_isTopMost_Calls_getCurrentIsTopmost_From_Pattern() throws Exception {
        Window pattern = new Window(element);
        pattern.rawPattern = rawPattern;

        boolean value = pattern.isTopMost();

        verify(rawPattern, atLeastOnce()).getCurrentIsTopmost(any());
    }

    @Test
    public void test_isModal_Calls_getCurrentIsModal_From_Pattern() throws Exception {
        Window pattern = new Window(element);
        pattern.rawPattern = rawPattern;

        boolean value = pattern.isModal();

        verify(rawPattern, atLeastOnce()).getCurrentIsModal(any());
    }

    @Test(expected= AutomationException.class)
    public void test_isTopMost_Throws_Exception_When_Error() throws Exception {

        doAnswer(invocation -> 1).when(rawPattern).getCurrentIsTopmost(any());

        Window pattern = new Window(element);
        pattern.rawPattern = rawPattern;

        boolean value = pattern.isTopMost();

        verify(rawPattern, atLeastOnce()).getCurrentIsTopmost(any());
    }

    @Test(expected= AutomationException.class)
    public void test_isModal_Throws_Exception_When_Error() throws Exception {
        doAnswer(invocation -> 1).when(rawPattern).getCurrentIsModal(any());

        Window pattern = new Window(element);
        pattern.rawPattern = rawPattern;

        boolean value = pattern.isModal();

        verify(rawPattern, atLeastOnce()).getCurrentIsModal(any());
    }

    @Test
    public void test_Close_Calls_Close_From_Pattern() throws Exception {
        Window pattern = new Window(element);
        pattern.rawPattern = rawPattern;

        pattern.close();

        verify(rawPattern, atLeastOnce()).close();
    }

    @Test(expected=AutomationException.class)
    public void test_Close_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(invocation -> 1).when(rawPattern).close();

        Window pattern = new Window(element);
        pattern.rawPattern = rawPattern;

        pattern.close();

        verify(rawPattern, atLeastOnce()).close();
    }

    @Test
    public void test_Maximize_Calls_SetWindowState_From_Pattern() throws Exception {
        Window pattern = new Window(element);
        pattern.rawPattern = rawPattern;

        pattern.maximize();

        verify(rawPattern, atLeastOnce()).setWindowVisualState(1);
    }

    @Test
    public void test_Minimize_Calls_SetWindowState_From_Pattern() throws Exception {
        Window pattern = new Window(element);
        pattern.rawPattern = rawPattern;

        pattern.minimize();

        verify(rawPattern, atLeastOnce()).setWindowVisualState(2);
    }

    @Test(expected=AutomationException.class)
    public void test_SetWindowVisualState_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(invocation -> 1).when(rawPattern).setWindowVisualState(any());

        Window pattern = new Window(element);
        pattern.rawPattern = rawPattern;

        pattern.setWindowState(WindowVisualState.Maximized);

        verify(rawPattern, atLeastOnce()).setWindowVisualState(1);
    }

    @Test(expected=AutomationException.class)
    public void test_waitForInputIdle_Throws_Exception_When_Pattern_Returns_Error() throws Exception {

        doAnswer(invocation -> 1).when(rawPattern).waitForInputIdle(any(Integer.class), any(IntByReference.class));

        Window pattern = new Window(element);
        pattern.rawPattern = rawPattern;

        pattern.waitForInputIdle(100);

        verify(rawPattern, atLeastOnce()).setWindowVisualState(1);
    }

    @Test
    public void test_waitForInputIdle_Calls_waitForInputIdle_From_Pattern() throws Exception {

        Window pattern = new Window(element);
        pattern.rawPattern = rawPattern;

        pattern.waitForInputIdle(100);

        verify(rawPattern, atLeastOnce()).waitForInputIdle(any(Integer.class), any(IntByReference.class));
    }
}
