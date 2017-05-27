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
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.controls.menu.AutomationMenuItem;
import mmarquee.automation.controls.menu.AutomationSystemMenu;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Window;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.List;

/**
 * Created by Mark Humphreys on 25/11/2016.
 */
public class AutomationWindowTest extends BaseAutomationTest {

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    protected Logger logger = Logger.getLogger(AutomationWindowTest.class.getName());

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetWindowName_Matches_Searched_For_Name()
            throws AutomationException, PatternNotFoundException {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        when(element.getName()).thenReturn("Name-01");

        Window window = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(window.isModal()).thenReturn(true);

        AutomationWindow windw = new AutomationWindow(element, window, container);

        assertEquals("Name-01", windw.getName());
    }

    @Test
    public void testGetWindowName_Does_Not_Match_Searched_For_Name()
            throws AutomationException, PatternNotFoundException {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        when(element.getName()).thenReturn("Name-01");

        Window window = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(window.isModal()).thenReturn(true);

        AutomationWindow windw = new AutomationWindow(element, window, container);

        assertNotEquals("Wrong-01", windw.getName());
    }

    @Test
    public void test_getSystemMenu() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationSystemMenu sm = window.getSystemMenu();

            String name = sm.getName();

            logger.info(name);

            assertEquals(getLocal("systemmenu.name"),name);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetTitleBar() throws Exception {

        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTitleBar sb = window.getTitleBar();

            String name = sb.getName();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    @Ignore // Fails to find appbar
    public void testGetAppBar_By_Index() throws Exception {
        // Needs a different application to test against
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationAppBar sb = window.getAppBar(0);

            String name = sb.getName();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testIsModal_Is_True_For_Modal_Window()
            throws AutomationException, PatternNotFoundException {

        AutomationElement element = Mockito.mock(AutomationElement.class);

        Window window = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(window.isModal()).thenReturn(true);

        AutomationWindow windw = new AutomationWindow(element, window, container);

        assertTrue(windw.isModal());
    }

    @Test
    public void testIsModal_Is_False_For_Non_Modal_Window()
            throws AutomationException, PatternNotFoundException {

        AutomationElement element = Mockito.mock(AutomationElement.class);

        Window window = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(window.isModal()).thenReturn(false);

        AutomationWindow windw = new AutomationWindow(element, window, container);

        assertFalse(windw.isModal());
    }

    @Test
    public void testGetMenuItem() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTitleBar sb = window.getTitleBar();

            AutomationMainMenu menu = sb.getMenuBar();

            List<AutomationMenuItem> items = menu.getItems();

            logger.info(menu.getItems().get(0).getName());

            assertTrue(items.size() == 1);
        } finally {
            closeApplication();
        }
    }
}

