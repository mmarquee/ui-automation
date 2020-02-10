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
 * Specialist pane that represents the RibbonBar.
 *
 * @author Mark Humphreys
 * Date 02/03/2016.
 */
public final class RibbonBar extends Panel {

    /**
     * Control name of the panel type.
     */
	public final static String CLASS_NAME = "UIRibbonCommandBarDock";
	
    /**
     * Construct the RibbonBar.
     * @param builder The builder
     * @throws AutomationException Something is wrong in automation.
     */
    public RibbonBar(final ElementBuilder builder)
            throws AutomationException {
        super(builder);
        assertClassName(CLASS_NAME);
    }

    /**
     * Get the RibbonCommandBar associated with this container.
     * @return The RibbonBar.
     * @throws AutomationException Automation issue.
     */
    public RibbonCommandBar getRibbonCommandBar()
            throws AutomationException {
        return new RibbonCommandBar(new ElementBuilder(this.getElementByControlType(0,
                ControlType.Pane, RibbonCommandBar.CLASS_NAME)));
    }
}
