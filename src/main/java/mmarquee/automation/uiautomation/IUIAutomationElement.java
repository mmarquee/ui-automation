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
import com.sun.jna.platform.win32.*;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * Created by inpwt on 06/07/2016.
 */
public interface IUIAutomationElement {

    /**
     * The interface IID for QueryInterface et al
     */
    public final static Guid.IID IID = new Guid.IID(
            "{D22108AA-8AC5-49A5-837B-37BBB3D7591E}");

    /**
     *
     * Retrieves pointers to the supported interfaces on an object.
     * This method calls IUnknown::AddRef on the pointer it returns.
     *
     * @param riid
     *            The identifier of the interface being requested.
     *
     * @param ppvObject
     *            The address of a pointer variable that receives the interface pointer requested in the riid parameter. Upon successful
     *            return, *ppvObject contains the requested interface pointer to the object. If the object does not support the
     *            interface, *ppvObject is set to NULL.
     *
     * @return
     *            This method returns S_OK if the interface is supported, and E_NOINTERFACE otherwise. If ppvObject is NULL, this method returns E_POINTER.
     *            For any one object, a specific query for the IUnknown interface on any of the object's interfaces must always return the same pointer value.
     *            This enables a client to determine whether two pointers point to the same component by calling QueryInterfacewith IID_IUnknown
     *            and comparing the results. It is specifically not the case that queries for interfaces other than IUnknown (even the same interface
     *            through the same pointer) must return the same pointer value.
     *
     *            There are four requirements for implementations of QueryInterface (In these cases, "must succeed" means "must succeed barring
     *            catastrophic failure."):
     *            The set of interfaces accessible on an object through QueryInterface must be static, not dynamic. This means that if a call
     *            toQueryInterface for a pointer to a specified interface succeeds the first time, it must succeed again, and if it fails
     *            the first time, it must fail on all subsequent queries. 
     *
     *            It must be reflexive: if a client holds a pointer to an interface on an object, and queries for that interface, the call must succeed. 
     *
     *            It must be symmetric: if a client holding a pointer to one interface queries successfully for another, a query through
     *            the obtained pointer for the first interface must succeed. 
     *
     *            It must be transitive: if a client holding a pointer to one interface queries successfully for a second, and through that
     *            pointer queries successfully for a third interface, a query for the first interface through the pointer for the
     *            third interface must succeed. 
     *            Notes to Implementers
     *            Implementations of QueryInterface must never check ACLs. The main reason for this rule is that COM requires that an object supporting a
     *            particular interface always return success when queried for that interface. Another reason is that checking ACLs on QueryInterface
     *            does not provide any real security because any client who has access to a particular interface can hand it directly to another
     *            client without any calls back to the server. Also, because COM caches interface pointers, it does not callQueryInterface on
     *            the server every time a client does a query.
     */
    WinNT.HRESULT QueryInterface(
            Guid.REFIID riid,
            PointerByReference ppvObject);

    /**
     *
     * Increments the reference count for an interface on an object. This method should be called for every new copy of a pointer to an interface on an object.
     * @return
     *            The method returns the new reference count. This value is intended to be used only for test purposes.
     *
     *            Objects use a reference counting mechanism to ensure that the lifetime of the object includes the lifetime of references to it. You use AddRef
     *            to stabilize a copy of an interface pointer. It can also be called when the life of a cloned pointer must extend beyond the
     *            lifetime of the original pointer. The cloned pointer must be released by calling IUnknown::Release.
     *
     *            The internal reference counter that AddRef maintains should be a 32-bit unsigned integer.
     *            Notes to Callers
     *            Call this method for every new copy of an interface pointer that you make. For example, if you are passing a copy of a pointer
     *            back from a method, you must call AddRef on that pointer. You must also call AddRef on a pointer before passing it as an in-out
     *            parameter to a method; the method will call IUnknown::Release before copying the out-value on top of it.
     */
    int AddRef();

