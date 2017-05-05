package mmarquee.demo;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationTreeWalker;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.AutomationApplication;

/**
 * Created by Mark Humphreys on 02/02/2017.
 */
public class DemoTreeWalker extends TestBase {
    public DemoTreeWalker() {

    }

    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        AutomationApplication application = null;

        try {
            // Start the application
            application = automation.launchOrAttach("explorer");

            AutomationTreeWalker walker = automation.getControlViewWalker();

            AutomationElement root = automation.getRootElement();

            walker.walk(root);
        } catch (Throwable ex) {
            // Smother
            logger.error("Exception thrown - " + ex.toString());
            ex.printStackTrace();
        }
    }
}
