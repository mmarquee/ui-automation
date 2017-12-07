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

import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;

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
     */
    public AutomationPanel(final ElementBuilder builder) {
        super(builder);
    }

    /**
     * Gets an MDI window from the panel.
     *
     * Yes, panels can have windows - in this case the window is assumed to be extant.
     *
     * @param index The nth element.
     * @return The found window.
     * @throws AutomationException Something went really wrong.
     * @deprecated Use getWindow(int) instead.
     */
    @Deprecated
    private AutomationWindow getMDIWindow(final int index)
            throws AutomationException {
        return getWindow(index);
    }

    /**
     * Gets a window from the panel.
     *
     * @param index The nth element.
     * @return The found window
     * @throws AutomationException Something went really wrong.
     */
    public AutomationWindow getWindow(final int index)
            throws AutomationException {
    	return new AutomationWindow(
    	        new ElementBuilder(
    	                this.getElementByControlType(index, ControlType.Window)));
    }

    /**
     * Gets a window from the panel.
     *
     * @param name Name of the control.
     * @return The found window.
     * @throws AutomationException Something went really wrong.
     */
    public AutomationWindow getWindow(final String name)
            throws AutomationException {
        return new AutomationWindow(
                new ElementBuilder(
                        this.getElementByControlType(name, ControlType.Window)));
    }

    /**
     * Gets a window from the panel.
     *
     * @param namePattern pattern matching the name of the control.
     * @return The found window.
     * @throws AutomationException Something went really wrong.
     */
    public AutomationWindow getWindow(final Pattern namePattern)
            throws AutomationException {
        return new AutomationWindow(
                new ElementBuilder(
                        this.getElementByControlType(namePattern, ControlType.Window)));
    }

    /**
     * Gets the window associated with the given automation id.
     *
     * @param id The id to use.
     * @return The found window.
     * @throws AutomationException Something has gone wrong.
      */
    public AutomationWindow getWindowByAutomationId(final String id)
            throws AutomationException {
        return new AutomationWindow(
                new ElementBuilder(
                        this.getElementByAutomationId(id, ControlType.Window)));
    }

    /**
     * Gets the window, using the search criteria.
     * @param search Matcher for the control
     * @return The found control
     * @throws AutomationException Something has gone wrong
     */
    public AutomationWindow getWindow(final Search search)
            throws AutomationException {
        if (search.getHasNamePattern()) {
            return getWindow(search.getNamePattern());
        } else if (search.getHasAutomationId()) {
            return getWindowByAutomationId(search.getAutomationId());
        } else if (search.getHasIndex()) {
            return getWindow(search.getIndex());
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
     */
    public AutomationWindow getMDIWindow(final Search search)
            throws AutomationException {
        if (search.getHasIndex()) {
            return getWindow(search.getIndex());
        } else {
            throw new AutomationException("Search type not found");
        }
    }
}
