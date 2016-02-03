package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 28/01/2016.
 */
public class AutomationTabItem extends AutomationContainer implements IProvidesSelectItem {

    private IUIAutomationSelectionItemPattern selectItemPattern;

    public AutomationTabItem(IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        selectItemPattern = this.getSelectItemPattern();
    }

    public void selectItem() {
        this.selectItemPattern.select();
    }
}
