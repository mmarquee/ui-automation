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

@IID("{6D74D017-6ECB-4381-B38B-3C17A48FF1C2}")
public interface IUIAutomationTransformPattern2 extends mmarquee.automation.uiautomation.IUIAutomationTransformPattern {
  // Methods:
  /**
   * @param zoom Mandatory double parameter.
   */

  @VTID(12)
  void zoom(
    double zoom);


  /**
   * @param zoomUnit Mandatory mmarquee.automation.uiautomation.ZoomUnit parameter.
   */

  @VTID(13)
  void zoomByUnit(
    mmarquee.automation.uiautomation.ZoomUnit zoomUnit);


  /**
   * <p>
   * Getter method for the COM property "CurrentCanZoom"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(14)
  int currentCanZoom();


  /**
   * <p>
   * Getter method for the COM property "CachedCanZoom"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(15)
  int cachedCanZoom();


  /**
   * <p>
   * Getter method for the COM property "CurrentZoomLevel"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(16)
  double currentZoomLevel();


  /**
   * <p>
   * Getter method for the COM property "CachedZoomLevel"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(17)
  double cachedZoomLevel();


  /**
   * <p>
   * Getter method for the COM property "CurrentZoomMinimum"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(18)
  double currentZoomMinimum();


  /**
   * <p>
   * Getter method for the COM property "CachedZoomMinimum"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(19)
  double cachedZoomMinimum();


  /**
   * <p>
   * Getter method for the COM property "CurrentZoomMaximum"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(20)
  double currentZoomMaximum();


  /**
   * <p>
   * Getter method for the COM property "CachedZoomMaximum"
   * </p>
   * @return  Returns a value of type double
   */

  @VTID(21)
  double cachedZoomMaximum();


  // Properties:
}
