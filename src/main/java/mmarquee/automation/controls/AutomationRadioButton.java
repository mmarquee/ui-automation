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
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.SelectionItem;
import mmarquee.automation.uiautomation.IUIAutomation;

/**
 * Created by inpwt on 31/01/2016.
 */
public class AutomationRadioButton extends AutomationBase {

    private SelectionItem selectItemPattern;

    public AutomationRadioButton(AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        try {
            selectItemPattern = this.getSelectItemPattern();
        } catch (PatternNotFoundException ex) {
            // Handle this nicely somehow
        }
    }

    /**
     * Selects this element
     */
    public void selectItem() {
        this.selectItemPattern.select();
    }
}
