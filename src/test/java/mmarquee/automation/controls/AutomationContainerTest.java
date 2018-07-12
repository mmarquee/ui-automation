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

import mmarquee.automation.uiautomation.IUIAutomationElement;
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

        wndw = new AutomationWindow(new ElementBuilder(element).addPattern(window, container));
        when(window.isAvailable()).thenReturn(true);
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
    IUIAutomationElement elem;
    
    List<AutomationElement> list;
    AutomationElement targetElement;
    
    @Test
    public void test_getEditBox_By_Index_Calls_findFirst_From_Element() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationEditBox edit = wndw.getEditBox(Search.getBuilder(0).build());
        assertEquals(targetElement,edit.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_getEditBox_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationEditBox edit = wndw.getEditBox(Search.getBuilder(1).build());
        assertEquals(targetElement,edit.getElement());
    }

    @Test
    public void test_getAppBar_By_Index_Calls_findFirst_From_Element() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationAppBar appBar = wndw.getAppBar(Search.getBuilder(0).build());
        assertEquals(targetElement,appBar.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetAppBar_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationAppBar bar = spyWndw.getAppBar(Search.getBuilder("myName").build());
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.AppBar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetAppBar_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getAppBar(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetAppBar_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationAppBar bar = spyWndw.getAppBar(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.AppBar);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetAppBar_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getAppBar(Search.getBuilder(Pattern.compile("NotmyName")).build());
    }
    
    @Test
    public void test_GetAppBar_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationAppBar bar = spyWndw.getAppBar(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.AppBar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetAppBar_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getAppBar(Search.getBuilder().automationId("unknownID").build());
    }

    @Test
    public void test_getSlider_By_Index_Calls_findFirst_From_Element() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationSlider slider = wndw.getSlider(Search.getBuilder(0).build());
        assertEquals(targetElement,slider.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_getButton_By_Index_Calls_findFirst_From_Element() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationButton btn = wndw.getButton(Search.getBuilder(0).build());
        assertEquals(targetElement,btn.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void Button() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getComboBox(Search.getBuilder(99).build());
    }

    @Test
    public void test_GetButton_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationButton button = spyWndw.getButton(Search.getBuilder("name").build());
        assertEquals(targetElement,button.getElement());

        verify(spyWndw).createNamePropertyCondition("name");
        verify(spyWndw).createControlTypeCondition(ControlType.Button);
        verify(element, atLeastOnce()).findFirst(any(TreeScope.class), any(PointerByReference.class));
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetButton_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getButton(Search.getBuilder("unknownName").build());
    }
    
    @Test
    public void test_GetButton_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationButton button = spyWndw.getButton(Search.getBuilder(Pattern.compile("myN.*")).build());
        assertEquals(targetElement,button.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Button);
        verify(element, atLeastOnce()).findAll(any(TreeScope.class), any(PointerByReference.class));
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetButton_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getButton(Search.getBuilder(Pattern.compile("other")).build());
    }

    @Test
    public void test_GetButton_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationButton button = spyWndw.getButton(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,button.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Button);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetButton_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getButton(Search.getBuilder().automationId("unknownID").build());
    }

    @Test
    public void testGetTab_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationTab tab = wndw.getTab(Search.getBuilder(0).build());

        assertTrue(tab != null);
        assertEquals(targetElement,tab.getElement());
        
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetTab_By_Index_Errors_When_Too_Big() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getTab(Search.getBuilder(99).build());
    }

    @Test
    public void test_GetTab_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationTab tab = spyWndw.getTab(Search.getBuilder("myName").build());
        assertEquals(targetElement,tab.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Tab);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTab_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getTab(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetTab_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationTab tab = spyWndw.getTab(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,tab.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Tab);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTab_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
    	when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
    	BaseAutomationTest.setElementCurrentName(elem, "myName");
        
        wndw.getTab(Search.getBuilder(Pattern.compile("NNN")).build());
    }
    
    @Test
    public void test_GetTab_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationTab tab = spyWndw.getTab(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,tab.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Tab);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTab_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getTab(Search.getBuilder().automationId("unknownID").build());
    }
    
    @Test
    public void testGetEditBox_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationEditBox editBox = wndw.getEditBox(Search.getBuilder(0).build());
        assertEquals(targetElement,editBox.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetEditBox_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationEditBox editBox = spyWndw.getEditBox(Search.getBuilder("myName").build());
        assertEquals(targetElement,editBox.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Edit);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetEditBox_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getEditBox(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetEditBox_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");
        
        AutomationEditBox editBox = spyWndw.getEditBox(Search.getBuilder(Pattern.compile("m.*e")).build());
        assertEquals(targetElement,editBox.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Edit);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetEditBox_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getEditBox(Search.getBuilder(Pattern.compile("\\s+")).build());
    }

    @Test
    public void test_GetEditBox_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationEditBox editBox = spyWndw.getEditBox(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,editBox.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Edit);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetEditBox_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getEditBox(Search.getBuilder().automationId("unknownID").build());
    }
    @Test
    public void testGetToolBar_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationToolBar editBox = wndw.getToolBar(Search.getBuilder(0).build());
        assertEquals(targetElement,editBox.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetToolBar_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationToolBar toolbar = spyWndw.getToolBar(Search.getBuilder("myName").build());
        assertEquals(targetElement,toolbar.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.ToolBar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetToolBar_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getToolBar(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetToolBar_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationToolBar toolbar = spyWndw.getToolBar(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,toolbar.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.ToolBar);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetToolBar_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getToolBar(Search.getBuilder(Pattern.compile("IsNotMyName")).build());
    }

    @Test
    public void test_GetToolBar_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationToolBar bar = spyWndw.getToolBar(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.ToolBar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetToolBar_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getToolBar(Search.getBuilder().automationId("unknownID").build());
    }

    @Test
    public void test_GetComboBox_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationComboBox editBox = wndw.getComboBox(Search.getBuilder(0).build());
        assertEquals(targetElement,editBox.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test (expected=IndexOutOfBoundsException.class)
    public void test_GetComboBox_By_Index_Errors_When_Too_Big() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getComboBox(Search.getBuilder(99).build());
    }

    @Test
    public void test_GetComboBox_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationComboBox comboBox = spyWndw.getComboBox(Search.getBuilder("name").build());
        assertEquals(targetElement,comboBox.getElement());

        verify(spyWndw).createNamePropertyCondition("name");
        verify(spyWndw).createControlTypeCondition(ControlType.ComboBox);
        verify(element, atLeastOnce()).findFirst(any(TreeScope.class), any(PointerByReference.class));
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetComboBox_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getComboBox(Search.getBuilder("unknownName").build());
    }
    
    @Test
    public void test_GetComboBox_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationComboBox comboBox = spyWndw.getComboBox(Search.getBuilder(Pattern.compile("myN.*")).build());
        assertEquals(targetElement,comboBox.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.ComboBox);
        verify(element, atLeastOnce()).findAll(any(TreeScope.class), any(PointerByReference.class));
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetComboBox_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getComboBox(Search.getBuilder(Pattern.compile("other")).build());
    }

    @Test
    public void test_GetComboBox_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationComboBox comboBox = spyWndw.getComboBox(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,comboBox.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.ComboBox);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetComboBox_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getComboBox(Search.getBuilder().automationId("unknownID").build());
    }
    
    @Test
    public void testGetCheckBox_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationCheckBox checkBox = wndw.getCheckBox(Search.getBuilder(0).build());
        assertEquals(targetElement,checkBox.getElement());

        verify(element, atLeastOnce()).findAll(any(TreeScope.class), any(PointerByReference.class));
    }

    @Test
    public void test_GetCheckBox_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationCheckBox checkBox = spyWndw.getCheckBox(Search.getBuilder("name").build());
        assertEquals(targetElement,checkBox.getElement());

        verify(spyWndw).createNamePropertyCondition("name");
        verify(spyWndw).createControlTypeCondition(ControlType.CheckBox);
        verify(element, atLeastOnce()).findFirst(any(TreeScope.class), any(PointerByReference.class));
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCheckBox_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getCheckBox(Search.getBuilder("unknownName").build());
    }
    
    @Test
    public void test_GetCheckBox_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationCheckBox checkBox = spyWndw.getCheckBox(Search.getBuilder(Pattern.compile("myN.*")).build());
        assertEquals(targetElement,checkBox.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.CheckBox);
        verify(element, atLeastOnce()).findAll(any(TreeScope.class), any(PointerByReference.class));
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCheckBox_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getCheckBox(Search.getBuilder(Pattern.compile("other")).build());
    }
    
    @Test
    public void test_GetCheckBox_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationCheckBox checkBox = spyWndw.getCheckBox(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,checkBox.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.CheckBox);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCheckBox_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getCheckBox(Search.getBuilder().automationId("unknown").build());
    }
    

    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetRadioButton_By_Index_Fails_When_Index_No_Present() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getRadioButton(Search.getBuilder(99).build());
    }

    @Test
    public void testGetRadioButton_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationRadioButton radio = wndw.getRadioButton(Search.getBuilder(0).build());
        assertEquals(targetElement,radio.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetRadioButton_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationRadioButton radio = spyWndw.getRadioButton(Search.getBuilder("myName").build());
        assertEquals(targetElement,radio.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.RadioButton);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetRadioButton_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getRadioButton(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetRadioButton_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationRadioButton radio = spyWndw.getRadioButton(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,radio.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.RadioButton);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetRadioButton_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getRadioButton(Search.getBuilder(Pattern.compile("notMyName")).build());
    }
    
    @Test
    public void test_GetRadioButton_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationRadioButton radio = spyWndw.getRadioButton(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,radio.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.RadioButton);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetRadioButton_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getRadioButton(Search.getBuilder("unknownID").build());
    }

    @Test
    public void testGetPanel_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationPanel panel = wndw.getPanel(Search.getBuilder(0).build());
        assertEquals(targetElement,panel.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetPanel_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationPanel panel = spyWndw.getPanel(Search.getBuilder("myName").build());
        assertEquals(targetElement,panel.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Pane);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPanel_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getPanel(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetPanel_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationPanel panel = spyWndw.getPanel(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,panel.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Pane);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPanel_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getPanel(Search.getBuilder(Pattern.compile("IsNotmyName")).build());
    }

    @Test
    public void test_GetPanel_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationPanel panel = spyWndw.getPanel(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,panel.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Pane);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPanel_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getPanel(Search.getBuilder().automationId("unknownID").build());
    }


    @Test
    public void test_GetPanelByClassName_By_Index() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationPanel bar = spyWndw.getPanel(Search.getBuilder(0).className("BlaBla").build());
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createIntegerVariant(ControlType.Pane.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPanelByClassName_By_Index_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getPanel(Search.getBuilder(0).className("BlaBla").build());
    }
    
    @Test
    public void test_GetPanelByClassName_By_Name() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationPanel panel = spyWndw.getPanel(Search.getBuilder("myName").className("BlaBla").build());
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

        wndw.getPanel(Search.getBuilder("unknownName").className("BlaBla").build());
    }
    
    @Test
    public void test_GetPanelByClassName_By_Name_with_RegExPattern() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationPanel panel = spyWndw.getPanel(Search.getBuilder(Pattern.compile("myName")).className("BlaBla").build());
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

        wndw.getPanel(Search.getBuilder(Pattern.compile("unknownName")).className("BlaBla").build());
    }
    
    @Test
    public void testGetDocument_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationDocument doc = wndw.getDocument(Search.getBuilder(0).build());
        assertEquals(targetElement,doc.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetDocument_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationDocument doc = spyWndw.getDocument(Search.getBuilder("myName").build());
        assertEquals(targetElement,doc.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Document);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDocument_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getDocument(Search.getBuilder("unknownName").build());
    }
    
    @Test
    public void test_GetDocument_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationDocument doc = spyWndw.getDocument(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,doc.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Document);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDocument_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getDocument((Search.getBuilder(Pattern.compile("NotmyName")).build()));
    }
    
    @Test
    public void test_GetDocument_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationDocument doc = spyWndw.getDocument((Search.getBuilder().automationId("myID").build()));
        assertEquals(targetElement,doc.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Document);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDocument_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getDocument(Search.getBuilder().automationId("unknownID").build());
    }
    
    @Test
    public void testGetProgress_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationProgressBar progress = wndw.getProgressBar(Search.getBuilder(0).build());
        assertEquals(targetElement,progress.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetProgress_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getProgressBar(Search.getBuilder(99).build());
    }

    @Test
    public void test_GetProgresBar_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationProgressBar bar = spyWndw.getProgressBar(Search.getBuilder("myName").build());
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.ProgressBar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetProgressBar_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getProgressBar(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetProgresBar_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationProgressBar bar = spyWndw.getProgressBar(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.ProgressBar);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetProgressBar_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getProgressBar(Search.getBuilder(Pattern.compile("notmyName")).build());
    }
    
    @Test
    public void test_GetProgresBar_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationProgressBar bar = spyWndw.getProgressBar(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.ProgressBar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetProgressBar_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getProgressBar(Search.getBuilder().automationId("unknownID").build());
    }


    @Test
    public void testGetHyperlink_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationHyperlink link = wndw.getHyperlink(Search.getBuilder(0).build());
        assertEquals(targetElement,link.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }
    
    @Test (expected=IndexOutOfBoundsException.class)
    public void testGetHyperlink_By_Index_Fails_When_Index_No_Present() throws Exception {
    	when(element.findAll(any(), any())).thenReturn(list);
    	
    	wndw.getHyperlink(Search.getBuilder(99).build());
    }

    @Test
    public void test_GetHyperlink_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationHyperlink link = spyWndw.getHyperlink(Search.getBuilder("myName").build());
        assertEquals(targetElement,link.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Hyperlink);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetHyperlink_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getHyperlink(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetHyperlink_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationHyperlink link = spyWndw.getHyperlink(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,link.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Hyperlink);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetHyperlink_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getHyperlink(Search.getBuilder(Pattern.compile("NotmyName")).build());
    }
    
    @Test
    public void test_GetHyperlink_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationHyperlink link = spyWndw.getHyperlink(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,link.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Hyperlink);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetHyperlink_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getHyperlink(Search.getBuilder().automationId("unknownID").build());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetToolbar_By_Index_Fails_When_Not_Found() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getHyperlink(Search.getBuilder(99).build());
    }

    @Test
    public void testGetToolbar_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationToolBar toolBar = wndw.getToolBar(Search.getBuilder(0).build());
        assertEquals(targetElement,toolBar.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetSlider_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getSlider(Search.getBuilder(99).build());
    }

    @Test
    public void test_GetSlider_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationSlider slider = spyWndw.getSlider(Search.getBuilder("myName").build());
        assertEquals(targetElement,slider.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Slider);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSlider_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getSlider(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetSlider_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationSlider slider = spyWndw.getSlider(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,slider.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Slider);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSlider_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getSlider(Search.getBuilder(Pattern.compile("NotmyName")).build());
    }
    
    @Test
    public void test_GetSlider_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationSlider slider = spyWndw.getSlider(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,slider.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Slider);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSlider_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getSlider(Search.getBuilder().automationId("unknownID").build());
    }

    @Test
    public void testGetCalendar_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);
        
        AutomationCalendar calendar = wndw.getCalendar(Search.getBuilder(0).build());
        assertEquals(targetElement,calendar.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetCalendar_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);

        wndw.getCalendar(Search.getBuilder(99).build());
    }

    @Test
    public void test_GetCalendar_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);
        
        AutomationCalendar cal = spyWndw.getCalendar(Search.getBuilder("myName").build());
        assertEquals(targetElement,cal.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Calendar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCalendar_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);
        
        wndw.getCalendar(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetCalendar_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationCalendar cal = spyWndw.getCalendar(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,cal.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Calendar);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCalendar_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);
        BaseAutomationTest.setElementCurrentName(elem, "myName");
        
        wndw.getCalendar(Search.getBuilder(Pattern.compile("NotmyName")).build());
    }
    
    @Test
    public void test_GetCalendar_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);

        AutomationCalendar cal = spyWndw.getCalendar(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,cal.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Calendar);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCalendar_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());
        BaseAutomationTest.setElementPropertyValue(elem, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);

        wndw.getCalendar(Search.getBuilder().automationId("unknownID").build());
    }

    @Test
    public void testGetDataGrid_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationDataGrid dataGrid = wndw.getDataGrid(Search.getBuilder(0).build());
        assertEquals(targetElement,dataGrid.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testGetDataGrid_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getDataGrid(Search.getBuilder(99).build());
    }

    @Test
    public void test_GetDataGrid_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationDataGrid edit = spyWndw.getDataGrid(Search.getBuilder("myName").build());
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.DataGrid);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDataGrid_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getDataGrid(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetDataGrid_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationDataGrid edit = spyWndw.getDataGrid(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.DataGrid);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDataGrid_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getDataGrid(Search.getBuilder(Pattern.compile("NotmyName")).build());
    }
    
    @Test
    public void test_GetDataGrid_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationDataGrid edit = spyWndw.getDataGrid(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.DataGrid);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDataGrid_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getDataGrid(Search.getBuilder().automationId("unknownID").build());
    }

    @Test
    public void testGetDataGrid_By_Index_and_ControlName() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);
        BaseAutomationTest.setElementClassName(elem, "TXYZ");

        AutomationDataGrid dataGrid = wndw.getDataGrid(Search.getBuilder(0).className("TXYZ").build());
        assertEquals(targetElement,dataGrid.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testGetDataGrid_By_Index_and_ControlName_Throws_Exception_When_ClassDoesNotMatch() throws Exception {
        when(element.findAll(any(), any())).thenThrow(ElementNotFoundException.class);
        BaseAutomationTest.setElementClassName(elem, "TABC");

        wndw.getDataGrid(Search.getBuilder(0).className("TXYZ").build());
    }
    
    @Test(expected=ElementNotFoundException.class)
    public void testGetDataGrid_By_Index_and_ControlName_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);
        BaseAutomationTest.setElementClassName(elem, "TXYZ");

        wndw.getDataGrid(Search.getBuilder(99).className("TXYZ").build());
    }

    @Test
    public void test_GetDataGrid_By_Name_and_ControlName() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);
        BaseAutomationTest.setElementClassName(elem, "TXYZ");

        AutomationDataGrid edit = spyWndw.getDataGrid(Search.getBuilder("myName").className("TXYZ").build());
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.DataGrid);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void test_GetDataGrid_By_Name_and_ControlName_Throws_Exception_When_ClassDoesNotMatch() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);
        BaseAutomationTest.setElementClassName(elem, "TABC");

        spyWndw.getDataGrid(Search.getBuilder("myName").className("TXYZ").build());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDataGrid_By_Name_and_ControlName_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getDataGrid(Search.getBuilder("notMyName").className("TXYZ").build());
    }
    
    @Test
    public void test_GetDataGrid_By_Name_with_RegExPattern_and_ControlName() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");
        BaseAutomationTest.setElementClassName(elem, "TXYZ");

        AutomationDataGrid edit = spyWndw.getDataGrid(Search.getBuilder(Pattern.compile("myName")).className("TXYZ").build());
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.DataGrid);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetDataGrid_By_Name_with_RegExPattern_and_ControlName_Throws_Exception_When_ClassDoesNotMatch() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");
        BaseAutomationTest.setElementClassName(elem, "TABC");

        spyWndw.getDataGrid(Search.getBuilder(Pattern.compile("myName")).className("TXYZ").build());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetDataGrid_By_Name_with_RegExPattern_and_ControlName_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");
        BaseAutomationTest.setElementClassName(elem, "TXYZ");

        wndw.getDataGrid(Search.getBuilder(Pattern.compile("NotMyName")).className("TXYZ").build());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void testTreeView_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getTreeView(Search.getBuilder(99).build());
    }

    @Test
    public void testTreeView_By_Index() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationTreeView treeView = wndw.getTreeView(Search.getBuilder(0).build());
        assertEquals(targetElement,treeView.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }


    @Test
    public void test_GetTreeView_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationTreeView treeView = spyWndw.getTreeView(Search.getBuilder("myName").build());
        assertEquals(targetElement,treeView.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Tree);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTreeView_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getTreeView(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetTreeView_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationTreeView treeView = spyWndw.getTreeView(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,treeView.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Tree);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTreeView_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getTreeView(Search.getBuilder(Pattern.compile("NotmyName")).build());
    }
    
    @Test
    public void test_GetTreeView_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationTreeView edit = spyWndw.getTreeView(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Tree);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTreeView_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getTreeView(Search.getBuilder().automationId("unknownID").build());
    }

    @Test
    public void testGetPasswordEditBox() throws Exception {
        BaseAutomationTest.setElementClassName(elem, "PasswordBox");

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationEditBox passwordEditBox = wndw.getPasswordEditBox(Search.getBuilder(0).build());
        assertEquals(targetElement,passwordEditBox.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }
    
    @Test(expected=ElementNotFoundException.class)
    public void testGetPasswordEditBox_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        BaseAutomationTest.setElementClassName(elem, "PasswordBox");

        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getPasswordEditBox(Search.getBuilder(99).build());
    }

    @Test
    public void test_GetPasswordEditBox_By_Name() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "PasswordBox");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationPasswordEditBox box = spyWndw.getPasswordEditBox(Search.getBuilder("myName").build());
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

        wndw.getPasswordEditBox(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetPasswordEditBox_By_Name_with_RegExPattern() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "PasswordBox");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationPasswordEditBox box = spyWndw.getPasswordEditBox(Search.getBuilder(Pattern.compile("(my)+Name")).build());
        assertEquals(targetElement,box.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Edit);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPasswordEditBox_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "PasswordBox");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getPasswordEditBox(Search.getBuilder(Pattern.compile("otherName")).build());
    }

    @Test
    public void test_GetPassword_By_AutomationId() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "PasswordBox");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationPasswordEditBox edit = spyWndw.getPasswordEditBox(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Edit);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPassword_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "PasswordBox");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getPasswordEditBox(Search.getBuilder().automationId("unknownID").build());
    }

    @Test
    public void testGetMaskedEdit_By_Index() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "TAutomatedMaskEdit");

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationMaskedEdit maskedEdit = wndw.getMaskedEdit(Search.getBuilder(0).build());
        assertEquals(targetElement,maskedEdit.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testGetMaskedEdit_By_Index_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "TAutomatedMaskEdit");

        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getMaskedEdit(Search.getBuilder(99).build());
    }

    @Test
    public void testGetMaskedEdit_By_Name_Calls_FindFirst_Once() throws Exception {
    	setElementTypeAndClassName(elem, ControlType.Edit, "TAutomatedMaskEdit");
        BaseAutomationTest.setElementCurrentName(elem, "SMITH-01");

        when(element.findFirst(any(), any())).thenReturn(targetElement);

        AutomationMaskedEdit maskedEdit = wndw.getMaskedEdit(Search.getBuilder("SMITH-01").build());
        assertEquals(targetElement,maskedEdit.getElement());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testGetMaskedEdit_By_Name_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "TAutomatedMaskEdit");

        when(element.findFirst(any(), any())).thenReturn(null);

        wndw.getMaskedEdit(Search.getBuilder("SMITH-01").build());
    }

    @Test
    public void testGetMaskedEdit_By_Name_with_RegExPattern_Calls_FindFirst_Once() throws Exception {
    	setElementTypeAndClassName(elem, ControlType.Edit, "TAutomatedMaskEdit");
        BaseAutomationTest.setElementCurrentName(elem, "SMITH-01");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationMaskedEdit maskedEdit = wndw.getMaskedEdit(Search.getBuilder(Pattern.compile("SMITH-01")).build());
        assertEquals(targetElement,maskedEdit.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void testGetMaskedEdit_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "TAutomatedMaskEdit");
        BaseAutomationTest.setElementCurrentName(elem, "myName");
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getMaskedEdit(Search.getBuilder(Pattern.compile("SMITH-01")).build());
    }

    @Test
    public void test_GetMaskedEdit_By_AutomationId() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "TAutomatedMaskEdit");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationMaskedEdit edit = spyWndw.getMaskedEdit(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Edit);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetMaskedEdit_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "TAutomatedMaskEdit");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getMaskedEdit(Search.getBuilder().automationId("unknownID").build());
    }

    @Test
    public void test_PasswordBox_By_Index() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "PasswordBox");

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationEditBox passwordEditBox = wndw.getPasswordEditBox(Search.getBuilder(0).build());
        assertEquals(targetElement,passwordEditBox.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_PasswordBox_By_Index_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "PasswordBox");

        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getPasswordEditBox(Search.getBuilder(99).build());
    }

    @Test
    public void testGetRibbonBar() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "UIRibbonCommandBarDock");
     
        AutomationElement el = Mockito.mock(AutomationElement.class);
        el.setElement(elem);

        when(el.findAll(any(), any())).thenReturn(list);

        AutomationWindow wndw = new AutomationWindow(new ElementBuilder(el).addPattern(window, container));
        AutomationRibbonBar ribbonBar = wndw.getRibbonBar();
        assertEquals(targetElement,ribbonBar.getElement());

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

        AutomationList list = wndw.getList(Search.getBuilder(0).build());
        assertEquals(targetElement,list.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetList_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        wndw.getList(Search.getBuilder(99).build());
    }

    @Test
    public void test_GetList_By_Name() throws Exception {
        when(element.findFirst(any(), any())).thenReturn(targetElement);

        AutomationList list = wndw.getList(Search.getBuilder("myName").build());
        assertEquals(targetElement,list.getElement());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetList_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(any(), any())).thenThrow(new ElementNotFoundException());

        wndw.getList(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetList_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationList list = wndw.getList(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,list.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetList_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getList(Search.getBuilder(Pattern.compile("NotmyName")).build());
    }

    @Test
    public void test_GetList_By_AutomationId() throws Exception {
        when(element.findFirst(any(), any())).thenReturn(targetElement);

        AutomationList list = wndw.getList(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,list.getElement());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetList_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(any(), any())).thenThrow(new ElementNotFoundException());

        wndw.getList(Search.getBuilder().automationId("unknownID").build());
    }

    /************************************************************************************
     *
     *          getTextBox
     *
     ***********************************************************************************/

    @Test
    public void test_GetTextBox_By_Index() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);

        AutomationTextBox textBox = spyWndw.getTextBox(Search.getBuilder(0).build());
        assertEquals(targetElement,textBox.getElement());

        verify(spyWndw).createIntegerVariant(ControlType.Text.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetTextBox_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getTextBox(Search.getBuilder(99).build());
    }

    @Test
    public void test_GetTextBox_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationTextBox textBox = spyWndw.getTextBox(Search.getBuilder("myName").build());
        assertEquals(targetElement,textBox.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Text);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTextBox_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getTextBox(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetTextBox_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationTextBox textBox = spyWndw.getTextBox(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,textBox.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Text);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTextBox_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getTextBox(Search.getBuilder(Pattern.compile("NotmyName")).build());
    }
    
    @Test
    public void test_GetTextBox_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationTextBox textBox = spyWndw.getTextBox(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,textBox.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Text);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetTextBox_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getTextBox(Search.getBuilder().automationId("unknownID").build());
    }

    @Test
    public void test_GetSplitButton_By_Index() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);

        AutomationSplitButton btn = spyWndw.getSplitButton(Search.getBuilder(0).build());
        assertEquals(targetElement,btn.getElement());

        verify(spyWndw).createIntegerVariant(ControlType.SplitButton.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetSpliztButton_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getSplitButton(Search.getBuilder(99).build());
    }
    
    @Test
    public void test_GetSplitButton_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationSplitButton btn = spyWndw.getSplitButton(Search.getBuilder("myName").build());
        assertEquals(targetElement,btn.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.SplitButton);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSplitButton_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getSplitButton(Search.getBuilder("unknownName").build());
    }
    
    @Test
    public void test_GetSplitButton_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationSplitButton btn = spyWndw.getSplitButton(Search.getBuilder(Pattern.compile("myName")).build());

        assertEquals(targetElement,btn.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.SplitButton);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSplitButton_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getSplitButton(Search.getBuilder(Pattern.compile("ThisIsNotMyName")).build());
    }
    
    @Test
    public void test_GetSplitButton_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationSplitButton btn = spyWndw.getSplitButton(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,btn.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.SplitButton);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSplitButton_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getSplitButton(Search.getBuilder().automationId("unknownID").build());
    }


    @Test
    public void test_GetImage_By_Index() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);

        AutomationImage img = spyWndw.getImage(Search.getBuilder(0).build());
        assertEquals(targetElement, img.getElement());

        verify(spyWndw).createIntegerVariant(ControlType.Image.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetImage_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getImage(Search.getBuilder(99).build());
    }
    
    @Test
    public void test_GetImage_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationImage img = spyWndw.getImage(Search.getBuilder("myName").build());
        assertEquals(targetElement,img.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Image);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetImage_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getImage(Search.getBuilder("unknownName").build());
    }
    
    @Test
    public void test_GetImage_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationImage img = spyWndw.getImage(Search.getBuilder(Pattern.compile(".*yNa.*")).build());
        assertEquals(targetElement,img.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Image);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetImage_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getImage(Search.getBuilder(Pattern.compile("blaBla")).build());
    }
    
    @Test
    public void test_GetImage_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationImage img = spyWndw.getImage(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,img.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Image);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetImage_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getImage(Search.getBuilder().automationId("unknownID").build());
    }

    @Test
    public void test_GetSpinner_By_Index() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);

        AutomationSpinner spin = spyWndw.getSpinner(Search.getBuilder(0).build());
        assertEquals(targetElement, spin.getElement());

        verify(spyWndw).createIntegerVariant(ControlType.Spinner.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetSpinner_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getSpinner(Search.getBuilder(99).build());
    }
    
    @Test
    public void test_GetSpinner_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationSpinner spin = spyWndw.getSpinner(Search.getBuilder("myName").build());
        assertEquals(targetElement, spin.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Spinner);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSpinner_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getSpinner(Search.getBuilder("unknownName").build());
    }
    
    @Test
    public void test_GetSpinner_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationSpinner spin = spyWndw.getSpinner(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,spin.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Spinner);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSpinner_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getSpinner(Search.getBuilder(Pattern.compile("notmyName")).build());
    }
    
    @Test
    public void test_GetSpinner_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationSpinner spinner = spyWndw.getSpinner(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,spinner.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Spinner);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetSpinner_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getSpinner(Search.getBuilder("unknownID").build());
    }

    @Test
    public void test_GetReBar_By_Index() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "ReBarWindow32");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationReBar bar = spyWndw.getReBar(Search.getBuilder(0).build());
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createIntegerVariant(ControlType.Pane.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetreBar_By_Index_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "ReBarWindow32");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getReBar(Search.getBuilder(99).build());
    }
    
    @Test
    public void test_GetReBar_By_Name() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "ReBarWindow32");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationReBar bar = spyWndw.getReBar(Search.getBuilder("myName").build());
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

        wndw.getReBar(Search.getBuilder("unknownName").build());
    }
    
    
    @Test
    public void test_GetReBar_By_Name_with_RegExPattern() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "ReBarWindow32");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationReBar bar = spyWndw.getReBar(Search.getBuilder(Pattern.compile("myName")).build());
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

        wndw.getReBar(Search.getBuilder(Pattern.compile("None")).build());
    }
    
    @Test
    public void test_GetReBar_By_AutomationId() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "ReBarWindow32");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationReBar edit = spyWndw.getReBar(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,edit.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Pane);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetReBar_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "ReBarWindow32");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getReBar(Search.getBuilder().automationId("unknownID").build());
    }

    @Test
    public void test_GetCustom_By_Index() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);

        AutomationCustom custom = spyWndw.getCustom(Search.getBuilder(0).build());
        assertEquals(targetElement, custom.getElement());

        verify(spyWndw).createIntegerVariant(ControlType.Custom.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetCustom_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getCustom(Search.getBuilder(99).build());
    }

    @Test
    public void test_GetCustom_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationCustom custom = spyWndw.getCustom(Search.getBuilder("myName").build());
        assertEquals(targetElement,custom.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Custom);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCustom_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getCustom(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetCustom_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationCustom custom = spyWndw.getCustom(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,custom.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Custom);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCustom_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getCustom(Search.getBuilder(Pattern.compile("NotReallyMyName")).build());
    }

    @Test
    public void test_GetCustom_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationCustom custom = spyWndw.getCustom(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,custom.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Custom);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCustom_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getCustom(Search.getBuilder().automationId("unknownID").build());
    }

    @Test
    public void test_GetCustomByClassName_By_Index() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationCustom bar = spyWndw.getCustom(Search.getBuilder(0).className("BlaBla").build());
        assertEquals(targetElement,bar.getElement());

        verify(spyWndw).createIntegerVariant(ControlType.Custom.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetCustomByClassName_By_Index_Throws_Exception_When_Not_found() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getCustom(Search.getBuilder(99).className("BlaBla").build());
    }
    
    @Test
    public void test_GetCustomByClassName_By_Name() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationCustom bar = spyWndw.getCustom(Search.getBuilder("myName").className("BlaBla").build());
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

        wndw.getCustom(Search.getBuilder("unknownName").className("BlaBla").build());
    }
    
    @Test
    public void test_GetCustomByClassName_By_Name_with_RegExPattern() throws Exception {
    	BaseAutomationTest.setElementClassName(elem, "BlaBla");
    	BaseAutomationTest.setElementCurrentName(elem, "myName");

        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        AutomationCustom bar = spyWndw.getCustom(Search.getBuilder(Pattern.compile("myName")).className("BlaBla").build());
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

        wndw.getCustom(Search.getBuilder(Pattern.compile("NixMyName")).className("BlaBla").build());
    }
    

    @Test
    public void test_GetPowerpointSlide_By_Index() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);

        AutomationPowerpointSlide slide = spyWndw.getPowerpointSlide(Search.getBuilder(0).build());
        assertEquals(targetElement, slide.getElement());

        verify(spyWndw).createIntegerVariant(ControlType.Custom.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetPowerpointSlide_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        wndw.getPowerpointSlide(Search.getBuilder(99).build());
    }
    
    @Test
    public void test_GetPowerpointSlide_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationPowerpointSlide slide = spyWndw.getPowerpointSlide(Search.getBuilder("myName").build());
        assertEquals(targetElement,slide.getElement());

        verify(spyWndw).createNamePropertyCondition("myName");
        verify(spyWndw).createControlTypeCondition(ControlType.Custom);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPowerpointSlide_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getPowerpointSlide(Search.getBuilder("unknownName").build());
    }
    
    @Test
    public void test_GetPowerpointSlide_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        AutomationPowerpointSlide slide = spyWndw.getPowerpointSlide(Search.getBuilder(Pattern.compile("myName")).build());
        assertEquals(targetElement,slide.getElement());

        verify(spyWndw).createControlTypeCondition(ControlType.Custom);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPowerpointSlide_with_RegExPattern_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        BaseAutomationTest.setElementCurrentName(elem, "myName");

        wndw.getPowerpointSlide(Search.getBuilder(Pattern.compile("libreOfficePresenterSlide")).build());
    }

    @Test
    public void test_GetPowerpointSlide_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationPowerpointSlide slide = spyWndw.getPowerpointSlide(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,slide.getElement());

        verify(spyWndw).createAutomationIdPropertyCondition("myID");
        verify(spyWndw).createControlTypeCondition(ControlType.Custom);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetPowerpointSlide_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        wndw.getPowerpointSlide(Search.getBuilder().automationId("unknownID").build());
    }

    @Test
    public void getControlByControlType_By_Index() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);
        setElementTypeAndClassName(elem, ControlType.Slider, "");

        AutomationBase custom = spyWndw.getControlByControlType(0, ControlType.Slider);
        assertEquals(targetElement, custom.getElement());
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
        assertEquals(targetElement, custom.getElement());
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
    
	private void setElementTypeAndClassName(IUIAutomationElement elem, ControlType controlType, String className) {
		BaseAutomationTest.answerIntByReference(controlType.getValue()).when(elem).getCurrentControlType(any());
		BaseAutomationTest.answerStringByReference(className).when(elem).getCurrentClassName(any());
	}
}

