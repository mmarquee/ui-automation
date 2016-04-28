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
import mmarquee.automation.ControlType;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.pattern.ExpandCollapse;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 10/02/2016.
 *
 * Wrapper for the MenuItem element.
 */
public class AutomationMenuItem extends AutomationBase {
    private Invoke invokePattern;
    private ExpandCollapse collapsePattern;

    /**
     * Construct the AutomationMenuItem
     * @param element The element
     * @param automation The automation library
     */
    public AutomationMenuItem(AutomationElement element, IUIAutomation automation) {
        super(element, automation);

        try {
            this.collapsePattern = this.getExpandCollapsePattern();
            this.invokePattern = this.getInvokePattern();
        } catch (PatternNotFoundException ex) {
        //    logger.info("Failed to get patterns");
        }
    }

    /**
     * Invoke the click pattern for the menu item
     */
    public void click() {
        if (this.invokePattern != null) {
            this.invokePattern.invoke();
        }
    }

    /**
     * Gets the list of items associatd with this menuitem
     * @return List of menu items
     */
    public List<AutomationMenuItem> getItems() {
        List<AutomationElement> items = this.findAll(TreeScope.TreeScope_Descendants,
                this.createControlTypeCondition(ControlType.MenuItem));

        List<AutomationMenuItem> list = new ArrayList<AutomationMenuItem>();

        for (AutomationElement item : items) {
            list.add(new AutomationMenuItem(item, automation));
        }

        return list;
    }

    /**
     * Is the control expanded
     * @return True if expanded
     */
    public boolean isExpanded() {
        return collapsePattern.isExpanded();
    }

    /**
     * Collapses the element
     */
    public void collapse() {
        this.collapsePattern.collapse();
    }

    /**
     * Expands the element
     */
    public void expand() {
        this.collapsePattern.expand();
    }
}
