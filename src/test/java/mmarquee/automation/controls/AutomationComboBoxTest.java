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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import mmarquee.automation.*;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.pattern.Value;
/**
 * @author Mark Humphreys
 * Date 29/11/2016.
 *
 * Tests for Combobox functionality
 */
public class AutomationComboBoxTest {
	@Mock AutomationElement element;
	@Mock AutomationElement targetElement;
	@Mock ExpandCollapse collapse;
	@Mock Value value;
	@Mock Selection selection;
	@Mock
    IUIAutomationElement elem;
	
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetComboBox_Get_Name() throws Exception {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);

        UIAutomation instance = new UIAutomation(mocked_automation);

        when(element.getName()).thenReturn("NAME");

        AutomationComboBox combo = new AutomationComboBox(
                new ElementBuilder(element).addPattern(collapse).addPattern(value).addPattern(selection).automation(instance));

        String name = combo.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetComboBox_Get_Text() throws Exception {
        when(value.isAvailable()).thenReturn(true);
        when(value.value()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationComboBox combo = new AutomationComboBox(
                new ElementBuilder(element).addPattern(collapse).addPattern(value).addPattern(selection).automation(instance));

        String name = combo.getValue();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetCombobox_GetExpanded_Gets_Result_From_Pattern() throws Exception {
        when(collapse.isAvailable()).thenReturn(true);
        when(collapse.isExpanded()).thenReturn(true);

        element.setElement(elem);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationComboBox combo = new AutomationComboBox(
                new ElementBuilder(element).addPattern(collapse).addPattern(value).addPattern(selection).automation(instance));

        boolean result = combo.isExpanded();

        assertTrue(result);
    }

    @Test
    public void testSetText_Calls_setValue_From_Pattern() throws Exception {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);
        
        when(value.isAvailable()).thenReturn(true);

        AutomationComboBox combo = new AutomationComboBox(
                new ElementBuilder(element).addPattern(collapse).addPattern(value).addPattern(selection).automation(instance));

        combo.setText("Test");

        verify(value, times(1)).setValue("Test");
    }

    @Test
    public void testExpand_Calls_Expand_From_Pattern() throws Exception {
        when(collapse.isAvailable()).thenReturn(true);
        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationComboBox combo = new AutomationComboBox(
                new ElementBuilder(element).addPattern(collapse).addPattern(value).addPattern(selection).automation(instance));

        combo.expand();

        verify(collapse, atLeast(1)).expand();
    }

    @Test
    public void testExpand_Calls_Collapse_From_Pattern() throws Exception {
        when(collapse.isAvailable()).thenReturn(true);
        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationComboBox combo = new AutomationComboBox(
                new ElementBuilder(element).addPattern(collapse).addPattern(value).addPattern(selection).automation(instance));

        combo.collapse();

        verify(collapse, atLeast(1)).collapse();
    }

    @Test
    public void test_text_Calls_Value_From_Pattern() throws Exception {
        when(value.isAvailable()).thenReturn(true);
        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationComboBox combo = new AutomationComboBox(
                new ElementBuilder(element).addPattern(collapse).addPattern(value).addPattern(selection).automation(instance));

        combo.getValue();

        verify(value, atLeast(1)).value();
    }

    @Test
    public void test_GetSelectedItems_Returns_No_Items_When_List_Empty() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        when(selection.getCurrentSelection()).thenReturn(list);
        when(selection.isAvailable()).thenReturn(true);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationComboBox combo = new AutomationComboBox(
                new ElementBuilder(element).addPattern(collapse).addPattern(value).addPattern(selection).automation(instance));

        List<AutomationListItem> items = combo.getSelectedItems();

        assertEquals(0, items.size());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSelectedItem_ThrowsException_When_List_Empty() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        when(selection.getCurrentSelection()).thenReturn(list);
        when(selection.isAvailable()).thenReturn(true);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationComboBox combo = new AutomationComboBox(
                new ElementBuilder(element).addPattern(collapse).addPattern(value).addPattern(selection).automation(instance));

        combo.getSelectedItem();
    }
}
