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

@IID("{7D4FB86C-8D34-40E1-8E83-62C15204E335}")
public interface IUIAutomationSpreadsheetItemPattern extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "CurrentFormula"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(3)
  java.lang.String currentFormula();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(4)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCurrentAnnotationObjects();


  /**
   * @return  Returns a value of type int[]
   */

  @VTID(5)
  int[] getCurrentAnnotationTypes();


  /**
   * <p>
   * Getter method for the COM property "CachedFormula"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(6)
  java.lang.String cachedFormula();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(7)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCachedAnnotationObjects();


  /**
   * @return  Returns a value of type int[]
   */

  @VTID(8)
  int[] getCachedAnnotationTypes();


  // Properties:
}
