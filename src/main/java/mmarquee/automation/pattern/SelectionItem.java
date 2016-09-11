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
import mmarquee.automation.uiautomation.IUIAutomationSelectionItemPattern;

/**
 * Created by Mark Humphreys on 25/02/2016.
 *
 * Wrapper for the SelectionItem pattern.
 */
public class SelectionItem extends BasePattern {

    /**
     * Constructor for the value pattern
     */
    public SelectionItem() {
        this.IID = IUIAutomationSelectionItemPattern.IID;
    }

    private IUIAutomationSelectionItemPattern getPattern() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT result0 = this.getRawPatternPointer(pbr);

        if (COMUtils.SUCCEEDED(result0)) {
            return IUIAutomationSelectionItemPattern.Converter.PointerToInterface(pbr);
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Selects the given item
     * @throws AutomationException Something has gone wrong
     */
    public void select() throws AutomationException {
        this.getPattern().Select();
    }

    /**
     * Is the control selected
     * @return True if selected
     * @throws AutomationException Something has gone wrong
     */
    public boolean isSelected() throws AutomationException {
        IntByReference ibr = new IntByReference();

        if (this.getPattern().Get_CurrentIsSelected(ibr) != 0) {
            throw new AutomationException();
        }

        return (ibr.getValue() == 1);
    }
}
