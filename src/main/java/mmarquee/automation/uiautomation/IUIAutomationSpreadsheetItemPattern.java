package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{7D4FB86C-8D34-40E1-8E83-62C15204E335}")
public interface IUIAutomationSpreadsheetItemPattern extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "CurrentFormula"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(3)
  java.lang.String currentFormula();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(4)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCurrentAnnotationObjects();


  /**
   * @return  Returns a value of type int[]
   */

  @VTID(5)
  int[] getCurrentAnnotationTypes();


  /**
   * <p>
   * Getter method for the COM property "CachedFormula"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(6)
  java.lang.String cachedFormula();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(7)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCachedAnnotationObjects();


  /**
   * @return  Returns a value of type int[]
   */

  @VTID(8)
  int[] getCachedAnnotationTypes();


  // Properties:
}
