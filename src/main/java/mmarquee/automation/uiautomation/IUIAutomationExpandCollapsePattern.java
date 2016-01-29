package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{619BE086-1F4E-4EE4-BAFA-210128738730}")
public interface IUIAutomationExpandCollapsePattern extends Com4jObject {
  // Methods:
  /**
   */

  @VTID(3)
  void expand();


  /**
   */

  @VTID(4)
  void collapse();


  /**
   * <p>
   * Getter method for the COM property "CurrentExpandCollapseState"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.ExpandCollapseState
   */

  @VTID(5)
  mmarquee.automation.uiautomation.ExpandCollapseState currentExpandCollapseState();


  /**
   * <p>
   * Getter method for the COM property "CachedExpandCollapseState"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.ExpandCollapseState
   */

  @VTID(6)
  mmarquee.automation.uiautomation.ExpandCollapseState cachedExpandCollapseState();


  // Properties:
}
