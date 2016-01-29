package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{85B94ECD-849D-42B6-B94D-D6DB23FDF5A4}")
public interface IUIAutomationProxyFactory extends Com4jObject {
  // Methods:
  /**
   * @param hwnd Mandatory java.nio.Buffer parameter.
   * @param idObject Mandatory int parameter.
   * @param idChild Mandatory int parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IRawElementProviderSimple
   */

  @VTID(3)
  mmarquee.automation.uiautomation.IRawElementProviderSimple createProvider(
    java.nio.Buffer hwnd,
    int idObject,
    int idChild);


  /**
   * <p>
   * Getter method for the COM property "ProxyFactoryId"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(4)
  java.lang.String proxyFactoryId();


  // Properties:
}
