package mmarquee.automation;

import mmarquee.automation.pattern.*;
import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 16/02/2016.
 *
 * Needs work to get working
 */
public class AutomationDocument extends AutomationBase {
    private Text textPattern;
    //private ValuePattern valuePattern;

    public AutomationDocument(IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        try {
            this.textPattern = this.getTextPattern();
        } catch (PatternNotFoundException ex) {
            // Smother???
        }
    //    this.valuePattern = this.getValuePattern();
    }

//    public void setText(String text) {
//        this.textPattern..setValue(text);
 //   }

    public String getText() {
        return this.textPattern.getText();
    }
}
