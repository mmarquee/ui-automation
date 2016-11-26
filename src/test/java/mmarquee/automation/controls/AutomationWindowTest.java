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
public class AutomationWindowTest {
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

        app = instance.launch("notepad.exe");

        app.waitForInputIdle();
    }

    @Test
    public void testGetWindowName_Matches_Searched_For_Name()
            throws AutomationException, PatternNotFoundException {
        AutomationWindow window = app.getWindow("Untitled - Notepad");
        assertTrue("Name should match", window.name().equals("Untitled - Notepad"));
    }

    @Test
    public void testIsModal_Is_False_For_Non_Modal_Window()
            throws AutomationException, PatternNotFoundException {
        AutomationWindow window = app.getWindow("Untitled - Notepad");
        assertFalse("Notepad isn't modal!", window.isModal());
    }
}

