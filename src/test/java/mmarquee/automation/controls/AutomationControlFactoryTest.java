/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mmarquee.automation.controls;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import mmarquee.automation.uiautomation.IUIAutomationElement;
import org.apache.log4j.Logger;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.sun.jna.platform.win32.Variant;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.ControlType;
import mmarquee.automation.PropertyID;
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.controls.menu.AutomationMenu;
import mmarquee.automation.controls.menu.AutomationMenuItem;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * @author Mark Humphreys
 * Date 28/11/2016.
 *
 * Tests for AutomationBase class
 */
public class AutomationControlFactoryTest {

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    protected Logger logger = Logger.getLogger(AutomationControlFactoryTest.class.getName());
	
    private AutomationBase parent;
    private AutomationElement parentElement;
	private AutomationElement element;
	private IUIAutomationElement iuielement;

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }
    
    @Before
    public void setUp() {
		parent = Mockito.mock(AutomationBase.class);
		parentElement = Mockito.mock(AutomationElement.class);
		when(parent.getElement()).thenReturn(parentElement);
		iuielement = Mockito.mock(IUIAutomationElement.class);
        element = new AutomationElement(iuielement);
    }
    

    @Test
    public void get_returns_AutomationContainer_on_None() throws Exception {
        testGet(ControlType.None, AutomationContainer.class);
    }

    @Test
    public void get_returns_AutomationButton_on_Button() throws Exception {
        testGet(ControlType.Button, AutomationButton.class);
    }

    @Test
    public void get_returns_AutomationCalendar_on_Calendar() throws Exception {
    	BaseAutomationTest.setElementPropertyValue(iuielement, PropertyID.IsValuePatternAvailable, Variant.VT_INT, 0);
    	testGet(ControlType.Calendar, AutomationCalendar.class);
    }

    @Test
    public void get_returns_AutomationCheckBox_on_CheckBox() throws Exception {
        testGet(ControlType.CheckBox, AutomationCheckBox.class);
    }

    @Test
    public void get_returns_AutomationComboBox_on_ComboBox() throws Exception {
        testGet(ControlType.ComboBox, AutomationComboBox.class);
    }

    @Test
    public void get_returns_AutomationEditBox_on_Edit() throws Exception {
        testGet(ControlType.Edit, AutomationEditBox.class);
    }

    @Test
    public void get_returns_AutomationPasswordEditBox_on_PasswordEdit() throws Exception {
        testGet(ControlType.Edit, AutomationPasswordEditBox.CLASS_NAME, AutomationPasswordEditBox.class);
    }

    @Test
    public void get_returns_AutomationMaskedEdit_on_MaskedEdit() throws Exception {
        testGet(ControlType.Edit,AutomationMaskedEdit.CLASS_NAME, AutomationMaskedEdit.class);
    }

    @Test
    public void get_returns_AutomationHyperlink_on_Hyperlink() throws Exception {
        testGet(ControlType.Hyperlink, AutomationHyperlink.class);
    }

    @Test
    public void get_returns_AutomationImage_on_Image() throws Exception {
        testGet(ControlType.Image, AutomationImage.class);
    }

    @Test
    public void get_returns_AutomationListItem_on_ListItem() throws Exception {
        testGet(ControlType.ListItem, AutomationListItem.class);
    }

    @Test
    public void get_returns_AutomationList_on_List() throws Exception {
        testGet(ControlType.List, AutomationList.class);
    }

    @Test
    public void get_returns_AutomationMenu_on_Menu() throws Exception {
        testGet(ControlType.Menu, AutomationMenu.class);
    }

    @Test
    public void get_returns_AutomationMainMenu_on_MenuBar() throws Exception {
        AutomationBase control = testGet(ControlType.MenuBar, AutomationMainMenu.class);
        assertEquals(parentElement, ((AutomationMainMenu) control ).getParentElement());
    }

    @Test
    public void get_returns_AutomationMenuItem_on_MenuItem() throws Exception {
        testGet(ControlType.MenuItem, AutomationMenuItem.class);
    }

    @Test
    public void get_returns_AutomationProgressBar_on_ProgressBar() throws Exception {
        testGet(ControlType.ProgressBar, AutomationProgressBar.class);
    }

    @Test
    public void get_returns_AutomationRadioButton_on_RadioButton() throws Exception {
        testGet(ControlType.RadioButton, AutomationRadioButton.class);
    }

    @Test
    public void get_returns_AutomationContainer_on_ScrollBar() throws Exception {
        testGet(ControlType.ScrollBar, AutomationContainer.class);
    }

    @Test
    public void get_returns_AutomationSlider_on_Slider() throws Exception {
        testGet(ControlType.Slider, AutomationSlider.class);
    }

    @Test
    public void get_returns_AutomationSpinner_on_Spinner() throws Exception {
        testGet(ControlType.Spinner, AutomationSpinner.class);
    }

    @Test
    public void get_returns_AutomationStatusBar_on_StatusBar() throws Exception {
        testGet(ControlType.StatusBar, AutomationStatusBar.class);
    }

    @Test
    public void get_returns_AutomationTab_on_Tab() throws Exception {
        testGet(ControlType.Tab, AutomationTab.class);
    }

    @Test
    public void get_returns_AutomationTabItem_on_TabItem() throws Exception {
        testGet(ControlType.TabItem, AutomationTabItem.class);
    }

    @Test
    public void get_returns_AutomationTextBox_on_Text() throws Exception {
        testGet(ControlType.Text, AutomationTextBox.class);
    }

    @Test
    public void get_returns_AutomationToolBar_on_ToolBar() throws Exception {
        testGet(ControlType.ToolBar, AutomationToolBar.class);
    }

    @Test
    public void get_returns_AutomationContainer_on_ToolTip() throws Exception {
        testGet(ControlType.ToolTip, AutomationContainer.class);
    }

    @Test
    public void get_returns_AutomationTreeView_on_Tree() throws Exception {
        testGet(ControlType.Tree, AutomationTreeView.class);
    }

    @Test
    public void get_returns_AutomationTreeViewItem_on_TreeItem() throws Exception {
        testGet(ControlType.TreeItem, AutomationTreeViewItem.class);
    }

    @Test
    public void get_returns_AutomationCustom_on_Custom() throws Exception {
        testGet(ControlType.Custom, AutomationCustom.class);
    }

    @Test
    public void get_returns_AutomationContainer_on_Group() throws Exception {
        testGet(ControlType.Group, AutomationContainer.class);
    }

    @Test
    public void get_returns_AutomationContainer_on_Thumb() throws Exception {
        testGet(ControlType.Thumb, AutomationContainer.class);
    }

    @Test
    public void get_returns_AutomationDataGrid_on_DataGrid() throws Exception {
        testGet(ControlType.DataGrid, AutomationDataGrid.class);
    }

    @Test
    public void get_returns_AutomationContainer_on_DataItem() throws Exception {
        testGet(ControlType.DataItem, AutomationContainer.class);
    }

    @Test
    public void get_returns_AutomationDocument_on_Document() throws Exception {
        testGet(ControlType.Document, AutomationDocument.class);
    }

    @Test
    public void get_returns_AutomationSplitButton_on_SplitButton() throws Exception {
        testGet(ControlType.SplitButton, AutomationSplitButton.class);
    }

    @Test
    public void get_returns_AutomationWindow_on_Window() throws Exception {
        testGet(ControlType.Window, AutomationWindow.class);
    }

    @Test
    public void get_returns_AutomationPanel_on_Pane() throws Exception {
        testGet(ControlType.Pane, AutomationPanel.class);
    }

    @Test
    public void get_returns_AutomationReBar_on_ReBar() throws Exception {
        testGet(ControlType.Pane, AutomationReBar.CLASS_NAME, AutomationReBar.class);
    }

    @Test
    public void get_returns_AutomationRibbonBar_on_RibbonBar() throws Exception {
        testGet(ControlType.Pane, AutomationRibbonBar.CLASS_NAME, AutomationRibbonBar.class);
    }

    @Test
    public void get_returns_AutomationRibbonCommandBar_on_RibbonCommandBar() throws Exception {
        testGet(ControlType.Pane, AutomationRibbonCommandBar.CLASS_NAME, AutomationRibbonCommandBar.class);
    }

    @Test
    public void get_returns_AutomationRibbonWorkPane_on_RibbonWorkPane() throws Exception {
        testGet(ControlType.Pane, AutomationRibbonWorkPane.CLASS_NAME, AutomationRibbonWorkPane.class);
    }

    @Test
    public void get_returns_AutomationNUIPane_on_NUIPane() throws Exception {
        testGet(ControlType.Pane, AutomationNUIPane.CLASS_NAME, AutomationNUIPane.class);
    }

    @Test
    public void get_returns_AutomationNetUIHWND_on_NetUIHWND() throws Exception {
        testGet(ControlType.Pane, AutomationNetUIHWND.CLASS_NAME, AutomationNetUIHWND.class);
    }

    @Test
    public void get_returns_AutomationContainer_on_Header() throws Exception {
        testGet(ControlType.Header, AutomationContainer.class);
    }

    @Test
    public void get_returns_AutomationContainer_on_HeaderItem() throws Exception {
        testGet(ControlType.HeaderItem, AutomationContainer.class);
    }

    @Test
    public void get_returns_AutomationContainer_on_Table() throws Exception {
        testGet(ControlType.Table, AutomationContainer.class);
    }

    @Test
    public void get_returns_AutomationTitleBar_on_TitleBar() throws Exception {
        testGet(ControlType.TitleBar, AutomationTitleBar.class);
    }

    @Test
    public void get_returns_AutomationContainer_on_Separator() throws Exception {
        testGet(ControlType.Separator, AutomationContainer.class);
    }

    @Test
    public void get_returns_AutomationContainer_on_SemanticZoom() throws Exception {
        testGet(ControlType.SemanticZoom, AutomationContainer.class);
    }

    @Test
    public void get_returns_AutomationAppBar_on_AppBar() throws Exception {
        testGet(ControlType.AppBar, AutomationAppBar.class);
    }




	private AutomationBase testGet(ControlType controlType, Class<? extends AutomationBase> expectedClass)
			throws AutomationException, PatternNotFoundException {
		return testGet(controlType, "", expectedClass);
	}
	
	private AutomationBase testGet(ControlType controlType, String className, Class<? extends AutomationBase> expectedClass)
			throws AutomationException, PatternNotFoundException {
		BaseAutomationTest.answerIntByReference(controlType.getValue()).when(iuielement).getCurrentControlType(any());
		BaseAutomationTest.answerStringByReference(className).when(iuielement).getCurrentClassName(any());
        
        AutomationBase control = AutomationControlFactory.get(parent, element);
		assertEquals(expectedClass, control.getClass());
        assertEquals(element,control.getElement());
        return control;
	}

}
