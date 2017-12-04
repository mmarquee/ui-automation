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
 * Specialist pane that represents the RibbonWorkPane of the MS Ribbon control.
 *
 * @author Mark Humphreys
 * Date 02/03/2016.
 *
 */
public final class AutomationRibbonWorkPane extends AutomationContainer {

    /**
     * The class name for this panel type.
     */
	public final static String CLASS_NAME = "UIRibbonWorkPane";

    /**
     * Construct the AutomationRibbonWorkPane.
     *
     * @param builder The builder.
     * @throws AutomationException Automation library error
     */
    public AutomationRibbonWorkPane(final ElementBuilder builder)
            throws AutomationException {
        super(builder);
        assertClassName(CLASS_NAME);
    }

    /**
     * Get the AutomationNUIPane associated with the given index.
     *
     * @param index The index.
     * @return The AutomationNUIPane.
     * @throws AutomationException Automation issue
     */
    public AutomationNUIPane getNUIPane(final int index)
            throws AutomationException {
        return new AutomationNUIPane(new ElementBuilder(this.getElementByControlType(index,
                ControlType.Pane, AutomationNUIPane.CLASS_NAME)));
    }

    /**
     * Get the AutomationNUIPane associated with the given index.
     *
     * @param search The search.
     * @return The AutomationNUIPane.
     * @throws AutomationException Automation issue.
     */
    public AutomationNUIPane getNUIPane(final Search search)
            throws AutomationException {
        return getNUIPane(search.getIndex());
    }
}

