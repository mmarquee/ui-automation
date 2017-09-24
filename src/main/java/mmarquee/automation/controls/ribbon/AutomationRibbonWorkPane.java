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
package mmarquee.automation.controls.ribbon;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.controls.AutomationContainer;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Created by Mark Humphreys on 02/03/2016.
 *
 * * Specialist pane that represents the RibbonWorkPane of the MS Ribbon control.
 */
public class AutomationRibbonWorkPane extends AutomationContainer {
	
	public final static String CLASS_NAME = "UIRibbonWorkPane";
	
    /**
     * Construct the AutomationRibbonWorkPane
     * @param element The element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Patter not found
     */
    public AutomationRibbonWorkPane(AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);
        assertClassName(CLASS_NAME);
    }

    /**
     * Construct the AutomationRibbonWorkPane
     * @param element The element
     * @param containerPattern The container Pattern
     * @param instance Automation instance
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Patter not found
     */
    public AutomationRibbonWorkPane(AutomationElement element, ItemContainer containerPattern, UIAutomation instance) throws PatternNotFoundException, AutomationException {
        super(element, containerPattern, instance);
        assertClassName(CLASS_NAME);
    }

    /**
     * Get the AutomationNUIPane associated with the given index
     * @param index The index
     * @return The AutomationNUIPane
     * @throws AutomationException Automation issue
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationNUIPane getNUIPane(int index) throws PatternNotFoundException, AutomationException {
        return new AutomationNUIPane(this.getElementByControlType(index, ControlType.Pane, AutomationNUIPane.CLASS_NAME));
    }
}

