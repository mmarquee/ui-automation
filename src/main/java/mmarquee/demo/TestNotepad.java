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

import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.*;
import mmarquee.automation.controls.MainMenu;
import mmarquee.automation.controls.MenuItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

/**
 * Test the automation wrapper on a Delphi VCL application.
 *
 * @author Mark Humphreys
 * Date 26/02/2016.
 */
public class TestNotepad extends TestBase {

    /**
     * Run the test.
     */
    public final void run() {
        UIAutomation automation = UIAutomation.getInstance();

        Logger logger =
                LogManager.getLogger(AutomationBase.class.getName());

        Application application =
                new Application(
                        new ElementBuilder()
                                .automation(automation)
                                .applicationPath("notepad.exe"));

        try {
            application.launchOrAttach();
        } catch (Throwable ex) {
            logger.warn("Failed to find notepad application", ex);
        }

        try {
            logger.info(Application.getVersionNumber("notepad.exe"));
        } catch (Throwable ex) {
            logger.warn("Failed to get version information", ex);
        }

        // Wait for the process to start
        assert application != null;
        application.waitForInputIdle(Application.SHORT_TIMEOUT);

        try {
            Window window = automation.getDesktopWindow(
                    Pattern.compile("Untitled - Notepad|Unbenannt - Editor"));
            String name = window.getName();
            logger.info(name);

            Object framework = window.getFramework();
            logger.info("Framework is " + framework.toString());

            boolean val = window.isModal();

            logger.info("Modal?" + val);

            EditBox edit =
                    window.getEditBox(Search.getBuilder(0).build());

            edit.setValue("This is a test of automation");

            window.focus();
            window.maximize();

            this.rest();

            // Interact with menus
            MainMenu menu = window.getMainMenu();

            try {
                MenuItem exit = menu.getMenuItem(
                        Pattern.compile("File|Datei"),
                        Pattern.compile("Exit|Beenden"));
                exit.click();

                try {
                    Window popup = window.getWindow(
                            Search.getBuilder(
                                    Pattern.compile("Notepad|Editor")).build());
                    Button btn = popup.getButton(
                            Search.getBuilder(
                                    Pattern.compile("Don't Save|Nicht speichern")).build());

                    btn.click();

                    logger.info("All closed down now");
                } catch (ItemNotFoundException ex) {
                    logger.info("Failed to find window");
                }
            } catch (ElementNotFoundException ex) {
                logger.info("Failed to find exit menu item");
            }
        } catch (Exception ex) {
            logger.info("Something went wrong - " + ex.toString());
        }
    }
}
