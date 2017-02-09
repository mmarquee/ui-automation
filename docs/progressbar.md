```
  AutomationProgressBar progress = applicationWindow.getProgressBar(0);
  logger.info("Progress = " + progress.getRangeValue());

  // Looks like this does bad things
  //  progress.setRangeValue(100.0);
  //  logger.info("Progress is now = " + progress.getRangeValue());
```