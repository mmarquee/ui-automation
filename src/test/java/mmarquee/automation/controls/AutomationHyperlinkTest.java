package mmarquee.automation.controls;

import mmarquee.automation.*;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 30/11/2016.
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

        AutomationHyperlink link = new AutomationHyperlink(element, pattern);

        String name = link.name();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testClick_Called_Once_When_Invoke_Pattern_Available() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Invoke pattern = Mockito.mock(Invoke.class);

        when(element.getPropertyValue(PropertyID.IsInvokePatternAvailable.getValue())).thenReturn(1);

        AutomationHyperlink link = new AutomationHyperlink(element, pattern);

        link.click();

        verify(pattern, times(1)).invoke();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testClick_Throws_PatternNotFoundException_When_Invoke_Pattern_Not_Available() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Invoke pattern = Mockito.mock(Invoke.class);

        when(element.getPropertyValue(PropertyID.IsInvokePatternAvailable.getValue())).thenReturn(0);

        AutomationHyperlink link = new AutomationHyperlink(element, pattern);

        link.click();

        verify(pattern, times(1)).invoke();
    }
}
