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
package mmarquee.automation.pattern;

import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationRangeValuePattern;

/**
 * Created by Mark Humphreys on 01/03/2016.
 *
 * Wrapper for the range pattern.
 */
public class Range extends BasePattern {

    /**
     * Constructor for the value pattern
     */
    public Range() {
        this.IID = IUIAutomationRangeValuePattern.IID;
    }

    private IUIAutomationRangeValuePattern getPattern() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT result0 = this.getRawPatternPointer(pbr);

        if (COMUtils.SUCCEEDED(result0)) {
            return IUIAutomationRangeValuePattern.Converter.PointerToInterface(pbr);
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Sets the value
     * @param value The value to set
     * @throws AutomationException Something has gone wrong
     */
    public void setValue (double value) throws AutomationException {
        if (this.getPattern().Set_Value(value) != 0) {
            throw new AutomationException();
        }
    }

    /**
     * Gets the value
     * @return The value
     * @throws AutomationException Something has gone wrong
     */
    public double getValue () throws AutomationException {
        DoubleByReference dbr = new DoubleByReference();

        if (this.getPattern().Get_CurrentValue(dbr) != 0) {
            throw new AutomationException();
        }

        return dbr.getValue();
    }
}
