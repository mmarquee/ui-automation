package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{B32A92B5-BC25-4078-9C08-D7EE95C48E03}")
public interface IUIAutomationCacheRequest extends Com4jObject {
  // Methods:
  /**
   * @param propertyId Mandatory int parameter.
   */

  @VTID(3)
  void addProperty(
    int propertyId);


  /**
   * @param patternId Mandatory int parameter.
   */

  @VTID(4)
  void addPattern(
    int patternId);


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCacheRequest
   */

  @VTID(5)
  mmarquee.automation.uiautomation.IUIAutomationCacheRequest clone();


  /**
   * <p>
   * Getter method for the COM property "TreeScope"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.TreeScope
   */

  @VTID(6)
  mmarquee.automation.uiautomation.TreeScope treeScope();


  /**
   * <p>
   * Setter method for the COM property "TreeScope"
   * </p>
   * @param scope Mandatory mmarquee.automation.uiautomation.TreeScope parameter.
   */

  @VTID(7)
  void treeScope(
          mmarquee.automation.uiautomation.TreeScope scope);


  /**
   * <p>
   * Getter method for the COM property "TreeFilter"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCondition
   */

  @VTID(8)
  mmarquee.automation.uiautomation.IUIAutomationCondition treeFilter();


  /**
   * <p>
   * Setter method for the COM property "TreeFilter"
   * </p>
   * @param filter Mandatory mmarquee.automation.uiautomation.IUIAutomationCondition parameter.
   */

  @VTID(9)
  void treeFilter(
          mmarquee.automation.uiautomation.IUIAutomationCondition filter);


  /**
   * <p>
   * Getter method for the COM property "AutomationElementMode"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.AutomationElementMode
   */

  @VTID(10)
  mmarquee.automation.uiautomation.AutomationElementMode automationElementMode();


  /**
   * <p>
   * Setter method for the COM property "AutomationElementMode"
   * </p>
   * @param mode Mandatory mmarquee.automation.uiautomation.AutomationElementMode parameter.
   */

  @VTID(11)
  void automationElementMode(
          mmarquee.automation.uiautomation.AutomationElementMode mode);


  // Properties:
}
