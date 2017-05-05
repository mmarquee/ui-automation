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
package mmarquee.automation.controls.menu;

import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.controls.AutomationToolbarButtonTest;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Mark Humphreys on 13/12/2016.
 */
public class AutomationMainMenuTest extends BaseAutomationTest {

    protected Logger logger = Logger.getLogger(AutomationToolbarButtonTest.class.getName());

    @Test
    public void testName() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationMainMenu menu = window.getMainMenu();

            String name = menu.name();

            assertEquals(getLocal("appmenu.name"),name);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetItems() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationMainMenu menu = window.getMainMenu();

            List<AutomationMenuItem> items = menu.getItems();

            int n = items.size();

            assertTrue(n == 2);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetMenuItem() throws Exception {
        loadApplication("notepad.exe", getLocal("notepad.title"));

        try {
            AutomationMainMenu menu = window.getMainMenu();

            AutomationMenuItem item = menu.getMenuItem(getLocal("menu.file"), getLocal("menu.exit"));

            logger.info(item.name());

            assertEquals(getLocal("menu.exit"),item.name());
        } finally {
            closeApplication();
        }
    }
}
