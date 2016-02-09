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

@IID("{414C3CDC-856B-4F5B-8538-3131C6302550}")
public interface IUIAutomationGridPattern extends Com4jObject {
  // Methods:
  /**
   * @param row Mandatory int parameter.
   * @param column Mandatory int parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(3)
  mmarquee.automation.uiautomation.IUIAutomationElement getItem(
    int row,
    int column);


  /**
   * <p>
   * Getter method for the COM property "CurrentRowCount"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(4)
  int currentRowCount();


  /**
   * <p>
   * Getter method for the COM property "CurrentColumnCount"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(5)
  int currentColumnCount();


  /**
   * <p>
   * Getter method for the COM property "CachedRowCount"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(6)
  int cachedRowCount();


  /**
   * <p>
   * Getter method for the COM property "CachedColumnCount"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(7)
  int cachedColumnCount();


  // Properties:
}
