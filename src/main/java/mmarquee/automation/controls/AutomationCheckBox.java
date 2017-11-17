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
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.Toggle;
import mmarquee.automation.uiautomation.ToggleState;

/**
 * @author Mark Humphreys
 * Date 31/01/2016.
 *
 * Wrapper for the CheckBox element.
 */
public class AutomationCheckBox
        extends AutomationBase
        implements Toggleable, Focusable {

    /**
     * The toggle pattern.
     */
    private Toggle togglePattern;

    /**
     * <p>
     * Invokes the toggle event for this control.
     * </p>
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Failed to find pattern.
     */
    public void toggle ()
            throws AutomationException, PatternNotFoundException {
        if (this.togglePattern == null) {
            togglePattern = this.getTogglePattern();
        }

        this.togglePattern.toggle();
    }

    /**
     * <p>
     * Gets the toggle state of this control.
     * </p>
     * @return The toggle state.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Failed to find pattern.
     */
    public ToggleState getToggleState ()
            throws AutomationException, PatternNotFoundException {
        if (this.togglePattern == null) {
            togglePattern = this.getTogglePattern();
        }

        return this.togglePattern.currentToggleState();
    }

    /**
     * Constructor for the AutomationCheckBox.
     *
     * @param element The element.
     * @throws AutomationException Automation exception.
     * @throws PatternNotFoundException Pattern not found.
     */
    public AutomationCheckBox (final AutomationElement element)
            throws PatternNotFoundException, AutomationException {
        super(new ElementBuilder(element));
    }

    /**
     * Constructor for the AutomationCheckBox.
     *
     * @param element The element.
     * @param pattern The pattern.
     * @param instance Automation instance.
     */
    public AutomationCheckBox(final AutomationElement element,
                              final Toggle pattern,
                              final UIAutomation instance) {
        super(new ElementBuilder(element).automation(instance));
        togglePattern = pattern;
    }

    /**
     * Constructor for the AutomationCheckBox.
     *
     * @param element The element.
     * @param pattern The pattern.
     */
    public AutomationCheckBox(final AutomationElement element,
                              final Toggle pattern) {
        super(new ElementBuilder(element));
        togglePattern = pattern;
    }

    /**
     * <p>
     * Sets the focus to this control.
     * </p>
     */
    public void focus() {
        this.element.setFocus();
    }
}
