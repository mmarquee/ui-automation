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
package mmarquee.automation.accessibility;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.LongByReference;
import com.sun.jna.ptr.PointerByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;


/**
 * Client functions from the oleacc (MSAA) library.
 *
 * See https://msdn.microsoft.com/en-us/library/windows/desktop/dd742692(v=vs.85).aspx
 *
 * @author Mark Humphreys
 * Date 06/01/2018
 */
public interface Oleacc extends StdCallLibrary {
    Oleacc INSTANCE =
            (Oleacc) Native.loadLibrary("oleacc", Oleacc.class, W32APIOptions.DEFAULT_OPTIONS);

/*
STDAPI AccessibleObjectFromWindow(
  _In_  HWND   hwnd,
  _In_  DWORD  dwObjectID,
  _In_  REFIID riid,
  _Out_ void   **ppvObject
);
 */
    int AccessibleObjectFromWindow(WinDef.HWND hwnd,
                                   WinDef.DWORD objid,
                                   Guid.GUID.ByReference refiid,
                                   PointerByReference ptr);


    /*
    STDAPI AccessibleObjectFromPoint(
  _In_  POINT       ptScreen,
  _Out_ IAccessible **ppacc,
  _Out_ VARIANT     *pvarChild
);
     */
//    int AccessibleObjectFromPoint(, Pointer pAccPointer, );

    /**
     * Retrieves the version number and build number of the Microsoft Active Accessibility file Oleacc.dll.
     *
     * @param pdwVer
     *               Address of a DWORD that receives the version number. The major version number is placed in the
     *               high word, and the minor version number is placed in the low word.
     * @param pdwBuild
     *              Address of a DWORD that receives the build number. The major build number is placed in the high
     *              word, and the minor build number is placed in the low word.
     */
    void GetOleaccVersionInfo(WinDef.DWORDByReference pdwVer,
                              WinDef.DWORDByReference pdwBuild);

    /*
    STDAPI AccessibleChildren(
  _In_  IAccessible *paccContainer,
  _In_  LONG        iChildStart,
  _In_  LONG        cChildren,
  _Out_ VARIANT     *rgvarChildren,
  _Out_ LONG        *pcObtained
);
     */


    int AccessibleChildren(Pointer pAccPointer,
                           int startFrom,
                           long childCount,
                           Variant.VARIANT tableRef,
                           LongByReference returnCount);

    /*
    HANDLE WINAPI GetProcessHandleFromHwnd(
  _In_ HWND hwnd
);

     */

    WinNT.HANDLE GetProcessHandleFromHwnd(WinDef.HWND hwnd);

    /*
    STDAPI WindowFromAccessibleObject(
  _In_  IAccessible *pacc,
  _Out_ HWND        *phwnd
);
     */

    /*
    UINT GetRoleText(
  _In_  DWORD  dwRole,
  _Out_ LPTSTR lpszRole,
  _In_  UINT   cchRoleMax
);
     */
    int GetRoleText(int roleId,
                    char[] buff,
                    int i);

    /*
    UINT GetStateText(
  _In_  DWORD  dwStateBit,
  _Out_ LPTSTR lpszStateBit,
  _In_  UINT   cchStateBitMax
);

     */
    int GetStateText(int roleId,
                    char[] buff,
                    int i);
}
