Buttons can be located by index, name or automation id.

```java
  // Get button by index
  AutomationButton button1 = window.getButton(0);
  button1.click();
```

```java
  // Get button by name
  AutomationButton button1 = window.getButton("OK");
  button1.click();
```

```java
  // Get button by automation id
  AutomationButton button1 = window.getButtonByAutomationId("OK");
  button1.click();
```