package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{14314595-B4BC-4055-95F2-58F2E42C9855}")
public interface IUIAutomationElementArray extends Com4jObject {
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
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(4)
  mmarquee.automation.uiautomation.IUIAutomationElement getElement(
    int index);


  // Properties:
}
