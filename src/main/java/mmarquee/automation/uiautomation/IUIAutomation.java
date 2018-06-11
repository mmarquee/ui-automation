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
import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * Wrapper for the IUIAutomation COM interface.
 *
 * Exposes methods that enable UI Automation client applications to discover, access, and filter
 * UI Automation elements. UI Automation exposes every element of the UI Automation as an object represented
 * by the IUIAutomation interface. The members of this interface are not specific to a particular element.
 *
 * @author Mark Humphreys
 * Date 06/07/2016.
 */
public interface IUIAutomation extends IUnknown {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{30CBE57D-D9D0-452A-AB13-7AC5AC4825EE}");

    /**
     * The IID for the library itself.
     */
    Guid.GUID CLSID = new Guid.GUID("{FF48DBA4-60EF-4201-AA87-54103EEF594E}");

    /**
     * Retrieves the UI Automation element that represents the desktop.
     * @param root The root element
     * @return Error code
     */
    int getRootElement(PointerByReference root);

    /**
     * Retrieves a UI Automation element for the specified window.
     * @param hwnd The handle
     * @param element The window associated with that handle
     * @return Error code
     */
    int getElementFromHandle(WinDef.HWND hwnd, PointerByReference element);

    /**
     * Creates a condition that selects elements that match both of two conditions.
     * @param condition1 First condition
     * @param condition2 Second condition
     * @param condition The resulting condition
     * @return Error code
     */
    int createAndCondition(Pointer condition1, Pointer condition2, PointerByReference condition);

    /**
     * Creates a condition that selects elements that have a property with the specified value.
     * @param propertyId The property id
     * @param value The property value
     * @param condition The resulting condition
     * @return Error code
     */
    int createPropertyCondition(int propertyId, Variant.VARIANT.ByValue value, PointerByReference condition);

    /**
     * Creates a combination of two conditions where a match exists if either of the conditions is true.
     * @param condition1 First condition
     * @param condition2 Second condition
     * @param condition Resulting condition
     * @return Error code
     */
    int createOrCondition(Pointer condition1, Pointer condition2, PointerByReference condition);

    /**
     * Retrieves a predefined condition that selects all elements.
     * @param condition The created condition
     * @return Error code
     */
    int createTrueCondition(PointerByReference condition);

    /**
     * Creates a condition that is always false.
     * @param condition The resulting condition
     * @return Error code
     */
    int createFalseCondition(PointerByReference condition);

    /**
     * Compares two UI Automation elements to determine whether they represent the same underlying UI element.
     * @param element1 First element
     * @param element2 Second element
     * @param same Equality
     * @return Error code
     */
    int compareElements(Pointer element1, Pointer element2, IntByReference same);

    /**
     * Creates a condition that is the negative of a specified condition.
     * @param condition The condition
     * @param retval The return condition
     * @return Error code
     */
    int createNotCondition(Pointer condition, PointerByReference retval);

    /**
     * Retrieves the registered programmatic name of a control pattern.
     * @param patternId Pattern Id
     * @param retval Return value
     * @return Error code
     */
    int getPatternProgrammaticName(int patternId, PointerByReference retval);

    /**
     * Retrieves the UI Automation element that has the input focus.
     * @param element The element
     * @return Error code
     */
    int getFocusedElement(PointerByReference element);

    /**
     * Retrieves a tree walker object that can be used to traverse the UI Automation tree.
     * @param condition The condition to walk the tree with
     * @param walker The resulting tree walker
     * @return Error code
     */
    int createTreeWalker(PointerByReference condition, PointerByReference walker);

    /**
     * Gets the control tree walker.
     * @param walker The walker
     * @return Error code
     */
    int getControlViewWalker(PointerByReference walker);

    /**
     * Registers a method that handles Microsoft UI Automation events.
     * @param eventId The event
     * @param scope The scope of events to be handled; that is, whether they are on the element itself, or on
     *              its ancestors and descendants.
     * @param element The element
     * @param cacheRequest The cache request (or null if not required)
     * @param handler The returned handler
     * @return Error code
     */
    int addAutomationEventHandler(IntByReference eventId, TreeScope scope, Pointer element, PointerByReference cacheRequest, PointerByReference handler);

    /**
     * Removes the specified UI Automation event handler.
     * @param eventId The event id
     * @param element The element
     * @param handler The handler
     * @return Error code
     */
    int removeAutomationEventHandler(IntByReference eventId, PointerByReference element, PointerByReference handler);

    /**
     * Retrieves the UI Automation element at the specified point on the desktop.
     * @param pt The point
     * @param element The element
     * @return Error code
     */
    int elementFromPoint(WinDef.POINT pt, PointerByReference element);

    /**
     * Creates a cache request.
     * @param request The cache request
     * @return Error code
     */
    int createCacheRequest(PointerByReference request);

    /**
     * Retrieves the properties that might be supported on a UI Automation element.
     * @param element The element
     * @param ids array of Ids (int)
     * @param names array of names (BSTR)
     * @return Error code
     */
    int pollForPotentialSupportedProperties(Pointer element, PointerByReference ids, PointerByReference names);

    /**
     * Retrieves the control patterns that might be supported on a UI Automation element.
     * @param element The element
     * @param ids array of Ids (int)
     * @param names array of names (BSTR)
     * @return Error code
     */
    int pollForPotentialSupportedPatterns(Pointer element, PointerByReference ids, PointerByReference names);
}
