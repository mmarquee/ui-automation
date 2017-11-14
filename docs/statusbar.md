# Discovery 
* See [Element Discovery](element-discovery.md)

# Example

```
  AutomationStatusBar statusBar = window.getStatusBar();
  AutomationTextBox tb1 = statusBar.getTextBox(Search.getBuilder(1).build());

  String eb1Text = tb1.getValue();
  logger.info("Status Bar text = " + eb1Text);
```