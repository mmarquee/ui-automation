## Element focus

This example will periodically (every 1.5 seconds) display the element that has focus.

```
  UIAutomation automation = UIAutomation.getInstance();

  do {
    this.rest();

    try {
      AutomationElement elementFocus = automation.getFocusedElement();
      logger.info("From focus = " + elementFocus.getName());

    } catch (Exception ex) {
      logger.info(ex.getStackTrace());
    }
  } while (true);
```

# Real World Examples

These applications have example code to show then working, in the mmarquee.demo folder / namespace. 

* [Excel](excel.md)
* [Word](word.md)
* [Powerpoint](powerpoint.md)
* [Notepad](notepad.md)
* [Explorer](explorer.md)


### These do not yet have examples
* [Chrome](chrome.md)
* [Swing](swing.md)
* [SWT](swt.md)
