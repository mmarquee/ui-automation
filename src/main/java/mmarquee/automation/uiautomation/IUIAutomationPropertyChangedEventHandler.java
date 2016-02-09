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
