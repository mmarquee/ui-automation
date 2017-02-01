Word has a nice control hierarchy, and uses 'Pages' which are Custom controls.

![UISpy for Word](https://github.com/mmarquee/ui-automation/blob/gh-pages/word.png)

```java
AutomationWindow window = application.getWindow("Document1 - Word");
logger.info(window.name());

AutomationPanel pane = window.getPanel("Document1");
logger.info(pane.name());
logger.info(pane.getClassName());
AutomationPanel pane1 = pane.getPanel(0);
logger.info(pane1.name());

AutomationDocument doc = pane1.getDocument(0);
logger.info(doc.name());

AutomationDocumentPage page0 = doc.getPage(0);
logger.info(page0.name());

AutomationEditBox edit = page0.getEditBox(0);
logger.info(edit.name());

String text = edit.getText();
logger.info("Text = " + text.substring(0,10));
```