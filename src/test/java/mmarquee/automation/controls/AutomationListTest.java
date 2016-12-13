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

import mmarquee.automation.BaseAutomationTest;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 01/12/2016.
 */
public class AutomationListTest extends BaseAutomationTest {
    protected Logger logger = Logger.getLogger(AutomationListTest.class.getName());

    @Test
    public void testName() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationList li1 = window.getListItem(0);

            String name = li1.name();

            logger.info(name);

            // The value that comes back here seems very wrong
            assertTrue(name.equals("<a href=\"http://www.google.co.uk\">This is a link</a>"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetItems() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationList li1 = window.getListItem(0);

            List<AutomationListItem> items = li1.getItems();

            logger.info(items.size());

            assertTrue(items.size() == 5);
        } finally {
            closeApplication();
        }
    }
}
