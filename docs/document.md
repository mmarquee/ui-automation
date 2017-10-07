# Discovery 
* See [Element Discovery](element-discovery.md)

```
  AutomationDocument document = applicationWindow.getDocument(0);

  logger.info("Document name is `" + document.name() + "`");
  logger.info("Text is " + document.getText());

  String result = document.getSelection();
  
  logger.info("Selection is " + result);
```