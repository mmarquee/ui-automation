package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{506A921A-FCC9-409F-B23B-37EB74106872}")
public interface IUIAutomationTextPattern2 extends mmarquee.automation.uiautomation.IUIAutomationTextPattern {
  // Methods:
  /**
   * @param annotation Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRange
   */

  @VTID(9)
  mmarquee.automation.uiautomation.IUIAutomationTextRange rangeFromAnnotation(
    mmarquee.automation.uiautomation.IUIAutomationElement annotation);


  /**
   * @param isActive Mandatory Holder<Integer> parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRange
   */

  @VTID(10)
  mmarquee.automation.uiautomation.IUIAutomationTextRange getCaretRange(
    Holder<Integer> isActive);


  // Properties:
}
