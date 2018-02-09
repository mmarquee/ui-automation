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
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.PointerByReference;

public interface IAccessible extends IUnknown {
    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID("{618736E0-3C3D-11CF-810C-00AA00389B71}");

    int Get_accParent(PointerByReference pdisp);
    int Get_accChildCount(LongByReference pcnt);
    int Get_accChild(int childId, PointerByReference cdisp);
    int Get_accName(int childId, PointerByReference pstr);
    int Get_accValue(int childId, PointerByReference pstr);
    int Get_accDescription(int childId, PointerByReference pstr);
    int Get_accRole(int childId, PointerByReference roleId);
    int Get_accState(int childId, PointerByReference stateId);
    int Get_accHelp(int childId, PointerByReference pstr);
    int Get_accHelpTopic(PointerByReference pstr, int childId, PointerByReference topic);
    int Get_accKeyboardShortcut(int childId, PointerByReference pstr);
    int Get_accFocus(PointerByReference ptr);
    int Get_accSelection(PointerByReference ptr);
    int Get_accDefaultAction(int childId, PointerByReference pstr);
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
    int Set_accName(int childId, String str);
    int Set_accValue(int childId, String str);
}
