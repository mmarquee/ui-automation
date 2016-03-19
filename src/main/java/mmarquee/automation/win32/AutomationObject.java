package mmarquee.automation.win32;

import java.awt.*;
import java.util.List;

/**
 * Created by inpwt on 19/03/2016.
 */
public interface AutomationObject {
    List<AutomationObject> getChildItems();
    AutomationObject getParent();
    Rectangle getRectangle();
}