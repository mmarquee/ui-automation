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
import mmarquee.automation.pattern.Value;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Mark Humphreys on 13/12/2016.
 *
 * Tests for MainMenu.
 */
public class AutomationMainMenuTest extends BaseAutomationTest {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    protected Logger logger = Logger.getLogger(AutomationToolbarButtonTest.class.getName());

    @Test
    public void testName() throws Exception {

        AutomationElement element =
                Mockito.mock(AutomationElement.class);

        AutomationElement parent =
                Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("MENU-01");

        AutomationMainMenu item =
                new AutomationMainMenu(parent, element);

        assertEquals("MENU-01", item.name());
    }

    @Test
    public void testGetItems() throws Exception {
        AutomationElement element =
                Mockito.mock(AutomationElement.class);

        AutomationElement parent =
                Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("MENU-01");

        List<AutomationElement> collection = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        collection.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(collection);

        AutomationMainMenu menu =
                new AutomationMainMenu(parent, element);

        List<AutomationMenuItem> items = menu.getItems();

        int n = items.size();

        assertTrue(n == 1);
    }

    /* Need to validate these tests */

    @Test
    public void testGetMenuItem_With_Both_Parameters() throws Exception {
        AutomationElement element =
                Mockito.mock(AutomationElement.class);

        AutomationElement parent =
                Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("MENU-01");

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        when(element.findFirst(anyObject(), anyObject())).thenReturn(new AutomationElement(elem));

        AutomationMainMenu menu =
                new AutomationMainMenu(parent, element);

        AutomationMenuItem item = menu.getMenuItem(getLocal("menu.file"), getLocal("menu.exit"));

        // Not quite sure what to test for here - has it worked at all?

      //  Mockito.verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetMenuItem_With_First_Parameter_Only() throws Exception {
        AutomationElement element =
                Mockito.mock(AutomationElement.class);

        AutomationElement parent =
                Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("MENU-01");

        List<AutomationElement> collection = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        collection.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(collection);

        AutomationMainMenu menu =
                new AutomationMainMenu(parent, element);

        AutomationMenuItem item = menu.getMenuItem(getLocal("menu.file"), "");

        // Not quite sure what to test for here - has it worked at all?

        //  Mockito.verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testMenuFudge() throws Exception {
        AutomationElement element =
                Mockito.mock(AutomationElement.class);

        AutomationElement parent =
                Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("MENU-01");

        List<AutomationElement> collection = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        collection.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(collection);

        AutomationMainMenu menu =
                new AutomationMainMenu(parent, element);

        int keyCode = KeyEvent.getExtendedKeyCodeForChar(getLocal("menu.exit.acc").toCharArray()[0]);

        menu.menuItemFudge(getLocal("menu.file"), keyCode);

        // Not quite sure what to test for here - has it worked at all?

        //  Mockito.verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }
}
