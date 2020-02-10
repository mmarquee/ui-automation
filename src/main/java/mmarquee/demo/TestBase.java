package mmarquee.demo;

import mmarquee.automation.controls.AutomationBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Mark Humphreys
 * Date 19/05/2016.
 */
public class TestBase {

    protected Logger logger =
            LogManager.getLogger(AutomationBase.class.getName());

    protected void rest() {
        try {
            Thread.sleep(1500);
        } catch (Exception ex) {
            logger.info("Interrupted");
        }
    }
}
