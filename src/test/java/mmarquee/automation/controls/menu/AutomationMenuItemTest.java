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
package mmarquee.automation.controls.menu;

import static junit.framework.TestCase.assertFalse;
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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.controls.ElementBuilder;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * @author Mark Humphreys
 * Date 04/12/2016.
 *
 * Tests for menu item functionality
 */
public class AutomationMenuItemTest extends BaseAutomationTest {

	@Mock AutomationElement mocked_element;
	@Mock AutomationElement targetElement;
	@Mock ExpandCollapse collapse;
	@Mock Invoke invoke;
	private List<AutomationElement> list;

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

        list = new ArrayList<>();
        list.add(targetElement);
    }

    @Test
    public void testGetName() throws Exception {
        when(mocked_element.getName()).thenReturn("NAME");

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

        assertEquals("NAME", item.getName());
    }

    @Test
    public void testIsExpanded_Is_False_When_Not_Expanded() throws Exception {
        when(collapse.isAvailable()).thenReturn(true);
        when(collapse.isExpanded()).thenReturn(false);

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

        assertFalse(item.isExpanded());
    }

    @Test
    public void testIsExpanded_Is_True_When_Expanded() throws Exception {
        when(collapse.isAvailable()).thenReturn(true);
        when(collapse.isExpanded()).thenReturn(true);

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

        assertTrue(item.isExpanded());
    }

    @Test
    public void testClick() throws Exception {

    	IUIAutomationElement elem = Mockito.mock(IUIAutomationElement.class);
        when(invoke.isAvailable()).thenReturn(true);

        mocked_element = new AutomationElement(elem);

        when(collapse.isExpanded()).thenReturn(true);

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

        item.click();

        verify(invoke, atLeastOnce()).invoke();
    }

    @Test
    public void test_GetItems_Returns_Items_When_List_Not_Empty() throws Exception {
        when(mocked_element.findAll(any(), any())).thenReturn(list);

        when(collapse.isExpanded()).thenReturn(true);

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

        List<AutomationMenuItem> items = item.getItems();

        assertEquals(list.size(), items.size());
        assertEquals(targetElement, items.get(0).getElement());
    }

    @Test
    public void test_GetItems_Returns_No_Items_When_List_Empty() throws Exception {
        when(collapse.isExpanded()).thenReturn(true);

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

        List<AutomationMenuItem> items = item.getItems();

        assertEquals(0, items.size());
    }

    @Test
    public void test_GetItems_Returns_MainMenuItems_When_ParentIsMainMenu() throws Exception {
    	AutomationMenuItem item =
    			new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

    	enableMainMenuReference(item, list);

        List<AutomationMenuItem> items = item.getItems();

        assertEquals(list.size(), items.size());
        assertEquals(targetElement, items.get(0).getElement());
    }

    @Test
    public void test_GetMenuItem_By_Index() throws Exception {
        when(mocked_element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(list);

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

		AutomationMenuItem subItem = item.getMenuItem(0);
        assertEquals(targetElement,subItem.getElement());

        verify(mocked_element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetMenuItem_By_Index_Returns_MainMenuItem_When_ParentIsMainMenu() throws Exception {
        AutomationMenuItem item =
        		new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

    	enableMainMenuReference(item, list);

		AutomationMenuItem subItem = item.getMenuItem(0);
        assertEquals(targetElement,subItem.getElement());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetMenuItem_By_Index_Throws_Exception_When_Not_found() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        when(mocked_element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(list);

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

        item.getMenuItem(99);
    }

    @Test
    public void test_GetMenuItem_By_Name() throws Exception {
        when(mocked_element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(targetElement);

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

		AutomationMenuItem subItem = item.getMenuItem("myName");
        assertEquals(targetElement,subItem.getElement());

        verify(mocked_element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void test_GetMenuItem_By_Name_Returns_MainMenuItem_When_ParentIsMainMenu() throws Exception {
        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

    	enableMainMenuReference(item, list);

        when(mocked_element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(targetElement);

		AutomationMenuItem subItem = item.getMenuItem("myName");
        assertEquals(targetElement,subItem.getElement());

    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetMenuItem_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(mocked_element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenThrow(new ElementNotFoundException());

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

        item.getMenuItem("unknownName");
    }

    @Test
    public void test_GetMenuItem_By_Name_with_RegExPattern() throws Exception {
        when(mocked_element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(list);
        when(targetElement.getName()).thenReturn("myName");

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

		AutomationMenuItem subItem = item.getMenuItem(Pattern.compile("myName?"));
        assertEquals(targetElement,subItem.getElement());

        verify(mocked_element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_GetMenuItem_By_Name_with_RegExPattern_Returns_MainMenuItem_When_ParentIsMainMenu() throws Exception {
        when(targetElement.getName()).thenReturn("myName");

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

    	enableMainMenuReference(item, list);

        when(mocked_element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(targetElement);

		AutomationMenuItem subItem = item.getMenuItem(Pattern.compile("(my)+Name"));
        assertEquals(targetElement,subItem.getElement());

    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetMenuItem_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
    	when(mocked_element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(list);
        when(targetElement.getName()).thenReturn("myName");

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

        item.getMenuItem(Pattern.compile("nix"));
    }

    @Test
    public void test_GetMenuItem_By_AutomationId() throws Exception {
        when(mocked_element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

        AutomationMenuItem subItem = item.getMenuItemByAutomationId("myID");
        assertEquals(targetElement,subItem.getElement());

        verify(mocked_element, atLeastOnce()).findFirst(any(), any());
    }

    @Test
    public void test_GetMenuItem_By_AutomationId_Returns_MainMenuItem_When_ParentIsMainMenu() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        list.add(targetElement);

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

    	enableMainMenuReference(item, list);

        AutomationMenuItem subItem = item.getMenuItemByAutomationId("myID");
        assertEquals(targetElement,subItem.getElement());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetMenuItem_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(mocked_element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

        item.getMenuItemByAutomationId("unknownID");
    }

    @Test
    public void test_Expand_Calls_Expand_From_Pattern() throws Exception {
        when(collapse.isAvailable()).thenReturn(true);
        when(collapse.isExpanded()).thenReturn(true);

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

        item.expand();

        Mockito.verify(collapse, atLeastOnce()).expand();
    }

    @Test
    public void test_Collapse_Calls_Collapse_From_Pattern() throws Exception {
        when(collapse.isAvailable()).thenReturn(true);
        when(collapse.isExpanded()).thenReturn(true);

        AutomationMenuItem item =
                new AutomationMenuItem(
                        new ElementBuilder(mocked_element).addPattern(collapse).addPattern(invoke));

        item.collapse();

        Mockito.verify(collapse, atLeastOnce()).collapse();
    }



	private AutomationElement enableMainMenuReference(AutomationMenuItem item, List<AutomationElement> childElements)
			throws AutomationException {
		AutomationElement mainMenuParent =  Mockito.mock(AutomationElement.class);
    	AutomationElement mainMenu =  Mockito.mock(AutomationElement.class);

        when(mainMenuParent.findFirst(any(), any())).thenReturn(mainMenu);
        when(mainMenu.findAll(any(), any())).thenReturn(childElements);
        when(mainMenu.findFirst(any(), any())).thenReturn(childElements.size() > 0 ? childElements.get(0) : null);

        item.parentMenuName = "bla";
        item.mainMenuParentElement = mainMenuParent;

        return mainMenu;
	}
}
