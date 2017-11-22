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
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Selection;
import mmarquee.automation.pattern.SelectionItem;

import java.util.List;

/**
 * @author Mark Humphreys
 * Date 26/02/2016.
 *
 * Wrapper for the Image control element.
 */
public class AutomationImage extends AutomationBase {
    /**
     * Construct the AutomationImage.
     * @param builder The builder
     * @throws AutomationException Automation library error
     */
    public AutomationImage(final ElementBuilder builder) throws AutomationException {
        super(builder);
    }

    // Powerpoint weirdness, probably needs it's own class in the end

    private SelectionItem selectionItemPattern;

    public AutomationElement getSelectionContainer() throws PatternNotFoundException, AutomationException {
        if (this.selectionItemPattern == null) {
            this.selectionItemPattern = this.getSelectItemPattern();
        }

        AutomationElement element
                = selectionItemPattern.getSelectionContainer();

        return element;
    }
}
