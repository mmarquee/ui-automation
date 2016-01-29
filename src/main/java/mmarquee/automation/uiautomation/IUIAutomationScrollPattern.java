package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{88F4D42A-E881-459D-A77C-73BBBB7E02DC}")
public interface IUIAutomationScrollPattern extends Com4jObject {
  // Methods:
  /**
   * @param horizontalAmount Mandatory mmarquee.automation.uiautomation.ScrollAmount parameter.
   * @param verticalAmount Mandatory mmarquee.automation.uiautomation.ScrollAmount parameter.
   */

  @VTID(3)
  void scroll(
    mmarquee.automation.uiautomation.ScrollAmount horizontalAmount,
    mmarquee.automation.uiautomation.ScrollAmount verticalAmount);


  /**
   * @param horizontalPercent Mandatory double parameter.
   * @param verticalPercent Mandatory double parameter.
   */

  @VTID(4)
  void setScrollPercent(
    double horizontalPercent,
    double verticalPercent);


  /**
   * <p>
   * Getter method for the COM property "CurrentHorizontalScrollPercent"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(5)
  double currentHorizontalScrollPercent();


  /**
   * <p>
   * Getter method for the COM property "CurrentVerticalScrollPercent"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(6)
  double currentVerticalScrollPercent();


  /**
   * <p>
   * Getter method for the COM property "CurrentHorizontalViewSize"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(7)
  double currentHorizontalViewSize();


  /**
   * <p>
   * Getter method for the COM property "CurrentVerticalViewSize"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(8)
  double currentVerticalViewSize();


  /**
   * <p>
   * Getter method for the COM property "CurrentHorizontallyScrollable"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(9)
  int currentHorizontallyScrollable();


  /**
   * <p>
   * Getter method for the COM property "CurrentVerticallyScrollable"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(10)
  int currentVerticallyScrollable();


  /**
   * <p>
   * Getter method for the COM property "CachedHorizontalScrollPercent"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(11)
  double cachedHorizontalScrollPercent();


  /**
   * <p>
   * Getter method for the COM property "CachedVerticalScrollPercent"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(12)
  double cachedVerticalScrollPercent();


  /**
   * <p>
   * Getter method for the COM property "CachedHorizontalViewSize"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(13)
  double cachedHorizontalViewSize();


  /**
   * <p>
   * Getter method for the COM property "CachedVerticalViewSize"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(14)
  double cachedVerticalViewSize();


  /**
   * <p>
   * Getter method for the COM property "CachedHorizontallyScrollable"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(15)
  int cachedHorizontallyScrollable();


  /**
   * <p>
   * Getter method for the COM property "CachedVerticallyScrollable"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(16)
  int cachedVerticallyScrollable();


  // Properties:
}
