package mmarquee.automation;

import mmarquee.automation.uiautomation.IUIAutomationEventHandler;

/**
 * Created by Mark Humphreys on 05/02/2017.
 */
public class AutomationEventHandler  {
    private IUIAutomationEventHandler handler;

    public AutomationEventHandler(IUIAutomationEventHandler handler) {
        this.handler = handler;
    }

}
