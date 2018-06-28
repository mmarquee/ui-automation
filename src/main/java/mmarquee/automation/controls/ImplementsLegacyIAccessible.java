/*
 * Copyright 2017-18 inpwtepydjuf@gmail.com
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

/**
 * Make interface for implementations of the ImplementsLegacyIAccessible pattern.
 *
 * @author Mark Humphreys
 * Date 24/12/2017.
 */
public interface ImplementsLegacyIAccessible extends Automatable {
    /**
     * Gets value from the IAccessible interface.
     * @return The string value
     * @throws AutomationException Automation library error
     */
    String getValueFromIAccessible() throws AutomationException;

    /**
     * Sets value via the IAccessible interface.
     * @param value The string value
     * @throws AutomationException Automation library error
     */
    void setValueFromIAccessible(final String value) throws AutomationException;
}