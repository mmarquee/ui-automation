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
import mmarquee.automation.UIAutomation;

/**
 * Created by Mark Humphreys on 02/03/2016.
 *
 * Wrapper for the AppBar element
 */
public class AutomationAppBar extends AutomationBase {
    /**
     * <p>
     * Constructor for the AutomationAppBar.
     * </p>
     * <p>
     * This element seems to be undocumented by Microsoft
     * </p>
     * @param element The underlying automation element
     * @throws AutomationException Automation library error
     */
    public AutomationAppBar(final AutomationElement element)
            throws AutomationException {
        super(element);
    }

    /**
     * <p>
     * Constructor for the AutomationAppBar.
     * </p>
     * <p>
     * This element seems to be undocumented by Microsoft.
     * </p>
     * @param element The underlying automation element.
     * @param instance The automation instance.
     * @throws AutomationException Automation library error.
     */
    AutomationAppBar(final AutomationElement element,
                     final UIAutomation instance)
            throws AutomationException {
        super(element, instance);
    }
}
