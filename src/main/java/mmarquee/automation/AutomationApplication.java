package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationApplication
        extends AutomationBase
        implements IAutomationApplication {

    private Process process;

    public void waitWhileBusy() {

    }

    public IAutomationWindow getWindow(String title) {

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

        return new AutomationWindow(this.uiAuto, foundElement);
    }

    public AutomationApplication (IUIAutomation uiAuto, Process process, IUIAutomationElement element) {
        this.process = process;
        this.uiAuto = uiAuto;
        this.element = element;
    }
}
