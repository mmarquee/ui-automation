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
package mmarquee.automation;

import mmarquee.automation.condition.Condition;
import mmarquee.automation.pattern.*;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 06/03/2016.
 */
public class AutomationElement {
    protected IUIAutomationElement element;

    public AutomationElement(IUIAutomationElement element) {
        this.element = element;
    }
/*
    public Object getCurrentPropertyValue(int propertyId) {
        return this.element.getCurrentPropertyValue(propertyId);
    }
*/
    public int currentControlType() {
        return this.element.currentControlType();
    }

    public String currentClassName() {
        return this.element.currentClassName();
    }

    public String currentName() {
        return this.element.currentName();
    }

    public void setName(String name) {
        this.element.setName(name);
    }
/*
    public AutomationElement findFirst(TreeScope scope, Condition condition) {
        IUIAutomationElement elem = this.element.findFirst(scope, condition.getCondition());
        return new AutomationElement(elem);
    }

    public com4j.Com4jObject getCurrentPattern(int patternId) {
        return this.element.getCurrentPattern(patternId);
    }
*/
    public void setFocus() {
        this.element.setFocus();
    }
/*
    public IUIAutomationElementArray findAll(TreeScope scope, IUIAutomationCondition condition) {
        return this.element.findAll(scope, condition);
    }
    */
}