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
package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.SelectionItem;

/**
 * Created by Mark Humphreys on 20/02/2016.
 *
 * Wrapper for the TreeViewItem element.
 */
public class AutomationTreeViewItem extends AutomationBase implements Selectable, Clickable {

    private SelectionItem selectItemPattern;
    private Invoke invokePattern;

    /**
     * Construct the AutomationTreeViewItem
     * @param element The element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationTreeViewItem(AutomationElement element)
            throws PatternNotFoundException, AutomationException {
        super(element);

        this.selectItemPattern = this.getSelectItemPattern();
        this.invokePattern = this.getInvokePattern();
    }

    /**
     * Select the item
     * @throws AutomationException Automation library issue
     */
    public void select() throws AutomationException {
        this.selectItemPattern.select();
    }

    /**
     * Is this item selected?
     * @return True if selected
     * @throws AutomationException Automation library issue
     */
    public boolean isSelected() throws AutomationException {
        return this.selectItemPattern.isSelected();
    }

    /**
     * Click the item
     * @throws AutomationException Automation library issue
     */
    public void click() throws AutomationException {
        if (this.isInvokePatternAvailable()) {
            this.invokePattern.invoke();
        } else {
            logger.warn("Invoke pattern is not available");
        }
    }
}
