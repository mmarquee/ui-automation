package mmarquee.automation.controls;

import com.sun.jna.platform.win32.User32;
import mmarquee.automation.AutomationElement;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.*;

/**
 * The pattern builder.
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

    public ElementBuilder(AutomationElement element) {
        this.initialise();
        this.element = element;
    }

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
    }

    /**
     * Base constructor.
     */
    public ElementBuilder() {
        this.initialise();
    }

    public ElementBuilder range(Range pattern) {
        this.range = pattern;
        return this;
    }

    public ElementBuilder toggle(Toggle pattern) {
        this.toggle = pattern;
        return this;
    }

    public ElementBuilder select(Selection pattern) {
        this.selection = pattern;
        return this;
    }

    public ElementBuilder collapse(ExpandCollapse pattern) {
        this.collapse = pattern;
        return this;
    }

    public ElementBuilder itemContainer(ItemContainer pattern) {
        this.itemContainer = pattern;
        return this;
    }

    public ElementBuilder selectionItem(SelectionItem pattern) {
        this.selectionItem = pattern;
        return this;
    }

    public ElementBuilder element(AutomationElement element) {
        this.element = element;
        return this;
    }

    public ElementBuilder value(Value pattern) {
        this.value = pattern;
        return this;
    }

    public ElementBuilder window(Window pattern) {
        this.windowPattern = pattern;
        return this;
    }

    public ElementBuilder user32(User32 user32) {
        this.user32 = user32;
        return this;
    }

    public ElementBuilder automation(UIAutomation automation) {
        this.instance = automation;
        return this;
    }

    public ElementBuilder invoke(Invoke invoke) {
        this.invoke = invoke;
        return this;
    }

    public ElementBuilder selection(Selection pattern) {
        this.selection = pattern;
        return this;
    }

    public UIAutomation getInstance() {
        return this.instance;
    }

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

    public boolean getHasAutomation() {
        return this.instance != null;
    }

    public boolean getHasInvoke() {
        return this.invoke != null;
    }

    public boolean getHasValue() { return this.value != null; }

    public boolean hasUser32() { return this.user32 != null; }
}
