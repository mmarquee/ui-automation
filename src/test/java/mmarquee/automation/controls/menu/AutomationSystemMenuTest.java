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
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.controls.AutomationToolbarButtonTest;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Created by Mark Humphreys on 03/12/2016.
 */
public class AutomationSystemMenuTest extends BaseAutomationTest {
    protected Logger logger = Logger.getLogger(AutomationToolbarButtonTest.class.getName());

    @Test
    public void testName() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationSystemMenu sm = window.getSystemMenu();

            String name = sm.name();

            logger.info(name);

            assertEquals(getLocal("systemmenu.name"),name);
        } finally {
            closeApplication();
        }
    }

    @Test(expected = ItemNotFoundException.class)
    public void testGetItem_When_Not_Found() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationSystemMenu sm = window.getSystemMenu();

            AutomationMenuItem item = sm.getItem("Not here");

            logger.info(item.name());

            assertTrue(item.name().equals("System"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetItem() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationSystemMenu sm = window.getSystemMenu();

            AutomationMenuItem item = sm.getItem(getLocal("systemmenu.name"));

            logger.info(item.name());

            assertEquals(getLocal("systemmenu.name"),item.name());
        } finally {
            closeApplication();
        }
    }

}
