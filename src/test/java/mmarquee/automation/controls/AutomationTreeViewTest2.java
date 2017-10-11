package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.PropertyID;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Non-o.s. specific tests for AutomationTreeView.
 *
 * @author Mark Humphreys
 * Date 11/10/2017.
 */
public class AutomationTreeViewTest2 {

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTreeView ctrl = new AutomationTreeView(element, instance);

        String name = ctrl.getName();

        assertTrue(name.equals("NAME"));
    }
}
