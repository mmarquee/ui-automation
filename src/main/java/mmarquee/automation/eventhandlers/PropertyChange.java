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
package mmarquee.automation.eventhandlers;

import mmarquee.automation.eventhandlers.raw.IUIAutomationPropertyChangedEventHandler;
import mmarquee.automation.uiautomation.IUIAutomationElement;

/**
 * Created by inpwt on 18/03/2016.
 *
 * Wrapper for the IUIAutomationFocusChangedEventHandler
 */
public class PropertyChange {
    private IUIAutomationPropertyChangedEventHandler eventHandler;

    /**
     * Constructor for TextChange handler
     * @param sender The sender
     * @param propertyId The property id
     * @param newValue the new value
     */
    public PropertyChange(IUIAutomationElement sender, int propertyId, Object newValue) {
        this.eventHandler.handlePropertyChangedEvent(sender, propertyId, newValue);
    }
}



