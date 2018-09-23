package mmarquee.scala.automation

import com.sun.jna.ptr.PointerByReference
import mmarquee.automation.AutomationException
import mmarquee.uiautomation.{IUIAutomationElement}

class Element (val element: IUIAutomationElement) {
  def getName : String = {
    val sr: PointerByReference = new PointerByReference

    val res: Int = this.element.getCurrentName(sr)
    if (res != 0) {
      throw new AutomationException(res)
    }

    if (sr.getValue == null) {
      return ""
    }
    else {
      return sr.getValue.getWideString(0)
    }
  }
}