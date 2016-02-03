package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 31/01/2016.
 */
public class AutomationRadioButton extends AutomationBase implements IProvidesSelectItem {

    private IUIAutomationSelectionItemPattern selectItemPattern;

    public AutomationRadioButton(IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        selectItemPattern = this.getSelectItemPattern();
    }

    public void selectItem() {
        this.selectItemPattern.select();
    }
}
