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

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.uiautomation.IUIAutomationWindowPattern;
import mmarquee.automation.uiautomation.IUIAutomationWindowPatternConverter;
import mmarquee.automation.uiautomation.WindowVisualState;

/**
 * Wrapper for the window pattern.
 *
 * @author Mark Humphreys
 * Date 25/02/2016.
 */
public class Window extends BasePattern {
    /**
     * Constructor for the window pattern.
     *
     * @param element The automation element for which the pattern is valid
     * @throws AutomationException If something goes wrong
     */
    public Window(final AutomationElement element) throws AutomationException {
    	super(element);
        this.IID = IUIAutomationWindowPattern.IID;
        this.patternID = PatternID.Window;
        this.availabilityPropertyID = PropertyID.IsWindowPatternAvailable;
    }

    /**
     * The raw pattern.
     */
    IUIAutomationWindowPattern rawPattern;

    /**
     * Gets the pattern.
     *
     * @return The pattern
     * @throws AutomationException Error in automation library
     * */
    private IUIAutomationWindowPattern getPattern() throws AutomationException {
        return getPattern(rawPattern,this::convertPointerToInterface);
    }

    /**
     * Waits for the window to be idle, and allow input.
     *
     * @param timeout A timeout to use
     * @throws AutomationException Something has gone wrong
     */
    public void waitForInputIdle(int timeout) throws AutomationException {
        IntByReference ibr = new IntByReference();
        final int res = this.getPattern().waitForInputIdle(timeout, ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }
    }

    /**
     * Maximize the 'window'.
     *
     * @throws AutomationException Something has gone wrong
     */
    public void maximize() throws AutomationException {
        this.setWindowState(WindowVisualState.Maximized);
    }

    /**
     * Minimize the 'window'.
     *
     * @throws AutomationException Something has gone wrong
     */
    public void minimize() throws AutomationException {
        this.setWindowState(WindowVisualState.Minimized);
    }

    /**
     * Can the window be maximized.
     *
     * @return True or false
     * @throws AutomationException Something is wrong
     */
    public boolean getCanMaximize() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.getPattern().getCurrentCanMaximize(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return (ibr.getValue() == 1);
    }

    /**
     * Can the window be minimized.
     *
     * @return True or false
     * @throws AutomationException Something is wrong
     */
    public boolean getCanMinimize() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.getPattern().getCurrentCanMinimize(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return (ibr.getValue() == 1);
    }

    /**
     * Returns whether this control is modal.
     *
     * @return Is this control modal?
     * @throws AutomationException Something has gone wrong
     */
    public boolean isModal() throws AutomationException {
        IntByReference ibr = new IntByReference();
        final int res = this.getPattern().getCurrentIsModal(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return (ibr.getValue()  == 1);
    }

    /**
     * IS this window topmost.
     *
     * @return Is the window topmost
     * @throws AutomationException Something has gone wrong
     */
    public boolean isTopMost() throws AutomationException {
        IntByReference ibr = new IntByReference();
        final int res = this.getPattern().getCurrentIsTopmost(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return (ibr.getValue()  == 1);
    }

    /**
     * Closes the 'window'.
     *
     * @throws AutomationException Something has gone wrong
     */
    public void close() throws AutomationException {
        final int res = this.getPattern().close();
        if (res != 0) {
            throw new AutomationException(res);
        }
    }

    /**
     * Sets the visual state.
     *
     * @param state The state to set
     * @throws AutomationException Something has gone wrong
     */
    public void setWindowState(WindowVisualState state) throws AutomationException {
        final int res = this.getPattern().setWindowVisualState(state.getValue());
        if (res != 0) {
            throw new AutomationException(res);
        }
    }

    /**
     * Converts the raw pointer to the interface.
     *
     * @param pUnknownA The raw pointer
     * @return The converted interface
     */
    IUIAutomationWindowPattern convertPointerToInterface(
            final PointerByReference pUnknownA) {
        return IUIAutomationWindowPatternConverter.pointerToInterface(pUnknownA);
    }
}
