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

package mmarquee.automation.controls.menu;

import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.controls.ElementBuilder;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Wrapper for the SystemMenu control element.
 * @author Mark Humphreys
 * Date 19/02/2016
 */
public class AutomationSystemMenu extends AutomationBase {
    /**
     * Construct the AutomationSystemMenu.
     * @param element The element
     * @throws AutomationException Automation issue
     */
    public AutomationSystemMenu(AutomationElement element)
            throws AutomationException {
        super(new ElementBuilder(element));
        this.getItems();
    }

    /**
     * The control type.
     */
    public static ControlType controlType = ControlType.Menu;

    /**
     * Get the item associated with the name.
     *
     * @param name The name to look for
     * @return The menu item
     * @throws AutomationException Automation issue
     */
    public AutomationMenuItem getItem(String name) throws AutomationException {

        PointerByReference condition = this.createTrueCondition();

        List<AutomationElement> collection =
                this.findAll(new TreeScope(TreeScope.Descendants), condition);

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
            return new AutomationMenuItem(new ElementBuilder(foundElement));
        } else {
            // Throw an exception
            throw  new ItemNotFoundException(name);
        }
    }

    /**
     * Get the item matching the name.
     *
     * @param namePattern Pattern matching the name to look for
     * @return The menu item
     * @throws AutomationException Automation issue
     */
    public AutomationMenuItem getItem(Pattern namePattern) throws AutomationException {

        PointerByReference condition = this.createTrueCondition();

        List<AutomationElement> collection =
                this.findAll(new TreeScope(TreeScope.Descendants), condition);

        AutomationElement foundElement = null;
        boolean found = false;

        for (AutomationElement elem: collection) {
            String eName = elem.getName();

            if (eName != null && namePattern.matcher(eName).matches()) {
                found = true;
                foundElement = elem;
                break;
            }
        }

        if (found) {
            return new AutomationMenuItem(new ElementBuilder(foundElement));
        } else {
            // Throw an exception
            throw  new ItemNotFoundException(namePattern.toString());
        }
    }

    /**
     * Gets the items.
     *
     * @return The list of menu items
     * @throws AutomationException Automation issue
     */
    protected List<AutomationMenuItem> getItems() throws AutomationException {
        PointerByReference condition = this.createTrueCondition();

        List<AutomationElement> items =
                this.findAll(new TreeScope(TreeScope.Children), condition);

        List<AutomationMenuItem> list = new ArrayList<>();

        for (AutomationElement item : items) {
            list.add(new AutomationMenuItem(new ElementBuilder(item)));
        }

        return list;
    }
}