package mmarquee.scala.automation

import com.sun.jna.platform.win32.COM.COMUtils
import com.sun.jna.platform.win32.Guid
import com.sun.jna.ptr.PointerByReference
import mmarquee.scala.automation.control.BaseElement
import mmarquee.uiautomation.{IUIAutomationElement, IUIAutomationElementConverter, TreeScope}

class Element (val element: IUIAutomationElement) extends BaseElement {
  def getName : Option[String] = {
    val sr: PointerByReference = new PointerByReference

    val res = this.element.getCurrentName(sr)
    if (res != 0) {
      None
    } else {
      if (sr.getValue == null) {
        Some("")
      }
      else {
        Some(sr.getValue.getWideString(0))
      }
    }
  }

  def findFirst(scope: TreeScope, pCondition: PointerByReference): Option[Element] = {
    val pbr = new PointerByReference
    this.element.findFirst(scope, pCondition.getValue, pbr)
    val elem = getAutomationElementFromReference(pbr)

    elem match {
      case Some(element) => Some(new Element(element))
      case None => None
    }
  }

  def getAutomationElementFromReference(pbr: PointerByReference): Option[IUIAutomationElement] = {
    val unknown = makeUnknown(pbr.getValue)
    val result0 = unknown.QueryInterface(new Guid.REFIID(IUIAutomationElement.IID), pbr)

    if (COMUtils.FAILED(result0))
      None
    else
      Some(IUIAutomationElementConverter.pointerToInterface(pbr))
  }
}