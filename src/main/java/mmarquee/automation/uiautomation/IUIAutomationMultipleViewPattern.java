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

@IID("{8D253C91-1DC5-4BB5-B18F-ADE16FA495E8}")
public interface IUIAutomationMultipleViewPattern extends Com4jObject {
  // Methods:
  /**
   * @param view Mandatory int parameter.
   * @return  Returns a value of type java.lang.String
   */

  @VTID(3)
  java.lang.String getViewName(
    int view);


  /**
   * @param view Mandatory int parameter.
   */

  @VTID(4)
  void setCurrentView(
    int view);


  /**
   * <p>
   * Getter method for the COM property "CurrentCurrentView"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(5)
  int currentCurrentView();


  /**
   * @return  Returns a value of type int[]
   */

  @VTID(6)
  int[] getCurrentSupportedViews();


  /**
   * <p>
   * Getter method for the COM property "CachedCurrentView"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(7)
  int cachedCurrentView();


  /**
   * @return  Returns a value of type int[]
   */

  @VTID(8)
  int[] getCachedSupportedViews();


  // Properties:
}
