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
package mmarquee.automation.controls.menu;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.IUIAutomation;

/**
 * Created by inpwt on 10/02/2016.
 */
public class AutomationMenuItem extends AutomationBase {
    private Invoke invokePattern;

    /**
     * Construct the AutomationMenuItem
     * @param element The element
     * @param automation The automation library
     */
    public AutomationMenuItem(AutomationElement element, IUIAutomation automation) {
        super(element, automation);

        try {
            this.invokePattern = this.getInvokePattern();
        } catch (PatternNotFoundException ex) {
            // Handle this nicely somehow
        }
    }

    /**
     * Invoke the click pattern for the menu item
     */
    public void click() {
        if (this.invokePattern != null) {
            this.invokePattern.invoke();
        }
    }
}
