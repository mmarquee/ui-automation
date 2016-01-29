package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{A9B55844-A55D-4EF0-926D-569C16FF89BB}")
public interface IUIAutomationTransformPattern extends Com4jObject {
  // Methods:
  /**
   * @param x Mandatory double parameter.
   * @param y Mandatory double parameter.
   */

  @VTID(3)
  void move(
    double x,
    double y);


  /**
   * @param width Mandatory double parameter.
   * @param height Mandatory double parameter.
   */

  @VTID(4)
  void resize(
    double width,
    double height);


  /**
   * @param degrees Mandatory double parameter.
   */

  @VTID(5)
  void rotate(
    double degrees);


  /**
   * <p>
   * Getter method for the COM property "CurrentCanMove"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(6)
  int currentCanMove();


  /**
   * <p>
   * Getter method for the COM property "CurrentCanResize"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(7)
  int currentCanResize();


  /**
   * <p>
   * Getter method for the COM property "CurrentCanRotate"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(8)
  int currentCanRotate();


  /**
   * <p>
   * Getter method for the COM property "CachedCanMove"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(9)
  int cachedCanMove();


  /**
   * <p>
   * Getter method for the COM property "CachedCanResize"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(10)
  int cachedCanResize();


  /**
   * <p>
   * Getter method for the COM property "CachedCanRotate"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(11)
  int cachedCanRotate();


  // Properties:
}
