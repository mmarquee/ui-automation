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
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.pattern.*;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationEditBox extends AutomationBase {

    private Value valuePattern;

    /**
     * Gets the value of the control
     * @return
     */
    public String getValue() {
        return valuePattern.value();
    }

    /**
     * Sets the value of the edit box
     * @param value The value to set
     */
    public void setValue(String value) {
        this.valuePattern.setValue(value);
    }

    /**
     * Whether the element is read only
     * @return True if readonly, otherwise false.
     */
    public boolean isReadOnly() {
        return this.valuePattern.isReadOnly() == 1;
    }

    public AutomationEditBox(AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        try {
            this.valuePattern = this.getValuePattern();
        } catch (PatternNotFoundException ex) {
            // Handle this nicely somehow
        }
    }
}
