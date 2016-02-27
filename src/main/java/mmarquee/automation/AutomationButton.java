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
package mmarquee.automation;

import mmarquee.automation.pattern.InvokePattern;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 02/02/2016.
 */
public class AutomationButton extends AutomationBase {

    private InvokePattern invokePattern = null;

    /**
     * Constructor for the AutomationButton
     * @param element The underlying automation element
     * @param uiAuto The automation library
     */
    public AutomationButton(IUIAutomationElement element, IUIAutomation uiAuto) {
        super (element, uiAuto);

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
        this.invokePattern.invoke();
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
