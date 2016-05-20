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
package mmarquee.automation.condition;

import mmarquee.automation.PropertyID;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.condition.raw.IUIAutomationCondition;
import mmarquee.automation.uiautomation.IUIAutomation;

/**
 * Created by inpwt on 24/02/2016.
 */
public class ControlIdCondition extends PropertyCondition {
    private int id;

    /**
     * Constructor for the condition
     * @param automation the automation library
     */
    public ControlIdCondition (UIAutomation automation) {
        super(automation);
        this.property = PropertyID.ControlType.getValue();
    }

    /**
     * Constructor for the condition
     * @param automation the automation library
     * @param id The property id
     */
    public ControlIdCondition (UIAutomation automation, int id) {
        super(automation);
        this.id = id;
       this.property = PropertyID.ControlType.getValue();
    }

    /**
     * Gets the underlying condition
     * @return The underlying condition
     */
    public IUIAutomationCondition getCondition () {
        return automation.CreatePropertyCondition(this.property, this.id);
    }
}
