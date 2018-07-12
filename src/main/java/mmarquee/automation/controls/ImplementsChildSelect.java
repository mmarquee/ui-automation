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

import java.util.List;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Selection;

/**
 * The Control supports the methods of the SelectionPattern.
 * 
 * @author Mark Humphreys
 * Date 21/09/2016.
 */
public interface ImplementsChildSelect extends Automatable, CanRequestBasePattern {

    /**
     * Is multiple selection allowed.
     *
     * @return True is multiple selection is allowed
     * @throws AutomationException Error thrown from automation library
     * @throws PatternNotFoundException Failed to find pattern
     */
    default boolean canSelectMultiple() throws AutomationException, PatternNotFoundException {
		final Selection selectionPattern = requestAutomationPattern(Selection.class);
		if (selectionPattern.isAvailable()) {
			return selectionPattern.canSelectMultiple();
		}
		throw new PatternNotFoundException("Cannot query multi select");
    }
    
    /**
     * Gets the selection from the data grid.
     *
     * @return The list of selected elements.
     * @throws AutomationException An automation error has occurred.
     * @throws PatternNotFoundException Pattern was not found.
     */
    default List<AutomationElement> getSelection()
            throws AutomationException, PatternNotFoundException {
        final Selection selectionPattern = requestAutomationPattern(Selection.class);
		if (selectionPattern.isAvailable()) {
			return selectionPattern.getSelection();
		}
		throw new PatternNotFoundException("Cannot query selection");
    }

    /**
     * Gets the selection from the data grid.
     *
     * @return The list of selected elements.
     * @throws AutomationException An automation error has occurred.
     * @throws PatternNotFoundException Pattern was not found.
     */
    default List<AutomationElement> getCurrentSelection()
            throws AutomationException, PatternNotFoundException {
        final Selection selectionPattern = requestAutomationPattern(Selection.class);
		if (selectionPattern.isAvailable()) {
			return selectionPattern.getCurrentSelection();
		}
		throw new PatternNotFoundException("Cannot query current selection");
    }
}
