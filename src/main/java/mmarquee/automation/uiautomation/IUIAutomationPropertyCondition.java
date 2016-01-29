package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{99EBF2CB-5578-4267-9AD4-AFD6EA77E94B}")
public interface IUIAutomationPropertyCondition extends mmarquee.automation.uiautomation.IUIAutomationCondition {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "propertyId"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(3)
  int propertyId();


  /**
   * <p>
   * Getter method for the COM property "PropertyValue"
   * </p>
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(4)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object propertyValue();


  /**
   * <p>
   * Getter method for the COM property "PropertyConditionFlags"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.PropertyConditionFlags
   */

  @VTID(5)
  mmarquee.automation.uiautomation.PropertyConditionFlags propertyConditionFlags();


  // Properties:
}
