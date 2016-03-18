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

@IID("{0B964EB3-EF2E-4464-9C79-61D61737A27E}")
public interface IUIAutomationTableItemPattern extends Com4jObject {
  // Methods:
  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(3)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCurrentRowHeaderItems();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(4)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCurrentColumnHeaderItems();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(5)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCachedRowHeaderItems();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(6)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCachedColumnHeaderItems();


  // Properties:
}
