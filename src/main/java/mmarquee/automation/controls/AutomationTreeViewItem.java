package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.ControlType;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.SelectionItem;

/**
 * Created by Mark Humphreys on 20/02/2016.
 *
 * Wrapper for the TreeViewItem element.
 */
public class AutomationTreeViewItem extends AutomationBase implements Selectable, Clickable {

    private SelectionItem selectItemPattern;
    private Invoke invokePattern;

    /**
     * Construct the AutomationTreeViewItem
     * @param element The element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationTreeViewItem(AutomationElement element)
            throws PatternNotFoundException, AutomationException {
        super(element);

        this.selectItemPattern = this.getSelectItemPattern();
        this.invokePattern = this.getInvokePattern();

        controlType = ControlType.TreeItem;
    }

    /**
     * Select the item
     * @throws AutomationException Automation library issue
     */
    public void select() throws AutomationException {
        this.selectItemPattern.select();
    }

    /**
     * Is this item selected?
     * @return True if selected
     * @throws AutomationException Automation library issue
     */
    public boolean isSelected() throws AutomationException {
        return this.selectItemPattern.isSelected();
    }

    /**
     * Click the item
     * @throws AutomationException Automation library issue
     */
    public void click() throws AutomationException {
        if (this.isInvokePatternAvailable()) {
            this.invokePattern.invoke();
        } else {
            logger.warn("Invoke pattern is not available");
        }
    }
}
