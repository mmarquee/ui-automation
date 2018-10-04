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
import mmarquee.automation.pattern.Text;

/**
 * The Control supports the methods of the Text Pattern.
 */
public interface ImplementsText extends Automatable, CanRequestBasePattern {

	 /**
     * Gets the text for the document.
     * @return The document's text
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    default public String getText() throws AutomationException, PatternNotFoundException {
		final Text textPattern = requestAutomationPattern(Text.class);
		if (textPattern.isAvailable()) {
			return textPattern.getText();
		}
		throw new PatternNotFoundException("Cannot get text");
    }
    /**
     * Gets the selection.
     *
     * @return String of text that is selected
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
    default public String getSelection() throws AutomationException, PatternNotFoundException {
		final Text textPattern = requestAutomationPattern(Text.class);
		if (textPattern.isAvailable()) {
			return textPattern.getSelection();
		}
		throw new PatternNotFoundException("Cannot get text");
    }
}
