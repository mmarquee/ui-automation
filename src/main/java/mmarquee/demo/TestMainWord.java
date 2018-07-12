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

import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;

/**
 * @author Mark Humphreys
 * Date 26/02/2016.
 *
 * Test the automation wrapper on Excel.
 */
public class TestMainWord extends TestBase {

    public final void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            // 0. Load Word
            try {
                // Start the application
                application = automation.launchOrAttach("C:\\Program Files (x86)\\Microsoft Office\\root\\Office16\\WINWORD.EXE");
            } catch (Throwable ex) {
                // Smother
                logger.error("Failed to launch or attach");
            }

            // 1. Load a file in Word

            // 2. Get the sheet
            assert application != null;
            AutomationWindow window =
                    application.getWindow(
                            Search.getBuilder(
                                    "Document1 - Word").build());
            logger.info(window.getName());

            AutomationPanel pane =
                    window.getPanel(
                            Search.getBuilder("Document1").build());
            logger.info(pane.getName());
            logger.info(pane.getClassName());
            AutomationPanel pane1 =
                    pane.getPanel(Search.getBuilder(0).build());
            logger.info(pane1.getName());

            AutomationDocument doc =
                    pane1.getDocument(Search.getBuilder(0).build());
            logger.info(doc.getName());

            AutomationDocumentPage page0 =
                    doc.getPage(Search.getBuilder(0).build());
            logger.info(page0.getName());

            AutomationEditBox edit =
                    page0.getEditBox(Search.getBuilder(0).build());
            logger.info(edit.getName());

            String text = edit.getText();
            logger.info("Text = " + text.substring(0,10));

            logger.info("++ ALL DONE ++");

        } catch (Exception ex) {
            logger.info("Something went wrong - " + ex.getClass());
        }
    }
}
