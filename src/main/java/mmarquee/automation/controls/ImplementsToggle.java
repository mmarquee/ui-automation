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

import mmarquee.automation.AutomationException;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Toggle;
import mmarquee.automation.uiautomation.ToggleState;

/**
 * @author Mark Humphreys
 * Date 19/05/2017.
 */
public interface ImplementsToggle extends Automatable, CanRequestBasePattern {
    /**
     * Toggles the element.
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
    default void toggle() throws AutomationException, PatternNotFoundException {
		final Toggle togglePattern = requestAutomationPattern(Toggle.class);
		if (togglePattern.isAvailable()) {
			togglePattern.toggle();
			return;
		}
		throw new PatternNotFoundException("Cannot toggle");
    }

    /**
     * Gets the state of the toggle.
     * @return Toggled state
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
    default ToggleState getToggleState() throws AutomationException, PatternNotFoundException {
		final Toggle togglePattern = requestAutomationPattern(Toggle.class);
		if (togglePattern.isAvailable()) {
			return togglePattern.currentToggleState();
		}
		throw new PatternNotFoundException("Cannot query toggle state");
    }
}
