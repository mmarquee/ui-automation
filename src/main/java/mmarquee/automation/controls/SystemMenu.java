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

import com.sun.jna.ptr.PointerByReference;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.Element;
import mmarquee.automation.ItemNotFoundException;
import mmarquee.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Wrapper for the SystemMenu control element.
 * @author Mark Humphreys
 * Date 19/02/2016
 */
public class SystemMenu extends AutomationBase {
    /**
     * Construct the SystemMenu.
     * @param element The element
     * @throws AutomationException Automation issue
     */
    public SystemMenu(Element element)
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
    public MenuItem getItem(String name) throws AutomationException {

        PointerByReference condition = this.createTrueCondition();

        List<Element> collection =
                this.findAll(new TreeScope(TreeScope.DESCENDANTS), condition);

        Element foundElement = null;
        boolean found = false;

        for (Element elem: collection) {
            String eName = elem.getName();

            if (eName.equals(name)) {
                found = true;
                foundElement = elem;
                break;
            }
        }

        if (found) {
            return new MenuItem(new ElementBuilder(foundElement));
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
    public MenuItem getItem(Pattern namePattern) throws AutomationException {

        PointerByReference condition = this.createTrueCondition();

        List<Element> collection =
                this.findAll(new TreeScope(TreeScope.DESCENDANTS), condition);

        Element foundElement = null;
        boolean found = false;

        for (Element elem: collection) {
            String eName = elem.getName();

            if (eName != null && namePattern.matcher(eName).matches()) {
                found = true;
                foundElement = elem;
                break;
            }
        }

        if (found) {
            return new MenuItem(new ElementBuilder(foundElement));
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
    public List<MenuItem> getItems() throws AutomationException {
        PointerByReference condition = this.createTrueCondition();

        List<Element> items =
                this.findAll(new TreeScope(TreeScope.CHILDREN), condition);

        List<MenuItem> list = new ArrayList<MenuItem>();

        for (Element item : items) {
            list.add(new MenuItem(new ElementBuilder(item)));
        }

        return list;
    }
}