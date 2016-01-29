package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{8D253C91-1DC5-4BB5-B18F-ADE16FA495E8}")
public interface IUIAutomationMultipleViewPattern extends Com4jObject {
  // Methods:
  /**
   * @param view Mandatory int parameter.
   * @return  Returns a value of type java.lang.String
   */

  @VTID(3)
  java.lang.String getViewName(
    int view);


  /**
   * @param view Mandatory int parameter.
   */

  @VTID(4)
  void setCurrentView(
    int view);


  /**
   * <p>
   * Getter method for the COM property "CurrentCurrentView"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(5)
  int currentCurrentView();


  /**
   * @return  Returns a value of type int[]
   */

  @VTID(6)
  int[] getCurrentSupportedViews();


  /**
   * <p>
   * Getter method for the COM property "CachedCurrentView"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(7)
  int cachedCurrentView();


  /**
   * @return  Returns a value of type int[]
   */

  @VTID(8)
  int[] getCachedSupportedViews();


  // Properties:
}
