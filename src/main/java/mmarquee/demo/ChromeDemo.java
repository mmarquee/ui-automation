package mmarquee.demo;

import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.Application;

class ChromeDemo extends TestBase {
    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        Application chrome = null;

        try {
            chrome = automation.launchOrAttach(
                    "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe\"",
                    "--force-renderer-accessibility");
        } catch (Throwable ex) {
            logger.warn("Failed to find application", ex);
        }
    }
}
