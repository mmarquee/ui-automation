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

import mmarquee.automation.UIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationTest;
import org.apache.log4j.Logger;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by Mark Humphreys on 28/11/2016.
 */
public class AutomationButtonTest {

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

    @Test
    public void testGetButtonByAutomationId() throws Exception {
        UIAutomation automation = UIAutomation.getInstance();

        try {
            AutomationApplication application
                    = automation.launchOrAttach("apps\\WpfApplicationWithAutomationIds.exe");

            // Wait for the process to start
            // This doesn't seem to wait for WPF examples
            application.waitForInputIdle(5000);

            // Sleep for WPF, to address above issue
            this.rest();

            AutomationWindow applicationWindow =
                    automation.getDesktopWindow("MainWindow");

            AutomationButton btnClickMe = applicationWindow.getButtonByAutomationId("idBtn1");
            logger.info(btnClickMe.name());
            btnClickMe.click();

            AutomationWindow popup = applicationWindow.getWindow("Confirmation");

            assertTrue(popup.name().equals("Confirmation"));
            AutomationButton btn = popup.getButton("Yes");
            btn.click();
        } finally {
            this.rest();
        }
    }

    @Test
    public void testGetButtonByName() throws Exception {
        UIAutomation automation = UIAutomation.getInstance();

        try {
            AutomationApplication application
                    = automation.launchOrAttach("apps\\WpfApplicationWithAutomationIds.exe");

            // Wait for the process to start
            // This doesn't seem to wait for WPF examples
            application.waitForInputIdle(5000);

            // Sleep for WPF, to address above issue
            this.rest();

            AutomationWindow applicationWindow =
                    automation.getDesktopWindow("MainWindow");

            AutomationButton btnClickMe = applicationWindow.getButton("Press Me");
            assertTrue(btnClickMe.name().equals("Press Me"));

            btnClickMe.click();

            AutomationWindow popup = applicationWindow.getWindow("Confirmation");

            AutomationButton btn = popup.getButton("Yes");
            btn.click();
        } finally {
            this.rest();
        }
    }

    @Test
    public void testGetName_For_Button() throws Exception {
        UIAutomation automation = UIAutomation.getInstance();

        try {
            AutomationApplication application =
                    automation.launchOrAttach("apps\\WpfApplicationWithAutomationIds.exe");

            // Wait for the process to start
            // This doesn't seem to wait for WPF examples
            application.waitForInputIdle(5000);

            // Sleep for WPF, to address above issue
            this.rest();

            AutomationWindow applicationWindow =
                    automation.getDesktopWindow("MainWindow");

            AutomationButton btnClickMe = applicationWindow.getButtonByAutomationId("idBtn1");
            assertTrue(btnClickMe.name().equals("Press Me"));

            // Cleanup
            btnClickMe.click();

            AutomationWindow popup = applicationWindow.getWindow("Confirmation");

            AutomationButton btn = popup.getButton("Yes");
            btn.click();
        } finally {
            this.rest();
        }
    }
}
