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

import static org.junit.Assert.assertEquals;
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
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * @author Mark Humphreys
 * Date 01/12/2016.
 */
public class AutomationToolbarTest2 {

    @Mock AutomationElement element;
    @Mock AutomationElement targetElement;
    
    @Mock ItemContainer container;
    @Mock
    IUIAutomationElement listElement;
    List<AutomationElement> list;

	static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }
    
    @Before
    public void setupMocks() {
        MockitoAnnotations.initMocks(this);
        
        list = new ArrayList<>();
        list.add(targetElement);
    }

    @Test
    public void test_GetToolbarButton_By_Index_Gets_Button_When_Within_Bounds() throws Exception {
    	
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationToolBar ctrl = new AutomationToolBar(
                new ElementBuilder(element).addPattern(container));

        ctrl.getToolbarButton(Search.getBuilder(0).build());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetToolbarButton_By_Index_Throws_Exception_When_Out_Of_Bounds() throws Exception {
        when(element.findAll(any(), any())).thenReturn(list);

        AutomationToolBar ctrl = new AutomationToolBar(
                new ElementBuilder(element).addPattern(container));

        ctrl.getToolbarButton(Search.getBuilder(1).build());
    }

    @Test
    public void test_GetToolbarButton_By_Name_Gets_Button() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationToolBar ctrl = new AutomationToolBar(
                new ElementBuilder(element).addPattern(container));

        AutomationToolBarButton button = ctrl.getToolbarButton(Search.getBuilder("myName").build());
        
        assertEquals(targetElement,button.getElement());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetToolbarButton_By_Name_Throws_Exception() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        AutomationToolBar ctrl = new AutomationToolBar(
                new ElementBuilder(element).addPattern(container));

        ctrl.getToolbarButton(Search.getBuilder("unknownName").build());
    }

    @Test
    public void test_GetToolbarButton_By_Name_with_RegExPattern_Gets_Button() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        when(targetElement.getName()).thenReturn("myName");
        
        AutomationToolBar ctrl = new AutomationToolBar(
                new ElementBuilder(element).addPattern(container));

        AutomationToolBarButton button = ctrl.getToolbarButton(Search.getBuilder(Pattern.compile("my.*")).build());
        
        assertEquals(targetElement,button.getElement());

        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetToolbarButton_By_Name_with_RegExPattern_Throws_Exception() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        AutomationToolBar ctrl = new AutomationToolBar(
                new ElementBuilder(element).addPattern(container));

        ctrl.getToolbarButton(Search.getBuilder(Pattern.compile("unknownName")).build());
    }

    @Test
    public void test_GetToolbarButton_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationToolBar ctrl = new AutomationToolBar(
                new ElementBuilder(element).addPattern(container));

        AutomationToolBar button = ctrl.getToolBar(Search.getBuilder().automationId("myID").build());
        assertEquals(targetElement,button.getElement());

        verify(element, atLeastOnce()).findFirst(any(), any());
    }
    
    @Test(expected=ElementNotFoundException.class)
    public void test_GetToolbarButton_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        AutomationToolBar ctrl = new AutomationToolBar(
                new ElementBuilder(element).addPattern(container));

        ctrl.getToolBar(Search.getBuilder().automationId("unknownID").build());
    }
}
