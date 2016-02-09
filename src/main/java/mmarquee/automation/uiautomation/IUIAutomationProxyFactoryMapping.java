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

@IID("{09E31E18-872D-4873-93D1-1E541EC133FD}")
public interface IUIAutomationProxyFactoryMapping extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "count"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(3)
  int count();


  /**
   * @param index Mandatory int parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationProxyFactoryEntry
   */

  @VTID(5)
  mmarquee.automation.uiautomation.IUIAutomationProxyFactoryEntry getEntry(
    int index);


      /**
       * @param before Mandatory int parameter.
       * @param factory Mandatory mmarquee.automation.uiautomation.IUIAutomationProxyFactoryEntry parameter.
       */

      @VTID(8)
      void insertEntry(
        int before,
        mmarquee.automation.uiautomation.IUIAutomationProxyFactoryEntry factory);


      /**
       * @param index Mandatory int parameter.
       */

      @VTID(9)
      void removeEntry(
        int index);


      /**
       */

      @VTID(10)
      void clearTable();


      /**
       */

      @VTID(11)
      void restoreDefaultTable();


      // Properties:
    }
