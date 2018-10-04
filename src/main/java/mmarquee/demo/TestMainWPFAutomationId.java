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
package mmarquee.demo;

import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.controls.AutomationButton;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.controls.Search;

/**
 * @author Mark Humphreys
 * Date 26/02/2016.
 *
 * Test the automation wrapper on a WPF application.
 */
public class TestMainWPFAutomationId extends TestBase {

    /**
     * Run the demo.
     */
    public final void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            application = automation.launchOrAttach(
                    "apps\\WpfApplicationWithAutomationIds.exe");
        } catch (Throwable ex) {
            logger.warn("Failed to find application", ex);
        }

        // Wait for the process to start
        // This doesn't seem to wait for WPF examples
        assert application != null;
        application.waitForInputIdle(AutomationApplication.SHORT_TIMEOUT);

        // Sleep for WPF, to address above issue
        this.rest();

        AutomationWindow applicationWindow = null;

        try {
            applicationWindow = automation.getDesktopWindow("MainWindow");
        } catch (Exception ex) {
            logger.info("Failed to find `MainWindow`");
        }

        try {
            assert applicationWindow != null;
            Object framework = applicationWindow.getFramework();
            logger.info("Framework is " + framework.toString());

            Object id = applicationWindow.getProcessId();
            logger.info("Process = " + id.toString());

            WinDef.POINT point = applicationWindow.getClickablePoint();
            logger.info("Clickable point = " + point.toString());

            String name = applicationWindow.getName();
            logger.info(name);

            try {
                boolean val = applicationWindow.isModal();

                logger.info(val);
            } catch (Exception ex) {
                logger.info("Ouch");
            }

            // BUTTONS ***********************************

            logger.info("++ BUTTONS ++");

            // NOTE: WPF buttons will set the automationID to be the name of
            // the control

            AutomationButton btnClickMe =
                    applicationWindow.getButton(
                            Search.getBuilder().automationId("idBtn1").build());
            logger.info(btnClickMe.getName());
            btnClickMe.click();

            logger.info("++ ALL DONE ++");

        } catch (Exception ex) {
            logger.info("Something went wrong - " + ex.getClass());
        }
    }
}
