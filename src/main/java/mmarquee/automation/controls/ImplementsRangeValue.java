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
import mmarquee.automation.pattern.Range;

/**
 * The Control supports the methods of the RangeValue pattern.
 *
 * @author Mark Humphreys
 * Date 21/09/2016.
 */
public interface ImplementsRangeValue extends Automatable, CanRequestBasePattern {

    /**
     * Gets the range value.
     *
     * @return The range value.
     * @throws AutomationException Error in automation library.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    default double getRangeValue()
            throws AutomationException, PatternNotFoundException {
		final Range rangePattern = requestAutomationPattern(Range.class);
		if (rangePattern.isAvailable()) {
			return rangePattern.getValue();
		}
		throw new PatternNotFoundException("Cannot get range value");
    }

    /**
     * Sets the range value.
     *
     * @param value The value to set.
     * @throws AutomationException Error in automation library.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    default void setRangeValue(final double value)
            throws AutomationException, PatternNotFoundException {
		final Range rangePattern = requestAutomationPattern(Range.class);
		if (rangePattern.isAvailable()) {
			rangePattern.setValue(value);
			return;
		}
		throw new PatternNotFoundException("Cannot set range value");
    }
}
