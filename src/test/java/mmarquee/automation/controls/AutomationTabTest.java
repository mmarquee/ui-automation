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

import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.*;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.SelectionItem;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 29/11/2016.
 */
public class AutomationTabTest extends BaseAutomationTest {

    protected Logger logger = Logger.getLogger(AutomationTabTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    @Ignore("Need to get the correct mocking setup")
    public void testGetTabPage_By_Index() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        ArrayList<AutomationElement> list = new ArrayList<>();
        AutomationElement testElem = Mockito.mock(AutomationElement.class);

        when(testElem.getName()).thenReturn("TEST");
        when(testElem.currentControlType()).thenReturn(ControlType.TabItem.getValue());
        when(testElem.currentPropertyValue(PropertyID.IsSelectionItemPatternAvailable.getValue())).thenReturn(1);

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

        String name = ctrl.name();

        assertTrue(name.equals("TEST"));
    }

    @Test
    public void testSelectTabPage_Not_Present() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTab tab = window.getTab(0);

            tab.selectTabPage("ERROR");
        } finally {
            closeApplication();
        }
    }
}
