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

import java.util.logging.Level;

import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.Application;
import mmarquee.automation.controls.Button;
import mmarquee.automation.controls.Search;
import mmarquee.automation.controls.Window;

/**
 * @author Mark Humphreys
 * Date 04/02/2017.
 */
public class DemoEventHandler extends TestBase {
    public DemoEventHandler() {

    }

    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        Application application = null;

        try {
            application = automation.launchOrAttach("apps\\Project1.exe");
        } catch (Throwable ex) {
            logger.log(Level.WARNING, "Failed to find application", ex);
        }

        try {
            // Wait for the process to start
            assert application != null;
            application.waitForInputIdle(Application.SHORT_TIMEOUT);
        } catch (Throwable ex) {
            logger.severe("Failed to wait properly");
        }

        try {
            Window window = automation.getDesktopWindow("Form1");
            String name = window.getName();
            logger.info(name);

            Button button = window.getButton(Search.getBuilder("OK").build());
/*
            automation.addAutomationEventHandler(
                    EventID.Invoke_Invoked,
                    new TreeScope(TreeScope.Element),
                    button.getElement(),
                    new AutomationEventHandler());
                    */
        } catch (Throwable ex) {
            logger.severe("Failed to get window properly");
        }
    }
}