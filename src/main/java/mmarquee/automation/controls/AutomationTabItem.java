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
 * Wrapper for the TabItem element.
 *
 * @author Mark Humphreys
 * Date 28/01/2016.
 */
public final class AutomationTabItem extends AutomationContainer implements ImplementsSelect {

   /**
     * Construct the AutomationTabItem.
     *
     * @param builder The builder.
     */
    public AutomationTabItem(final ElementBuilder builder) {
        super(builder);
    }

    /**
     * Selects this item.
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Pattern not found
     */
    public void selectItem()
            throws AutomationException, PatternNotFoundException  {
        select();
    }
}
