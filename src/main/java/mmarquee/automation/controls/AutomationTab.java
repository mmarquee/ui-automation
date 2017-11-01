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
import mmarquee.automation.UIAutomation;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Mark Humphreys
 * Date 26/01/2016.
 *
 * Wrapper for the Tab element.
 */
public class AutomationTab extends AutomationContainer {

    /**
     * Gets the tab items for the tab container.
     * @return List of tabsItems.
     * @throws PatternNotFoundException Failed to find the required pattern.
     */
    public List<AutomationTabItem> getTabItems() throws PatternNotFoundException {
        // Now get the list of tab items
        List<AutomationTabItem> tabItems = new ArrayList<AutomationTabItem>();

        try {
            List<AutomationElement> collection = this.findAll(new TreeScope(TreeScope.Descendants),this.createControlTypeCondition(ControlType.TabItem));

            for (AutomationElement elem : collection) {
                tabItems.add(new AutomationTabItem(elem, this.automation));
            }
        } catch (AutomationException ex) {
            logger.error(ex.getMessage());
        }

        return tabItems;
    }

    /**
     * Selects the tab with the given name.
     * @param name The name of the tab to select.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Pattern not found.
     */
    public void selectTabPage(final String name) throws AutomationException, PatternNotFoundException {

        boolean found = false;

        for(AutomationTabItem item: this.getTabItems()) {
            if (name.equals(item.getName())) {
                found = true;
                item.selectItem();
            }
        }

        if (!found) {
            throw new ElementNotFoundException();
        }
    }

    /**
     * Selects the tab matching given namePattern.
     * @param namePattern Pattern matching the name of the tab to select.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Pattern not found.
     */
    public void selectTabPage(final Pattern namePattern) throws AutomationException, PatternNotFoundException {

        boolean found = false;

        for(AutomationTabItem item: this.getTabItems()) {
            final String name = item.getName();
            
			if (name != null && namePattern.matcher(name).matches()) {
                found = true;
                item.selectItem();
            }
        }

        if (!found) {
            throw new ElementNotFoundException();
        }
    }

    /**
     * Constructor for the AutomationTab.
     * @param element The underlying element.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public AutomationTab (final AutomationElement element)
            throws PatternNotFoundException, AutomationException {
        super(element);
    }

    /**
     * Constructor for the AutomationTab.
     * @param element The underlying element.
     * @param container The ItemContainer pattern.
     * @param instance Automation instance.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public AutomationTab (final AutomationElement element,
                          final ItemContainer container,
                          final UIAutomation instance)
            throws PatternNotFoundException, AutomationException {
        super(element, container, instance);
    }

    /**
     * Constructor for the AutomationTab.
     * @param element The underlying element.
     * @param container The ItemContainer pattern.
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public AutomationTab (final AutomationElement element,
                          final ItemContainer container)
            throws PatternNotFoundException, AutomationException {
        super(element, container);
    }
}