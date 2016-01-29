package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{620E691C-EA96-4710-A850-754B24CE2417}")
public interface IUIAutomationTablePattern extends Com4jObject {
  // Methods:
  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(3)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCurrentRowHeaders();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(4)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCurrentColumnHeaders();


  /**
   * <p>
   * Getter method for the COM property "CurrentRowOrColumnMajor"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.RowOrColumnMajor
   */

  @VTID(5)
  mmarquee.automation.uiautomation.RowOrColumnMajor currentRowOrColumnMajor();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(6)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCachedRowHeaders();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(7)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCachedColumnHeaders();


  /**
   * <p>
   * Getter method for the COM property "CachedRowOrColumnMajor"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.RowOrColumnMajor
   */

  @VTID(8)
  mmarquee.automation.uiautomation.RowOrColumnMajor cachedRowOrColumnMajor();


  // Properties:
}
