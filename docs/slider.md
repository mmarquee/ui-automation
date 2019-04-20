# Discovery 
* See [Element Discovery](element-discovery.md)

# Example

```
  Slider slider = applicationWindow.getSlider(Search.getBuilder(0).build());
  logger.info("Slider value = " + slider.getRangeValue());

  // Set the value
  slider.setRangeValue(20);
  
  // Get the new valueOn
  logger.info("Slider value is now = " + slider.getRangeValue());
```