package mmarquee.automation.controls;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.PropertyID;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * @author Mark Humphreys
 * Date 12/01/2017.
 *
 * These tests currently require that they are run in a Windows environment
 */
public class AutomationContainerTest {
    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

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
        assertEquals(targetElement,edit.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_getEditBox_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationEditBox edit = wndw.getEditBox(1);
        assertEquals(targetElement,edit.getElement());
    }

    @Test
    public void test_getAppBar_By_Index_Calls_findFirst_From_Element() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationAppBar appBar = wndw.getAppBar(0);
        assertEquals(targetElement,appBar.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetAppBar_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationAppBar bar = spyWndw.getAppBar("myName");
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.AppBar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetAppBar_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getAppBar("unknownName");
    }
    @Test
    public void test_GetAppBar_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationAppBar bar = spyWndw.getAppBarByAutomationId("myID");
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.AppBar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetAppBar_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getAppBarByAutomationId("unknownID");
    }

   
    @Test
    public void test_getSlider_By_Index_Calls_findFirst_From_Element() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationSlider slider = wndw.getSlider(0);
        assertEquals(targetElement,slider.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_getButton_By_Index_Calls_findFirst_From_Element() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationButton btn = wndw.getButton(0);
        assertEquals(targetElement,btn.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetTab_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationTab tab = wndw.getTab(0);

        assertTrue(tab != null);
        assertEquals(targetElement,tab.getElement());
        
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetTab_By_Index_Errors_When_Too_Big() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getTab(99);
    }

    @Test
    public void test_GetTab_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationTab tab = spyWndw.getTab("myName");
        assertEquals(targetElement,tab.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Tab);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTab_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getTab("unknownName");
    }

    
    @Test
    public void test_GetTab_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationTab tab = spyWndw.getTabByAutomationId("myID");
        assertEquals(targetElement,tab.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Tab);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTab_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getTabByAutomationId("unknownID");
    }
    
    @Test
    public void testGetEditBox_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationEditBox editBox = wndw.getEditBox(0);
        assertEquals(targetElement,editBox.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void testGetToolBar_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationToolBar editBox = wndw.getToolBar(0);
        assertEquals(targetElement,editBox.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetToolBar_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationToolBar bar = spyWndw.getToolBarByAutomationId("myID");
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.ToolBar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetToolBar_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getToolBarByAutomationId("unknownID");
    }

    @Test
    public void testGetCombobox_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationComboBox editBox = wndw.getCombobox(0);
        assertEquals(targetElement,editBox.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetCombobox_By_Index_Errors_When_Too_Big() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getCombobox(99);
    }

    @Test
    public void testGetCheckBox_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationCheckBox checkBox = wndw.getCheckBox(0);
        assertEquals(targetElement,checkBox.getElement());

        verify(element, atLeastOnce()).findAll(any(TreeScope.class), any(PointerByReference.class));
    }

    @Test
    public void test_GetCheckBox_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationCheckBox checkBox = spyWndw.getCheckBox("name");
        assertEquals(targetElement,checkBox.getElement());

        verify(spyWndw).createNamePropertyCondition("name");
        verify(spyWndw).createControlTypeCondition(ControlType.CheckBox);
        verify(element, atLeastOnce()).findFirst(any(TreeScope.class), any(PointerByReference.class));
    }
    
    @Test
    public void test_GetCheckBox_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationCheckBox checkBox = spyWndw.getCheckBoxByAutomationId("myID");
        assertEquals(targetElement,checkBox.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.CheckBox);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCheckBox_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getCheckBoxByAutomationId("unknownID");
    }
    

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetRadioButton_By_Index_Fails_When_Index_No_Present() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getRadioButton(99);
    }

    @Test
    public void testGetRadioButton_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationRadioButton radio = wndw.getRadioButton(0);
        assertEquals(targetElement,radio.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetRadioButton_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationRadioButton radio = spyWndw.getRadioButton("myName");
        assertEquals(targetElement,radio.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.RadioButton);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetRadioButton_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getRadioButton("unknownName");
    }
    
    @Test
    public void test_GetRadioButton_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationRadioButton radio = spyWndw.getRadioButtonByAutomationId("myID");
        assertEquals(targetElement,radio.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.RadioButton);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetRadioButton_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getRadioButtonByAutomationId("unknownID");
    }

    @Test
    public void testGetPanel_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationPanel panel = wndw.getPanel(0);
        assertEquals(targetElement,panel.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetPanel_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationPanel panel = spyWndw.getPanel("myName");
        assertEquals(targetElement,panel.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Pane);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPanel_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getPanel("unknownName");
    }

    @Test
    public void test_GetPanel_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationPanel panel = spyWndw.getPanelByAutomationId("myID");
        assertEquals(targetElement,panel.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Pane);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPanel_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getPanelByAutomationId("unknownID");
    }


    @Test
    public void test_GetPanelByClassName_By_Index() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationPanel bar = spyWndw.getPanelByClassName(0,"BlaBla");
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createIntegerVariant(ControlType.Pane.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPanelByClassName_By_Index_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getPanelByClassName(99,"BlaBla");
    }
    
    @Test
    public void test_GetPanelByClassName_By_Name() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationPanel panel = spyWndw.getPanelByClassName("myName","BlaBla");
        assertEquals(targetElement,panel.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Pane);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPanelByClassName_By_Name_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getPanelByClassName("unknownName","BlaBla");
    }
    
    @Test
    public void testGetDocument_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationDocument doc = wndw.getDocument(0);
        assertEquals(targetElement,doc.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetDocument_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationDocument doc = spyWndw.getDocument("myName");
        assertEquals(targetElement,doc.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Document);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDocument_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getDocument("unknownName");
    }
    
    @Test
    public void test_GetDocument_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationDocument doc = spyWndw.getDocumentByAutomationId("myID");
        assertEquals(targetElement,doc.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Document);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDocument_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getDocumentByAutomationId("unknownID");
    }

    @Test
    public void testGetProgress_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationProgressBar progress = wndw.getProgressBar(0);
        assertEquals(targetElement,progress.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetProgresBar_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationProgressBar bar = spyWndw.getProgressBarByAutomationId("myID");
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.ProgressBar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetProgressBar_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getProgressBarByAutomationId("unknownID");
    }


    @Test
    public void testGetHyperlink_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationHyperlink link = wndw.getHyperlink(0);
        assertEquals(targetElement,link.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }
    
    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetHyperlink_By_Index_Fails_When_Index_No_Present() throws Exception {
    	when(element.findAll(any(), any())).thenReturn(list);
    	
    	wndw.getHyperlink(99);
    }

    @Test
    public void test_GetHyperlink_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationHyperlink link = spyWndw.getHyperlink("myName");
        assertEquals(targetElement,link.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Hyperlink);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetHyperlink_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getHyperlink("unknownName");
    }
    
    @Test
    public void test_GetHyperlink_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationHyperlink link = spyWndw.getHyperlinkByAutomationId("myID");
        assertEquals(targetElement,link.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Hyperlink);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetHyperlink_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getHyperlinkByAutomationId("unknownID");
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetToolbar_By_Index_Fails_When_Not_Found() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getHyperlink(99);
    }

    @Test
    public void testGetToolbar_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationToolBar toolBar = wndw.getToolBar(0);
        assertEquals(targetElement,toolBar.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetSlider_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getSlider(99);
    }

    @Test
    public void test_GetSlider_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationSlider slider = spyWndw.getSliderByAutomationId("myID");
        assertEquals(targetElement,slider.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Slider);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSlider_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getSliderByAutomationId("unknownID");
    }

    
    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetProgress_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getProgressBar(99);
    }

    @Test
    public void testGetCalendar_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);
        
        AutomationCalendar calendar = wndw.getCalendar(0);
        assertEquals(targetElement,calendar.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetCalendar_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);

        wndw.getCalendar(99);
    }

    @Test
    public void test_GetCalendar_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);
        
        AutomationCalendar cal = spyWndw.getCalendar("myName");
        assertEquals(targetElement,cal.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Calendar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCalendar_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);
        
        wndw.getCalendar("unknownName");
    }
    @Test
    public void test_GetCalendar_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);

        AutomationCalendar cal = spyWndw.getCalendarByAutomationId("myID");
        assertEquals(targetElement,cal.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Calendar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCalendar_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);

        wndw.getCalendarByAutomationId("unknownID");
    }

    @Test
    public void testGetDataGrid_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationDataGrid dataGrid = wndw.getDataGrid(0);
        assertEquals(targetElement,dataGrid.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetDataGrid_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getDataGrid(99);
    }

    @Test
    public void test_GetDataGrid_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationDataGrid edit = spyWndw.getDataGridByAutomationId("myID");
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.DataGrid);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDataGrid_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getDataGridByAutomationId("unknownID");
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testTreeView_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getTreeView(99);
    }

    @Test
    public void testTreeView_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationTreeView treeView = wndw.getTreeView(0);
        assertEquals(targetElement,treeView.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetTreeView_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationTreeView edit = spyWndw.getTreeViewByAutomationId("myID");
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Tree);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTreeView_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getTreeViewByAutomationId("unknownID");
    }

    @Test
    public void testGetPasswordEditBox() throws Exception {
        BaseAutomationTest.setElementClassName(elem, "PasswordBox");

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationEditBox passwordEditBox = wndw.getPasswordEditBox(0);
        assertEquals(targetElement,passwordEditBox.element);

        verify(element, atLeastOnce()).findAll(any(), any());
    }
    
    @Test(expected=ElementNotFoundException.class)
    public void testGetPasswordEditBox_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        BaseAutomationTest.setElementClassName(elem, "PasswordBox");

        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getPasswordEditBox(99);
    }

    @Test
    public void test_GetPasswordEditBox_By_Name() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "PasswordBox");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationPasswordEditBox box = spyWndw.getPasswordEditBox("myName");
        assertEquals(targetElement,box.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Edit);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPasswordEditBox_By_Name_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "PasswordBox");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getPasswordEditBox("unknownName");
    }

    @Test
    public void test_GetPassword_By_AutomationId() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "PasswordBox");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationPasswordEditBox edit = spyWndw.getPasswordEditBoxByAutomationId("myID");
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Edit);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPassword_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "PasswordBox");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getPasswordEditBoxByAutomationId("unknownID");
    }

    @Test
    public void testGetMaskedEdit_By_Index() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "TAutomatedMaskEdit");

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationMaskedEdit maskedEdit = wndw.getMaskedEdit(0);
        assertEquals(targetElement,maskedEdit.element);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testGetMaskedEdit_By_Index_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "TAutomatedMaskEdit");

        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getMaskedEdit(99);
    }

    @Test
    public void testGetMaskedEdit_By_Name_Calls_FindFirst_Once() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "TAutomatedMaskEdit");

        doAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {

                Object[] args = invocation.getArguments();
                IntByReference reference = (IntByReference)args[0];

                reference.setValue(ControlType.Edit.getValue());

                return 0;
            }
        }).when(elem).getCurrentControlType(any());

        doAnswer(new Answer<Integer>() {
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

        when(element.findFirst(any(), any())).thenReturn(targetElement);

        AutomationMaskedEdit maskedEdit = wndw.getMaskedEdit("SMITH-01");
        assertEquals(targetElement,maskedEdit.element);

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testGetMaskedEdit_By_Name_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "TAutomatedMaskEdit");

        when(element.findFirst(any(), any())).thenReturn(null);

        wndw.getMaskedEdit("SMITH-01");
    }
    

    @Test
    public void test_GetMaskedEdit_By_AutomationId() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "TAutomatedMaskEdit");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationMaskedEdit edit = spyWndw.getMaskedEditByAutomationId("myID");
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Edit);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetMaskedEdit_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "TAutomatedMaskEdit");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getMaskedEditByAutomationId("unknownID");
    }


    @Test
    public void test_PasswordBox_By_Index() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "PasswordBox");

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationEditBox passwordEditBox = wndw.getPasswordEditBox(0);
        assertEquals(targetElement,passwordEditBox.element);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_PasswordBox_By_Index_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "PasswordBox");

        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getPasswordEditBox(99);
    }

    @Test
    public void testGetRibbonBar() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "UIRibbonCommandBarDock");
     
        AutomationElement el = Mockito.mock(AutomationElement.class);
        el.setElement(elem);

        when(el.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(el, window, container);
        AutomationRibbonBar ribbonBar = wndw.getRibbonBar();
        assertEquals(targetElement,ribbonBar.element);

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

        AutomationList list = wndw.getList(0);
        assertEquals(targetElement,list.element);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetList_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getList(99);
    }

    @Test
    public void test_GetList_By_Name() throws Exception {
        when(element.findFirst(any(), any())).thenReturn(targetElement);

        AutomationList list = wndw.getList("myName");
        assertEquals(targetElement,list.element);

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

        AutomationList list = wndw.getListByAutomationId("myID");
        assertEquals(targetElement,list.element);

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetList_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(any(), any())).thenThrow(new ElementNotFoundException());

        wndw.getListByAutomationId("unknownID");
    }

    /************************************************************************************
     *
     *          getTextBox
     *
     ***********************************************************************************/

    @Test
    public void test_GetTextBox_By_Index() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);

        AutomationTextBox textBox = spyWndw.getTextBox(0);
        assertEquals(targetElement,textBox.element);

        verify(spyWndw).createIntegerVariant(ControlType.Text.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetTextBox_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getTextBox(99);
    }

    @Test
    public void test_GetTextBox_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationTextBox textBox = spyWndw.getTextBox("myName");
        assertEquals(targetElement,textBox.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Text);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTextBox_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getTextBox("unknownName");
    }

    @Test
    public void test_GetTextBox_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationTextBox textBox = spyWndw.getTextBoxByAutomationId("myID");
        assertEquals(targetElement,textBox.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Text);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTextBox_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getTextBoxByAutomationId("unknownID");
    }

    @Test
    public void test_GetSplitButton_By_Index() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);

        AutomationSplitButton btn = spyWndw.getSplitButton(0);
        assertEquals(targetElement,btn.element);

        verify(spyWndw).createIntegerVariant(ControlType.SplitButton.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetSpliztButton_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getSplitButton(99);
    }
    
    @Test
    public void test_GetSplitButton_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationSplitButton btn = spyWndw.getSplitButton("myName");
        assertEquals(targetElement,btn.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.SplitButton);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSplitButton_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getSplitButton("unknownName");
    }
    
    @Test
    public void test_GetSplitButton_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationSplitButton btn = spyWndw.getSplitButtonByAutomationId("myID");
        assertEquals(targetElement,btn.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.SplitButton);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSplitButton_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getSplitButtonByAutomationId("unknownID");
    }


    @Test
    public void test_GetImage_By_Index() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);

        AutomationImage img = spyWndw.getImage(0);
        assertEquals(targetElement,img.element);

        verify(spyWndw).createIntegerVariant(ControlType.Image.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetImage_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getImage(99);
    }
    
    @Test
    public void test_GetImage_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationImage img = spyWndw.getImage("myName");
        assertEquals(targetElement,img.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Image);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetImage_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getImage("unknownName");
    }
    
    @Test
    public void test_GetImage_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationImage img = spyWndw.getImageByAutomationId("myID");
        assertEquals(targetElement,img.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Image);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetImage_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getImageByAutomationId("unknownID");
    }

    @Test
    public void test_GetSpinner_By_Index() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);

        AutomationSpinner spin = spyWndw.getSpinner(0);
        assertEquals(targetElement,spin.element);

        verify(spyWndw).createIntegerVariant(ControlType.Spinner.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetSpinner_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getSpinner(99);
    }
    
    @Test
    public void test_GetSpinner_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationSpinner spin = spyWndw.getSpinner("myName");
        assertEquals(targetElement,spin.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Spinner);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSpinner_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getSpinner("unknownName");
    }
    
    @Test
    public void test_GetSpinner_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationSpinner spinner = spyWndw.getSpinnerByAutomationId("myID");
        assertEquals(targetElement,spinner.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Spinner);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSpinner_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getSpinnerByAutomationId("unknownID");
    }

    @Test
    public void test_GetReBar_By_Index() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "ReBarWindow32");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationReBar bar = spyWndw.getReBar(0);
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createIntegerVariant(ControlType.Pane.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetreBar_By_Index_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "ReBarWindow32");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getReBar(99);
    }
    
    @Test
    public void test_GetReBar_By_Name() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "ReBarWindow32");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationReBar bar = spyWndw.getReBar("myName");
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Pane);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetReBar_By_Name_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "ReBarWindow32");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getReBar("unknownName");
    }
    
    @Test
    public void test_GetReBar_By_AutomationId() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "ReBarWindow32");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationReBar edit = spyWndw.getReBarByAutomationId("myID");
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Pane);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetReBar_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "ReBarWindow32");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getReBarByAutomationId("unknownID");
    }

    @Test
    public void test_GetCustom_By_Index() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);

        AutomationCustom custom = spyWndw.getCustom(0);
        assertEquals(targetElement,custom.element);

        verify(spyWndw).createIntegerVariant(ControlType.Custom.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetCustom_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getCustom(99);
    }

    @Test
    public void test_GetCustom_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationCustom custom = spyWndw.getCustom("myName");
        assertEquals(targetElement,custom.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Custom);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCustom_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getCustom("unknownName");
    }

    @Test
    public void test_GetCustom_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationCustom custom = spyWndw.getCustomByAutomationId("myID");
        assertEquals(targetElement,custom.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Custom);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCustom_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getCustomByAutomationId("unknownID");
    }

    @Test
    public void test_GetCustomByClassName_By_Index() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationCustom bar = spyWndw.getCustomByClassName(0,"BlaBla");
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createIntegerVariant(ControlType.Custom.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCustomByClassName_By_Index_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getCustomByClassName(99,"BlaBla");
    }
    
    @Test
    public void test_GetCustomByClassName_By_Name() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationCustom bar = spyWndw.getCustomByClassName("myName","BlaBla");
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Custom);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCustomByClassName_By_Name_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getCustomByClassName("unknownName","BlaBla");
    }
    

    @Test
    public void test_GetPowerpointSlide_By_Index() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);

        AutomationPowerpointSlide slide = spyWndw.getPowerpointSlide(0);
        assertEquals(targetElement,slide.element);

        verify(spyWndw).createIntegerVariant(ControlType.Custom.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetPowerpointSlide_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getPowerpointSlide(99);
    }
    
    @Test
    public void test_GetPowerpointSlide_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationPowerpointSlide slide = spyWndw.getPowerpointSlide("myName");
        assertEquals(targetElement,slide.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Custom);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPowerpointSlide_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getPowerpointSlide("unknownName");
    }

    @Test
    public void test_GetPowerpointSlide_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationPowerpointSlide slide = spyWndw.getPowerpointSlideByAutomationId("myID");
        assertEquals(targetElement,slide.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Custom);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPowerpointSlide_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getPowerpointSlideByAutomationId("unknownID");
    }

    @Test
    public void getControlByControlType_By_Index() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);
        setElementTypeAndClassName(elem, ControlType.Slider, "");

        AutomationBase custom = spyWndw.getControlByControlType(0, ControlType.Slider);
        assertEquals(targetElement,custom.element);
        assertEquals(AutomationSlider.class,custom.getClass());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void getControlByControlType_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);

        wndw.getControlByControlType(99,  ControlType.Slider);
    }

    @Test
    public void getControlByControlType_By_Index_and_ClassName() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        setElementTypeAndClassName(elem, ControlType.Hyperlink, "FooBar");

        AutomationBase custom = spyWndw.getControlByControlType(0, ControlType.Hyperlink, "FooBar");
        assertEquals(targetElement,custom.element);
        assertEquals(AutomationHyperlink.class,custom.getClass());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void getControlByControlType_By_Index_and_ClassName_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        setElementTypeAndClassName(elem, ControlType.Hyperlink, "FooBar");

        wndw.getControlByControlType(99,  ControlType.Hyperlink, "FooBar");
    }

    @Test
    public void getControlByControlType_By_Name() throws Exception {
    	BaseAutomationTest.setElementCurrentName(elem, "myName");
        setElementTypeAndClassName(elem, ControlType.Edit, "");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationBase bar = spyWndw.getControlByControlType("myName",ControlType.Edit);
        assertEquals(targetElement,bar.getElement());
        assertEquals(AutomationEditBox.class,bar.getClass());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void getControlByControlType_By_Name_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getControlByControlType("unknownName",ControlType.Button);
    }

    @Test
    public void getControlByControlType_By_Name_and_ClassName() throws Exception {
    	BaseAutomationTest.setElementCurrentName(elem, "myName");
        setElementTypeAndClassName(elem, ControlType.Button, "Blubber");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationBase bar = spyWndw.getControlByControlType("myName",ControlType.Button, "Blubber");
        assertEquals(targetElement,bar.getElement());
        assertEquals(AutomationButton.class,bar.getClass());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void getControlByControlType_By_Name_and_ClassName_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getControlByControlType("unknownName",ControlType.Calendar, "Blubber");
    }
    @Test
    public void getControlByAutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);
        setElementTypeAndClassName(elem, ControlType.Custom, "");

        AutomationBase custom = spyWndw.getControlByAutomationId("myID");
        assertEquals(targetElement,custom.getElement());
        assertEquals(AutomationCustom.class,custom.getClass());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void getControlByAutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getControlByAutomationId("unknownID");
    }

    @Test
    public void getControlByName() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);
        setElementTypeAndClassName(elem, ControlType.Document, "");

        AutomationBase custom = spyWndw.getControlByName("myName");
        assertEquals(targetElement,custom.getElement());
        assertEquals(AutomationDocument.class,custom.getClass());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void getControlByName_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getControlByName("unknownName");
    }

    @Test
    public void getControlByClassName() throws Exception {
        setElementTypeAndClassName(elem, ControlType.Window, "BlaBlubber");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationBase bar = spyWndw.getControlByClassName("BlaBlubber");
        assertEquals(targetElement,bar.getElement());
        assertEquals(AutomationWindow.class,bar.getClass());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void getControlByClassName_Throws_Exception_When_Not_found() throws Exception {

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getControlByClassName("BlaBlubber");
    }

    @Test
    public void getControlByClassName_By_Index() throws Exception {
        setElementTypeAndClassName(elem, ControlType.AppBar, "BlaBla");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationBase bar = spyWndw.getControlByClassName(0,"BlaBla");
        assertEquals(targetElement,bar.getElement());
        assertEquals(AutomationAppBar.class,bar.getClass());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void getControlByClassName_By_Index_Throws_Exception_When_Not_found() throws Exception {
        BaseAutomationTest.setElementClassName(elem, "BlaBla");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getControlByClassName(99,"BlaBla");
    }

    @Test
    public void getControlByClassName_By_Name() throws Exception {
    	BaseAutomationTest.setElementCurrentName(elem, "myName");
        setElementTypeAndClassName(elem, ControlType.AppBar, "BlaBla");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationBase bar = spyWndw.getControlByClassName("myName","BlaBla");
        assertEquals(targetElement,bar.getElement());
        assertEquals(AutomationAppBar.class,bar.getClass());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void getControlByClassName_By_Name_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getControlByClassName("unknownName","BlaBla");
    }

	private void setElementTypeAndClassName(IUIAutomationElement3 elem, ControlType controlType, String className) {
		BaseAutomationTest.answerIntByReference(controlType.getValue()).when(elem).getCurrentControlType(any());
		BaseAutomationTest.answerStringByReference(className).when(elem).getCurrentClassName(any());
	}
}

