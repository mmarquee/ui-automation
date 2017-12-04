package mmarquee.demo;

import com.sun.jna.platform.win32.WinDef;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.UIAutomation;

import java.awt.*;

/**
 * @author Mark Humphreys
 * Date 12/02/2017.
 */
public class DemoPointOver extends TestBase {
    public DemoPointOver() {

    }

    public void run() {
        UIAutomation automation = UIAutomation.getInstance();

        do {
            this.rest();

            Point p = MouseInfo.getPointerInfo().getLocation();

            logger.info(p.getX() + " - " + p.getY());

            WinDef.POINT point = new WinDef.POINT();
            point.x = (int) p.getX();
            point.y = (int) p.getY();

            try {
                AutomationElement elementUnder = automation.getElementFromPoint(point);

                logger.info("From point = " + elementUnder.getName());

                AutomationElement elementFocus = automation.getFocusedElement();
                logger.info("From focus = " + elementFocus.getName());

            } catch (Exception ex) {
                logger.info(ex.getStackTrace());
            }
        } while (true);
    }
}
