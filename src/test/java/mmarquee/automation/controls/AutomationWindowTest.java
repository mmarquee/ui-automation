/*
 * Copyright 2016 inpwtepydjuf@gmail.com
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

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.controls.menu.AutomationMenuItem;
import mmarquee.automation.controls.menu.AutomationSystemMenu;
import mmarquee.automation.controls.rebar.AutomationReBar;
import mmarquee.automation.controls.ribbon.AutomationRibbonBar;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.utils.Utils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 25/11/2016.
 */
public class AutomationWindowTest extends BaseAutomationTest {

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    protected Logger logger = Logger.getLogger(AutomationWindowTest.class.getName());

    @After
    public void tearDown() {
        // Must be a better way of doing this????
        this.andRest();

        // Notepad _MIGHT_ still be running, so close it if it is
        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "Untitled - Notepad");

        if (hwnd != null) {
            Utils.quitProcess(hwnd);
        }
    }

    @Before
    public void setUp() throws Exception {
        // Notepad _MIGHT_ still be running, so close it if it is
        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "Untitled - Notepad");

        if (hwnd != null) {
            Utils.quitProcess(hwnd);
        }

        instance = UIAutomation.getInstance();

        application = instance.launch("notepad.exe");

        application.waitForInputIdle();
    }

    @Test
    public void testGetRebar_By_Index() throws Exception {

        loadApplication("explorer", "File Explorer");

        try {
            AutomationReBar rebar = window.getReBar(0);
            AutomationToolBar toolbar = rebar.getToolBar("Up band toolbar");

            assertTrue(toolbar.name().equals("Up band toolbar"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetWindowName_Matches_Searched_For_Name()
            throws AutomationException, PatternNotFoundException {
        AutomationWindow window = application.getWindow("Untitled - Notepad");

        assertTrue("Name should match", window.name().equals("Untitled - Notepad"));
    }

    @Test
    public void testGetStatusBar() throws Exception {

        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationStatusBar sb = window.getStatusBar();

            String name = sb.name();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void test_getSystemMenu() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationSystemMenu sm = window.getSystemMenu();

            String name = sm.name();

            logger.info(name);

            assertTrue(name.equals("System"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetTitleBar_By_Index() throws Exception {

        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTitleBar sb = window.getTitleBar();

            String name = sb.name();

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

            String name = sb.name();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testIsModal_Is_False_For_Non_Modal_Window()
            throws AutomationException, PatternNotFoundException {
        AutomationWindow window = application.getWindow("Untitled - Notepad");
        assertFalse("Notepad isn't modal!", window.isModal());
    }

    @Test
    public void testGetMenuItem() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTitleBar sb = window.getTitleBar();

            AutomationMainMenu menu = sb.getMenuBar();

            List<AutomationMenuItem> items = menu.getItems();



            logger.info(menu.getItems().get(0).name());

            assertTrue(items.size() == 1);
        } finally {
            closeApplication();
        }
    }
}

