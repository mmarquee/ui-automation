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

@IID("{59213F4F-7346-49E5-B120-80555987A148}")
public interface IUIAutomationRangeValuePattern extends Com4jObject {
  // Methods:
  /**
   * @param val Mandatory double parameter.
   */

  @VTID(3)
  void setValue(
    double val);


  /**
   * <p>
   * Getter method for the COM property "CurrentValue"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(4)
  double currentValue();


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
   * Getter method for the COM property "CurrentMaximum"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(6)
  double currentMaximum();


  /**
   * <p>
   * Getter method for the COM property "CurrentMinimum"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(7)
  double currentMinimum();


  /**
   * <p>
   * Getter method for the COM property "CurrentLargeChange"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(8)
  double currentLargeChange();


  /**
   * <p>
   * Getter method for the COM property "CurrentSmallChange"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(9)
  double currentSmallChange();


  /**
   * <p>
   * Getter method for the COM property "CachedValue"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(10)
  double cachedValue();


  /**
   * <p>
   * Getter method for the COM property "CachedIsReadOnly"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(11)
  int cachedIsReadOnly();


  /**
   * <p>
   * Getter method for the COM property "CachedMaximum"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(12)
  double cachedMaximum();


  /**
   * <p>
   * Getter method for the COM property "CachedMinimum"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(13)
  double cachedMinimum();


  /**
   * <p>
   * Getter method for the COM property "CachedLargeChange"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(14)
  double cachedLargeChange();


  /**
   * <p>
   * Getter method for the COM property "CachedSmallChange"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(15)
  double cachedSmallChange();


  // Properties:
}
