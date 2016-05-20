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

import mmarquee.automation.eventhandlers.raw.IUIAutomationEventHandler;
import mmarquee.automation.uiautomation.IUIAutomationElement;

/**
 * Created by inpwt on 18/03/2016.
 *
 * Wrapper for the IUIAutomationEventHandler
 */
public class EventHandler {
    private IUIAutomationEventHandler handler;

    /**
     * Constructor for the event handler
     * @param sender The sender
     * @param eventId The event id to handle
     */
    public EventHandler(IUIAutomationElement sender,
                        int eventId) {
        this.handler.handleAutomationEvent(sender, eventId);
    }

    /**
     * Gets the raw event handler
     * @return IUIAutomationEventHandler The event handler
     */
    public IUIAutomationEventHandler getEventHandler() {
        return this.handler;
    }
}
