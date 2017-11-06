package mmarquee.automation.controls;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import mmarquee.automation.*;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author Mark Humphreys
 * Date 13/01/2017.
 *
 * Mocked tests for AutomationApplication.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest( { Ole32Wrapper.class })
public class AutomationApplicationTest {
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

        AutomationElement element = Mockito.mock(AutomationElement.class);
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);

        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationApplication app = new AutomationApplication(element,
                handle,
                false,
                instance);

        AutomationWindow window = app.getWindow("Untitled - Notepad");

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testSetAttached_Set_To_False() throws Exception {

        AutomationElement element = Mockito.mock(AutomationElement.class);
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);

        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationApplication app = new AutomationApplication(element, handle, false, instance);

        assertFalse(app.getIsAttached());
    }

    @Test
    public void testSetAttached_Set_To_True() throws Exception {

        AutomationElement element = Mockito.mock(AutomationElement.class);
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);

        UIAutomation instance = new UIAutomation(mocked_automation);
        AutomationApplication app = new AutomationApplication(element, handle, true, instance);

        assertTrue(app.getIsAttached());
    }
}
