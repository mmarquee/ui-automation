package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.Window;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 12/01/2017.
 */
public class AutomationContainerTest2 {
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    AutomationElement element;

    @Mock
    Window window;

    @Mock
    ItemContainer container;

    @Test
    public void testName_Gets_Name_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        Window pattern = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getName()).thenReturn("NAME");

        AutomationWindow window = new AutomationWindow(element, pattern, container);

        assertTrue(window.getName().equals("NAME"));
    }

    @Test
    public void testGetPanel_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getPanel("PANEL-01");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testGetPanel_By_AutomationID_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getPanelByAutomationId("ID-01");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    @Ignore("Needs further work for _by_Index checks")
    public void testGetPanel_By_Index_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getPanel(0);

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testGetSlider_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
         wndw.getSlider("PANEL-01");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    @Ignore("Need to mock more of the element, etc.")
    public void testGetMaskedEdit_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getMaskedEdit("PANEL-01");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testGetButton_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getButton("PANEL-01");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testGetButton_By_AutomationID_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getButtonByAutomationId("PANEL-01");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testGetToolbar_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getToolBar("PANEL-01");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testGetImage_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getImage("PANEL-01");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testAutomationSpinner_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getSpinner("PANEL-01");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testGetCustom_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getCustom("PANEL-01");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testGetCustom_By_AutomationID_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getCustomByAutomationId("PANEL-01");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    @Ignore("Need to mock findall")
    public void testGetCustom_By_ControlType_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getCustomByControlType("PANEL-01");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testgetEditBox_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getEditBox("Edit99");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testgetEditBox_By_AutomationID_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getEditBoxByAutomationId("Edit99");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testgetComboboxByAutomationId_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getComboboxByAutomationId("AutomatedCombobox1");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testgetCombobox_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getCombobox("AutomatedCombobox1");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testgetTreeView_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getTreeView("Not there");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testgetProgressBar_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getProgressBar("NotThere");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    @Ignore("Needs further mocking")
    public void testgetMaskedEdit_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getMaskedEdit("AutomatedMaskEdit1");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test
    public void testgetPanelByAutomationId_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getPanelByAutomationId("9999");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }
}
