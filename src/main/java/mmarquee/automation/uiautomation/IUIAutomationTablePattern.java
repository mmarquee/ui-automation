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

@IID("{620E691C-EA96-4710-A850-754B24CE2417}")
public interface IUIAutomationTablePattern extends Com4jObject {
  // Methods:
  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(3)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCurrentRowHeaders();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(4)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCurrentColumnHeaders();


  /**
   * <p>
   * Getter method for the COM property "CurrentRowOrColumnMajor"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.RowOrColumnMajor
   */

  @VTID(5)
  mmarquee.automation.uiautomation.RowOrColumnMajor currentRowOrColumnMajor();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(6)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCachedRowHeaders();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(7)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCachedColumnHeaders();


  /**
   * <p>
   * Getter method for the COM property "CachedRowOrColumnMajor"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.RowOrColumnMajor
   */

  @VTID(8)
  mmarquee.automation.uiautomation.RowOrColumnMajor cachedRowOrColumnMajor();


  // Properties:
}
