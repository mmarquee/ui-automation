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

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationEditBox extends AutomationBase {
    public String text() {
        //return this.name();
        com4j.Com4jObject unknown = this.element.getCurrentPattern(PatternID.ValuePatternId);

        String value = "";

        if (unknown != null) {
            IUIAutomationValuePattern pattern =
                    unknown.queryInterface(IUIAutomationValuePattern.class);

            if (pattern != null) {
                value = pattern.currentValue();
            }
        }

        return value;
    }

    public AutomationEditBox(IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
    }
}
