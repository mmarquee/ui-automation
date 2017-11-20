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
import mmarquee.automation.AutomationElement;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.*;

/**
 * The pattern builder.
 *
 * @author Mark Humphreys
 * Date 20/11/2017
 */
public class ElementBuilder {
    private AutomationElement element;
    private UIAutomation instance;

    private Range range;
    private Value value;
    private Invoke invoke;
    private Toggle toggle;
    private ExpandCollapse collapse;
    private Selection selection;
    private ItemContainer itemContainer;
    private SelectionItem selectionItem;

    private WinNT.HANDLE handle;
    private Process process;
    private boolean attached;

    /**
     * The window pattern.
     */
    private Window windowPattern;

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
        this.instance = null;
        this.invoke = null;
        this.value = null;
        this.toggle = null;
        this.range = null;
        this.itemContainer = null;
        this.selectionItem = null;
        this.windowPattern = null;
        this.user32 = null;
        this.handle = null;
        this.process = null;
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
     * Create a ElementBuilder with a Range pattern.
     * @param pattern The Range pattern
     * @return The ElementBuilder
     */
    public ElementBuilder range(Range pattern) {
        this.range = pattern;
        return this;
    }

    /**
     * Create a ElementBuilder with a Toggle pattern.
     * @param pattern The Toggle pattern
     * @return The ElementBuilder
     */
    public ElementBuilder toggle(Toggle pattern) {
        this.toggle = pattern;
        return this;
    }

    /**
     * Create a ElementBuilder with a Selection pattern.
     * @param pattern The Selection pattern
     * @return The ElementBuilder
     */
    public ElementBuilder select(Selection pattern) {
        this.selection = pattern;
        return this;
    }

    /**
     * Create a ElementBuilder with a ExpandCollapse pattern.
     * @param pattern The ExpandCollapse pattern
     * @return The ElementBuilder
     */
    public ElementBuilder collapse(ExpandCollapse pattern) {
        this.collapse = pattern;
        return this;
    }

    /**
     * Create a ElementBuilder with a ItemContainer pattern.
     * @param pattern The ItemContainer pattern
     * @return The ElementBuilder
     */
    public ElementBuilder itemContainer(ItemContainer pattern) {
        this.itemContainer = pattern;
        return this;
    }

    /**
     * Create a ElementBuilder with a SelectionItem pattern.
     * @param pattern The SelectionItem pattern
     * @return The ElementBuilder
     */
    public ElementBuilder selectionItem(SelectionItem pattern) {
        this.selectionItem = pattern;
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
     * Create a ElementBuilder with a Value pattern.
     * @param pattern The Value pattern
     * @return The ElementBuilder
     */
    public ElementBuilder value(Value pattern) {
        this.value = pattern;
        return this;
    }

    /**
     * Create a ElementBuilder with a Window pattern.
     * @param pattern The Window pattern
     * @return The ElementBuilder
     */
    public ElementBuilder window(Window pattern) {
        this.windowPattern = pattern;
        return this;
    }

    /**
     * Create a ElementBuilder with a User32,
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
     * Create a ElementBuilder with a Invoke pattern.
     * @param invoke The Invoke pattern
     * @return The ElementBuilder
     */
    public ElementBuilder invoke(Invoke invoke) {
        this.invoke = invoke;
        return this;
    }

    /**
     * Create a ElementBuilder with a Selection pattern.
     * @param pattern The Selection pattern
     * @return The ElementBuilder
     */
    public ElementBuilder selection(Selection pattern) {
        this.selection = pattern;
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

    public UIAutomation getInstance() {
        return this.instance;
    }

    public boolean getAttached() { return this.attached; }

    public User32 getUser32() {
        return this.user32;
    }

    public Invoke getInvoke() {
        return this.invoke;
    }

    public Range getRange() {
        return this.range;
    }

    public Value getValue() {
        return this.value;
    }

    public Toggle getToggle() {
        return this.toggle;
    }

    public WinNT.HANDLE getHandle() {
        return this.handle;
    }

    public Window getWindow() {
        return this.windowPattern;
    }

    public SelectionItem getSelectItemPattern() { return this.selectionItem;  }

    public ExpandCollapse getCollapse() { return this.collapse; }

    public Selection getSelection() { return this.selection; }

    public AutomationElement getElement() {
        return this.element;
    }

    public ItemContainer getItemContainer() { return this.itemContainer; }

    public Process getProcess() { return this.process; }

    public boolean getHasAutomation() {
        return this.instance != null;
    }

    public boolean getHasInvoke() {
        return this.invoke != null;
    }

    public boolean getHasValue() { return this.value != null; }

    public boolean getHasUser32() { return this.user32 != null; }

    public boolean getHasHandle() { return this.handle != null; }
}
