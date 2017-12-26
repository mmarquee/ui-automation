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
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.uiautomation.patterns.IUIAutomationExpandCollapsePattern;
import mmarquee.automation.uiautomation.patterns.IUIAutomationExpandCollapsePatternConverter;

/**
 * Wrapper for  the ExpandCollapse pattern.
 *
 * @author Mark Humphreys
 * Date 25/02/2016.
 */
public class ExpandCollapse extends BasePattern {

    /**
     * Constructor for the pattern.
     */
    public ExpandCollapse() {
        this.IID = IUIAutomationExpandCollapsePattern.IID;
    }

    /**
     * The raw pointer.
     */
    private IUIAutomationExpandCollapsePattern rawPattern;

    /**
     * Constructor taking a raw pattern.
     * @param rawPattern The raw pattern
     */
    ExpandCollapse(final IUIAutomationExpandCollapsePattern rawPattern) {
        this();
        this.rawPattern = rawPattern;
    }

    /**
     * Gets the pattern.
     * @return The pattern
     * @throws AutomationException Something went wrong getting the pattern
     */
    private IUIAutomationExpandCollapsePattern getPattern()
            throws AutomationException {
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
     * Expands the control.
     * @throws AutomationException Something has gone wrong
     */
    public void expand() throws AutomationException {
        final int res = this.getPattern().expand();
        if (res != 0) {
            throw new AutomationException(res);
        }
    }

    /**
     * Collapses the control.
     * @throws AutomationException Something has gone wrong
     */
    public void collapse()throws AutomationException  {
        final int res = this.getPattern().collapse();
        if (res != 0) {
            throw new AutomationException(res);
        }
    }

    /**
     * Determines whether the control is expanded.
     * @return Is the control expanded
     * @throws AutomationException Something has gone wrong
     */
    public boolean isExpanded() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.getPattern().getCurrentExpandCollapseState(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return ibr.getValue() == 1;
    }

    /**
     * Gets the interface from the raw PointerByReference.
     * @param pUnknown The Unknown pointer
     * @return The pattern
     */
    public IUIAutomationExpandCollapsePattern convertPointerToInterface(
            final PointerByReference pUnknown) {
        return IUIAutomationExpandCollapsePatternConverter.PointerToInterface(pUnknown);
    }
}