    /**
     * Decrements the reference count for an interface on an object.
     *
     * @return
     *            The method returns the new reference count. This value is intended to be used only for test purposes.
     *
     *            When the reference count on an object reaches zero, Release must cause the interface pointer to free itself. When the released
     *            pointer is the only existing reference to an object (whether the object supports single or multiple interfaces), the
     *            implementation must free the object.
     *
     *            Note that aggregation of objects restricts the ability to recover interface pointers.
     *            Notes to Callers
     *            Call this method when you no longer need to use an interface pointer. If you are writing a method that takes an in-out
     *            parameter, call Release on the pointer you are passing in before copying the out-value on top of it.
     */
    int Release();

    int setFocus();
    int get_CurrentName (/* [retval][out] */ PointerByReference sr);
    int get_CurrentClassName (/* [retval][out] */ PointerByReference sr);
    int findAll (TreeScope scope, Pointer condition, /* [retval][out] */ PointerByReference sr);
    int findFirst (TreeScope scope, Pointer condition, /* [retval][out] */ PointerByReference sr);
    int get_ClickablePoint(/* [out] */ WinDef.POINT.ByReference clickable, /* [retval][out] */ WinDef.BOOLByReference gotClickable);
    int get_CurrentIsPassword(IntByReference value);
    int get_CurrentAriaRole (/* [retval][out] */ PointerByReference sr);
    int get_CurrentPattern(Integer patternId, PointerByReference pbr);
    int get_CurrentPropertyValue(int propertyId, Variant.VARIANT.ByReference value);
    int get_CurrentControlType(IntByReference ipr);
    int get_CurrentProviderDescription(PointerByReference sr);
    int get_CurrentFrameworkId (/* [retval][out] */ PointerByReference retVal);
    int get_CurrentItemStatus (/* [retval][out] */ PointerByReference retVal);
    int get_CurrentOrientation (/* [retval][out] */ IntByReference retVal);
    int get_CurrentAcceleratorKey (/* [retval][out] */ PointerByReference retVal);
    int get_CurrentProcessId (/* [retval][out] */ IntByReference retVal);
    int get_CurrentBoundingRectangle (/* [retval][out] */ WinDef.RECT retVal);
    int get_CurrentLocalizedControlType (/* [retval][out] */ PointerByReference retVal);

        public static class Converter {
        private static int UIAutomationElement_Methods  = 85; // 0-2 IUnknown, 3-84 IUIAutomationElement

        public static IUIAutomationElement PointerToInterface(final PointerByReference ptr) {
            final Pointer interfacePointer = ptr.getValue();
            final Pointer vTablePointer = interfacePointer.getPointer(0);
            final Pointer[] vTable = new Pointer[UIAutomationElement_Methods];
            vTablePointer.read(0, vTable, 0, vTable.length);
            return new IUIAutomationElement() {

                // IUnknown
                public WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pointerByReference) {
                    Function f = Function.getFunction(vTable[0], Function.ALT_CONVENTION);
                    return new WinNT.HRESULT(f.invokeInt(new Object[]{interfacePointer, byValue, pointerByReference}));
                }

                public int AddRef() {
                    Function f = Function.getFunction(vTable[1], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer});
                }

                public int Release() {
                    Function f = Function.getFunction(vTable[2], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer});
                }

