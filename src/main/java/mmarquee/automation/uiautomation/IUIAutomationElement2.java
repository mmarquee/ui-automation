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

@IID("{6749C683-F70D-4487-A698-5F79D55290D6}")
public interface IUIAutomationElement2 extends mmarquee.automation.uiautomation.IUIAutomationElement {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "CurrentOptimizeForVisualContent"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(85)
  int currentOptimizeForVisualContent();


  /**
   * <p>
   * Getter method for the COM property "CachedOptimizeForVisualContent"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(86)
  int cachedOptimizeForVisualContent();


  /**
   * <p>
   * Getter method for the COM property "CurrentLiveSetting"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.LiveSetting
   */

  @VTID(87)
  mmarquee.automation.uiautomation.LiveSetting currentLiveSetting();


  /**
   * <p>
   * Getter method for the COM property "CachedLiveSetting"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.LiveSetting
   */

  @VTID(88)
  mmarquee.automation.uiautomation.LiveSetting cachedLiveSetting();


  /**
   * <p>
   * Getter method for the COM property "CurrentFlowsFrom"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(89)
  mmarquee.automation.uiautomation.IUIAutomationElementArray currentFlowsFrom();


  /**
   * <p>
   * Getter method for the COM property "CachedFlowsFrom"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(90)
  mmarquee.automation.uiautomation.IUIAutomationElementArray cachedFlowsFrom();


  // Properties:
}
