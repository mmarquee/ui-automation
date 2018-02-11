/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
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

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.ptr.PointerByReference;

/**
 * Wrapper around the IUIAutomation7 interface.
 *
 * This interface is supported in Windows 10, version 1703 [desktop apps only] upwards
 *
 * @author Mark Humphreys
 * Date 24/07/2016.
 */
public interface IUIAutomationElement7 extends IUIAutomationElement6 {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{204E8572-CFC3-4C11-B0C8-7DA7420750B7}");

    /**
     * Find all matching elements in the specified order.
     * @param scope The scope
     * @param condition The condition to match
     * @param options The tree navigation order
     * @param root Start element
     * @param foundArray The matching elements
     * @return Error code
     */
    int findAllWithOptions(TreeScope scope,
                           Pointer condition,
                           Pointer options,
                           Pointer root,
                           PointerByReference foundArray);

    /**
     * Finds all matching elements in the specified order, but also caches their properties and patterns.
     * @param scope The scope
     * @param condition The condition to match
     * @param options The tree navigation order
     * @param root Start element
     * @param foundArray The matching elements
     * @return Error code
     */
    int findAllWithOptionsBuildCache(TreeScope scope,
                                     Pointer condition,
                                     Pointer options,
                                     Pointer root,
                                     PointerByReference foundArray);

    /**
     * Finds the first matching element in the specified order.
     * @param scope The scope
     * @param condition The condition to match
     * @param options The tree navigation order
     * @param root Start element
     * @param found The matching element
     * @return Error code
     */
    int findFirstWithOptions(TreeScope scope,
                             Pointer condition,
                             Pointer options,
                             Pointer root,
                             PointerByReference found);

    /**
     * Finds the first matching element in the specified order, but also caches its properties and pattern.
     * @param scope The scope
     * @param condition The condition to match
     * @param options The tree navigation order
     * @param root Start element
     * @param found The matching element
     * @return Error code
     */
    int findFirstWithOptionsBuildCache(TreeScope scope,
                                       Pointer condition,
                                       Pointer options,
                                       Pointer root,
                                       PointerByReference found);

    /**
     * Gets metadata from the UI Automation element that indicates how the information should be interpreted.
     *
     * For example, should the string "1/4" be interpreted as a fraction or a date?
     * @param target The property to retrieve
     * @param metadata The type of metadata
     * @param retVal The metadata
     * @return Error code
     */
    int getCurrentMetadataValue(Integer target,
                                Integer metadata,
                                Variant.VARIANT.ByReference retVal);
}
