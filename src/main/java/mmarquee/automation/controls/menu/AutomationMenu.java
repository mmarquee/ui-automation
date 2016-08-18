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

package mmarquee.automation.controls.menu;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.controls.AutomationBase;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by inpwt on 09/02/2016.
 *
 * * Wrapper for the Menu control element.
 */
public class AutomationMenu extends AutomationBase {
    protected Logger logger = Logger.getLogger(AutomationMenu.class.getName());

    /**
     * Construct the AutomationMenu
     * @param element The element
     */
    public AutomationMenu(AutomationElement element) {
        super(element);
    }

    /**
     * Gets the item associated with the index
     * @param index The index
     * @return The found item
     * @throws AutomationException Something went wrong
     */
    public AutomationMenuItem getMenuItem (int index) throws AutomationException {
        logger.info("Finding " + index);

        List<AutomationElement> items = this.findAll();

        AutomationMenuItem item = new AutomationMenuItem(items.get(index));

        return item;
    }
}
