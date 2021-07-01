package mmarquee.demo;

import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.Application;
import mmarquee.automation.controls.ElementBuilder;

public class TestMultiArgs extends TestBase {
    public final void run() {

        UIAutomation automation = UIAutomation.getInstance();

        Application application =
            new Application(
                 new ElementBuilder()
                    .automation(automation)
                        .applicationPath("notepad.exe")
                            .applicationArguments("--arg1=value1 --arg2=value2"));

        try {
            // Start the application
            application.launchOrAttachWithArgs();
        } catch (Throwable ex) {
            // Smother, but report it
            logger.error("Failed to launch or attach");
        }
    }
}
