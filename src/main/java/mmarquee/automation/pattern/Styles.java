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
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationStylesPattern;

/**
 * Created by Mark Humphreys on 01/03/2016.
 *
 * Wrapper around the styles pattern.
 */
public class Styles extends BasePattern {

    /**
     * Constructor for the value pattern
     */
    public Styles() {
        this.IID = IUIAutomationStylesPattern.IID;
    }

    private IUIAutomationStylesPattern getPattern() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT result0 = this.getRawPatternPointer(pbr);

        if (COMUtils.SUCCEEDED(result0)) {
            return IUIAutomationStylesPattern.Converter.PointerToInterface(pbr);
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Gets the style by name
     * @return The style name.
     * @throws AutomationException Something has gone wrong
     */
    public String getStyleName() throws AutomationException {
        PointerByReference sr = new PointerByReference();

        if (this.getPattern().Get_CurrentStyleName(sr) != 0) {
            throw new AutomationException();
        }

        return sr.getValue().getWideString(0);
    }

    /**
     * Gets the style id.
     * @return The style id.
     * @throws AutomationException Something has gone wrong
     */
    public int getStyleId() throws AutomationException {
        IntByReference ipr = new IntByReference();

        if (this.getPattern().Get_CurrentStyleId(ipr) != 0) {
            throw new AutomationException();
        }

        return ipr.getValue();
    }
}
