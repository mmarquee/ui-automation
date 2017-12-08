# Discovery 
* See [Element Discovery](element-discovery.md)

```
AutomationCheckBox check = window.getCheckBox(Search.getBuilder(0).build());
check.toggle();

try {
  ToggleState state = check.getToggleState();
  logger.info("State: " + state);
} catch (Exception ex) {
  logger.info("Failed to get toggle state");
}
```

# Methods

## Name

Gets the name of the button control

## Value

Gets the value of the button, i.e. the text associated with it

## Toggle
## ToggleState
