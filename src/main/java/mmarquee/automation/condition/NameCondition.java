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

/**
 * Created by inpwt on 24/02/2016.
 *
 * Wrapper for the Property condition, for name.
 */
public class NameCondition  extends PropertyCondition {
    private String name;

    public NameCondition () {
        this.property = PropertyID.Name.getValue();
    }

    /**
     * Constructor for the name condition
     * @param name The name to check
     */
    public NameCondition (String name) {
        this.name = name;
        this.property = PropertyID.Name.getValue();
    }

    /**
     * Gets the raw property
     * @return The underlying, raw condition
     */
    public IUIAutomationCondition getCondition () {
        return this.automation.CreatePropertyCondition(this.property, this.name);
    }
}
