package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.IUIAutomation;
import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Mark Humphreys
 * Date 12/01/2017.
 *
 * AutomationContainer tests.
 */
public class AutomationContainerTest2 {

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

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

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testGetPanel_By_AutomationID_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getPanelByAutomationId("ID-01");

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    @Ignore("Needs further work for _by_Index checks")
    public void testGetPanel_By_Index_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getPanel(0);

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testGetSlider_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

         wndw.getSlider(new Search.Builder().name("PANEL-01").build());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    @Ignore("TODO: Fix me")
    public void testGetMaskedEdit_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationElement elem = Mockito.mock(AutomationElement.class);

        List<AutomationElement> list = new ArrayList<>();

        AutomationElement elm = Mockito.mock(AutomationElement.class);

        Mockito.when(elm.getControlType()).thenReturn(ControlType.Edit.getValue());
        Mockito.when(elm.getClassName()).thenReturn("TAutomatedMaskEdit");
        Mockito.when(elm.getName()).thenReturn("AutomatedMaskEdit1");

        list.add(elm);

        Mockito.when(elem.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getMaskedEdit("AutomatedMaskEdit1");

        verify(elem, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetButton_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getButton("PANEL-01");

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testGetButton_By_AutomationID_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getButtonByAutomationId("PANEL-01");

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testGetToolbar_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getToolBar("PANEL-01");

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testGetImage_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getImage(new Search.Builder().name("PANEL-01").build());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testAutomationSpinner_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getSpinner(new Search.Builder().name("PANEL-01").build());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testGetCustom_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getCustom(new Search.Builder().name("PANEL-01").build());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testGetCustom_By_AutomationID_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getCustom(new Search.Builder().automationId("PANEL-01").build());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testGetCustom_By_ControlType_Calls_findAll_From_Element() throws Exception {
        AutomationElement elem = Mockito.mock(AutomationElement.class);

        List<AutomationElement> list = new ArrayList<>();

        AutomationElement elm = Mockito.mock(AutomationElement.class);

        Mockito.when(elm.getName()).thenReturn("PANEL-01");
        Mockito.when(elm.getClassName()).thenReturn("CUSTOM-PANEL");

        list.add(elm);

        Mockito.when(elem.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(elem, window, container);

        wndw.getCustom(new Search.Builder().controlType("CUSTOM-PANEL").build());

        verify(elem, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected = ElementNotFoundException.class)
    public void testGetCustom_By_ControlType_Throws_Exception_When_No_Found() throws Exception {
        AutomationElement elem = Mockito.mock(AutomationElement.class);

        AutomationWindow wndw = new AutomationWindow(elem, window, container);

        wndw.getCustom(new Search.Builder().controlType("PANEL-01").build());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testgetEditBox_By_Name_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getEditBox(new Search.Builder().name("Edit99").build());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testgetEditBox_By_AutomationID_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getEditBox(new Search.Builder().automationId("Edit99").build());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testgetComboboxByAutomationId_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getComboBox(new Search.Builder().name("AutomatedCombobox1").build());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testgetCombobox_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getComboBox(new Search.Builder().name("AutomatedCombobox1").build());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testgetTreeView_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getTreeView("Not there");

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void testgetProgressBar_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getProgressBar(new Search.Builder().name("notThere").build());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    @Ignore("TODO: Fix me")
    public void testgetMaskedEdit_Calls_findFirst_From_Element() throws Exception {
        AutomationElement elem = Mockito.mock(AutomationElement.class);

        List<AutomationElement> list = new ArrayList<>();

        AutomationElement elm = Mockito.mock(AutomationElement.class);

        Mockito.when(elm.getControlType()).thenReturn(ControlType.Edit.getValue());
        Mockito.when(elm.getClassName()).thenReturn("TAutomatedMaskEdit");
        Mockito.when(elm.getName()).thenReturn("AutomatedMaskEdit1");

        list.add(elm);

        Mockito.when(elem.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getMaskedEdit(0);

        verify(elem, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testgetPanelByAutomationId_Calls_findFirst_From_Element() throws Exception {
        AutomationWindow wndw = new AutomationWindow(element, window, container);

        wndw.getPanelByAutomationId("9999");

        verify(element, atLeastOnce()).findFirst(any(), any());
    }
}
