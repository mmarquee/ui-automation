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
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Value;

/**
 * Created by Mark Humphreys on 08/03/2016.
 *
 * Wrapper for the Custom ControlId, which is usually a container.
 */
public class AutomationCustom extends AutomationContainer implements Valueable {

    /**
     * The value pattern.
     */
    private Value valuePattern;

    /**
     * Constructor for the AutomationCustom.
     *
     * @param element The element.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Pattern not found.
     */
    public AutomationCustom(final AutomationElement element)
            throws PatternNotFoundException, AutomationException {
        super(element);
    }

    /**
     * Constructor for the AutomationCustom control.
     *
     * @param element The element.
     * @param container ItemContainer pattern.
     * @param value The Value pattern to use.
     * @param instance The automation instance.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Pattern not found.
     */
    AutomationCustom(final AutomationElement element,
                     final ItemContainer container,
                     final Value value,
                     final UIAutomation instance)
            throws PatternNotFoundException, AutomationException {
        super(element, container, instance);

        this.valuePattern = value;
    }

    /**
     * Gets the value text associated with this element.
     *
     * @return The value of the item.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Failed to find pattern.
     */
    public String getValue()
            throws AutomationException, PatternNotFoundException {
        if (this.valuePattern == null) {
            this.valuePattern = this.getValuePattern();
        }

        return this.valuePattern.value();
    }

    /**
     * Constructor for the AutomationCustom.
     *
     * @param element The element.
     * @param container ItemContainer pattern.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Pattern not found.
     */
    AutomationCustom(final AutomationElement element,
                     final ItemContainer container)
            throws PatternNotFoundException, AutomationException {
        super(element, container);
    }
}
