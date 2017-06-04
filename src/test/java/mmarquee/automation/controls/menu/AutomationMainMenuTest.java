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

import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.Toggle;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.IUIAutomationExpandCollapsePattern;
import mmarquee.automation.uiautomation.IUIAutomationTextPattern;
import mmarquee.automation.uiautomation.IUIAutomationTogglePattern;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

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

    @Test
    public void testName() throws Exception {

        AutomationElement element =
                Mockito.mock(AutomationElement.class);

        AutomationElement parent =
                Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("MENU-01");

        AutomationMainMenu item =
                new AutomationMainMenu(parent, element);

        assertEquals("MENU-01", item.getName());
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

        assertTrue(items.size() == 1);
    }

    @Test
    @Ignore("Need much more work")
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

    @Test(expected = ItemNotFoundException.class)
    public void testGetMenuItem_With_First_Parameter_Only_Throws_Exception_When_Not_Found() throws Exception {
        AutomationElement element =
                Mockito.mock(AutomationElement.class);

        AutomationElement parent =
                Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("NOT MENU-01");

        when(element.findFirst(anyObject(), anyObject())).thenReturn(null);

        AutomationMainMenu menu =
                new AutomationMainMenu(parent, element);

        AutomationMenuItem item = menu.getMenuItem(getLocal("menu.file"), "");
    }

    @Test
    public void testGetMenuItem_With_First_Parameter_Only_Does_Not_Throws_Exception_When_Found() throws Exception {
        AutomationElement element =
                Mockito.mock(AutomationElement.class);

        AutomationElement parent =
                Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("MENU-01");

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        when(element.findFirst(anyObject(), anyObject())).thenReturn(new AutomationElement(elem));

        AutomationMainMenu menu =
                new AutomationMainMenu(parent, element);

        AutomationMenuItem item = menu.getMenuItem("MENU-01", "");
    }

    @Test(expected = ItemNotFoundException.class)
    @Ignore("Still a mess")
    public void testMenuFudge_Throws_Exception_When_Top_Level_Not_Found() throws Exception {
        AutomationElement element =
                Mockito.mock(AutomationElement.class);

        AutomationElement parent =
                Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("NOT MENU-01");

        when(element.findFirst(anyObject(), anyObject())).thenReturn(null);

        AutomationMainMenu menu =
                new AutomationMainMenu(parent, element);

        int keyCode = KeyEvent.getExtendedKeyCodeForChar(getLocal("menu.exit.acc").toCharArray()[0]);

        menu.menuItemFudge(getLocal("menu.file"), keyCode);

        // Not quite sure what to test for here - has it worked at all?

        //  Mockito.verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    @Ignore("Still a mess")
    public void testMenuFudge() throws Exception {
        AutomationElement element =
                Mockito.mock(AutomationElement.class);

        AutomationElement parent =
                Mockito.mock(AutomationElement.class);

        AutomationMainMenu menu =
                new AutomationMainMenu(parent, element);

        AutomationElement found = Mockito.mock(AutomationElement.class);

        PointerByReference pbr = new PointerByReference();

        when(found.getPattern(anyInt())).thenReturn(pbr);
        when(element.findFirst(anyObject(), anyObject())).thenReturn(found);

        Unknown mockUnknown = Mockito.mock(Unknown.class);

        Mockito.when(mockUnknown.QueryInterface(anyObject(), anyObject())).thenAnswer(
            invocation -> {
                return new WinNT.HRESULT(1);
            }
        );

        ExpandCollapse pattern = Mockito.mock(ExpandCollapse.class);

        doReturn(mockUnknown)
                .when(pattern)
                .makeUnknown(anyObject());

        int keyCode = KeyEvent.getExtendedKeyCodeForChar(getLocal("menu.exit.acc").toCharArray()[0]);

        menu.menuItemFudge(getLocal("menu.file"), keyCode);

        // Not quite sure what to test for here - has it worked at all?

        //  Mockito.verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }
}
