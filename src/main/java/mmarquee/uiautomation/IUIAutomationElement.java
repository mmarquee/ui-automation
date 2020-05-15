/*
 * Copyright 2016-18 inpwtepydjuf@gmail.com
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
package mmarquee.uiautomation;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * Wrapper for the IUIAutomationElement3 automation interface.
 *
 * @author Mark Humphreys
 * Date 06/07/2016.
 */
public interface IUIAutomationElement extends IUnknown {

    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{D22108AA-8AC5-49A5-837B-37BBB3D7591E}");

    /**
     * Sets the keyboard focus to this UI Automation element.
     *
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int setFocus();

    /**
     * Retrieves the name of the element.
     *
     * @param sr The reference to the name
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int getCurrentName (PointerByReference sr);

    /**
     * Retrieves the class name of the element.
     *
     * @param sr The reference to the class name
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int getCurrentClassName (PointerByReference sr);

    /**
     * Returns all UI Automation elements that satisfy the specified condition.
     *
     * @param scope The scope
     * @param condition The condition(s)
     * @param sr Found elements
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int findAll (TreeScope scope, Pointer condition, PointerByReference sr);

    /**
     * Retrieves the first child or descendant element that matches the
     * specified condition.
     *
     * @param scope The scope
     * @param condition The condition(s)
     * @param sr Found element
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int findFirst (TreeScope scope, Pointer condition, PointerByReference sr);

    /**
     * Returns all UI Automation elements that satisfy the specified condition,
     * prefetches the requested properties and control patterns, and stores the
     * prefetched items in the cache.
     *
     * @param scope The scope
     * @param condition The condition(s)
     * @param cr The cache
     * @param sr Found element(s)
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int findAllBuildCache(int scope,
                          Pointer condition,
                          Pointer cr,
                          PointerByReference sr);
    int findFirstBuildCache(int scope,
                            Pointer condition,
                            Pointer cr,
                            PointerByReference sr);
    int buildUpdatedCache (Pointer cacheRequest,
                           PointerByReference updatedElement);

    /**
     * Retrieves a point on the element that can be clicked.
     *
     * @param clickable Receives the physical screen coordinates of a point
     *                  that can be used by a client to click this element.
     * @param gotClickable Receives TRUE if a clickable point was retrieved,
     *                     or FALSE otherwise.
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int getClickablePoint(WinDef.POINT.ByReference clickable,
                          WinDef.BOOLByReference gotClickable);
    int getCurrentIsPassword(IntByReference value);
    int getCurrentAriaRole (PointerByReference sr);
    int getCurrentPattern(Integer patternId, PointerByReference pbr);
    int getCurrentPropertyValue(int propertyId,
                                Variant.VARIANT.ByReference value);
    int getCurrentControlType(IntByReference ipr);
    int getCurrentProviderDescription(PointerByReference sr);
    int getCurrentFrameworkId (PointerByReference retVal);
    int getCurrentItemStatus (PointerByReference retVal);

    /**
     * Retrieves a value that indicates the orientation of the element.
     *
     * @param retVal The orientation of the element
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int getCurrentOrientation (IntByReference retVal);
    int getCurrentAcceleratorKey (PointerByReference retVal);
    int getCurrentProcessId (IntByReference retVal);
    int getCurrentBoundingRectangle (WinDef.RECT retVal);
    int getCurrentLocalizedControlType (PointerByReference retVal);
    int getCurrentIsOffscreen (WinDef.BOOLByReference retVal);
    int getCurrentIsEnabled (WinDef.BOOLByReference retVal);
    int getCurrentIsControlElement (WinDef.BOOLByReference retVal);
    int getCurrentIsContentElement (WinDef.BOOLByReference retVal);

    /**
     * Retrieves the unique identifier assigned to the UI element.
     *
     * @param runtimeId The runtime is of the element
     * @return If this method succeeds, it returns S_OK. Otherwise, it returns
     *         an HRESULT error code.
     */
    int getCurrentRuntimeId(PointerByReference runtimeId);
    int getCurrentAutomationId(PointerByReference retVal);
    int getCurrentCulture (IntByReference retVal);
    int getCachedName(PointerByReference retVal);
}

