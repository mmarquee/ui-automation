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
import mmarquee.automation.pattern.GridItem;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Methods to interact with the grid item pattern.
 */
public interface ImplementsGridItem extends Automatable, CanRequestBasePattern {
	

    /**
     * Gets the current row for this element.
     * @return The row.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    default int getRow() throws AutomationException, PatternNotFoundException {
    	final GridItem gridItemPattern = requestAutomationPattern(GridItem.class);
        if (gridItemPattern.isAvailable()) {
        	return gridItemPattern.getRow();
        }
		throw new PatternNotFoundException("Cannot get row");
    }

    /**
     * Gets the current column for this element.
     * @return The column.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Pattern not found.
     */
    default int getColumn() throws AutomationException, PatternNotFoundException {

    	final GridItem gridItemPattern = requestAutomationPattern(GridItem.class);
        if (gridItemPattern.isAvailable()) {
        	return gridItemPattern.getColumn();
        }
		throw new PatternNotFoundException("Cannot get column");
    }
}
