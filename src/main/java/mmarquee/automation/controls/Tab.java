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
import mmarquee.automation.ControlType;
import mmarquee.automation.Element;
import mmarquee.automation.ElementNotFoundException;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Wrapper for the Tab element.
 * @author Mark Humphreys
 * Date 26/01/2016.
 */
public class Tab extends Container {

    /**
     * Gets the tab items for the tab container.
     * @return List of tabsItems.
     */
    public List<TabItem> getTabItems() {
        // Now get the list of tab items
        List<TabItem> tabItems = new ArrayList<>();

        try {
            List<Element> collection =
                    this.findAll(new TreeScope(TreeScope.DESCENDANTS),
                            this.createControlTypeCondition(ControlType.TabItem));

            for (Element elem : collection) {
                tabItems.add(
                        new TabItem(new ElementBuilder(elem)
                                .automation(this.getAutomation())));
            }
        } catch (AutomationException ex) {
            getLogger().severe(ex.getMessage());
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

        for (TabItem item: this.getTabItems()) {
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

        for (TabItem item: this.getTabItems()) {
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
     * @param builder The builder
     */
    public Tab(final ElementBuilder builder) {
        super(builder);
    }
}
