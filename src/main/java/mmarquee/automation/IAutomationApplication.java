package mmarquee.automation;

/**
 * Created by inpwt on 26/01/2016.
 */
public interface IAutomationApplication {
    void waitWhileBusy();

    IAutomationWindow getWindow(String name);
}
