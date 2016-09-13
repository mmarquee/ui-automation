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
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * Created by Mark Humphreys on 13/07/2016.
 */
public interface IUIAutomationWindowPattern extends IUnknown {
    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID("{0FAEF453-9208-43EF-BBB2-3B485177864F}");

    int Close();
    int WaitForInputIdle(Integer milliseconds, IntByReference success);
    int SetWindowVisualState(Integer state);
    int Get_CurrentCanMaximize(IntByReference retVal);
    int Get_CurrentCanMinimize(IntByReference retVal);
    int Get_CurrentIsModal(IntByReference retVal);
    int Get_CurrentIsTopmost(IntByReference retVal);

    class Converter {
        private static int METHODS = 18; // 0-2 IUnknown, 3-17 IUIAutomationInvokePattern

        public static IUIAutomationWindowPattern PointerToInterface(final PointerByReference ptr) {
            final Pointer interfacePointer = ptr.getValue();
            final Pointer vTablePointer = interfacePointer.getPointer(0);
            final Pointer[] vTable = new Pointer[METHODS];
            vTablePointer.read(0, vTable, 0, vTable.length);
            return new IUIAutomationWindowPattern() {
                // IUnknown

                //     @Override
                public WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pointerByReference) {
                    Function f = Function.getFunction(vTable[0], Function.ALT_CONVENTION);
                    return new WinNT.HRESULT(f.invokeInt(new Object[]{interfacePointer, byValue, pointerByReference}));
                }

                //   @Override
                public int AddRef() {
                    Function f = Function.getFunction(vTable[1], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer});
                }

                public int Release() {
                    Function f = Function.getFunction(vTable[2], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer});
                }

                public int Close() {
                    Function f = Function.getFunction(vTable[3], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer});
                }

                public int WaitForInputIdle(Integer milliseconds, IntByReference success) {
                    Function f = Function.getFunction(vTable[4], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, milliseconds, success});
                }

                public int SetWindowVisualState(Integer state){
                    Function f = Function.getFunction(vTable[5], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, state});
                }

                public int Get_CurrentCanMaximize(IntByReference retVal){
                    Function f = Function.getFunction(vTable[6], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int Get_CurrentCanMinimize(IntByReference retVal){
                    Function f = Function.getFunction(vTable[7], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int Get_CurrentIsModal(IntByReference retVal) {
                    Function f = Function.getFunction(vTable[8], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int Get_CurrentIsTopmost(IntByReference retVal) {
                    Function f = Function.getFunction(vTable[9], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }
            };
        }
    }

}
