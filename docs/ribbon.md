# Discovery 
* See [Element Discovery](element-discovery.md)

The ribbon control is a complex structure, but the tree of controls is navigable, as the snippet below shows (automating the Windows Explorer), finding the button associated with the Preview Pane and clicking on it to turn it on/off.

```
  RibbonBar ribbon = window.getRibbonBar();
  RibbonCommandBar commandBar = ribbon.getRibbonCommandBar();
  RibbonWorkPane pane = commandBar.getRibbonWorkPane();
  logger.info("First work pane is " + pane.name());

  NUIPane uiPane = pane.getNUIPane(Search.getBuilder(0).build());
  logger.info("First NUIPane is " + uiPane.name());

  NetUIHWND uiHWND = uiPane.getNetUIHWND(Search.getBuilder(0).build());
  AutomationButton btn = uiHWND.getButton(Search.getBuilder("Minimise the Ribbon").build());

  Tab tab = uiHWND.getTab(Search.getBuilder(0).build());
  tab.selectTabPage("View");

  Panel panel = uiHWND.getPanel(Search.getBuilder("Lower Ribbon").build());

  ToolBar panes = panel.getToolBar(Search.getBuilder("Panes").build());

  panes.getButton(Search.getBuilder("Preview pane").build()).click();
```