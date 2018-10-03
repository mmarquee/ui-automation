# Discovery 
* See [Element Discovery](element-discovery.md)

```
  ProgressBar progress = applicationWindow.getProgressBar(Search.getBuilder(0).build());
  logger.info("Progress = " + progress.getRangeValue());

  if (progress.getIsReadOnly()) {
    logger.info("Progress range is read-only");
  } else {
    progress.setRangeValue(50.0);
    logger.info("Progress is now = " + progress.getRangeValue());
  }
```