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
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.controls.menu.AutomationMenuItem;
import mmarquee.automation.pattern.ItemContainer;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 03/12/2016.
 */
public class AutomationTitleBarTest extends BaseAutomationTest {
    protected Logger logger = Logger.getLogger(AutomationTitleBarTest.class.getName());

    @Test
    public void testName_Returns_Name_From_Element() throws Exception {
        AutomationElement element = Mockito.mock(AutomationElement.class);
        ItemContainer container = Mockito.mock(ItemContainer.class);

        when(element.getName()).thenReturn("NAME");

        AutomationTitleBar ctrl = new AutomationTitleBar(element, container);

        String name = ctrl.name();

        assertTrue(name.equals("NAME"));
    }

    @Test
    public void testGetMenu() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTitleBar sb = window.getTitleBar();

            AutomationMainMenu menu = sb.getMenuBar();

            List<AutomationMenuItem> items = menu.getItems();

            assertTrue(items.size() == 1);
        } finally {
            closeApplication();
        }
    }
}
