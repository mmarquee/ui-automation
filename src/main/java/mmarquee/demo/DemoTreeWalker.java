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

            AutomationElement child = walker.getFirstChildElement(root.element);

            logger.info(child.getName());
            logger.info(child.getClassName());
            logger.info(child.getControlType());

    //        AutomationWindow window = application.getWindow("File Explorer");

            AutomationElement element = child;

            // This crashes at some point

            while (element.element != null) {
                element = walker.getNextSiblingElement(element.element);
                logger.info(element.getName());
                logger.info(element.getClassName());
                logger.info(element.getControlType());
            }

        } catch (Throwable ex) {
            // Smother
            logger.error("Exception thrown - " + ex.toString());
        }
    }
}
