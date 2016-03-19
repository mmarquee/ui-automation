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
import mmarquee.automation.condition.raw.IUIAutomationCondition;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 06/03/2016.
 */
public class AutomationElement {
    // public for now
    public IUIAutomationElement element;

    /**
     * Constructor of AutomationElement
     * @param element The element
     */
    public AutomationElement(IUIAutomationElement element) {
        this.element = element;
    }

    /**
     * Gets the property associated with the passed in id
     * @param propertyId The property ID to get
     * @return The property ID
     */
    public Object getCurrentPropertyValue(int propertyId) {
        return this.element.getCurrentPropertyValue(propertyId);
    }

    /**
     * Gets the current control type
     * @return The current control type
     */
    public int currentControlType() {
        return this.element.currentControlType();
    }

    /**
     * Gets the current class name of the element
     * @return The current class name
     */
    public String currentClassName() {
        return this.element.currentClassName();
    }

    /**
     * Gets the current name of the control
     * @return The name of the element
     */
    public String currentName() {
        return this.element.currentName();
    }

    /**
     * Sets the name of the element
     * @param name The name to use
     */
    public void setName(String name) {
        this.element.setName(name);
    }

    /**
     * Finds the first element that matches the condition
     * @param scope Tree scope
     * @param condition The condition
     * @return The first matching element
     * @throws ElementNotFoundException
     */
    public AutomationElement findFirst(TreeScope scope, Condition condition) throws ElementNotFoundException {
        IUIAutomationElement elem = this.element.findFirst(scope, condition.getCondition());

        if (elem != null) {
            return new AutomationElement(elem);
        } else {
            throw new ElementNotFoundException();
        }
    }

    /**
     * Finds the first element that matches the raw condition
     * @param scope Tree scope
     * @param condition The raw condition
     * @return The first matching element
     * @throws ElementNotFoundException
     */
    public AutomationElement findFirstFromRawCondition(TreeScope scope, IUIAutomationCondition condition) throws ElementNotFoundException {
        IUIAutomationElement elem = this.element.findFirst(scope, condition);

        if (elem != null) {
            return new AutomationElement(elem);
        } else {
            throw new ElementNotFoundException();
        }
    }

    /**
     * Get the current pattern that matches the patternId
     * @param patternId What pattern to get
     * @return The pattern
     */
    public com4j.Com4jObject getCurrentPattern(int patternId) {
        return this.element.getCurrentPattern(patternId);
    }

    /**
     * Sets focus to the element
     */
    public void setFocus() {
        this.element.setFocus();
    }

    /**
     * Gets all of the elements that match the condition and scope
     * @param scope The scope in the element tree
     * @param condition The condition
     * @return List of matching elements
     */
    public List<AutomationElement> findAll(TreeScope scope, IUIAutomationCondition condition) {
        IUIAutomationElementArray collection = this.element.findAll(scope, condition);

        List<AutomationElement> items = new ArrayList<AutomationElement>();

        for (int count = 0; count < collection.length(); count++) {
            items.add(new AutomationElement(collection.getElement(count)));
        }

        return items;
    }
}