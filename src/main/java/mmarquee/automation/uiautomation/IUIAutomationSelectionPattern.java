/*
 * Copyright 2016 inpwtepydjuf@gmail.com
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
 * Created by Mark Humphreys on 13/07/2016.
 */
public interface IUIAutomationSelectionPattern extends IUnknown {
    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID(
            "{5ED5202E-B2AC-47A6-B638-4B0BF140D78E}");

    int AddRef();
    int Release();
    WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pointerByReference);

    int getCurrentSelection(PointerByReference retVal);

    class Converter {
        private static int METHODS = 6; // 0-2 IUnknown, 3-5 IUIAutomationSelectionPattern

        public static IUIAutomationSelectionPattern PointerToInterface(final PointerByReference ptr) {
            final Pointer interfacePointer = ptr.getValue();
            final Pointer vTablePointer = interfacePointer.getPointer(0);
            final Pointer[] vTable = new Pointer[METHODS];
            vTablePointer.read(0, vTable, 0, vTable.length);
            return new IUIAutomationSelectionPattern() {
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

                public int getCurrentSelection(PointerByReference retVal)
                {
                    Function f = Function.getFunction(vTable[3], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }
            };
        }
    }
}
