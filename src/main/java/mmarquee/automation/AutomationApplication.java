package mmarquee.automation;

import mmarquee.automation.uiautomation.*;
import com.sun.jna.*;

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationApplication extends AutomationBase {
    private Process process;

    public void waitWhileBusy() {
        //WaitForInputIdle(this.rocess, timeout);
        // Need to solve this !!!

     //   com.sun.jna.win32;

        //win32.User32.INSTANCE.WaitForInputIdle();

        //com.sun.jna.
    }

    public AutomationWindow getWindow(String title) {

        IUIAutomationElement foundElement = null;

        IUIAutomationElementArray collection = this.findAll();

        int length = collection.length();

        for(int count=0; count < length; count++){
            IUIAutomationElement element = collection.getElement(count);
            String name = element.currentName();
            if (name.equals(title)){
                foundElement = element;
            }
        }

        return new AutomationWindow(foundElement, this.uiAuto);
    }

    public AutomationApplication (IUIAutomationElement element, IUIAutomation uiAuto, Process process) {
        super(element, uiAuto);
        this.process = process;
    }
}
