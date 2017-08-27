package mmarquee.automation.controls;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by Mark Humphreys on 12/01/2017.
 *
 * Tests for AutomationContainer.
 */
public class AutomationContainerTest {
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    AutomationElement element;

    @Mock
    private Window window;

    @Mock
    ItemContainer container;

    @InjectMocks
    UIAutomation automation;

    @Test
    public void test_getEditBox_By_Index_Calls_findFirst_From_Element() throws Exception {

        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getEditBox(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_getEditBox_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getEditBox(1);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_getAppBar_By_Index_Calls_findFirst_From_Element() throws Exception {

        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getAppBar(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_getSlider_By_Index_Calls_findFirst_From_Element() throws Exception {

        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getSlider(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_getButton_By_Index_Calls_findFirst_From_Element() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getButton(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetTab_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        AutomationTab tab = automatedWindow.getTab(0);

        verify(element, atLeastOnce()).findAll(any(), any());

        assertTrue(tab != null);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetTab_By_Index_Errors_When_Too_Big() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        AutomationTab tab = automatedWindow.getTab(99);

        assertTrue(tab != null);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetEditBox_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getEditBox(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetToolBar_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getToolBar(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetCombobox_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getCombobox(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetCombobox_By_Index_Errors_When_Too_Big() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getCombobox(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetCheckBox_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getCheckbox(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetRadioButton_By_Index_Fails_When_Index_No_Present() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getRadioButton(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetRadioButton_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getRadioButton(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetPanel_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getPanel(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetDocument_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getDocument(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetProgress_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getProgressBar(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetHyperlink_By_Index_Fails_When_Index_No_Present() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getHyperlink(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetHyperlink_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getHyperlink(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetToolbar_By_Index_Fails_When_Not_Found() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getHyperlink(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetToolbar_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getToolBar(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetSlider_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getSlider(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetProgress_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getProgressBar(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetCalendar_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getProgressBar(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetCalendar_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getProgressBar(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetDataGrid_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getDataGrid(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetDataGrid_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getDataGrid(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testTreeView_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getTreeView(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testTreeView_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getTreeView(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetPasswordEditBox() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        Mockito.when(elem.getCurrentClassName(any())).thenAnswer(
                invocation -> {
                    Object[] args = invocation.getArguments();
                    PointerByReference reference = (PointerByReference)args[0];

                    String value = "PasswordBox";
                    Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                    pointer.setWideString(0, value);

                    reference.setValue(pointer);

                    return 0;
                });

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getPasswordEditBox(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testGetPasswordEditBox_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        Mockito.when(elem.getCurrentClassName(any())).thenAnswer(
                invocation -> {
                    Object[] args = invocation.getArguments();
                    PointerByReference reference = (PointerByReference)args[0];

                    String value = "PasswordBox";
                    Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                    pointer.setWideString(0, value);

                    reference.setValue(pointer);

                    return 0;
                });

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getPasswordEditBox(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetMaskedEdit_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        Mockito.when(elem.getCurrentClassName(any())).thenAnswer(
                invocation -> {
                    Object[] args = invocation.getArguments();
                    PointerByReference reference = (PointerByReference)args[0];

                    String value = "TAutomationMaskEdit";
                    Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                    pointer.setWideString(0, value);

                    reference.setValue(pointer);

                    return 0;
                });

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getMaskedEdit(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testGetMaskedEdit_By_Index_Throws_Exception_When_Not_found() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        Mockito.when(elem.getCurrentClassName(any())).thenAnswer(
                invocation -> {
                    Object[] args = invocation.getArguments();
                    PointerByReference reference = (PointerByReference)args[0];

                    String value = "TAutomationMaskEdit";
                    Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                    pointer.setWideString(0, value);

                    reference.setValue(pointer);

                    return 0;
                });

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getMaskedEdit(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetMaskedEdit_By_Name_Calls_FindFirst_Once() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[0];

                String value = "TAutomatedMaskEdit";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(elem).getCurrentClassName(any());

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(ControlType.Edit.getValue());

                return 0;
            }
        }).when(elem).getCurrentControlType(any());


        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[0];

                String value = "SMITH-01";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(elem).getCurrentName(any());

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getMaskedEdit("SMITH-01");

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testGetMaskedEdit_By_Name_Throws_Exception_When_Not_found() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        Mockito.when(elem.getCurrentClassName(any())).thenAnswer(
                invocation -> {
                    Object[] args = invocation.getArguments();
                    PointerByReference reference = (PointerByReference)args[0];

                    String value = "TAutomationMaskEdit";
                    Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                    pointer.setWideString(0, value);

                    reference.setValue(pointer);

                    return 0;
                });

        list.add(new AutomationElement(elem));

        when(element.findFirst(any(), any())).thenReturn(null);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getMaskedEdit("SMITH-01");

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_PasswordBox_By_Index_Throws_Exception_When_Not_Found() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        Mockito.when(elem.getCurrentClassName(any())).thenAnswer(
                invocation -> {
                    Object[] args = invocation.getArguments();
                    PointerByReference reference = (PointerByReference)args[0];

                    String value = "PasswordBox";
                    Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                    pointer.setWideString(0, value);

                    reference.setValue(pointer);

                    return 0;
                });

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getMaskedEdit(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_PasswordBox_By_Index_Throws_Exception_When_Not_found() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        Mockito.when(elem.getCurrentClassName(any())).thenAnswer(
                invocation -> {
                    Object[] args = invocation.getArguments();
                    PointerByReference reference = (PointerByReference)args[0];

                    String value = "PasswordBox";
                    Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                    pointer.setWideString(0, value);

                    reference.setValue(pointer);

                    return 0;
                });

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getMaskedEdit(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetRibbonBar() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        Mockito.when(elem.getCurrentClassName(any())).thenAnswer(
                invocation -> {
                    Object[] args = invocation.getArguments();
                    PointerByReference reference = (PointerByReference)args[0];

                    String value = "UIRibbonCommandBarDock";
                    Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                    pointer.setWideString(0, value);

                    reference.setValue(pointer);

                    return 0;
                });

        AutomationElement el = Mockito.mock(AutomationElement.class);
        el.element = elem;

        list.add(new AutomationElement(elem));

        when(el.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(el, window, container);
        wndw.getRibbonBar();

        verify(el, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testList_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getList(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testList_By_Index_Throws_Exception_When_Not_found() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getList(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testList_By_Name() throws Exception {
        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        AutomationElement child = new AutomationElement(elem);

        when(element.findFirst(any(), any())).thenReturn(child);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getList("myName");

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testList_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(any(), any())).thenThrow(new ElementNotFoundException());

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getList("unknownName");

    }
    
    @Test
    public void testList_By_AutomationId() throws Exception {
        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        AutomationElement child = new AutomationElement(elem);

        when(element.findFirst(any(), any())).thenReturn(child);

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getListByAutomationId("myID");

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testList_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(any(), any())).thenThrow(new ElementNotFoundException());

        AutomationWindow automatedWindow = new AutomationWindow(element, window, container);
        automatedWindow.getListByAutomationId("unknownID");
    }
}

