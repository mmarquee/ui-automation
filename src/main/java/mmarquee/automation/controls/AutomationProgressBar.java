/*
 * Copyright 2016 inpwtepydjuf@gmail.com
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
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Range;

/**
 * Created by Mark Humphreys on 25/04/2016.
 *
 * Wrapper for the ProgressBar control.
 */
public class AutomationProgressBar extends AutomationBase {

    private Range rangePattern;

    /**
     * Construct the AutomationPanel
     * @param element The element
     * @throws AutomationException Error in automation library
     */
    public AutomationProgressBar(AutomationElement element)
            throws AutomationException {
        super(element);

        try {
            this.rangePattern = this.getRangePattern();
        } catch (PatternNotFoundException ex) {
            logger.warn("RangeValue pattern not found");
        }

        controlType = ControlType.ProgressBar;
    }

    /**
     * Gets the range value
     * @return The range value
     * @throws AutomationException Error in automation library
     */
    public double getRangeValue() throws AutomationException {
        return this.rangePattern.getValue();
    }

    /**
     * Sets the range value
     * @param value The value to set
     * @throws AutomationException Error in automation library
     */
    public void setRangeValue(double value) throws AutomationException {
        this.rangePattern.setValue(value);
    }
}
