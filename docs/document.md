# Discovery 
* See [Element Discovery](element-discovery.md)

```
  AutomationDocument document = applicationWindow.getDocument(Search.getBuilder(0).build());

  logger.info("Document name is `" + document.name() + "`");
  logger.info("Text is " + document.getText());

  String result = document.getSelection();
  
  logger.info("Selection is " + result);
```