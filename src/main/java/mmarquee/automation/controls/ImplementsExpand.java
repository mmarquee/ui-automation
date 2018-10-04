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
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * @author Mark Humphreys
 * Date 21/09/2016.
 */
public interface ImplementsExpand extends Automatable, CanRequestBasePattern {

    /**
     * Expands the element.
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
    default void expand() throws AutomationException {
		final ExpandCollapse collapsePattern = requestAutomationPattern(ExpandCollapse.class);
		if (collapsePattern.isAvailable()) {
			collapsePattern.expand();
			return;
		}
		throw new PatternNotFoundException("Cannot expand");
    }

    /**
     * Collapses the element.
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
    default void collapse() throws AutomationException {
        final ExpandCollapse collapsePattern = requestAutomationPattern(ExpandCollapse.class);
        if (collapsePattern.isAvailable()) {
        	collapsePattern.collapse();
			return;
        } 
        throw new PatternNotFoundException("collapse could not be called");
    }

    /**
     * Whether the element is expanded.
     * @return True if expanded
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
    default boolean isExpanded() throws AutomationException {
        final ExpandCollapse collapsePattern = requestAutomationPattern(ExpandCollapse.class);
        if (collapsePattern.isAvailable()) {
        	return collapsePattern.isExpanded();
        } 
        throw new PatternNotFoundException("Cannot collapse");
    }
}
