package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{FDE5EF97-1464-48F6-90BF-43D0948E86EC}")
public interface IUIAutomationDockPattern extends Com4jObject {
  // Methods:
  /**
   * @param dockPos Mandatory mmarquee.automation.uiautomation.DockPosition parameter.
   */

  @VTID(3)
  void setDockPosition(
    mmarquee.automation.uiautomation.DockPosition dockPos);


  /**
   * <p>
   * Getter method for the COM property "CurrentDockPosition"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.DockPosition
   */

  @VTID(4)
  mmarquee.automation.uiautomation.DockPosition currentDockPosition();


  /**
   * <p>
   * Getter method for the COM property "CachedDockPosition"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.DockPosition
   */

  @VTID(5)
  mmarquee.automation.uiautomation.DockPosition cachedDockPosition();


  // Properties:
}
