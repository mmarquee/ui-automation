package mmarquee.automation.mouse;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * Created by inpwt on 26/02/2016.
 *
 * Wrapper for Robot class
 */
public class AutomationMouse {
    private Robot robot;

    /**
     * Constructor for the AutomationMouse
     */
    public AutomationMouse () {
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            // Not sure what happens if this fails
        }
    }

    /**
     * Moves mouse pointer to given screen coordinates.
     * @param x         X position
     * @param y         Y position
     */
    public void setLocation(int x, int y) {
        robot.mouseMove(x, y);
    }

    /**
     * Clicks the left mouse button
     */
    public void leftClick() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        try {
            Thread.sleep(500);
        } catch (Exception ex) {
            // Hmmm.
        }
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    /**
     * Clicks the right mouse button
     */
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
