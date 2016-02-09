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

import mmarquee.automation.uiautomation.*;
import com.sun.jna.*;

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationApplication extends AutomationBase {
    private Process process;

    public void waitForInputIdle() {
        //WaitForInputIdle(this.rocess, timeout);
        // Need to solve this !!!

     //   com.sun.jna.win32;

        //win32.User32.INSTANCE.WaitForInputIdle();

        //com.sun.jna.
    }

    public AutomationWindow getWindow(String title) {

        IUIAutomationElement foundElement = null;

        IUIAutomationElementArray collection = this.findAll();

        int length = collection.length();

        for(int count=0; count < length; count++){
            IUIAutomationElement element = collection.getElement(count);
            String name = element.currentName();
            if (name.equals(title)){
                foundElement = element;
            }
        }

        return new AutomationWindow(foundElement, this.uiAuto);
    }

    public AutomationApplication (IUIAutomationElement element, IUIAutomation uiAuto, Process process) {
        super(element, uiAuto);
        this.process = process;
    }
}
