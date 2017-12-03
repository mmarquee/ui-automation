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

import com.sun.jna.platform.win32.Guid;

/**
 * Wrapper around the IUIAutomation2 interface, only implementing the extra methods.
 *
 * This interface is supported fro Windows 8.1 desktop onwards
 *
 * @author Mark Humphreys
 * Date 24/07/2016.
 */
public interface IUIAutomationElement2 extends IUIAutomationElement {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID("{34723aff-0c9d-49d0-9896-7ab52df8cd8a}");

    // IUnknown
//    int AddRef();
//    int Release();
//    WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pointerByReference);

    // IUIAutomationElement
    /*
    int setFocus();
    int getCurrentName(PointerByReference sr);
    int getCurrentClassName(PointerByReference sr);
    int findAll(TreeScope scope, Pointer condition, PointerByReference sr);
    int findFirst(TreeScope scope, Pointer condition, PointerByReference sr);
    int getClickablePoint(WinDef.POINT.ByReference clickable, WinDef.BOOLByReference gotClickable);
    int getCurrentIsPassword(IntByReference value);
    int getCurrentAriaRole(PointerByReference sr);
    int getCurrentPattern(Integer patternId, PointerByReference pbr);
    int getCurrentPropertyValue(int propertyId, Variant.VARIANT.ByReference value);
    int getCurrentControlType(IntByReference ipr);
    int getCurrentProviderDescription(PointerByReference sr);
    int getCurrentFrameworkId(PointerByReference retVal);
    int getCurrentItemStatus(PointerByReference retVal);
    int getCurrentOrientation(IntByReference retVal);
    int getCurrentAcceleratorKey(PointerByReference retVal);
    int getCurrentProcessId(IntByReference retVal);
    int getCurrentBoundingRectangle(WinDef.RECT retVal);
    int getCurrentLocalizedControlType(PointerByReference retVal);
    int getCurrentIsOffscreen(WinDef.BOOLByReference retVal);
    int getCurrentIsEnabled(WinDef.BOOLByReference retVal);
    int getCurrentIsControlElement(WinDef.BOOLByReference retVal);
    int getCurrentIsContentElement(WinDef.BOOLByReference retVal);
    int getRuntimeId(PointerByReference runtimeId);
    int getCurrentAutomationId(PointerByReference retVal);
    int getCurrentCulture(IntByReference retVal);
*/
    // IUIAutomationElement2
}