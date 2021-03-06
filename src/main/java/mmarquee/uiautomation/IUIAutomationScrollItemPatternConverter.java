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
package mmarquee.uiautomation;

import com.sun.jna.Function;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

public class IUIAutomationScrollItemPatternConverter {
    public static IUIAutomationScrollItemPattern pointerToInterface(
            final PointerByReference ptr) {
        final int METHODS = 8; // 0-2 IUnknown,
                               // 3-7 IUIAutomationSelectionItemPattern
        final Pointer interfacePointer = ptr.getValue();
        final Pointer vTablePointer = interfacePointer.getPointer(0);
        final Pointer[] vTable = new Pointer[METHODS];
        vTablePointer.read(0, vTable, 0, vTable.length);
        return new IUIAutomationScrollItemPattern() {
            // IUnknown

            @Override
            public WinNT.HRESULT QueryInterface(Guid.REFIID byValue,
                                                PointerByReference pbr) {
                Function f = Function.getFunction(vTable[0],
                        Function.ALT_CONVENTION);
                return new WinNT.HRESULT(f.invokeInt(
                        new Object[]{interfacePointer,
                                byValue, pbr}));
            }

            @Override
            public int AddRef() {
                Function f = Function.getFunction(vTable[1],
                        Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer});
            }

            public int Release() {
                Function f = Function.getFunction(vTable[2],
                        Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer});
            }

            public int scrollIntoView() {
                Function f = Function.getFunction(vTable[2],
                        Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer});
            }
        };
    }
}
