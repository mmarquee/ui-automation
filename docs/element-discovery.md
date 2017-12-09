Most elements, will sit within a container, either a window or a pane, etc. Once the container has been found, the desired element can be found using one of three methods. 

THere are couple of ways to find elements, either through the consistent Search API, or a more traditional set of APIs.

# Search API
In order to have a consistent search API, the criteria are built using a builder, allowing the following search criteria to be added (note that not all combinations will return values for specific elements)

* name
* regex name pattern
* index
* automation id
* classname
* controlname

These can be combined, as shown in these examples

```
  tab.getTextBox(Search.getBuilder("AutomatedCombobox1").className("MyClassName").build()).getValue();
```

:Name

Here a text box is found using it's control name.

```
  String text = tab.getTextBox(Search.getBuilder("AutomatedCombobox1").build()).getValue();
  logger.info("Text for textBox1 is " + text);
```

:Automation Id

Here a button is found using it's automation id. Some controls, especially Delphi Win32 controls may not set the automation id to a useful value, so this method will not be usable for those controls.

Note that the automation id is an 'extra' search criteria, and has to be 'chained' with an existing set of search criteria, as shown below.

```
  // Get button by automation id
  AutomationButton button1 = window.getButtonSearch.getBuilder().automationId("AutomatedCombobox1").build());
  button1.click();
```

:Index

The 'last resort' is to find the control by an index. This will be the index of that control type within the given container.

The example shows a button being located by it's index. This will be the first button element in the overall window that contains the button.

```
  // Get button by index
  AutomationButton button1 = window.getButton(Search.getBuilder(0).build());
  button1.click();
```

#'Traditional' Find API

Each element also has a more specific find API, allowing specific functions to find specific element type, for example.

## Index
```
  // Get button by index
  AutomationButton button1 = window.getButton(0);
  button1.click();
```

## Name
```
  // Get button by name
  AutomationButton button1 = window.getButton("Button1");
  button1.click();
```

## AutomationId
```
  // Get button by automationId
  AutomationButton button1 = window.getButtonByAutomationId("ButtonId");
  button1.click();
```

## Regex pattern

```
  // Get button by regex pattern
  AutomationButton button1 = window.getButton(Pattern.compile("myN.*"));
  button1.click();
```
 