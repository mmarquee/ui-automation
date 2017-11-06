# Discovery 
* See [Element Discovery](element-discovery.md)

# Example

```
  AutomationStatusBar statusBar = window.getStatusBar();
  AutomationTextBox tb1 = statusBar.getTextBox(1);

  String eb1Text = tb1.getValue();
  logger.info("Status Bar text = " + eb1Text);
```