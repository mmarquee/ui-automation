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
package mmarquee.automation.uiautomation.conditions;

import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.ptr.IntByReference;

/**
 * Represents a condition based on a property value that is used to find UI Automation elements.
 *
 * @author Mark Humphreys
 * Date 26/12/2017
 */
public interface IUIAutomationPropertyCondition extends IUIAutomationCondition {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID(
            "{99EBF2CB-5578-4267-9AD4-AFD6EA77E94B}");

    /**
     * Retrieves the identifier of the property on which this condition is based.
     * @param propertyId The property id
     * @return Error code
     */
    int getPropertyId(Integer propertyId);

    /**
     * Retrieves the property value that must be matched for the condition to be true.
     * @param value The value
     * @return Error code
     */
    int getPropertyValue(Variant.VARIANT.ByReference value);

    /**
     * Retrieves a set of flags that specify how the condition is applied.
     * @param flags The flags
     * @return Error code
     */
    int getPropertyConditionFlags(IntByReference flags);
}
