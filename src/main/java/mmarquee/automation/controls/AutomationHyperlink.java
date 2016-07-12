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
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Created by inpwt on 03/02/2016.
 *
 * Wrapper for the Hyperlink element.
 */
public class AutomationHyperlink extends AutomationBase {
    private Invoke invokePattern;

    /**
     * Constructor for the AutomationHyperlink
     * @param element The underlying automation element
     */
    public AutomationHyperlink(AutomationElement element) {
        super(element);

        try {
            this.invokePattern = this.getInvokePattern();
        } catch (PatternNotFoundException ex) {
            // Smother, really?
        }
    }

    /**
     * Fires the click event associated with this element.
     **/
    public void click() {
        this.invokePattern.invoke();
    }
}