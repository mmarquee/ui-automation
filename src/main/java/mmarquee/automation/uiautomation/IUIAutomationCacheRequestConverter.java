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
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;

/**
 * @author Mark Humphreys
 * Date 16/12/2017
 */
public class IUIAutomationCacheRequestConverter {
    public static IUIAutomationCacheRequest pointerToInterface(final PointerByReference ptr) {
        final Pointer interfacePointer = ptr.getValue();
        final Pointer vTablePointer = interfacePointer.getPointer(0);
        final Pointer[] vTable = new Pointer[10];
        vTablePointer.read(0, vTable, 0, vTable.length);
        return new IUIAutomationCacheRequest() {

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

            // IUIAutomationCacheRequest

            public int addPattern(int inVal) {
                Function f = Function.getFunction(vTable[3], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, inVal});
            }

            public int addProperty(int inVal) {
                Function f = Function.getFunction(vTable[4], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, inVal});
            }

            public int clone(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[5], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getTreeScope(PointerByReference inVal) {
                Function f = Function.getFunction(vTable[6], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, inVal});
            }

            public int setTreeScope(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[7], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getTreeFilter(PointerByReference inVal) {
                Function f = Function.getFunction(vTable[8], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, inVal});
            }

            public int setTreeFilter(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[9], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getAutomationElementMode(PointerByReference inVal) {
                Function f = Function.getFunction(vTable[8], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, inVal});
            }

            public int setAutomationElementMode(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[9], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }
        };
    }
}
