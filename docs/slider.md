# Discovery 
* See [Element Discovery](element-discovery.md)

```
  AutomationSlider slider = applicationWindow.getSlider(Search.getBuilder(0).build());
  logger.info("Slider value = " + slider.getRangeValue());

  // Looks like this does bad things too
  //       progress.setRangeValue(25);
  //       logger.info("Progress is now = " + progress.getRangeValue());
```