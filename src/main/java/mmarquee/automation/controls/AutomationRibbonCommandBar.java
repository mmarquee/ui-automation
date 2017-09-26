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
 * Specialist pane that represents the RibbonCommandBar
 */
public class AutomationRibbonCommandBar extends AutomationContainer {

    /**
     * The class name for this panel.
     */
	public final static String CLASS_NAME = "UIRibbonCommandBar";
	
    /**
     * Construct the AutomationRibbonCommandBar.
     *
     * @param element The element.
     * @throws AutomationException Something is wrong in automation.
     * @throws PatternNotFoundException Pattern not found.
     */
    public AutomationRibbonCommandBar(final AutomationElement element)
            throws PatternNotFoundException, AutomationException {
        super(element);
        assertClassName(CLASS_NAME);
    }

    /**
     * Construct the AutomationRibbonCommandBar.
     *
     * @param element The element.
     * @param container The container Pattern.
     * @param instance Automation instance.
     * @throws AutomationException Something is wrong in automation
     * @throws PatternNotFoundException Pattern not found
     */
    AutomationRibbonCommandBar(final AutomationElement element,
                               final ItemContainer container,
                               final UIAutomation instance)
            throws PatternNotFoundException, AutomationException {
        super(element, container, instance);
        assertClassName(CLASS_NAME);
    }

    /**
     * Construct the AutomationRibbonCommandBar.
     *
     * @param element The element.
     * @param container The container Pattern.
     * @throws AutomationException Something is wrong in automation.
     * @throws PatternNotFoundException Pattern not found.
     */
    AutomationRibbonCommandBar(final AutomationElement element,
                               final ItemContainer container)
            throws PatternNotFoundException, AutomationException {
        super(element, container);
        assertClassName(CLASS_NAME);
    }

    /**
     * Get the RibbonWorkPane associated with the container.
     * @return The AutomationRibbonWorkPane.
     * @throws AutomationException Something is wrong in automation.
     * @throws PatternNotFoundException Pattern not found.
     */
    public AutomationRibbonWorkPane getRibbonWorkPane()
            throws PatternNotFoundException, AutomationException {
        return new AutomationRibbonWorkPane(this.getElementByControlType(0, ControlType.Pane, AutomationRibbonWorkPane.CLASS_NAME));
    }
}
