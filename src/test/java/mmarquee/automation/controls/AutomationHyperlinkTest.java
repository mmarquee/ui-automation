package mmarquee.automation.controls;

import mmarquee.automation.*;
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
 * Date 30/11/2016.
 */
public class AutomationHyperlinkTest {
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName_Gets_Name_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Invoke pattern = Mockito.mock(Invoke.class);

        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationHyperlink link = new AutomationHyperlink(
                new ElementBuilder(element).addPattern(pattern).automation(instance));

        String name = link.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testClick_Called_Once_When_Invoke_Pattern_Available() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Invoke pattern = Mockito.mock(Invoke.class);
        when(pattern.isAvailable()).thenReturn(true);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationHyperlink link = new AutomationHyperlink(
                new ElementBuilder(element).addPattern(pattern).automation(instance));

        link.click();

        verify(pattern, times(1)).invoke();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testClick_Throws_PatternNotFoundException_When_Invoke_Pattern_Not_Available() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Invoke pattern = Mockito.mock(Invoke.class);
        when(pattern.isAvailable()).thenReturn(false);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationHyperlink link = new AutomationHyperlink(
                new ElementBuilder(element).addPattern(pattern).automation(instance));

        link.click();

        verify(pattern, times(1)).invoke();
    }
}
