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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import mmarquee.automation.controls.ElementBuilder;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * @author Mark Humphreys
 * Date 13/12/2016.
 *
 * Tests for MainMenu.
 */
public class AutomationMenuTest extends BaseAutomationTest {

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    @Mock private AutomationElement element;
	@Mock private AutomationElement targetElement;
	@Mock
    IUIAutomationElement elem;
	
	List<AutomationElement> list;

	@Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        list = new ArrayList<>();
        list.add(targetElement);
    }

	@Test
	public void getItems() throws Exception {
		
		AutomationMenu menu = new AutomationMenu(new ElementBuilder(element));
		
		List<AutomationElement> itemElements = new LinkedList<>();
		itemElements.add(targetElement);
		when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(itemElements);
		
		List<AutomationMenuItem> items = menu.getItems();
		
		assertEquals(targetElement,items.get(0).getElement());
	}
	

    @Test
    public void test_GetMenuItem_By_Index() throws Exception {
        List<AutomationElement> list = new ArrayList<>();
        list.add(targetElement);
        
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(list);

		AutomationMenu menu = new AutomationMenu(new ElementBuilder(element));
		
		AutomationMenuItem item = menu.getMenuItem(0);
        assertEquals(targetElement,item.getElement());

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
    public void test_GetMenuItem_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(targetElement);

		AutomationMenu menu = new AutomationMenu(new ElementBuilder(element));

		AutomationMenuItem item = menu.getMenuItem("myName");
        assertEquals(targetElement,item.getElement());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetMenuItem_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenThrow(new ElementNotFoundException());

		AutomationMenu menu = new AutomationMenu(new ElementBuilder(element));
		
		menu.getMenuItem("unknownName");
    }

    @Test
    public void test_GetMenuItem_By_Name_with_RegExPattern() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(list);
        when(targetElement.getName()).thenReturn("myName");
        
		AutomationMenu menu = new AutomationMenu(new ElementBuilder(element));

		AutomationMenuItem item = menu.getMenuItem(Pattern.compile("\\S+ame"));
        assertEquals(targetElement,item.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ItemNotFoundException.class)
    public void test_GetMenuItem_By_Name_with_RegExPattern_Throws_Exception_When_Not_found() throws Exception {
    	when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Children), any())).thenReturn(list);
        when(targetElement.getName()).thenReturn("myName");
        
		AutomationMenu menu = new AutomationMenu(new ElementBuilder(element));
		
		menu.getMenuItem(Pattern.compile("\\d+"));
    }

    @Test
    public void test_GetMenuItem_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

		AutomationMenu menu = new AutomationMenu(new ElementBuilder(element));

        AutomationMenuItem item = menu.getMenuItemByAutomationId("myID");
        assertEquals(targetElement,item.getElement());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetMenuItem_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

		AutomationMenu menu = new AutomationMenu(new ElementBuilder(element));

        menu.getMenuItemByAutomationId("unknownID");
    }
}
