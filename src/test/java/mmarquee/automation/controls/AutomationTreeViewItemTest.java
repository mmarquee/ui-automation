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

import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.PropertyID;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.SelectionItem;

/**
 * @author Mark Humphreys
 * Date 02/12/2016.
 */
public class AutomationTreeViewItemTest {
    @Mock AutomationElement element;
    @Mock SelectionItem selection;
    @Mock ExpandCollapse expand;
    @Mock Invoke invoke;

    @BeforeClass
    public static void checkOs() throws Exception {
        Assume.assumeTrue(isWindows());
    }

    private static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

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

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(element, selection, expand, invoke);

        String name = ctrl.getName();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testSelect() throws Exception {
        when(element.getPropertyValue(PropertyID.IsSelectionItemPatternAvailable.getValue())).thenReturn(1);
        
        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(element, selection, expand, invoke);

        ctrl.select();

        verify(selection, atLeastOnce()).select();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testSelect_When_Pattern_Is_NOT_Available() throws Exception {
        when(element.getPropertyValue(PropertyID.IsSelectionItemPatternAvailable.getValue())).thenReturn(0);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(element, null, expand, invoke);

        ctrl.select();
    }

    
    @Test
    public void testClick_When_Pattern_Is_Available() throws Exception {
        when(element.getPropertyValue(PropertyID.IsInvokePatternAvailable.getValue())).thenReturn(1);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(element, selection, expand, invoke);

        ctrl.click();

        verify(invoke, atLeastOnce()).invoke();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testClick_When_Pattern_Is_NOT_Available() throws Exception {
        when(element.getPropertyValue(PropertyID.IsInvokePatternAvailable.getValue())).thenReturn(0);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(element, selection, expand, null);

        ctrl.click();
    }

    @Test
    public void testIsSelected_Gets_Value_From_Pattern() throws Exception {
        when(selection.isSelected()).thenReturn(true);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(element, selection, expand, invoke);

        boolean selected = ctrl.isSelected();

        assertTrue(selected);
        
        verify(selection).isSelected();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testIsSelected_When_Pattern_Is_NOT_Available() throws Exception {
        when(element.getPropertyValue(PropertyID.IsSelectionItemPatternAvailable.getValue())).thenReturn(0);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(element, null, expand, invoke);

        ctrl.isSelected();
    }


    @Test
    public void testIsExpanded_Gets_Value_From_Pattern() throws Exception {
        when(expand.isExpanded()).thenReturn(true);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(element, selection, expand, invoke);

        boolean expanded = ctrl.isExpanded();

        assertTrue(expanded);
        verify(expand).isExpanded();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testIsExpanded_When_Pattern_Is_NOT_Available() throws Exception {
        when(element.getPropertyValue(PropertyID.IsExpandCollapsePatternAvailable.getValue())).thenReturn(0);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(element, selection, null, invoke);

        ctrl.isExpanded();
    }

    @Test
    public void testExpand() throws Exception {
        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(element, selection, expand, invoke);

        ctrl.expand();

        verify(expand).expand();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testExpand_When_Pattern_Is_NOT_Available() throws Exception {
        when(element.getPropertyValue(PropertyID.IsExpandCollapsePatternAvailable.getValue())).thenReturn(0);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(element, selection, null, invoke);

        ctrl.expand();
    }
    
    @Test
    public void testCollapse() throws Exception {
        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(element, selection, expand, invoke);

        ctrl.collapse();

        verify(expand).collapse();
    }

    @Test(expected=PatternNotFoundException.class)
    public void testCollapse_When_Pattern_Is_NOT_Available() throws Exception {
        when(element.getPropertyValue(PropertyID.IsExpandCollapsePatternAvailable.getValue())).thenReturn(0);

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(element, selection, null, invoke);

        ctrl.collapse();
    }
}
