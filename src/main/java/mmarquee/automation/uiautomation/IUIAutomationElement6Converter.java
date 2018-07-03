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

package mmarquee.automation.uiautomation;

import com.sun.jna.Function;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

public class IUIAutomationElement6Converter {

    private static final int IUI3_SHOW_CONTEXT_MENU = 91;
    private static final int IUI3_CURRENT_IS_PERIPHERAL = 92;
    private static final int IUI3_CACHED_IS_PERIPHERAL = 93;
    private static final int IUI6_CURRENT_FULL_DESCRIPTION = 104;
    private static final int IUI6_CACHED_FULL_DESCRIPTION = 105;

    public static IUIAutomationElement6 pointerToInterface(final PointerByReference ptr) {
        //  0-2  IUnknown,
        //  3-84 IUIAutomationElement,
        // 85-90 IUIAutomationElement2,
        // 91-93 IUIAutomationElement3
        // ????? IUIAutomationElement4          (6) 94,95,96,97,98,99
        // ????? IUIAutomationElement5          (4) 100,101,102,103
        // ????? IUIAutomationElement6          (2) 104,105
        final int IUIAutomationElement6_Methods = 118;

        final Pointer interfacePointer = ptr.getValue();
        final Pointer vTablePointer = interfacePointer.getPointer(0);
        final Pointer[] vTable = new Pointer[IUIAutomationElement6_Methods];
        vTablePointer.read(0, vTable, 0, vTable.length);
        return new IUIAutomationElement6() {

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

            // IUIAutomationElement
            public int setFocus() {
                Function f = Function.getFunction(vTable[3], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer});
            }

            public int getRuntimeId (/* SAFEARRAY */ PointerByReference runtimeId) {
                Function f = Function.getFunction(vTable[4], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, runtimeId});
            }

