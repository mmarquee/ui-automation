package mmarquee.scala.automation

import com.sun.jna.platform.win32.COM.{COMUtils, Unknown}
import com.sun.jna.platform.win32.Variant.VARIANT
import com.sun.jna.platform.win32.{Guid, OleAuto, Variant, WinNT}
import com.sun.jna.ptr.PointerByReference
import mmarquee.automation._
import mmarquee.uiautomation._

class UIAutomation {

  /**
    * The wrapper for the Ole32 library.
    */
  private var ole32: Ole32Wrapper = _

  /**
    * Main automation interface.
    */
  private var automation: IUIAutomation = _

  /**
    * Initialize the automation library.
    * */
  this.initialize()

  private def initialize(): Unit = {
    this.ole32 = new Ole32Wrapper

    val pbr1: PointerByReference = new PointerByReference

    val result: WinNT.HRESULT =
      getOle32Unknown.QueryInterface(
        new Guid.REFIID(IUIAutomation.IID), pbr1)
    if (COMUtils.SUCCEEDED(result))
      this.automation = IUIAutomationConverter.pointerToInterface(pbr1)
    else
      throw new Exception // Handle a bad situation
  }

  private def getOle32Unknown: Unknown = {
    return ole32.getUnknown
  }

  private def getRootElement(element: PointerByReference): Int =
    this.automation.getRootElement(element)

  def getRootElement: Option[Element] = {
    val pRoot = new PointerByReference

    this.getRootElement(pRoot)

    val uRoot = new Unknown(pRoot.getValue)

    val result0 =
      uRoot.QueryInterface(
        new Guid.REFIID(IUIAutomationElement.IID), pRoot)

    if (COMUtils.SUCCEEDED(result0))
      Some(new Element(IUIAutomationElementConverter.pointerToInterface(pRoot)))
    else
      None
  }

  def getElement(controlType: ControlType,
                  title: String,
                  treeScopeConstant: Int,
                  numberOfRetries: Int): Option[Element] = {
    val foundElement = null

    val pCondition =
      this.createAndCondition(
        this.createNamePropertyCondition(title).get,
        this.createControlTypeCondition(controlType).get)

    foundElement =
      this.getRootElement.findFirst(
        new TreeScope(treeScopeConstant), pCondition.get)

    if (foundElement == null)
      None
    else
      Option(new Element(foundElement))
  }

  def createNamePropertyCondition(name: String): Option[PointerByReference] = {
    val variant = new VARIANT.ByValue
    val sysAllocated = OleAuto.INSTANCE.SysAllocString(name)
    variant.setValue(Variant.VT_BSTR, sysAllocated)

    try
      this.createPropertyCondition(PropertyID.Name.getValue, variant)
    finally
      OleAuto.INSTANCE.SysFreeString(sysAllocated)
  }

  def createControlTypeCondition(id: ControlType): Option[PointerByReference] = {
    val variant = new VARIANT.ByValue
    variant.setValue(Variant.VT_INT, id.getValue)
    this.createPropertyCondition(PropertyID.ControlType.getValue, variant)
  }

  def createAndCondition(pCondition1: PointerByReference,
                         pCondition2: PointerByReference): Option[PointerByReference] = {
    val pbr = new PointerByReference
    val res =
      this.automation.createAndCondition(pCondition1.getValue,
                                         pCondition2.getValue,
                                         pbr)
    if (res == 0)
      Some(pbr)
    else
      None
  }

  def createPropertyCondition(id: Int, value: VARIANT.ByValue): Option[PointerByReference] = {
    val pCondition = new PointerByReference
    val res = this.automation.createPropertyCondition(id, value, pCondition)
    if (res == 0) {
      val unkCondition = new Unknown(pCondition.getValue)
      val pUnknown = new PointerByReference
      val result1 = unkCondition.QueryInterface(new Guid.REFIID(IUIAutomationCondition.IID), pUnknown)
      if (COMUtils.SUCCEEDED(result1))
        Some(pCondition)
      else
        None
    }
    else
      None
  }
}
