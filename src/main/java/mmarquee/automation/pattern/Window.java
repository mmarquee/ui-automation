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

import mmarquee.automation.pattern.raw.IUIAutomationWindowPattern;
import mmarquee.automation.uiautomation.WindowVisualState;

/**
 * Created by inpwt on 25/02/2016.
 */
public class Window extends BasePattern {

    /**
     * Waits for the window to be idle, and allow input
     * @param timeout A timeout to use
     */
    public void waitForInputIdle(int timeout){
        ((IUIAutomationWindowPattern)this.pattern).waitForInputIdle(timeout);
    }

    /**
     * Maximize the control
     */
    public void maximize() {
        this.setWindowState(WindowVisualState.WindowVisualState_Maximized);
    }

    /**
     * Minimize the control
     */
    public void minimize() {
        this.setWindowState(WindowVisualState.WindowVisualState_Minimized);
    }

    /**
     * Returns whether this control is modal
     * @return Is this control modal?
     */
    public boolean isModal () {
        return ((IUIAutomationWindowPattern)this.pattern).currentIsModal()  == 1;
    }

    /**
     * IS this window topmost
     * @return Is the window topmost
     */
    public boolean isTopMost () {
        return ((IUIAutomationWindowPattern)this.pattern).currentIsTopmost() == 1;
    }

    /**
     * Closes the window
     */
    public void close() {
        ((IUIAutomationWindowPattern)this.pattern).close();
    }

    /**
     * Sets the visual state
     * @param state The state to set
     */
    public void setWindowState(WindowVisualState state) {
        ((IUIAutomationWindowPattern)this.pattern).setWindowVisualState(state);
    }
}
