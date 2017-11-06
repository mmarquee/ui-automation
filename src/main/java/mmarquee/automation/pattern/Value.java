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

import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationTablePatternConverter;
import mmarquee.automation.uiautomation.IUIAutomationValuePattern;
import mmarquee.automation.uiautomation.IUIAutomationValuePatternConverter;

/**
 * @author Mark Humphreys
 * Date 25/02/2016.
 *
 * Wrapper for the value pattern.
 */
public class Value extends BasePattern {

    /**
     * Constructor for the value pattern
     */
    public Value() {
        this.IID = IUIAutomationValuePattern.IID;
    }

    private IUIAutomationValuePattern rawPattern;

    public Value(IUIAutomationValuePattern rawPattern) {
        this.IID = IUIAutomationValuePattern.IID;
        this.rawPattern = rawPattern;
    }

    /**
     * Gets the pattern
     * @return The actual pattern itself
     */
    private IUIAutomationValuePattern getPattern() throws AutomationException {
        if (this.rawPattern != null) {
            return this.rawPattern;
        } else {
            PointerByReference pbr = new PointerByReference();

            WinNT.HRESULT result0 = this.getRawPatternPointer(pbr);

            if (COMUtils.SUCCEEDED(result0)) {
                return this.convertPointerToInterface(pbr);
            } else {
                throw new AutomationException(result0.intValue());
            }
        }
    }

    /**
     * Get the current value of the control
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
     * Gets the current readonly status of the control
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
     * Sets the value of the control
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

    public IUIAutomationValuePattern convertPointerToInterface(PointerByReference pUnknownA) {
        return IUIAutomationValuePatternConverter.PointerToInterface(pUnknownA);
    }
}
