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

@IID("{94CF8058-9B8D-4AB9-8BFD-4CD0A33C8C70}")
public interface IUIAutomationTogglePattern extends Com4jObject {
  // Methods:
  /**
   */

  @VTID(3)
  void toggle();


  /**
   * <p>
   * Getter method for the COM property "CurrentToggleState"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.ToggleState
   */

  @VTID(4)
  mmarquee.automation.uiautomation.ToggleState currentToggleState();


  /**
   * <p>
   * Getter method for the COM property "CachedToggleState"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.ToggleState
   */

  @VTID(5)
  mmarquee.automation.uiautomation.ToggleState cachedToggleState();


  // Properties:
}
