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
package mmarquee.automation;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.Variant;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.uiautomation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 06/03/2016.
 * <p>
 * Wrapper for the underlying automation element.
 */
public class AutomationElement {
    /**
     * The underlying automation element
     * <p>
     * TODO: Make this work with protected (done for EventHandler)
     */
    public IUIAutomationElement element;

    /**
     * Constructor of AutomationElement
     *
     * @param element The element
     */
    public AutomationElement(IUIAutomationElement element) {
        this.element = element;
    }

    /**
     * Gets the property associated with the passed in id
     *
     * @param propertyId The property ID to get
     * @return The property ID
     */
    public Object get_CurrentPropertyValue(int propertyId) {
        Variant.VARIANT.ByReference value = new Variant.VARIANT.ByReference();

        int result = this.element.get_CurrentPropertyValue(propertyId, value);

        return value.getValue();  //??
    }

    /**
     * Gets the current control type
     *
     * @return The current control type
     */
    public int currentControlType() {
        IntByReference ibr = new IntByReference();

        int result = this.element.get_CurrentControlType(ibr);

        return ibr.getValue();
    }

    /**
     * Gets the current class name of the element
     *
     * @return The current class name
     */
    public String currentClassName() {
        PointerByReference sr = new PointerByReference();

        this.element.get_CurrentClassName(sr);

        return sr.getValue().getWideString(0);
    }

    /**
     * Gets the current IsPassword value.
     *
     * @return True if IsPassword
     */
    public Boolean currentIsPassword() {
        IntByReference ibr = new IntByReference();

        int result = this.element.get_CurrentIsPassword(ibr);

        return ibr.getValue() == 1;
    }

    /**
     * ]
     * Gets the name, either from the current ot cache property
     *
     * @return The name (either cached or current)
     */
    public String getName() {
        return this.currentName();
    }

    /**
     * Gets the current name of the control
     *
     * @return The name of the element
     */
    protected String currentName() {
        PointerByReference sr = new PointerByReference();

        element.get_CurrentName(sr);

        this.element.get_CurrentName(sr);

        return sr.getValue().getWideString(0);
    }

    /**
     * Sets the name of the element
     * @param name The name to use
     */
//    public void setName(String name) {
//        this.element.setName(name);
// Not sure how this worked
//    }

    /**
     * Finds the first element that matches the condition
     * @param scope Tree scope
     * @param condition The condition
     * @return The first matching element
     */
//    public AutomationElement findFirst(TreeScope scope, Condition condition) /{
//
    //      PointerByReference pbr = new PointerByReference();
//
    //      int result = this.element.findFirst(scope, condition.getCondition(), pbr);
///
    //     Guid.REFIID refiidElement = new Guid.REFIID(IUIAutomationElement.IID_IUIAUTOMATION_ELEMENT);
//
    //      Unknown uRoot = new Unknown(pbr.getValue());
    //
    //  WinNT.HRESULT result0 = uRoot.QueryInterface(refiidElement, pbr);
//
    //      if (COMUtils.SUCCEEDED(result0)) {
    //        return new AutomationElement(IUIAutomationElement.Converter.PointerToIUIAutomationElement(pbr));
    //  } else {
    //    return null;
    // }
    //  }

    /**
     * Finds the first element that matches the raw condition
     *
     * @param scope      Tree scope
     * @param pCondition The raw condition
     * @return The first matching element
     */
    public AutomationElement findFirst(TreeScope scope, PointerByReference pCondition) throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        this.element.findFirst(scope, pCondition.getValue(), pbr);

        // See what we got
        Unknown uElement = new Unknown(pbr.getValue());

        Guid.REFIID refiidElement = new Guid.REFIID(IUIAutomationElement.IID);

        WinNT.HRESULT result0 = uElement.QueryInterface(refiidElement, pbr);

