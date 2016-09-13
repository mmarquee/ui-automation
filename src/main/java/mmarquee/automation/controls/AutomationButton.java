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
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Created by Mark Humphreys on 02/02/2016.
 *
 * Wrapper around the Button element.
 */
public class AutomationButton extends AutomationBase {

    private Invoke invokePattern = null;

    /**
     * Constructor for the AutomationButton
     * @param element The underlying automation element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationButton(AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);

        this.invokePattern = this.getInvokePattern();
    }

    /**
     * <p>
     * Invokes the click event for this control
     * </p>
     * @throws AutomationException Error in the automation library
     */
    public void click() throws AutomationException{
        if (this.isInvokePatternAvailable()) {
            this.invokePattern.invoke();
        } else {
            logger.warn("Invoke pattern is not available");
        }
    }

    /**
     * <p>
     * Sets the focus to this control
     * </p>
     */
    public void focus() {
        this.element.setFocus();
    }
}
