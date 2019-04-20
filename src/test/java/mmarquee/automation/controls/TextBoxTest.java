package mmarquee.automation.controls;

import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.Value;
import mmarquee.uiautomation.IUIAutomation;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Tests for TextBox.
 *
 * @author Mark Humphreys
 * Date 11/12/2016.
 */
public class TextBoxTest {
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName_Gets_Value_From_Element() throws Exception {
        Element element = Mockito.mock(Element.class);

        when(element.getName()).thenReturn("NAME");

        Value value = Mockito.mock(Value.class);
        when(value.isAvailable()).thenReturn(true);
        when(value.value()).thenReturn("VALUE");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        TextBox ctrl = new TextBox(
                new ElementBuilder(element).automation(instance).addPattern(value));

        String name = ctrl.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetValue_Gets_Value_From_Element_Value() throws Exception {
        Element element = Mockito.mock(Element.class);

        Value value = Mockito.mock(Value.class);
        when(value.isAvailable()).thenReturn(true);
        when(value.value()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        TextBox ctrl = new TextBox(
                new ElementBuilder(element).automation(instance).addPattern(value));

        String name = ctrl.getValue();

        assertTrue(name.equals("NAME"));
    }
}
