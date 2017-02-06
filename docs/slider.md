```java
  AutomationSlider slider = applicationWindow.getSlider(0);
  logger.info("Slider value = " + slider.getRangeValue());

  // Looks like this does bad things too
  //       progress.setRangeValue(25);
  //       logger.info("Progress is now = " + progress.getRangeValue());
```