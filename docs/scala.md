## sbt
To add the library as a prerequisite, use the following entry to the library dependencies in the `build.sbt` file

```
  libraryDependencies += "com.github.mmarquee" % "ui-automation " & "0.4.3"
```

## Examples

The sample code encapsulates some simple automation of the Notepad program, starting the application, adding text, exiting the application and confirming exit

To simplify the code, the standard behaviour of the application has been encapsulated in the `NotepadApplication` class - ee the `main\Scala` folder for the full code

```scala
  object AutomationTest {
 
   var notepad: NotepadApplication = _
 
   def start(): Unit = {
     notepad = new NotepadApplication()
 
     notepad.launch
     notepad.addText("Hello there")
     notepad.clickExit()
     val confirm = notepad.getConfirm
 
     if (confirm != null) {
       System.out.println(s"Found the confirmation popup")
     } else {
       System.out.println(s"Didn't find confirmation window")
     }
 
     notepad.confirmExit()
   }
 
   def main(args: Array[String]) {
     start()
   }
 }

```
