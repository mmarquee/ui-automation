Assuming that the application has already been started, the following code shows how to get data out of cells. 

```java            
AutomationWindow window = application.getWindow("Book1 - Excel");
logger.info(window.name());

AutomationPanel panelX = window.getPanelByClassName(0, "XLDESK");
logger.info(panelX.name());
logger.info(panelX.getClassName());

AutomationTab tab = panelX.getTab(0);
logger.info(tab.name());
AutomationDataGrid grid = tab.getDataGrid(0);
logger.info(grid.name());

// Get some data
AutomationDataGridCell cell = grid.getItem(0,0);
logger.info(cell.name());
logger.info(cell.value());
```

NOTE: The call to getPanelByClassName is only available post release 0.3.10