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
 * Converter implementation of IUIAutomationElement.
 *
 * @author Mark Humphreys
 * Date 05/06/2017.
 */
public class IUIAutomationElementConverter {
    public static final int IUI_ELEMENT_GET_CLICKABLE_POINT = 84;
    //   0-2   IUnknown,
    //   3-84  IUIAutomationElement
    private static int UIAutomationElement_Methods = 85;

    public static IUIAutomationElement pointerToInterface(final PointerByReference ptr) {
        final Pointer interfacePointer = ptr.getValue();
        final Pointer vTablePointer = interfacePointer.getPointer(0);
        final Pointer[] vTable = new Pointer[UIAutomationElement_Methods];
        vTablePointer.read(0, vTable, 0, vTable.length);
        return new IUIAutomationElement() {

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

            public int getRuntimeId(PointerByReference runtimeId) {
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

            public int findFirstBuildCache(int scope,
                                           Pointer condition,
                                           Pointer cacheRequest,
                                           PointerByReference found) {
                Function f = Function.getFunction(vTable[7], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, scope, condition, cacheRequest, found});
            }

            public int findAllBuildCache(int scope,
                                         Pointer condition,
                                         Pointer cacheRequest,
                                         PointerByReference found) {
                Function f = Function.getFunction(vTable[8], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, scope, condition, cacheRequest, found});
            }

            public int buildUpdatedCache(/* [in] */ /* IUIAutomationCacheRequest */ Pointer cacheRequest, /* IUIAutomationElement */ PointerByReference updatedElement) {
                Function f = Function.getFunction(vTable[9], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, cacheRequest, updatedElement});
            }

            public int getCurrentPropertyValue(int propertyId, Variant.VARIANT.ByReference value) {
                Function f = Function.getFunction(vTable[10], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, propertyId, value});
            }

            public int getCurrentPropertyValueEx(int propertyId,
                                                 WinDef.BOOL ignoreDefaultValue,
                                                 Variant.VARIANT retVal) {
                Function f = Function.getFunction(vTable[11], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, propertyId, ignoreDefaultValue, retVal});
            }

            public int getCachedPropertyValue(/* [in] */ int propertyId, Variant.VARIANT retVal) {
                Function f = Function.getFunction(vTable[12], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, propertyId, retVal});
            }

            public int getCachedPropertyValueEx(/* [in] */ int propertyId, /* [in] */ WinDef.BOOL ignoreDefaultValue, Variant.VARIANT retVal) {
                Function f = Function.getFunction(vTable[13], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, propertyId, ignoreDefaultValue, retVal});
            }

            public int getCurrentPatternAs(
                    int patternId,
                    Guid.REFIID riid,
                    PointerByReference patternObject) {
                Function f = Function.getFunction(vTable[14], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, patternId, riid, patternObject});
            }

            public int getCachedPatternAs(/* [in] */ int patternId, /* [in] */ Guid.REFIID riid, /* [retval][iid_is][out] */ PointerByReference patternObject) {
                Function f = Function.getFunction(vTable[15], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, patternId, riid, patternObject});
            }

            public int getCurrentPattern(Integer patternId, PointerByReference pbr) {
                Function f = Function.getFunction(vTable[16], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, patternId, pbr});
            }

            public int getCachedPattern(/* [in] */ int patternId, PointerByReference patternObject) {
                Function f = Function.getFunction(vTable[17], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, patternId, patternObject});
            }

            public int getCachedParent(/* IUIAutomationElement */ PointerByReference parent) {
                Function f = Function.getFunction(vTable[18], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, parent});
            }

            public int getCachedChildren(/* IUIAutomationElementArray */ PointerByReference children) {
                Function f = Function.getFunction(vTable[19], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, children});
            }

            public int getCurrentProcessId(IntByReference retVal) {
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

            public int getCurrentHelpText(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[31], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

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

            public int getCurrentNativeWindowHandle(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[36], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentItemType(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[37], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

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

            public int getCurrentIsRequiredForForm(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[41], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentItemStatus(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[42], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentBoundingRectangle(WinDef.RECT retVal) {
                Function f = Function.getFunction(vTable[43], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentLabeledBy(/* IUIAutomationElement */ PointerByReference retVal) {
                Function f = Function.getFunction(vTable[44], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentAriaRole(PointerByReference sr) {
                Function f = Function.getFunction(vTable[45], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, sr});
            }

            public int getCurrentAriaProperties(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[46], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentIsDataValidForForm(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[47], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentControllerFor(/* IUIAutomationElementArray */ PointerByReference retVal) {
                Function f = Function.getFunction(vTable[48], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentDescribedBy(/* IUIAutomationElementArray */ PointerByReference retVal) {
                Function f = Function.getFunction(vTable[49], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentFlowsTo(/* IUIAutomationElementArray */ PointerByReference retVal) {
                Function f = Function.getFunction(vTable[50], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCurrentProviderDescription(PointerByReference sr) {
                Function f = Function.getFunction(vTable[51], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, sr});
            }

            public int getCachedControlType(IntByReference retVal) {
                Function f = Function.getFunction(vTable[53], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedLocalizedControlType(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[54], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedName(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[55], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedAcceleratorKey(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[56], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedAccessKey(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[57], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedHasKeyboardFocus(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[58], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedIsKeyboardFocusable(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[59], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedIsEnabled(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[60], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedAutomationId(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[61], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedClassName(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[62], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedHelpText(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[63], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedCulture(IntByReference retVal) {
                Function f = Function.getFunction(vTable[64], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedIsControlElement(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[65], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedIsContentElement(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[66], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedIsPassword(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[67], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedNativeWindowHandle(WinDef.HWND retVal) {
                Function f = Function.getFunction(vTable[68], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedItemType(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[69], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedIsOffscreen(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[70], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedOrientation(IntByReference retVal) {
                Function f = Function.getFunction(vTable[71], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedFrameworkId(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[72], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedIsRequiredForForm(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[73], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedItemStatus(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[74], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedBoundingRectangle(WinDef.RECT retVal) {
                Function f = Function.getFunction(vTable[75], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedLabeledBy(/* IUIAutomationElement */ PointerByReference retVal) {
                Function f = Function.getFunction(vTable[76], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedAriaRole(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[77], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedAriaProperties(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[78], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedIsDataValidForForm(WinDef.BOOLByReference retVal) {
                Function f = Function.getFunction(vTable[79], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedControllerFor(/* IUIAutomationElementArray */ PointerByReference retVal) {
                Function f = Function.getFunction(vTable[80], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedDescribedBy(/* IUIAutomationElementArray */ PointerByReference retVal) {
                Function f = Function.getFunction(vTable[81], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedFlowsTo(/* IUIAutomationElementArray */ PointerByReference retVal) {

                Function f = Function.getFunction(vTable[82], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getCachedProviderDescription(PointerByReference retVal) {
                Function f = Function.getFunction(vTable[83], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, retVal});
            }

            public int getClickablePoint(WinDef.POINT.ByReference clickable,
                                         WinDef.BOOLByReference gotClickable) {
                Function f = Function.getFunction(vTable[IUI_ELEMENT_GET_CLICKABLE_POINT], Function.ALT_CONVENTION);
                return f.invokeInt(new Object[]{interfacePointer, clickable, gotClickable});
            }
        };
    }
}
