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
