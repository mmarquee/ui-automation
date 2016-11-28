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
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.IUIAutomationTest;
import mmarquee.automation.uiautomation.OrientationType;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 28/11/2016.
 */
public class AutomationBaseTest {
    protected Logger logger = Logger.getLogger(IUIAutomationTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
    }

    protected void rest() {
        try {
            Thread.sleep(1500);
        } catch (Exception ex) {
            logger.info("Interrupted");
        }
    }

    @Before
    public void setUp() throws Exception {
        loadApplication();
    }

    @After
    public void tearDown() throws PatternNotFoundException, AutomationException {
        closeApplication();
    }

    private AutomationApplication application = null;
    private AutomationWindow applicationWindow = null;

    private void loadApplication() throws Exception {
        UIAutomation automation = UIAutomation.getInstance();

        application = automation.launchOrAttach("apps\\WpfApplicationWithAutomationIds.exe");

        // Wait for the process to start
        // This doesn't seem to wait for WPF examples
        application.waitForInputIdle(5000);

        // Sleep for WPF, to address above issue
        this.rest();

        applicationWindow = automation.getDesktopWindow("MainWindow");
    }

    private void closeApplication() throws PatternNotFoundException, AutomationException {
        AutomationButton btnClickMe = applicationWindow.getButton("Press Me");
        assertTrue(btnClickMe.name().equals("Press Me"));

        btnClickMe.click();

        AutomationWindow popup = applicationWindow.getWindow("Confirmation");

        AutomationButton btn = popup.getButton("Yes");
        btn.click();

        this.rest();
    }

    @Test
    public void testGetAriaRole_For_Window() throws AutomationException {
        String m = applicationWindow.getAriaRole();

        assertTrue(m.equals(""));
    }

    @Test
    public void testGetOrientation_For_Window() throws AutomationException {
        OrientationType m = applicationWindow.getOrientation();

        assertTrue(m == OrientationType.None);
    }
}
