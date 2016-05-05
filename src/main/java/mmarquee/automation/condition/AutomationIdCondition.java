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
import mmarquee.automation.condition.raw.IUIAutomationCondition;
import mmarquee.automation.uiautomation.IUIAutomation;

/**
 * Created by inpwt on 26/04/2016.
 *
 * Wrapper for the condition that is used to check automation ID
 */
public class AutomationIdCondition  extends PropertyCondition {
    private String name;

    /**
     * Constructor, for an empty condition.
     *
     * @param automation the automation library
     */
    public AutomationIdCondition (IUIAutomation automation) {
        super(automation);
        this.property = PropertyID.AutomationId.getValue();
    }

    /**
     * Constructor, with supplied automation ID.
     *
     * @param automation The automation library
     * @param automationId The automation ID
     */
    public AutomationIdCondition (IUIAutomation automation, String automationId) {
        super(automation);
        this.name = automationId;
        this.property = PropertyID.AutomationId.getValue();
    }

    /**
     * Gets the raw property
     * @return The underlying, raw condition
     */
    public IUIAutomationCondition getCondition () {
        return automation.createPropertyCondition(this.property, this.name);
    }
}
