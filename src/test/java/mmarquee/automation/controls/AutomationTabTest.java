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

import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.*;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.SelectionItem;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 29/11/2016.
 */
public class AutomationTabTest {

    @Spy
    private Unknown mockUnknown;

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    @Ignore("Need to get the correct mocking setup")
    public void testGetTabPage_By_Name_Succeeds_When_Tab_Present() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        // For .findAll
        ArrayList<AutomationElement> list = new ArrayList<>();
        AutomationElement testElem = Mockito.mock(AutomationElement.class);

        when(testElem.getName()).thenReturn("TEST");
        when(testElem.getControlType()).thenReturn(ControlType.TabItem.getValue());
        when(testElem.getPropertyValue(PropertyID.IsSelectionItemPatternAvailable.getValue())).thenReturn(1);


        // Need to get the selectItem pattern to work correctly
        SelectionItem selectItem = Mockito.mock(SelectionItem.class);

        doReturn(mockUnknown)
                .when(selectItem)
                .makeUnknown(anyObject());

        // Needs to get the selection item that we've mocked

        PointerByReference pbr = new PointerByReference();

        selectItem.convertPointerToInterface(pbr);

        when(testElem.getPattern(anyInt())).thenReturn(pbr);

//        when(testElem.getSelectItemPattern()).thenReturn(selectItem);

        list.add(testElem);

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationTab ctrl = new AutomationTab(element, container);

        ctrl.selectTabPage("TEST");

//        String name = ctrl.getName();

 //       assertTrue(name.equals("TEST"));
    }

    @Test(expected = ElementNotFoundException.class)
    @Ignore("Need to get the correct mocking setup")
    public void testGetTabPage_By_Name_Throws_Exception_When_Tab_Not_Present() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        ArrayList<AutomationElement> list = new ArrayList<>();
        AutomationElement testElem = Mockito.mock(AutomationElement.class);

        when(testElem.getName()).thenReturn("TEST");
        when(testElem.getControlType()).thenReturn(ControlType.TabItem.getValue());
        when(testElem.getPropertyValue(PropertyID.IsSelectionItemPatternAvailable.getValue())).thenReturn(1);

        // Need to get the selectItem pattern to work correctly
        SelectionItem selectItem = Mockito.mock(SelectionItem.class);

        PointerByReference pbr = new PointerByReference();
        //  IUnknown unknown = new IUnknown(selectItem);

        //  pbr.setValue(selectItem.asPointer());
        selectItem.getRawPatternPointer(pbr);

        when(testElem.getPattern(anyInt())).thenReturn(pbr);
        list.add(testElem);

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationTab ctrl = new AutomationTab(element, container);

        ctrl.selectTabPage("TEST");

        String name = ctrl.getName();

        assertTrue(name.equals("TEST"));
    }

    @Test
    @Ignore("Needs proper mocking setup")
    public void testSelectTabPage_Not_Present() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        ArrayList<AutomationElement> list = new ArrayList<>();
        AutomationElement testElem = Mockito.mock(AutomationElement.class);

        when(testElem.getName()).thenReturn("TEST");
        when(testElem.getControlType()).thenReturn(ControlType.TabItem.getValue());
        when(testElem.getPropertyValue(PropertyID.IsSelectionItemPatternAvailable.getValue())).thenReturn(1);

        // Need to get the selectItem pattern to work correctly
        SelectionItem selectItem = Mockito.mock(SelectionItem.class);

        PointerByReference pbr = new PointerByReference();
        //  IUnknown unknown = new IUnknown(selectItem);

        //  pbr.setValue(selectItem.asPointer());
        selectItem.getRawPatternPointer(pbr);

        when(testElem.getPattern(anyInt())).thenReturn(pbr);
        list.add(testElem);

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationTab ctrl = new AutomationTab(element, container);

        ctrl.selectTabPage("TEST");

        String name = ctrl.getName();

        assertTrue(name.equals("TEST"));
    }
}
