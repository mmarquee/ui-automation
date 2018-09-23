package mmarquee.scala.automation

import mmarquee.automation.utils.Utils
import mmarquee.scala.automation.control.Window

class Application (auto: UIAutomation) {
  private var process: Process = _
  private var window: Window = _

  def launch (cmd: String): Unit = {
    this.process = Utils.startProcess(cmd)
    this.window = auto.getDesktopWindow("Untitled - Notepad")
  }
}
