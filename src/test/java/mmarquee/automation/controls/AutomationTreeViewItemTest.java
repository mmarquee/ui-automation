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

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import mmarquee.automation.UIAutomation;
import mmarquee.automation.uiautomation.IUIAutomation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.PropertyID;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.SelectionItem;

/**
 * Tests for AutomationTreeViewItem.
 *
 * @author Mark Humphreys
 * Date 02/12/2016.
 */
public class AutomationTreeViewItemTest {
    @Mock AutomationElement element;
    @Mock SelectionItem selection;
    @Mock ExpandCollapse expand;
    @Mock Invoke invoke;

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testName_Returns_Name_From_Element() throws Exception {
        when(element.getName()).thenReturn("NAME");

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(
                new ElementBuilder(element).addPattern(expand).automation(instance).addPattern(invoke));

        String name = ctrl.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testSelect() throws Exception {
        when(element.getPropertyValue(PropertyID.IsSelectionItemPatternAvailable.getValue())).thenReturn(1);

        when(selection.isAvailable()).thenReturn(true);
        
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(
                new ElementBuilder(element).addPattern(selection).addPattern(expand).automation(instance).addPattern(invoke));

        ctrl.select();

        verify(selection, atLeastOnce()).select();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testSelect_When_Pattern_Is_NOT_Available() throws Exception {
        when(element.getPropertyValue(PropertyID.IsSelectionItemPatternAvailable.getValue())).thenReturn(0);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(
                new ElementBuilder(element).addPattern(expand).automation(instance).addPattern(invoke));

        ctrl.select();
    }

    @Test
    public void testClick_When_Pattern_Is_Available() throws Exception {
        when(invoke.isAvailable()).thenReturn(true);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(
                new ElementBuilder(element).addPattern(expand).automation(instance).addPattern(invoke).addPattern(selection));

        ctrl.click();

        verify(invoke, atLeastOnce()).invoke();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testClick_When_Pattern_Is_NOT_Available() throws Exception {
        when(invoke.isAvailable()).thenReturn(false);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(
                new ElementBuilder(element).addPattern(expand).automation(instance).addPattern(invoke).addPattern(selection));

        ctrl.click();
    }

    @Test
    public void testIsSelected_Gets_Value_From_Pattern() throws Exception {
        when(selection.isSelected()).thenReturn(true);
        when(selection.isAvailable()).thenReturn(true);
        
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(
                new ElementBuilder(element).addPattern(expand).automation(instance).addPattern(invoke).addPattern(selection));

        boolean selected = ctrl.isSelected();

        assertTrue(selected);

        verify(selection).isSelected();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testIsSelected_When_Pattern_Is_NOT_Available() throws Exception {
        when(element.getPropertyValue(PropertyID.IsSelectionItemPatternAvailable.getValue())).thenReturn(0);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(
                new ElementBuilder(element).addPattern(expand).automation(instance).addPattern(invoke));

        ctrl.isSelected();
    }


    @Test
    public void testIsExpanded_Gets_Value_From_Pattern() throws Exception {
        when(expand.isAvailable()).thenReturn(true);
        when(expand.isExpanded()).thenReturn(true);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(
                new ElementBuilder(element).addPattern(expand).automation(instance).addPattern(invoke).addPattern(selection));

        boolean expanded = ctrl.isExpanded();

        assertTrue(expanded);
        verify(expand).isExpanded();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testIsExpanded_When_Pattern_Is_NOT_Available() throws Exception {
        when(element.getPropertyValue(PropertyID.IsExpandCollapsePatternAvailable.getValue())).thenReturn(0);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(
                new ElementBuilder(element).automation(instance).addPattern(invoke).addPattern(selection));

        ctrl.isExpanded();
    }

    @Test
    public void testExpand() throws Exception {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);
        when(expand.isAvailable()).thenReturn(true);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(
                new ElementBuilder(element).addPattern(expand).automation(instance).addPattern(invoke).addPattern(selection));

        ctrl.expand();

        verify(expand).expand();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testExpand_When_Pattern_Is_NOT_Available() throws Exception {
        when(element.getPropertyValue(PropertyID.IsExpandCollapsePatternAvailable.getValue())).thenReturn(0);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(
                new ElementBuilder(element).automation(instance).addPattern(invoke).addPattern(selection));

        ctrl.expand();
    }

    @Test
    public void testCollapse() throws Exception {
        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        when(expand.isAvailable()).thenReturn(true);
        
        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(
                new ElementBuilder(element).addPattern(expand).automation(instance).addPattern(invoke).addPattern(selection));

        ctrl.collapse();

        verify(expand).collapse();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testCollapse_When_Pattern_Is_NOT_Available() throws Exception {
        when(element.getPropertyValue(PropertyID.IsExpandCollapsePatternAvailable.getValue())).thenReturn(0);

        IUIAutomation mocked_automation = Mockito.mock(IUIAutomation.class);
        UIAutomation instance = new UIAutomation(mocked_automation);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(
                new ElementBuilder(element).automation(instance).addPattern(invoke).addPattern(selection));

        ctrl.collapse();
    }
}
