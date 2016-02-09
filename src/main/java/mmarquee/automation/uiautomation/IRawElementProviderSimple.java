/*
 * Copyright 2016 inpwtepydjuf@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
