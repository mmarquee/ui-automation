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
import mmarquee.automation.pattern.Value;

/**
 * The Control supports the methods of the Value Pattern.
 *
 * @author Mark Humphreys
 * Date 19/05/2017.
 */
public interface ImplementsValue extends Automatable, CanRequestBasePattern {
    /**
     * Gets the value of the element.
     * @return The value
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
    default String getValue() throws AutomationException, PatternNotFoundException {
		final Value valuePattern = requestAutomationPattern(Value.class);
		if (valuePattern.isAvailable()) {
			return valuePattern.value();
		} else {
			throw new PatternNotFoundException("Cannot get value");
		}
    }

    /**
     * Sets the value of the element.
	 *
     * @param value the value to set
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
    default void setValue(final String value) throws AutomationException, PatternNotFoundException {
		final Value valuePattern = requestAutomationPattern(Value.class);
		if (valuePattern.isAvailable()) {
			valuePattern.setValue(value);
			return;
		} else {
			throw new PatternNotFoundException("Cannot set value");
		}
    }

    /**
     * Tests whether the Value is read only.
	 *
     * @return Read only?
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    default boolean isReadOnly() throws AutomationException, PatternNotFoundException {
		final Value valuePattern = requestAutomationPattern(Value.class);
		if (valuePattern.isAvailable()) {
			return valuePattern.isReadOnly();
		} else {
			throw new PatternNotFoundException("Cannot check read only state");
		}
    }
}
