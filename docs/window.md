* See [Element Discovery](element-discovery.md)

Window is the ultimate container for an application, it may have menus and other control buttons that other controls do not have.

# Methods

## SetTransparency

This sets the `alpha attribute` of the window, allowing the automation API to indicate which window is beig processed (for example).

```
AutomationWindow window = automation.getDesktopWindow("Form1");
window.setTransparency(128);
```