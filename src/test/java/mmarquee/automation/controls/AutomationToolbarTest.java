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

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 01/12/2016.
 */
public class AutomationToolbarTest extends BaseAutomationTest {

    protected Logger logger = Logger.getLogger(AutomationToolbarTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    public void testGetName() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationToolBar tbar = window.getToolBar(0);

            String name = tbar.name();

            logger.info(name);

            assertTrue(name.equals("ToolBar1"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetToolbarButton() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationToolBar tbar = window.getToolBar(0);

            AutomationToolBarButton btn = tbar.getToolbarButton(0);

            String name = btn.name();

            logger.info(name);

            assertTrue(name.equals(""));
        } finally {
            closeApplication();
        }
    }
}
