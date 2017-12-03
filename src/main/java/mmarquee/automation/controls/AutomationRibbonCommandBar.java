/*
 * Copyright 2016-17 inpwtepydjuf@gmail.com
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
package mmarquee.automation.controls;

import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;

/**
 * Specialist pane that represents the RibbonCommandBar.
 *
 * @author Mark Humphreys
 * Date 02/03/2016.
 */
public final class AutomationRibbonCommandBar extends AutomationContainer {

    /**
     * The class name for this panel.
     */
	public final static String CLASS_NAME = "UIRibbonCommandBar";
	
    /**
     * Construct the AutomationRibbonCommandBar.
     *
     * @param builder The builder.
     * @throws AutomationException Something is wrong in automation
     */
    public AutomationRibbonCommandBar(final ElementBuilder builder)
            throws AutomationException {
        super(builder);
        assertClassName(CLASS_NAME);
    }

    /**
     * Get the RibbonWorkPane associated with the container.
     * @return The AutomationRibbonWorkPane.
     * @throws AutomationException Something is wrong in automation
     */
    public AutomationRibbonWorkPane getRibbonWorkPane()
            throws AutomationException {
        return new AutomationRibbonWorkPane(new ElementBuilder(this.getElementByControlType(0,
                ControlType.Pane, AutomationRibbonWorkPane.CLASS_NAME)));
    }
}
