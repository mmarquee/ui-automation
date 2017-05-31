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
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Window;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Created by Mark Humphreys on 25/11/2016.
 *
 * Tests for AutomatedWindow class
 */
public class AutomationWindowTest {

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetWindowName_Matches_Searched_For_Name()
            throws AutomationException, PatternNotFoundException {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        when(element.getName()).thenReturn("Name-01");

        Window window = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(window.isModal()).thenReturn(true);

        AutomationWindow windw = new AutomationWindow(element, window, container);

        assertEquals("Name-01", windw.getName());
    }

    @Test
    public void testGetWindowName_Does_Not_Match_Searched_For_Name()
            throws AutomationException, PatternNotFoundException {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        when(element.getName()).thenReturn("Name-01");

        Window window = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(window.isModal()).thenReturn(true);

        AutomationWindow windw = new AutomationWindow(element, window, container);

        assertNotEquals("Wrong-01", windw.getName());
    }

    @Test
    public void test_getSystemMenu_Does_Not_Throw_Exception() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        Window window = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        AutomationElement elem = Mockito.mock(AutomationElement.class);

        List<AutomationElement> list = new ArrayList<>();
        list.add(elem);

        Mockito.when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        List<AutomationElement> menus = new ArrayList<>();

        AutomationElement item = Mockito.mock(AutomationElement.class);

        menus.add(item);

        Mockito.when(elem.findAll(anyObject(), anyObject())).thenReturn(menus);

        AutomationWindow windw = new AutomationWindow(element, window, container);

        windw.getSystemMenu();
    }

    @Test
    public void testGetTitleBar_Does_Not_Throw_Exception() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        Window window = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        AutomationElement elem = Mockito.mock(AutomationElement.class);

        List<AutomationElement> list = new ArrayList<>();
        list.add(elem);

        Mockito.when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        List<AutomationElement> menus = new ArrayList<>();

        AutomationElement item = Mockito.mock(AutomationElement.class);

        menus.add(item);

        Mockito.when(elem.findAll(anyObject(), anyObject())).thenReturn(menus);

        AutomationWindow windw = new AutomationWindow(element, window, container);

        windw.getTitleBar();
    }

    @Test
    public void testGetAppBar_By_Index_Does_Not_Throw_Exception() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);

        Window window = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        AutomationElement elem = Mockito.mock(AutomationElement.class);

        List<AutomationElement> list = new ArrayList<>();
        list.add(elem);

        Mockito.when(element.findAll(anyObject(), anyObject())).thenReturn(list);

        List<AutomationElement> menus = new ArrayList<>();

        AutomationElement item = Mockito.mock(AutomationElement.class);

        menus.add(item);

        Mockito.when(elem.findAll(anyObject(), anyObject())).thenReturn(menus);

        AutomationWindow windw = new AutomationWindow(element, window, container);

        windw.getAppBar(0);
    }

    @Test
    public void testIsModal_Is_True_For_Modal_Window()
            throws AutomationException, PatternNotFoundException {

        AutomationElement element = Mockito.mock(AutomationElement.class);

        Window window = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(window.isModal()).thenReturn(true);

        AutomationWindow windw = new AutomationWindow(element, window, container);

        assertTrue(windw.isModal());
    }

    @Test
    public void testIsModal_Is_False_For_Non_Modal_Window()
            throws AutomationException, PatternNotFoundException {

        AutomationElement element = Mockito.mock(AutomationElement.class);

        Window window = Mockito.mock(Window.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(window.isModal()).thenReturn(false);

        AutomationWindow windw = new AutomationWindow(element, window, container);

        assertFalse(windw.isModal());
    }
}

