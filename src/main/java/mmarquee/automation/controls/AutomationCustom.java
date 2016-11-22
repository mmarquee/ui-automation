/*
 * Copyright 2016 inpwtepydjuf@gmail.com
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

/**
 * Created by Mark Humphreys on 08/03/2016.
 *
 * Wrapper for the Custom ControlId, which is usually a container.
 */
public class AutomationCustom extends AutomationContainer {
    /**
     * Constructor for the AutomationCustom
     * @param element The element
     * @throws AutomationException Automation library error
     */
    public AutomationCustom (AutomationElement element) throws AutomationException {
        super(element);
    }
}
