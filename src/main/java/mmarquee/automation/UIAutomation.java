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

    public AutomationApplication launch(String... command) {
        ProcessBuilder pb = new ProcessBuilder(command);

        try {
            process = pb.start();
        } catch (java.io.IOException ex) {
            // Never do this in real code
        }

        return new AutomationApplication(rootElement, uiAuto, process);
    }

    public AutomationWindow getDesktopWindow(String title) {
        List<AutomationWindow> result = new ArrayList<AutomationWindow>();

        IUIAutomationCondition condition = uiAuto.createTrueCondition();

        IUIAutomationElementArray collection = this.rootElement.findAll(TreeScope.TreeScope_Children, condition);

        int length = collection.length();

        AutomationWindow foundElement = null;

        for (int count = 0; count < length; count++) {
            IUIAutomationElement element = collection.getElement(count);
            String name = element.currentName();

            if (name.equals(title)) {
                foundElement = new AutomationWindow(element, this.uiAuto);
            }
        }

        return foundElement;
    }

    public List<AutomationWindow> getDesktopWindows() {

        List<AutomationWindow> result = new ArrayList<AutomationWindow>();

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