            public int findFirst(TreeScope scope, Pointer condition, PointerByReference sr) {
                Function f = Function.getFunction(vTable[5], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, scope.value, condition, sr});
            }

            public int findAll(TreeScope scope, Pointer condition, PointerByReference sr) {
                Function f = Function.getFunction(vTable[6], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, scope.value, condition, sr});
            }

            public int findFirstBuildCache (int scope, Pointer condition,
                                             Pointer cacheRequest,
                                            PointerByReference found) {
                Function f = Function.getFunction(vTable[7], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, scope, condition, cacheRequest, found});
            }

            public int findAllBuildCache (int scope, Pointer condition,
                                          Pointer cacheRequest,
                                          PointerByReference found) {
                Function f = Function.getFunction(vTable[8], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, scope, condition, cacheRequest, found});
            }

            public int buildUpdatedCache (Pointer cacheRequest,
                                          PointerByReference updatedElement) {
                Function f = Function.getFunction(vTable[9], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, cacheRequest, updatedElement});
            }

            public int getCurrentPropertyValue(int propertyId, Variant.VARIANT.ByReference value) {
                Function f = Function.getFunction(vTable[10], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, propertyId, value});
            }

            /*
            public int getCurrentPropertyValueEx ( int propertyId,  WinDef.BOOL ignoreDefaultValue, Variant.VARIANT retVal) {
                Function f = Function.getFunction(vTable[11], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, propertyId, ignoreDefaultValue, retVal});
            }

            public int GetCachedPropertyValue ( int propertyId, Variant.VARIANT retVal) {
                Function f = Function.getFunction(vTable[12], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, propertyId, retVal});
            }

            public int GetCachedPropertyValueEx ( int propertyId,  WinDef.BOOL ignoreDefaultValue, Variant.VARIANT retVal) {
                Function f = Function.getFunction(vTable[13], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, propertyId, ignoreDefaultValue, retVal});
            }

            public int getCurrentPatternAs ( int patternId,  Guid.REFIID riid, PointerByReference patternObject) {
                Function f = Function.getFunction(vTable[14], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, patternId, riid, patternObject});
            }

            public int GetCachedPatternAs ( int patternId,  Guid.REFIID riid, PointerByReference patternObject) {
                Function f = Function.getFunction(vTable[15], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, patternId, riid, patternObject});
            }
            */

            public int getCurrentPattern(Integer patternId, PointerByReference pbr) {
                Function f = Function.getFunction(vTable[16], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, patternId, pbr});
            }

            /*
            public int GetCachedPattern ( int patternId, PointerByReference patternObject) {
                Function f = Function.getFunction(vTable[17], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, patternId, patternObject});
            }

            public int GetCachedParent (PointerByReference parent) {
                Function f = Function.getFunction(vTable[18], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, parent});
            }

            public int GetCachedChildren (PointerByReference children) {
                Function f = Function.getFunction(vTable[19], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, children});
            }
            */

            public int getCurrentProcessId (IntByReference retVal) {
                Function f = Function.getFunction(vTable[20], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentControlType(IntByReference ipr) {
                Function f = Function.getFunction(vTable[21], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, ipr});
            }

            public int getCurrentLocalizedControlType(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[22], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentName(PointerByReference sr) {
                Function f = Function.getFunction(vTable[23], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, sr});
            }

            public int getCurrentAcceleratorKey(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[24], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            /*
            public int getCurrentAccessKey(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[25], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentHasKeyboardFocus(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[26], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentIsKeyboardFocusable(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[27], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }
            */

            public int getCurrentIsEnabled(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[28], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentAutomationId(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[29], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentClassName(PointerByReference sr) {
                Function f = Function.getFunction(vTable[30], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, sr});
            }

            /*
            public int getCurrentHelpText(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[31], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }
            */

            public int getCurrentCulture(IntByReference retVal) {
                Function f = Function.getFunction(vTable[32], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentIsControlElement(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[33], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentIsContentElement(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[34], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentIsPassword(IntByReference value) {
                Function f = Function.getFunction(vTable[35], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, value});
            }

            /*
            public int getCurrentNativeWindowHandle(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[36], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentItemType(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[37], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }
            */

            public int getCurrentIsOffscreen(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[38], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentOrientation(IntByReference retVal) {
                Function f = Function.getFunction(vTable[39], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentFrameworkId(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[40], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            /*
            public int getCurrentIsRequiredForForm(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[41], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }
            */

            public int getCurrentItemStatus(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[42], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentBoundingRectangle(WinDef.RECT retVal) {
                Function f = Function.getFunction(vTable[43], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            /*
            public int getCurrentLabeledBy(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[44], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }
            */

            public int getCurrentAriaRole(PointerByReference sr) {
                Function f = Function.getFunction(vTable[45], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, sr});
            }

            /*
            public int getCurrentAriaProperties(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[46], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentIsDataValidForForm(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[47], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentControllerFor(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[48], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentDescribedBy (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[49], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentFlowsTo(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[50], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }
            */

            public int getCurrentProviderDescription(PointerByReference sr) {
                Function f = Function.getFunction(vTable[51], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, sr});
            }

            /*
            public int get_CachedControlType (IntByReference retVal) {
                Function f = Function.getFunction(vTable[53], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedLocalizedControlType (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[54], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }
            */

            public int getCachedName(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[55], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            /*
            public int get_CachedAcceleratorKey (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[56], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedAccessKey (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[57], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedHasKeyboardFocus (WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[58], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedIsKeyboardFocusable (WinDef.BOOLByReference retVal) {
               Function f = Function.getFunction(vTable[59], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedIsEnabled (WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[60], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedAutomationId (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[61], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedClassName (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[62], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedHelpText (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[63], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedCulture (IntByReference retVal) {
                Function f = Function.getFunction(vTable[64], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedIsControlElement (WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[65], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedIsContentElement (WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[66], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedIsPassword (WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[67], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedNativeWindowHandle (WinDef.HWND retVal) {
                Function f = Function.getFunction(vTable[68], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedItemType (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[69], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedIsOffscreen (WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[70], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedOrientation (IntByReference retVal) {
                Function f = Function.getFunction(vTable[71], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedFrameworkId (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[72], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedIsRequiredForForm (WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[73], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedItemStatus (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[74], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedBoundingRectangle (WinDef.RECT retVal) {
                Function f = Function.getFunction(vTable[75], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedLabeledBy (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[76], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedAriaRole (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[77], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedAriaProperties (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[78], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedIsDataValidForForm (WinDef.BOOLByReference retVal) {
               Function f = Function.getFunction(vTable[79], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedControllerFor (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[80], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedDescribedBy (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[81], Function
                        .ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedFlowsTo (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[82], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int get_CachedProviderDescription (PointerByReference retVal) {
                Function f = Function.getFunction(vTable[83], Function.ALT_CONVENTION);
               return f.invokeInt(new Object[]{interfacePointer, retVal});
            }
            */

            public int getClickablePoint(WinDef.POINT.ByReference clickable, WinDef.BOOLByReference gotClickable) {
                Function f = Function.getFunction(vTable[84], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, clickable, gotClickable});
            }

            // IUIAutomationElement2

            // IUIAutomationElement3
            public int showContextMenu() {
                Function f = Function.getFunction(vTable[IUI3_SHOW_CONTEXT_MENU], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer});
            }

            public int getCurrentIsPeripheral(Integer retVal){
                Function f = Function.getFunction(vTable[IUI3_CURRENT_IS_PERIPHERAL], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedIsPeripheral(Integer retVal){
                Function f = Function.getFunction(vTable[IUI3_CACHED_IS_PERIPHERAL], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            // IUIAutomationElement4
            // IUIAutomationElement5
            // IUIAutomationElement6

            public int getCurrentFullDescription(PointerByReference sr) {
                Function f = Function.getFunction(vTable[IUI6_CURRENT_FULL_DESCRIPTION], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, sr});
            }

            public int getCachedFullDescription(PointerByReference sr) {
                Function f = Function.getFunction(vTable[IUI6_CACHED_FULL_DESCRIPTION], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, sr});
            }
        };
    }
}
