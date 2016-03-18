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

package mmarquee.automation.eventhandlers.raw;

import com4j.Com4jObject;
import com4j.IID;
import com4j.VTID;

@IID("{E81D1B4E-11C5-42F8-9754-E7036C79F054}")
public interface IUIAutomationStructureChangedEventHandler extends Com4jObject {
  // Methods:
  /**
   * @param sender Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param changeType Mandatory mmarquee.automation.uiautomation.StructureChangeType parameter.
   * @param runtimeId Mandatory int[] parameter.
   */

  @VTID(3)
  void handleStructureChangedEvent(
    mmarquee.automation.uiautomation.IUIAutomationElement sender,
    mmarquee.automation.uiautomation.StructureChangeType changeType,
    int[] runtimeId);


  // Properties:
}
