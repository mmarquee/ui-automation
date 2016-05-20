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

package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Toggle;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.ToggleState;

/**
 * Created by inpwt on 31/01/2016.
 *
 * Wrapper for the CheckBox element.
 */
public class AutomationCheckbox extends AutomationBase {

    private Toggle togglePattern;

    /**
     * <p>
     * Invokes the toggle event for this control
     * </p>
     */
    public void toggle () {
        this.togglePattern.toggle();
    }

    /**
     * <p>
     * Gets the toggle state of this control
     * </p>
     * @return The toggle state
     */
    public ToggleState getToggleState () {
        return this.togglePattern.currentToggleState();
    }

    /**
     * Constructor for the AutomationCheckbox
     * @param element The element
     */
    public AutomationCheckbox (AutomationElement element) {
        super(element);

        try {
            togglePattern = this.getTogglePattern();
        } catch (PatternNotFoundException ex) {
            // Handle this nicely somehow
        }
    }
}
