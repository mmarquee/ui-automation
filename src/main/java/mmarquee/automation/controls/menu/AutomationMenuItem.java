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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.controls.Clickable;
import mmarquee.automation.controls.Expandable;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Humphreys on 10/02/2016.
 *
 * Wrapper for the MenuItem element.
 */
public class AutomationMenuItem extends AutomationBase implements Clickable, Expandable {
    private Invoke invokePattern;
    private ExpandCollapse collapsePattern;

    /**
     * Construct the AutomationMenuItem
     * @param element The element
     * @throws PatternNotFoundException Expected pattern not found
     * @throws AutomationException Automation error
     */
    public AutomationMenuItem(AutomationElement element)
            throws PatternNotFoundException, AutomationException {
        super(element);
        this.collapsePattern = this.getExpandCollapsePattern();
        this.invokePattern = this.getInvokePattern();
    }

    public static ControlType controlType = ControlType.MenuItem;

    /**
     * Invoke the click pattern for the menu item.
     * @throws AutomationException Something has gone wrong
     */
    public void click() throws AutomationException {
        if (this.invokePattern != null) {
            this.invokePattern.invoke();
        }
    }

    /**
     * Gets the list of items associated with this menu item
     * @return List of menu items
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public List<AutomationMenuItem> getItems() throws PatternNotFoundException, AutomationException {
        List<AutomationElement> items = this.findAll(new TreeScope(TreeScope.Descendants),
                this.createControlTypeCondition(ControlType.MenuItem).getValue());

        List<AutomationMenuItem> list = new ArrayList<AutomationMenuItem>();

        for (AutomationElement item : items) {
            list.add(new AutomationMenuItem(item));
        }

        return list;
    }

    /**
     * Is the control expanded
     * @return True if expanded
     * @throws AutomationException Something has gone wrong
     */
    public boolean isExpanded() throws AutomationException {
        return collapsePattern.isExpanded();
    }

    /**
     * Collapses the element
     * @throws AutomationException Something has gone wrong
     */
    public void collapse() throws AutomationException {
        this.collapsePattern.collapse();
    }

    /**
     * Expands the element
     * @throws AutomationException Something has gone wrong
     */
    public void expand() throws AutomationException {
        this.collapsePattern.expand();
    }
}
