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
package mmarquee.automation.pattern;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.uiautomation.IUIAutomationGridItemPattern;
import mmarquee.automation.uiautomation.IUIAutomationGridItemPatternConverter;

/**
 * Wrapper for the GridItem pattern.
 *
 * @author Mark Humphreys
 * Date 27/01/2017.
 */
public class GridItem extends BasePattern {

    /**
     * Constructor for the pattern.
     *
     * @param element The automation element for which the pattern is valid
     * @throws AutomationException If something goes wrong
     */
    public GridItem(final AutomationElement element) throws AutomationException {
    	super(element);
        this.IID = IUIAutomationGridItemPattern.IID;
        this.patternID = PatternID.GridItem;
        this.availabilityPropertyID = PropertyID.IsGridItemPatternAvailable;
    }

    /**
     * The raw GridItem pattern.
     */
    IUIAutomationGridItemPattern rawPattern;

    /**
     * Gets the pattern.
     *
     * @return The grid pattern
     * @throws AutomationException Exception raised by library
     */
    private IUIAutomationGridItemPattern getPattern()
            throws AutomationException {
    	return getPattern(rawPattern, this::convertPointerToInterface);
    }

    /**
     * Gets the converted pointer.
     *
     * @param pUnknownA The raw pointer
     * @return The converted pointer
     */
    IUIAutomationGridItemPattern convertPointerToInterface(PointerByReference pUnknownA) {
        return IUIAutomationGridItemPatternConverter.pointerToInterface(pUnknownA);
    }

    /**
     * Gets the current column for the cell.
     *
     * @return The column
     * @throws AutomationException Automation returned an error
     */
    public int getColumn() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.getPattern().getCurrentColumn(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return ibr.getValue();
    }

    /**
     * Gets the current row for the cell.
     *
     * @return The row
     * @throws AutomationException Automation returned an error
     */
    public int getRow() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.getPattern().getCurrentRow(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return ibr.getValue();
    }
}
