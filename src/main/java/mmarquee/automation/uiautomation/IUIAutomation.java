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
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * Created by Mark Humphreys on 06/07/2016.
 *
 * Use this like:
 * PointerByReference pbr=new PointerByReference();
 * HRESULT result=SomeCOMObject.QueryInterface(IID_IUIAUTOMATION, pbr);
 * if(COMUtils.SUCCEEDED(result)) IUIAutomation iua=IUIAutomation.Converter.PointerToInterface(pbr);
 *
 */
public interface IUIAutomation extends IUnknown {
    /**
     * The interface IID for QueryInterface et al
     */
    Guid.IID IID = new Guid.IID("{30CBE57D-D9D0-452A-AB13-7AC5AC4825EE}");

    /**
     * The IID for the library itself
     */
    Guid.GUID CLSID = new Guid.GUID("{FF48DBA4-60EF-4201-AA87-54103EEF594E}");

    int GetRootElement(PointerByReference root);
    int ElementFromHandle(WinDef.HWND hwnd, PointerByReference element);
    int CreateAndCondition(Pointer condition1, Pointer condition2, PointerByReference condition);
    int CreatePropertyCondition(int propertyId, Variant.VARIANT.ByValue value, PointerByReference condition);
    int CreateOrCondition(Pointer condition1, Pointer condition2, PointerByReference condition);
    int CreateTrueCondition(PointerByReference condition);
    int CreateFalseCondition(PointerByReference condition);
    int CompareElements(Pointer element1, Pointer element2, IntByReference same);
    int CreateNotCondition(Pointer condition, PointerByReference retval);
    int GetPatternProgrammaticName(int patternId, PointerByReference retval);
    int GetFocusedElement(PointerByReference element);

    class Converter {
        private static int UIA_COMPARE_ELEMENTS = 3;
        private static int UIA_COMPARE_RUNTIME_IDS = 4;
        private static int UIA_GET_ROOT_ELEMENT = 5;
        private static int UIA_GET_ELEMENT_FROM_HANDLE = 6;
        private static int UIA_GET_FOCUSED_ELEMENT = 8;
        private static int UIA_CREATE_TRUE_CONDITION = 21;
        private static int UIA_CREATE_FALSE_CONDITION = 22;
        private static int UIA_CREATE_PROPERTY_CONDITION = 23;
        private static int UIA_CREATE_AND_CONDITION = 25;
        private static int UIA_CREATE_OR_CONDITION = 28;
        private static int UIA_CREATE_NOT_CONDITION = 31;
        private static int UIA_GET_PATTERN_PROGRAMMATIC_NAME = 50;
        private static int UIA_ELEMENT_FROM_IACCESSIBLE = 56;

        private static int UIAutomation_Methods  = 58; // 0-2 IUnknown, 3-57 IUIAutomation

        private static Pointer myInterfacePointer;

        public static IUIAutomation PointerToInterface(final PointerByReference ptr) {
            myInterfacePointer = ptr.getValue();
            Pointer vTablePointer = myInterfacePointer.getPointer(0);

            final Pointer[] vTable = new Pointer[UIAutomation_Methods];
            vTablePointer.read(0, vTable, 0, vTable.length);
            return new IUIAutomation() {

                @Override
                public WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pointerByReference) {
                    Function f = Function.getFunction(vTable[0], Function.ALT_CONVENTION);
                    return new WinNT.HRESULT(f.invokeInt(new Object[]{myInterfacePointer, byValue, pointerByReference}));
                }

                @Override
                public int AddRef() {
                    Function f = Function.getFunction(vTable[1], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer});
                }

                public int Release() {
                    Function f = Function.getFunction(vTable[2], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer});
                }

                public int CompareElements(Pointer element1, Pointer element2, IntByReference same) {
                    Function f = Function.getFunction(vTable[UIA_COMPARE_ELEMENTS], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer, element1, element2, same});
                }

                public int GetRootElement(PointerByReference root) {
                    Function f = Function.getFunction(vTable[UIA_GET_ROOT_ELEMENT], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer, root});
                }

                public int GetFocusedElement(PointerByReference element) {
                    Function f = Function.getFunction(vTable[UIA_GET_FOCUSED_ELEMENT], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer, element});
                }

                public int ElementFromHandle(WinDef.HWND hwnd, PointerByReference element) {
                    Function f = Function.getFunction(vTable[UIA_GET_ELEMENT_FROM_HANDLE], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer, hwnd, element});
                }

                public int CreatePropertyCondition(int propertyId, Variant.VARIANT.ByValue value, PointerByReference condition) {
                    Function f = Function.getFunction(vTable[UIA_CREATE_PROPERTY_CONDITION], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer, propertyId, value, condition});
                }

                public int CreateAndCondition(Pointer condition1, Pointer condition2, PointerByReference condition) {
                    Function f = Function.getFunction(vTable[UIA_CREATE_AND_CONDITION], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer, condition1, condition2, condition});
                }

                public int CreateOrCondition(Pointer condition1, Pointer condition2, PointerByReference condition) {
                    Function f = Function.getFunction(vTable[UIA_CREATE_OR_CONDITION], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer, condition1, condition2, condition});
                }

                public int CreateTrueCondition(PointerByReference condition) {
                    Function f = Function.getFunction(vTable[UIA_CREATE_TRUE_CONDITION], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer, condition});
                }

                public int CreateFalseCondition(PointerByReference condition) {
                    Function f = Function.getFunction(vTable[UIA_CREATE_FALSE_CONDITION], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer, condition});
                }

                public int CreateNotCondition(Pointer condition, PointerByReference retval) {
                    Function f = Function.getFunction(vTable[UIA_CREATE_NOT_CONDITION], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer, condition, retval});
                }

                public int GetPatternProgrammaticName(int patternId, PointerByReference retval) {
                    Function f = Function.getFunction(vTable[UIA_GET_PATTERN_PROGRAMMATIC_NAME], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer, patternId, retval});
                }
            };
        }
    }
}
