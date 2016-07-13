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

import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.uiautomation.IUIAutomationItemContainerPattern;
import mmarquee.automation.uiautomation.IUIAutomationValuePattern;

import static com.sun.jna.platform.win32.Variant.VT_BSTR;

/**
 * Created by inpwt on 25/02/2016.
 *
 * Wrapper for the value pattern.
 */
public class Value extends BasePattern {
    private IUIAutomationValuePattern getPattern() {
        Unknown uElement = new Unknown(this.pattern);

        Guid.REFIID refiidElement = new Guid.REFIID(IUIAutomationValuePattern.IID);

        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT result0 = uElement.QueryInterface(refiidElement, pbr);

        if (COMUtils.SUCCEEDED(result0)) {
            return IUIAutomationValuePattern.Converter.PointerToInterface(pbr);
        } else {
            return null; // or throw exception?
        }
    }

    /**
     * Get the current value of the control
     * @return The current value
     */
    public String value() {
        PointerByReference sr = new PointerByReference();
        this.getPattern().Get_CurrentValue(sr);

        return sr.getValue().getWideString(0);
    }

    /**
     * Gets the current readonly status of the control
     * @return True if read-only
     */
    public boolean isReadOnly() {
        IntByReference ibr = new IntByReference();
        this.getPattern().Get_CurrentIsReadOnly(ibr);

        return (ibr.getValue() == 1);
    }

    /**
     * Sets the value of the control
     * @param value Value to use
     */
    public void setValue(String value) {
        Variant.VARIANT.ByValue variant = new Variant.VARIANT.ByValue();
        WTypes.BSTR sysAllocated = OleAuto.INSTANCE.SysAllocString(value);
        variant.setValue(Variant.VT_BSTR, sysAllocated);

        this.getPattern().Set_Value(variant);
    }
}
