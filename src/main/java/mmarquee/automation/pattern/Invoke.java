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

import com.sun.jna.ptr.PointerByReference;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;
import mmarquee.automation.uiautomation.IUIAutomationInvokePattern;
import mmarquee.automation.uiautomation.IUIAutomationInvokePatternConverter;

/**
 * Wrapper for the Invoke pattern.
 *
 * @author Mark Humphreys
 * Date 25/02/2016.
 */
public class Invoke extends BasePattern {

    /**
     * The raw pattern.
     */
    IUIAutomationInvokePattern rawPattern;

    /**
     * Constructor for the pattern.
     *
     * @param element The automation element for which the pattern is valid
     * @throws AutomationException If something goes wrong
     */
    public Invoke(final AutomationElement element) throws AutomationException {
        super(element);
        this.IID = IUIAutomationInvokePattern.IID;
        this.patternID = PatternID.Invoke;
        this.availabilityPropertyID = PropertyID.IsInvokePatternAvailable;
    }

    /**
     * Gets the pattern.
     *
     * @return The pattern
     * @throws AutomationException Something went wrong getting the pattern
     */
    private IUIAutomationInvokePattern getPattern() throws AutomationException {
    	return getPattern(rawPattern, this::convertPointerToInterface);
    }

    /**
     * Invokes the pattern on the control.
     *
     * @throws AutomationException Something went wrong getting the pattern
     */
    public void invoke() throws AutomationException {
        final int res = this.getPattern().invoke();
        if (res != 0) {
            throw new AutomationException(res);
        }
    }

    /**
     * Gets the converted pointer.
     *
     * @param pUnknown The raw pointer
     * @return The converted pointer
     */
    IUIAutomationInvokePattern convertPointerToInterface(PointerByReference pUnknown) {
        return IUIAutomationInvokePatternConverter.pointerToInterface(pUnknown);
    }
}
