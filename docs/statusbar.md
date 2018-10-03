# Discovery 
* See [Element Discovery](element-discovery.md)

# Example

```
  StatusBar statusBar = window.getStatusBar();
  TextBox tb1 = statusBar.getTextBox(Search.getBuilder(1).build());

  String eb1Text = tb1.getValue();
  logger.info("Status Bar text = " + eb1Text);
```