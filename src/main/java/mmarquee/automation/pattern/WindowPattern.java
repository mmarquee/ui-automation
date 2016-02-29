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

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 25/02/2016.
 */
public class WindowPattern extends BasePattern {

    /**
     * Waits for the window to be idle, and allow input
     * @param timeout A timeout to use
     */
    public void waitForInputIdle(int timeout){
        ((IUIAutomationWindowPattern)this.pattern).waitForInputIdle(timeout);
    }

    /**
     * Maximize the controll
     */
    public void maximize() {
        ((IUIAutomationWindowPattern)this.pattern).setWindowVisualState(WindowVisualState.WindowVisualState_Maximized);
    }

    /**
     * Minimize the control
     */
    public void minimize() {
        ((IUIAutomationWindowPattern)this.pattern).setWindowVisualState(WindowVisualState.WindowVisualState_Minimized);
    }

    /**
     * Returns whether this control is modal
     * @return Is this control modal
     */
    public int currentIsModal () {
        return ((IUIAutomationWindowPattern)this.pattern).currentIsModal();
    }
}
