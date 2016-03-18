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

@IID("{0FAEF453-9208-43EF-BBB2-3B485177864F}")
public interface IUIAutomationWindowPattern extends Com4jObject {
  // Methods:
  /**
   */

  @VTID(3)
  void close();


  /**
   * @param milliseconds Mandatory int parameter.
   * @return  Returns a value of type int
   */

  @VTID(4)
  int waitForInputIdle(
    int milliseconds);


  /**
   * @param state Mandatory mmarquee.automation.uiautomation.WindowVisualState parameter.
   */

  @VTID(5)
  void setWindowVisualState(
    mmarquee.automation.uiautomation.WindowVisualState state);


  /**
   * <p>
   * Getter method for the COM property "CurrentCanMaximize"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(6)
  int currentCanMaximize();


  /**
   * <p>
   * Getter method for the COM property "CurrentCanMinimize"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(7)
  int currentCanMinimize();


  /**
   * <p>
   * Getter method for the COM property "CurrentIsModal"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(8)
  int currentIsModal();


  /**
   * <p>
   * Getter method for the COM property "CurrentIsTopmost"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(9)
  int currentIsTopmost();


  /**
   * <p>
   * Getter method for the COM property "CurrentWindowVisualState"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.WindowVisualState
   */

  @VTID(10)
  mmarquee.automation.uiautomation.WindowVisualState currentWindowVisualState();


  /**
   * <p>
   * Getter method for the COM property "CurrentWindowInteractionState"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.WindowInteractionState
   */

  @VTID(11)
  mmarquee.automation.uiautomation.WindowInteractionState currentWindowInteractionState();


  /**
   * <p>
   * Getter method for the COM property "CachedCanMaximize"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(12)
  int cachedCanMaximize();


  /**
   * <p>
   * Getter method for the COM property "CachedCanMinimize"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(13)
  int cachedCanMinimize();


  /**
   * <p>
   * Getter method for the COM property "CachedIsModal"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(14)
  int cachedIsModal();


  /**
   * <p>
   * Getter method for the COM property "CachedIsTopmost"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(15)
  int cachedIsTopmost();


  /**
   * <p>
   * Getter method for the COM property "CachedWindowVisualState"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.WindowVisualState
   */

  @VTID(16)
  mmarquee.automation.uiautomation.WindowVisualState cachedWindowVisualState();


  /**
   * <p>
   * Getter method for the COM property "CachedWindowInteractionState"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.WindowInteractionState
   */

  @VTID(17)
  mmarquee.automation.uiautomation.WindowInteractionState cachedWindowInteractionState();


  // Properties:
}
