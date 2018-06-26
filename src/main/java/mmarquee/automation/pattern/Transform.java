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
package mmarquee.automation.pattern;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.PatternID;
import mmarquee.automation.PropertyID;

/**
 * Wrapper around the Transform pattern.
 *
 * This looks to be unused anywhere
 */
public class Transform extends BasePattern {

    /**
     * Constructor for the Transform pattern.
     *
     * @param element The automation element for which the pattern is valid
     * @throws AutomationException If something goes wrong
     */
    public Transform(final AutomationElement element) throws AutomationException {
    	super(element);
        this.patternID = PatternID.Transform;
        this.availabilityPropertyID = PropertyID.IsTransformPatternAvailable;
    }

}
