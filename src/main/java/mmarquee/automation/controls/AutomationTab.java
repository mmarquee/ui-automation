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

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark Humphreys on 26/01/2016.
 *
 * Wrapper for the Tab element.
 */
public class AutomationTab extends AutomationContainer {

    public List<AutomationTabItem> getTabItems() throws PatternNotFoundException {
        // Now get the list of tab items
        List<AutomationTabItem> tabItems = new ArrayList<>();

        try {
            List<AutomationElement> collection = this.findAll(new TreeScope(TreeScope.Descendants));

            for (AutomationElement elem : collection) {
                int retVal = elem.currentControlType();

                if (retVal == ControlType.TabItem.getValue()) {
                    tabItems.add(new AutomationTabItem(elem));
                }
            }
        } catch (AutomationException ex) {
            logger.error(ex.getMessage());
        }

        return tabItems;
    }

    /**
     * Selects the tab with the given name
     * @param name The name of the tab to select
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Pattern not found
     */
    public void selectTabPage(String name) throws AutomationException, PatternNotFoundException {

        for(AutomationTabItem item: this.getTabItems()) {
            if (name.equals(item.name())) {
                item.selectItem();
            }
        }
    }

    /**
     * Constructor for the AutomationTab
     * @param element The underlying element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationTab (AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);
    }

    /**
     * Constructor for the AutomationTab
     * @param element The underlying element
     * @param container The ItemContainer pattern
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationTab (AutomationElement element, ItemContainer container) throws PatternNotFoundException, AutomationException {
        super(element, container);
    }
}