package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.AutomationException;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.SelectionItem;

/**
 * Created by Mark Humphreys on 20/02/2016.
 *
 * Wrapper for the TreeViewItem element.
 */
public class AutomationTreeViewItem extends AutomationBase {

    private SelectionItem selectItemPattern;
    private Invoke invokePattern;

    /**
     * Construct the AutomationTreeViewItem
     * @param element The element
     * @throws AutomationException Automation library error
     * @throws PatternNotFoundException Expected pattern not found
     */
    public AutomationTreeViewItem(AutomationElement element) throws PatternNotFoundException, AutomationException {
        super(element);

        this.selectItemPattern = this.getSelectItemPattern();
        this.invokePattern = this.getInvokePattern();
    }

    /**
     * Select the item
     * @throws AutomationException Automation library issue
     */
    public void select() throws AutomationException {
        this.selectItemPattern.select();
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
