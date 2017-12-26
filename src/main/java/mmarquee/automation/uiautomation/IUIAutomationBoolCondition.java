/*
 * Copyright 2017 inpwtepydjuf@gmail.com
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

import com.sun.jna.platform.win32.Guid;

/**
 * Represents a condition that can be either TRUE (selects all elements) or FALSE (selects no elements).
 */
public interface IUIAutomationBoolCondition extends IUIAutomationCondition {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID(
            "1B4E1F2E-75EB-4D0B-8952-5A69988E2307");

    /**
     * Retrieves the value of the condition: either TRUE or FALSE.
     * @param boolVal The condition
     * @return Error status
     */
    int getBooleanValue(Integer boolVal);
}
