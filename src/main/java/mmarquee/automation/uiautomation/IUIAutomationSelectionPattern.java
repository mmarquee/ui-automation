package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{5ED5202E-B2AC-47A6-B638-4B0BF140D78E}")
public interface IUIAutomationSelectionPattern extends Com4jObject {
  // Methods:
  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(3)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCurrentSelection();


  /**
   * <p>
   * Getter method for the COM property "CurrentCanSelectMultiple"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(4)
  int currentCanSelectMultiple();


  /**
   * <p>
   * Getter method for the COM property "CurrentIsSelectionRequired"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(5)
  int currentIsSelectionRequired();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(6)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCachedSelection();


  /**
   * <p>
   * Getter method for the COM property "CachedCanSelectMultiple"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(7)
  int cachedCanSelectMultiple();


  /**
   * <p>
   * Getter method for the COM property "CachedIsSelectionRequired"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(8)
  int cachedIsSelectionRequired();


  // Properties:
}
