# Discovery 
* See [Element Discovery](element-discovery.md)

```
AutomationCheckBox check = window.getCheckBox(0);
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
## Value
## Toggle
## ToggleState