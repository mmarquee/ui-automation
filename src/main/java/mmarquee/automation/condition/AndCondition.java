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

import mmarquee.automation.condition.raw.IUIAutomationCondition;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 24/02/2016.
 *
 * Wrapper for the And Condition.
 */
public class AndCondition extends Condition {

    private List<Condition> conditions;

    /**
     * Constructor for Condition
     */
    public AndCondition () {
        List<Condition> conditions = new ArrayList<Condition>();
    }

    /**
     * Constructor for Condition
     * @param firstCondition First condition
     * @param secondCondition Second condition
     */
    public AndCondition (Condition firstCondition, Condition secondCondition) {
        this.conditions = new ArrayList<Condition>();
        this.add(firstCondition);
        this.add(secondCondition);
    }

    /**
     * Add a condition
     * @param condition The condition to add
     */
    private void add(Condition condition) {
        this.conditions.add(condition);
    }

    /**
     * Gets the raw condition
     * @return the underlying IUIAutomationCondition
     */
    public IUIAutomationCondition getCondition () {
        return this.automation.CreateAndCondition(
                this.conditions.get(0).getCondition(),
                this.conditions.get(1).getCondition());
    }
}
