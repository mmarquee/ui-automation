package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.PropertyID;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

/**
 * Created by Mark on 30/11/2016.
 */
public class AutomationTreeViewTest {

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("NAME");

        AutomationTreeView ctrl = new AutomationTreeView(element);

        String name = ctrl.name();

        assertTrue(name.equals("NAME"));    }

    @Test
    public void testGetItem_When_Item_Present() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        IUIAutomationElement listElement = Mockito.mock(IUIAutomationElement.class);

        AutomationElement result = new AutomationElement(listElement);

        when(element.findFirst(anyObject(), anyObject())).thenReturn(result);
        when(element.currentPropertyValue(PropertyID.IsSelectionItemPatternAvailable.getValue())).thenReturn(1);

        AutomationTreeView ctrl = new AutomationTreeView(element);

        AutomationTreeViewItem treeItem = ctrl.getItem("SubItem");
    }
}
