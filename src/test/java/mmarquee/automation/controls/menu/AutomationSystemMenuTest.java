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
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.ItemNotFoundException;

/**
 * @author Mark Humphreys
 * Date 03/12/2016.
 *
 * Tests for SystemMenu automation.
 */
public class AutomationSystemMenuTest extends BaseAutomationTest {

	@Mock AutomationElement element;
    @Mock AutomationElement targetElement;
    List<AutomationElement> list;
	
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
    public void testName() throws Exception {
        when(element.getName()).thenReturn("MENU-01");

        AutomationSystemMenu item = new AutomationSystemMenu(element);

        assertEquals("MENU-01", item.getName());
    }

    @Test
    public void testGetItems_Returns_Correct_Number_Of_Elements() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationSystemMenu item = new AutomationSystemMenu(element);
        List<AutomationMenuItem> items = item.getItems();

        assertTrue(items.size() == 1);
    }

    @Test
    public void testGetItem_Does_Not_Throw_Exception_When_Found() throws Exception {
        when(targetElement.getName()).thenReturn("NAME-01");
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationSystemMenu menu = new AutomationSystemMenu(element);

        AutomationMenuItem item = menu.getItem("NAME-01");
    }

    @Test(expected = ItemNotFoundException.class)
    public void testGetItem_Throw_Exception_When_Not_Found() throws Exception {
        when(targetElement.getName()).thenReturn("NAME-01");
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationSystemMenu menu = new AutomationSystemMenu(element);

        menu.getItem("NAME-02");
    }

    @Test
    public void testGetItem_with_RegExPattern_Does_Not_Throw_Exception_When_Found() throws Exception {
        when(targetElement.getName()).thenReturn("NAME-01");
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationSystemMenu menu = new AutomationSystemMenu(element);

        AutomationMenuItem item = menu.getItem(Pattern.compile(".*-\\d+"));
    }

    @Test(expected = ItemNotFoundException.class)
    public void testGetItem_with_RegExPattern_Throw_Exception_When_Not_Found() throws Exception {
        when(targetElement.getName()).thenReturn("NAME-01");
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationSystemMenu menu = new AutomationSystemMenu(element);

        menu.getItem(Pattern.compile(".*-00"));
    }
}
