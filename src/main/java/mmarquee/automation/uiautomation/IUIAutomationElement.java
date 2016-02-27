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

import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.PointerByReference;
import com4j.*;

@IID("{D22108AA-8AC5-49A5-837B-37BBB3D7591E}")
public interface IUIAutomationElement extends Com4jObject {
  // Methods:
  /**
   */

  @VTID(3)
  void setFocus();


  /**
   * @return  Returns a value of type int[]
   */

  @VTID(4)
  int[] getRuntimeId();


  /**
   * @param scope Mandatory mmarquee.automation.uiautomation.TreeScope parameter.
   * @param condition Mandatory mmarquee.automation.uiautomation.IUIAutomationCondition parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(5)
  mmarquee.automation.uiautomation.IUIAutomationElement findFirst(
    mmarquee.automation.uiautomation.TreeScope scope,
    mmarquee.automation.uiautomation.IUIAutomationCondition condition);


  /**
   * @param scope Mandatory mmarquee.automation.uiautomation.TreeScope parameter.
   * @param condition Mandatory mmarquee.automation.uiautomation.IUIAutomationCondition parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(6)
  mmarquee.automation.uiautomation.IUIAutomationElementArray findAll(
    mmarquee.automation.uiautomation.TreeScope scope,
    mmarquee.automation.uiautomation.IUIAutomationCondition condition);


  /**
   * @param scope Mandatory mmarquee.automation.uiautomation.TreeScope parameter.
   * @param condition Mandatory mmarquee.automation.uiautomation.IUIAutomationCondition parameter.
   * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(7)
  mmarquee.automation.uiautomation.IUIAutomationElement findFirstBuildCache(
    mmarquee.automation.uiautomation.TreeScope scope,
    mmarquee.automation.uiautomation.IUIAutomationCondition condition,
    mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest);


  /**
   * @param scope Mandatory mmarquee.automation.uiautomation.TreeScope parameter.
   * @param condition Mandatory mmarquee.automation.uiautomation.IUIAutomationCondition parameter.
   * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(8)
  mmarquee.automation.uiautomation.IUIAutomationElementArray findAllBuildCache(
    mmarquee.automation.uiautomation.TreeScope scope,
    mmarquee.automation.uiautomation.IUIAutomationCondition condition,
    mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest);


  /**
   * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(9)
  mmarquee.automation.uiautomation.IUIAutomationElement buildUpdatedCache(
    mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest);


  /**
   * @param propertyId Mandatory int parameter.
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(10)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getCurrentPropertyValue(
    int propertyId);


  /**
   * @param propertyId Mandatory int parameter.
   * @param ignoreDefaultValue Mandatory int parameter.
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(11)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getCurrentPropertyValueEx(
    int propertyId,
    int ignoreDefaultValue);


  /**
   * @param propertyId Mandatory int parameter.
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(12)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getCachedPropertyValue(
    int propertyId);


  /**
   * @param propertyId Mandatory int parameter.
   * @param ignoreDefaultValue Mandatory int parameter.
   * @return  Returns a value of type java.lang.Object
   */

  @VTID(13)
  @ReturnValue(type=NativeType.VARIANT)
  java.lang.Object getCachedPropertyValueEx(
    int propertyId,
    int ignoreDefaultValue);


  /**
   * @param patternId Mandatory int parameter.
   * @param riid Mandatory GUID parameter.
   * @return  Returns a value of type java.nio.Buffer
   */

  @VTID(14)
  java.nio.Buffer getCurrentPatternAs(
    int patternId,
    GUID riid);


  /**
   * @param patternId Mandatory int parameter.
   * @param riid Mandatory GUID parameter.
   * @return  Returns a value of type java.nio.Buffer
   */

  @VTID(15)
  java.nio.Buffer getCachedPatternAs(
    int patternId,
    GUID riid);


  /**
   * @param patternId Mandatory int parameter.
   * @return  Returns a value of type com4j.Com4jObject
   */

  @VTID(16)
  com4j.Com4jObject getCurrentPattern(
    int patternId);


  /**
   * @param patternId Mandatory int parameter.
   * @return  Returns a value of type com4j.Com4jObject
   */

  @VTID(17)
  com4j.Com4jObject getCachedPattern(
    int patternId);


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(18)
  mmarquee.automation.uiautomation.IUIAutomationElement getCachedParent();


