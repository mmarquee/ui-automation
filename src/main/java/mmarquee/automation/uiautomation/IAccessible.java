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

import com.sun.jna.platform.win32.COM.IDispatch;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * The IAccessible interface.
 */
public interface IAccessible extends IDispatch {
    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID("{618736E0-3C3D-11CF-810C-00AA00389B71}");

    /**
     * Get the accessible parent.
     *
     * @param pdisp the accessible element
     * @return The result
     */
    int getAccParent(PointerByReference pdisp);
    int getAccChildCount(LongByReference pcnt);
    int getAccChild(int childId, PointerByReference cdisp);
    int getAccName(int childId, PointerByReference pstr);
    int getAccValue(int childId, PointerByReference pstr);
    int getAccDescription(int childId, PointerByReference pstr);
    int getAccRole(int childId, PointerByReference roleId);
    int getAccState(int childId, PointerByReference stateId);
    int getAccHelp(int childId, PointerByReference pstr);
    int Get_accHelpTopic(PointerByReference pstr, int childId, PointerByReference topic);
    int getAccKeyboardShortcut(int childId, PointerByReference pstr);
    int getAccFocus(PointerByReference ptr);
    int getAccSelection(PointerByReference ptr);
    int getAccDefaultAction(int childId, PointerByReference pstr);
    int accSelect(long flags, int childId);
    int accLocation(IntByReference pxLeft,
                    IntByReference pyTop,
                    IntByReference pcxWidth,
                    IntByReference pcyHeight,
                    Variant varChild);  // Not sure about this definition
    int accNavigate(long navDir, PointerByReference varStart,
                    PointerByReference vv);
    int accHitTest(long xLeft, long yTop, PointerByReference res);
    int accDoDefaultAction(int childId);
    int setAccName(int childId, String str);
    int setAccValue(int childId, String str);
}
