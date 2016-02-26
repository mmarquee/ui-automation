package mmarquee.automation;

/**
 * Created by inpwt on 26/02/2016.
 */
public class TestNotepad {
    public void run() {

        UIAutomation automation = new UIAutomation();

        AutomationApplication application = null;

        try {
            application = automation.launchOrAttach("notepad.exe");
        } catch (Throwable ex) {
            // Smother
        }

        // Wait for the process to start
        application.waitForInputIdle(5000);

        AutomationWindow window = automation.getDesktopWindow("Untitled - Notepad");
        window.focus();

        AutomationDocument document = window.getDocument(0);
        //document.setText("This is a journey into sound");

//        document.setName("This is a journey into sound");

/*
		AutomationMainMenu menu = window.getMainMenu();

		AutomationMenuItem exit = menu.getMenuItem("File", "Exit");
		exit.click();
		*/
    }
}
