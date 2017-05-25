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
import mmarquee.automation.AutomationException;
import mmarquee.automation.pattern.Toggle;
import mmarquee.automation.uiautomation.ToggleState;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 30/11/2016.
 */
public class AutomationCheckBoxTest {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testName_Gets_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Toggle pattern = Mockito.mock(Toggle.class);

        when(element.getName()).thenReturn("NAME");

        AutomationCheckbox checkbox = new AutomationCheckbox(element, pattern);

        String name = checkbox.name();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void test_getToggleState_Gets_Value_From_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Toggle pattern = Mockito.mock(Toggle.class);

        when(pattern.currentToggleState()).thenReturn(ToggleState.On);

        AutomationCheckbox checkbox = new AutomationCheckbox(element, pattern);

        ToggleState state = checkbox.getToggleState();

        assertTrue(state == ToggleState.On);
    }

    @Test
    public void testToggle() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Toggle pattern = Mockito.mock(Toggle.class);

        AutomationCheckbox checkbox = new AutomationCheckbox(element, pattern);

        checkbox.toggle();
    }
}
