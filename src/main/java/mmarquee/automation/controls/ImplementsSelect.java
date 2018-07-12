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
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.SelectionItem;

/**
 * The Control supports the methods of the SelectionItemPattern.
 *
 * @author Mark Humphreys
 * Date 21/09/2016.
 */
public interface ImplementsSelect extends Automatable, CanRequestBasePattern {
    /**
     * Selects the element.
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
    default void select() throws AutomationException, PatternNotFoundException {
    	final SelectionItem selectionItemPattern = requestAutomationPattern(SelectionItem.class);
 		if (selectionItemPattern.isAvailable()) {
 			selectionItemPattern.select();
 			return;
 		}
 		throw new PatternNotFoundException("Cannot select item");
    }

    /**
     * Whether the element is selected.
     * @return True if selected
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
    default boolean isSelected() throws AutomationException, PatternNotFoundException {
    	final SelectionItem selectionItemPattern = requestAutomationPattern(SelectionItem.class);
 		if (selectionItemPattern.isAvailable()) {
 			return selectionItemPattern.isSelected();
 		}
 		throw new PatternNotFoundException("Cannot query selection state");
    }

    /**
     * Adds to the selection.
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
    default void addToSelection() throws AutomationException, PatternNotFoundException {
    	final SelectionItem selectionItemPattern = requestAutomationPattern(SelectionItem.class);
 		if (selectionItemPattern.isAvailable()) {
 			selectionItemPattern.addToSelection();
 			return;
 		}
 		throw new PatternNotFoundException("Cannot extend selection");
    }

    /**
     * Removes from the selection.
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
    default void removeFromSelection() throws AutomationException, PatternNotFoundException {
    	final SelectionItem selectionItemPattern = requestAutomationPattern(SelectionItem.class);
 		if (selectionItemPattern.isAvailable()) {
 			selectionItemPattern.removeFromSelection();
 			return;
 		}
 		throw new PatternNotFoundException("Cannot reduce selection");
    }


    /**
     * Gets the selection container.
     * @return  The selection container
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
    default AutomationElement getSelectionContainer() throws AutomationException, PatternNotFoundException {
    	final SelectionItem selectionItemPattern = requestAutomationPattern(SelectionItem.class);
 		if (selectionItemPattern.isAvailable()) {
 			return selectionItemPattern.getSelectionContainer();
 		}
 		throw new PatternNotFoundException("Cannot get the selection container");
    }
}
