package mmarquee.automation.controls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.sun.jna.platform.win32.User32;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.uiautomation.IUIAutomation;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.ControlType;
import mmarquee.automation.ItemNotFoundException;

/**
 * Windows specific tests for AutomationTreeView.
 *
 * @author Mark Humphreys
 * Date 30/11/2016.
 */
public class AutomationTreeViewTest {

    @Mock AutomationElement element;
    @Mock AutomationElement targetElement;
    @Mock
    User32 user32;

    List<AutomationElement> list;
	AutomationTreeView ctrl;

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTreeView ctrl = new AutomationTreeView(
                new ElementBuilder(element).automation(instance));

        String name = ctrl.getName();

        assertTrue(name.equals("NAME"));
    }

    @Before
    public void setupMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        list = new ArrayList<>();
        list.add(targetElement);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        ctrl = Mockito.spy(
                new AutomationTreeView(
                        new ElementBuilder(element).automation(instance).user32(user32)));
    }
}
