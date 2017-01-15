package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.pattern.SelectionItem;
import mmarquee.automation.pattern.Value;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 15/01/2017.
 */
public class AutomationListItemTest {
    @Test
    public void test_IsSelected_Gets_Value_From_SelectionItem() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        SelectionItem selection = Mockito.mock(SelectionItem.class);
        when(selection.isSelected()).thenReturn(true);

        AutomationListItem ctrl = new AutomationListItem(element, selection);

        boolean result = ctrl.isSelected();

        verify(selection, atLeastOnce()).isSelected();
    }

    @Test
    public void test_Select_Gets_Value_From_SelectionItem() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        SelectionItem selection = Mockito.mock(SelectionItem.class);

        AutomationListItem ctrl = new AutomationListItem(element, selection);

        ctrl.select();

        verify(selection, atLeastOnce()).select();
    }
}
