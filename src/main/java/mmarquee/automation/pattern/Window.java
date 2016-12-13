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
import mmarquee.automation.uiautomation.IUIAutomationWindowPattern;
import mmarquee.automation.uiautomation.WindowVisualState;

/**
 * Created by Mark Humphreys on 25/02/2016.
 *
 * Wrapper for the window pattern.
 */
public class Window extends BasePattern {
    /**
     * Constructor for the value pattern
     */
    public Window() {
        this.IID = IUIAutomationWindowPattern.IID;
    }

    private IUIAutomationWindowPattern getPattern() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        WinNT.HRESULT result0 = this.getRawPatternPointer(pbr);

        if (COMUtils.SUCCEEDED(result0)) {
            return IUIAutomationWindowPattern.Converter.PointerToInterface(pbr);
        } else {
            throw new AutomationException();
        }
    }

    /**
     * Waits for the window to be idle, and allow input
     * @param timeout A timeout to use
     * @throws AutomationException Something has gone wrong
     */
    public void waitForInputIdle(int timeout) throws AutomationException {
        IntByReference ibr = new IntByReference();
        if (this.getPattern().waitForInputIdle(timeout, ibr) != 0) {
            throw new AutomationException();
        }
    }

    /**
     * Maximize the 'window'
     * @throws AutomationException Something has gone wrong
     */
    public void maximize() throws AutomationException {
        this.setWindowState(WindowVisualState.Maximized);
    }

    /**
     * Minimize the 'window'
     * @throws AutomationException Something has gone wrong
     */
    public void minimize() throws AutomationException {
        this.setWindowState(WindowVisualState.Minimized);
    }

    /**
     * Returns whether this control is modal
     * @return Is this control modal?
     * @throws AutomationException Something has gone wrong
     */
    public boolean isModal() throws AutomationException {
        IntByReference ibr = new IntByReference();
        if (this.getPattern().getCurrentIsModal(ibr) != 0) {
            throw new AutomationException();
        }

        return (ibr.getValue()  == 1);
    }

    /**
     * IS this window topmost
     * @return Is the window topmost
     * @throws AutomationException Something has gone wrong
     */
    public boolean isTopMost() throws AutomationException {
        IntByReference ibr = new IntByReference();
        if (this.getPattern().getCurrentIsTopmost(ibr) != 0) {
            throw new AutomationException();
        }

        return (ibr.getValue()  == 1);
    }

    /**
     * Closes the 'window'
     * @throws AutomationException Something has gone wrong
     */
    public void close() throws AutomationException {
        if (this.getPattern().close() != 0) {
            throw new AutomationException();
        }
    }

    /**
     * Sets the visual state
     * @param state The state to set
     * @throws AutomationException Something has gone wrong
     */
    public void setWindowState(WindowVisualState state) throws AutomationException {
        if (this.getPattern().setWindowVisualState(state.getValue()) != 0) {
            throw new AutomationException();
        }
    }
}
