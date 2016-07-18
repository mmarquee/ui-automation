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
import com.sun.jna.platform.win32.COM.Unknown;
import com.sun.jna.platform.win32.Guid;
import com.sun.jna.platform.win32.WinNT;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.uiautomation.IUIAutomationItemContainerPattern;
import mmarquee.automation.uiautomation.IUIAutomationSelectionItemPattern;

/**
 * Created by inpwt on 25/02/2016.
 *
 * Wrapper for the SelectionItem pattern.
 */
public class SelectionItem extends BasePattern {

    private IUIAutomationSelectionItemPattern getPattern() {
        Unknown uElement = new Unknown(this.pattern);

        Guid.REFIID refiidElement = new Guid.REFIID(IUIAutomationSelectionItemPattern.IID);

        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT result0 = uElement.QueryInterface(refiidElement, pbr);

        if (COMUtils.SUCCEEDED(result0)) {
            return IUIAutomationSelectionItemPattern.Converter.PointerToInterface(pbr);
        } else {
            return null; // or throw exception?
        }
    }

    /**
     * Selects the given item
     */
    public void select () {
        this.getPattern().Select();
    }

    /**
     * Is the control selected
     * @return True if selected
     */
    public boolean isSelected () {
        IntByReference ibr = new IntByReference();

        int result = this.getPattern().Get_CurrentIsSelected(ibr);

        return (ibr.getValue() == 1);
    }
}
