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
package mmarquee.automation.controls.ribbon;

import mmarquee.automation.controls.AutomationContainer;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.ControlType;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 02/03/2016.
 *
 * Specialist pane that represents the RibbonBar
 */
public class AutomationRibbonCommandBar extends AutomationContainer {
    public AutomationRibbonCommandBar(AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
    }

    /**
     * Get the RibbonWorkPane associated with the container
     * @return The AutomationRibbonWorkPane
     */
    public AutomationRibbonWorkPane getRibbonWorkPane() {
        return new AutomationRibbonWorkPane(this.getControlByControlType(0, ControlType.Pane, "UIRibbonWorkPane"), this.uiAuto);
    }
}
