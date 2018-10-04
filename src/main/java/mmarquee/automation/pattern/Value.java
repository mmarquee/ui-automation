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

import com.sun.jna.platform.win32.OleAuto;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.uiautomation.IUIAutomationValuePattern;
import mmarquee.automation.uiautomation.IUIAutomationValuePatternConverter;

/**
 * Wrapper for the value pattern.
 *
 * @author Mark Humphreys
 * Date 25/02/2016.
 */
public class Value extends BasePattern {

    /**
     * Constructor for the value pattern.
     *
     * @param element The automation element for which the pattern is valid
     * @throws AutomationException If something goes wrong
     */
    public Value(final AutomationElement element) throws AutomationException {
    	super(element);
        this.IID = IUIAutomationValuePattern.IID;
        this.patternID = PatternID.Value;
        this.availabilityPropertyID = PropertyID.IsValuePatternAvailable;
    }

    /**
     * The raw pattern.
     */
    IUIAutomationValuePattern rawPattern;

    /**
     * Gets the pattern.
     *
     * @return The actual pattern itself
     * @throws AutomationException Error in automation library
     */
    private IUIAutomationValuePattern getPattern() throws AutomationException {
        return getPattern(rawPattern,this::convertPointerToInterface);
    }

    /**
     * Get the current value of the control.
     *
     * @return The current value
     * @throws AutomationException Something has gone wrong
     */
    public String value() throws AutomationException {
        PointerByReference sr = new PointerByReference();

        final int res = this.getPattern().getValue(sr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return sr.getValue().getWideString(0);
    }

    /**
     * Gets the current readonly status of the control.
     *
     * @return True if read-only
     * @throws AutomationException Something has gone wrong
     */
    public boolean isReadOnly() throws AutomationException {
        IntByReference ibr = new IntByReference();
        final int res = this.getPattern().getCurrentIsReadOnly(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return (ibr.getValue() == 1);
    }

    /**
     * Sets the value of the control.
     *
     * @param value Value to use
     * @throws NullPointerException When something has gone wrong
     * @throws AutomationException Something has gone wrong
     */
    public void setValue(String value) throws AutomationException, NullPointerException {
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString(value);

        try {
            final int res = this.getPattern().setValue(sysAllocated);
            if (res != 0) {
                throw new AutomationException(res);
            }
        } finally {
            OleAuto.INSTANCE.SysFreeString(sysAllocated);
        }
    }

    /**
     * Converts the raw pointer to the interface.
     *
     * @param pUnknownA The raw pointer
     * @return The converted interface
     */
    IUIAutomationValuePattern convertPointerToInterface(
            final PointerByReference pUnknownA) {
        return IUIAutomationValuePatternConverter.pointerToInterface(pUnknownA);
    }
}
