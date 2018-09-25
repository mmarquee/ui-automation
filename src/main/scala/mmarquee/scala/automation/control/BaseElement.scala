package mmarquee.scala.automation.control

import com.sun.jna.Pointer
import com.sun.jna.platform.win32.COM.Unknown

trait BaseElement {
  def makeUnknown(pvInstance: Pointer) = new Unknown(pvInstance)
}
