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
import mmarquee.automation.uiautomation.ToggleState;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 30/11/2016.
 */
public class AutomationRadioButtonTest extends BaseAutomationTest {

    protected Logger logger = Logger.getLogger(AutomationRadioButtonTest.class.getName());

    @Test
    public void testName() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationRadioButton rb1 = window.getRadioButton(0);

            String name = rb1.name();

            logger.info(name);

            assertTrue(name.equals("Radio 2"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void test_isSelected() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationRadioButton rb1 = window.getRadioButton(0);

            boolean selected = rb1.isSelected();

            assertTrue(selected == false);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testSelect() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationRadioButton rb1 = window.getRadioButton(0);

            rb1.selectItem();

            boolean selected = rb1.isSelected();

            assertTrue(selected);
        } finally {
            closeApplication();
        }
    }
}
