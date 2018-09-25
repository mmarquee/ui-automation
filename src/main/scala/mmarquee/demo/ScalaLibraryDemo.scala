package mmarquee.demo

import mmarquee.scala.automation.control.Window
import mmarquee.scala.automation.{Application, Element, UIAutomation}

object ScalaLibraryDemo {
  def main(args: Array[String]): Unit = {
    /* Initialize the library */
    val auto: UIAutomation = new UIAutomation

    /* Get the root element */
    val root: Element = auto.getRootElement.get

    /* Get the root name */
    val name = root.getName

    Console.println(name.get)

    val app: Application = new Application(auto)
    app.launch("notepad.exe")

    val window: Window = app.getWindow("Untitled - Notepad").get

   // window.maximize

    //window.maximize
  }
}
