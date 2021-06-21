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

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinNT;
import mmarquee.automation.Element;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.BasePattern;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * The element builder.
 *
 * @author Mark Humphreys
 * Date 20/11/2017
 */
public class ElementBuilder {
    /** The Element. */
    private Element element;

    /** The automation instance. */
    private UIAutomation instance;

    /** The parent element. */

    private Element parent;

    /** Predefined automation patterns (for testing purposes). */
    protected final Set<BasePattern> automationPatterns = new HashSet<>();

    /** The handle. */
    private WinNT.HANDLE handle;

    /** The process. */
    private Process process;


    /** The application path. */
    private String path;

    /** The application arguments. */
    private String arguments;

    /** Attached. */
    private boolean attached;

    /**
     * The user32 instance.
     */
    private User32 user32;

    /**
     * Constructor with an instance.
     *
     * @param inInstance The element
     */
    public ElementBuilder(final UIAutomation inInstance) {
        this.initialise();
        this.instance = inInstance;
    }

    /**
     * Constructor with an element.
     *
     * @param inElement The element
     */
    public ElementBuilder(final Element inElement) {
        this.initialise();
        this.element = inElement;
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
        this.path = "";
        this.arguments = "";
    }

    /**
     * Base constructor.
     */
    public ElementBuilder() {
        this.initialise();
    }

    /**
     * Create a ElementBuilder with a handle.
     * @param inHandle The handle
     * @return The ElementBuilder
     */
    public ElementBuilder handle(final WinNT.HANDLE inHandle) {
        this.handle = inHandle;
        return this;
    }

    /**
     * Create a ElementBuilder with a process.
     * @param inProcess The process
     * @return The ElementBuilder
     */
    public ElementBuilder process(final Process inProcess) {
        this.process = inProcess;
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
     * Create a ElementBuilder with a path (this only make sense for
     * applications).
     * @param inPath The path
     * @return The ElementBuilder
     */
    public ElementBuilder applicationPath(final String inPath) {
        this.path = inPath;

        return this;
    }

    public ElementBuilder applicationArguments(final String inArguments) {
        this.arguments = inArguments;

        return this;
    }

    /**
     * Create a ElementBuilder with a parent.
     * @param inParent The parent
     * @return The ElementBuilder
     */
    public ElementBuilder parent(final Element inParent) {
        this.parent = inParent;
        return this;
    }

    /**
     * Create a ElementBuilder with an Element.
     * @param inElement The Element
     * @return The ElementBuilder
     */
    public ElementBuilder element(final Element inElement) {
        this.element = inElement;
        return this;
    }

    /**
     * Create a ElementBuilder with a User32.
     * @param inUser32 The User32
     * @return The ElementBuilder
     */
    public ElementBuilder user32(final User32 inUser32) {
        this.user32 = inUser32;
        return this;
    }

    /**
     * Create a ElementBuilder with an UIAutomation instance.
     * @param inAutomation The UIAutomation instance
     * @return The ElementBuilder
     */
    public ElementBuilder automation(final UIAutomation inAutomation) {
        this.instance = inAutomation;
        return this;
    }

    /**
     * Create a ElementBuilder with the attached setting.
     * @param inAttached The attached value
     * @return The ElementBuilder
     */
    public ElementBuilder attached(final boolean inAttached) {
        this.attached = inAttached;
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
    public Element getElement() {
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
     * Gets the path (only for applications).
     * @return The configured path
     */
    public String getPath() {
        return this.path;
    }

    public String getArguments() {
        return this.arguments;
    }

    /**
     * Gets the parent.
     * @return The parent
     */
    public Element getParent() {
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

    /**
     * Has a path.
     * @return true if path is present
     */
    public boolean getHasPath()  {
        return !this.path.isEmpty();
    }

    /**
     * Has arguments.
     * @return true if arguments are present
     */
    public boolean getHasArguments() {
        return !this.arguments.isEmpty();
    }
}
