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

@IID("{32EBA289-3583-42C9-9C59-3B6D9A1E9B6A}")
public interface IUIAutomationTextPattern extends Com4jObject {
  // Methods:
    /**
     * @param child Mandatory mmarquee.automation.uiautomation.IUIAutomationElement parameter.
     * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRange
     */

    @VTID(4)
    mmarquee.automation.uiautomation.IUIAutomationTextRange rangeFromChild(
      mmarquee.automation.uiautomation.IUIAutomationElement child);


    /**
     * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRangeArray
     */

    @VTID(5)
    mmarquee.automation.uiautomation.IUIAutomationTextRangeArray getSelection();


    /**
     * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRangeArray
     */

    @VTID(6)
    mmarquee.automation.uiautomation.IUIAutomationTextRangeArray getVisibleRanges();


    /**
     * <p>
     * Getter method for the COM property "DocumentRange"
     * </p>
     * @return  Returns a value of type mmarquee.automation.uiautomation.IUIAutomationTextRange
     */

    @VTID(7)
    mmarquee.automation.uiautomation.IUIAutomationTextRange documentRange();


    /**
     * <p>
     * Getter method for the COM property "SupportedTextSelection"
     * </p>
     * @return  Returns a value of type mmarquee.automation.uiautomation.SupportedTextSelection
     */

    @VTID(8)
    mmarquee.automation.uiautomation.SupportedTextSelection supportedTextSelection();


    // Properties:
  }