        if (COMUtils.SUCCEEDED(result0)) {
            IUIAutomationElement element =
                    IUIAutomationElement.Converter.PointerToInterface(pbr);
            return new AutomationElement(element);
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Get the current pattern that matches the patternId
     *
     * @param patternId What pattern to get
     * @return The pattern
     */
    public PointerByReference getCurrentPattern(int patternId) {
        PointerByReference pbr = new PointerByReference();

        int result = this.element.get_CurrentPattern(patternId, pbr);

        return pbr;
    }

    /**
     * Sets focus to the element
     */
    public void setFocus() {
        this.element.setFocus();
    }

    /**
     * Gets all of the elements that match the condition and scope
     *
     * @param scope      The scope in the element tree
     * @param pCondition The condition
     * @return List of matching elements
     */
    public List<AutomationElement> findAll(TreeScope scope, Pointer pCondition) {

        List<AutomationElement> items = new ArrayList<AutomationElement>();

        PointerByReference pAll = new PointerByReference();

        int resultAll = this.element.findAll(scope, pCondition, pAll);

        // What has come out of findAll ??

        Unknown unkConditionA = new Unknown(pAll.getValue());
        PointerByReference pUnknownA = new PointerByReference();

        Guid.REFIID refiidA = new Guid.REFIID(IUIAutomationElementArray.IID);

        WinNT.HRESULT resultA = unkConditionA.QueryInterface(refiidA, pUnknownA);
        if (COMUtils.SUCCEEDED(resultA)) {
            IUIAutomationElementArray collection =
                    IUIAutomationElementArray.Converter.PointerToInterface(pUnknownA);

            IntByReference ibr = new IntByReference();

            collection.get_Length(ibr);

            int counter = ibr.getValue();

            for (int a = 0; a < counter; a++) {
                PointerByReference pbr = new PointerByReference();

                collection.GetElement(a, pbr);

                // Now make a Element out of it

                Unknown uElement = new Unknown(pbr.getValue());

                Guid.REFIID refiidElement = new Guid.REFIID(IUIAutomationElement.IID);

                WinNT.HRESULT result0 = uElement.QueryInterface(refiidElement, pbr);

                if (COMUtils.SUCCEEDED(result0)) {
                    IUIAutomationElement element =
                            IUIAutomationElement.Converter.PointerToInterface(pbr);

                    items.add(new AutomationElement(element));
                }
            }
        }

        return items;
    }

    /**
     * Gets the current ARIA role
     *
     * @return String representing the ARIA role
     */
    public String getAriaRole() {
        PointerByReference sr = new PointerByReference();

        this.element.get_CurrentAriaRole(sr);

        return sr.getValue().getWideString(0);
    }

    /**
     * Gets the current orientation
     *
     * @return The orientation
     */
    public OrientationType getOrientation() throws Exception {
        IntByReference ibr = new IntByReference();

        int result = this.element.get_CurrentOrientation(ibr);

        // Hummm..

        int value = ibr.getValue();

        if (value == 0) {
            return OrientationType.OrientationType_None;
        } else if (value == 1) {
            return OrientationType.OrientationType_Horizontal;
        } else if (value == 2) {
            return OrientationType.OrientationType_Vertical;
        } else {
            throw new Exception("Not a valid orientation");
        }
    }

    /**
     * Gets the framework ID
     *
     * @return The framework ID
     */
    public String getFrameworkId() {

        PointerByReference sr = new PointerByReference();

        int result = this.element.get_CurrentFrameworkId(sr);

        return sr.getValue().getWideString(0);
    }

    /**
     * Gets the provider description
     *
     * @return The provider description
     */
    public String getProviderDescription() {
        PointerByReference sr = new PointerByReference();

        int result = this.element.get_CurrentProviderDescription(sr);

        return sr.getValue().getWideString(0);
    }
    /**
     * Get the runtime Id
     * @return The runtime ID
     */
    //   public int[] getRuntimeId() {
    //       return element.getRuntimeId();
    //   }

    /**
     * Gets the process ID
     *
     * @return The process ID
     */
    public Integer getProcessId() {
        IntByReference ibr = new IntByReference();
        int result = this.element.get_CurrentProcessId(ibr);

        return ibr.getValue();
    }

    /**
     * Gets the current item status
     *
     * @return The status
     */
    public String getItemStatus() {
        PointerByReference sr = new PointerByReference();

        int result = this.element.get_CurrentItemStatus(sr);

        return sr.getValue().getWideString(0);
    }

    /**
     * Gets the current accelerator key associated with the element
     *
     * @return The accelerator key
     */
    public String getAcceleratorKey() {
        PointerByReference sr = new PointerByReference();

        int result = this.element.get_CurrentAcceleratorKey(sr);

        return sr.getValue().getWideString(0);
    }
}