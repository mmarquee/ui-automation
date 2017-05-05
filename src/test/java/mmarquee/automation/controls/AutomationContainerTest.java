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

import javax.lang.model.element.Element;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;

/**
 * Created by Mark Humphreys on 12/01/2017.
 */
public class AutomationContainerTest {
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

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationEditBox edit = wndw.getEditBox(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_getEditBox_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationEditBox edit = wndw.getEditBox(1);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void test_getAppBar_By_Index_Calls_findFirst_From_Element() throws Exception {

        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationAppBar appBar = wndw.getAppBar(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void test_getSlider_By_Index_Calls_findFirst_From_Element() throws Exception {

        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationSlider slider = wndw.getSlider(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void test_getButton_By_Index_Calls_findFirst_From_Element() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationButton btn = wndw.getButton(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetTab_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationTab tab = wndw.getTab(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());

        assertTrue(tab != null);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetTab_By_Index_Errors_When_Too_Big() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationTab tab = wndw.getTab(99);

        assertTrue(tab != null);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetEditBox_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationEditBox editBox = wndw.getEditBox(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetToolBar_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationToolBar editBox = wndw.getToolBar(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetCombobox_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationComboBox editBox = wndw.getCombobox(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetCombobox_By_Index_Errors_When_Too_Big() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationComboBox editBox = wndw.getCombobox(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetCheckBox_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationCheckbox radio = wndw.getCheckbox(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetRadioButton_By_Index_Fails_When_Index_No_Present() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationRadioButton radio = wndw.getRadioButton(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetRadioButton_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationRadioButton radio = wndw.getRadioButton(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetPanel_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationPanel panel = wndw.getPanel(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetDocument_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationDocument doc = wndw.getDocument(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetProgress_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationProgressBar progress = wndw.getProgressBar(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetHyperlink_By_Index_Fails_When_Index_No_Present() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationHyperlink link = wndw.getHyperlink(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetHyperlink_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationHyperlink link = wndw.getHyperlink(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetToolbar_By_Index_Fails_When_Not_Found() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getHyperlink(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetToolbar_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getToolBar(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetSlider_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getSlider(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetProgress_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getProgressBar(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetCalendar_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getProgressBar(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetCalendar_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getProgressBar(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetDataGrid_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getDataGrid(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetDataGrid_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getDataGrid(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testTreeView_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getTreeView(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testTreeView_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getTreeView(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetPasswordEditBox() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        IUIAutomationElement3 spyElem = Mockito.spy(elem);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[0];

                String value = "PasswordBox";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(spyElem).getCurrentClassName(anyObject());

        list.add(new AutomationElement(spyElem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getPasswordEditBox(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testGetPasswordEditBox_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        IUIAutomationElement3 spyElem = Mockito.spy(elem);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[0];

                String value = "PasswordBox";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(spyElem).getCurrentClassName(anyObject());

        list.add(new AutomationElement(spyElem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        AutomationEditBox box = wndw.getPasswordEditBox(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetMaskedEdit_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        IUIAutomationElement3 spyElem = Mockito.spy(elem);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[1];

                String value = "TAutomationMaskEdit";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(spyElem).getCurrentClassName(anyObject());

        list.add(new AutomationElement(spyElem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getMaskedEdit(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetMaskedEdit_By_Index_Throws_Exception_When_Not_found() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        IUIAutomationElement3 spyElem = Mockito.spy(elem);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[1];

                String value = "TAutomationMaskEdit";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(spyElem).getCurrentClassName(anyObject());

        list.add(new AutomationElement(spyElem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getMaskedEdit(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetMaskedEdit_By_Name_Calls_FindFirst_Once() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        IUIAutomationElement3 spyElem = Mockito.spy(elem);

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
        }).when(spyElem).getCurrentClassName(anyObject());

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(ControlType.Edit.getValue());

                return 0;
            }
        }).when(spyElem).getCurrentControlType(anyObject());


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
        }).when(spyElem).getCurrentName(anyObject());

        list.add(new AutomationElement(spyElem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getMaskedEdit("SMITH-01");

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testGetMaskedEdit_By_Name_Throws_Exception_When_Not_found() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        IUIAutomationElement3 spyElem = Mockito.spy(elem);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[1];

                String value = "TAutomationMaskEdit";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(spyElem).getCurrentClassName(anyObject());

        list.add(new AutomationElement(spyElem));

        when(element.findFirst(anyObject(), anyObject())).thenReturn(null);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getMaskedEdit("SMITH-01");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_PasswordBox_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        IUIAutomationElement3 spyElem = Mockito.spy(elem);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[1];

                String value = "PasswordBox";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(spyElem).getCurrentClassName(anyObject());

        list.add(new AutomationElement(spyElem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getMaskedEdit(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_PasswordBox_By_Index_Throws_Exception_When_Not_found() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        IUIAutomationElement3 spyElem = Mockito.spy(elem);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[1];

                String value = "PasswordBox";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(spyElem).getCurrentClassName(anyObject());

        list.add(new AutomationElement(spyElem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getMaskedEdit(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testGetRibbonBar() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        IUIAutomationElement3 spyElem = Mockito.spy(elem);

        doAnswer(new Answer() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                PointerByReference reference = (PointerByReference)args[0];

                String value = "UIRibbonCommandBarDock";
                Pointer pointer = new Memory(Native.WCHAR_SIZE * (value.length() +1));
                pointer.setWideString(0, value);

                reference.setValue(pointer);

                return 0;
            }
        }).when(spyElem).getCurrentClassName(anyObject());

        AutomationElement el = Mockito.mock(AutomationElement.class);
        el.element = spyElem;

        list.add(new AutomationElement(spyElem));

        when(el.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(el, window, container);
        wndw.getRibbonBar();

        verify(el, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testList_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getListItem(0);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testList_By_Index_Throws_Exception_When_Not_found() throws Exception {
        List<AutomationElement> list = new ArrayList<>();

        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        list.add(new AutomationElement(elem));

        when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getListItem(99);

        verify(element, atLeastOnce()).findAll(anyObject(), anyObject());
    }

    @Test
    public void testList_By_Name() throws Exception {
        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        AutomationElement child = new AutomationElement(elem);

        when(element.findFirst(anyObject(), anyObject())).thenReturn(child);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getListItem("myName");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testList_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(anyObject(), anyObject())).thenThrow(new ElementNotFoundException());

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getListItem("unknownName");

    }
    
    @Test
    public void testList_By_AutomationId() throws Exception {
        IUIAutomationElement3 elem = Mockito.mock(IUIAutomationElement3.class);

        AutomationElement child = new AutomationElement(elem);

        when(element.findFirst(anyObject(), anyObject())).thenReturn(child);

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getListItemByAutomationId("myID");

        verify(element, atLeastOnce()).findFirst(anyObject(), anyObject());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testList_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(anyObject(), anyObject())).thenThrow(new ElementNotFoundException());

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getListItemByAutomationId("unknownID");

    }
}

