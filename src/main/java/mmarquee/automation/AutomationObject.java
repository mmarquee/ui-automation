package mmarquee.automation;

import java.awt.*;
import java.util.List;

/**
 * Created by inpwt on 19/03/2016.
 */
public abstract class AutomationObject {
    public abstract List<AutomationObject> getChildItems();
    public abstract AutomationObject getParent();

    public abstract Rectangle getRectangle();
 //   public abstract void mouseDown(Point p);
 //   public abstract void mouseUp(Point p);
//
//    public void mouseClick(Point p) {
//        mouseDown(p);
//        mouseUp(p);
//    }
}