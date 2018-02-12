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

import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.uiautomation.IUIAutomationRangeValuePattern;
import mmarquee.automation.uiautomation.IUIAutomationRangeValuePatternConverter;

/**
 * Wrapper for the range valuepattern.
 *
 * @author Mark Humphreys
 * Date 01/03/2016.
 */
public class Range extends BasePattern {

    /**
     * Constructor for the range value pattern
     * @throws AutomationException
     */
    public Range(final AutomationElement element) throws AutomationException {
    	super(element);
        this.IID = IUIAutomationRangeValuePattern.IID;
        this.patternID = PatternID.RangeValue;
        this.availabilityPropertyID = PropertyID.IsRangeValuePatternAvailable;
    }

    IUIAutomationRangeValuePattern rawPattern;

    public IUIAutomationRangeValuePattern convertPointerToInterface(PointerByReference pUnknownA) {
        return IUIAutomationRangeValuePatternConverter.pointerToInterface(pUnknownA);
    }

    private IUIAutomationRangeValuePattern getPattern() throws AutomationException {
    	return getPattern(rawPattern, this::convertPointerToInterface);
    }

    /**
     * Sets the value.
     * @param value The value to set
     * @throws AutomationException Something has gone wrong
     */
    public void setValue(final double value) throws AutomationException {
        final int res = this.getPattern().setValue(value);
        if (res != 0) {
            throw new AutomationException(res);
        }
    }

    /**
     * Gets the value
     * @return The value
     * @throws AutomationException Something has gone wrong
     */
    public double getValue() throws AutomationException {
        DoubleByReference dbr = new DoubleByReference();

        final int res = this.getPattern().getValue(dbr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return dbr.getValue();
    }
}
