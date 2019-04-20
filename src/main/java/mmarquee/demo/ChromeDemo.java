package mmarquee.demo;

import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.Application;
import mmarquee.automation.controls.ElementBuilder;

class ChromeDemo extends TestBase {
    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        Application chrome =
                new Application(
                        new ElementBuilder()
                                .automation(automation)
                                .applicationPath("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe")
                                .applicationArguments("--force-renderer-accessibility"));


        try {
            chrome.launchOrAttach();
        } catch (Throwable ex) {
            logger.warn("Failed to find application", ex);
        }
    }
}
