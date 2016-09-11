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
import mmarquee.automation.uiautomation.IUIAutomationExpandCollapsePattern;

/**
 * Created by inpwt on 25/02/2016.
 *
 * Wrapper for  the ExpandCollapse pattern
 */
public class ExpandCollapse extends BasePattern {

    /**
     * Constructor for the pattern
     */
    public ExpandCollapse() {
        this.IID = IUIAutomationExpandCollapsePattern.IID;
    }

    /**
     * Gets the pattern
     * @return The pattern
     * @throws AutomationException Something went wrong getting the pattern
     */
    private IUIAutomationExpandCollapsePattern getPattern() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT result0 = this.getRawPatternPointer(pbr);

        if (COMUtils.SUCCEEDED(result0)) {
            return IUIAutomationExpandCollapsePattern.Converter.PointerToInterface(pbr);
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Expands the control
     * @throws AutomationException Something has gone wrong
     */
    public void expand() throws AutomationException {
        if (this.getPattern().Expand() != 0) {
            throw new AutomationException();
        }
    }

    /**
     * Collapses the control
     * @throws AutomationException Something has gone wrong
     */
    public void collapse()throws AutomationException  {
        if (this.getPattern().Collapse() != 0) {
            throw new AutomationException();
        }
    }

    /**
     * Determines whether the control is expanded
     * @return Is the control expanded
     * @throws AutomationException Something has gone wrong
     */
    public boolean isExpanded() throws AutomationException {
        IntByReference ibr = new IntByReference();

        if (this.getPattern().Get_CurrentExpandCollapseState(ibr) != 0) {
            throw new AutomationException();
        }

        return ibr.getValue() == 1; //ExpandCollapseState.ExpandCollapseState_Expanded;
    }
}
