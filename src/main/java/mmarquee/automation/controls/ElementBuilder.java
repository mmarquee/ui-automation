package mmarquee.automation.controls;

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
}
