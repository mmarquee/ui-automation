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
package mmarquee.automation.controls.menu;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.controls.AutomationToolbarButtonTest;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.Invoke;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 04/12/2016.
 *
 * Tests for menu item functionality
 */
public class AutomationMenuItemTest extends BaseAutomationTest {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetName() throws Exception {
        AutomationElement mocked_element =
                Mockito.mock(AutomationElement.class);

        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Invoke invoke = Mockito.mock(Invoke.class);

        when(mocked_element.getName()).thenReturn("NAME");

        AutomationMenuItem item =
                new AutomationMenuItem(mocked_element, collapse, invoke);

        assertEquals("NAME", item.name());
    }

    @Test
    public void testIsExpanded_Is_False_When_Not_Expanded() throws Exception {
        AutomationElement mocked_element =
                Mockito.mock(AutomationElement.class);

        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Invoke invoke = Mockito.mock(Invoke.class);

        when(collapse.isExpanded()).thenReturn(false);

        AutomationMenuItem item =
                new AutomationMenuItem(mocked_element, collapse, invoke);

        assertFalse(item.isExpanded());
    }

    @Test
    public void testIsExpanded_Is_True_When_Expanded() throws Exception {
        AutomationElement mocked_element =
                Mockito.mock(AutomationElement.class);

        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Invoke invoke = Mockito.mock(Invoke.class);

        when(collapse.isExpanded()).thenReturn(true);

        AutomationMenuItem item =
                new AutomationMenuItem(mocked_element, collapse, invoke);

        assertTrue(item.isExpanded());
    }

    @Test
    public void testClick() throws Exception {
        AutomationElement mocked_element =
                Mockito.mock(AutomationElement.class);

        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Invoke invoke = Mockito.mock(Invoke.class);

        when(collapse.isExpanded()).thenReturn(true);

        AutomationMenuItem item =
                new AutomationMenuItem(mocked_element, collapse, invoke);

        item.click();

        verify(invoke, atLeastOnce()).invoke();
    }
}
