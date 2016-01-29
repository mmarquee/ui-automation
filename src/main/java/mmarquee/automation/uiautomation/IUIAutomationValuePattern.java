package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{A94CD8B1-0844-4CD6-9D2D-640537AB39E9}")
public interface IUIAutomationValuePattern extends Com4jObject {
  // Methods:
  /**
   * @param val Mandatory java.lang.String parameter.
   */

  @VTID(3)
  void setValue(
    java.lang.String val);


  /**
   * <p>
   * Getter method for the COM property "CurrentValue"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(4)
  java.lang.String currentValue();


  /**
   * <p>
   * Getter method for the COM property "CurrentIsReadOnly"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(5)
  int currentIsReadOnly();


  /**
   * <p>
   * Getter method for the COM property "CachedValue"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(6)
  java.lang.String cachedValue();


  /**
   * <p>
   * Getter method for the COM property "CachedIsReadOnly"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(7)
  int cachedIsReadOnly();


  // Properties:
}
