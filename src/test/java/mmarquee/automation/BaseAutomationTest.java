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
package mmarquee.automation;

import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Created by Mark Humphreys on 29/11/2016.
 */
public class BaseAutomationTest {
    protected UIAutomation instance;
    protected AutomationApplication application;
    protected AutomationWindow window;
    protected String windowName;

    protected void andRest() {
        // Must be a better way of doing this????
        try {
            Thread.sleep(500);
        } catch (Throwable ex) {
            // interrupted
        }
    }

    protected void loadApplication(String appName, String windowName) throws Exception {
        this.andRest();

        this.windowName = windowName;

        UIAutomation automation = UIAutomation.getInstance();

        application = automation.launchOrAttach(appName);

        // Wait for the process to start
        // This doesn't seem to wait for WPF examples
        application.waitForInputIdle(5000);

        // Sleep for WPF, to address above issue
        this.andRest();

        window = automation.getDesktopWindow(this.windowName);
    }

    protected void closeApplication() throws PatternNotFoundException, AutomationException {
        application.quit(this.windowName);
    }
}
