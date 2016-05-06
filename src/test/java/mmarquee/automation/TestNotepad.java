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

import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.controls.*;
import mmarquee.automation.controls.menu.AutomationMainMenu;
import mmarquee.automation.controls.menu.AutomationMenuItem;
import mmarquee.automation.uiautomation.ToggleState;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by inpwt on 26/02/2016
 *  *
 * Test the automation wrapper on a Delphi VCL application.
 */
public class TestNotepad {

    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        Logger logger = Logger.getLogger(AutomationBase.class.getName());

        AutomationApplication application = null;

        try {
            application = automation.launchOrAttach("notepad.exe");
        } catch (Throwable ex) {
            logger.warn("Failed to find notepad application", ex);
        }

        // Wait for the process to start
        application.waitForInputIdle(5000);

        try {
            AutomationWindow window = automation.getDesktopWindow("Untitled - Notepad");
            String name = window.name();
            logger.info(name);

            Object framework = window.getFramework();
            logger.info("Framework is " + framework.toString());

            boolean val = window.isModal();

            AutomationEditBox edit = window.getEditBox(0);

            edit.setValue("This is a test of automation");

            // Interact with menus
            AutomationMainMenu menu = window.getMainMenu();

            try {
                AutomationMenuItem exit = menu.getMenuItem("File", "Exit");
                exit.click();

                try {
                    AutomationWindow popup = window.getWindow("Notepad");

                    AutomationButton btn = popup.getButton("Don't Save");

                    btn.click();

                    logger.info("All closed down now");
                } catch (ItemNotFoundException ex) {
                    logger.info("Failed to find window");
                }
            } catch (ElementNotFoundException ex) {
                logger.info("Failed to find exit menu item");
            }
        } catch (ElementNotFoundException ex) {
            logger.info("Element Not Found ");
        }
    }
}
