package mmarquee.automation.controls;

import mmarquee.automation.AutomationElement;
import mmarquee.automation.controls.AutomationBase;
import mmarquee.automation.pattern.*;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 16/02/2016.
 *
 * Needs work to get working
 */
public class AutomationDocument extends AutomationBase {
    private Text textPattern;

    /**
     * Constructor for the AutomationDocument
     * @param element The underlying automation element
     * @param uiAuto The automation library
     */
    public AutomationDocument(AutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        try {
            this.textPattern = this.getTextPattern();
        } catch (PatternNotFoundException ex) {
            // Smother - really???
        }
    //    this.valuePattern = this.getValuePattern();
    }

    public String getText() {
        return this.textPattern.getText();
    }
}
