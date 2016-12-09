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

import mmarquee.automation.AutomationException;
import mmarquee.automation.BaseAutomationTest;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.OrientationType;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 28/11/2016.
 */
public class AutomationBaseTest extends BaseAutomationTest {
    protected Logger logger = Logger.getLogger(AutomationBaseTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    protected void closeApplication() throws PatternNotFoundException, AutomationException {
        AutomationButton btnClickMe = window.getButton("Press Me");
        assertTrue(btnClickMe.name().equals("Press Me"));

        btnClickMe.click();

        AutomationWindow popup = window.getWindow("Confirmation");

        AutomationButton btn = popup.getButton("Yes");
        btn.click();

        this.andRest();
    }

    @Test
    public void testGetAriaRole_For_Window() throws Exception {
        loadApplication("apps\\WpfApplicationWithAutomationIds.exe", "MainWindow");

        try {
            String m = window.getAriaRole();

            assertTrue(m.equals(""));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetOrientation_For_Window() throws  Exception {
        loadApplication("apps\\WpfApplicationWithAutomationIds.exe", "MainWindow");

        try {
            OrientationType m = window.getOrientation();

            assertTrue(m == OrientationType.None);
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetFramework_For_Window() throws  Exception {
        loadApplication("apps\\WpfApplicationWithAutomationIds.exe", "MainWindow");

        try {
            String m = window.getFramework().toString();

            logger.info(m);

            assertTrue(m.equals("WPF"));
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testGetFrameworkId_For_Window() throws  Exception {
        loadApplication("apps\\WpfApplicationWithAutomationIds.exe", "MainWindow");

        try {
            String m = window.getFrameworkId();

            logger.info(m);

            assertTrue(m.equals("WPF"));
        } finally {
            closeApplication();
        }
    }
}
