package mmarquee.automation.controls;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import mmarquee.automation.UIAutomation;
import mmarquee.automation.uiautomation.IUIAutomation;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
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

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }
    
    @Mock AutomationElement element;
    @Mock AutomationElement targetElement;
    
    List<AutomationElement> list;
	AutomationTreeView ctrl;

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTreeView ctrl = new AutomationTreeView(
                new ElementBuilder(element).automation(instance));

        String name = ctrl.getName();

        assertTrue(name.equals("NAME"));
    }

    @Before
    public void setupMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
        
        list = new ArrayList<>();
        list.add(targetElement);
        
        ctrl = Mockito.spy(new AutomationTreeView(new ElementBuilder(element)));
    }

    @Test
    public void testGetItem_When_Item_Is_Present() throws Exception {
        when(element.findFirst(any(), any())).thenReturn(targetElement);

        AutomationTreeViewItem treeItem = ctrl.getItem(Search.getBuilder("SubItem").build());

        assertEquals(targetElement,treeItem.getElement());
        verify(ctrl).createNamePropertyCondition("SubItem");
        verify(ctrl).createControlTypeCondition(ControlType.TreeItem);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }


    @Test(expected=ItemNotFoundException.class)
    public void testGetItem_Fails_When_Item_Is_Not_Present() throws Exception {
        when(element.findFirst(any(), any())).thenReturn(null);

        ctrl.getItem(Search.getBuilder("SubItem").build());
    }

    @Test
    public void testGetItem_with_RegExPattern_When_Item_Is_Present() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);
        when(targetElement.getName()).thenReturn("myName");

        AutomationTreeViewItem treeItem = ctrl.getItem(Search.getBuilder(Pattern.compile(".*yN.+")).build());

        assertEquals(targetElement,treeItem.getElement());
        verify(ctrl).createControlTypeCondition(ControlType.TreeItem);
        verify(element, atLeastOnce()).findAll(any(), any());
    }


    @Test(expected=ItemNotFoundException.class)
    public void testGetItem_with_RegExPattern_Fails_When_Item_Is_Not_Present() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);
        when(targetElement.getName()).thenReturn("myName");

        ctrl.getItem(Search.getBuilder(Pattern.compile("nixIs")).build());
    }
    
    @Test
    public void testGetItemByAutomationId_When_Item_Present() throws Exception {
        when(element.findFirst(any(), any())).thenReturn(targetElement);

        AutomationTreeViewItem treeItem = ctrl.getItem(Search.getBuilder().automationId("autoId").build());

        assertEquals(targetElement,treeItem.getElement());
        verify(ctrl).createAutomationIdPropertyCondition("autoId");
        verify(ctrl).createControlTypeCondition(ControlType.TreeItem);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ItemNotFoundException.class)
    public void testGetItemByAutomationId_Fails_When_Item_Is_Not_Present() throws Exception {
        when(element.findFirst(any(), any())).thenReturn(null);

        ctrl.getItem(Search.getBuilder().automationId("wrongAutoId").build());
    }
}
