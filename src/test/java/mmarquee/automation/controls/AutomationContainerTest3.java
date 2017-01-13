package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by inpwt on 12/01/2017.
 */
public class AutomationContainerTest3 {
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

    @InjectMocks
    UIAutomation automation;

    @Test
    public void test_getEditBox_By_Index_Calls_findFirst_From_Element() throws Exception {

        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationEditBox edit = wndw.getEditBox(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_getEditBox_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationEditBox edit = wndw.getEditBox(1);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void test_getAppBar_By_Index_Calls_findFirst_From_Element() throws Exception {

        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationAppBar appBar = wndw.getAppBar(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void test_getSlider_By_Index_Calls_findFirst_From_Element() throws Exception {

        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationSlider slider = wndw.getSlider(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void test_getButton_By_Index_Calls_findFirst_From_Element() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationButton btn = wndw.getButton(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetTab_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationTab tab = wndw.getTab(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetEditBox_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationEditBox editBox = wndw.getEditBox(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetToolBar_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationToolBar editBox = wndw.getToolBar(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetCombobox_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationComboBox editBox = wndw.getCombobox(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetCombobox_By_Index_Errors_When_Too_Big() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationComboBox editBox = wndw.getCombobox(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetCheckBox_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationCheckbox radio = wndw.getCheckbox(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetRadioButton_By_Index_Fails_When_Index_No_Present() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationRadioButton radio = wndw.getRadioButton(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetRadioButton_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationRadioButton radio = wndw.getRadioButton(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetPanel_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationPanel panel = wndw.getPanel(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetDocument_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationDocument doc = wndw.getDocument(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetProgress_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationProgressBar progress = wndw.getProgressBar(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetHyperlink_By_Index_Fails_When_Index_No_Present() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationHyperlink link = wndw.getHyperlink(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetHyperlink_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationHyperlink link = wndw.getHyperlink(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetToolbar_By_Index_Fails_When_Not_Found() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getHyperlink(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetToolbar_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getToolBar(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetSlider_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getSlider(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetProgress_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getProgressBar(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetCalendar_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getProgressBar(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetCalendar_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getProgressBar(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetDataGrid_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getDataGrid(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

}

