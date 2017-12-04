Notepad is quite a simple application, and the following code is adapted from the demo program.

```
  application = automation.launchOrAttach("notepad.exe");
     
  // Wait for the process to start
  application.waitForInputIdle(5000);
   
  AutomationWindow window = automation.getDesktopWindow("Untitled - Notepad");
  String name = window.name();
  logger.info(name);

  Object framework = window.getFramework();
  logger.info("Framework is " + framework.toString());

  logger.info("Modal = " + window.isModal());

  AutomationEditBox edit = window.getEditBox(Search.getBuilder(0).build());

  edit.setValue("This is a test of automation");

  window.focus();
  window.maximize();

  this.rest();

  // Interact with menus
  AutomationMainMenu menu = window.getMainMenu();
    
  AutomationMenuItem exit = menu.getMenuItem("File", "Exit");
  exit.click();
           
  AutomationWindow popup = window.getWindow("Notepad");
  AutomationButton btn = popup.getButton("Don't Save");

  btn.click();

  logger.info("All closed down now");
             
```