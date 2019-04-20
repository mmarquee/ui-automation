Notepad is quite a simple application, and the following code is adapted from the demo program.

```
  Application application = automation.launchOrAttach("notepad.exe");
     
  // Wait for the process to start
  application.waitForInputIdle(5000);
   
  Window window = automation.getDesktopWindow("Untitled - Notepad");
  String name = window.name();
  logger.info(name);

  Object framework = window.getFramework();
  logger.info("Framework is " + framework.toString());

  logger.info("Modal = " + window.isModal());

  EditBox edit = window.getEditBox(Search.getBuilder(0).build());

  edit.setValue("This is a test of automation");

  window.focus();
  window.maximize();

  this.rest();

  // Interact with menus
  MainMenu menu = window.getMainMenu();
    
  MenuItem exit = menu.getMenuItem("File", "Exit");
  exit.click();
           
  Window popup = window.getWindow("Notepad");
  Button btn = popup.getButton("Don't Save");

  btn.click();

  logger.info("All closed down now");
             
```