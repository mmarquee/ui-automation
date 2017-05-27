package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 11/12/2016.
 */
public class AutomationTextBoxTest {
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName_Gets_Value_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("NAME");

        AutomationTextBox ctrl = new AutomationTextBox(element);

        String name = ctrl.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetValue_Gets_Value_From_Element_Name() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("VALUE");

        AutomationTextBox ctrl = new AutomationTextBox(element);

        String name = ctrl.getValue();

        assertTrue(name.equals("VALUE"));
    }
}
