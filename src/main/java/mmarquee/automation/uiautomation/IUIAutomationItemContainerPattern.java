package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{C690FDB2-27A8-423C-812D-429773C9084E}")
public interface IUIAutomationItemContainerPattern extends Com4jObject {
  // Methods:
  /**
   * @param pStartAfter Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param propertyId Mandatory int parameter.
   * @param value Mandatory java.lang.Object parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(3)
  mmarquee.automation.uiautomation.IUIAutomationElement findItemByProperty(
    mmarquee.automation.uiautomation.IUIAutomationElement pStartAfter,
    int propertyId,
    @MarshalAs(NativeType.VARIANT) java.lang.Object value);


  // Properties:
}
