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
import mmarquee.automation.pattern.Text;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.List;

/**
 * @author Mark Humphreys
 * Date 16/02/2016.
 *
 * Wrapper for the Document element.
 *
 */
public class AutomationDocument extends AutomationBase {
    private Text textPattern;

    /**
     * Constructor for the AutomationDocument
     * @param element The underlying automation element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationDocument(AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);
//        this.textPattern = this.getTextPattern();
    }

    /**
     * Constructor for the AutomationDocument
     * @param element The underlying automation element
     * @param pattern The Text pattern
     * @param instance Automation instance
     */
    AutomationDocument(AutomationElement element, Text pattern, UIAutomation instance) {
        super(element, instance);
        this.textPattern = pattern;
    }

    /**
     * Gets the text for the document
     * @return The document's text
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    public String getText() throws AutomationException, PatternNotFoundException {
        if (this.textPattern == null) {
            this.textPattern = this.getTextPattern();
        }
        return this.textPattern.getText();
    }

    /**
     * Gets the selection
     *
     * @return String of text that is selected
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    public String getSelection() throws AutomationException, PatternNotFoundException {
        if (this.textPattern == null) {
            this.textPattern = this.getTextPattern();
        }

        return this.textPattern.getSelection();
    }

    public AutomationDocumentPage getPage(int index) throws PatternNotFoundException, AutomationException {
        List<AutomationElement> items = this.findAll(
                new TreeScope(TreeScope.Descendants),
                this.createControlTypeCondition(ControlType.Custom));

        return new AutomationDocumentPage(items.get(index));
    }
}
