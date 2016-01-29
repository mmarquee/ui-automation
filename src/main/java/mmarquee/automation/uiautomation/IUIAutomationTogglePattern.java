package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{94CF8058-9B8D-4AB9-8BFD-4CD0A33C8C70}")
public interface IUIAutomationTogglePattern extends Com4jObject {
  // Methods:
  /**
   */

  @VTID(3)
  void toggle();


  /**
   * <p>
   * Getter method for the COM property "CurrentToggleState"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.ToggleState
   */

  @VTID(4)
  mmarquee.automation.uiautomation.ToggleState currentToggleState();


  /**
   * <p>
   * Getter method for the COM property "CachedToggleState"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.ToggleState
   */

  @VTID(5)
  mmarquee.automation.uiautomation.ToggleState cachedToggleState();


  // Properties:
}
