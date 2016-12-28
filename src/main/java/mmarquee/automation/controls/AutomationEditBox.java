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
import mmarquee.automation.pattern.Value;

/**
 * Created by Mark Humphreys on 26/01/2016.
 *
 * Wrapper around the edit box element
 */
public class AutomationEditBox extends AutomationBase {

    private Value valuePattern;

    /**
     * Gets the value of the control
     * @return The string value of the control
     * @throws AutomationException Something has gone wrong
     */
    public String getValue() throws AutomationException {
        return valuePattern.value();
    }

    /**
     * Sets the value of the edit box
     * @param value The value to set
     * @throws AutomationException Something has gone wrong
     */
    public void setValue(String value) throws AutomationException {
        this.valuePattern.setValue(value);
    }

    /**
     * Whether the element is read only
     * @return True if readonly, otherwise false.
     * @throws AutomationException Something has gone wrong
     */
    public boolean isReadOnly()throws AutomationException {
        return this.valuePattern.isReadOnly();
    }

    /**
     * Whether the element is a password
     * @return True if it's a password, otherwise false.
     * @throws AutomationException Automation error
     */
    public boolean isPassword() throws AutomationException {
        return this.element.currentIsPassword();
    }

    /**
     * Constructor for the AutomationEditBox
     * @param element The underlying element
     * @throws PatternNotFoundException Expected pattern not found
     * @throws AutomationException Automation error
     */
    public AutomationEditBox(AutomationElement element)
            throws PatternNotFoundException, AutomationException {
        super(element);
        this.valuePattern = this.getValuePattern();
    }

    /**
     * Constructor for the AutomationEditBox
     * @param element The underlying element
     * @param value The Value pattern
     */
    public AutomationEditBox(AutomationElement element, Value value) {
        super(element);
        this.valuePattern = value;
    }
}
