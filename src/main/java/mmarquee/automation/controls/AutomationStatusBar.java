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
 * Date 01/02/2016.
 *
 * Wrapper for the StatusBar element.
 */
public class AutomationStatusBar extends AutomationContainer {

    /**
     * Constructor for AutomationStatusBar.
     *
     * @param builder The builder
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Did not find the pattern.
     */
    public AutomationStatusBar(final ElementBuilder builder)
            throws PatternNotFoundException, AutomationException{
        super(builder);
    }
}
