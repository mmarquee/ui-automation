package mmarquee.automation;

/**
 * Created by inpwt on 26/01/2016.
 */
public interface IAutomationWindow {
    void focus();
    IAutomationTab getTabByIndex(int index);
    String name();
}
