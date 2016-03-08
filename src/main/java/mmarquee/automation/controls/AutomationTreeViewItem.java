package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.pattern.Invoke;
import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.SelectionItem;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 20/02/2016.
 */
public class AutomationTreeViewItem extends AutomationBase {

    private SelectionItem selectItemPattern;
    private Invoke invokePattern;

    public AutomationTreeViewItem(AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        try {
            this.selectItemPattern = this.getSelectItemPattern();
        } catch (PatternNotFoundException ex) {
            logger.info("Failed to find selectitem pattern");
        }

        try {
            this.invokePattern = this.getInvokePattern();
        } catch (PatternNotFoundException ex) {
            logger.info("Failed to find invoke pattern");
        }
    }

    /**
     * Select the item
     */
    public void select() {
        this.selectItemPattern.select();
    }

    /**
     * Click the item
     */
    public void click() {
        this.invokePattern.invoke();
    }
}
