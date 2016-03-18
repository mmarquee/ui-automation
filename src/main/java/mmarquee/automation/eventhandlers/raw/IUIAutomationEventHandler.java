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

@IID("{146C3C17-F12E-4E22-8C27-F894B9B79C69}")
public interface IUIAutomationEventHandler extends Com4jObject {
  // Methods:
  /**
   * @param sender Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param eventId Mandatory int parameter.
   */

  @VTID(3)
  void handleAutomationEvent(
    mmarquee.automation.uiautomation.IUIAutomationElement sender,
    int eventId);


  // Properties:
}
