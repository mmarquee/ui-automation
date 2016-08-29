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

import com.sun.jna.Pointer;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.List;

/**
 * Created by inpwt on 19/02/2016.
 *
 * Wrapper for the SystemMenu control element.
 */
public class AutomationSystemMenu extends AutomationBase {
    /**
     * Construct the AutomationSystemMenu
     * @param element The element
     * @throws AutomationException Automation issue
     */
    public AutomationSystemMenu(AutomationElement element) throws AutomationException {
        super(element);

        this.getItems();
    }

    /**
     * Get the item associated with the name
     * @param name The name to look for
     * @return The menu item
     * @throws AutomationException Automation issue
     */
    public AutomationMenuItem getItem(String name) throws AutomationException {

        Pointer condition = this.createTrueCondition();

        List<AutomationElement> collection =
                this.findAll(new TreeScope(TreeScope.TreeScope_Descendants), condition);

        AutomationElement foundElement = null;
        boolean found = false;

        for (AutomationElement elem: collection) {
            String eName = elem.getName();

            if (eName.equals(name)) {
                found = true;
                foundElement = elem;
                break;
            }
        }

        if (found) {
            return new AutomationMenuItem(foundElement);
        } else {
            // Throw an exception
            throw  new ItemNotFoundException();
        }
    }

    /**
     * Gets the items.
     *
     * Not fully implemented
     *
     * @throws AutomationException Automation issue
     */
    private void getItems() throws AutomationException {
        Pointer condition = this.createTrueCondition();

        List<AutomationElement> collection =
                this.findAll(new TreeScope(TreeScope.TreeScope_Children), condition);

        AutomationElement element = collection.get(0);

        String name = element.getName();
    }
}