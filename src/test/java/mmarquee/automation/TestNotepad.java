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

/**
 * Created by inpwt on 26/02/2016.
 */
public class TestNotepad {
    public void run() {

        UIAutomation automation = new UIAutomation();

        AutomationApplication application = null;

        try {
            application = automation.launchOrAttach("notepad.exe");
        } catch (Throwable ex) {
            // Smother
        }

        // Wait for the process to start
        application.waitForInputIdle(5000);

        AutomationWindow window = automation.getDesktopWindow("Untitled - Notepad");
        window.focus();

        AutomationDocument document = window.getDocument(0);
        //document.setText("This is a journey into sound");

//        document.setName("This is a journey into sound");

/*
		AutomationMainMenu menu = window.getMainMenu();

		AutomationMenuItem exit = menu.getMenuItem("File", "Exit");
		exit.click();
		*/
    }
}
