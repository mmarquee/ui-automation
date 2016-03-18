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

@IID("{6552B038-AE05-40C8-ABFD-AA08352AAB86}")
public interface IUIAutomationTextChildPattern extends Com4jObject {
  // Methods:
  /**
   * <p>
   * Getter method for the COM property "TextContainer"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationElement
   */

  @VTID(3)
  mmarquee.automation.uiautomation.IUIAutomationElement textContainer();


  /**
   * <p>
   * Getter method for the COM property "TextRange"
   * </p>
   * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRange
   */

  @VTID(4)
  mmarquee.automation.uiautomation.IUIAutomationTextRange textRange();


  // Properties:
}
