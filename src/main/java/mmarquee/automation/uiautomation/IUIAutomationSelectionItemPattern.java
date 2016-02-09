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

@IID("{A8EFA66A-0FDA-421A-9194-38021F3578EA}")
public interface IUIAutomationSelectionItemPattern extends Com4jObject {
  // Methods:
  /**
   */

  @VTID(3)
  void select();


  /**
   */

  @VTID(4)
  void addToSelection();


  /**
   */

  @VTID(5)
  void removeFromSelection();


  /**
   * <p>
   * Getter method for the COM property "CurrentIsSelected"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(6)
  int currentIsSelected();


  /**
   * <p>
   * Getter method for the COM property "CurrentSelectionContainer"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(7)
  mmarquee.automation.uiautomation.IUIAutomationElement currentSelectionContainer();


  /**
   * <p>
   * Getter method for the COM property "CachedIsSelected"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(8)
  int cachedIsSelected();


  /**
   * <p>
   * Getter method for the COM property "CachedSelectionContainer"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(9)
  mmarquee.automation.uiautomation.IUIAutomationElement cachedSelectionContainer();


  // Properties:
}
