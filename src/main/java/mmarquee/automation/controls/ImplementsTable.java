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
import mmarquee.uiautomation.RowOrColumnMajor;

import java.util.List;

/**
 * The Control supports the methods of the Table pattern.
 * 
 * @author Mark Humphreys
 * Date 21/09/2016.
 */
public interface ImplementsTable extends Automatable {
	
	/**
     * Gets the list of the column headers.
     *
     * @return List of GridItems
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    List<DataGridCell> getCurrentColumnHeaders ()
            throws PatternNotFoundException, AutomationException;
    
    /**
     * Gets the row headers for the grid.
     * @return The list of column header
     * @throws AutomationException Something has gone wrong
     */
    List<DataGridCell> getCurrentRowHeaders() throws AutomationException;
    
    /**
     * Returns whether the grid has column or row headers.
     *
     * @return RowOrColumnMajor Row or column
     * @throws AutomationException Error thrown from automation library
     * @throws PatternNotFoundException Failed to find pattern
     */
    RowOrColumnMajor getRowOrColumnMajor()
            throws AutomationException, PatternNotFoundException;
}
