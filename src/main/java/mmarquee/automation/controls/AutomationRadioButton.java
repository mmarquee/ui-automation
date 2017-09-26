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
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.SelectionItem;

/**
 * Created by Mark Humphreys on 31/01/2016.
 *
 * Wrapper for the RadioButton element.
 */
public class AutomationRadioButton extends AutomationBase implements Selectable {

    /**
     * The selection item pattern.
     */
    private SelectionItem selectItemPattern;

    /**
     * Construct the AutomationRadioButton.
     *
     * @param element The element.
     * @param selection The item selection pattern.
     * @param instance Automation instance.
     * @throws PatternNotFoundException Expected pattern not found.
     * @throws AutomationException Automation library error.
     */
    AutomationRadioButton(final AutomationElement element,
                          final SelectionItem selection,
                          final UIAutomation instance)
            throws PatternNotFoundException, AutomationException {
        super(element, instance);
        selectItemPattern = selection;
    }

    /**
     * Construct the AutomationRadioButton.
     *
     * @param element The element.
     * @throws PatternNotFoundException Expected pattern not found.
     * @throws AutomationException Automation library error.
     */
    public AutomationRadioButton(final AutomationElement element)
            throws PatternNotFoundException, AutomationException {
        super(element);
//        selectItemPattern = this.getSelectItemPattern();
    }

    /**
     * Selects this element.
     *
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Failed to find pattern.
     */
    public void select()
            throws AutomationException, PatternNotFoundException {
        if (this.selectItemPattern == null) {
            selectItemPattern = this.getSelectItemPattern();
        }

        this.selectItemPattern.select();
    }

    /**
     * Gets the selection state.
     *
     * @return The selection state.
     * @throws AutomationException Error in the automation library.
     * @throws PatternNotFoundException Failed to find pattern.
     */
    public boolean isSelected()
            throws AutomationException, PatternNotFoundException {
        if (this.selectItemPattern == null) {
            selectItemPattern = this.getSelectItemPattern();
        }

        return this.selectItemPattern.isSelected();
    }
}
