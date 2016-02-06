package mmarquee.automation;

import mmarquee.automation.uiautomation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by inpwt on 03/02/2016.
 */
public class AutomationStringGrid extends AutomationBase
{

    private IUIAutomationValuePattern valuePattern;
    private IUIAutomationGridPattern gridPattern;
    private IUIAutomationTablePattern tablePattern;
    private IUIAutomationSelectionPattern selectionPattern;

    public AutomationStringGrid(IUIAutomationElement element, IUIAutomation uiAuto) {
        super(element, uiAuto);

        this.valuePattern = this.getValuePattern();
        this.gridPattern = this.getGridPattern();
        this.tablePattern = this.getTablePattern();
        this.selectionPattern = this.getSelectionPattern();
    }

    public String value() {
        return this.valuePattern.currentValue();
    }

    public boolean isReadOnly() {
        return this.valuePattern.currentIsReadOnly() == 1;
    }

    public AutomationStringGridItem selected() {
        IUIAutomationElementArray collection = selectionPattern.getCurrentSelection();

        AutomationStringGridItem item = new AutomationStringGridItem(collection.getElement(0), uiAuto);

        return item;
    }

    public List<AutomationStringGridItem> getColumnHeaders () {

        IUIAutomationElementArray collection = tablePattern.getCurrentColumnHeaders();
        int length = collection.length();

        List<AutomationStringGridItem> items = new ArrayList<AutomationStringGridItem>();

        for (int count = 0; count < length; count++) {
            items.add(new AutomationStringGridItem(collection.getElement(count), uiAuto));
        }

        return items;
    }

    public AutomationStringGridItem getItem(int x, int y) {
        return new AutomationStringGridItem(this.gridPattern.getItem(x, y), uiAuto);
    }
}
