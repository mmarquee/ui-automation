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

@IID("{B32A92B5-BC25-4078-9C08-D7EE95C48E03}")
public interface IUIAutomationCacheRequest extends Com4jObject {
  // Methods:
  /**
   * @param propertyId Mandatory int parameter.
   */

  @VTID(3)
  void addProperty(
    int propertyId);


  /**
   * @param patternId Mandatory int parameter.
   */

  @VTID(4)
  void addPattern(
    int patternId);


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCacheRequest
   */

  @VTID(5)
  mmarquee.automation.uiautomation.IUIAutomationCacheRequest clone();


  /**
   * <p>
   * Getter method for the COM property "TreeScope"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.TreeScope
   */

  @VTID(6)
  mmarquee.automation.uiautomation.TreeScope treeScope();


  /**
   * <p>
   * Setter method for the COM property "TreeScope"
   * </p>
   * @param scope Mandatory mmarquee.automation.uiautomation.TreeScope parameter.
   */

  @VTID(7)
  void treeScope(
          mmarquee.automation.uiautomation.TreeScope scope);


  /**
   * <p>
   * Getter method for the COM property "TreeFilter"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationCondition
   */

  @VTID(8)
  mmarquee.automation.uiautomation.IUIAutomationCondition treeFilter();


  /**
   * <p>
   * Setter method for the COM property "TreeFilter"
   * </p>
   * @param filter Mandatory mmarquee.automation.uiautomation.IUIAutomationCondition parameter.
   */

  @VTID(9)
  void treeFilter(
          mmarquee.automation.uiautomation.IUIAutomationCondition filter);


  /**
   * <p>
   * Getter method for the COM property "AutomationElementMode"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.AutomationElementMode
   */

  @VTID(10)
  mmarquee.automation.uiautomation.AutomationElementMode automationElementMode();


  /**
   * <p>
   * Setter method for the COM property "AutomationElementMode"
   * </p>
   * @param mode Mandatory mmarquee.automation.uiautomation.AutomationElementMode parameter.
   */

  @VTID(11)
  void automationElementMode(
          mmarquee.automation.uiautomation.AutomationElementMode mode);


  // Properties:
}
