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

package mmarquee.automation.pattern.raw;

import com4j.Com4jObject;
import com4j.IID;
import com4j.VTID;

@IID("{619BE086-1F4E-4EE4-BAFA-210128738730}")
public interface IUIAutomationExpandCollapsePattern extends Com4jObject {
  // Methods:
  /**
   */

  @VTID(3)
  void expand();


  /**
   */

  @VTID(4)
  void collapse();


  /**
   * <p>
   * Getter method for the COM property "CurrentExpandCollapseState"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.ExpandCollapseState
   */

  @VTID(5)
  mmarquee.automation.uiautomation.ExpandCollapseState currentExpandCollapseState();


  /**
   * <p>
   * Getter method for the COM property "CachedExpandCollapseState"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.ExpandCollapseState
   */

  @VTID(6)
  mmarquee.automation.uiautomation.ExpandCollapseState cachedExpandCollapseState();


  // Properties:
}
