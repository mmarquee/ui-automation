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
import org.junit.Test;

/**
 * Created by Mark Humphreys on 29/11/2016.
 */
public class AutomationTabText extends BaseAutomationTest {

    protected Logger logger = Logger.getLogger(AutomationTabText.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testGetTabPage_By_Index() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTab tab = window.getTab(0);

            tab.selectTabPage("Calendar");
       } finally {
            closeApplication();
        }
    }

    @Test
    public void testSelectTabePage_Not_Present() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationTab tab = window.getTab(0);

            tab.selectTabPage("ERROR");
        } finally {
            closeApplication();
        }
    }
}
