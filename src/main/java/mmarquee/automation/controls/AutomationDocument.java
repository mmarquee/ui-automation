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
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Text;

/**
 * Created by Mark Humphreys on 16/02/2016.
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
        this.textPattern = this.getTextPattern();
    }

    /**
     * Constructor for the AutomationDocument
     * @param element The underlying automation element
     * @param pattern The Text pattern
     */
    public AutomationDocument(AutomationElement element, Text pattern) {
        super(element);
        this.textPattern = pattern;
    }

    /**
     * Gets the text for the document
     * @return The document's text
     * @throws AutomationException Something has gone wrong
     */
    public String getText() throws AutomationException {
        return this.textPattern.getText();
    }

    /**
     * Gets the selection
     *
     * @return String of text that is selected
     * @throws AutomationException Something has gone wrong
     */
    public String getSelection() throws AutomationException {
        return this.textPattern.getSelection();
    }
}
