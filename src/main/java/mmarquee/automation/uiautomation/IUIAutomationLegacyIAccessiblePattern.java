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

import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.ptr.PointerByReference;

public interface IUIAutomationLegacyIAccessiblePattern extends IUnknown {
    /**
     * The interface IID for QueryInterface et al.
     */
    Guid.IID IID = new Guid.IID(
            "{828055AD-355B-4435-86D5-3B51C14A9B1B}");

    //   int select(Integer flagsSelect);
    //   int doDefaultAction();

    /**
     * Sets the value for the element.
     *
     * @param sr The value
     * @return Result from call
     */
    int setValue(WTypes.BSTR sr);
    //  int getCurrentChildId(PointerByReference pRetVal);

    /**
     * Gets the current name from accessibility routines.
     *
     * @param pszName The name
     * @return State of the call
     */
    int getCurrentName(PointerByReference pszName);
    //  int getCurrentValue(PointerByReference pszValue);
    //  int getCurrentDescription(PointerByReference pszDescription);
    //  int getCurrentRole(PointerByReference pdwRole);
    //  int getCurrentState(PointerByReference pdwState);
    //  int getCurrentHelp(PointerByReference pszHelp);
    //  int getCurrentKeyboardShortcut(PointerByReference pszKeyboardShortcut);
    //  int getCurrentSelection(PointerByReference pvarSelectedChildren);
    //  int getCurrentDefaultAction(PointerByReference pszDefaultAction);
    // int getCachedChildId(PointerByReference pRetVal);
    // int getCachedName(PointerByReference pszName);
    // int getCachedValue(PointerByReference pszValue);
    // int getCachedDescription(PointerByReference pszDescription);
    // int getCachedRole(PointerByReference pdwRole);
    // int getCachedState(PointerByReference pdwState);
    // int getCachedHelp(PointerByReference pszHelp);
    // int getCachedKeyboardShortcut(PointerByReference pszKeyboardShortcut);
    // int getCachedSelection(PointerByReference pvarSelectedChildren);
    // int getCachedDefaultAction(PointerByReference pszDefaultAction);
    // int getIAccessible(PointerByReference ppAccessible);
}
