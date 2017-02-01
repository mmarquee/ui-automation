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
import mmarquee.automation.controls.*;

/**
 * Created by Mark Humphreys on 26/02/2016.
 *
 * Test the automation wrapper on Excel.
 */
public class TestMainPowerpoint extends TestBase {

    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            // 0. Load Powerpoint
            try {
                // Start the application
                application = automation.launchOrAttach("\"C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\POWERPNT.EXE\"");
            } catch (Throwable ex) {
                // Smother
                logger.error("Failed to launch or attach Powerpoint");
            }

            // 1. Load the file

            // You'll have to do that manually

            // 2. Get the sheet
            AutomationWindow window = application.getWindow("This is text.pptx - PowerPoint");
            logger.info(window.name());

            // pane "" (MDIClient)
            // pane "Powerpoint Edit View - [This is text.pptx]"
            // pane "Slide"
            // slide "Slide 1 - This is text" (custom)
            // textbox - Title TextBox
            // textbox - TextBox
            // image ""

            AutomationPanel panelX = window.getPanelByClassName(0, "MDIClient");
            logger.info(panelX.name());
            logger.info(panelX.getClassName());

            AutomationPanel panel1 = panelX.getPanel("PowerPoint Edit View - [This is text.pptx]");
            logger.info(panelX.name());
            AutomationPanel panel2 = panel1.getPanel("Slide");
            logger.info(panel2.name());
            AutomationPowerpointSlide slide = panel2.getPowerpointSlide("Slide 1 - This is text");
            logger.info(slide.name());

            // Oddly enough this is an image control, and has text in it's selection
            AutomationImage image = slide.getImage("Title TextBox");
            AutomationElement element = image.getSelectionContainer();

           // logger.info(text.getValue());

            AutomationImage image1 = slide.getImage(0);

            logger.info("++ ALL DONE ++");

        } catch (Exception ex) {
            logger.info("Something went wrong - " + ex.getClass());
        }
    }
}
