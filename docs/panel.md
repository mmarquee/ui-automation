Panels (or Panes) are generic containers for other controls.

They can be found using an index, or by the name, as shown in this example.

```java
  AutomationPanel pane = window.getPanel("Document1");
  logger.info(pane.name());
  logger.info(pane.getClassName());
  AutomationPanel pane1 = pane.getPanel(0);
  logger.info(pane1.name());
```