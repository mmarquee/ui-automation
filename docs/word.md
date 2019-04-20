Word has a nice control hierarchy, and uses 'Pages' which are Custom controls.

![UISpy for Word](images/word.png)

```
Window window = application.getWindow(Search.getBuilder("Document1 - Word").build());
logger.info(window.name());

Panel pane = window.getPanel(Search.getBuilder("Document1").build());
logger.info(pane.name());
logger.info(pane.getClassName());
Panel pane1 = pane.getPanel(Search.getBuilder(0).build());
logger.info(pane1.name());

Document doc = pane1.getDocument(Search.getBuilder(0).build());
logger.info(doc.name());

DocumentPage page0 = doc.getPage(Search.getBuilder(0).build());
logger.info(page0.name());

EditBox edit = page0.getEditBox(Search.getBuilder(0).build());
logger.info(edit.name());

String text = edit.getText();
logger.info("Text = " + text.substring(0,10));
```