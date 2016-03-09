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

import com4j.Holder;
import com4j.IID;
import com4j.VTID;

@IID("{506A921A-FCC9-409F-B23B-37EB74106872}")
public interface IUIAutomationTextPattern2 extends mmarquee.automation.uiautomation.IUIAutomationTextPattern {
  // Methods:
  /**
   * @param annotation Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRange
   */

  @VTID(9)
  mmarquee.automation.uiautomation.IUIAutomationTextRange rangeFromAnnotation(
    mmarquee.automation.uiautomation.IUIAutomationElement annotation);


  /**
   * @param isActive Mandatory Holder<Integer> parameter.
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRange
   */

  @VTID(10)
  mmarquee.automation.uiautomation.IUIAutomationTextRange getCaretRange(
    Holder<Integer> isActive);


  // Properties:
}
