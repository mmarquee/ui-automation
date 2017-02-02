```java
  AutomationToolBar toolbar = applicationWindow.getToolBar(0);
  logger.info("Toolbar name is " + toolbar.name()); // Blank in default WPF

  AutomationToolBarButton btn0 = toolbar.getToolbarButton(0);

  if (btn0.isEnabled()) {
    logger.info("btn0 Enabled");
    logger.info(btn1.name());
    btn1.click();
  }
```

## Toolbar buttons

The above example shows getting a button from the toolbar, and checking for status, etc.

A caveat here is that the buttons associated with the Delphi VCL toolbar don't seem to implement the invoke pattern correctly, and so don't fire the event in the underlying control. The same functionality works for WPF.
This appears not to be a library issue as it also does not work from UISpy.