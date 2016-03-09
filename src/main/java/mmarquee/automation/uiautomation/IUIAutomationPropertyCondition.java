
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

import com4j.IID;
import com4j.NativeType;
import com4j.ReturnValue;
import com4j.VTID;

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
