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

@IID("{FDE5EF97-1464-48F6-90BF-43D0948E86EC}")
public interface IUIAutomationDockPattern extends Com4jObject {
  // Methods:
  /**
   * @param dockPos Mandatory mmarquee.automation.uiautomation.DockPosition parameter.
   */

  @VTID(3)
  void setDockPosition(
    mmarquee.automation.uiautomation.DockPosition dockPos);


  /**
   * <p>
   * Getter method for the COM property "CurrentDockPosition"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.DockPosition
   */

  @VTID(4)
  mmarquee.automation.uiautomation.DockPosition currentDockPosition();


  /**
   * <p>
   * Getter method for the COM property "CachedDockPosition"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.DockPosition
   */

  @VTID(5)
  mmarquee.automation.uiautomation.DockPosition cachedDockPosition();


  // Properties:
}
