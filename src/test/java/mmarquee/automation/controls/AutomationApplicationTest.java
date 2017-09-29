package mmarquee.automation.controls;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author Mark Humphreys
 * Date 13/01/2017.
 *
 * Mocked tests for AutomationApplication
 *
 * Currently these need to be run in a Windows environment.
 */
public class AutomationApplicationTest {
    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    AutomationElement element;

    @Mock
    Window window;

    @Mock
    ItemContainer container;

    @Mock
    WinNT.HANDLE handle;

    @InjectMocks
    UIAutomation automation;

    @Test(expected= ElementNotFoundException.class)
    public void testGetWindow_Returns_Exception_When_Element_Not_Found()
            throws AutomationException, PatternNotFoundException {

        AutomationApplication app = new AutomationApplication(element, handle, false);

        AutomationWindow window = app.getWindow("Untitled - Notepad");

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    @Ignore("Need to mock the elem getting a name")
    public void testGetWindow_Calls_FindAll_From_Element()
            throws AutomationException, PatternNotFoundException {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationApplication app = new AutomationApplication(element, handle, false);

        AutomationWindow window = app.getWindow("Untitled - Notepad");

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testSetAttached_Set_To_False() throws Exception {
        AutomationApplication app = new AutomationApplication(element, handle, false);

        assertFalse(app.getIsAttached());
    }

    @Test
    public void testSetAttached_Set_To_True() throws Exception {
        AutomationApplication app = new AutomationApplication(element, handle, true);

        assertTrue(app.getIsAttached());
    }

    @Test
    public void testClose_Calls_FindWindow() throws Exception {
        User32 user32 = Mockito.mock(User32.class);

        AutomationApplication app = new AutomationApplication(element, handle, true, user32);

        app.close("Untitled - Notepad");

        verify(user32, atLeastOnce()).FindWindow(any(), any());
    }

    @Test
    public void testQuit_Calls_FindWindow() throws Exception {
        User32 user32 = Mockito.mock(User32.class);

        AutomationApplication app = new AutomationApplication(element, handle, true, user32);

        app.quit("Untitled - Notepad");

        verify(user32, atLeastOnce()).FindWindow(any(), any());
    }

}
