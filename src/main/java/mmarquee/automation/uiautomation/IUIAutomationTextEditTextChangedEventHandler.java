package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{92FAA680-E704-4156-931A-E32D5BB38F3F}")
public interface IUIAutomationTextEditTextChangedEventHandler extends Com4jObject {
  // Methods:
  /**
   * @param sender Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param textEditChangeType Mandatory mmarquee.automation.uiautomation.TextEditChangeType parameter.
   * @param eventStrings Mandatory java.lang.String[] parameter.
   */

  @VTID(3)
  void handleTextEditTextChangedEvent(
    mmarquee.automation.uiautomation.IUIAutomationElement sender,
    mmarquee.automation.uiautomation.TextEditChangeType textEditChangeType,
    java.lang.String[] eventStrings);


  // Properties:
}
