package mmarquee.scala.automation

import mmarquee.automation.ControlType
import mmarquee.automation.utils.Utils
import mmarquee.scala.automation.control.Window
import mmarquee.uiautomation.TreeScope

class Application (auto: UIAutomation) {
  private var process: Process = _
  private var window: Window = _

  def launch (cmd: String): Unit = {
    this.process = Utils.startProcess(cmd)
  }

  private val FIND_DESKTOP_ATTEMPTS = 25

  def getWindow(title: String) : Option[Window] =
    getWindow(title, FIND_DESKTOP_ATTEMPTS)

  def getWindow(title: String, retries: Int) : Option[Window] = {
    auto.getElement(ControlType.Window, title, TreeScope.Children, retries) match {
      case Some(i) => Some(Window(i))
      case None => None
    }
  }
}
