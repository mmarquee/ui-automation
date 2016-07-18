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
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

/**
 * Created by HumphreysM on 06/07/2016.
 */
public interface IUIAutomation {
    /**
     * The interface IID for QueryInterface et al
     */
    public final static Guid.IID IID = new Guid.IID(
            "{30CBE57D-D9D0-452A-AB13-7AC5AC4825EE}");


    /**
     * The IID for the library itself
     */
    public static final Guid.GUID CLSID = new Guid.GUID(
            "{FF48DBA4-60EF-4201-AA87-54103EEF594E}");


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

    int GetRootElement(PointerByReference root);
    int ElementFromHandle(WinDef.HWND hwnd, PointerByReference element);
    int CreateAndCondition(Pointer condition1, Pointer condition2, PointerByReference condition);
    int CreatePropertyCondition(int propertyId, Variant.VARIANT.ByValue value, PointerByReference condition);
    int CreateOrCondition(Pointer condition1, Pointer condition2, PointerByReference condition);
    int CreateTrueCondition(PointerByReference condition);
    int CreateFalseCondition(PointerByReference condition);
    int CompareElements(Pointer element1, Pointer element2, IntByReference same);

     /*
    Use this like:
    PointerByReference pbr=new PointerByReference();
    HRESULT result=SomeCOMObject.QueryInterface(IID_IUIAUTOMATION, pbr);
    if(COMUtils.SUCCEEDED(result)) IUIAutomation iua=IUIAutomation.Converter.PointerToInterface(pbr);
     */

    public static class Converter {

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

                // IUnknown

          //      @Override
                public WinNT.HRESULT QueryInterface(Guid.REFIID byValue, PointerByReference pointerByReference) {
                    Function f = Function.getFunction(vTable[0], Function.ALT_CONVENTION);
                    return new WinNT.HRESULT(f.invokeInt(new Object[]{myInterfacePointer, byValue, pointerByReference}));
                }

           //     @Override
                public int AddRef() {
                    Function f = Function.getFunction(vTable[1], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer});
                }

                public int Release() {
                    Function f = Function.getFunction(vTable[2], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer});
                }

                // IUIAutomation actual (there are more obviously, not yet implemented(

                public int CompareElements(Pointer element1, Pointer element2, IntByReference same) {
                    Function f = Function.getFunction(vTable[UIA_COMPARE_ELEMENTS], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer, element1, element2, same});
                }

                public int GetRootElement(PointerByReference root) {
                    Function f = Function.getFunction(vTable[UIA_GET_ROOT_ELEMENT], Function.ALT_CONVENTION);
                    return f.invokeInt(new Object[]{myInterfacePointer, root});
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

            };
        }
    }

}
