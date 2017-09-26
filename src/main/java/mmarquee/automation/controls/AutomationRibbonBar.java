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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Created by Mark Humphreys on 02/03/2016.
 *
 * Specialist pane that represents the RibbonBar
 */
public class AutomationRibbonBar extends AutomationPanel {

    /**
     * Control name of the panel type.
     */
	public final static String CLASS_NAME = "UIRibbonCommandBarDock";
	
    /**
     * Construct the AutomationRibbonBar.
     * @param element The element.
     * @throws AutomationException Something is wrong in automation.
     * @throws PatternNotFoundException Could not find pattern.
     */
    public AutomationRibbonBar(final AutomationElement element)
            throws AutomationException, PatternNotFoundException {
        super(element);
        assertClassName(CLASS_NAME);
    }

    /**
     * Construct the AutomationReBar.
     * @param element The element.
     * @param containerPattern The container Pattern.
     * @param instance Automation instance.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Pattern not found.
     */
    AutomationRibbonBar(final AutomationElement element,
                        final ItemContainer containerPattern,
                        final UIAutomation instance)
            throws PatternNotFoundException, AutomationException {
        super(element, containerPattern, instance);
        assertClassName(CLASS_NAME);
    }

    /**
     * Construct the AutomationReBar.
     * @param element The element.
     * @param containerPattern The container Pattern.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Pattern not found.
     */
    AutomationRibbonBar(final AutomationElement element,
                        final ItemContainer containerPattern)
            throws PatternNotFoundException, AutomationException {
        super(element, containerPattern);
        assertClassName(CLASS_NAME);
    }

    /**
     * Get the RibbonCommandBar associated with this container.
     * @return The AutomationRibbonBar.
     * @throws AutomationException Automation issue.
     * @throws PatternNotFoundException Pattern not found.
     */
    public AutomationRibbonCommandBar getRibbonCommandBar()
            throws PatternNotFoundException, AutomationException {
        return new AutomationRibbonCommandBar(this.getElementByControlType(0, ControlType.Pane, AutomationRibbonCommandBar.CLASS_NAME));
    }
}
