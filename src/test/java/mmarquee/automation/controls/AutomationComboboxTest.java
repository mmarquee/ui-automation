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

import com.sun.jna.ptr.IntByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Value;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

/**
 * Created by Mark Humphreys on 29/11/2016.
 *
 * Tests for Combobox functionality
 */
public class AutomationComboboxTest {
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCombobox_Get_Name() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Value value = Mockito.mock(Value.class);

        when(element.getName()).thenReturn("NAME");

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value);

        String name = combo.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetCombobox_Get_Text() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Value value = Mockito.mock(Value.class);

        when(value.value()).thenReturn("NAME");

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value);

        String name = combo.getValue();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetList_Gets_Correct_Size_Of_List_When_Empty()
            throws AutomationException, PatternNotFoundException {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Value value = Mockito.mock(Value.class);

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        when(elem.findAll(anyObject(), anyObject(), anyObject())).thenReturn(-1);

        element.element = elem;

        AutomationComboBox cb1 = new AutomationComboBox(element, collapse, value);

        List<AutomationListItem> elements = cb1.getList();

        assertTrue(elements.size() == 0);
    }

    @Test
    public void testGetList_Gets_Correct_Size_Of_List_When_findAll_Returns_No_Entries()
            throws AutomationException, PatternNotFoundException {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Value value = Mockito.mock(Value.class);

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        when(elem.findAll(anyObject(), anyObject(), anyObject())).thenReturn(0);

        element.element = elem;

        AutomationComboBox cb1 = new AutomationComboBox(element, collapse, value);

        List<AutomationListItem> elements = cb1.getList();

        assertTrue(elements.size() == 0);
    }

    @Test
    public void testGetCombobox_GetExpanded_Gets_Result_From_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Value value = Mockito.mock(Value.class);

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        when(collapse.isExpanded()).thenReturn(true);

        element.element = elem;

        AutomationComboBox cb1 = new AutomationComboBox(element, collapse, value);

        boolean result = cb1.isExpanded();

        assertTrue(result);
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

    @Test
    public void testExpand_Calls_Expand_From_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Value value = Mockito.mock(Value.class);

        when(element.getName()).thenReturn("NAME");

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value);

        combo.expand();

        verify(collapse, atLeast(1)).expand();
    }

    @Test
    public void testExpand_Calls_Collapse_From_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Value value = Mockito.mock(Value.class);

        when(element.getName()).thenReturn("NAME");

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value);

        combo.collapse();

        verify(collapse, atLeast(1)).collapse();
    }

    @Test
    public void test_text_Calls_Value_From_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Value value = Mockito.mock(Value.class);

        when(element.getName()).thenReturn("NAME");

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value);

        combo.getValue();

        verify(value, atLeast(1)).value();
    }

    @Test
    public void test_GetList_Calls_Value_From_Pattern() throws Exception {

        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        Mockito.when(elem.getCurrentControlType(anyObject())).thenAnswer(
                invocation -> {
                    Object[] args = invocation.getArguments();
                    IntByReference reference = (IntByReference)args[0];

                    reference.setValue(50007);

                    return 0;
                });

        list.add(new AutomationElement(elem));

        AutomationElement element = Mockito.mock(AutomationElement.class);
        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Value value = Mockito.mock(Value.class);

        when(element.getName()).thenReturn("NAME");
        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value);

        combo.getList();

        verify(element, atLeast(1)).findAll(anyObject(), anyObject());
    }
}
