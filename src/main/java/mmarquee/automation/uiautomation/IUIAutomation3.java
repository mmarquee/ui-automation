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

import com4j.IID;
import com4j.VTID;
import mmarquee.automation.eventhandlers.raw.IUIAutomationTextEditTextChangedEventHandler;

@IID("{73D768DA-9B51-4B89-936E-C209290973E7}")
/**
 * Requires Windows 8.1 or higher
 */
public interface IUIAutomation3 extends mmarquee.automation.uiautomation.IUIAutomation2 {
  // Methods:
  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param scope Mandatory mmarquee.automation.uiautomation.TreeScope parameter.
   * @param textEditChangeType Mandatory mmarquee.automation.uiautomation.TextEditChangeType parameter.
   * @param cacheRequest Mandatory mmarquee.automation.uiautomation.IUIAutomationCacheRequest parameter.
   * @param handler Mandatory mmarquee.automation.uiautomation.IUIAutomationTextEditTextChangedEventHandler parameter.
   */

  @VTID(64)
  void addTextEditTextChangedEventHandler(
    mmarquee.automation.uiautomation.IUIAutomationElement element,
    mmarquee.automation.uiautomation.TreeScope scope,
    mmarquee.automation.uiautomation.TextEditChangeType textEditChangeType,
    mmarquee.automation.uiautomation.IUIAutomationCacheRequest cacheRequest,
    IUIAutomationTextEditTextChangedEventHandler handler);


  /**
   * @param element Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @param handler Mandatory mmarquee.automation.uiautomation.IUIAutomationTextEditTextChangedEventHandler parameter.
   */

  @VTID(65)
  void removeTextEditTextChangedEventHandler(
    mmarquee.automation.uiautomation.IUIAutomationElement element,
    IUIAutomationTextEditTextChangedEventHandler handler);


  // Properties:
}
