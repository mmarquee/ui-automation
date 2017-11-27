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
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.SelectionItem;

/**
 * Wrapper for the ListItem element.
 *
 * @author Mark Humphreys
 * Date 09/02/2016.
 */
public class AutomationListItem extends AutomationContainer
        implements Selectable, Clickable {

    /**
     * The selectionItem pattern.
     */
    SelectionItem selectItemPattern;

    /**
     * Constructor for the AutomationListItem.
     * @param builder The builder
     * @throws AutomationException Automation library error.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    public AutomationListItem(final ElementBuilder builder)
            throws PatternNotFoundException, AutomationException {
        super(builder);
        this.selectItemPattern = builder.getSelectItem();
    }

    /**
     * Selects this item.
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public void select()
            throws AutomationException, PatternNotFoundException {
        if (this.selectItemPattern == null) {
            this.selectItemPattern = this.getSelectItemPattern();
        }

        if (this.selectItemPattern != null) {
        	this.selectItemPattern.select();
        } else {
            throw new PatternNotFoundException("Select pattern not found");
        }
    }

    /**
     * Is this item selected.
     * @return True if selected.
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public boolean isSelected()
            throws AutomationException, PatternNotFoundException {
        if (this.selectItemPattern == null) {
            this.selectItemPattern = this.getSelectItemPattern();
        }
        if (this.selectItemPattern != null) {
        	return this.selectItemPattern.isSelected();
        }
        throw new PatternNotFoundException("Select pattern not found");
    }

    /**
     * Clicks the item.
     * 
     * @throws AutomationException Error in the automation library.
     * @throws PatternNotFoundException Could not find the invoke pattern.
     */
    public void click()
            throws AutomationException, PatternNotFoundException {
        super.invoke();
    }

}
