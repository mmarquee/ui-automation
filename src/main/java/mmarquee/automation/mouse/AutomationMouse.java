package mmarquee.automation.mouse;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Created by inpwt on 26/02/2016.
 */
public class AutomationMouse {
    private Robot robot;

    public AutomationMouse () {
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            // Not sure what happens if this fails
        }
    }

    public void setLocation(int x, int y) {
        robot.mouseMove(x, y);
    }

    public void leftClick() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        try {
            Thread.sleep(500);
        } catch (Exception ex) {
            // Hmmm.
        }
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    public void rightClick() {
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        try {
            Thread.sleep(500);
        } catch (Exception ex) {
            // Hmmm.
        }
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }
}
