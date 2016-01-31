package mmarquee.automation;

import mmarquee.automation.uiautomation.*;

/**
 * Created by inpwt on 28/01/2016.
 */
public class AutomationContainer extends AutomationBase {
    protected IUIAutomationElement getControlByControlType(int index, int id) {
        IUIAutomationElementArray collection;

        IUIAutomationElement foundElement = null;

        collection = this.findAll(TreeScope.TreeScope_Descendants);

        int length = collection.length();

        int counter = 0;

        for (int count = 0; count < length; count++) {
            IUIAutomationElement element = collection.getElement(count);
            int retVal = element.currentControlType();

            if (retVal == id)  {
                if (counter == index) {
                    foundElement = element;
                } else {
                    counter++;
                }
            }
        }

        return foundElement;
    }

    /*
    function TAutomationContainer.GetControlByControlType(title: string; id: word): IUIAutomationElement;
var
  element : IUIAutomationElement;
  collection : IUIAutomationElementArray;
  count : integer;
  name : widestring;
  length : integer;
  retVal : integer;

begin
  result := nil;

  // Find the element
  collection := FindAll(TreeScope_Descendants);

  collection.Get_Length(length);

  for count := 0 to length -1 do
  begin
    collection.GetElement(count, element);
    element.Get_CurrentControlType(retVal);

    if (retval = id) then
    begin
      element.Get_CurrentName(name);

      if (name = title)then
      begin
        result := element;
        break;
      end;
    end;
  end;

  if result = nil then
    raise EDelphiAutomationException.Create('Unable to find control');

end;

     */

    public IAutomationTab getTabByIndex(int index){
        return new AutomationTab(this.uiAuto, this.getControlByControlType(index, ControlTypeID.TabControlTypeId));
    }

    public IAutomationEditBox getEditBoxByIndex(int index) {
        return new AutomationEditBox(this.uiAuto, this.getControlByControlType(index, ControlTypeID.EditControlTypeId));
    }

}
