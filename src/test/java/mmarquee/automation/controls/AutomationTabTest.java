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
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.SelectionItem;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 29/11/2016.
 */
public class AutomationTabTest {

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    @Ignore("Still needs better tests")
    public void testGetTabPage_By_Name_Succeeds_When_Tab_Present() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        List<AutomationElement> list = new ArrayList<>();

        AutomationElement testElem = Mockito.mock(AutomationElement.class);
        when(testElem.getName()).thenReturn("TEST");
        when(testElem.getControlType()).thenReturn(ControlType.TabItem.getValue());

        doNothing().when(testElem).setFocus();

        list.add(testElem);

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationTab ctrl = new AutomationTab(element, container);

        ctrl.selectTabPage("TEST");
    }

    @Test(expected = ElementNotFoundException.class)
    public void testGetTabPage_By_Name_Throws_Exception_When_Tab_Not_Present() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        SelectionItem selectionItem = Mockito.mock(SelectionItem.class);
        doNothing().when(selectionItem).select();

        List<AutomationElement> list = new ArrayList<>();

        AutomationElement testElem = Mockito.mock(AutomationElement.class);
        when(testElem.getName()).thenReturn("TEST-01");
        when(testElem.getControlType()).thenReturn(ControlType.TabItem.getValue());
//        when(testElem.getSelectItemPattern()).thenReturn(selectionItem);

        list.add(testElem);

        when(element.findAll(any(), any())).thenReturn(list);

        ItemContainer container = Mockito.mock(ItemContainer.class);
        AutomationTab ctrl = new AutomationTab(element, container);

        ctrl.selectTabPage("TEST");
    }
}
