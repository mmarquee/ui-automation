## sbt
To add the library as a prerequisite, use the following entry in the build.sbt file

```scala
  libraryDependencies += "com.github.mmarquee" % "ui-automation " & "0.4.2"
```

## Examples

This class encapsulates some simple automation of the Notepad program, starting the application, adding text, exiting the application and confirming exit

```scala
  class NotepadAutomation {
    private var automation = UIAutomation.getElement
    private var application: AutomationApplication = _
    private var window: AutomationWindow = _

    def launch(): Unit = {
        this.application = this.automation.launch("notepad.exe")

        this.window = automation.getDesktopWindow("Untitled - Notepad")
    }

    def addText(text: String): Unit = {
        val editor = this.window.getEditBox("Edit1")
        editor.setValue(text)
    }

    def clickExit(): Unit = {
        val menu = this.window.getMainMenu(1)
        menu.getMenuItem ("File", "Exit").click
    }

    def confirmExit(): Unit = {
        val confirm = this.window.getWindow("Notepad")
        confirm.getButton("Don't Save").click
    }
  }

  ...
  val notepad = NotepadAutomation()
  notepad.launch
  notepad.addText("This is automated from Scala")
  notepad.clickExit
  notepad.confirmExit
  ...

```
