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

@IID("{D50E472E-B64B-490C-BCA1-D30696F9F289}")
public interface IUIAutomationProxyFactoryEntry extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "ProxyFactory"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationProxyFactory
   */

  @VTID(3)
  mmarquee.automation.uiautomation.IUIAutomationProxyFactory proxyFactory();


  /**
   * <p>
   * Getter method for the COM property "ClassName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(4)
  java.lang.String className();


  /**
   * <p>
   * Getter method for the COM property "ImageName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(5)
  java.lang.String imageName();


  /**
   * <p>
   * Getter method for the COM property "AllowSubstringMatch"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(6)
  int allowSubstringMatch();


  /**
   * <p>
   * Getter method for the COM property "CanCheckBaseClass"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(7)
  int canCheckBaseClass();


  /**
   * <p>
   * Getter method for the COM property "NeedsAdviseEvents"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(8)
  int needsAdviseEvents();


  /**
   * <p>
   * Setter method for the COM property "ClassName"
   * </p>
   * @param className Mandatory java.lang.String parameter.
   */

  @VTID(9)
  void className(
    @MarshalAs(NativeType.Unicode) java.lang.String className);


  /**
   * <p>
   * Setter method for the COM property "ImageName"
   * </p>
   * @param imageName Mandatory java.lang.String parameter.
   */

  @VTID(10)
  void imageName(
    @MarshalAs(NativeType.Unicode) java.lang.String imageName);


  /**
   * <p>
   * Setter method for the COM property "AllowSubstringMatch"
   * </p>
   * @param allowSubstringMatch Mandatory int parameter.
   */

  @VTID(11)
  void allowSubstringMatch(
    int allowSubstringMatch);


  /**
   * <p>
   * Setter method for the COM property "CanCheckBaseClass"
   * </p>
   * @param canCheckBaseClass Mandatory int parameter.
   */

  @VTID(12)
  void canCheckBaseClass(
    int canCheckBaseClass);


  /**
   * <p>
   * Setter method for the COM property "NeedsAdviseEvents"
   * </p>
   * @param adviseEvents Mandatory int parameter.
   */

  @VTID(13)
  void needsAdviseEvents(
    int adviseEvents);


  /**
   * @param eventId Mandatory int parameter.
   * @param propertyId Mandatory int parameter.
   * @param winEvents Mandatory int[] parameter.
   */

  @VTID(14)
  void setWinEventsForAutomationEvent(
    int eventId,
    int propertyId,
    int[] winEvents);


  /**
   * @param eventId Mandatory int parameter.
   * @param propertyId Mandatory int parameter.
   * @return  Returns a value of type int[]
   */

  @VTID(15)
  int[] getWinEventsForAutomationEvent(
    int eventId,
    int propertyId);


  // Properties:
}
