package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.PropertyID;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.IUIAutomation;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Mark Humphreys
 * Date 12/01/2017.
 */
public class AutomationSplitButtonTest {
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testGetName_For_Button() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Invoke pattern = Mockito.mock(Invoke.class);

        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationSplitButton button = new AutomationSplitButton(
                new ElementBuilder(element).automation(instance).invoke(pattern));

        String name = button.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testSetFocus_Calls_setFocus_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Invoke pattern = Mockito.mock(Invoke.class);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationSplitButton button = new AutomationSplitButton(
                new ElementBuilder(element).automation(instance).invoke(pattern));

        button.focus();

        verify(element, times(1)).setFocus();
    }

    @Test
    public void testClick_Calls_Invoke_Once_From_Pattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Invoke pattern = Mockito.mock(Invoke.class);

        when(element.getPropertyValue(PropertyID.IsInvokePatternAvailable.getValue())).thenReturn(1);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationSplitButton button = new AutomationSplitButton(
                new ElementBuilder(element).automation(instance).invoke(pattern));

        button.click();

        verify(pattern, times(1)).invoke();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testClick_Calls_Throws_PatternNotFoundException_When_Pattern_Not_Available() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Invoke pattern = Mockito.mock(Invoke.class);

        when(element.getPropertyValue(PropertyID.IsInvokePatternAvailable.getValue())).thenReturn(0);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationSplitButton button = new AutomationSplitButton(
                new ElementBuilder(element).automation(instance).invoke(pattern));

        button.click();

        verify(pattern, times(0)).invoke();
    }
}
