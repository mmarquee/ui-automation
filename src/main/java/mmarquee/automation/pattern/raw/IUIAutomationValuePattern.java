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

@IID("{A94CD8B1-0844-4CD6-9D2D-640537AB39E9}")
public interface IUIAutomationValuePattern extends Com4jObject {
  // Methods:
  /**
   * @param val Mandatory java.lang.String parameter.
   */

  @VTID(3)
  void setValue(
    java.lang.String val);


  /**
   * <p>
   * Getter method for the COM property "CurrentValue"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(4)
  java.lang.String currentValue();


  /**
   * <p>
   * Getter method for the COM property "CurrentIsReadOnly"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(5)
  int currentIsReadOnly();


  /**
   * <p>
   * Getter method for the COM property "CachedValue"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(6)
  java.lang.String cachedValue();


  /**
   * <p>
   * Getter method for the COM property "CachedIsReadOnly"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(7)
  int cachedIsReadOnly();


  // Properties:
}
