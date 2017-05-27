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
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 01/12/2016.
 *
 * Tests for AutomationList
 */
public class AutomationListTest {

    @Test
    public void testName_Gets_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Selection selection = Mockito.mock(Selection.class);

        when(element.getName()).thenReturn("NAME");

        AutomationList list = new AutomationList(element, selection);

        String name = list.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetItems_By_Index_Throws_Exception_When_Not_Found() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Selection selection = Mockito.mock(Selection.class);

        IUIAutomationElement3 listElement = Mockito.mock(IUIAutomationElement3.class);

        List<AutomationElement> result = new ArrayList<>();
        result.add(new AutomationElement(listElement));

        when(element.findAll(anyObject(), anyObject())).thenReturn(result);

        AutomationList list = new AutomationList(element, selection);

        list.getItem(1);
    }

    @Test
    public void testGetItems_By_Index_Mocked() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Selection selection = Mockito.mock(Selection.class);

        IUIAutomationElement3 listElement = Mockito.mock(IUIAutomationElement3.class);

        List<AutomationElement> result = new ArrayList<>();
        result.add(new AutomationElement(listElement));

        when(element.findAll(anyObject(), anyObject())).thenReturn(result);

        AutomationList list = new AutomationList(element, selection);

        AutomationListItem item = list.getItem(0);

        assertTrue(item != null);
    }

    @Test
    public void testGetItems() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Selection selection = Mockito.mock(Selection.class);

        IUIAutomationElement3 listElement = Mockito.mock(IUIAutomationElement3.class);

        List<AutomationElement> result = new ArrayList<>();
        result.add(new AutomationElement(listElement));

        when(element.findAll(anyObject(), anyObject())).thenReturn(result);

        AutomationList list = new AutomationList(element, selection);

        List<AutomationListItem> items = list.getItems();

        assertTrue(items.size() == 1);
    }
}
