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
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * @author Mark Humphreys
 * Date 05/06/2017.
 */
public class IUIAutomationConverter {
    private static int UIA_COMPARE_ELEMENTS = 3;
    private static int UIA_COMPARE_RUNTIME_IDS = 4;
    private static int UIA_GET_ROOT_ELEMENT = 5;
    private static int UIA_GET_ELEMENT_FROM_HANDLE = 6;
    private static int UIA_GET_ELEMENT_FROM_POINT = 7;
    private static int UIA_GET_FOCUSED_ELEMENT = 8;
    private static int UIA_CREATE_TREE_WALKER = 13;
    private static int UIA_GET_CONTROL_VIEW_WALKER = 14;
    private static int UIA_CREATE_CACHE_REQUEST = 20;
    private static int UIA_CREATE_TRUE_CONDITION = 21;
    private static int UIA_CREATE_FALSE_CONDITION = 22;
    private static int UIA_CREATE_PROPERTY_CONDITION = 23;
    private static int UIA_CREATE_AND_CONDITION = 25;
    private static int UIA_CREATE_OR_CONDITION = 28;
    private static int UIA_CREATE_NOT_CONDITION = 31;
    private static int UIA_ADD_AUTOMATION_EVENT_HANDLER = 32;
    private static int UIA_REMOVE_AUTOMATION_EVENT_HANDLER = 33;
    private static int UIA_GET_PATTERN_PROGRAMMATIC_NAME = 50;
    private static int UIA_ELEMENT_FROM_IACCESSIBLE = 56;
    private static int UIA_POLL_FOR_POTENTIAL_SUPPORTED_PATTERNS = 51;
    private static int UIA_POLL_FOR_POTENTIAL_SUPPORTED_PROPERTIES = 52;

    private static int UIAutomation_Methods  = 58; // 0-2 IUnknown, 3-57 IUIAutomation

    private static Pointer myInterfacePointer;

    public static IUIAutomation pointerToInterface(final PointerByReference ptr) {
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

            public int compareElements(Pointer element1, Pointer element2, IntByReference same) {
                Function f = Function.getFunction(vTable[UIA_COMPARE_ELEMENTS], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, element1, element2, same});
            }

            public int getRootElement(PointerByReference root) {
                Function f = Function.getFunction(vTable[UIA_GET_ROOT_ELEMENT], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, root});
            }

            public int getFocusedElement(PointerByReference element) {
                Function f = Function.getFunction(vTable[UIA_GET_FOCUSED_ELEMENT], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, element});
            }

            public int getElementFromHandle(WinDef.HWND hwnd, PointerByReference element) {
                Function f = Function.getFunction(vTable[UIA_GET_ELEMENT_FROM_HANDLE], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, hwnd, element});
            }

            public int elementFromPoint(WinDef.POINT pt, PointerByReference element) {
                Function f = Function.getFunction(vTable[UIA_GET_ELEMENT_FROM_POINT], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, pt, element});
            }

            public int createCacheRequest(PointerByReference request) {
                Function f = Function.getFunction(vTable[UIA_CREATE_CACHE_REQUEST], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, request});
            }

            public int createPropertyCondition(int propertyId, Variant.VARIANT.ByValue value, PointerByReference condition) {
                Function f = Function.getFunction(vTable[UIA_CREATE_PROPERTY_CONDITION], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, propertyId, value, condition});
            }

            public int createAndCondition(Pointer condition1, Pointer condition2, PointerByReference condition) {
                Function f = Function.getFunction(vTable[UIA_CREATE_AND_CONDITION], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, condition1, condition2, condition});
            }

            public int createOrCondition(Pointer condition1, Pointer condition2, PointerByReference condition) {
                Function f = Function.getFunction(vTable[UIA_CREATE_OR_CONDITION], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, condition1, condition2, condition});
            }

            public int createTrueCondition(PointerByReference condition) {
                Function f = Function.getFunction(vTable[UIA_CREATE_TRUE_CONDITION], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, condition});
            }

            public int createFalseCondition(PointerByReference condition) {
                Function f = Function.getFunction(vTable[UIA_CREATE_FALSE_CONDITION], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, condition});
            }

            public int createNotCondition(Pointer condition, PointerByReference retval) {
                Function f = Function.getFunction(vTable[UIA_CREATE_NOT_CONDITION], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, condition, retval});
            }

            public int getPatternProgrammaticName(int patternId, PointerByReference retval) {
                Function f = Function.getFunction(vTable[UIA_GET_PATTERN_PROGRAMMATIC_NAME], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, patternId, retval});
            }

            public int createTreeWalker(PointerByReference condition, PointerByReference walker) {
                Function f = Function.getFunction(vTable[UIA_CREATE_TREE_WALKER], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, condition, walker});
            }

            public int getControlViewWalker(PointerByReference walker) {
                Function f = Function.getFunction(vTable[UIA_GET_CONTROL_VIEW_WALKER], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, walker});
            }

            public int addAutomationEventHandler(IntByReference eventId, TreeScope scope, Pointer element, PointerByReference cacheRequest, PointerByReference handler) {
                Function f = Function.getFunction(vTable[UIA_ADD_AUTOMATION_EVENT_HANDLER], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, eventId, element, scope, cacheRequest, handler});
            }

            public int removeAutomationEventHandler(IntByReference eventId, PointerByReference element, PointerByReference handler) {
                Function f = Function.getFunction(vTable[UIA_REMOVE_AUTOMATION_EVENT_HANDLER], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, eventId, element, handler});
            }

            public int pollForPotentialSupportedProperties(Pointer element, /* SAFEARRAY */ PointerByReference ids, /* SAFEARRAY */ PointerByReference names) {
                Function f = Function.getFunction(vTable[UIA_POLL_FOR_POTENTIAL_SUPPORTED_PROPERTIES], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, element, ids, names});
            }

            public int pollForPotentialSupportedPatterns(Pointer element, /* SAFEARRAY */ PointerByReference ids, /* SAFEARRAY */ PointerByReference names) {
                Function f = Function.getFunction(vTable[UIA_POLL_FOR_POTENTIAL_SUPPORTED_PATTERNS], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{myInterfacePointer, element, ids, names});
            }
        };
    }
}
