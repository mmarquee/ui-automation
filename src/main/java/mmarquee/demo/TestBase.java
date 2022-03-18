package mmarquee.demo;

import java.util.logging.Logger;

import mmarquee.automation.controls.AutomationBase;

/**
 * @author Mark Humphreys
 * Date 19/05/2016.
 */
public class TestBase {

    protected Logger logger =
            Logger.getLogger(AutomationBase.class.getName());

    protected void rest() {
        try {
            Thread.sleep(1500);
        } catch (Exception ex) {
            logger.info("Interrupted");
        }
    }
}
