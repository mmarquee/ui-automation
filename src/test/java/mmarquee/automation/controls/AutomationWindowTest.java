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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.IUIAutomation;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Mark Humphreys
 * Date 25/11/2016.
 *
 * Tests for AutomatedWindow class
 */
public class AutomationWindowTest {

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

	@Mock AutomationElement element;
	@Mock Window window;
	@Mock ItemContainer container;
	@Mock AutomationElement targetElement;
	@Mock AutomationElement item;
	List<AutomationElement> list = new ArrayList<>();
	List<AutomationElement> menus = new ArrayList<>();
	
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        list.add(targetElement);
        menus.add(item);
        when(window.isAvailable()).thenReturn(true);
    }

    @Test
    public void testGetWindowName_Matches_Searched_For_Name()
            throws AutomationException, PatternNotFoundException {
        when(element.getName()).thenReturn("Name-01");

        when(window.isModal()).thenReturn(true);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow windw = new AutomationWindow(
                new ElementBuilder(element).addPattern(container, window).automation(instance));

        assertEquals("Name-01", windw.getName());
    }

    @Test
    public void testGetWindowName_Does_Not_Match_Searched_For_Name()
            throws AutomationException, PatternNotFoundException {
        when(element.getName()).thenReturn("Name-01");

        when(window.isModal()).thenReturn(true);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow windw = new AutomationWindow(
                new ElementBuilder(element).addPattern(container, window).automation(instance));

        assertNotEquals("Wrong-01", windw.getName());
    }

    @Test
    public void test_getSystemMenu_Does_Not_Throw_Exception() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        when(targetElement.findAll(any(), any())).thenReturn(menus);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow windw = new AutomationWindow(
                new ElementBuilder(element).addPattern(container, window));

        windw.getSystemMenu();
    }

    @Test
    public void testGetTitleBar_Does_Not_Throw_Exception() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        when(targetElement.findAll(any(), any())).thenReturn(menus);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow windw = new AutomationWindow(
                new ElementBuilder(element).addPattern(container, window));

        windw.getTitleBar();
    }

    @Test
    public void testGetAppBar_By_Index_Does_Not_Throw_Exception() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        when(targetElement.findAll(any(), any())).thenReturn(menus);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow windw = new AutomationWindow(
                new ElementBuilder(element).addPattern(container, window));

        windw.getAppBar(Search.getBuilder(0).build());
    }

    @Test
    public void testIsModal_Is_True_For_Modal_Window()
            throws AutomationException, PatternNotFoundException {
        when(window.isModal()).thenReturn(true);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow windw = new AutomationWindow(
                new ElementBuilder(element).addPattern(container, window).automation(instance));

        assertTrue(windw.isModal());
    }

    @Test
    public void testIsModal_Is_False_For_Non_Modal_Window()
            throws AutomationException, PatternNotFoundException {
        when(window.isModal()).thenReturn(false);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationWindow windw = new AutomationWindow(
                new ElementBuilder(element).addPattern(container, window).automation(instance));

        assertFalse(windw.isModal());
    }
}