                public int setFocus() {
                    Function f = Function.getFunction(vTable[3], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer});
                }

                public int GetRuntimeId (/* [retval][out] */ /* SAFEARRAY */ PointerByReference runtimeId) {
                    Function f = Function.getFunction(vTable[4], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, runtimeId});
                }

                public int findFirst(TreeScope scope, Pointer condition, /* [retval][out] */ PointerByReference sr) {
                    Function f = Function.getFunction(vTable[5], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, scope.value, condition, sr});
                }

                public int findAll(TreeScope scope, Pointer condition, /* [retval][out] */ PointerByReference sr) {
                    Function f = Function.getFunction(vTable[6], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, scope.value, condition, sr});
                }

                public int FindFirstBuildCache (/* [in] */ int scope, /* [in] */ /* IUIAutomationCondition */ Pointer condition, /* [in] */ /* IUIAutomationCacheRequest */ Pointer cacheRequest, /* [retval][out] */ /* IUIAutomationElement */ PointerByReference found) {
                    Function f = Function.getFunction(vTable[7], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, scope, condition, cacheRequest, found});
                }

                public int FindAllBuildCache (/* [in] */ int scope, /* [in] */ /* IUIAutomationCondition */ Pointer condition, /* [in] */ /* IUIAutomationCacheRequest */ Pointer cacheRequest, /* [retval][out] */ /* IUIAutomationElementArray */ PointerByReference found) {
                    Function f = Function.getFunction(vTable[8], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, scope, condition, cacheRequest, found});
                }

                public int BuildUpdatedCache (/* [in] */ /* IUIAutomationCacheRequest */ Pointer cacheRequest, /* [retval][out] */ /* IUIAutomationElement */ PointerByReference updatedElement) {
                    Function f = Function.getFunction(vTable[9], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, cacheRequest, updatedElement});
                }

                public int get_CurrentPropertyValue(int propertyId, Variant.VARIANT.ByReference value) {
                    Function f = Function.getFunction(vTable[10], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, propertyId, value});
                }

                public int GetCurrentPropertyValueEx (/* [in] */ int propertyId, /* [in] */ WinDef.BOOL ignoreDefaultValue, /* [retval][out] */ Variant.VARIANT retVal) {
                    Function f = Function.getFunction(vTable[11], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, propertyId, ignoreDefaultValue, retVal});
                }

                public int GetCachedPropertyValue (/* [in] */ int propertyId, /* [retval][out] */ Variant.VARIANT retVal) {
                    Function f = Function.getFunction(vTable[12], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, propertyId, retVal});
                }

                public int GetCachedPropertyValueEx (/* [in] */ int propertyId, /* [in] */ WinDef.BOOL ignoreDefaultValue, /* [retval][out] */ Variant.VARIANT retVal) {
                    Function f = Function.getFunction(vTable[13], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, propertyId, ignoreDefaultValue, retVal});
                }

                public int GetCurrentPatternAs (/* [in] */ int patternId, /* [in] */ Guid.REFIID riid, /* [retval][iid_is][out] */ PointerByReference patternObject) {
                    Function f = Function.getFunction(vTable[14], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, patternId, riid, patternObject});
                }

                public int GetCachedPatternAs (/* [in] */ int patternId, /* [in] */ Guid.REFIID riid, /* [retval][iid_is][out] */ PointerByReference patternObject) {
                    Function f = Function.getFunction(vTable[15], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, patternId, riid, patternObject});
                }

                public int get_CurrentPattern(Integer patternId, PointerByReference pbr) {
                    Function f = Function.getFunction(vTable[16], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, patternId, pbr});
                }

                public int GetCachedPattern (/* [in] */ int patternId, /* [retval][out] */ PointerByReference patternObject) {
                    Function f = Function.getFunction(vTable[17], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, patternId, patternObject});
                }

                public int GetCachedParent (/* [retval][out] */ /* IUIAutomationElement */ PointerByReference parent) {
                    Function f = Function.getFunction(vTable[18], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, parent});
                }

                public int GetCachedChildren (/* [retval][out] */ /* IUIAutomationElementArray */ PointerByReference children) {
                    Function f = Function.getFunction(vTable[19], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, children});
                }

                public int get_CurrentProcessId (/* [retval][out] */ IntByReference retVal) {
                    Function f = Function.getFunction(vTable[20], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentControlType(IntByReference ipr) {
                    Function f = Function.getFunction(vTable[21], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, ipr});
                }

                public int get_CurrentLocalizedControlType (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[22], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentName(/* [retval][out] */ PointerByReference sr) {
                    Function f = Function.getFunction(vTable[23], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, sr});
                }

                public int get_CurrentAcceleratorKey (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[24], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentAccessKey (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[25], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentHasKeyboardFocus (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[26], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentIsKeyboardFocusable (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[27], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentIsEnabled (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[28], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentAutomationId (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[29], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentClassName(/* [retval][out] */ PointerByReference sr) {
                    Function f = Function.getFunction(vTable[30], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, sr});
                }

                public int get_CurrentHelpText (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[31], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentCulture (/* [retval][out] */ IntByReference retVal) {
                    Function f = Function.getFunction(vTable[32], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentIsControlElement (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[33], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentIsContentElement (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[34], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentIsPassword(IntByReference value) {
                    Function f = Function.getFunction(vTable[35], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, value});
                }

                public int get_CurrentNativeWindowHandle (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[36], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentItemType (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[37], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentIsOffscreen (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[38], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentOrientation (/* [retval][out] */ IntByReference retVal) {
                    Function f = Function.getFunction(vTable[39], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentFrameworkId (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[40], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentIsRequiredForForm (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[41], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentItemStatus (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[42], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentBoundingRectangle (/* [retval][out] */ WinDef.RECT retVal) {
                    Function f = Function.getFunction(vTable[43], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentLabeledBy (/* [retval][out] */ /* IUIAutomationElement */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[44], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentAriaRole(/* [retval][out] */ PointerByReference sr) {
                    Function f = Function.getFunction(vTable[45], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, sr});
                }

                public int get_CurrentAriaProperties (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[46], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentIsDataValidForForm (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[47], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentControllerFor (/* [retval][out] */ /* IUIAutomationElementArray */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[48], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentDescribedBy (/* [retval][out] */ /* IUIAutomationElementArray */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[49], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentFlowsTo (/* [retval][out] */ /* IUIAutomationElementArray */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[50], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CurrentProviderDescription(PointerByReference sr) {
                    Function f = Function.getFunction(vTable[51], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, sr});
                }

                public int get_CachedControlType (/* [retval][out] */ IntByReference retVal) {
                    Function f = Function.getFunction(vTable[53], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedLocalizedControlType (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[54], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedName (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[55], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedAcceleratorKey (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[56], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedAccessKey (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[57], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedHasKeyboardFocus (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[58], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedIsKeyboardFocusable (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[59], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedIsEnabled (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[60], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedAutomationId (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[61], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedClassName (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[62], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedHelpText (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[63], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedCulture (/* [retval][out] */ IntByReference retVal) {
                    Function f = Function.getFunction(vTable[64], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedIsControlElement (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[65], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedIsContentElement (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[66], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedIsPassword (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[67], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedNativeWindowHandle (/* [retval][out] */ WinDef.HWND retVal) {
                    Function f = Function.getFunction(vTable[68], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedItemType (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[69], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedIsOffscreen (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[70], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedOrientation (/* [retval][out] */ IntByReference retVal) {
                    Function f = Function.getFunction(vTable[71], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedFrameworkId (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[72], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedIsRequiredForForm (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[73], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedItemStatus (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[74], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedBoundingRectangle (/* [retval][out] */ WinDef.RECT retVal) {
                    Function f = Function.getFunction(vTable[75], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedLabeledBy (/* [retval][out] */ /* IUIAutomationElement */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[76], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedAriaRole (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[77], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedAriaProperties (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[78], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedIsDataValidForForm (/* [retval][out] */ WinDef.BOOLByReference retVal) {
                    Function f = Function.getFunction(vTable[79], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedControllerFor (/* [retval][out] */ /* IUIAutomationElementArray */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[80], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedDescribedBy (/* [retval][out] */ /* IUIAutomationElementArray */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[81], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedFlowsTo (/* [retval][out] */ /* IUIAutomationElementArray */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[82], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_CachedProviderDescription (/* [retval][out] */ PointerByReference retVal) {
                    Function f = Function.getFunction(vTable[83], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, retVal});
                }

                public int get_ClickablePoint (/* [out] */ WinDef.POINT.ByReference clickable, /* [retval][out] */ WinDef.BOOLByReference gotClickable) {
                    Function f = Function.getFunction(vTable[84], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{interfacePointer, clickable, gotClickable});
                }

            };
        }
    }
}
