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

import mmarquee.automation.pattern.raw.IUIAutomationItemContainerPattern;
import mmarquee.automation.uiautomation.IUIAutomationElement;

/**
 * Created by inpwt on 25/02/2016.
 *
 * Wrapper for the itemcontainer pattern
 */
public class ItemContainer extends BasePattern {

    /**
     * Finds an item by property
     * @param pStartAfter Where to start in the tree of elements
     * @param propertyId The property id to find
     * @param value The value of the property
     * @return The item found
     */
    public IUIAutomationElement findItemByProperty (IUIAutomationElement pStartAfter, int propertyId, Object value) {
        return ((IUIAutomationItemContainerPattern)this.pattern).findItemByProperty(pStartAfter, propertyId, value);
    }
}
