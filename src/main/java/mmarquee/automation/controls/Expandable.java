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
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * @author Mark Humphreys
 * Date 21/09/2016.
 */
public interface Expandable extends Automatable {

    /**
     * Expands the element.
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
    void expand() throws AutomationException, PatternNotFoundException;

    /**
     * Collapses the element.
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
    void collapse() throws AutomationException, PatternNotFoundException;

    /**
     * Whether the element is expanded.
     * @return True if expanded
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
    boolean isExpanded() throws AutomationException, PatternNotFoundException;
}
