package mmarquee.scala.automation

import com.sun.jna.platform.win32.COM.COMUtils
import com.sun.jna.platform.win32.Guid
import com.sun.jna.platform.win32.Variant.VARIANT
import com.sun.jna.ptr.PointerByReference
import mmarquee.automation.{AutomationException, ElementNotFoundException}
import mmarquee.uiautomation.{IUIAutomationElement, IUIAutomationElementConverter, TreeScope}

class Element (val element: IUIAutomationElement) extends AutomationBase {

  def findFirst(scope: TreeScope, pCondition: PointerByReference): Element = {
    val pbr = new PointerByReference
    this.element.findFirst(scope, pCondition.getValue, pbr)
    try {
      new Element(getAutomationElementFromReference(pbr))
    } catch {
      case npe: NullPointerException =>
        throw new ElementNotFoundException
    }
  }

  def getAutomationElementFromReference(pbr: PointerByReference): IUIAutomationElement = {
    val unknown = makeUnknown(pbr.getValue)
    val result0 = unknown.QueryInterface(new Guid.REFIID(IUIAutomationElement.IID), pbr)
    if (COMUtils.FAILED(result0)) throw new AutomationException(result0.intValue)
    IUIAutomationElementConverter.pointerToInterface(pbr)
  }

  def getName : String = {
    val sr = new PointerByReference

    val res = this.element.getCurrentName(sr)
    if (res != 0) {
      throw new AutomationException(res)
    }

    if (sr.getValue == null) {
      ""
    } else {
      sr.getValue.getWideString(0)
    }
  }

  def getPropertyValue(propertyId: Int) : Any = {
    val value = new VARIANT.ByReference

    val res = this.element.getCurrentPropertyValue(propertyId, value)
    if (res != 0) throw new AutomationException(res)

    value.getValue
  }

  def getPattern(patternId: Int): PointerByReference = {
    val pbr = new PointerByReference
    val res = this.element.getCurrentPattern(patternId, pbr)
    if (res != 0) throw new AutomationException(res)
    pbr
  }

}