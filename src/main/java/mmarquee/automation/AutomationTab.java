package mmarquee.automation;

import mmarquee.automation.uiautomation.IUIAutomation;
import mmarquee.automation.uiautomation.IUIAutomationElement;
import mmarquee.automation.uiautomation.IUIAutomationElementArray;
import mmarquee.automation.uiautomation.TreeScope;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 26/01/2016.
 */
public class AutomationTab extends AutomationContainer implements IAutomationTab {

    private List<IAutomationTabItem> tabItems;

    public void selectTabPage(String name) {
        for (int count = 0; count < this.tabItems.size(); count++) {
            if (name.equals(this.tabItems.get(count).name())) {
                this.tabItems.get(count).select();
            }
        }
    }

    /*
    procedure TAutomationTab.SelectTabPage(const value: string);
var
  count : integer;

begin
  for count := 0 to self.FTabItems.Count -1 do
  begin
    if self.FTabItems[count].Name = value then
    begin
      self.FTabItems[count].Select;
//      FSelectedItem := self.FTabItems[count];
      break;
    end;
  end;
end;
     */
    public IAutomationEditBox setEditBoxByIndex(int index) {
        return new AutomationEditBox();
    }

    public AutomationTab (IUIAutomation uiAuto, IUIAutomationElement element) {
        this.element = element;
        this.uiAuto = uiAuto;

        // Now get the list of tab items
        tabItems = new ArrayList<IAutomationTabItem>();

        IUIAutomationElementArray collection = this.findAll(TreeScope.TreeScope_Descendants);

        int length = collection.length();

        for (int count = 0; count < length; count++ ) {
            IUIAutomationElement elem = collection.getElement(count);

            int retVal = elem.currentControlType();

            if (retVal == ControlTypeID.TabItemControlTypeId) {
                this.tabItems.add(new AutomationTabItem(this.uiAuto, elem));
            }
        }
    }

}
