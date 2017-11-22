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

import java.util.regex.Pattern;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.ItemContainer;
import mmarquee.automation.pattern.PatternNotFoundException;

/**
 * Wrapper for the Panel element.
 *
 * @author Mark Humphreys
 * Date 26/02/2016.
 */
public class AutomationPanel extends AutomationContainer {
    /**
     * Construct the AutomationPanel.
     *
     * @param builder The builder
     * @throws AutomationException Something is wrong in automation.
     * @throws PatternNotFoundException Could not find pattern.
     */
    public AutomationPanel(final ElementBuilder builder)
            throws AutomationException, PatternNotFoundException {
        super(builder);
    }

    /**
     * Gets an MDI window from the panel.
     *
     * Yes, panels can have windows - in this case the window is assumed to be extant.
     *
     * @param index The nth element.
     * @return The found window.
     * @throws PatternNotFoundException Failed to find the right pattern.
     * @throws AutomationException Something went really wrong.
     * @deprecated Use getWindow(int) instead.
     */
    @Deprecated
    private AutomationWindow getMDIWindow(final int index)
            throws PatternNotFoundException, AutomationException {
        return getWindow(index);
    }

    /**
     * Gets a window from the panel.
     *
     * @param index The nth element.
     * @return The found window.
     * @throws PatternNotFoundException Failed to find the right pattern.
     * @throws AutomationException Something went really wrong.
     */
    public AutomationWindow getWindow(final int index)
            throws PatternNotFoundException, AutomationException {
    	return new AutomationWindow(new ElementBuilder(this.getElementByControlType(index, ControlType.Window)));
    }

    /**
     * Gets a window from the panel.
     *
     * @param name Name of the control.
     * @return The found window.
     * @throws PatternNotFoundException Failed to find the right pattern.
     * @throws AutomationException Something went really wrong.
     */
    public AutomationWindow getWindow(final String name)
            throws PatternNotFoundException, AutomationException {
        return new AutomationWindow(new ElementBuilder(this.getElementByControlType(name, ControlType.Window)));
    }

    /**
     * Gets a window from the panel.
     *
     * @param namePattern pattern matching the name of the control.
     * @return The found window.
     * @throws PatternNotFoundException Failed to find the right pattern.
     * @throws AutomationException Something went really wrong.
     */
    public AutomationWindow getWindow(final Pattern namePattern)
            throws PatternNotFoundException, AutomationException {
        return new AutomationWindow(new ElementBuilder(this.getElementByControlType(namePattern, ControlType.Window)));
    }

    /**
     * Gets the window associated with the given automation id.
     *
     * @param id The id to use.
     * @return The found window.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Expected pattern not found.
      */
    public AutomationWindow getWindowByAutomationId(final String id)
            throws PatternNotFoundException, AutomationException {
        return new AutomationWindow(new ElementBuilder(this.getElementByAutomationId(id, ControlType.Window)));
    }

    /**
     * Gets the window, using the search criteria.
     * @param search Matcher for the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationWindow getWindow(final Search search) throws PatternNotFoundException, AutomationException {
        if (search.getHasPattern()) {
            return getWindow(search.getPattern());
        } else if (search.getHasAutomationId()) {
            return getWindowByAutomationId(search.getAutomationId());
        } else if (search.getHasId()) {
            return getWindow(search.getId());
        } else if (search.getHasName()) {
            return getWindow(search.getName());
        } else {
            throw new AutomationException("Search type not found");
        }
    }

    /**
     * Gets the window, using the search criteria.
     * @param search Matcher for the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationWindow getMDIWindow(final Search search) throws PatternNotFoundException, AutomationException {
        if (search.getHasId()) {
            return getWindow(search.getId());
        } else {
            throw new AutomationException("Search type not found");
        }
    }
}
