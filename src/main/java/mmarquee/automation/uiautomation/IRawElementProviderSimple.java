package mmarquee.automation.uiautomation;

import com4j.*;

@IID("{D6DD68D1-86FD-4332-8666-9ABEDEA2D24C}")
public interface IRawElementProviderSimple extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "ProviderOptions"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.ProviderOptions
   */

  @VTID(3)
  mmarquee.automation.uiautomation.ProviderOptions providerOptions();


  /**
   * @param patternId Mandatory int parameter.
   * @return  Returns a value of type com4j.Com4jObject
   */

  @VTID(4)
  com4j.Com4jObject getPatternProvider(
    int patternId);


  /**
   * @param propertyId Mandatory int parameter.
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(5)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getPropertyValue(
    int propertyId);


  /**
   * <p>
   * Getter method for the COM property "HostRawElementProvider"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IRawElementProviderSimple
   */

  @VTID(6)
  mmarquee.automation.uiautomation.IRawElementProviderSimple hostRawElementProvider();


  // Properties:
}
