/*
 * Copyright 2017 inpwtepydjuf@gmail.com
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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinNT;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.BasePattern;

/**
 * The element builder.
 *
 * @author Mark Humphreys
 * Date 20/11/2017
 */
public class ElementBuilder {
    /** The AutomationElement. */
    private AutomationElement element;

    /** The automation instance. */
    private UIAutomation instance;

    /** The parent element. */

    private AutomationElement parent;

    /** Predefined automation patterns (for testing purposes). */
    protected final Set<BasePattern> automationPatterns = new HashSet<>();

    /** The handle. */
    private WinNT.HANDLE handle;

    /** The process. */
    private Process process;

    /** Attached. */
    private boolean attached;

    /**
     * The user32 instance.
     */
    private User32 user32;

    /**
     * Constructor with an instance.
     *
     * @param instance The element
     */
    public ElementBuilder(UIAutomation instance) {
        this.initialise();
        this.instance = instance;
    }

    /**
     * Constructor with an element.
     *
     * @param element The element
     */
    public ElementBuilder(AutomationElement element) {
        this.initialise();
        this.element = element;
    }

    /**
     * Initialise the builder.
     */
    private void initialise() {
        this.element = null;
        this.automationPatterns.clear();
        this.instance = null;
        this.user32 = null;
        this.handle = null;
        this.process = null;
        this.parent = null;
    }

    /**
     * Base constructor.
     */
    public ElementBuilder() {
        this.initialise();
    }

    /**
     * Create a ElementBuilder with a handle.
     * @param handle The handle
     * @return The ElementBuilder
     */
    public ElementBuilder handle(WinNT.HANDLE handle) {
        this.handle = handle;
        return this;
    }

    /**
     * Create a ElementBuilder with a process.
     * @param process The process
     * @return The ElementBuilder
     */
    public ElementBuilder process(Process process) {
        this.process = process;
        return this;
    }

    /**
     * Create an ElementBuilder with the given pattern.
     * @param patterns The pattern to support
     * @return The ElementBuilder
     */
    public ElementBuilder addPattern(final BasePattern... patterns) {
        for (final BasePattern pattern: patterns) {
            this.automationPatterns.add(pattern);
        }
        return this;
    }

    /**
     * Create a ElementBuilder with a parent.
     * @param parent The parent
     * @return The ElementBuilder
     */
    public ElementBuilder parent(AutomationElement parent) {
        this.parent = parent;
        return this;
    }

    /**
     * Create a ElementBuilder with an AutomationElement.
     * @param element The AutomationElement
     * @return The ElementBuilder
     */
    public ElementBuilder element(AutomationElement element) {
        this.element = element;
        return this;
    }

    /**
     * Create a ElementBuilder with a User32.
     * @param user32 The User32
     * @return The ElementBuilder
     */
    public ElementBuilder user32(User32 user32) {
        this.user32 = user32;
        return this;
    }

    /**
     * Create a ElementBuilder with an UIAutomation instance.
     * @param automation The UIAutomation instance
     * @return The ElementBuilder
     */
    public ElementBuilder automation(UIAutomation automation) {
        this.instance = automation;
        return this;
    }

    /**
     * Create a ElementBuilder with the attached setting.
     * @param attached The attached value
     * @return The ElementBuilder
     */
    public ElementBuilder attached(boolean attached) {
        this.attached = attached;
        return this;
    }

    /**
     * Gets the automation instance.
     * @return The automation instance
     */
    public UIAutomation getInstance() {
        return this.instance;
    }

    /**
     * Gets the attached flag.
     * @return True if set
     */
    public boolean getAttached() {
        return this.attached;
    }

    /**
     * Gets the user32 instance.
     * @return The instance
     */
    public User32 getUser32() {
        return this.user32;
    }

    /**
     * Gets the provided pattern.
     * @return The provided pattern
     */
    public Collection<BasePattern> getAutomationPatterns() {
        return this.automationPatterns;
    }

    /**
     * Gets the element handle.
     * @return The element handle
     */
    public WinNT.HANDLE getHandle() {
        return this.handle;
    }

    /**
     * The element itself.
     * @return The element
     */
    public AutomationElement getElement() {
        return this.element;
    }

    /**
     * Gets the process.
     * @return The process
     */
    public Process getProcess() {
        return this.process;
    }

    /**
     * Gets the parent.
     * @return The parent
     */
    public AutomationElement getParent() {
        return this.parent;
    }

    /**
     * Has automation.
     * @return true if present
     */
    public boolean getHasAutomation() {
        return this.instance != null;
    }

    /**
     * Has User32.
     * @return true if present
     */
    public boolean getHasUser32() {
        return this.user32 != null;
    }

    /**
     * Has handle.
     * @return true if present
     */
    public boolean getHasHandle() {
        return this.handle != null;
    }
}
