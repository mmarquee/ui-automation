There appear to be 2 different types of menus, depending on the framework that is being used, MenuBar and Menu based, VCL/Delphi and 'classic' windows applications menus start with a MenuBar. where as .NET applications start with Menus. Examples are shown below and in the examples provided.

### Delphi / VCL Menus

VCL menus are rather tricky, as they need to be expanded and collapsed before the menuitems can be seen via automation, also these menu items do not belong to the parent item, but the overall menu.

The example below shows the current (as of 25/04/2016) support for 2 level menus. If either of these text items are not found the ElementNotFoundException exception will be thrown.

```
  try {
    AutomationMenuItem exit = menu.getMenuItem("File", "Exit");
    exit.click();
  } catch (ElementNotFoundException ex) {
    ..
  }
```

### WPF Menus

```
  AutomationMainMenu mainMenu = window.getMenu(0);

  // Get the first menu item (i.e. "File")
  AutomationMenuItem file = mainMenu.getItems().get(0);
  file.expand();

  // A short wait for the expand to work
  try {
    Thread.sleep(500);
  } catch (Exception ex) {
    logger.info("Interrupted");
  }

  // Look for a specific menu item (in this case 'exit' is the 4th entry)
  AutomationMenuItem exit = file.getItems().get(3);

  exit.click();
```

## Popup Menus
A popup menu is just another menu, and can be accessed in the same manner. In the WPF example, there is a button that has an associated context menu, the code below illustrates how to get at this menu and associated menu items

```
  AutomationMouse mouse = new AutomationMouse();
  mouse.setLocation(1119, 896);
  mouse.rightClick();

  // Wait to make sure menu is displayed
  try {
    Thread.sleep(500);
  } catch (Exception ex) {
    logger.info("Interrupted");
  }

  // Should be able to get the popup menu here
  AutomationMenu popup = window.getMenu(0)
  
```