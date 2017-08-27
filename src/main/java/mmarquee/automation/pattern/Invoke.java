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

import com.sun.jna.platform.win32.COM.COMUtils;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.IUIAutomationInvokePattern;
import mmarquee.automation.uiautomation.IUIAutomationWindowPattern;

/**
 * Created by Mark Humphreys on 25/02/2016.
 *
 * Wrapper for the Invoke pattern
 */
public class Invoke extends BasePattern {

    /**
     * Constructor for the pattern
     */
    public Invoke() {
        this.IID = IUIAutomationInvokePattern.IID;
    }

    private IUIAutomationInvokePattern rawPattern;

    public Invoke(IUIAutomationInvokePattern rawPattern) {
        this.IID = IUIAutomationInvokePattern.IID;
        this.rawPattern = rawPattern;
    }

    /**
     * Gets the pattern
     * @return The pattern
     * @throws AutomationException Something went wrong getting the pattern
     */
    private IUIAutomationInvokePattern getPattern() throws AutomationException {
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
     * Invokes the pattern on the control
     * @throws AutomationException Something went wrong getting the pattern
     */
    public void invoke() throws AutomationException {
        final int res = this.getPattern().invoke();
        if (res != 0) {
            throw new AutomationException(res);
        }
    }

    public IUIAutomationInvokePattern convertPointerToInterface(PointerByReference pUnknown) {
        return IUIAutomationInvokePattern.Converter.PointerToInterface(pUnknown);
    }
}
