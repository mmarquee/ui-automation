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
package mmarquee.automation.controls;

import mmarquee.automation.*;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.IUIAutomation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Mark Humphreys
 * Date 28/11/2016.
 */
public class AutomationButtonTest {

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetName_For_Button() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);

        UIAutomation instance = new UIAutomation(mocked_automation);

        Invoke pattern = Mockito.mock(Invoke.class);

        when(element.getName()).thenReturn("NAME");

        AutomationButton button = new AutomationButton(
                new ElementBuilder(element).addPattern(pattern).automation(instance));

        String name = button.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testSetFocus_Calls_setFocus_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Invoke pattern = Mockito.mock(Invoke.class);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);

        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationButton button = new AutomationButton(
                new ElementBuilder(element).addPattern(pattern).automation(instance));

        button.focus();

        verify(element, times(1)).setFocus();
    }

    @Test
    public void testClick_Calls_Invoke_Once_From_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Invoke pattern = Mockito.mock(Invoke.class);
        when(pattern.isAvailable()).thenReturn(true);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);

        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationButton button = new AutomationButton(
                new ElementBuilder(element).addPattern(pattern).automation(instance));

        button.click();

        verify(pattern, times(1)).invoke();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testClick_Calls_Throws_PatternNotFoundException_When_Pattern_Not_Available() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Invoke pattern = Mockito.mock(Invoke.class);
        when(pattern.isAvailable()).thenReturn(false);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);

        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationButton button = new AutomationButton(
                new ElementBuilder(element).addPattern(pattern).automation(instance));

        button.click();

        verify(pattern, times(0)).invoke();
    }
}
