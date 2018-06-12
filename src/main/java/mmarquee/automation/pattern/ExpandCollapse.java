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
import mmarquee.automation.uiautomation.IUIAutomationExpandCollapsePattern;
import mmarquee.automation.uiautomation.IUIAutomationExpandCollapsePatternConverter;

/**
 * Wrapper for  the ExpandCollapse pattern.
 *
 * @author Mark Humphreys
 * Date 25/02/2016.
 */
public class ExpandCollapse extends BasePattern {

    /**
     * The raw pointer.
     */
    IUIAutomationExpandCollapsePattern rawPattern;

    /**
     * Constructor for the pattern.
     * @param element The automation element for which the pattern is valid
     * @throws AutomationException If something goes wrong
     */
    public ExpandCollapse(final AutomationElement element) throws AutomationException {
    	super(element);
        this.IID = IUIAutomationExpandCollapsePattern.IID;
        this.patternID = PatternID.ExpandCollapse;
        this.availabilityPropertyID = PropertyID.IsExpandCollapsePatternAvailable;
    }

    /**
     * Gets the pattern.
     * @return The pattern
     * @throws AutomationException Something went wrong getting the pattern
     */
    private IUIAutomationExpandCollapsePattern getPattern()
            throws AutomationException {
    	return getPattern(rawPattern, this::convertPointerToInterface);
    }

    /**
     * Expands the control.
     * @throws AutomationException Something has gone wrong
     */
    public void expand() throws AutomationException {
        final int res = this.getPattern().expand();
        if (res != 0) {
            throw new AutomationException(res);
        }
    }

    /**
     * Collapses the control.
     * @throws AutomationException Something has gone wrong
     */
    public void collapse()throws AutomationException  {
        final int res = this.getPattern().collapse();
        if (res != 0) {
            throw new AutomationException(res);
        }
    }

    /**
     * Determines whether the control is expanded.
     * @return Is the control expanded
     * @throws AutomationException Something has gone wrong
     */
    public boolean isExpanded() throws AutomationException {
        IntByReference ibr = new IntByReference();

        final int res = this.getPattern().getCurrentExpandCollapseState(ibr);
        if (res != 0) {
            throw new AutomationException(res);
        }

        return ibr.getValue() == 1;
    }

    /**
     * Gets the interface from the raw PointerByReference.
     * @param pUnknown The Unknown pointer
     * @return The pattern
     */
    IUIAutomationExpandCollapsePattern convertPointerToInterface(
            final PointerByReference pUnknown) {
        return IUIAutomationExpandCollapsePatternConverter.pointerToInterface(pUnknown);
    }
}
