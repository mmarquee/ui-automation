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

@IID("{1DC7B570-1F54-4BAD-BCDA-D36A722FB7BD}")
public interface IUIAutomationDragPattern extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "CurrentIsGrabbed"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(3)
  int currentIsGrabbed();


  /**
   * <p>
   * Getter method for the COM property "CachedIsGrabbed"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(4)
  int cachedIsGrabbed();


  /**
   * <p>
   * Getter method for the COM property "CurrentDropEffect"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(5)
  java.lang.String currentDropEffect();


  /**
   * <p>
   * Getter method for the COM property "CachedDropEffect"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(6)
  java.lang.String cachedDropEffect();


  /**
   * <p>
   * Getter method for the COM property "CurrentDropEffects"
   * </p>
   * @return  Returns a value of type java.lang.String[]
   */

  @VTID(7)
  java.lang.String[] currentDropEffects();


  /**
   * <p>
   * Getter method for the COM property "CachedDropEffects"
   * </p>
   * @return  Returns a value of type java.lang.String[]
   */

  @VTID(8)
  java.lang.String[] cachedDropEffects();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(9)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCurrentGrabbedItems();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(10)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCachedGrabbedItems();


  // Properties:
}
