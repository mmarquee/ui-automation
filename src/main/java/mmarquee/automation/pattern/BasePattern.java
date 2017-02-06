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
package mmarquee.automation.pattern;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationElement3;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Humphreys on 29/02/2016.
 *
 * Base for the pattern wrappers
 */
public abstract class BasePattern implements Pattern {

    /**
     * The guid of the pattern.
     */
    protected Guid.IID IID;

    /**
     * The underlying automation pattern
     */
    protected Pointer pattern;

    /**
     * Constructs a Pattern
     */
    public BasePattern () {
        this.pattern = null;
    }

    /**
     * Turns a collection (array) of automation elements, into a collection.
     *
     * @param collection The ElementArray.
     * @return The List
     * @throws AutomationException Error in the automation library
     */
    List<AutomationElement> collectionToList(IUIAutomationElementArray collection) throws AutomationException {

        IntByReference ibr = new IntByReference();

        if (collection.getLength(ibr) != 0) {
            throw new AutomationException();
        }

        List<AutomationElement> list = new ArrayList<AutomationElement>();

        for (int count = 0; count < ibr.getValue(); count++) {

            PointerByReference pbr = new PointerByReference();

            if (collection.getElement(count, pbr) != 0) {
                throw new AutomationException();
            }

            Unknown uElement = new Unknown(pbr.getValue());

            WinNT.HRESULT result0 = uElement.QueryInterface(new Guid.REFIID(IUIAutomationElement3.IID), pbr);

            if (COMUtils.SUCCEEDED(result0)) {
                IUIAutomationElement3 element =
                        IUIAutomationElement3.Converter.PointerToInterface(pbr);

                list.add(new AutomationElement(element));
            }
        }

        return list;
    }

    /**
     * Sets the pattern
     * @param pattern The pattern to set
     */
    public void setPattern(Pointer pattern) {
        this.pattern =  pattern;
    }

    /**
     * Is this pattern available?
     * @return True if available.
     */
    public boolean isAvailable () {
        return (pattern == null);
    }

    /**
     * Gets the raw pointer to the pattern
     * @param pbr The raw pointer
     * @return Result of the call from the COM library
     */
    public WinNT.HRESULT getRawPatternPointer(PointerByReference pbr) {
        Unknown uElement = makeUnknown(this.pattern);
        return uElement.QueryInterface(new Guid.REFIID(this.IID), pbr);
    }

    /**
     * Creates an Unknown object from the pointer.
     *
     * Allows Mockito to be used to create Unknown objects
     *
     * @param pvInstance The pointer to use
     * @return An Unknown object
     */
    public Unknown makeUnknown(Pointer pvInstance) {
        return new Unknown(pvInstance);
    }

    /**
     * Converts the unknown value to a IUIAutomationElement3
     * @param pUnknownA The Unknown pointer
     * @return The pattern
     */
    public IUIAutomationElement3 convertPointerToElementInterface(PointerByReference pUnknownA) {
        return IUIAutomationElement3.Converter.PointerToInterface(pUnknownA);
    }

    /**
     * Converts the unknown value to a IUIAutomationElementArray
     * @param pUnknownA The Unknown pointer
     * @return The pattern
     */
    public IUIAutomationElementArray convertPointerToElementArrayInterface(PointerByReference pUnknownA) {
        return IUIAutomationElementArray.Converter.PointerToInterface(pUnknownA);
    }
}
