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

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.TreeScope;

public class AutomationPanelTest {

    @Mock
    AutomationElement element;

    @InjectMocks
    UIAutomation automation;
    
    AutomationPanel panel;
    
    IUIAutomationElement3 elem;
    
    List<AutomationElement> list;
    AutomationElement targetElement;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        panel = Mockito.spy(new AutomationPanel(element));
        
        elem = Mockito.mock(IUIAutomationElement3.class);
        
        list = new ArrayList<>();
        targetElement = new AutomationElement(elem);
        list.add(targetElement);
    }

    
    @Test
    public void test_GetWindow_By_Index() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Subtree), any())).thenReturn(list);

        AutomationWindow window = panel.getWindow(0);
        assertEquals(targetElement,window.element);

        verify(panel).createIntegerVariant(ControlType.Window.getValue());
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test(expected=IndexOutOfBoundsException.class)
    public void test_GetWindow_By_Index_Throws_Exception_When_Not_found() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        panel.getTextBox(99);
    }

    @Test
    public void test_GetWindow_By_Name() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationWindow window = panel.getWindow("myName");
        assertEquals(targetElement,window.getElement());

        verify(panel).createNamePropertyCondition("myName");
        verify(panel).createControlTypeCondition(ControlType.Window);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetWindow_By_Name_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        panel.getTextBox("unknownName");
    }

    @Test
    public void test_GetWindow_By_AutomationId() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(targetElement);

        AutomationWindow window = panel.getWindowByAutomationId("myID");
        assertEquals(targetElement,window.getElement());

        verify(panel).createAutomationIdPropertyCondition("myID");
        verify(panel).createControlTypeCondition(ControlType.Window);
        verify(element, atLeastOnce()).findFirst(any(), any());
    }

    @Test(expected=ElementNotFoundException.class)
    public void test_GetWindow_By_AutomationId_Throws_Exception_When_Not_found() throws Exception {
        when(element.findFirst(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenThrow(new ElementNotFoundException());

        panel.getTextBoxByAutomationId("unknownID");
    }
}
