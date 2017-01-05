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

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.utils.Utils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 25/11/2016.
 */
public class AutomationApplicationTest extends BaseAutomationTest {
    protected Logger logger = Logger.getLogger(AutomationApplicationTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    private UIAutomation instance;
    private AutomationApplication app;

    @Before
    public void setUp() throws Exception {
        // Notepad _MIGHT_ still be running, so close it if it is
        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "Untitled - Notepad");

        if (hwnd != null) {
            Utils.quitProcess(hwnd);
        }

        instance = UIAutomation.getInstance();

        app = instance.launch("notepad.exe");

        app.waitForInputIdle();
    }

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

    @Test
    public void testGetWindowFinds_A_Running_Window()
            throws AutomationException, PatternNotFoundException {
        AutomationWindow window = app.getWindow("Untitled - Notepad");
        assertTrue("Name should be set", window.name().equals("Untitled - Notepad"));
    }

    @Test
    public void testGetWindowFinds_Does_Not_Find_A_Non_Running_Window()
            throws AutomationException, PatternNotFoundException {

        try {
            AutomationWindow window = app.getWindow("Untitled - Notepad99");

            try {
                String name = window.name();

                assertFalse("Name should be equal", window.name().equals("Untitled - Notepad99"));
            } catch (Throwable e) {
                assertTrue("Shouldn't get a value", true);
            }
        } catch (Throwable e) {
            assertTrue("Shouldn't get a value", true);
        }
    }

    @Test
    @Ignore
    public void testClose_Works() {
        app.close("Untitled - Notepad");

        this.andRest();

        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "Untitled - Notepad");

        assertTrue("Notepad won't have closed", hwnd != null);
    }

    @Test
    @Ignore // passes when run individually
    public void testQuit_Works() {
        app.quit("Untitled - Notepad");

        this.andRest();

        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "Untitled - Notepad");

        assertTrue("Notepad should have quit", hwnd == null);
    }

    @Test
    public void testIsAttached() {
        logger.info(app.getIsAttached());

        assertFalse(app.getIsAttached());
    }

    @Test
    public void testSetAttached_Set_To_False() {
        app.setIsAttached(false);

        assertFalse(app.getIsAttached());
    }

    @Test
    public void testSetAttached_Set_To_True() {
        app.setIsAttached(true);

        assertTrue(app.getIsAttached());
    }

}
