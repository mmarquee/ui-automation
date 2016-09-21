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
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Created by Mark Humphreys on 03/02/2016.
 *
 * Wrapper for the Hyperlink element.
 */
public class AutomationHyperlink extends AutomationBase implements Clickable {
    private Invoke invokePattern;

    /**
     * Constructor for the AutomationHyperlink
     * @param element The underlying automation element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationHyperlink(AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);
        this.invokePattern = this.getInvokePattern();
        controlType = ControlType.Hyperlink;
    }

    /**
     * Fires the click event associated with this element.
     * @throws AutomationException Something has gone wrong
     **/
    public void click() throws AutomationException {
        this.invokePattern.invoke();
    }
}