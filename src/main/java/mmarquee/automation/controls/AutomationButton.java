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
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Wrapper around the Button element.
 * @author Mark Humphreys
 * Date 02/02/2016.
 */
public class AutomationButton
        extends AutomationBase
        implements Clickable, Focusable {
    public static ControlType controlType = ControlType.Button;

    /**
     * Constructor for the AutomationButton.
     *
     * @param builder The builder
     */
    public AutomationButton(final ElementBuilder builder) {
        super(builder);
    }

    /**
     * <p>
     * Invokes the click event for this control.
     * </p>
     * @throws AutomationException Error in the automation library.
     * @throws PatternNotFoundException Could not find the invoke pattern.
     */
    public void click() throws AutomationException, PatternNotFoundException {
        this.invoke();
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
