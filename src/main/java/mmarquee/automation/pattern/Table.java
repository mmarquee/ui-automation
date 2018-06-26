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

import java.util.List;

import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.uiautomation.IUIAutomationTablePattern;
import mmarquee.automation.uiautomation.IUIAutomationTablePatternConverter;
import mmarquee.automation.uiautomation.RowOrColumnMajor;

/**
 * Wrapper for the table pattern.
 *
 * @author Mark Humphreys
 * Date 25/02/2016.
 */
public class Table extends BasePattern {

    /**
     * Constructor for the value pattern.
     * @param element The automation element for which the pattern is valid
     * @throws AutomationException If something goes wrong
     */
    public Table(final AutomationElement element) throws AutomationException {
    	super(element);
        this.IID = IUIAutomationTablePattern.IID;
        this.patternID = PatternID.Table;
        this.availabilityPropertyID = PropertyID.IsTableItemPatternAvailable;
    }

    /**
     * The raw pattern.
     */
    IUIAutomationTablePattern rawPattern;

    /**
     * Gets the pattern.
     *
     * @return The pattern
     * @throws AutomationException Error in automation library
     */
    private IUIAutomationTablePattern getPattern() throws AutomationException {
    	return getPattern(rawPattern, this::convertPointerToInterface);
    }

    /**
     * Gets the column headers for the grid.
     *
     * @return The list of column header
     * @throws AutomationException Something has gone wrong
     */
    public List<AutomationElement> getCurrentColumnHeaders() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.getPattern().getCurrentColumnHeaders(pbr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return collectionToList(getAutomationElementArrayFromReference(pbr));
    }

    /**
     * Gets the row or column major for the table.
     *
     * @return RowOrColumnMajor value of the table element
     * @throws AutomationException Error in automation library
     */
    public RowOrColumnMajor getRowOrColumnMajor() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.getPattern().getCurrentRowOrColumnMajor(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return RowOrColumnMajor.fromInt(ibr.getValue());
    }

    /**
     * Gets the row headers for the grid.
     *
     * @return The list of column header
     * @throws AutomationException Something has gone wrong
     */
    public List<AutomationElement> getCurrentRowHeaders() throws AutomationException {
        PointerByReference pbr = new PointerByReference();

        final int res = this.getPattern().getCurrentRowHeaders(pbr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return collectionToList(getAutomationElementArrayFromReference(pbr));
    }

    /**
     * Converts the unknown value to a IUIAutomationTablePattern.
     *
     * @param pUnknownA The Unknown pointer
     * @return The pattern
     */
    IUIAutomationTablePattern convertPointerToInterface(
            final PointerByReference pUnknownA) {
        return IUIAutomationTablePatternConverter.pointerToInterface(pUnknownA);
    }
}
