/*
 * Copyright 2016-18 inpwtepydjuf@gmail.com
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
import mmarquee.automation.pattern.BasePattern;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Interface that flags whether a control can get a base pattern.
 */
public interface CanRequestBasePattern {
    /**
     * Request an Automation pattern.
     *
     * @param automationPatternClass The pattern to request
     * @param <T> The type
     * @return The base pattern
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Failed to find pattern
     */
	<T extends BasePattern> T requestAutomationPattern(final Class<T> automationPatternClass) throws PatternNotFoundException, AutomationException;
}
