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
package mmarquee.automation.pattern;

import com4j.Com4jObject;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 29/02/2016.
 *
 * Base for the pattern wrappers
 */
public abstract class BasePattern implements Pattern {

    /**
     * The underlying automation pattern
     */
    protected Com4jObject pattern;

    /**
     * Constructs a Pattern
     */
    public BasePattern () {
        this.pattern = null;
    }

    /**
     * Turns a collection (array) of automation elements, it to a collection.
     *
     * @param collection The ElementArray.
     * @return The List
     */
    List<AutomationElement> collectionToList(IUIAutomationElementArray collection) {

        List<AutomationElement> list = new ArrayList<AutomationElement>();

        for(int count = 0; count < collection.length(); count++) {
            list.add(new AutomationElement(collection.getElement(count)));
        }

        return list;
    }

    /**
     * Sets the pattern
     * @param pattern The pattern to set
     */
    public void setPattern(Com4jObject pattern) {
        this.pattern =  pattern;
    }

    /**
     * Is this pattern available?
     * @return True if available.
     */
    public boolean isAvailable () {
        return (pattern == null);
    }
}
