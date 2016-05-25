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

import com4j.Com4jObject;
import com4j.ComThread;
import com4j.EventCookie;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.EventID;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.eventhandlers.raw.IUIAutomationEventHandler;
import mmarquee.automation.uiautomation.IUIAutomationElement;

import java.util.logging.Logger;

/**
 * Created by inpwt on 18/03/2016.
 *
 * Wrapper for the IUIAutomationEventHandler
 */
public class EventHandler /* implements IUIAutomationEventHandler */ {

    static final Logger logger = Logger.getLogger(EventHandler.class.getName());

    private String name;

    public void handleAutomationEvent(
            IUIAutomationElement sender,
            int eventId) {
        logger.info("handleAutomationEvent fired");
    }
/*
    public <T extends Com4jObject> boolean is(Class<T> var1) {
        return true;
    }

    public <T extends Com4jObject> T queryInterface(Class<T> var1) {

    }

    public <T> EventCookie advise(Class<T> var1, T var2) {

    }
*/
    public void setName(String var1) {
        this.name = var1;
    }

    public String toString() {
        return this.name + "EventHandler"; //?
    }
/*
    public void dispose() {

    }

    public ComThread getComThread() {

    }

    public long getPointer() {

    }

    public long getIUnknownPointer() {

    }

    public int getPtr() {

    }
*/
}
