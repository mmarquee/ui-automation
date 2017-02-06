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
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Created by Mark Humphreys on 01/02/2017.
 *
 * Specialised custom control representing a powerpoint slide.
 */
public class AutomationPowerpointSlide extends AutomationCustom {
    /**
     * Constructor for the AutomationPowerpointSlide
     * @param element The element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationPowerpointSlide (AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);
    }

    /**
     * Constructor for the AutomationPowerpointSlide
     * @param element The element
     * @param container ItemContainer pattern
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Pattern not found
     */
    public AutomationPowerpointSlide (AutomationElement element, ItemContainer container) throws PatternNotFoundException, AutomationException {
        super(element, container);
    }
}
