# Discovery 
* See [Element Discovery](element-discovery.md)

```
  AutomationProgressBar progress = applicationWindow.getProgressBar(Search.getBuilder(0).build());
  logger.info("Progress = " + progress.getRangeValue());

  // Looks like this does bad things
  //  progress.setRangeValue(100.0);
  //  logger.info("Progress is now = " + progress.getRangeValue());
```