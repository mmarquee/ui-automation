```java
  AutomationToolBar toolbar = applicationWindow.getToolBar(0);
  logger.info("Toolbar name is " + toolbar.name()); // Blank in default WPF

  AutomationButton btn1 = toolbar.getButton(1);

  if (btn1.isEnabled()) {
    logger.info("btn0 Enabled");
    logger.info(btn1.name());
    btn1.click();
  }

```