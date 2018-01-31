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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.AutomationReBar;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.uiautomation.IUIAutomation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Mark Humphreys
 * Date 31/05/2017.
 *
 * Basic tests for ReBar.
 */
public class AutomationReBarTests {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName_Is_Returned_From_The_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        when(element.getClassName()).thenReturn(AutomationReBar.CLASS_NAME);
        when(element.getName()).thenReturn("REBAR-01");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        ItemContainer container = Mockito.mock(ItemContainer.class);

        AutomationReBar pane = new AutomationReBar(
                new ElementBuilder(element).addPattern(container).automation(instance));

        String name = pane.getName();

        assertEquals("REBAR-01", name);
    }

    @Test
    public void testName_Is_Returned_From_The_Element_Alternative_Constructor() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        when(element.getClassName()).thenReturn(AutomationReBar.CLASS_NAME);
        when(element.getName()).thenReturn("REBAR-01");

        ItemContainer container = Mockito.mock(ItemContainer.class);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationReBar pane = new AutomationReBar(
                new ElementBuilder(element).addPattern(container).automation(instance));

        String name = pane.getName();

        assertEquals("REBAR-01", name);
    }
}
