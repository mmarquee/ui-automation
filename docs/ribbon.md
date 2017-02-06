The ribbon control is a complex structure, but the tree of controls is navigable, as the snippet below shows (automating the Windows Explorer), finding the button associated with the Preview Pane and clicking on it to turn it on/off.

```java
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
  AutomationToolBar panes = panel.getToolBar("Panes");

  panes.getButton("Preview pane").click();
```