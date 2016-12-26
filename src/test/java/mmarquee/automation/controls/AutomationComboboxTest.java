/*
 * Copyright 2016 inpwtepydjuf@gmail.com
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
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Value;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 29/11/2016.
 */
public class AutomationComboboxTest extends BaseAutomationTest {

    protected Logger logger = Logger.getLogger(AutomationComboboxTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testGetCombobox_Get_Name() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Value value = Mockito.mock(Value.class);

        when(element.getName()).thenReturn("NAME");

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value);

        String name = combo.name();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetCombobox_Get_Text() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Value value = Mockito.mock(Value.class);

        when(value.value()).thenReturn("NAME");

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value);

        String name = combo.text();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetList_Gets_Correct_Size_Of_List_When_Empty()
            throws AutomationException, PatternNotFoundException {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Value value = Mockito.mock(Value.class);

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        when(elem.findAll(anyObject(), anyObject(), anyObject())).thenReturn(-1);

        element.element = elem;

        AutomationComboBox cb1 = new AutomationComboBox(element, collapse, value);

        List<AutomationListItem> elements = cb1.getList();

        assertTrue(elements.size() == 0);
    }

    @Test
    public void testGetList_Gets_Correct_Size_Of_List() throws Exception {

        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationComboBox cb1 = window.getCombobox(0);

            List<AutomationListItem> elements = cb1.getList();

            int count = elements.size();

            logger.info(count);

            assertTrue(count == 0);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetCombobox_Get_List_Gets_Correct_Size_Of_List_When_Expanded() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationComboBox cb1 = window.getCombobox(0);

            cb1.expand();

            List<AutomationListItem> elements = cb1.getList();

            int count = elements.size();

            logger.info(count);

            assertTrue(count == 3);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetCombobox_GetExpanded_False_When_Expanded() throws Exception {

        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationComboBox cb1 = window.getCombobox(0);

            assertFalse(cb1.isExpanded());
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetCombobox_GetExpanded_True_When_Expanded() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationComboBox cb1 = window.getCombobox(0);

            cb1.expand();

            assertTrue(cb1.isExpanded());
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetCombobox_GetExpanded_False_When_Expanded_Then_Collapsed() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationComboBox cb1 = window.getCombobox(0);

            cb1.expand();
            this.andRest();
            cb1.collapse();

            assertFalse(cb1.isExpanded());
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testSetText_Calls_setValue_From_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Value value = Mockito.mock(Value.class);

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value);

        combo.setText("Test");

        verify(value, times(1)).setValue("Test");
    }
}
