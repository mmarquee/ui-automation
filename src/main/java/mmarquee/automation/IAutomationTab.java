package mmarquee.automation;

/**
 * Created by inpwt on 26/01/2016.
 */
public interface IAutomationTab {
    void selectTabPage(String name);
    String name();
    IAutomationEditBox setEditBoxByIndex(int index);
}
