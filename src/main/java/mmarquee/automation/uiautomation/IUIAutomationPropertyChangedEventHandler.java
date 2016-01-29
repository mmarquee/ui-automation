package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{40CD37D4-C756-4B0C-8C6F-BDDFEEB13B50}")
public interface IUIAutomationPropertyChangedEventHandler extends Com4jObject {
  // Methods:
  /**
   * @param sender Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param propertyId Mandatory int parameter.
   * @param newValue Mandatory java.lang.Object parameter.
   */

  @VTID(3)
  void handlePropertyChangedEvent(
    mmarquee.automation.uiautomation.IUIAutomationElement sender,
    int propertyId,
    @MarshalAs(NativeType.VARIANT) java.lang.Object newValue);


  // Properties:
}
