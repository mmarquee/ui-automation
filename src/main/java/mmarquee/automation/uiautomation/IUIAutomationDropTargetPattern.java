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

@IID("{69A095F7-EEE4-430E-A46B-FB73B1AE39A5}")
public interface IUIAutomationDropTargetPattern extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "CurrentDropTargetEffect"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(3)
  java.lang.String currentDropTargetEffect();


  /**
   * <p>
   * Getter method for the COM property "CachedDropTargetEffect"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(4)
  java.lang.String cachedDropTargetEffect();


  /**
   * <p>
   * Getter method for the COM property "CurrentDropTargetEffects"
   * </p>
   * @return  Returns a value of type java.lang.String[]
   */

  @VTID(5)
  java.lang.String[] currentDropTargetEffects();


  /**
   * <p>
   * Getter method for the COM property "CachedDropTargetEffects"
   * </p>
   * @return  Returns a value of type java.lang.String[]
   */

  @VTID(6)
  java.lang.String[] cachedDropTargetEffects();


  // Properties:
}
