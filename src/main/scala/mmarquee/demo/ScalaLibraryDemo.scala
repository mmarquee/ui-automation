package mmarquee.demo

import mmarquee.scala.automation.{Application, Element, UIAutomation}

object ScalaLibraryDemo {
  def main(args: Array[String]): Unit = {
    /* Initialize the library */
    val auto: UIAutomation = new UIAutomation

    /* Get the root element */
    val root: Element = auto.getRootElement

    /* Get the root name */
    val name = root.getName

    Console.println(name)

    val app: Application = new Application(auto)
    app.launch("notepad.exe")
  }
}
