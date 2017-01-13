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

package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Toggle;
import mmarquee.automation.uiautomation.ToggleState;

/**
 * Created by Mark Humphreys on 31/01/2016.
 *
 * Wrapper for the CheckBox element.
 */
public class AutomationCheckbox extends AutomationBase {

    private Toggle togglePattern;

    /**
     * <p>
     * Invokes the toggle event for this control
     * </p>
     * @throws AutomationException Something has gone wrong
     */
    public void toggle () throws AutomationException, PatternNotFoundException {
        if (this.togglePattern == null) {
            togglePattern = this.getTogglePattern();
        }

        this.togglePattern.toggle();
    }

    /**
     * <p>
     * Gets the toggle state of this control
     * </p>
     * @return The toggle state
     * @throws AutomationException Something has gone wrong
     */
    public ToggleState getToggleState () throws AutomationException, PatternNotFoundException {
        if (this.togglePattern == null) {
            togglePattern = this.getTogglePattern();
        }

        return this.togglePattern.currentToggleState();
    }

    /**
     * Constructor for the AutomationCheckbox
     * @param element The element
     * @throws AutomationException Automation exception
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationCheckbox (AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);
//        togglePattern = this.getTogglePattern();
    }

    /**
     * Constructor for the AutomationCheckbox
     * @param element The element
     * @param pattern The pattern
     */
    public AutomationCheckbox(AutomationElement element, Toggle pattern) {
        super(element);
        togglePattern = pattern;
    }
}
