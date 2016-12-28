/*
 * Copyright 2016 inpwtepydjuf@gmail.com
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
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.pattern.SelectionItem;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 02/12/2016.
 */
public class AutomationTreeViewItemTest extends BaseAutomationTest {

    protected Logger logger = Logger.getLogger(AutomationTabText.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testName_Returns_Name_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        SelectionItem selection = Mockito.mock(SelectionItem.class);
        Invoke invoke = Mockito.mock(Invoke.class);

        when(element.getName()).thenReturn("NAME");

        AutomationTreeViewItem ctrl = new AutomationTreeViewItem(element, selection, invoke);

        String name = ctrl.name();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testSelect() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTreeView tv1 = window.getTreeView(0);

            AutomationTreeViewItem treeItem = tv1.getItem("Sub-SubItem");

            treeItem.select();

            assertTrue(treeItem.isSelected());
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testIsSelected_When_True() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTreeView tv1 = window.getTreeView(0);

            AutomationTreeViewItem treeItem = tv1.getItem("Sub-SubItem");

            treeItem.select();

            assertTrue(treeItem.isSelected());
        } finally {
            closeApplication();
        }
    }
}
