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

import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.*;
import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.uiautomation.*;

import java.util.List;

/**
 * Demo for the caching functionality.
 */
public class DemoCaching extends TestBase {

    /**
     * Run the thing.
     */
    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            application =
                    automation.launchOrAttach("apps\\Project1.exe");
        } catch (Throwable ex) {
            logger.warn("Failed to find application", ex);
        }

        try {
            // Wait for the process to start
            assert application != null;
            application.waitForInputIdle(AutomationApplication.SHORT_TIMEOUT);
        } catch (Throwable ex) {
            logger.error("Failed to wait properly");
        }

        try {
            // Now do some caching!!!

            AutomationWindow window = automation.getDesktopWindow("Form1");

            PointerByReference condition = automation.createTrueCondition();

            CacheRequest cacheRequest = new CacheRequest(automation);

            cacheRequest.addPattern(PatternID.Selection.getValue());
            cacheRequest.addProperty(PropertyID.Name.getValue());

            List<AutomationElement> all =
                    window.getElement().findAll(
                            new TreeScope(TreeScope.Children),
                                  condition,
                                  cacheRequest);

            logger.info("Elements:" + all.size());

            for(AutomationElement item: all) {
                try {
                    logger.info(" *" + item.getCachedName());
                } catch (Exception ex) {
                    logger.info("Oops, caching doesn't quite work yet");
                }
            }

        } catch (Exception ex) {
            logger.info("Something went wrong - " + ex.getClass());
        }
    }
}