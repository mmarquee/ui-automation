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

import mmarquee.automation.pattern.raw.IUIAutomationExpandCollapsePattern;
import mmarquee.automation.uiautomation.ExpandCollapseState;

/**
 * Created by inpwt on 25/02/2016.
 *
 * Wrapper for  the ExpandCollapse pattern
 */
public class ExpandCollapse extends BasePattern {
    /**
     * Expands the control
     */
    public void expand() {
        ((IUIAutomationExpandCollapsePattern)this.pattern).expand();
    }

    /**
     * Collapses the control
     */
    public void collapse() {
        ((IUIAutomationExpandCollapsePattern)this.pattern).collapse();
    }

    /**
     * Determines whether the control is expanded
     * @return Is the control expanded
     */
    public boolean isExpanded() {
        ExpandCollapseState state =
            ((IUIAutomationExpandCollapsePattern)this.pattern).currentExpandCollapseState();

        return state == ExpandCollapseState.ExpandCollapseState_Expanded;
    }
}
