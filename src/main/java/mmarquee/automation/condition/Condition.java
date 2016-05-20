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

import mmarquee.automation.UIAutomation;
import mmarquee.automation.condition.raw.IUIAutomationCondition;
import mmarquee.automation.uiautomation.IUIAutomation;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created by inpwt on 24/02/2016.
 *
 * Base class for conditions
 */
public abstract class Condition {
    protected UIAutomation automation = UIAutomation.getInstance();

    /**
     * Base 'abstract' method for Condition, needs to be implemented in the
     * subclasses, otherwise an exception is raised
     *
     * @return The raw condition
     * @throws NotImplementedException Not implemented
     */
    public IUIAutomationCondition getCondition() throws NotImplementedException {
        throw new NotImplementedException();
    }
}

