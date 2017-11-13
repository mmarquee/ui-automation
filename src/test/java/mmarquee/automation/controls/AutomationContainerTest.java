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

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.sun.jna.platform.win32.Variant;
import com.sun.jna.ptr.PointerByReference;

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
    
    @Mock
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
    public void test_GetAppBar_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationAppBar bar = spyWndw.getAppBar(Pattern.compile("myName"));
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.AppBar);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetAppBar_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getAppBar(Pattern.compile("NotmyName"));
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

    @Test (expected=IndexOutOfBoundsException.class)
    public void Button() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getComboBox(new Search.Builder().id(99).build());
    }

    @Test
    public void test_GetButton_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationButton button = spyWndw.getButton("name");
        assertEquals(targetElement,button.getElement());

        verify(spyWndw).createNamePropertyCondition("name");
        verify(spyWndw).createControlTypeCondition(ControlType.Button);
        verify(element, atLeastOnce()).findFirst(any(TreeScope.class), any(PointerByReference.class));
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetButton_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getButton("unknownName");
    }
    
    @Test
    public void test_GetButton_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationButton button = spyWndw.getButton(Pattern.compile("myN.*"));
        assertEquals(targetElement,button.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Button);
        verify(element, atLeastOnce()).findAll(any(TreeScope.class), any(PointerByReference.class));
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetButton_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getButton(Pattern.compile("other"));
    }

    @Test
    public void test_GetButton_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationButton button = spyWndw.getButtonByAutomationId("myID");
        assertEquals(targetElement,button.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Button);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetButton_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getButtonByAutomationId("unknownID");
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
    public void test_GetTab_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationTab tab = spyWndw.getTab(Pattern.compile("myName"));
        assertEquals(targetElement,tab.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Tab);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTab_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
    	when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
    	BaseAutomationTest.setElementCurrentName(elem, "myName");
        
        wndw.getTab(Pattern.compile("NNN"));
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
    public void test_GetEditBox_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationEditBox editBox = spyWndw.getEditBox("myName");
        assertEquals(targetElement,editBox.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Edit);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetEditBox_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getEditBox("unknownName");
    }

    @Test
    public void test_GetEditBox_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");
        
        AutomationEditBox editBox = spyWndw.getEditBox(Pattern.compile("m.*e"));
        assertEquals(targetElement,editBox.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Edit);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetEditBox_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getEditBox(Pattern.compile("\\s+"));
    }

    @Test
    public void test_GetEditBox_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationEditBox editBox = spyWndw.getEditBoxByAutomationId("myID");
        assertEquals(targetElement,editBox.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Edit);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetEditBox_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getEditBoxByAutomationId("unknownID");
    }
    @Test
    public void testGetToolBar_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationToolBar editBox = wndw.getToolBar(0);
        assertEquals(targetElement,editBox.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetToolBar_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationToolBar toolbar = spyWndw.getToolBar("myName");
        assertEquals(targetElement,toolbar.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.ToolBar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetToolBar_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getToolBar("unknownName");
    }

    @Test
    public void test_GetToolBar_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationToolBar toolbar = spyWndw.getToolBar(Pattern.compile("myName"));
        assertEquals(targetElement,toolbar.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.ToolBar);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetToolBar_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getToolBar(Pattern.compile("IsNotMyName"));
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
    public void test_GetComboBox_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationComboBox editBox = wndw.getComboBox(new Search.Builder().id(0).build());
        assertEquals(targetElement,editBox.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void test_GetComboBox_By_Index_Errors_When_Too_Big() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getComboBox(new Search.Builder().id(99).build());
    }

    @Test
    public void test_GetComboBox_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationComboBox comboBox = spyWndw.getComboBox(new Search.Builder().name("name").build());
        assertEquals(targetElement,comboBox.getElement());

        verify(spyWndw).createNamePropertyCondition("name");
        verify(spyWndw).createControlTypeCondition(ControlType.ComboBox);
        verify(element, atLeastOnce()).findFirst(any(TreeScope.class), any(PointerByReference.class));
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetComboBox_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getComboBox(new Search.Builder().name("unknownName").build());
    }
    
    @Test
    public void test_GetComboBox_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationComboBox comboBox = spyWndw.getComboBox(new Search.Builder().namePattern(Pattern.compile("myN.*")).build());
        assertEquals(targetElement,comboBox.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.ComboBox);
        verify(element, atLeastOnce()).findAll(any(TreeScope.class), any(PointerByReference.class));
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetComboBox_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getComboBox(new Search.Builder().namePattern(Pattern.compile("other")).build());
    }

    @Test
    public void test_GetComboBox_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationComboBox comboBox = spyWndw.getComboBox(new Search.Builder().automationId("myID").build());
        assertEquals(targetElement,comboBox.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.ComboBox);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetComboBox_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getComboBox(new Search.Builder().automationId("unknownID").build());
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

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCheckBox_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getCheckBox("unknownName");
    }
    
    @Test
    public void test_GetCheckBox_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationCheckBox checkBox = spyWndw.getCheckBox(Pattern.compile("myN.*"));
        assertEquals(targetElement,checkBox.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.CheckBox);
        verify(element, atLeastOnce()).findAll(any(TreeScope.class), any(PointerByReference.class));
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCheckBox_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getCheckBox(Pattern.compile("other"));
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
    public void test_GetRadioButton_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationRadioButton radio = spyWndw.getRadioButton(Pattern.compile("myName"));
        assertEquals(targetElement,radio.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.RadioButton);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetRadioButton_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getRadioButton(Pattern.compile("notMyName"));
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
    public void test_GetPanel_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationPanel panel = spyWndw.getPanel(Pattern.compile("myName"));
        assertEquals(targetElement,panel.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Pane);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPanel_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getPanel(Pattern.compile("IsNotmyName"));
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
        verify(spyWndw).createClassNamePropertyCondition("BlaBla");
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
    public void test_GetPanelByClassName_By_Name_with_RegExPattern() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationPanel panel = spyWndw.getPanelByClassName(Pattern.compile("myName"),"BlaBla");
        assertEquals(targetElement,panel.getElement());

        verify(spyWndw).createClassNamePropertyCondition("BlaBla");
        verify(spyWndw).createControlTypeCondition(ControlType.Pane);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPanelByClassName_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

    	when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getPanelByClassName(Pattern.compile("unkownName"),"BlaBla");
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
    public void test_GetDocument_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationDocument doc = spyWndw.getDocument(Pattern.compile("myName"));
        assertEquals(targetElement,doc.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Document);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDocument_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getDocument(Pattern.compile("NotmyName"));
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

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetProgress_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getProgressBar(99);
    }

    @Test
    public void test_GetProgresBar_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationProgressBar bar = spyWndw.getProgressBar("myName");
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.ProgressBar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetProgressBar_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getProgressBar("unknownName");
    }

    @Test
    public void test_GetProgresBar_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationProgressBar bar = spyWndw.getProgressBar(Pattern.compile("myName"));
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.ProgressBar);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetProgressBar_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getProgressBar(Pattern.compile("notmyName"));
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
    public void test_GetHyperlink_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationHyperlink link = spyWndw.getHyperlink(Pattern.compile("myName"));
        assertEquals(targetElement,link.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Hyperlink);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetHyperlink_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getHyperlink(Pattern.compile("NotmyName"));
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
    public void test_GetSlider_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationSlider slider = spyWndw.getSlider("myName");
        assertEquals(targetElement,slider.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Slider);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSlider_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getSlider("unknownName");
    }

    @Test
    public void test_GetSlider_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationSlider slider = spyWndw.getSlider(Pattern.compile("myName"));
        assertEquals(targetElement,slider.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Slider);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSlider_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getSlider(Pattern.compile("NotmyName"));
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
    public void test_GetCalendar_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationCalendar cal = spyWndw.getCalendar(Pattern.compile("myName"));
        assertEquals(targetElement,cal.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Calendar);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCalendar_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);
        BaseAutomationTest.setElementCurrentName(elem, "myName");
        
        wndw.getCalendar(Pattern.compile("NotmyName"));
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
    public void test_GetDataGrid_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationDataGrid edit = spyWndw.getDataGrid("myName");
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.DataGrid);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDataGrid_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getDataGrid("unknownName");
    }

    @Test
    public void test_GetDataGrid_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationDataGrid edit = spyWndw.getDataGrid(Pattern.compile("myName"));
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.DataGrid);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDataGrid_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getDataGrid(Pattern.compile("NotmyName"));
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

    @Test
    public void testGetDataGrid_By_Index_and_ControlName() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);
        BaseAutomationTest.setElementClassName(elem, "TXYZ");

        AutomationDataGrid dataGrid = wndw.getDataGrid(0, "TXYZ");
        assertEquals(targetElement,dataGrid.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testGetDataGrid_By_Index_and_ControlName_Throws_Exception_When_ClassDoesNotMatch() throws Exception {
        when(element.findAll(any(), any())).thenThrow(ElementNotFoundException.class);
        BaseAutomationTest.setElementClassName(elem, "TABC");

        wndw.getDataGrid(0, "TXYZ");
    }
    
    @Test(expected=ElementNotFoundException.class)
    public void testGetDataGrid_By_Index_and_ControlName_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);
        BaseAutomationTest.setElementClassName(elem, "TXYZ");

        wndw.getDataGrid(99, "TXYZ");
    }

    @Test
    public void test_GetDataGrid_By_Name_and_ControlName() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);
        BaseAutomationTest.setElementClassName(elem, "TXYZ");

        AutomationDataGrid edit = spyWndw.getDataGrid("myName", "TXYZ");
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.DataGrid);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void test_GetDataGrid_By_Name_and_ControlName_Throws_Exception_When_ClassDoesNotMatch() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);
        BaseAutomationTest.setElementClassName(elem, "TABC");

        spyWndw.getDataGrid("myName", "TXYZ");
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDataGrid_By_Name_and_ControlName_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getDataGrid("unknownName", "TXYZ");
    }
    
    @Test
    public void test_GetDataGrid_By_Name_with_RegExPattern_and_ControlName() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");
        BaseAutomationTest.setElementClassName(elem, "TXYZ");

        AutomationDataGrid edit = spyWndw.getDataGrid(Pattern.compile("myName"), "TXYZ");
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.DataGrid);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetDataGrid_By_Name_with_RegExPattern_and_ControlName_Throws_Exception_When_ClassDoesNotMatch() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");
        BaseAutomationTest.setElementClassName(elem, "TABC");

        spyWndw.getDataGrid(Pattern.compile("myName"), "TXYZ");
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDataGrid_By_Name_with_RegExPattern_and_ControlName_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");
        BaseAutomationTest.setElementClassName(elem, "TXYZ");

        wndw.getDataGrid(Pattern.compile("NotmyName"), "TXYZ");
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
    public void test_GetTreeView_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationTreeView treeView = spyWndw.getTreeView("myName");
        assertEquals(targetElement,treeView.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Tree);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTreeView_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getTreeView("unknownName");
    }

    @Test
    public void test_GetTreeView_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationTreeView treeView = spyWndw.getTreeView(Pattern.compile("myName"));
        assertEquals(targetElement,treeView.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Tree);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTreeView_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getTreeView(Pattern.compile("NotmyName"));
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
    public void test_GetPasswordEditBox_By_Name_with_RegExPattern() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "PasswordBox");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationPasswordEditBox box = spyWndw.getPasswordEditBox(Pattern.compile("(my)+Name"));
        assertEquals(targetElement,box.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Edit);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPasswordEditBox_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "PasswordBox");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getPasswordEditBox(Pattern.compile("otherName"));
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
    	setElementTypeAndClassName(elem, ControlType.Edit, "TAutomatedMaskEdit");
        BaseAutomationTest.setElementCurrentName(elem, "SMITH-01");

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
    public void testGetMaskedEdit_By_Name_with_RegExPattern_Calls_FindFirst_Once() throws Exception {
    	setElementTypeAndClassName(elem, ControlType.Edit, "TAutomatedMaskEdit");
        BaseAutomationTest.setElementCurrentName(elem, "SMITH-01");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationMaskedEdit maskedEdit = wndw.getMaskedEdit(Pattern.compile("SMITH-01"));
        assertEquals(targetElement,maskedEdit.element);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testGetMaskedEdit_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "TAutomatedMaskEdit");
        BaseAutomationTest.setElementCurrentName(elem, "myName");
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getMaskedEdit(Pattern.compile("SMITH-01"));
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
    public void test_GetList_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationList list = wndw.getList(Pattern.compile("myName"));
        assertEquals(targetElement,list.element);

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetList_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getList(Pattern.compile("NotmyName"));
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
    public void test_GetTextBox_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationTextBox textBox = spyWndw.getTextBox(Pattern.compile("myName"));
        assertEquals(targetElement,textBox.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Text);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTextBox_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getTextBox(Pattern.compile("NotmyName"));
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
    public void test_GetSplitButton_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationSplitButton btn = spyWndw.getSplitButton(Pattern.compile("myName"));
        assertEquals(targetElement,btn.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.SplitButton);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSplitButton_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getSplitButton(Pattern.compile("ThisIsNotMyName"));
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
    public void test_GetImage_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationImage img = spyWndw.getImage(Pattern.compile(".*yNa.*"));
        assertEquals(targetElement,img.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Image);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetImage_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getImage(Pattern.compile("blaBla"));
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
    public void test_GetSpinner_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationSpinner spin = spyWndw.getSpinner(Pattern.compile("myName"));
        assertEquals(targetElement,spin.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Spinner);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSpinner_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getSpinner(Pattern.compile("NotmyName"));
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
        verify(spyWndw).createClassNamePropertyCondition("ReBarWindow32");
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
    public void test_GetReBar_By_Name_with_RegExPattern() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "ReBarWindow32");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationReBar bar = spyWndw.getReBar(Pattern.compile("myName"));
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createClassNamePropertyCondition("ReBarWindow32");
        verify(spyWndw).createControlTypeCondition(ControlType.Pane);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetReBar_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "ReBarWindow32");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

    	when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getReBar(Pattern.compile("None"));
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
    public void test_GetCustom_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationCustom custom = spyWndw.getCustom(Pattern.compile("myName"));
        assertEquals(targetElement,custom.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Custom);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCustom_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getCustom(Pattern.compile("NotReallyMyName"));
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
        verify(spyWndw).createClassNamePropertyCondition("BlaBla");
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
    public void test_GetCustomByClassName_By_Name_with_RegExPattern() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationCustom bar = spyWndw.getCustomByClassName(Pattern.compile("myName"),"BlaBla");
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createClassNamePropertyCondition("BlaBla");
        verify(spyWndw).createControlTypeCondition(ControlType.Custom);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCustomByClassName_with_RegExPattern_By_Name_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

    	when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getCustomByClassName(Pattern.compile("NixMyName"),"BlaBla");
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
    public void test_GetPowerpointSlide_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationPowerpointSlide slide = spyWndw.getPowerpointSlide(Pattern.compile("myName"));
        assertEquals(targetElement,slide.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Custom);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPowerpointSlide_with_RegExPattern_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getPowerpointSlide(Pattern.compile("libreOfficePresenterSlide"));
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
    public void getControlByControlType_By_Name_with_RegExPattern() throws Exception {
    	BaseAutomationTest.setElementCurrentName(elem, "myName");
        setElementTypeAndClassName(elem, ControlType.Edit, "");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationBase bar = spyWndw.getControlByControlType(Pattern.compile("myName"),ControlType.Edit);
        assertEquals(targetElement,bar.getElement());
        assertEquals(AutomationEditBox.class,bar.getClass());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void getControlByControlType_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

    	when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getControlByControlType(Pattern.compile("myOtherName"),ControlType.Button);
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
    public void getControlByControlType_By_Name_and_ClassName_with_RegExPattern() throws Exception {
    	BaseAutomationTest.setElementCurrentName(elem, "myName");
        setElementTypeAndClassName(elem, ControlType.Button, "Blubber");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationBase bar = spyWndw.getControlByControlType(Pattern.compile("myName"),ControlType.Button, "Blubber");
        assertEquals(targetElement,bar.getElement());
        assertEquals(AutomationButton.class,bar.getClass());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void getControlByControlType_By_Name_and_ClassName_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
    	 when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getControlByControlType(Pattern.compile("myNameWrong"),ControlType.Calendar, "Blubber");
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
    public void getControlByName_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");
        setElementTypeAndClassName(elem, ControlType.Document, "");

        AutomationBase custom = spyWndw.getControlByName(Pattern.compile(".*Name"));
        assertEquals(targetElement,custom.getElement());
        assertEquals(AutomationDocument.class,custom.getClass());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void getControlByName_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");
        setElementTypeAndClassName(elem, ControlType.Document, "");
        
        wndw.getControlByName(Pattern.compile("yourName"));
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

    @Test
    public void getControlByClassName_By_Name_with_RegExPattern() throws Exception {
    	BaseAutomationTest.setElementCurrentName(elem, "myName");
        setElementTypeAndClassName(elem, ControlType.AppBar, "BlaBla");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationBase bar = spyWndw.getControlByClassName(Pattern.compile("myName"),"BlaBla");
        assertEquals(targetElement,bar.getElement());
        assertEquals(AutomationAppBar.class,bar.getClass());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void getControlByClassName_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

    	when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getControlByClassName(Pattern.compile("myNonsenseName"),"BlaBla");
    }
    
	private void setElementTypeAndClassName(IUIAutomationElement3 elem, ControlType controlType, String className) {
		BaseAutomationTest.answerIntByReference(controlType.getValue()).when(elem).getCurrentControlType(any());
		BaseAutomationTest.answerStringByReference(className).when(elem).getCurrentClassName(any());
	}
}

