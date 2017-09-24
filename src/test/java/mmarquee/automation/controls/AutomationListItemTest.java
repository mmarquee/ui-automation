package mmarquee.automation.controls;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assume;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.PropertyID;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.SelectionItem;

/**
 * Created by Mark Humphreys on 15/01/2017.
 */
public class AutomationListItemTest {
    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    @Test
    public void test_IsSelected_Gets_Value_From_SelectionItem() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        SelectionItem selection = Mockito.mock(SelectionItem.class);
        when(selection.isSelected()).thenReturn(true);

        AutomationListItem ctrl = new AutomationListItem(element);
        ctrl.selectItemPattern = selection;

        boolean result = ctrl.isSelected();
        assertEquals(true,result);

        verify(selection, atLeastOnce()).isSelected();
    }

    @Test
    public void test_Select_Selects_SelectionItem() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        SelectionItem selection = Mockito.mock(SelectionItem.class);

        AutomationListItem ctrl = new AutomationListItem(element);
        ctrl.selectItemPattern = selection;

        ctrl.select();

        verify(selection, atLeastOnce()).select();
    }

    @Test
    public void test_Click_Invokes_InvokePattern() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Invoke invokePattern = Mockito.mock(Invoke.class);
        
        when(element.getPropertyValue(PropertyID.IsInvokePatternAvailable.getValue())).thenReturn(1);

        AutomationListItem ctrl = new AutomationListItem(element);
        ctrl.invokePattern = invokePattern;

        ctrl.click();

        verify(invokePattern, atLeastOnce()).invoke();
    }

    @Test(expected=PatternNotFoundException.class)
    public void test_Click_Does_Not_Invoke_If_Not_Possible() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Invoke invokePattern = Mockito.mock(Invoke.class);
        
        when(element.getPropertyValue(PropertyID.IsInvokePatternAvailable.getValue())).thenReturn(0);

        AutomationListItem ctrl = new AutomationListItem(element);
        ctrl.invokePattern = invokePattern;

        ctrl.click();

        verify(invokePattern, never()).invoke();
    }
}
