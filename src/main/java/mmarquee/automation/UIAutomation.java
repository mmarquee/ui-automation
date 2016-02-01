package mmarquee.automation;

import mmarquee.automation.uiautomation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HumphreysM on 26/01/2016.
 */
public class UIAutomation {

    private IUIAutomationElement rootElement;

    private IUIAutomation uiAuto;

    private Process process;

    public UIAutomation() {

        uiAuto = ClassFactory.createCUIAutomation();

        rootElement = uiAuto.getRootElement();
    }

    public IAutomationApplication launch(String... command) {
        ProcessBuilder pb = new ProcessBuilder(command);

        try {
            process = pb.start();
        } catch (java.io.IOException ex) {
            // Never do this in real code
        }

        // OK, we have started the application, so how do we actually get the firt element in the 'tree' of controls?
        // the Delphi code semms to get passed this by now getting the list of dekstop windows and finding the one we
        // want, but this is a bit odd, and not sure whether this is actually how it SHOULD work at all.


        return new AutomationApplication(rootElement, uiAuto, process);
    }

    public IAutomationWindow getDesktopWindow(String title) {
        List<IAutomationWindow> result = new ArrayList<IAutomationWindow>();

        IUIAutomationCondition condition = uiAuto.createTrueCondition();

        IUIAutomationElementArray collection = this.rootElement.findAll(TreeScope.TreeScope_Children, condition);

        int length = collection.length();

        IAutomationWindow foundElement = null;

        for (int count = 0; count < length; count++) {
            IUIAutomationElement element = collection.getElement(count);
            String name = element.currentName();

            if (name.equals(title)) {
                foundElement = new AutomationWindow(element, this.uiAuto);
            }
        }

        return foundElement;
    }

    public List<IAutomationWindow> getDesktopWindows() {

        List<IAutomationWindow> result = new ArrayList<IAutomationWindow>();

        IUIAutomationCondition condition = uiAuto.createTrueCondition();

        IUIAutomationElementArray collection = this.rootElement.findAll(TreeScope.TreeScope_Children, condition);

        int length = collection.length();

        for (int count = 0; count < length; count++) {
            IUIAutomationElement element = collection.getElement(count);

            result.add(new AutomationWindow(element, this.uiAuto));
        }

        return result;
    }
}
