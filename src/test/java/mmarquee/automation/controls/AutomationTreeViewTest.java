package mmarquee.automation.controls;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Before;
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
    
    List<AutomationElement> list;
	AutomationTreeView ctrl;

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Before
    public void setupMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        list = new ArrayList<>();
        list.add(targetElement);
        
        ctrl = Mockito.spy(new AutomationTreeView(element));
    }

    @Test
    public void testGetItem_When_Item_Is_Present() throws Exception {
        when(element.findFirst(any(), any())).thenReturn(targetElement);

        AutomationTreeViewItem treeItem = ctrl.getItem("SubItem");

        assertEquals(targetElement,treeItem.getElement());
        verify(ctrl).createNamePropertyCondition("SubItem");
        verify(ctrl).createControlTypeCondition(ControlType.TreeItem);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }


    @Test(expected=ItemNotFoundException.class)
    public void testGetItem_Fails_When_Item_Is_Not_Present() throws Exception {
        when(element.findFirst(any(), any())).thenReturn(null);

        ctrl.getItem("SubItem");
    }

    @Test
    public void testGetItem_with_RegExPattern_When_Item_Is_Present() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);
        when(targetElement.getName()).thenReturn("myName");

        AutomationTreeViewItem treeItem = ctrl.getItem(Pattern.compile(".*yN.+"));

        assertEquals(targetElement,treeItem.getElement());
        verify(ctrl).createControlTypeCondition(ControlType.TreeItem);
        verify(element, atLeastOnce()).findAll(any(), any());
    }


    @Test(expected=ItemNotFoundException.class)
    public void testGetItem_with_RegExPattern_Fails_When_Item_Is_Not_Present() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);
        when(targetElement.getName()).thenReturn("myName");

        ctrl.getItem(Pattern.compile("nixIs"));
    }
    
    @Test
    public void testGetItemByAutomationId_When_Item_Present() throws Exception {
        when(element.findFirst(any(), any())).thenReturn(targetElement);

        AutomationTreeViewItem treeItem = ctrl.getItemByAutomationId("autoId");

        assertEquals(targetElement,treeItem.getElement());
        verify(ctrl).createAutomationIdPropertyCondition("autoId");
        verify(ctrl).createControlTypeCondition(ControlType.TreeItem);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }


    @Test(expected=ItemNotFoundException.class)
    public void testGetItemByAutomationId_Fails_When_Item_Is_Not_Present() throws Exception {
        when(element.findFirst(any(), any())).thenReturn(null);

        ctrl.getItemByAutomationId("wrongAutoId");
    }
}
