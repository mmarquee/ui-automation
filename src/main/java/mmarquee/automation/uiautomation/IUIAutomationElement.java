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
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.COM.IUnknown;
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

    int setFocus();
    int getCurrentName (PointerByReference sr);
    int getCurrentClassName (PointerByReference sr);
    int findAll (TreeScope scope, Pointer condition, PointerByReference sr);
    int findFirst (TreeScope scope, Pointer condition, PointerByReference sr);
    int findAllBuildCache(int scope, Pointer condition, Pointer cr, PointerByReference sr);
    int findFirstBuildCache(int scope, Pointer condition, Pointer cr, PointerByReference sr);
    int buildUpdatedCache (Pointer cacheRequest,
                           PointerByReference updatedElement);
    int getClickablePoint(WinDef.POINT.ByReference clickable, WinDef.BOOLByReference gotClickable);
    int getCurrentIsPassword(IntByReference value);
    int getCurrentAriaRole (PointerByReference sr);
    int getCurrentPattern(Integer patternId, PointerByReference pbr);
    int getCurrentPropertyValue(int propertyId, Variant.VARIANT.ByReference value);
    int getCurrentControlType(IntByReference ipr);
    int getCurrentProviderDescription(PointerByReference sr);
    int getCurrentFrameworkId (PointerByReference retVal);
    int getCurrentItemStatus (PointerByReference retVal);
    int getCurrentOrientation (IntByReference retVal);
    int getCurrentAcceleratorKey (PointerByReference retVal);
    int getCurrentProcessId (IntByReference retVal);
    int getCurrentBoundingRectangle (WinDef.RECT retVal);
    int getCurrentLocalizedControlType (PointerByReference retVal);
    int getCurrentIsOffscreen (WinDef.BOOLByReference retVal);
    int getCurrentIsEnabled (WinDef.BOOLByReference retVal);
    int getCurrentIsControlElement (WinDef.BOOLByReference retVal);
    int getCurrentIsContentElement (WinDef.BOOLByReference retVal);
    int getRuntimeId(PointerByReference runtimeId);
    int getCurrentAutomationId(PointerByReference retVal);
    int getCurrentCulture (IntByReference retVal);
    int getCachedName(PointerByReference retVal);
}

