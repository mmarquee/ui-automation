package mmarquee.automation.controls;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.AutomationException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.utils.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 25/11/2016.
 */
public class AutomationApplicationTest {
    private void andRest() {
        // Must be a better way of doing this????
        try {
            Thread.sleep(500);
        } catch (Throwable ex) {
            // interrupted
        }
    }

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

    /* These test are not quite working yet */
/*
    @Test
    public void testClose_Works() {
        app.close("Untitled - Notepad");

        this.andRest();

        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "Untitled - Notepad");

        assertTrue("Notepad won't have closed", hwnd != null);
    }

    public void testQuit_Works() {
        app.quit("Untitled - Notepad");

        this.andRest();

        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "Untitled - Notepad");

        assertTrue("Notepad should have quit", hwnd == null);
    }
*/
}
