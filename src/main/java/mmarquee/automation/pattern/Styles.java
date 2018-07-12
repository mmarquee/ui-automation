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
import mmarquee.automation.uiautomation.IUIAutomationStylesPattern;
import mmarquee.automation.uiautomation.IUIAutomationStylesPatternConverter;

/**
 * Wrapper around the styles pattern.
 *
 * @author Mark Humphreys
 * Date 01/03/2016.
 *
 * This looks to be unused anywhere
 */
public class Styles extends BasePattern {

    /**
     * Constructor for the styles pattern.
     *
     * @param element The automation element for which the pattern is valid
     * @throws AutomationException If something goes wrong
     */
    public Styles(final AutomationElement element) throws AutomationException {
    	super(element);
        this.IID = IUIAutomationStylesPattern.IID;
        this.patternID = PatternID.Styles;
        this.availabilityPropertyID = PropertyID.IsStylesPatternAvailable;
    }

    /**
     * The raw pattern.
     */
    private IUIAutomationStylesPattern rawPattern;

    /**
     * Gets the raw pattern.
     * @return The raw pattern
     */
    public IUIAutomationStylesPattern getRawPattern() {
        return this.rawPattern;
    }

    /**
     * Sets the raw pattern.
     * @param pattern The raw pattern to set
     */
    public void setRawPattern(IUIAutomationStylesPattern pattern) {
        this.rawPattern = pattern;
    }

    /**
     * Gets the pattern.
     *
     * @return The pattern
     * @throws AutomationException Error is automation library
     */
    private IUIAutomationStylesPattern getPattern() throws AutomationException {
    	return getPattern(rawPattern, this::convertPointerToInterface);
    }

    /**
     * Gets the style by name.
     *
     * @return The style name.
     * @throws AutomationException Something has gone wrong
     */
    public String getStyleName() throws AutomationException {
        PointerByReference sr = new PointerByReference();

        final int res = this.getPattern().getCurrentStyleName(sr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return sr.getValue().getWideString(0);
    }

    /**
     * Gets the style id.
     *
     * @return The style id.
     * @throws AutomationException Something has gone wrong
     */
    public int getStyleId() throws AutomationException {
        IntByReference ipr = new IntByReference();

        final int res = this.getPattern().getCurrentStyleId(ipr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return ipr.getValue();
    }

    /**
     * Converts the raw pointer to the pattern.
     *
     * @param pUnknown The raw pointer
     * @return The converted interface
     */
    IUIAutomationStylesPattern convertPointerToInterface
            (final PointerByReference pUnknown) {
        return IUIAutomationStylesPatternConverter.pointerToInterface(pUnknown);
    }
}
