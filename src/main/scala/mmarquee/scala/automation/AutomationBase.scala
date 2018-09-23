package mmarquee.scala.automation

import com.sun.jna.Pointer
import com.sun.jna.platform.win32.COM.Unknown

trait AutomationBase {
  def makeUnknown(pvInstance: Pointer) = new Unknown(pvInstance)
}