  /**
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(19)
  mmarquee.automation.uiautomation.IUIAutomationElementArray getCachedChildren();


  /**
   * <p>
   * Getter method for the COM property "CurrentProcessId"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(20)
  int currentProcessId();


  /**
   * <p>
   * Getter method for the COM property "CurrentControlType"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(21)
  int currentControlType();


  /**
   * <p>
   * Getter method for the COM property "CurrentLocalizedControlType"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(22)
  java.lang.String currentLocalizedControlType();


  /**
   * <p>
   * Getter method for the COM property "CurrentName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(23)
  java.lang.String currentName();


  /**
   * <p>
   * Getter method for the COM property "CurrentAcceleratorKey"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(24)
  java.lang.String currentAcceleratorKey();


  /**
   * <p>
   * Getter method for the COM property "CurrentAccessKey"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(25)
  java.lang.String currentAccessKey();


  /**
   * <p>
   * Getter method for the COM property "CurrentHasKeyboardFocus"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(26)
  int currentHasKeyboardFocus();


  /**
   * <p>
   * Getter method for the COM property "CurrentIsKeyboardFocusable"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(27)
  int currentIsKeyboardFocusable();


  /**
   * <p>
   * Getter method for the COM property "CurrentIsEnabled"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(28)
  int currentIsEnabled();


  /**
   * <p>
   * Getter method for the COM property "CurrentAutomationId"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(29)
  java.lang.String currentAutomationId();


  /**
   * <p>
   * Getter method for the COM property "CurrentClassName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(30)
  java.lang.String currentClassName();


  /**
   * <p>
   * Getter method for the COM property "CurrentHelpText"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(31)
  java.lang.String currentHelpText();


  /**
   * <p>
   * Getter method for the COM property "CurrentCulture"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(32)
  int currentCulture();


  /**
   * <p>
   * Getter method for the COM property "CurrentIsControlElement"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(33)
  int currentIsControlElement();


  /**
   * <p>
   * Getter method for the COM property "CurrentIsContentElement"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(34)
  int currentIsContentElement();


  /**
   * <p>
   * Getter method for the COM property "CurrentIsPassword"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(35)
  int currentIsPassword();


  /**
   * <p>
   * Getter method for the COM property "CurrentNativeWindowHandle"
   * </p>
   * @return  Returns a value of type java.nio.Buffer
   */

  @VTID(36)
  java.nio.Buffer currentNativeWindowHandle();


  /**
   * <p>
   * Getter method for the COM property "CurrentItemType"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(37)
  java.lang.String currentItemType();


  /**
   * <p>
   * Getter method for the COM property "CurrentIsOffscreen"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(38)
  int currentIsOffscreen();


  /**
   * <p>
   * Getter method for the COM property "CurrentOrientation"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.OrientationType
   */

  @VTID(39)
  mmarquee.automation.uiautomation.OrientationType currentOrientation();


