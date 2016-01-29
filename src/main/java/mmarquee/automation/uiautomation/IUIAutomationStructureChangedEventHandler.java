package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{E81D1B4E-11C5-42F8-9754-E7036C79F054}")
public interface IUIAutomationStructureChangedEventHandler extends Com4jObject {
  // Methods:
  /**
   * @param sender Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param changeType Mandatory mmarquee.automation.uiautomation.StructureChangeType parameter.
   * @param runtimeId Mandatory int[] parameter.
   */

  @VTID(3)
  void handleStructureChangedEvent(
    mmarquee.automation.uiautomation.IUIAutomationElement sender,
    mmarquee.automation.uiautomation.StructureChangeType changeType,
    int[] runtimeId);


  // Properties:
}
