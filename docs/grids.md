# Discovery 
* See [Element Discovery](element-discovery.md)

## WFP
```
  AutomationDataGrid grid = window.getDataGrid(Search.getBuilder(0).build());
  AutomationDataGridCell cell = grid.getItem(0,0);
  String itemName = item.name();
```

## Delphi

The [DelphiUIAutomation](https://github.com/markhumphreysjhc/DelphiUIAutomation) project introduced some Delphi controls that implement IUIAutomation providers, allowing them to be accessed by automation. The TAutomatedStringGrid is one of these, as the base Delphi (as of XE5 at least) control does not implement the Grid or Table interfaces and so is opaque to automation. In order to get the element associated with the specific TAutomationStringGrid, then this is done in the following manner.

```
  AutomationDataGrid grid = window.getDataGrid(Search.getBuilder(0).className("TAutomationStringGrid").build());
  AutomationDataGridCell item = grid.getItem(0,0);
  String itemName = item.name();
```

This specifically looks controls with a control name of "TAutomationStringGrid", which is the name of the Delphi control introduced in the above project.

## Further Examples
* [Excel](Excel)
