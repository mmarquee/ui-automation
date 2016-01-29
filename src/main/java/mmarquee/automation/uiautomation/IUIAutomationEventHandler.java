package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{146C3C17-F12E-4E22-8C27-F894B9B79C69}")
public interface IUIAutomationEventHandler extends Com4jObject {
  // Methods:
  /**
   * @param sender Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param eventId Mandatory int parameter.
   */

  @VTID(3)
  void handleAutomationEvent(
    mmarquee.automation.uiautomation.IUIAutomationElement sender,
    int eventId);


  // Properties:
}
