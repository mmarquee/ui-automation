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
import mmarquee.automation.pattern.*;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 16/02/2016.
 *
 * Implements IGridProvider, IScrollProvider, ITableProvider, IValueProvider
 */
public class AutomationCalendar extends AutomationBase {
    private Value valuePattern;

    /**
     * Constructor for the AutomationCalendar.
     * @param element The underlying automation element
     * @param uiAuto The IUIAutomation associated with this session
     */
    public AutomationCalendar(AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
        try {
            this.valuePattern = this.getValuePattern();
        } catch (PatternNotFoundException ex) {
            // Handle this nicely somehow
        }
    }

    /**
     * Gets the current value of the control
     * @return The current value
     */
    public String getValue() {
        return this.valuePattern.value();
    }
}