package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.UIAutomation;
import mmarquee.automation.pattern.Invoke;

/**
 * The pattern builder.
 */
public class ElementBuilder {
    private AutomationElement element;
    private UIAutomation instance;
    private Invoke invoke;

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
    }

    /**
     * Constructor with an element.
     */
    public ElementBuilder() {
        this.initialise();
    }

    public ElementBuilder element(AutomationElement element) {
        this.element = element;
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

    public UIAutomation getInstance() {
        return this.instance;
    }

    public Invoke getInvoke() {
        return this.invoke;
    }

    public AutomationElement getElement() {
        return this.element;
    }

    public boolean getHasAutomation() {
        return this.instance != null;
    }

    public boolean getHasInvoke() {
        return this.invoke != null;
    }
}
