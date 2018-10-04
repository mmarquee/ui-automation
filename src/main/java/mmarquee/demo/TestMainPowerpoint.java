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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.AutomationApplication;
import mmarquee.automation.controls.AutomationImage;
import mmarquee.automation.controls.Search;
import mmarquee.automation.controls.AutomationWindow;
import mmarquee.automation.controls.AutomationPanel;
import mmarquee.automation.controls.AutomationPowerpointSlide;

/**
 * Test the automation wrapper on Excel.
 *
 * @author Mark Humphreys
 * Date 26/02/2016.
 *
 */
public class TestMainPowerpoint extends TestBase {

    /**
     * Run it.
     */
    public final void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            // 0. Load Powerpoint
            try {
                // Start the application
                application = automation.launchOrAttach(
                        "C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\POWERPNT.EXE\"");
            } catch (Throwable ex) {
                // Smother
                logger.error("Failed to launch or attach Powerpoint");
            }

            // 1. Load the file

            // You'll have to do that manually

            // 2. Get the sheet
            assert application != null;
            AutomationWindow window =
                    application.getWindow(
                            Search.getBuilder().
                                    className("PPTFrameClass").build());
            logger.info(window.getName());

            AutomationPanel panelX =
                    window.getPanel(
                            Search.getBuilder(0).className(
                                    "MDIClient").build());
            logger.info(panelX.getName());
            logger.info(panelX.getClassName());

            AutomationPanel panel1 =
                    panelX.getPanel(
                            Search.getBuilder(
                                    "PowerPoint Edit View - [This is text.pptx]").build());
            logger.info(panelX.getName());
            AutomationPanel panel2 =
                    panel1.getPanel(
                            Search.getBuilder(
                                    "Slide").build());
            logger.info(panel2.getName());
            AutomationPowerpointSlide slide =
                    panel2.getPowerpointSlide(
                            Search.getBuilder(
                                    "Slide 1 - This is text").build());
            logger.info(slide.getName());

            // Oddly enough this is an image control, and has text in it's
            // selection
            AutomationImage image =
                    slide.getImage(
                            Search.getBuilder("Title TextBox").build());
            AutomationElement element = image.getSelectionContainer();

            logger.info(element.getName());

            AutomationImage image1 =
                    slide.getImage(
                            Search.getBuilder(0).build());

            logger.info(image1.getName());

            logger.info("++ ALL DONE ++");

        } catch (Exception ex) {
            logger.info("Something went wrong - " + ex.getClass());
        }
    }
}
