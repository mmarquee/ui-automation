package mmarquee.automation;

import mmarquee.automation.pattern.PatternNotFoundException;
import mmarquee.automation.pattern.SelectionItem;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 20/02/2016.
 */
public class AutomationTreeViewItem extends AutomationBase {

    private SelectionItem selectItemPattern;

    public AutomationTreeViewItem(IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        try {
            this.selectItemPattern = this.getSelectItemPattern();
        } catch (PatternNotFoundException ex) {
            // Handle this nicely somehow
        }

    }

    public void select() {
        this.selectItemPattern.select();
    }
}
