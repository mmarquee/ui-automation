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
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.PatternNotFoundException;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by Mark Humphreys on 28/11/2016.
 */
public class AutomationButtonTest extends BaseAutomationTest {

    protected Logger logger = Logger.getLogger(AutomationButtonTest.class.getName());

    static {
        ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
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
            this.andRest();

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
            this.andRest();
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
            this.andRest();

            AutomationWindow applicationWindow =
                    automation.getDesktopWindow("MainWindow");

            AutomationButton btnClickMe = applicationWindow.getButton("Press Me");
            assertTrue(btnClickMe.name().equals("Press Me"));

            btnClickMe.click();

            AutomationWindow popup = applicationWindow.getWindow("Confirmation");

            AutomationButton btn = popup.getButton("Yes");
            btn.click();
        } finally {
            this.andRest();
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
            this.andRest();

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
            this.andRest();
        }
    }

    @Test
    public void testSetFocus() throws Exception {
        loadApplication("apps\\Project1.exe", "Form1");

        try {
            AutomationButton cb1 = window.getButton(0);

            cb1.focus();

            // Not quite sure how to check this
        } finally {
            closeApplication();
        }
    }

    @Test
    public void testClick() throws Exception {
        loadApplication("apps\\WpfApplicationWithAutomationIds.exe", "MainWindow");

        try {
            AutomationButton btnClickMe = window.getButtonByAutomationId("idBtn1");

            btnClickMe.click();

            AutomationWindow popup = window.getWindow("Confirmation");

            AutomationButton btn = popup.getButton("Yes");
            btn.click();

            assertTrue(btn.name().equals("Yes"));
        } finally {
            closeApplication();
        }
    }

    @Test(expected=PatternNotFoundException.class)
    @Ignore // Need to work out how to mock this properly.
    public void testClick_Throws_PatternNotFoundException_When_Pattern_Not_Foud() throws Exception {
        AutomationButton mocked_button =
                Mockito.mock(AutomationButton.class);

        when(mocked_button.isInvokePatternAvailable()).thenReturn(false);

        mocked_button.click();
    }
}
