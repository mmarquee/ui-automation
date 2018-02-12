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

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.ControlType;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.pattern.SelectionItem;
import mmarquee.automation.uiautomation.TreeScope;

/**
 * Tests for AutomationTab.
 *
 * @author Mark Humphreys
 * Date 29/11/2016.
 */
public class AutomationTabTest {
    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    @Mock
    AutomationElement element;

    AutomationTab automationTab;
    
    List<AutomationElement> list;
    
    @Mock
    AutomationElement targetElement;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        automationTab = Mockito.spy(new AutomationTab(new ElementBuilder(element)));
        
        list = new ArrayList<>();
        list.add(targetElement);
    }
    
    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }
    
    @Test
    public void test_GetTabItems_Returns_Items() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);

        List<AutomationTabItem> tabItems = automationTab.getTabItems();
        
        assertEquals(1,tabItems.size());
        
        assertEquals(targetElement,tabItems.get(0).getElement());

        verify(automationTab).createControlTypeCondition(ControlType.TabItem);
        verify(element, atLeastOnce()).findAll(any(), any());
    }

    @Test
    public void test_SelectTabPage_By_Name_Succeeds_When_Tab_Present() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        when(targetElement.getName()).thenReturn("TEST-01");
        SelectionItem mockSelectItemPattern = BaseAutomationTest.mockSelectItemPattern(targetElement);

        automationTab.selectTabPage("TEST-01");
        
        verify(mockSelectItemPattern).select();
        verify(automationTab).createControlTypeCondition(ControlType.TabItem);
        verify(element, atLeastOnce()).findAll(any(), any());
    }
    
    @Test(expected = ElementNotFoundException.class)
    public void test_SelectTabPage_By_Name_Throws_Exception_When_Tab_Not_Present() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        when(targetElement.getName()).thenReturn("TEST-01");
        
        automationTab.selectTabPage("TEST");
    }

    @Test
    public void test_SelectTabPage_By_Name_with_RegExPattern_Succeeds_When_Tab_Present() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        when(targetElement.getName()).thenReturn("TEST-01");
        SelectionItem mockSelectItemPattern = BaseAutomationTest.mockSelectItemPattern(targetElement);

        automationTab.selectTabPage(Pattern.compile("TEST-\\d{2,3}"));
        
        verify(mockSelectItemPattern).select();
        verify(automationTab).createControlTypeCondition(ControlType.TabItem);
        verify(element, atLeastOnce()).findAll(any(), any());
    }
    
    @Test(expected = ElementNotFoundException.class)
    public void test_SelectTabPage_By_Name_with_RegExPattern_Throws_Exception_When_Tab_Not_Present() throws Exception {
        when(element.findAll(BaseAutomationTest.isTreeScope(TreeScope.Descendants), any())).thenReturn(list);
        when(targetElement.getName()).thenReturn("TEST-01");
        
        automationTab.selectTabPage(Pattern.compile("test.*"));
    }
}
