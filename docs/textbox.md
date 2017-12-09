# Discovery 
* See [Element Discovery](element-discovery.md)

# Example

```
AutomationTextBox tb0 = applicationWindow.getTextBox(Search.getBuilder(9).build());
String tb0Text = tb0.getValue();
logger.info("Text for text box 1 is " + tb0Text);
```