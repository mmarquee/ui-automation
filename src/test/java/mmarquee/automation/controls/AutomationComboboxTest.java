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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sun.jna.ptr.IntByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.pattern.Value;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * Created by Mark Humphreys on 29/11/2016.
 *
 * Tests for Combobox functionality
 */
public class AutomationComboboxTest {
	@Mock AutomationElement element;
	@Mock AutomationElement targetElement;
	@Mock ExpandCollapse collapse;
	@Mock Value value;
	@Mock Selection selection;
	@Mock IUIAutomationElement3 elem;
	
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCombobox_Get_Name() throws Exception {

        when(element.getName()).thenReturn("NAME");

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        String name = combo.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetCombobox_Get_Text() throws Exception {
        when(value.value()).thenReturn("NAME");

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        String name = combo.getValue();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetList_Gets_Correct_Size_Of_List_When_Empty()
            throws AutomationException, PatternNotFoundException {
        when(elem.findAll(any(), any(), any())).thenReturn(-1);

        element.element = elem;

        AutomationComboBox cb1 = new AutomationComboBox(element, collapse, value, selection);

        List<AutomationListItem> elements = cb1.getList();

        assertTrue(elements.size() == 0);
    }

    @Test
    public void testGetList_Gets_Correct_Size_Of_List_When_findAll_Returns_No_Entries()
            throws AutomationException, PatternNotFoundException {
        when(elem.findAll(any(), any(), any())).thenReturn(0);

        element.element = elem;

        AutomationComboBox cb1 = new AutomationComboBox(element, collapse, value, selection);

        List<AutomationListItem> elements = cb1.getList();

        assertTrue(elements.size() == 0);
    }

    @Test
    public void testGetCombobox_GetExpanded_Gets_Result_From_Pattern() throws Exception {
        when(collapse.isExpanded()).thenReturn(true);

        element.element = elem;

        AutomationComboBox cb1 = new AutomationComboBox(element, collapse, value, selection);

        boolean result = cb1.isExpanded();

        assertTrue(result);
    }

    @Test
    public void testSetText_Calls_setValue_From_Pattern() throws Exception {
        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        combo.setText("Test");

        verify(value, times(1)).setValue("Test");
    }

    @Test
    public void testExpand_Calls_Expand_From_Pattern() throws Exception {
        when(element.getName()).thenReturn("NAME");

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        combo.expand();

        verify(collapse, atLeast(1)).expand();
    }

    @Test
    public void testExpand_Calls_Collapse_From_Pattern() throws Exception {
        when(element.getName()).thenReturn("NAME");

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        combo.collapse();

        verify(collapse, atLeast(1)).collapse();
    }

    @Test
    public void test_text_Calls_Value_From_Pattern() throws Exception {
        when(element.getName()).thenReturn("NAME");

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        combo.getValue();

        verify(value, atLeast(1)).value();
    }

    @Test
    @Deprecated
    public void test_GetList_Calls_Value_From_Pattern() throws Exception {

        List<AutomationElement> list = new ArrayList<>();

        Mockito.when(elem.getCurrentControlType(any())).thenAnswer(
                invocation -> {
                    Object[] args = invocation.getArguments();
                    IntByReference reference = (IntByReference)args[0];

                    reference.setValue(50007);

                    return 0;
                });

        list.add(new AutomationElement(elem));

        when(element.getName()).thenReturn("NAME");
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        combo.getList();

        verify(element, atLeast(1)).findAll(any(), any());
    }

    @Deprecated
    @Test
    public void test_GetList_Returns_Items_When_List_Not_Empty() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        list.add(targetElement);

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        List<AutomationListItem> items = combo.getList();

        assertEquals(list.size(), items.size());
        assertEquals(targetElement, items.get(0).getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Deprecated
    @Test
    public void test_GetList_Returns_No_Items_When_List_Empty() throws Exception {
        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        List<AutomationListItem> items = combo.getList();

        assertEquals(0, items.size());
    }
    

    @Test
    public void test_GetItems_Calls_Value_From_Pattern() throws Exception {

        List<AutomationElement> list = new ArrayList<>();

        Mockito.when(elem.getCurrentControlType(any())).thenAnswer(
                invocation -> {
                    Object[] args = invocation.getArguments();
                    IntByReference reference = (IntByReference)args[0];

                    reference.setValue(50007);

                    return 0;
                });

        list.add(new AutomationElement(elem));

        when(element.getName()).thenReturn("NAME");
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        combo.getItems();

        verify(element, atLeast(1)).findAll(any(), any());
    }

    @Test
    public void test_GetItems_Returns_Items_When_List_Not_Empty() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        list.add(targetElement);

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        List<AutomationListItem> items = combo.getItems();

        assertEquals(list.size(), items.size());
        assertEquals(targetElement, items.get(0).getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetItems_Returns_No_Items_When_List_Empty() throws Exception {
        when(collapse.isExpanded()).thenReturn(true);

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        List<AutomationListItem> items = combo.getItems();

        assertEquals(0, items.size());
    }
    
    @Test
    public void test_GetItem_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        list.add(targetElement);
        
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(list);

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        AutomationListItem item = combo.getItem(0);
        assertEquals(targetElement,item.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }
    
    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetItem_By_Index_Throws_Exception_When_Not_found() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(list);

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        combo.getItem(99);
    }

    @Test
    public void test_GetItem_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        AutomationListItem item = combo.getItem("myName");
        assertEquals(targetElement,item.getElement());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetItem_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        combo.getItem("unknownName");
    }

    @Test
    public void test_GetItem_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        AutomationListItem item = combo.getItemByAutomationId("myID");
        assertEquals(targetElement,item.getElement());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }
    
    @Test(expected=ElementNotFoundException.class)
    public void test_GetItem_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

       combo.getItemByAutomationId("unknownID");
    }

    @Test
    public void test_GetSelectedItems_Returns_Items_When_List_Not_Empty() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        list.add(targetElement);

        when(selection.getCurrentSelection()).thenReturn(list);

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        List<AutomationListItem> items = combo.getSelectedItems();

        assertEquals(list.size(), items.size());
        assertEquals(targetElement, items.get(0).getElement());
    }

    @Test
    public void test_GetSelectedItems_Returns_No_Items_When_List_Empty() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        when(selection.getCurrentSelection()).thenReturn(list);

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        List<AutomationListItem> items = combo.getSelectedItems();

        assertEquals(0, items.size());
    }

    @Test
    public void test_GetSelectedItem_Returns_Item_When_List_Not_Empty() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        list.add(targetElement);

        when(selection.getCurrentSelection()).thenReturn(list);

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        AutomationListItem item = combo.getSelectedItem();

        assertEquals(targetElement, item.getElement());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSelectedItem_ThrowsException_When_List_Empty() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        when(selection.getCurrentSelection()).thenReturn(list);

        AutomationComboBox combo = new AutomationComboBox(element, collapse, value, selection);

        combo.getSelectedItem();
    }
}
