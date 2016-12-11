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

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 02/12/2016.
 */
public class AutomationCalendarTest extends BaseAutomationTest {

    protected Logger logger = Logger.getLogger(AutomationCalendarTest.class.getName());

    @Test
    public void testName() throws Exception {
        loadApplication("apps\\SampleWpfApplication.exe", "MainWindow");

        try {
            AutomationTab tab = window.getTab(0);

            tab.selectTabPage("Calendar");

            AutomationCalendar calendar = window.getCalendar(0);

            String name = calendar.name();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test(expected=Exception.class)
    public void testGetValue_Throws_Exception() throws Exception {
        loadApplication("apps\\SampleWpfApplication.exe", "MainWindow");

        try {
            AutomationTab tab = window.getTab(0);

            tab.selectTabPage("Calendar");

            this.andRest();

            AutomationCalendar calendar = window.getCalendar(0);

            String value = calendar.getValue();

            logger.info(value);

            assertFalse(value.equals(""));
        } finally {
            closeApplication();
        }
    }
}
