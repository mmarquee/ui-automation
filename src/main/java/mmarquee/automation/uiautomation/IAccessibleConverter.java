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

import com.sun.jna.Function;
import com.sun.jna.Pointer;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.OaIdl;
import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public class IAccessibleConverter {

    public static IAccessible pointerToInterface(final PointerByReference ptr) {

        final int IAccessible_Methods = 28; // IUnknown = 3, IDispatch = 4, IAccessible = 21

        final Pointer interfacePointer = ptr.getValue();
        final Pointer vTablePointer = interfacePointer.getPointer(0);
        final Pointer[] vTable = new Pointer[IAccessible_Methods];
        vTablePointer.read(0, vTable, 0, vTable.length);

        return new IAccessible() {

            // IUnknown (0,1,2)
            @Override
            public WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pointerByReference) {
                Function f = Function.getFunction(vTable[0], Function.ALT_CONVENTION);
                return new WinNT.HRESULT(f.invokeInt(new Object[]{interfacePointer, byValue, pointerByReference}));
            }

            @Override
            public int AddRef() {
                Function f = Function.getFunction(vTable[1], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer});
            }

            public int Release() {
                Function f = Function.getFunction(vTable[2], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer});
            }

            // IDispatch (3,4,5,6)

            public WinNT.HRESULT GetTypeInfoCount(WinDef.UINTByReference var1) {
                Function f = Function.getFunction(vTable[3], Function.ALT_CONVENTION);
                return new WinNT.HRESULT(f.invokeInt(new Object[]{interfacePointer, var1}));
            }

            public WinNT.HRESULT GetTypeInfo(WinDef.UINT var1, WinDef.LCID var2, PointerByReference var3) {
                Function f = Function.getFunction(vTable[4], Function.ALT_CONVENTION);
                return new WinNT.HRESULT(f.invokeInt(new Object[]{interfacePointer, var1, var2, var2}));
            }

            public WinNT.HRESULT GetIDsOfNames(Guid.REFIID var1,
                                               WString[] var2,
                                               int var3,
                                               WinDef.LCID var4,
                                               OaIdl.DISPIDByReference var5) {
                Function f = Function.getFunction(vTable[5], Function.ALT_CONVENTION);
                return new WinNT.HRESULT(f.invokeInt(new Object[]{interfacePointer, var1, var2, var3, var4, var5}));
            }

            public WinNT.HRESULT Invoke(OaIdl.DISPID var1,
                                        Guid.REFIID var2,
                                        WinDef.LCID var3,
                                        WinDef.WORD var4,
                                        OleAuto.DISPPARAMS.ByReference var5,
                                        com.sun.jna.platform.win32.Variant.VARIANT.ByReference var6,
                                        com.sun.jna.platform.win32.OaIdl.EXCEPINFO.ByReference var7,
                                        IntByReference var8) {
                Function f = Function.getFunction(vTable[6], Function.ALT_CONVENTION);
                return new WinNT.HRESULT (f.invokeInt(
                        new Object[]{interfacePointer, var1, var2, var3, var4, var5, var6, var7, var8}));
            }

            // IAccessible

            public int Get_accName(int childId, PointerByReference pstr) {
                Function f = Function.getFunction(vTable[10], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, childId, pstr});
            }

            public int Get_accValue(int childId, PointerByReference pstr) {
                Function f = Function.getFunction(vTable[11], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, childId, pstr});
            }

            public int Get_accDescription(int childId, PointerByReference pstr) {
                Function f = Function.getFunction(vTable[12], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, childId, pstr});
            }
        };
    }
}


