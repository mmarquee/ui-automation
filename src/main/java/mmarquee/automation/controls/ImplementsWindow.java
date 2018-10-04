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
import mmarquee.automation.pattern.Window;
import mmarquee.automation.uiautomation.WindowVisualState;

/**
 * Methods to simplify interaction with the Window Pattern.
 *
 * @author Mark Humphreys
 * Date 19/05/2017.
 */
public interface ImplementsWindow extends Automatable, CanRequestBasePattern {

    /**
     * Waits for this window to become idle.
     * @param timeout The timeout
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Failed to find pattern
     */
	default void waitForInputIdle(int timeout) throws AutomationException, PatternNotFoundException {
		final Window windowPattern = requestAutomationPattern(Window.class);
		if (windowPattern.isAvailable()) {
			windowPattern.waitForInputIdle(timeout);
			return;
		}
		throw new PatternNotFoundException("Cannot wait for idle");
    }

    /**
     * Maximize the window.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    default void maximize() throws AutomationException, PatternNotFoundException {
		final Window windowPattern = requestAutomationPattern(Window.class);
		if (windowPattern.isAvailable()) {
			windowPattern.maximize();
			return;
		}
		throw new PatternNotFoundException("Cannot maximize");
    }

    /**
     * Minimize the window.
     * @throws AutomationException Something has gone wrong.
     * @throws PatternNotFoundException Expected pattern not found.
     */
    default void minimize() throws AutomationException, PatternNotFoundException {
		final Window windowPattern = requestAutomationPattern(Window.class);
		if (windowPattern.isAvailable()) {
			windowPattern.minimize();
			return;
		}
		throw new PatternNotFoundException("Cannot minimize");
    }

    /**
     * Closes the window.
     * @throws AutomationException Something has gone wrong
     * @throws PatternNotFoundException Expected pattern not found
     */
    default void close() throws AutomationException, PatternNotFoundException {
		final Window windowPattern = requestAutomationPattern(Window.class);
		if (windowPattern.isAvailable()) {
			windowPattern.close();
			return;
		}
		throw new PatternNotFoundException("Cannot close");
    }

    /**
     * Can the window be maximized.
     * @return True or false
     * @throws AutomationException Something is wrong
     */
    default boolean getCanMaximize() throws AutomationException {
		final Window windowPattern = requestAutomationPattern(Window.class);
		if (windowPattern.isAvailable()) {
			return windowPattern.getCanMaximize();
		}
		throw new PatternNotFoundException("Cannot query for maximisation");
    }

    /**
     * Can the window be minimized.
     * @return True or false
     * @throws AutomationException Something is wrong
     */
    default boolean getCanMinimize() throws AutomationException {
		final Window windowPattern = requestAutomationPattern(Window.class);
		if (windowPattern.isAvailable()) {
			return windowPattern.getCanMinimize();
		}
		throw new PatternNotFoundException("Cannot query for minimization");
    }

    /**
     * Returns whether this control is modal.
     * @return Is this control modal?
     * @throws AutomationException Something has gone wrong
     */
    default boolean isModal() throws AutomationException {
		final Window windowPattern = requestAutomationPattern(Window.class);
		if (windowPattern.isAvailable()) {
			return windowPattern.isModal();
		}
		throw new PatternNotFoundException("Cannot query for modal state");
    }

    /**
     * IS this window topmost.
     * @return Is the window topmost
     * @throws AutomationException Something has gone wrong
     */
    default boolean isTopMost() throws AutomationException {
		final Window windowPattern = requestAutomationPattern(Window.class);
		if (windowPattern.isAvailable()) {
			return windowPattern.isTopMost();
		}
		throw new PatternNotFoundException("Cannot query for topmost state");
    }

    /**
     * Sets the visual state.
     * @param state The state to set
     * @throws AutomationException Something has gone wrong
     */
    default void setWindowState(WindowVisualState state) throws AutomationException {
		final Window windowPattern = requestAutomationPattern(Window.class);
		if (windowPattern.isAvailable()) {
			windowPattern.setWindowState(state);
			return;
		}
		throw new PatternNotFoundException("Cannot set window state");
    }
}
