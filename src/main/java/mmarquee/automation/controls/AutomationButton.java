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

/**
 * Created by inpwt on 02/02/2016.
 *
 * Wrapper around the Button element.
 */
public class AutomationButton extends AutomationBase {

    private Invoke invokePattern = null;

    /**
     * Constructor for the AutomationButton
     * @param element The underlying automation element
     */
    public AutomationButton(AutomationElement element) {
        super (element);

        try {
            this.invokePattern = this.getInvokePattern();
        } catch (PatternNotFoundException ex) {
            // Handle this nicely somehow
        }
    }

    /**
     * <p>
     * Invokes the click event for this control
     * </p>
     */
    public void click() {
        if (this.isInvokePatternAvailable()) {
            this.invokePattern.invoke();
        } else {
            logger.info("Invoke pattern is not available");
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
