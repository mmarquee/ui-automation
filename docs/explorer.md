The Windows Explorer application has a complex user interface, and the examples below only show some of the possible interactions that are available.

```
  Window window = automation.getDesktopWindow("File Explorer");
  window.focus();

  // Get the ribbon, work our way down and click the "Preview Button"
  RibbonBar ribbon = window.getRibbonBar();
  RibbonCommandBar commandBar = ribbon.getRibbonCommandBar();
  RibbonWorkPane pane = commandBar.getRibbonWorkPane();
  logger.info("First work pane is " + pane.name());

  NUIPane uiPane = pane.getNUIPane(Search.getBuilder(0).build());
  logger.info("First NUIPane is " + uiPane.name());

  NetUIHWND uiHWND = uiPane.getNetUIHWND(Search.getBuilder(0).build());

  Button btn = uiHWND.getButton("Minimise the Ribbon");

  Tab tab = uiHWND.getTab(Search.getBuilder(0).build());
  tab.selectTabPage("View");

  Panel panel = uiHWND.getPanel(Search.getBuilder("Lower Ribbon").build());
  ToolBar panes = panel.getToolBar("Panes");

  panes.getButton("Preview pane").click();
  SplitButton split = panes.getSplitButton(Search.getBuilder(""Navigation pane").build());
  split.click();
```