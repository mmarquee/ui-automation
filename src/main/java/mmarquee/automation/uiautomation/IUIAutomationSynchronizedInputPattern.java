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

import com4j.Com4jObject;
import com4j.IID;
import com4j.VTID;

@IID("{2233BE0B-AFB7-448B-9FDA-3B378AA5EAE1}")
public interface IUIAutomationSynchronizedInputPattern extends Com4jObject {
  // Methods:
  /**
   * @param inputType Mandatory mmarquee.automation.uiautomation.SynchronizedInputType parameter.
   */

  @VTID(3)
  void startListening(
    mmarquee.automation.uiautomation.SynchronizedInputType inputType);


  /**
   */

  @VTID(4)
  void cancel();


  // Properties:
}
