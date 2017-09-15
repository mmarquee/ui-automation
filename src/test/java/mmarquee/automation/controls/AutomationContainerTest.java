package mmarquee.automation.controls;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * Created by Mark Humphreys on 12/01/2017.
 */
public class AutomationContainerTest {
    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        wndw = new AutomationWindow(element, window, container);
        spyWndw = Mockito.spy(wndw);
        
        elem = Mockito.mock(IUIAutomationElement3.class);
        
        list = new ArrayList<>();
        targetElement = new AutomationElement(elem);
        list.add(targetElement);
    }

    @Mock
    AutomationElement element;

    @Mock
    Window window;

    @Mock
    ItemContainer container;

    @InjectMocks
    UIAutomation automation;
    
    AutomationWindow wndw;
    AutomationWindow spyWndw;
    
    IUIAutomationElement3 elem;
    
    List<AutomationElement> list;
    AutomationElement targetElement;
    
    @Test
    public void test_getEditBox_By_Index_Calls_findFirst_From_Element() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationEditBox edit = wndw.getEditBox(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_getEditBox_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationEditBox edit = wndw.getEditBox(1);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_getAppBar_By_Index_Calls_findFirst_From_Element() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationAppBar appBar = wndw.getAppBar(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_getSlider_By_Index_Calls_findFirst_From_Element() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationSlider slider = wndw.getSlider(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_getButton_By_Index_Calls_findFirst_From_Element() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationButton btn = wndw.getButton(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetTab_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationTab tab = wndw.getTab(0);

        verify(element, atLeastOnce()).findAll(any(), any());

        assertTrue(tab != null);
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetTab_By_Index_Errors_When_Too_Big() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationTab tab = wndw.getTab(99);

        assertTrue(tab != null);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetEditBox_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationEditBox editBox = wndw.getEditBox(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetToolBar_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationToolBar editBox = wndw.getToolBar(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetCombobox_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationComboBox editBox = wndw.getCombobox(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetCombobox_By_Index_Errors_When_Too_Big() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationComboBox editBox = wndw.getCombobox(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetCheckBox_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationCheckbox radio = wndw.getCheckbox(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetRadioButton_By_Index_Fails_When_Index_No_Present() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationRadioButton radio = wndw.getRadioButton(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetRadioButton_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationRadioButton radio = wndw.getRadioButton(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetPanel_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationPanel panel = wndw.getPanel(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetDocument_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationDocument doc = wndw.getDocument(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetProgress_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationProgressBar progress = wndw.getProgressBar(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetHyperlink_By_Index_Fails_When_Index_No_Present() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationHyperlink link = wndw.getHyperlink(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetHyperlink_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationHyperlink link = wndw.getHyperlink(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetToolbar_By_Index_Fails_When_Not_Found() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getHyperlink(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetToolbar_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getToolBar(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetSlider_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getSlider(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetProgress_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getProgressBar(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetCalendar_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getProgressBar(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetCalendar_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getProgressBar(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetDataGrid_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getDataGrid(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetDataGrid_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getDataGrid(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testTreeView_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getTreeView(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testTreeView_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getTreeView(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetPasswordEditBox() throws Exception {
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
        }).when(elem).getCurrentClassName(any());

        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getPasswordEditBox(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    @Ignore("Throwing Mockito exception")
    public void testGetPasswordEditBox_Throws_Exception_When_Out_Of_Bounds() throws Exception {
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
        }).when(elem).getCurrentClassName(any());

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationEditBox box = wndw.getPasswordEditBox(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    @Ignore("Mockito exception thrown")
    public void testGetMaskedEdit_By_Index() throws Exception {
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
        }).when(elem).getCurrentClassName(any());

        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getMaskedEdit(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    @Ignore("Throws Mockito exception now")
    public void testGetMaskedEdit_By_Index_Throws_Exception_When_Not_found() throws Exception {
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
        }).when(elem).getCurrentClassName(any());

        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getMaskedEdit(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    @Ignore("Throws a Mockito exception now")
    public void testGetMaskedEdit_By_Name_Calls_FindFirst_Once() throws Exception {
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

        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getMaskedEdit("SMITH-01");

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    @Ignore("Throws a Mockito exception now")
    public void testGetMaskedEdit_By_Name_Throws_Exception_When_Not_found() throws Exception {
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
        }).when(elem).getCurrentClassName(any());

        when(element.findFirst(any(), any())).thenReturn(null);

        wndw.getMaskedEdit("SMITH-01");

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    @Ignore("Throws Mockito exception now")
    public void test_PasswordBox_By_Index() throws Exception {
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
        }).when(elem).getCurrentClassName(any());

        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getMaskedEdit(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    @Ignore("Throws Mockito exception now")
    public void test_PasswordBox_By_Index_Throws_Exception_When_Not_found() throws Exception {
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
        }).when(elem).getCurrentClassName(any());

        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getMaskedEdit(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetRibbonBar() throws Exception {
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
        }).when(elem).getCurrentClassName(any());

        AutomationElement el = Mockito.mock(AutomationElement.class);
        el.element = elem;

        when(el.findAll(any(), any())).thenReturn(list);
        
        AutomationWindow wndw = new AutomationWindow(el, window, container);
        wndw.getRibbonBar();

        verify(el, atLeastOnce()).findAll(any(), any());
    }

    /************************************************************************************
     *
     *          getList
     *
     ***********************************************************************************/

    @Test
    public void test_GetList_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getList(0);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetList_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getList(99);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetList_By_Name() throws Exception {
        when(element.findFirst(any(), any())).thenReturn(targetElement);

        wndw.getList("myName");

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetList_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(any(), any())).thenThrow(new ElementNotFoundException());

        wndw.getList("unknownName");
    }

    @Test
    public void test_GetList_By_AutomationId() throws Exception {
        when(element.findFirst(any(), any())).thenReturn(targetElement);

        wndw.getListByAutomationId("myID");

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetList_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(any(), any())).thenThrow(new ElementNotFoundException());

        AutomationWindow wndw = new AutomationWindow(element, window, container);
        wndw.getListByAutomationId("unknownID");

    }

    /************************************************************************************
     *
     *          getTextBox
     *
     ***********************************************************************************/

    @Test
    public void test_GetTextBox_By_Index() throws Exception {
        when(element.findAll(isTreeScope(TreeScope.Subtree), any())).thenReturn(list);

        AutomationTextBox textBox = spyWndw.getTextBox(0);
        assertEquals(targetElement,textBox.element);

        verify(spyWndw).createIntegerVariant(ControlType.Text.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetTextBox_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getTextBox(99);
    }

    @Test
    public void test_GetTextBox_By_Name() throws Exception {
        when(element.findFirst(isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationTextBox textBox = spyWndw.getTextBox("myName");
        assertEquals(targetElement,textBox.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Text);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTextBox_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getTextBox("unknownName");
    }

    @Test
    public void test_GetTextBox_By_AutomationId() throws Exception {
        when(element.findFirst(isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationTextBox textBox = spyWndw.getTextBoxByAutomationId("myID");
        assertEquals(targetElement,textBox.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Text);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTextBox_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getTextBoxByAutomationId("unknownID");
    }
    
    /***************************************
     * Special Matchers
     ***************************************/


	TreeScope isTreeScope(int expectedValue) {
		return argThat(new TreeScopeMatcher(expectedValue));
	}
	
    class TreeScopeMatcher implements ArgumentMatcher<TreeScope> {
    	final int expectedValue;
    	
    	TreeScopeMatcher(int expectedValue) {
    		this.expectedValue = expectedValue;
    	}
    	
        public boolean matches(TreeScope list) {
            return list.value == expectedValue;
        }
    }
}

