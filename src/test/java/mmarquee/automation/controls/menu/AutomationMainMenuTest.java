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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import mmarquee.automation.controls.ElementBuilder;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * @author Mark Humphreys
 * Date 13/12/2016.
 *
 * Tests for MainMenu.
 */
public class AutomationMainMenuTest extends BaseAutomationTest {
    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    @Mock private AutomationElement element;
	@Mock private AutomationElement parent;
	@Mock
    IUIAutomationElement elem;

	@Mock AutomationElement targetElement;
	List<AutomationElement> list;
	
	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        list = new ArrayList<>();
        list.add(targetElement);
    }

    @Test
    public void testName() throws Exception {
        when(element.getName()).thenReturn("MENU-01");

        AutomationMainMenu item =
                new AutomationMainMenu(new ElementBuilder(element).parent(parent));

        assertEquals("MENU-01", item.getName());
    }

    @Test
    public void testGetItems() throws Exception {
        when(element.getName()).thenReturn("MENU-01");

        List<AutomationElement> collection = new ArrayList<>();

        collection.add(new AutomationElement(elem));

        when(element.findAll(any(), any())).thenReturn(collection);

        AutomationMainMenu menu =
                new AutomationMainMenu(new ElementBuilder(element).parent(parent));

        List<AutomationMenuItem> items = menu.getItems();

        assertTrue(items.size() == 1);
    }

    @Test
    public void testGetMenuItem_With_Both_Parameters() throws Exception {
        when(element.getName()).thenReturn(getLocal("menu.file"));
       
        AutomationElement menuItemElement1 = Mockito.mock(AutomationElement.class);
        ExpandCollapse expandCollapsePattern = BaseAutomationTest.mockExpandCollapsePattern(menuItemElement1);
		when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(menuItemElement1);

        AutomationElement menuItemElement2 = Mockito.mock(AutomationElement.class);
        
        when(menuItemElement1.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(menuItemElement2);

        AutomationMainMenu menu =
                new AutomationMainMenu(new ElementBuilder(element).parent(parent));

        AutomationMenuItem item = menu.getMenuItem(getLocal("menu.file"), getLocal("menu.exit"));
        
        verify(expandCollapsePattern, atLeastOnce()).expand();
        assertEquals(menuItemElement2,item.getElement());
    }

    @Test(expected = ItemNotFoundException.class)
    public void testGetMenuItem_With_First_Parameter_Only_Throws_Exception_When_Not_Found() throws Exception {
        when(element.getName()).thenReturn("NOT MENU-01");

        when(element.findFirst(any(), any())).thenReturn(null);

        AutomationMainMenu menu =
                new AutomationMainMenu(new ElementBuilder(element).parent(parent));

        menu.getMenuItem(getLocal("menu.file"), "");
    }

    @Test
    public void testGetMenuItem_With_First_Parameter_Only_Does_Not_Throws_Exception_When_Found() throws Exception {
        when(element.getName()).thenReturn("MENU-01");

        when(element.findFirst(any(), any())).thenReturn(new AutomationElement(elem));

        AutomationMainMenu menu =
                new AutomationMainMenu(new ElementBuilder(element).parent(parent));

        menu.getMenuItem("MENU-01", "");
    }

    @Test
    public void testGetMenuItem_With_One_Parameter_Does_Not_Throws_Exception_When_Found() throws Exception {
        when(element.getName()).thenReturn("MENU-01");

        when(element.findFirst(any(), any())).thenReturn(new AutomationElement(elem));

        AutomationMainMenu menu =
                new AutomationMainMenu(new ElementBuilder(element).parent(parent));

        menu.getMenuItem("MENU-01");
    }

    @Test(expected = ItemNotFoundException.class)
    public void testGetMenuItem_With_One_Parameter_Throws_Exception_When_Not_Found() throws Exception {
        when(element.getName()).thenReturn("NOT MENU-01");

        when(element.findFirst(any(), any())).thenReturn(null);

        AutomationMainMenu menu =
                new AutomationMainMenu(new ElementBuilder(element).parent(parent));

        menu.getMenuItem(getLocal("menu.file"));
    }


    @Test
    public void testGetMenuItem_with_RegExPattern_With_Both_Parameters() throws Exception {
    	AutomationElement menuItemElement1 = Mockito.mock(AutomationElement.class);
        when(menuItemElement1.getName()).thenReturn(getLocal("menu.file"));
        list.add(menuItemElement1);
        
        ExpandCollapse expandCollapsePattern = BaseAutomationTest.mockExpandCollapsePattern(menuItemElement1);
        
		when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(list);

        AutomationElement menuItemElement2 = Mockito.mock(AutomationElement.class);
        when(menuItemElement2.getName()).thenReturn(getLocal("menu.exit"));
        list.add(menuItemElement2);
        
        when(menuItemElement1.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(list);

        AutomationMainMenu menu =
                new AutomationMainMenu(new ElementBuilder(element).parent(parent));

        AutomationMenuItem item = menu.getMenuItem(Pattern.compile(Pattern.quote(getLocal("menu.file"))), 
        		Pattern.compile(Pattern.quote(getLocal("menu.exit"))));
        
        verify(expandCollapsePattern, atLeastOnce()).expand();
        assertEquals(menuItemElement2,item.getElement());
    }

    @Test(expected = ItemNotFoundException.class)
    public void testGetMenuItem_with_RegExPattern_With_First_Parameter_Only_Throws_Exception_When_Not_Found() throws Exception {
        when(targetElement.getName()).thenReturn("NOT MENU-01");

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationMainMenu menu =
                new AutomationMainMenu(new ElementBuilder(element).parent(parent));

        menu.getMenuItem(Pattern.compile("\\S+-\\d+"), null);
    }

    @Test
    public void testGetMenuItem_with_RegExPattern_With_First_Parameter_Does_Not_Throws_Exception_When_Found() throws Exception {
        when(targetElement.getName()).thenReturn("MENU-01");

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationMainMenu menu =
                new AutomationMainMenu(new ElementBuilder(element).parent(parent));

        menu.getMenuItem(Pattern.compile("\\S+-\\d+"), null);
    }

    @Test
    public void testGetMenuItem_with_RegExPattern_With_One_Parameter_Does_Not_Throws_Exception_When_Found() throws Exception {
        when(targetElement.getName()).thenReturn("MENU-01");

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationMainMenu menu =
                new AutomationMainMenu(new ElementBuilder(element).parent(parent));

        menu.getMenuItem(Pattern.compile("\\S+-\\d+"));
    }

    @Test(expected = ItemNotFoundException.class)
    public void testGetMenuItem_with_RegExPattern_With_One_Parameter_Throws_Exception_When_Not_Found() throws Exception {
        when(targetElement.getName()).thenReturn("NOT MENU-01");

        when(element.findAll(any(), any())).thenReturn(list);

        AutomationMainMenu menu =
                new AutomationMainMenu(new ElementBuilder(element).parent(parent));

        menu.getMenuItem(Pattern.compile("\\S+-\\d+"));
    }

    @Test
    public void test_GetMenuItem_By_Index() throws Exception {
        AutomationElement menuItemElement = Mockito.mock(AutomationElement.class);
        
        List<AutomationElement> list = new ArrayList<>();
        list.add(menuItemElement);
        
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(list);

		AutomationMenu menu = new AutomationMenu(new ElementBuilder(element));
		
		AutomationMenuItem item = menu.getMenuItem(0);
        assertEquals(menuItemElement,item.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetMenuItem_By_Index_Throws_Exception_When_Not_found() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(list);

		AutomationMenu menu = new AutomationMenu(new ElementBuilder(element));
		menu.getMenuItem(99);
    }

    @Test
    public void test_GetMenuItem_By_AutomationId() throws Exception {
        AutomationElement menuItemElement = Mockito.mock(AutomationElement.class);
        
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(menuItemElement);

        AutomationMainMenu menu = Mockito.spy(new AutomationMainMenu(new ElementBuilder(element).parent(parent)));

        AutomationMenuItem item = menu.getMenuItemByAutomationId("myID");
        assertEquals(menuItemElement,item.getElement());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetMenuItem_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());
        AutomationMainMenu menu = Mockito.spy(new AutomationMainMenu(new ElementBuilder(element).parent(parent)));

        menu.getMenuItemByAutomationId("unknownID");
    }
}