  /**
   * <p>
   * Getter method for the COM property "CurrentFrameworkId"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(40)
  java.lang.String currentFrameworkId();


  /**
   * <p>
   * Getter method for the COM property "CurrentIsRequiredForForm"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(41)
  int currentIsRequiredForForm();


  /**
   * <p>
   * Getter method for the COM property "CurrentItemStatus"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(42)
  java.lang.String currentItemStatus();


  /**
   * <p>
   * Getter method for the COM property "CurrentLabeledBy"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(44)
  mmarquee.automation.uiautomation.IUIAutomationElement currentLabeledBy();


  /**
   * <p>
   * Getter method for the COM property "CurrentAriaRole"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(45)
  java.lang.String currentAriaRole();


  /**
   * <p>
   * Getter method for the COM property "CurrentAriaProperties"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(46)
  java.lang.String currentAriaProperties();


  /**
   * <p>
   * Getter method for the COM property "CurrentIsDataValidForForm"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(47)
  int currentIsDataValidForForm();


  /**
   * <p>
   * Getter method for the COM property "CurrentControllerFor"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(48)
  mmarquee.automation.uiautomation.IUIAutomationElementArray currentControllerFor();


  /**
   * <p>
   * Getter method for the COM property "CurrentDescribedBy"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(49)
  mmarquee.automation.uiautomation.IUIAutomationElementArray currentDescribedBy();


  /**
   * <p>
   * Getter method for the COM property "CurrentFlowsTo"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(50)
  mmarquee.automation.uiautomation.IUIAutomationElementArray currentFlowsTo();


  /**
   * <p>
   * Getter method for the COM property "CurrentProviderDescription"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(51)
  java.lang.String currentProviderDescription();


  /**
   * <p>
   * Getter method for the COM property "CachedProcessId"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(52)
  int cachedProcessId();


  /**
   * <p>
   * Getter method for the COM property "CachedControlType"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(53)
  int cachedControlType();


  /**
   * <p>
   * Getter method for the COM property "CachedLocalizedControlType"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(54)
  java.lang.String cachedLocalizedControlType();


  /**
   * <p>
   * Getter method for the COM property "CachedName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(55)
  java.lang.String cachedName();


  /**
   * <p>
   * Getter method for the COM property "CachedAcceleratorKey"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(56)
  java.lang.String cachedAcceleratorKey();


  /**
   * <p>
   * Getter method for the COM property "CachedAccessKey"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(57)
  java.lang.String cachedAccessKey();


  /**
   * <p>
   * Getter method for the COM property "CachedHasKeyboardFocus"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(58)
  int cachedHasKeyboardFocus();


  /**
   * <p>
   * Getter method for the COM property "CachedIsKeyboardFocusable"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(59)
  int cachedIsKeyboardFocusable();


  /**
   * <p>
   * Getter method for the COM property "CachedIsEnabled"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(60)
  int cachedIsEnabled();


  /**
   * <p>
   * Getter method for the COM property "CachedAutomationId"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(61)
  java.lang.String cachedAutomationId();


  /**
   * <p>
   * Getter method for the COM property "CachedClassName"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(62)
  java.lang.String cachedClassName();


  /**
   * <p>
   * Getter method for the COM property "CachedHelpText"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(63)
  java.lang.String cachedHelpText();


  /**
   * <p>
   * Getter method for the COM property "CachedCulture"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(64)
  int cachedCulture();


  /**
   * <p>
   * Getter method for the COM property "CachedIsControlElement"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(65)
  int cachedIsControlElement();


  /**
   * <p>
   * Getter method for the COM property "CachedIsContentElement"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(66)
  int cachedIsContentElement();


  /**
   * <p>
   * Getter method for the COM property "CachedIsPassword"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(67)
  int cachedIsPassword();


  /**
   * <p>
   * Getter method for the COM property "CachedNativeWindowHandle"
   * </p>
   * @return  Returns a value of type java.nio.Buffer
   */

  @VTID(68)
  java.nio.Buffer cachedNativeWindowHandle();


  /**
   * <p>
   * Getter method for the COM property "CachedItemType"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(69)
  java.lang.String cachedItemType();


  /**
   * <p>
   * Getter method for the COM property "CachedIsOffscreen"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(70)
  int cachedIsOffscreen();


  /**
   * <p>
   * Getter method for the COM property "CachedOrientation"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.OrientationType
   */

  @VTID(71)
  mmarquee.automation.uiautomation.OrientationType cachedOrientation();


  /**
   * <p>
   * Getter method for the COM property "CachedFrameworkId"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(72)
  java.lang.String cachedFrameworkId();


  /**
   * <p>
   * Getter method for the COM property "CachedIsRequiredForForm"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(73)
  int cachedIsRequiredForForm();


  /**
   * <p>
   * Getter method for the COM property "CachedItemStatus"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(74)
  java.lang.String cachedItemStatus();


  /**
   * <p>
   * Getter method for the COM property "CachedLabeledBy"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(76)
  mmarquee.automation.uiautomation.IUIAutomationElement cachedLabeledBy();


  /**
   * <p>
   * Getter method for the COM property "CachedAriaRole"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(77)
  java.lang.String cachedAriaRole();


  /**
   * <p>
   * Getter method for the COM property "CachedAriaProperties"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(78)
  java.lang.String cachedAriaProperties();


  /**
   * <p>
   * Getter method for the COM property "CachedIsDataValidForForm"
   * </p>
   * @return  Returns a value of type int
   */

  @VTID(79)
  int cachedIsDataValidForForm();


  /**
   * <p>
   * Getter method for the COM property "CachedControllerFor"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(80)
  mmarquee.automation.uiautomation.IUIAutomationElementArray cachedControllerFor();


  /**
   * <p>
   * Getter method for the COM property "CachedDescribedBy"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(81)
  mmarquee.automation.uiautomation.IUIAutomationElementArray cachedDescribedBy();


  /**
   * <p>
   * Getter method for the COM property "CachedFlowsTo"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElementArray
   */

  @VTID(82)
  mmarquee.automation.uiautomation.IUIAutomationElementArray cachedFlowsTo();


  /**
   * <p>
   * Getter method for the COM property "CachedProviderDescription"
   * </p>
   * @return  Returns a value of type java.lang.String
   */

  @VTID(83)
  java.lang.String cachedProviderDescription();


    // Properties:
  }
