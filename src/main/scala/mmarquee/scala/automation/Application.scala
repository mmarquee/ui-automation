package mmarquee.scala.automation

import mmarquee.automation.utils.Utils

class Application (auto: UIAutomation) {
  private var process: Process = _

  def launch (cmd: String): Unit = {
    this.process = Utils.startProcess(cmd)
  }
}
