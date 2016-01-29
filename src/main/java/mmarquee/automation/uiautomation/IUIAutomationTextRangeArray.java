package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{CE4AE76A-E717-4C98-81EA-47371D028EB6}")
public interface IUIAutomationTextRangeArray extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "Length"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(3)
  int length();


  /**
   * @param index Mandatory int parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRange
   */

  @VTID(4)
  mmarquee.automation.uiautomation.IUIAutomationTextRange getElement(
    int index);


  // Properties:
}
