The Windows Explorer application has a complex user interface, and the examples below only show some of the possible interactions that are available.

```
  AutomationWindow window = automation.getDesktopWindow("File Explorer");
  window.focus();

  // Get the ribbon, work our way down and click the "Preview Button"
  AutomationRibbonBar ribbon = window.getRibbonBar();
  AutomationRibbonCommandBar commandBar = ribbon.getRibbonCommandBar();
  AutomationRibbonWorkPane pane = commandBar.getRibbonWorkPane();
  logger.info("First work pane is " + pane.name());

  AutomationNUIPane uiPane = pane.getNUIPane(0);
  logger.info("First NUIPane is " + uiPane.name());

  AutomationNetUIHWND uiHWND = uiPane.getNetUIHWND(0);

  AutomationButton btn = uiHWND.getButton("Minimise the Ribbon");

  AutomationTab tab = uiHWND.getTab(0);
  tab.selectTabPage("View");

  AutomationPanel panel = uiHWND.getPanel("Lower Ribbon");
  AutomationToolBar panes = panel.getToolBar("Panes");

  panes.getButton("Preview pane").click();
  AutomationSplitButton split = panes.getSplitButton("Navigation pane");
  split.click();
```