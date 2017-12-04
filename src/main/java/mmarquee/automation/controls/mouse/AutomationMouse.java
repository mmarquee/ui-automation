package mmarquee.automation.controls.mouse;

import com.sun.jna.platform.win32.WinDef;

import java.awt.*;
import java.awt.event.InputEvent;

/**
 * @author Mark Humphreys
 * Date 6/02/2016.
 *
 * Wrapper for Robot, used if no other option is available.
 */
public class AutomationMouse {

    /**
     * The instance of AutomationMouse.
     */
    private static AutomationMouse INSTANCE = null;

    /**
     * The sleep duration.
     */
    static final int MOUSE_SLEEP_DURATION = 500;

    /**
     * The Robot.
     */
    private Robot robot;

    /**
     * Constructor for the AutomationMouse.
     */
    protected AutomationMouse() {
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            // Not sure what happens if this fails
        }
    }

    /**
     * Gets the instance.
     *
     * @return the instance of the ui automation library
     */
    public static AutomationMouse getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AutomationMouse();
        }

        return INSTANCE;
    }


    /**
     * Moves mouse pointer to given screen coordinates.
     * @param x         X position
     * @param y         Y position
     */
    public void setLocation(final int x,
                            final int y) {
        robot.mouseMove(x, y);
    }

    /**
     * Moves the mouse pointer to a given screen point.
     *
     * @param point The point to move to.
     */
    public void setLocation(final WinDef.POINT point) {
        robot.mouseMove(point.x, point.y);
    }

    /**
     * Clicks the left mouse button.
     */
    public void leftClick() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        try {
            Thread.sleep(MOUSE_SLEEP_DURATION);
        } catch (Exception ex) {
            // Hmmm.
        }
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
    }

    /**
     * Double clicks the left mouse.
     */
    public void doubleLeftClick() {
        leftClick();
        leftClick();
    }

    /**
     * Clicks the right mouse button.
     */
    public void rightClick() {
        robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
        try {
            Thread.sleep(MOUSE_SLEEP_DURATION);
        } catch (Exception ex) {
            // Hmmm.
        }
        robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
    }
}
