package mmarquee.automation.controls;

import com.sun.jna.platform.win32.User32;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.ControlType;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.uiautomation.IUIAutomation;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Windows specific tests for AutomationTreeView.
 *
 * @author Mark Humphreys
 * Date 30/11/2016.
 */
public class AutomationTreeViewTest2 {

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }
    
    @Mock AutomationElement element;
    @Mock AutomationElement targetElement;
    @Mock
    User32 user32;

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

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        ctrl = Mockito.spy(
                new AutomationTreeView(
                        new ElementBuilder(element).automation(instance).user32(user32)));
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

        AutomationTreeViewItem treeItem =
                ctrl.getItem(Search.getBuilder().automationId("autoId").build());

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
