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

import com.sun.jna.Function;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.IUnknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

/**
 * Created by Mark Humphreys on 24/07/2016.
 *
 * Wrapper around the IUIAutomation4 interface, only implementing the extra methods.
 *
 * This interface is supported fro Windows 8.1 desktop onwards
 */
public interface IUIAutomationElement3 extends IUnknown {
    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID("{8471DF34-AEE0-4A01-A7DE-7DB9AF12C296}");

    int AddRef();
    int Release();
    WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pointerByReference);

    int showContextMenu();

    class Converter {
        private static int UIAutomationElement_Methods = 94; // 0-2 IUnknown, 3-84 IUIAutomationElement, 85-90 IUIAutomationElement2, 91-93 IUIAutomationElement3

        public static IUIAutomationElement3 PointerToInterface(final PointerByReference ptr) {
            final Pointer interfacePointer = ptr.getValue();
            final Pointer vTablePointer = interfacePointer.getPointer(0);
            final Pointer[] vTable = new Pointer[UIAutomationElement_Methods];
            vTablePointer.read(0, vTable, 0, vTable.length);
            return new IUIAutomationElement3() {

                // IUnknown
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

                public int showContextMenu() {
                    Function f = Function.getFunction(vTable[91], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer});
                }
            };
        }
    }
}
