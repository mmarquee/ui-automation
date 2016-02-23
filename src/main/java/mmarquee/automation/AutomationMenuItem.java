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
package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 10/02/2016.
 */
public class AutomationMenuItem extends AutomationBase {
    private IUIAutomationInvokePattern invokePattern;
    private IUIAutomationExpandCollapsePattern expandPattern;

    public AutomationMenuItem(IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);
        try {
            this.invokePattern = this.getInvokePattern();
            this.click();
        } catch (Exception ex) {
            // All is OK
        }

        //if (this.element.)

     //   try {
            this.expandPattern = this.getExpandCollapsePattern();
     //   } catch (Exception ex) {
     //       // All is OK
     //
     //   }
    }

    /**
     * Invoke the expand pattern for the menu item
     */
    public void expand() {
        if (this.expandPattern != null) {
            try {
                this.element.wait(750);
            } catch (Exception ex) {
                // Not sure about this yet
            }

            this.expandPattern.expand();
        }
    }

    /**
     * Invoke the collapse pattern for the menu item
     */
    public void collapse() {
        if (this.expandPattern != null) {
            this.expandPattern.collapse();
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
     * Gets the items associated with this menuitem
     * @return List of menu items
     */
    public List<AutomationMenuItem> getItems() {
        IUIAutomationElementArray items = this.findAll();
        
        int length = items.length();

        List<AutomationMenuItem> list = new ArrayList<AutomationMenuItem>();

        for (int count = 0; count < items.length(); count++) {
            list.add(new AutomationMenuItem(items.getElement(count), uiAuto));
        }

        return list;
    }

    /**
     * Get the menu item associated with this name
     * @param name to look for
     * @return The menu item
     */
    public AutomationMenuItem getMenuItem (String name) {

        IUIAutomationElementArray items = this.element.findAll(TreeScope.TreeScope_Children,
                this.createControlTypeCondition(ControlTypeID.MenuItem));

        int length = items.length();

        IUIAutomationElement item = this.element.findFirst(TreeScope.TreeScope_Children,
                this.createAndCondition(
                        this.createNamePropertyCondition(name),
                        this.createControlTypeCondition(ControlTypeID.MenuItem)));

        return new AutomationMenuItem(item, this.uiAuto);
    }
}
