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

@IID("{828055AD-355B-4435-86D5-3B51C14A9B1B}")
public interface IUIAutomationLegacyIAccessiblePattern extends Com4jObject {
  // Methods:
  /**
   * @param flagsSelect Mandatory int parameter.
   */

  @VTID(3)
  void select(
    int flagsSelect);


  /**
   */

  @VTID(4)
  void doDefaultAction();


  /**
   * @param szValue Mandatory java.lang.String parameter.
   */

  @VTID(5)
  void setValue(
    @MarshalAs(NativeType.Unicode) java.lang.String szValue);


  /**
   * <p>
   * Getter method for the COM property "CurrentChildId"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(6)
  int currentChildId();


  /**
   * <p>
   * Getter method for the COM property "CurrentName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(7)
  java.lang.String currentName();


  /**
   * <p>
   * Getter method for the COM property "CurrentValue"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(8)
  java.lang.String currentValue();


  /**
   * <p>
   * Getter method for the COM property "CurrentDescription"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(9)
  java.lang.String currentDescription();


  /**
   * <p>
   * Getter method for the COM property "CurrentRole"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(10)
  int currentRole();


  /**
   * <p>
   * Getter method for the COM property "CurrentState"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(11)
  int currentState();


  /**
   * <p>
   * Getter method for the COM property "CurrentHelp"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(12)
  java.lang.String currentHelp();


  /**
   * <p>
   * Getter method for the COM property "CurrentKeyboardShortcut"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(13)
  java.lang.String currentKeyboardShortcut();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(14)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCurrentSelection();


  /**
   * <p>
   * Getter method for the COM property "CurrentDefaultAction"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(15)
  java.lang.String currentDefaultAction();


  /**
   * <p>
   * Getter method for the COM property "CachedChildId"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(16)
  int cachedChildId();


  /**
   * <p>
   * Getter method for the COM property "CachedName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(17)
  java.lang.String cachedName();


  /**
   * <p>
   * Getter method for the COM property "CachedValue"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(18)
  java.lang.String cachedValue();


  /**
   * <p>
   * Getter method for the COM property "CachedDescription"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(19)
  java.lang.String cachedDescription();


  /**
   * <p>
   * Getter method for the COM property "CachedRole"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(20)
  int cachedRole();


  /**
   * <p>
   * Getter method for the COM property "CachedState"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(21)
  int cachedState();


  /**
   * <p>
   * Getter method for the COM property "CachedHelp"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(22)
  java.lang.String cachedHelp();


  /**
   * <p>
   * Getter method for the COM property "CachedKeyboardShortcut"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(23)
  java.lang.String cachedKeyboardShortcut();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(24)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCachedSelection();


  /**
   * <p>
   * Getter method for the COM property "CachedDefaultAction"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(25)
  java.lang.String cachedDefaultAction();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IAccessible
   */

  @VTID(26)
  mmarquee.automation.uiautomation.IAccessible getIAccessible();


  // Properties:
}
