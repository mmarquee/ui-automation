Most elements, will sit within a container, either a window or a pane, etc. Once the container has been found, the desired element can be found using one of three methods. 

:Name

Here a textbox is found using it's control name.

```
  String text = tab.getTextBox("textBox1").getValue();
  logger.info("Text for textBox1 is " + text);
```

:Automation Id

Here a button is found using it's automation id. Some controls, especially Delphi Win32 controls may not set the automation id to a useful value, so this method will not be usable for those controls.

```
  // Get button by automation id
  AutomationButton button1 = window.getButtonByAutomationId("OK");
  button1.click();
```

:Index

The 'last resort' is to find the control by an index. This will be the index of that control type within the given container.

The example shows a button being located by it's index. This will be the first button element in the overall window that contains the button.

```
  // Get button by index
  AutomationButton button1 = window.getButton(0);
  button1.click();
```