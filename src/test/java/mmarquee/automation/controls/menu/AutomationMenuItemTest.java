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

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.controls.AutomationTitleBar;
import mmarquee.automation.controls.AutomationToolbarButtonTest;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.awt.event.KeyEvent;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 04/12/2016.
 *
 * Tests for menu item functionality
 */
public class AutomationMenuItemTest extends BaseAutomationTest {

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    protected Logger logger = Logger.getLogger(AutomationToolbarButtonTest.class.getName());

    /* Not really a test of menuitem, check it is called elsewhere
    @Test
    public void testGetMenuItem() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTitleBar sb = window.getTitleBar();
            AutomationMainMenu menu = sb.getMenuBar();
            List<AutomationMenuItem> items = menu.getItems();
            assertTrue(items.size() == 1);
        } finally {
            closeApplication();
        }
    }
*/
    @Test
    public void testGetName() throws Exception {
        AutomationElement mocked_element =
                Mockito.mock(AutomationElement.class);

        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Invoke invoke = Mockito.mock(Invoke.class);

        when(mocked_element.getName()).thenReturn("NAME");

        AutomationMenuItem item =
                new AutomationMenuItem(mocked_element, collapse, invoke);

        assertEquals("NAME", item.name());
    }

    @Test
    public void testIsExpanded_Is_False_When_Not_Expanded() throws Exception {
        AutomationElement mocked_element =
                Mockito.mock(AutomationElement.class);

        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Invoke invoke = Mockito.mock(Invoke.class);

        when(collapse.isExpanded()).thenReturn(false);

        AutomationMenuItem item =
                new AutomationMenuItem(mocked_element, collapse, invoke);

        assertFalse(item.isExpanded());
    }

    @Test
    public void testIsExpanded_Is_True_When_Expanded() throws Exception {
        AutomationElement mocked_element =
                Mockito.mock(AutomationElement.class);

        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Invoke invoke = Mockito.mock(Invoke.class);

        when(collapse.isExpanded()).thenReturn(true);

        AutomationMenuItem item =
                new AutomationMenuItem(mocked_element, collapse, invoke);

        assertFalse(item.isExpanded());
    }

    @Test
    public void testClick() throws Exception {
        AutomationElement mocked_element =
                Mockito.mock(AutomationElement.class);

        ExpandCollapse collapse = Mockito.mock(ExpandCollapse.class);
        Invoke invoke = Mockito.mock(Invoke.class);

        when(collapse.isExpanded()).thenReturn(true);

        AutomationMenuItem item =
                new AutomationMenuItem(mocked_element, collapse, invoke);

        item.click();

        verify(invoke, atLeastOnce()).invoke();
    }

    /* Make sure this being called elsewhere
    @Test
    public void testMenuFudge() throws Exception {
        loadApplication("notepad.exe", getLocal("notepad.title"));

        try {
            AutomationMainMenu menu = window.getMainMenu();

            int keyCode = KeyEvent.getExtendedKeyCodeForChar(getLocal("menu.exit.acc").toCharArray()[0]);
            menu.menuItemFudge(getLocal("menu.file"), keyCode);

            this.andRest();

            WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, this.windowName);

            assertTrue("Notepad should have quit", hwnd == null);
        } finally {
            // Should be closed already
            WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, this.windowName);

            if (hwnd != null) {
                closeApplication();
            }
        }
    }
    */
}
