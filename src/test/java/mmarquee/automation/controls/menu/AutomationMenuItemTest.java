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
package mmarquee.automation.controls.menu;

import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.controls.AutomationTitleBar;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 04/12/2016.
 */
public class AutomationMenuItemTest extends BaseAutomationTest {

    @Test
    @Ignore // Still working on this
    public void testGetMenuItem() throws Exception {
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
