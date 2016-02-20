package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 20/02/2016.
 */
public class AutomationTreeViewItem extends AutomationBase {

    private IUIAutomationSelectionItemPattern selectItemPattern;

    public AutomationTreeViewItem(IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        this.selectItemPattern = this.getSelectItemPattern();
    }

    public void select() {
        this.selectItemPattern.select();
    }
}
