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
import mmarquee.automation.ControlType;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Range;

/**
 * @author Mark Humphreys
 * Date 25/04/2016.
 *
 * Wrapper for the ProgressBar control.
 */
public class AutomationProgressBar extends AutomationBase {

    /**
     * The range pattern.
     */
    private Range rangePattern;

    /**
     * Construct the AutomationPanel.
     *
     * @param element The element.
     * @throws AutomationException Error in automation library.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public AutomationProgressBar(final AutomationElement element)
            throws AutomationException, PatternNotFoundException {
        super(element);

//        this.rangePattern = this.getRangePattern();
    }

    /**
     * Construct the AutomationPanel.
     *
     * @param element The element.
     * @param range Range pattern.
     * @param instance Automation instance.
     */
    AutomationProgressBar(final AutomationElement element,
                          final Range range,
                          final UIAutomation instance) {
        super(element, instance);

        this.rangePattern = range;
    }

    /**
     * Gets the range value.
     *
     * @return The range value.
     * @throws AutomationException Error in automation library.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public double getRangeValue()
            throws AutomationException, PatternNotFoundException {
        if (this.rangePattern == null) {
            this.rangePattern = this.getRangePattern();
        }

        return this.rangePattern.getValue();
    }

    /**
     * Sets the range value.
     *
     * @param value The value to set.
     * @throws AutomationException Error in automation library.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public void setRangeValue(final double value)
            throws AutomationException, PatternNotFoundException {
        if (this.rangePattern == null) {
            this.rangePattern = this.getRangePattern();
        }

        this.rangePattern.setValue(value);
    }
}
