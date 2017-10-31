package mmarquee.automation.controls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.sun.jna.platform.win32.WinNT;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.Ole32Wrapper;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.TreeScope;

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
        instance = new UIAutomation(mocked_automation);

        list = new ArrayList<>();
        list.add(targetElement);
    }

    @Mock
    AutomationElement element;
    
    @Mock
    IUIAutomation mocked_automation;
    
    UIAutomation instance;

    @Mock
    Window window;

    @Mock
    ItemContainer container;

    @Mock
    WinNT.HANDLE handle;

    @InjectMocks
    UIAutomation automation;
    
    List<AutomationElement> list;
    
    @Mock
    AutomationElement targetElement;

    @Test
    public void testGetWindow_Returns_Window()
            throws AutomationException, PatternNotFoundException {

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children),any())).thenReturn(list);
        when(targetElement.getName()).thenReturn("Untitled - Notepad");
        
        AutomationApplication app = new AutomationApplication(element,
                handle,
                false,
                instance);

        AutomationWindow window = app.getWindow("Untitled - Notepad");
        assertEquals(targetElement,window.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testGetWindow_Returns_Exception_When_Element_Not_Found()
            throws AutomationException, PatternNotFoundException {

        AutomationApplication app = new AutomationApplication(element,
                handle,
                false,
                instance);

        app.getWindow("Untitled - Notepad");

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetWindow_with_RegExPattern_Returns_Window()
            throws AutomationException, PatternNotFoundException {

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children),any())).thenReturn(list);
        when(targetElement.getName()).thenReturn("Untitled - Notepad");
        
        AutomationApplication app = new AutomationApplication(element,
                handle,
                false,
                instance);

        AutomationWindow window = app.getWindow(Pattern.compile("untitled.+",Pattern.CASE_INSENSITIVE));
        assertEquals(targetElement,window.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testGetWindow_with_RegExPattern_Returns_Exception_When_Element_Not_Found()
            throws AutomationException, PatternNotFoundException {

        AutomationApplication app = new AutomationApplication(element,
                handle,
                false,
                instance);

        app.getWindow(Pattern.compile("bla\\.+blubb"));

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testSetAttached_Set_To_False() throws Exception {
        AutomationApplication app = new AutomationApplication(element, handle, false, instance);

        assertFalse(app.getIsAttached());
    }

    @Test
    public void testSetAttached_Set_To_True() throws Exception {
        AutomationApplication app = new AutomationApplication(element, handle, true, instance);

        assertTrue(app.getIsAttached());
    }
}
