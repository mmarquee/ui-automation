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

package mmarquee.automation;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.OaIdl.VARIANT_BOOL;
import com.sun.jna.platform.win32.WinDef.BOOL;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.IUIAutomationElementArrayConverter;
import mmarquee.automation.uiautomation.IUIAutomationElementConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Base class to have underlying behaviour.
 *
 * @author Mark Humphreys
 * Date 08/02/2017.
 */
public abstract class BaseAutomation {
    /**
     * Creates an Unknown object from the pointer.
     *
     * Allows Mockito to be used to create Unknown objects.
     *
     * @param pvInstance The pointer to use
     * @return An Unknown object
     */
    public Unknown makeUnknown(final Pointer pvInstance) {
        return new Unknown(pvInstance);
    }

    /**
     * Convert a raw PointerByReference to a IUIAutomationElement.
     *
     * @param pbr The raw pointer.
     * @return The IUIAutomationElement.
     * @throws AutomationException Automation library has thrown an error.
     */
    public IUIAutomationElement getAutomationElementFromReference(
            final PointerByReference pbr)
            throws AutomationException {
        Unknown unknown = makeUnknown(pbr.getValue());

        WinNT.HRESULT result0 =
                unknown.QueryInterface(
                        new Guid.REFIID(IUIAutomationElement.IID), pbr);

        if (COMUtils.FAILED(result0)) {
            throw new AutomationException(result0.intValue());
        }

        return IUIAutomationElementConverter.pointerToInterface(pbr);
    }

    /**
     * Convert a raw PointerByReference to a IUIAutomationElementArray.
     * @param pbr The raw pointer.
     * @return The IUIAutomationElementArray.
     * @throws AutomationException Automation library has thrown an error.
     */
    public IUIAutomationElementArray getAutomationElementArrayFromReference(
            final PointerByReference pbr)
            throws AutomationException {
        Unknown unknown = this.makeUnknown(pbr.getValue());
        PointerByReference pUnknown = new PointerByReference();

        WinNT.HRESULT result0 =
                unknown.QueryInterface(
                        new Guid.REFIID(
                                IUIAutomationElementArray.IID),
                                pUnknown);

        if (COMUtils.FAILED(result0)) {
            throw new AutomationException(result0.intValue());
        }

        return IUIAutomationElementArrayConverter.pointerToInterface(pUnknown);
    }

    /**
     * Turns a collection (array) of automation elements, into a collection.
     *
     * @param collection The ElementArray.
     * @return The List.
     * @throws AutomationException Error in the automation library.
     */
    public List<AutomationElement> collectionToList(
            final IUIAutomationElementArray collection)
            throws AutomationException {

        IntByReference ibr = new IntByReference();

        final int res = collection.getLength(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        List<AutomationElement> list = new ArrayList<>();

        for (int count = 0; count < ibr.getValue(); count++) {

            PointerByReference pbr = new PointerByReference();

            final int res1 = collection.getElement(count, pbr);
            if (res1 != 0) {
                throw new AutomationException(res1);
            }

            Unknown uElement = new Unknown(pbr.getValue());

            WinNT.HRESULT result0 =
                    uElement.QueryInterface(
                            new Guid.REFIID(IUIAutomationElement.IID), pbr);

            if (COMUtils.SUCCEEDED(result0)) {
                IUIAutomationElement element =
                        IUIAutomationElementConverter.pointerToInterface(pbr);

                list.add(new AutomationElement(element));
            }
        }

        return list;
    }

    /**
     * Gets the raw pointer to the element.
     *
     * @param element The underlying element.
     * @return Pointer The raw pointer.
     * @throws AutomationException An error has occurred in the automation
     *                             library.
     */
    protected Pointer getPointerFromElement(final IUIAutomationElement element)
            throws AutomationException {
        PointerByReference pElement = new PointerByReference();

        WinNT.HRESULT result1 =
                element.QueryInterface(
                        new Guid.REFIID(IUIAutomationElement.IID), pElement);
        if (!COMUtils.SUCCEEDED(result1)) {
            throw new AutomationException(result1.intValue());
        }

        return pElement.getValue();
    }

    /**
     * Converts a propertyValue into its boolean representation.
     *
     * @param propertyValue the result from a getPropertyValue() call
     * @return true if true (= !0), false otherwise
     *
     */
    public static boolean isPropertyValueTrue(final Object propertyValue) {
        if (propertyValue instanceof VARIANT_BOOL) {
            return ((VARIANT_BOOL) propertyValue).booleanValue();
        }

        if (propertyValue instanceof BOOL) {
            return ((BOOL) propertyValue).booleanValue();
        }

        if (propertyValue instanceof Boolean) {
            return ((Boolean) propertyValue).booleanValue();
        }

        if (propertyValue instanceof Number) {
            return ((Number) propertyValue).intValue() != 0;
        }

        return !propertyValue.equals(0);
    }
}
