# Discovery 
* See [Element Discovery](element-discovery.md)

# Example

```
  AutomationToolBar toolbar = applicationWindow.getToolBar(Search.getBuilder(0).build());
  logger.info("Toolbar name is " + toolbar.name()); // Blank in default WPF

  AutomationToolBarButton btn0 = toolbar.getToolbarButton(Search.getBuilder(0).build());

  if (btn0.isEnabled()) {
    logger.info("btn0 Enabled");
    logger.info(btn1.name());
    btn1.click();
  }
```

## Toolbar buttons

The above example shows getting a button from the toolbar, and checking for status, etc.

When a Delphi toolbar has no imagelist associated with it, then the buttons show in automation as menu items, and will correctly call the Invoke pattern's Invoke method, but when the toolbar as images associated with it, then they show as Buttons, but don't seem to implement the Invoke pattern correctly, and so don't fire the event in the underlying control. The same functionality works for WPF.
This appears not to be a library issue as it also does not work from UISpy.
