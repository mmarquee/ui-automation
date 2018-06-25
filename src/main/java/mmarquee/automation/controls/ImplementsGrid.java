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
import mmarquee.automation.pattern.Grid;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Methods to interact with the grid pattern.
 */
public interface ImplementsGrid extends Automatable, CanRequestBasePattern {
	
    /**
     * Gets the item associated with the given cell.
     *
     * @param row X Offset
     * @param column Y Offset
     * @return The GridItem at the given cell position
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    default AutomationElement getGridItem(final int row, final int column)
            throws PatternNotFoundException, AutomationException  {
    	
    	final Grid gridPattern = requestAutomationPattern(Grid.class);
        if (gridPattern.isAvailable()) {
        	return gridPattern.getItem(row, column);
        }
		throw new PatternNotFoundException("Cannot get item");
    }
    

    /**
     * Gets the row count of the gridPattern.
     *
     * @return The row count
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    default int rowCount() throws AutomationException, PatternNotFoundException {
    	
    	final Grid gridPattern = requestAutomationPattern(Grid.class);
        if (gridPattern.isAvailable()) {
        	return gridPattern.rowCount();
        }
		throw new PatternNotFoundException("Cannot get row count");
    }

    /**
     * Gets the column count of the gridPattern.
     *
     * @return The column count
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    default int columnCount() throws AutomationException, PatternNotFoundException {
    	final Grid gridPattern = requestAutomationPattern(Grid.class);
        if (gridPattern.isAvailable()) {
        	return gridPattern.columnCount();
        }
		throw new PatternNotFoundException("Cannot get column count");
    }
    
}
